package pl.swrobel.ecommerce.productcatalog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class SqlProductStorageTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUpDatabase() {
        jdbcTemplate.execute("DROP TABLE `product_catalog__products` IF EXISTS");

        var sql = """
                CREATE TABLE `product_catalog__products` (
                id VARCHAR(100) NOT NULL,
                name VARCHAR(100) NOT NULL,
                description VARCHAR(100) NOT NULL,
                price VARCHAR(100),
                cover VARCHAR(100),
                PRIMARY KEY(id)
                )
                """;
        jdbcTemplate.execute(sql);

    }
    private Product thereIsProduct() {
        return new Product(
                UUID.randomUUID(),
                "test1",
                "desc"
        );
    }

    private ProductStorage thereIsStorage() {
        return new SqlProductStorage(jdbcTemplate);
    }

    @Test
    void helloWorldSql() {
        var sql = """
            select now();
    """;
        var result = jdbcTemplate.queryForObject(sql, String.class);
        assert result.contains("2025");


    }
    @Test
    void itCreateTable() {

        var result = jdbcTemplate.queryForObject("select count(*) from `product_catalog__products`", Integer.class);
        assert result == 0;
    }

    @Test
    void ItAllowstoInsertIntoTable() {
        var sql = """
                CREATE TABLE `product_catalog_products` (
                id VARCHAR(255) NOT NULL,
                name VARCHAR(255),
                description VARCHAR(255),
                price FLOAT,
                cover Varchar(255),
                PRIMARY KEY (id)
                )
                """;
        jdbcTemplate.execute(sql);

        var insertSql = """
                 INSERT INTO `product_catalog__products` (id, name, description) 
                 VALUES ('2e17235b-2fdd-4582-ad22-0f423d4977d0', 'product 1', 'not nice'),
                        ('2e17235b-2fdd-4582-ad22-0f423d4977d2', 'product 2', 'not nice')
                """;
        jdbcTemplate.execute(insertSql);
        var result = jdbcTemplate.queryForObject("select count(*) from `product_catalog_products`", Integer.class);
        assert  result == 2;
    }

    @Test
    void ItAllowstoInsertIntoTableWithArguments() {
        var sql = """
                CREATE TABLE `product_catalog_products` (
                id VARCHAR(255) NOT NULL,
                name VARCHAR(255) NOT NULL,
                description VARCHAR(255) NOT NULL,
                price FLOAT,
                cover Varchar(255),
                PRIMARY KEY (id)
                )
                """;
        jdbcTemplate.execute(sql);

        var insertSql = """
                 INSERT INTO `product_catalog__products` (id, name, description) 
                 VALUES (?,?,?)
                """;
        jdbcTemplate.update(insertSql, "bf560a60-6974-43ad-8996-25aa510b4a19","product 1", "nice");
        var result = jdbcTemplate.queryForObject("select count(*) from `product_catalog_products`", Integer.class);
        assert  result == 1;
    }

    @Test
    void ItAllowstoInsertIntoTableWithNamedArguments() {

        var insertSql = """
                 INSERT INTO `product_catalog__products` (id, name, description) 
                 VALUES 
                     (':id', ':name', ':desc')
                """;
        Map<String, Object> params = new HashMap<>();
        params.put("id", "4d2109e8-e7aa-49cf-9877-55b676a08088");
        params.put("desc", "products");
        params.put("name", "products1");

        var namedJdbc = new NamedParameterJdbcTemplate(jdbcTemplate);
        namedJdbc.update(insertSql, params);

        jdbcTemplate.update(insertSql, "bf560a60-6974-43ad-8996-25aa510b4a19","product1", "nice");
        var result = jdbcTemplate.queryForObject("select count(*) from `product_catalog_products`", Integer.class);
        assert  result == 1;

    }

    @Test
    void itSavesAndLoadsProduct() {
        Product product = thereIsProduct();
        ProductStorage storage = thereIsStorage();

        storage.save(product);
        var loaded = storage.getProductById(product.getId());

        assert loaded.getId().equals(product.getId());
        assert loaded.getName().equals(product.getName());

    }



}

