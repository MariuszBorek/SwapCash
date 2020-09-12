package pl.exchangeapp.conection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pl.exchangeapp.enums.Currency;
import pl.exchangeapp.enums.DataFormat;

import java.io.IOException;

public class Client {
    private static Client client;
    private final OkHttpClient okHttpClient = new OkHttpClient();

    private Client() {
    }

    public static Client getInstance() {
        if (client == null) {
            synchronized (Client.class) {
                if (client == null) {
                    client = new Client();
                }
            }
        }

        return client;
    }

    public String executeRequest(Currency currency, DataFormat dataFormat) throws IOException {
        Request newRequest= new RequestBuilder()
                .currency(currency)
                .dataFormat(dataFormat)
                .build();
        Response response = okHttpClient.newCall(newRequest).execute();
        if(response.isSuccessful()) {
            return response.body().string();
        }
        return "";
    }

}
