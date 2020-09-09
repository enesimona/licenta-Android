package com.example.enesimona30.licenta1.utils;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * Created by enesimona30 on 10/04/17.
 */

public class Masa extends PropertyInfo implements Serializable, KvmSerializable{
    private Integer idMasa;
    private Integer restaurant;
    private Integer capacitate;

    public Integer getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Integer restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getIdMasa() {
        return idMasa;
    }

    public void setIdMasa(Integer idMasa) {
        this.idMasa = idMasa;
    }


    public Integer getCapacitate() {
        return capacitate;
    }

    public void setCapacitate(Integer capacitate) {
        this.capacitate = capacitate;
    }

    public Masa() {
    }

    public Masa(Integer restaurant, Integer capacitate) {
        this.restaurant = restaurant;
        this.capacitate = capacitate;
    }

    @Override
    public String toString() {
        return "Masa{" +
                "idMasa=" + idMasa +
                ", idRestaurant=" + restaurant +
                ", capacitate=" + capacitate +
                '}';
    }

    @Override
    public Object getProperty(int i) {
        switch (i){
            case 0:
                return idMasa;
            case 1:
                return restaurant;
            case 2:
                return capacitate;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 3;
    }

    @Override
    public void setProperty(int i, Object value) {
            switch (i){
                case 0:
                    this.idMasa= Integer.valueOf(value.toString());
                    break;
                case 1:
                    this.restaurant=Integer.valueOf(value.toString());
                    break;
                case 2:
                    this.capacitate=Integer.valueOf(value.toString());
                    break;
                default:
                    break;
            }
    }

    @Override
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {
            switch (index){
                case 0:
                    info.type = PropertyInfo.INTEGER_CLASS;
                    info.name = "idMasa";
                    break;
                case 1:
                    info.type = PropertyInfo.INTEGER_CLASS;
                    info.name = "restaurant";
                    break;
                case 2:
                    info.type = PropertyInfo.INTEGER_CLASS;
                    info.name = "capacitate";
                    break;
                default:
                    break;
            }
    }
}
