package com.swiftsynq.charis.cryptosearch.Infrastructures;

import com.squareup.otto.Bus;

/**
 * Created by TEMIDJOY on 8/15/2017.
 */

public class BusProvider {

    private static final Bus BUS = new Bus();

    public static Bus getInstance(){
        return BUS;
    }

    public BusProvider(){}
}