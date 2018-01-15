package org.parthapp.android.news.Network;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by parth on 1/7/2018.
 */
public class Client {

    public static final String BASE_URL = "https://newsapi.org/";
    public static Retrofit retrofit = null;


    public static Retrofit getClient(){

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
