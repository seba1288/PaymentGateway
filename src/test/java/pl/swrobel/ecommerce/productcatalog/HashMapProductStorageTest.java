//package pl.swrobel.ecommerce.productcatalog;
//
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class HashMapProductStorageTest {
////
//    @Test
//    void itSavesAndLoadProduct() {
//        Product product = thereIsProduct();
//        ProductStorage storage = thereIsStorage();
//
//        storage.save(product);
//
//        var loaded  = storage.loadProductById(product.getId());
//
//        assertEquals(product.getId(), loaded.getId());
//        assertEquals(product.getDescription(), loaded.getDescription());
//    }
//
//    private Product thereIsProduct() {
//        return  new Product(UUID.randomUUID(),
//                "first it is product",
//                "yep product"
//        );
//    }
//
//    private HashMapProductStorage thereIsStorage() {
//        return  new HashMapProductStorage();
//
//    }
//
//    @Test
//    void itLoadsAllProducts() {
//        Product product = thereIsProduct();
//        ProductStorage storage = thereIsStorage();
//
//        storage.save(product);
//
//        List<Product> all = storage.allProducts();
//
//        assertEquals(1,all.size());
//    }
//}
