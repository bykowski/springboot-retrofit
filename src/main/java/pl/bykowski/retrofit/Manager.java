package pl.bykowski.retrofit;


import pl.bykowski.common.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RestController;
import retrofit.Response;

import java.io.IOException;
import java.util.List;

@RestController
public class Manager {

    private RetrofitClient retrofitClient;

    @Autowired
    public Manager(RetrofitClient retrofitClient) {
        this.retrofitClient = retrofitClient;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runExample() {
        // get example product
        Product keyboard = getProductByName("Keyboard");
        System.out.println(keyboard);

        // add example product
        addProduct(new Product("Printer", 30));

        // get all products
        List<Product> products = getAllProducts();
        products.stream().forEach(System.out::println);
    }

    private ProductService getProductServiceImpl() {
        return retrofitClient.getRetrofitClient().create(ProductService.class);
    }

    private List<Product> getAllProducts() {
        ProductService service = getProductServiceImpl();
        Response<List<Product>> response = null;
        try {
            response = service.getProduct().execute();
        } catch (IOException e) {
            //TODO exception handler
            e.printStackTrace();
        }
        return response.body();
    }

    private void addProduct(Product product) {
        ProductService service = getProductServiceImpl();
        try {
            service.addProduct(product).execute();
        } catch (IOException e) {
            //TODO exception handler
            e.printStackTrace();
        }
    }

    private Product getProductByName(String productName) {
        ProductService service = getProductServiceImpl();
        Response<Product> keyboard = null;
        try {
            keyboard = service.getProduct(productName).execute();
        } catch (IOException e) {
            //TODO exception handler
            e.printStackTrace();
        }
        return keyboard.body();
    }
}
