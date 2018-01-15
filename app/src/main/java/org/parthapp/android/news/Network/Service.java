package org.parthapp.android.news.Network;

import org.parthapp.android.news.Pojo.Result;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by parth on 12/31/2017.
 */
public interface Service {

    //String BASE_URL="https://newsapi.org/";

    @GET("v2/everything?sortBy=popularity&apiKey=39dccf90039941938d17156338e5b9f8")
    Call<Result> getNews(@Query("q")String query, @Query("from")String from, @Query("page")int page,@Query("sources")String orderBy);

}
