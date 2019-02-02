package pl.bykowski.api;

import org.springframework.web.bind.annotation.*;
import pl.bykowski.common.Product;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductApi {

    private List<Product> productList;

    public ProductApi() {
        productList = new ArrayList<>();
        productList.add(new Product("Keyboard", 20));
        productList.add(new Product("Mouse", 10));
    }

    @GetMapping("/getProducts")
    public List<Product> getProducts() {
        return productList;
    }

    @GetMapping("/getProductsByName/{name}")
    public Product getProducts(@PathVariable("name") String name) {
        return productList.stream().filter(item -> item.getName().equals(name)).findFirst().get();
    }

    @PostMapping("/addProduct")
    public void addProduct(@RequestBody Product product) {
        productList.add(product);
    }
}
