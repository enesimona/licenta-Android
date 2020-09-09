package com.example.enesimona30.licenta1.utils;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

/**
 * Created by enesimona30 on 06/05/17.
 */

public class NotaPlata extends PropertyInfo implements Serializable, KvmSerializable{

    private Integer idNota;
    private Date dataEmitere;
    private float valoare;
    private float procent;
    private Boolean isRedusa;
    private Utilizator utilizator;
    private RestaurantBD restaurant;

    public NotaPlata() {
    }

    public NotaPlata(Integer idNota, Date dataEmitere, float valoare, float procent, Boolean isRedusa, Utilizator utilizator, RestaurantBD restaurant) {
        this.idNota = idNota;
        this.dataEmitere = dataEmitere;
        this.valoare = valoare;
        this.procent = procent;
        this.isRedusa = isRedusa;
        this.utilizator = utilizator;
        this.restaurant = restaurant;
    }

    public NotaPlata(Date dataEmitere, float valoare, float procent, Boolean isRedusa, Utilizator utilizator, RestaurantBD restaurant) {
        this.dataEmitere = dataEmitere;
        this.valoare = valoare;
        this.procent = procent;
        this.isRedusa = isRedusa;
        this.utilizator = utilizator;
        this.restaurant = restaurant;
    }

    public Integer getIdNota() {
        return idNota;
    }

    public void setIdNota(Integer idNota) {
        this.idNota = idNota;
    }

    public Date getDataEmitere() {
        return dataEmitere;
    }

    public void setDataEmitere(Date dataEmitere) {
        this.dataEmitere = dataEmitere;
    }

    public float getValoare() {
        return valoare;
    }

    public void setValoare(float valoare) {
        this.valoare = valoare;
    }

    public float getProcent() {
        return procent;
    }

    public void setProcent(float procent) {
        this.procent = procent;
    }

    public Boolean getRedusa() {
        return isRedusa;
    }

    public void setRedusa(Boolean redusa) {
        isRedusa = redusa;
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    public RestaurantBD getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantBD restaurant) {
        this.restaurant = restaurant;
    }


    @Override
    public Object getProperty(int i) {
        switch (i){
            case 0:
                return idNota;
            case 1:
                return dataEmitere;
            case 2:
                return valoare;
            case 3:
                return procent;
            case 4:
                return isRedusa;
            case 5:
                return utilizator;
            case 6:
                return restaurant;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 7;
    }

    @Override
    public void setProperty(int i, Object value) {
        switch (i) {
            case 0:
                this.idNota = Integer.parseInt(value.toString());
                break;
            case 1:
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    this.dataEmitere = (Date) df.parse(value.toString().replace("T", " "));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                this.valoare = Float.parseFloat(value.toString());
                break;
            case 3:
                this.procent = Float.parseFloat(value.toString());
                break;
            case 4:
                this.isRedusa = Boolean.parseBoolean(value.toString());
                break;
            case 5:
                this.utilizator = (Utilizator) value;
                break;
            case 6:
                this.restaurant = (RestaurantBD) value;
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
                info.name = "idNota";
                break;
            case 1:
                info.type = Date.class;
                info.name = "dataEmitere";
                break;
            case 2:
                info.type = float.class;
                info.name = "valoare";
                break;
            case 3:
                info.type = float.class;
                info.name = "procent";
                break;
            case 4:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "isRedusa";
                break;
            case 5:
                info.type = utilizator.getClass();
                info.name = "utilizator";
                break;
            case 6:
                info.type = restaurant.getClass();
                info.name = "restaurant";
                break;
            default:break;
        }
    }

    @Override
    public String toString() {
        return "NotaPlata{" +
                "dataEmitere=" + dataEmitere +
                ", valoare=" + valoare +
                ", procent=" + procent +
                ", isRedusa=" + isRedusa +
                ", utilizator=" + utilizator +
                ", restaurant=" + restaurant +
                '}';
    }
}
