package com.swiftsynq.charis.cryptosearch.Interfaces;

import com.swiftsynq.charis.cryptosearch.Models.CurrencyResource;
import com.swiftsynq.charis.cryptosearch.Models.ServerResource;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by TEMIDJOY on 8/15/2017.
 */

 public interface APIInterface {
    @GET("/data/price")
    Call<Object> compareCurrency(@Query("fsym")String fsym, @Query("tsyms") String tsyms);
    @GET("/api/data/coinlist/")
    Call<CurrencyResource> CoinsList();
}
