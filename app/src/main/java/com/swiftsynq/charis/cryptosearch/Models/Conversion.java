package com.swiftsynq.charis.cryptosearch.Models;

/**
 * Created by TEMIDJOY on 10/16/2017.
 */

public class Conversion {
    public String imageUrl;
    public String currencyName;
    public String rate;
    public String crypoName;

    public Conversion(String imageUrl, String currencyName, String rate, String crypoName) {
        this.imageUrl = imageUrl;
        this.currencyName = currencyName;
        this.rate = rate;
        this.crypoName = crypoName;
    }
    public Conversion()
    {

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getCrypoName() {
        return crypoName;
    }

    public void setCrypoName(String crypoName) {
        this.crypoName = crypoName;
    }
}
