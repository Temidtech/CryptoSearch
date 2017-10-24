package com.swiftsynq.charis.cryptosearch.Models;

/**
 * Created by TEMIDJOY on 10/20/2017.
 */

public class CryptoCoins {
    public String Symbol;
    public String imageUrl;
    public String cryptoName;

    public CryptoCoins(String symbol, String imageUrl, String cryptoName) {
        Symbol = symbol;
        this.imageUrl = imageUrl;
        this.cryptoName = cryptoName;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCryptoName() {
        return cryptoName;
    }

    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }
}
