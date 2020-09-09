package com.example.enesimona30.licenta1.utils;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * Created by enesimona30 on 07/04/17.
 */

public class RestaurantBD extends PropertyInfo implements Serializable, KvmSerializable {

    private Integer idRestaurantBD;
    private String numeRestaurantBD;

    public RestaurantBD(Integer idRestaurantBD, String numeRestaurantBD) {
        this.idRestaurantBD = idRestaurantBD;
        this.numeRestaurantBD = numeRestaurantBD;
    }

    public RestaurantBD() {
    }

    public RestaurantBD(Integer idRestaurantBD) {
        this.idRestaurantBD = idRestaurantBD;
    }

    public Integer getIdRestaurantBD() {
        return idRestaurantBD;
    }

    public void setIdRestaurantBD(Integer idRestaurantBD) {
        this.idRestaurantBD = idRestaurantBD;
    }

    public String getNumeRestaurantBD() {
        return numeRestaurantBD;
    }

    public void setNumeRestaurantBD(String numeRestaurantBD) {
        this.numeRestaurantBD = numeRestaurantBD;
    }

    @Override
    public Object getProperty(int i) {
        switch (i) {
            case 0:
                return idRestaurantBD;
            case 1:
                return numeRestaurantBD;

        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 2;
    }

    @Override
    public void setProperty(int i, Object value) {
        switch(i) {
            case 0:
                this.idRestaurantBD = Integer.parseInt(value.toString());
                break;
            case 1:
                this.numeRestaurantBD = value.toString();
                break;
            default:
                break;
        }
    }

    @Override
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {
        switch (index) {
            case 0:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "idRestaurantBD";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "numeRestaurantBD";
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return "RestaurantBD{" +
                "idRestaurantBD=" + idRestaurantBD +
                ", numeRestaurantBD='" + numeRestaurantBD + '\'' +
                '}';
    }
}
