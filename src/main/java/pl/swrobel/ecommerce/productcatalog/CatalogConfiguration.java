package pl.swrobel.ecommerce.productcatalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;


@Configuration
public class CatalogConfiguration {

    @Bean
    ProductCatalog createMyProductCatalog(ProductStorage storage) {
        var catalog =  new ProductCatalog(
                storage
        );
        catalog.createProduct("nice one 1","yep");
        catalog.createProduct("nice one 2","yep");
        catalog.createProduct("nice one 3","yep");
        return catalog;
    }

    @Bean
    ProductStorage createMyStorage(JdbcTemplate jdbcTemplate) {
        return new SqlProductStorage(jdbcTemplate);
    }
}
