package com.swiftsynq.charis.cryptosearch.Events;


import com.swiftsynq.charis.cryptosearch.Models.CurrencyResource;
import com.swiftsynq.charis.cryptosearch.Models.ServerResource;

/**
 * Created by TEMIDJOY on 8/15/2017.
 */

public class ServerEvent {
    private ServerResource serverResponse;

    public ServerEvent(ServerResource serverResponse) {
        this.serverResponse = serverResponse;
    }

    public ServerResource getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(ServerResource serverResponse) {
        this.serverResponse = serverResponse;
    }
}