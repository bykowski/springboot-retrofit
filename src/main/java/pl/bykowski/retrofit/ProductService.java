package pl.bykowski.retrofit;

import pl.bykowski.common.Product;
import retrofit.Call;
import retrofit.http.*;

import java.util.List;

public interface ProductService {

    @GET("/getProducts")
    Call<List<Product>> getProduct();

    @GET("/getProductsByName/{name}")
    Call<Product> getProduct(@Path("name") String name);

    @POST("/addProduct")
    Call<Void> addProduct(@Body Product product);
}
