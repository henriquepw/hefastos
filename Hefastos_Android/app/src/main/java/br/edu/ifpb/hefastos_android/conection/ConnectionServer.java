package br.edu.ifpb.hefastos_android.conection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Gabriel on 07/04/2017.
 */

public class ConnectionServer {

    private static final String URL_BASE = "http://34.209.165.216:8080/RestEasyApp/";

    private static APIservice service;
    private static ConnectionServer instance = new ConnectionServer();

    public static ConnectionServer getInstance() {
        return instance;
    }

    public APIservice getService() {
        return service;
    }

    private ConnectionServer() {
        updateServiceAdress();
    }

    public void updateServiceAdress() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(APIservice.class);

    }

}
