package com.swiftsynq.charis.cryptosearch.Events;


import com.swiftsynq.charis.cryptosearch.Models.CurrencyResource;
import com.swiftsynq.charis.cryptosearch.Models.ServerResource;

/**
 * Created by TEMIDJOY on 8/15/2017.
 */

public class CoinsServerEvent {
    private CurrencyResource serverResponse;

    public CoinsServerEvent(CurrencyResource serverResponse) {
        this.serverResponse = serverResponse;
    }

    public CurrencyResource getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(CurrencyResource serverResponse) {
        this.serverResponse = serverResponse;
    }
}