package com.example.enesimona30.licenta1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enesimona30.licenta1.utils.Constants;
import com.example.enesimona30.licenta1.utils.MarshalDate;
import com.example.enesimona30.licenta1.utils.MarshalFloat;
import com.example.enesimona30.licenta1.utils.NotaPlata;
import com.example.enesimona30.licenta1.utils.RestaurantBD;
import com.example.enesimona30.licenta1.utils.Utilizator;
import com.google.android.gms.vision.barcode.Barcode;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class ResultActivity extends AppCompatActivity {

    public static Barcode barcode;
    private TextView tvResultValoareNota, tvResultPuncteObtinute, tvResultPuncteTotale;
    public static Utilizator utilizator=new Utilizator();
    private Button btnResultCumulare;
    private Button btnResultConsumare;
    private Boolean isRedusa;
    private NotaPlata notaPlata=new NotaPlata();
    public static Float procent;
    private ArrayList<String> strings=new ArrayList<>();

    public static Float puncteCumulate=Float.valueOf(40);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvResultValoareNota=(TextView) findViewById(R.id.tv_result_valoare_nota);

        Point[] p = barcode.cornerPoints;
        String barcodeText=barcode.displayValue;

        StringTokenizer st=new StringTokenizer(barcodeText);

        while(st.hasMoreElements()){
            String s=st.nextElement().toString();
            strings.add(s);
            Log.i("STRINGS", s);
        }



        tvResultValoareNota.setText("Valoare nota: 100.0 lei");
        tvResultPuncteTotale=(TextView) findViewById(R.id.tv_result_puncte_cumulate);

        btnResultCumulare=(Button) findViewById(R.id.btn_result_cumulare);
        btnResultConsumare=(Button) findViewById(R.id.btn_result_reducere);
        tvResultPuncteObtinute=(TextView) findViewById(R.id.tv_result_puncte_obtinute);


        try {
            Date dataEmitere=Constants.simpleDateFormat.parse(strings.get(3).toString());
            notaPlata=new NotaPlata();
            notaPlata.setDataEmitere(dataEmitere);

            Float valoare=Float.parseFloat(strings.get(2));
            //Float valoare= Float.valueOf(100);
            notaPlata.setValoare(valoare);

            notaPlata.setProcent(procent);

            notaPlata.setUtilizator(utilizator);

            RestaurantBD restaurant=new RestaurantBD(Integer.parseInt(strings.get(1)));
            notaPlata.setRestaurant(restaurant);

            tvResultPuncteObtinute.setText("Puncte obtinute: "+valoare*procent/100);
            Float suma=puncteCumulate+valoare*procent/100;
            tvResultPuncteTotale.setText("Puncte totale: "+suma);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        btnResultConsumare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    notaPlata.setRedusa(true);
                    new CallWSAddCheck().execute(notaPlata);
            }
        });

        btnResultCumulare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    notaPlata.setRedusa(false);
                    new CallWSAddCheck().execute(notaPlata);
            }
        });


    }

    class CallWSAddCheck extends AsyncTask<NotaPlata, View, Integer> {
       // private static final String URL = "http://192.168.0.28.:8080/WS/WS?WSDL";
       private static final String URL="http://172.20.10.2:8080/WS/WS?WSDL";

        private static final String SOAP_ACTION = "http://ws/adaugareNota";
        private static final String NAMESPACE = "http://ws/";
        private static final String METHOD_NAME = "adaugareNota";


        @Override
        protected Integer doInBackground(NotaPlata... params) {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            try {
                NotaPlata nota = new NotaPlata(params[0].getDataEmitere(), params[0].getValoare(), params[0].getProcent(), params[0].getRedusa(),
                        params[0].getUtilizator(), params[0].getRestaurant());

                Log.i("%%%%%%%%%%%%", nota.toString());
                nota.setIdNota(4);

                PropertyInfo pi = new PropertyInfo();
                pi.setName("nota");
                pi.setValue(nota);
                pi.setType(nota.getClass());
                request.addProperty(pi);

                Utilizator utilizator=nota.getUtilizator();
/*
                PropertyInfo pi2=new PropertyInfo();
                pi2.setName("utilizator");
                pi2.setValue(utilizator);
                pi2.setType(utilizator.getClass());
                request.addProperty(pi2);
*/
                RestaurantBD restaurant=nota.getRestaurant();
/*
                PropertyInfo pi3=new PropertyInfo();
                pi3.setName("restaurant");
                pi3.setValue(restaurant);
                pi3.setType(restaurant.getClass());
                request.addProperty(pi3);
*/
                nota.setProperty(5, utilizator);
                nota.setProperty(6, restaurant);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = false;

                MarshalDate md = new MarshalDate();
                md.register(envelope);

                MarshalFloat mf=new MarshalFloat();
                mf.register(envelope);

                envelope.setOutputSoapObject(request);

                envelope.addMapping(NAMESPACE, "NotaPlata", new NotaPlata().getClass());
                envelope.addMapping(NAMESPACE, "Utilizator", new Utilizator().getClass());
                envelope.addMapping(NAMESPACE, "RestaurantBD", new RestaurantBD().getClass());

                AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(URL);

                androidHttpTransport.call(SOAP_ACTION, envelope);

                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

                Integer status = Integer.valueOf(response.toString());
                return status;

            } catch (SoapFault soapFault) {
                soapFault.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return 0;

        }

        @Override
        protected void onPostExecute(Integer integer) {

            if (integer == null) {
                Toast.makeText(ResultActivity.this, "NULL!", Toast.LENGTH_SHORT).show();
            } else if (integer.intValue() == 1) {

                AlertDialog.Builder dialog1 = new AlertDialog.Builder(ResultActivity.this);

                dialog1.setTitle("Succes!");
                dialog1.setMessage("Arata chelnerului urmatorul cod: "+strings.get(0));

                dialog1.setNeutralButton("Inapoi la aplicatie", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }
                });

                AlertDialog dialog2 = dialog1.create();
                dialog2.show();

            } else if(integer.intValue() == 2){
               // Toast.makeText(ResultActivity.this, "Puncte Cumulate!", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder dialog1 = new AlertDialog.Builder(ResultActivity.this);

                dialog1.setTitle("Succes!");
                dialog1.setMessage("Punctele au fost cumulate.");

                dialog1.setNeutralButton("Inapoi la aplicatie", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }
                });

                AlertDialog dialog2 = dialog1.create();
                dialog2.show();

            } else if (integer.intValue() == -1) {
                Toast.makeText(ResultActivity.this, "Eroare!", Toast.LENGTH_SHORT).show();

                super.onPostExecute(integer);
            }
        }
    }

}
