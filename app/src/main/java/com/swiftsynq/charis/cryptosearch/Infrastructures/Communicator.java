package com.swiftsynq.charis.cryptosearch.Infrastructures;

import android.util.Log;

import com.squareup.otto.Produce;
import com.swiftsynq.charis.cryptosearch.Events.CoinsServerEvent;
import com.swiftsynq.charis.cryptosearch.Events.CoinsErrorEvent;
import com.swiftsynq.charis.cryptosearch.Events.ExRateErrorEvent;
import com.swiftsynq.charis.cryptosearch.Events.ServerEvent;
import com.swiftsynq.charis.cryptosearch.Interfaces.APIInterface;
import com.swiftsynq.charis.cryptosearch.Models.CurrencyResource;
import com.swiftsynq.charis.cryptosearch.Models.ServerResource;
import com.swiftsynq.charis.cryptosearch.Util.Constant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TEMIDJOY on 10/13/2017.
 */

public class Communicator {
    private static final String TAG = "Communicator";

    public void FindExRate(String fsym,String tsym) {

        //Here a logging interceptor is created
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //The logging interceptor will be added to the http client
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        //The Retrofit builder will have the client attached, in order to get connection logs
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.SERVER_URL)
                .build();
        APIInterface service = retrofit.create(APIInterface.class);

        Call<Object> call = service.compareCurrency(fsym,tsym);

        call.enqueue(new Callback<Object>() {

            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                BusProvider.getInstance().post(response.body());

                Log.e(TAG, "Success");

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                // handle execution failures like no internet connectivity
                BusProvider.getInstance().post(produceExRateError(2, "Operation Failed !You are not connected to the Internet."));

                Log.e(TAG, "Failure"+t.getMessage());
            }
        });
    }
    public void CoinsList() {

        //Here a logging interceptor is created
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //The logging interceptor will be added to the http client
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        //The Retrofit builder will have the client attached, in order to get connection logs
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.COINS_URL)
                .build();
        APIInterface service = retrofit.create(APIInterface.class);

        Call<CurrencyResource> call = service.CoinsList();

        call.enqueue(new Callback<CurrencyResource>() {

            @Override
            public void onResponse(Call<CurrencyResource> call, Response<CurrencyResource> response) {

                BusProvider.getInstance().post(new CoinsServerEvent(response.body()));

                Log.e(TAG, "Success");

            }

            @Override
            public void onFailure(Call<CurrencyResource> call, Throwable t) {
                // handle execution failures like no internet connectivity
                BusProvider.getInstance().post(produceErrorEvent(4, "Unable to fetch coins !You are not connected to the Internet."));

                Log.e(TAG, "Failure"+t.getMessage());
            }
        });
    }
   @Produce
   public ServerEvent produceServerEvent(ServerResource serverResponse) {
       return new ServerEvent(serverResponse);
   }

    @Produce
    public CoinsErrorEvent produceErrorEvent(int errorCode, String errorMsg) {
        return new CoinsErrorEvent(errorCode, errorMsg);
    }
    @Produce
    public ExRateErrorEvent produceExRateError(int errorCode, String errorMsg) {
        return new ExRateErrorEvent(errorCode, errorMsg);
    }
}
