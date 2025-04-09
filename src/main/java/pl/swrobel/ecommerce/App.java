package pl.swrobel.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.swrobel.productcatalog.ArrayListProductStorage;
import pl.swrobel.productcatalog.ProductCatalog;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("Welcome to Ecommerce!");

    }

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
