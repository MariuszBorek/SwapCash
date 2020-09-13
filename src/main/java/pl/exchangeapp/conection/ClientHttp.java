package pl.exchangeapp.conection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pl.exchangeapp.enums.Currency;
import pl.exchangeapp.enums.DataFormat;

import java.io.IOException;

public class ClientHttp {
    private static ClientHttp clientHttp;
    private final OkHttpClient okHttpClient = new OkHttpClient();

    private ClientHttp() {
    }

    public static ClientHttp getInstance() {
        if (clientHttp == null) {
            synchronized (ClientHttp.class) {
                if (clientHttp == null) {
                    clientHttp = new ClientHttp();
                }
            }
        }

        return clientHttp;
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
