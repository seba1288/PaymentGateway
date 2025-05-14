package pl.swrobel.ecommerce.productcatalog;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SqlProductStorage implements ProductStorage {
    private final JdbcTemplate jdbcTemplate;

    public SqlProductStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Product> allProduct() {
        return null;
    }

    @Override
    public void add(Product newProduct) {

    }

    public void seve(Product newProduct)  {
        //if exists
        var insertSql = """
                 INSER INTO `product_catalog__products` (id, name,description) 
                 VALUES (:id, :name, :description)
                """;
        Map<String, Object> params = new HashMap<>();
        params.put("id", newProduct.getId());
        params.put("desc", newProduct.getDescription());
        params.put("name", newProduct.getName());

        var namedJdbc = new NamedParameterJdbcTemplate(jdbcTemplate);
        namedJdbc.update(insertSql, params);
    }

    @Override
    public Product getProductById(String productId) {
        // SQL --> Product
        var sql = "SELECT * FROM `product_catalog__products` WHERE id = ?";

        var result = jdbcTemplate.queryForObject(
                sql,
                (rs, i) -> {
                    var product = new Product(
                            UUID.fromString(rs.getString("ID")),
                            rs.getString("NAME"),
                            rs.getString("DESCRIPTION")
                    );
                    return product;
                });
        return result;
    }

    @Override
    public void save(Product newProduct) {

    }
}