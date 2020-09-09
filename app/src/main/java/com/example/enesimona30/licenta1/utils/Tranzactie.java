package com.example.enesimona30.licenta1.utils;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

/**
 * Created by enesimona30 on 18/07/17.
 */

public class Tranzactie implements Serializable, KvmSerializable{
    private Integer id_tranzactie;
    private String nume_tranzactie;
    private Date data;
    private Integer id_utilizator;

    public Tranzactie(Integer id_tranzactie, String nume_tranzactie, Date data, Integer id_utilizator) {
        this.id_tranzactie = id_tranzactie;
        this.nume_tranzactie = nume_tranzactie;
        this.data = data;
        this.id_utilizator = id_utilizator;
    }

    public Tranzactie(String nume_tranzactie, Date data, Integer id_utilizator) {
        this.nume_tranzactie = nume_tranzactie;
        this.data = data;
        this.id_utilizator = id_utilizator;
    }

    public Integer getId_tranzactie() {
        return id_tranzactie;
    }

    public void setId_tranzactie(Integer id_tranzactie) {
        this.id_tranzactie = id_tranzactie;
    }

    public String getNume_tranzactie() {
        return nume_tranzactie;
    }

    public void setNume_tranzactie(String nume_tranzactie) {
        this.nume_tranzactie = nume_tranzactie;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getId_utilizator() {
        return id_utilizator;
    }

    public void setId_utilizator(Integer id_utilizator) {
        this.id_utilizator = id_utilizator;
    }

    @Override
    public String toString() {
        return "Tranzactie{" + "id_tranzactie=" + id_tranzactie + ", nume_tranzactie=" + nume_tranzactie + ", data=" + data + ", id_utilizator=" + id_utilizator + '}';
    }


    @Override
    public Object getProperty(int i) {
        switch (i){
            case 0: return id_tranzactie;
            case 1: return nume_tranzactie;
            case 2: return data;
            case 3: return id_utilizator;
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
                this.id_utilizator = Integer.parseInt(value.toString());
                break;
            case 1:
                this.nume_tranzactie = value.toString();
                break;
            case 2:
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    this.data = (Date) df.parse(value.toString().replace("T", " "));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                this.id_utilizator=Integer.parseInt(value.toString());
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
                info.name = "id_tranzactie";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "nume_tranzactie";
                break;
            case 2:
                info.type = Date.class;
                info.name = "data";
                break;
            case 3:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "id_utilizator";
                break;
            default:
                break;
        }
    }
}
