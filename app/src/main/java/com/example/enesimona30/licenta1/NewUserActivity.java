package com.example.enesimona30.licenta1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.enesimona30.licenta1.utils.Constants;
import com.example.enesimona30.licenta1.utils.MarshalDate;
import com.example.enesimona30.licenta1.utils.Utilizator;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

import java.util.Date;

public class NewUserActivity extends AppCompatActivity implements Constants {

    private Button btnContNou;
    private EditText etNume, etPrenume, etDataNasterii, etEmail, etTelefon, etNumeUtilizator, etParola, etConfirmaParola;
    private RadioGroup rgSex;
    private RadioButton rbSexFeminin, rbSexMasculin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        initComponents();
    }

    protected void initComponents(){
        etNume=(EditText) findViewById(R.id.et_new_user_nume);
        etPrenume=(EditText) findViewById(R.id.et_new_user_prenume);
        etDataNasterii=(EditText) findViewById(R.id.et_new_user_data_nasterii);
        etEmail=(EditText) findViewById(R.id.et_new_user_email);
        etTelefon=(EditText) findViewById(R.id.et_new_user_telefon);
        etParola=(EditText) findViewById(R.id.et_new_user_parola);
        etConfirmaParola=(EditText) findViewById(R.id.et_new_user_confirma_parola);
        etNumeUtilizator=(EditText) findViewById(R.id.et_new_user_nume_utilizator);

        rgSex=(RadioGroup) findViewById(R.id.rg_new_user_sex);
        rbSexFeminin=(RadioButton) findViewById(R.id.rb_new_user_feminin);
        rbSexMasculin=(RadioButton) findViewById(R.id.rb_new_user_masculin);


        btnContNou=(Button) findViewById(R.id.btn_new_user_adauga_user);
        btnContNou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etParola.getText().toString().equals(etConfirmaParola.getText().toString())) {
                    if(validation()==true) {
                        Utilizator utilizator_nou=creazaUtilizator();
                        new CallWSRegister().execute(utilizator_nou);
                    }else {
                        Toast.makeText(getApplicationContext(), "Completeaza toate campurile!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Parola si Confirmare parola sunt diferite!", Toast.LENGTH_SHORT).show();
                }
                        }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    private Utilizator creazaUtilizator(){
        try{
            String nume=etNume.getText().toString();
            String prenume=etPrenume.getText().toString();
            Date data_nasterii=simpleDateFormat.parse(etDataNasterii.getText().toString());
            Boolean feminin;
            if(rbSexFeminin.isChecked()==true){
                feminin=true;
            }else {
                feminin=false;
            }
            String email=etEmail.getText().toString();
            String telefon=etTelefon.getText().toString();
            String nume_utilizator=etNumeUtilizator.getText().toString();
            String parola=etParola.getText().toString();

            return new Utilizator(nume, prenume, data_nasterii, feminin, email, telefon, nume_utilizator, parola);

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Eroare la creaaare utilizator!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return  null;
    }

    private boolean validation(){
        if(etNume.getText().toString()==null || etNume.getText().toString().isEmpty()){
            return false;
        }
        if(etPrenume.getText().toString()==null || etPrenume.getText().toString().isEmpty()){
            return false;
        }
        if(etDataNasterii.getText().toString()==null || etDataNasterii.getText().toString().isEmpty()){
            return false;
        }
        if(etEmail.getText().toString()==null || etEmail.getText().toString().isEmpty()){
            return false;
        }
        if(etTelefon.getText().toString()==null || etTelefon.getText().toString().isEmpty()){
            return false;
        }
        if(etParola.getText().toString()==null || etParola.getText().toString().isEmpty()){
            return false;
        }
        if(etConfirmaParola.getText().toString()==null || etConfirmaParola.getText().toString().isEmpty()){
            return false;
        }
        if(etNumeUtilizator.getText().toString()==null || etNumeUtilizator.getText().toString().isEmpty()){
            return false;
        }

        return true;
    }


    class CallWSRegister extends AsyncTask<Utilizator, View, Integer> {
        private static final String URL="http://192.168.0.28.:8080/WS/WS?WSDL"; //xml care defineste serviciul web al nostru
       // private static final String URL="http://192.168.0.16.:8080/WS/WS?WSDL";
        private static final String SOAP_ACTION="http://ws/register"; //actiune definita prin namespace ul si numele met
        private static final String NAMESPACE="http://ws/";
        private static final String METHOD_NAME="register";


        @Override
        protected Integer doInBackground(Utilizator... params) {
            SoapObject request=new SoapObject(NAMESPACE, METHOD_NAME);
            try {
                Utilizator utilizator=new Utilizator(params[0].getNume(), params[0].getPrenume(), params[0].getData_nasterii(), params[0].getSex(),
                                params[0].getEmail(), params[0].getTelefon(), params[0].getParola(), params[0].getNume_utilizator());
                utilizator.setId(4);
                PropertyInfo pi = new PropertyInfo();
                pi.setName("utilizator");
                pi.setValue(utilizator);
                pi.setType(utilizator.getClass());
                request.addProperty(pi);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = false;

                MarshalDate md=new MarshalDate();
                md.register(envelope);

                envelope.setOutputSoapObject(request);

                envelope.addMapping(NAMESPACE, "Utilizator", new Utilizator().getClass());
                AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(URL);


                androidHttpTransport.call(SOAP_ACTION, envelope);
                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

                Integer status = Integer.valueOf(response.toString());
                return status;
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return 0;

        }

        @Override
        protected void onPostExecute(Integer integer) {
          //  if(integer==null){
          //      Toast.makeText(NewUserActivity.this, getResources().getString(R.string.new_user_este_null), Toast.LENGTH_SHORT).show();
          //  }
          //  if(integer.intValue()!=-2){
                AlertDialog.Builder dialog1 = new AlertDialog.Builder(NewUserActivity.this);

                //dialog1.setTitle(getResources().getString(R.string.new_user_succes));
                dialog1.setTitle("Atentie!");
               // dialog1.setMessage(getResources().getString(R.string.new_user_mesaj_succes));
                dialog1.setMessage("Nume utilizator deja existent.");
                //dialog1.setNeutralButton(getResources().getString(R.string.new_user_intra_in_aplicatie), new DialogInterface.OnClickListener() {
                dialog1.setNeutralButton("Modifica nume utilizator", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(NewUserActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                });
                AlertDialog dialog2 = dialog1.create();
                dialog2.show();
                HomeActivity.utilizator.setNume_utilizator(etNumeUtilizator.getText().toString());
                HomeActivity.utilizator.setId(integer);
           // }else if(integer.intValue()==-2){
           //     Toast.makeText(NewUserActivity.this, getResources().getString(R.string.new_user_eroare_la_creare_utilizator), Toast.LENGTH_SHORT).show();
           // }else{
          //      Toast.makeText(NewUserActivity.this, getResources().getString(R.string.new_user_eroare_la_creare_utilizator), Toast.LENGTH_SHORT).show();
           // }
           // super.onPostExecute(integer);
        }
    }



}
