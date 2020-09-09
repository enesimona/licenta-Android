package com.example.enesimona30.licenta1.utils;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

/**
 * Created by enesimona30 on 05/04/17.
 */

public class Alocare extends PropertyInfo implements Serializable, KvmSerializable {

    private Integer id_alocare;
    private Masa masa;
    private Date data;
    private Boolean dispobilitate;

    public Alocare(Integer id_alocare, Masa masa, Date data, Boolean dispobilitate) {
        this.id_alocare = id_alocare;
        this.masa = masa;
        this.data = data;
        this.dispobilitate = dispobilitate;
    }

    public Alocare() {
    }

    public Alocare(Masa masa, Date data, Boolean dispobilitate) {
        this.masa = masa;
        this.data = data;
        this.dispobilitate = dispobilitate;
    }

    public Integer getId_alocare() {
        return id_alocare;
    }

    public void setId_alocare(Integer id_alocare) {
        this.id_alocare = id_alocare;
    }

    public Masa getMasa() {
        return masa;
    }

    public void setMasa(Masa masa) {
        this.masa = masa;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Boolean getDispobilitate() {
        return dispobilitate;
    }

    public void setDispobilitate(Boolean dispobilitate) {
        this.dispobilitate = dispobilitate;
    }

    @Override
    public Object getProperty(int i) {
        switch (i) {
            case 0:
                return id_alocare;
            case 1:
                return masa;
            case 2:
                return data;
            case 3:
                return dispobilitate;

        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 4;
    }

    @Override
    public void setProperty(int i, Object value) {
        switch(i) {
            case 0:
                this.id_alocare = Integer.parseInt(value.toString());
                break;
            case 1:
                this.masa = (Masa) value;
                break;
            case 2:
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                try {
                    this.data = (Date) df.parse(value.toString().replace("T", " "));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                this.dispobilitate = Boolean.parseBoolean(value.toString());
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
                info.name = "id_alocare";
                break;
            case 1:
                info.type = masa.getClass();
                info.name = "masa";
                break;
            case 2:
                info.type = Date.class;
                info.name = "data";
                break;
            case 3:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "dispobilitate";
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return "Alocare{" +
                "id_alocare=" + id_alocare +
                ", masa=" + masa +
                ", data=" + data +
                ", dispobilitate=" + dispobilitate +
                '}';
    }
}


