package com.example.enesimona30.licenta1.utils;

import android.os.Parcel;
import android.os.Parcelable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

/**
 * Created by enesimona30 on 25/02/17.
 */

public class Utilizator extends PropertyInfo implements Serializable, KvmSerializable, Constants {

    private Integer id;
    private String nume;
    private String prenume;
    private Date data_nasterii;
    private Boolean sex;
    private String email;
    private String telefon;
    private String nume_utilizator;
    private String parola;

    public Utilizator(){}

    public Utilizator(Integer id, String nume, String prenume, Date data_nasterii, Boolean sex, String email, String Telefon, String nume_utilizator, String parola) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.data_nasterii = data_nasterii;
        this.sex = sex;
        this.email = email;
        this.telefon = Telefon;
        this.nume_utilizator = nume_utilizator;
        this.parola = parola;
    }

    public Utilizator(String nume, String prenume, Date data_nasterii, Boolean sex, String email, String Telefon, String nume_utilizator, String parola) {
        this.nume = nume;
        this.prenume = prenume;
        this.data_nasterii = data_nasterii;
        this.sex = sex;
        this.email = email;
        this.telefon = Telefon;
        this.nume_utilizator = nume_utilizator;
        this.parola = parola;
    }

    public Utilizator(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public Date getData_nasterii() {
        return data_nasterii;
    }

    public void setData_nasterii(Date data_nasterii) {
        this.data_nasterii = data_nasterii;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String Telefon) {
        this.telefon = Telefon;
    }

    public String getNume_utilizator() {
        return nume_utilizator;
    }

    public void setNume_utilizator(String nume_utilizator) {
        this.nume_utilizator = nume_utilizator;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }


    @Override
    public Object getProperty(int i) {
        switch (i) {
            case 0: return id;
            case 1: return nume;
            case 2: return prenume;
            case 3: return data_nasterii;
            case 4: return sex;
            case 5: return email;
            case 6: return telefon;
            case 7: return nume_utilizator;
            case 8: return parola;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 9;
    }

    @Override
    public void setProperty(int i, Object value) {
        switch(i) {
            case 0:
                this.id = Integer.parseInt(value.toString());
                break;
            case 1:
                this.nume = value.toString();
                break;
            case 2:
                this.prenume = value.toString();
                break;
            case 3:
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    this.data_nasterii = (Date) df.parse(value.toString().replace("T", " "));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                this.sex = Boolean.parseBoolean(value.toString());
                break;
            case 5:
                this.email = value.toString();
                break;
            case 6:
                this.telefon = value.toString();
                break;
            case 7:
                this.nume_utilizator = value.toString();
                break;
            case 8:
                this.parola = value.toString();
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
                info.name = "id";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "nume";
                break;
            case 2:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "prenume";
                break;
            case 3:
                info.type = Date.class;
                info.name = "data_nasterii";
                break;
            case 4:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "sex";
                break;
            case 5:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "email";
                break;
            case 6:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "telefon";
                break;
            case 7:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "nume_utilizator";
                break;
            case 8:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "parola";
                break;
            default:break;
        }

    }

    @Override
    public String toString() {
        return "Utilizator{" +
                "id=" + id +
                '}';
    }
}
