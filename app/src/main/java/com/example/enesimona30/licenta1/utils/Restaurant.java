package com.example.enesimona30.licenta1.utils;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * Created by enesimona30 on 10/03/17.
 */

public class Restaurant implements Serializable, Parcelable{

    private Integer idRestaurant;
    private String numeRestaurant;
    private String adresaRestaurant;
    private Double latitudine;
    private Double longitudine;
    private String numarTelefon;
    private Float rating;
    private String webSiteUri;
    private Bitmap poza;
    private String cod;
    private Integer nrMese;

    public Restaurant(Integer idRestaurant, String numeRestaurant) {
        this.idRestaurant = idRestaurant;
        this.numeRestaurant = numeRestaurant;
    }

    public Restaurant(String numeRestaurant){
        this.numeRestaurant=numeRestaurant;
    }

    public Restaurant(Integer idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public Restaurant(String adresaRestaurant, String cod, Double latitudine, Double longitudine, String numarTelefon, String numeRestaurant, Float rating, String webSiteUri) {
        this.adresaRestaurant = adresaRestaurant;
        this.cod = cod;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.numarTelefon = numarTelefon;
        this.numeRestaurant = numeRestaurant;
        this.rating = rating;
        this.webSiteUri = webSiteUri;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Bitmap getPoza() {
        return poza;
    }

    public void setPoza(Bitmap poza) {
        this.poza = poza;
    }

    protected Restaurant(Parcel in) {
        numeRestaurant = in.readString();
        adresaRestaurant = in.readString();
        numarTelefon = in.readString();
        webSiteUri = in.readString();
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    public String getAdresaRestaurant() {
        return adresaRestaurant;
    }

    public void setAdresaRestaurant(String adresaRestaurant) {
        this.adresaRestaurant = adresaRestaurant;
    }

    public Integer getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Integer idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public Double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(Double latitudine) {
        this.latitudine = latitudine;
    }

    public Double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(Double longitudine) {
        this.longitudine = longitudine;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    public String getNumeRestaurant() {
        return numeRestaurant;
    }

    public void setNumeRestaurant(String numeRestaurant) {
        this.numeRestaurant = numeRestaurant;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getWebSiteUri() {
        return webSiteUri;
    }

    public void setWebSiteUri(String webSiteUri) {
        this.webSiteUri = webSiteUri;
    }

    public Restaurant(String adresaRestaurant, Integer idRestaurant, Double latitudine, Double longitudine, String numarTelefon, String numeRestaurant, Float rating, String webSiteUri) {
        this.adresaRestaurant = adresaRestaurant;
        this.idRestaurant = idRestaurant;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.numarTelefon = numarTelefon;
        this.numeRestaurant = numeRestaurant;
        this.rating = rating;
        this.webSiteUri = webSiteUri;
    }

    public Restaurant(String adresaRestaurant, Double latitudine, Double longitudine, String numarTelefon, String numeRestaurant, Float rating, String webSiteUri) {
        this.adresaRestaurant = adresaRestaurant;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.numarTelefon = numarTelefon;
        this.numeRestaurant = numeRestaurant;
        this.rating = rating;
        this.webSiteUri = webSiteUri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(numeRestaurant);
        dest.writeString(adresaRestaurant);
        dest.writeString(numarTelefon);
        dest.writeString(webSiteUri);
    }

    public Restaurant(String adresaRestaurant, Double latitudine, Double longitudine, String numarTelefon, String numeRestaurant, Bitmap poza, Float rating, String webSiteUri) {
        this.adresaRestaurant = adresaRestaurant;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.numarTelefon = numarTelefon;
        this.numeRestaurant = numeRestaurant;
        this.poza = poza;
        this.rating = rating;
        this.webSiteUri = webSiteUri;
    }

    public Restaurant(){}

    public Integer getNrMese() {
        return nrMese;
    }

    public void setNrMese(Integer nrMese) {
        this.nrMese = nrMese;
    }


}
