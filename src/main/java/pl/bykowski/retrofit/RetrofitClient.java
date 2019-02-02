package pl.bykowski.retrofit;

import com.squareup.okhttp.OkHttpClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

@Component
public class RetrofitClient {
    public Retrofit getRetrofitClient() {
        OkHttpClient httpClient = new OkHttpClient();
        return new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
    }
}
