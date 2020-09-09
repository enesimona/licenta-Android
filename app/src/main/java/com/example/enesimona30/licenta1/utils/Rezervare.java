package com.example.enesimona30.licenta1.utils;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

/**
 * Created by enesimona30 on 02/04/17.
 */

public class Rezervare extends PropertyInfo implements Serializable, KvmSerializable {
    private Integer idRezervare;
    private Alocare alocare;
    private Utilizator utilizator;

    public Rezervare() {
    }

    public Rezervare(Integer idRezervare, Alocare alocare, Utilizator utilizator) {
        this.idRezervare = idRezervare;
        this.alocare = alocare;
        this.utilizator = utilizator;
    }

    public Rezervare(Alocare alocare, Utilizator utilizator) {
        this.alocare = alocare;
        this.utilizator = utilizator;
    }

    public Integer getIdRezervare() {
        return idRezervare;
    }

    public void setIdRezervare(Integer idRezervare) {
        this.idRezervare = idRezervare;
    }

    public Alocare getAlocare() {
        return alocare;
    }

    public void setAlocare(Alocare alocare) {
        this.alocare = alocare;
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    @Override
    public Object getProperty(int i) {
        switch (i) {
            case 0:
                return idRezervare;
            case 1:
                return alocare;
            case 2:
                return utilizator;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 3;
    }

    @Override
    public void setProperty(int i, Object value) {
        switch(i) {
            case 0:
                this.idRezervare = Integer.parseInt(value.toString());
                break;
            case 1:
                this.alocare = (Alocare) value;
                break;
            case 2:
                this.utilizator = (Utilizator) value;
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
                info.name = "id_rezervare";
                break;
            case 1:
                info.type = alocare.getClass();
                info.name = "alocare";
                break;
            case 2:
                info.type = utilizator.getClass();
                info.name = "utilizator";
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return "Rezervare{" +
                "idRezervare=" + idRezervare +
                ", alocare=" + alocare +
                ", utilizator=" + utilizator +
                '}';
    }
}
