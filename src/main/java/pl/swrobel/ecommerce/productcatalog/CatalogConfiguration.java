package pl.swrobel.ecommerce.productcatalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatalogConfiguration {

    @Bean
    ProductCatalog createMyProductCatalog() {
        var catalog =  new ProductCatalog(
                new ArrayListProductStorage()
        );
        catalog.createProduct("nice one 1","yep");
        catalog.createProduct("nice one 2","yep");
        catalog.createProduct("nice one 3","yep");
        return catalog;
    }
}
