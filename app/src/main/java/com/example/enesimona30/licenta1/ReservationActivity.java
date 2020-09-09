package com.example.enesimona30.licenta1;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.enesimona30.licenta1.utils.Alocare;
import com.example.enesimona30.licenta1.utils.CustomTimePickerDialog;
import com.example.enesimona30.licenta1.utils.MarshalDate;
import com.example.enesimona30.licenta1.utils.Masa;
import com.example.enesimona30.licenta1.utils.RestaurantBD;
import com.example.enesimona30.licenta1.utils.Rezervare;
import com.example.enesimona30.licenta1.utils.Utilizator;

import org.joda.time.DateTime;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Date;

public class ReservationActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    private DatePicker datePicker;
    private java.util.Calendar calendar;
    private int year, month, day;
    private Button btnReservationData, btnReservationOra, btnReservationNrLocuri, btnReservasionRezerva;
   // public static RestaurantBD restaurantBD = new RestaurantBD(1, "Vacamuuu");
    public static Utilizator utilizatorCurent = new Utilizator();

    private Integer nrLocuri=4;
    private Masa masaSelectata;


    private DateTime dataRezervare;
    private Integer an, luna, zi, ora, minuteSelectate;
    private Date dataSelectata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        btnReservationData = (Button) findViewById(R.id.btn_reservation_data);
        btnReservationOra = (Button) findViewById(R.id.btn_reservation_ora);
        btnReservationNrLocuri = (Button) findViewById(R.id.btn_reservation_nr_persoane);

        calendar = java.util.Calendar.getInstance();
        year = calendar.get(java.util.Calendar.YEAR);

        month = calendar.get(java.util.Calendar.MONTH);
        day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);


        btnReservationOra.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                java.util.Calendar mcurrentTime = java.util.Calendar.getInstance();
                int hour = mcurrentTime.get(java.util.Calendar.HOUR_OF_DAY);
                final int minute = mcurrentTime.get(java.util.Calendar.MINUTE);
                CustomTimePickerDialog mTimePicker;
                mTimePicker = new CustomTimePickerDialog(ReservationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        btnReservationOra.setText(selectedHour + ":" + selectedMinute);
                        ora = selectedHour;
                        minuteSelectate = selectedMinute;

                        dataRezervare = new DateTime(an, luna, zi, ora, minuteSelectate);
                        dataSelectata = dataRezervare.toDate();
                        //  Toast.makeText(getApplicationContext(), dataSelectata.toString(), Toast.LENGTH_LONG).show();
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();


            }
        });

        btnReservationNrLocuri.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                show();
            }
        });


        masaSelectata = new Masa();
        masaSelectata.setRestaurant(1);
        masaSelectata.setCapacitate(nrLocuri);


        btnReservasionRezerva = (Button) findViewById(R.id.btn_reservation_rezerva);
        btnReservasionRezerva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CallWSReservation().execute(masaSelectata, initRezervare());
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    private Rezervare initRezervare(){



        Alocare alocare=new Alocare(null, dataSelectata, false);

        Rezervare rezervare=new Rezervare(alocare, utilizatorCurent);
        return rezervare;
    }


    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    showDate(arg1, arg2 + 1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        btnReservationData.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
        an = year;
        luna = month;
        zi = day;

    }

    public void show() {

        final Dialog d = new Dialog(ReservationActivity.this);
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.dialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(10); // max value 100
        np.setMinValue(1);   // min value 0
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnReservationNrLocuri.setText(String.valueOf(np.getValue())); //set the value to textview
                nrLocuri = np.getValue();
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss(); // dismiss the dialog
            }
        });
        d.show();

    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        Log.i("value is", "" + i1);
    }


    class CallWSReservation extends AsyncTask<Object, View, Integer> {
       // private static final String URL = "http://192.168.0.28.:8080/WS/WS?WSDL"; //xml care defineste serviciul web al nostru
        private static final String URL="http://172.20.10.2:8080/WS/WS?WSDL";
        // private static final String URL="http://192.168.0.16.:8080/WS/WS?WSDL";
        private static final String SOAP_ACTION = "http://ws/reservation"; //actiune definita prin namespace ul si numele met
        private static final String NAMESPACE = "http://ws/";
        private static final String METHOD_NAME = "reservation";

        @Override
        protected Integer doInBackground(Object... params) {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            try {

                Rezervare rezervare;
                rezervare= (Rezervare) params[1];

                Masa masaPreluata=(Masa) params[0];
                Masa masa=new Masa(masaPreluata.getRestaurant(), masaPreluata.getCapacitate());

                Log.i("MASA", masa.toString());

                masa.setProperty(1, masa.getRestaurant());
                masa.setProperty(2, masa.getCapacitate());

                masa.setIdMasa(4);

                PropertyInfo pi3=new PropertyInfo();
                pi3.setName("masa");
                pi3.setValue(masa);
                pi3.setType(masa.getClass());
                request.addProperty(pi3);
                //request.addProperty("rezervare", params[0]);
               // request.addProperty("masa", params[1]);


                Alocare alocare;
                alocare=rezervare.getAlocare();
                alocare.setProperty(1, masa);
                Log.i("ALOCARE", alocare.toString());

                Utilizator utilizator;
                utilizator=rezervare.getUtilizator();


                rezervare.setProperty(1, alocare);
                rezervare.setProperty(2, utilizator);
                Log.i("REZERVARE", rezervare.toString());

                rezervare.setIdRezervare(4);
                PropertyInfo pi = new PropertyInfo();
                pi.setName("rezervare");
                pi.setValue(rezervare);
                pi.setType(rezervare.getClass());
                request.addProperty(pi);




                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = false;

                MarshalDate md = new MarshalDate();
                md.register(envelope);

                envelope.setOutputSoapObject(request);

                envelope.addMapping(NAMESPACE, "Rezervare", new Rezervare().getClass());

                envelope.addMapping(NAMESPACE, "Alocare", new Alocare().getClass());
                envelope.addMapping(NAMESPACE, "Masa", new Masa().getClass());
                envelope.addMapping(NAMESPACE, "RestaurantBD", new RestaurantBD().getClass());
                envelope.addMapping(NAMESPACE, "Utilizator", new Utilizator().getClass());

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

            /*
            if(integer==0){
                Toast.makeText(ReservationActivity.this, "null", Toast.LENGTH_LONG).show();
            }else if(integer==1){
                Toast.makeText(ReservationActivity.this, "nu sunt mese disponibile", Toast.LENGTH_LONG).show();
            }else if(integer==2){
                Toast.makeText(ReservationActivity.this, "succes", Toast.LENGTH_LONG).show();
            }else if(integer==3){
                Toast.makeText(ReservationActivity.this, "exista mese disponibile dar nu s a inserat in BD", Toast.LENGTH_LONG).show();
            }else if(integer==4){
                Toast.makeText(ReservationActivity.this, "nu exista mese care sa respecte conditia", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(ReservationActivity.this, "nu e bine", Toast.LENGTH_LONG).show();
            }

            AlertDialog.Builder dialog3 = new AlertDialog.Builder(ReservationActivity.this);

            //dialog1.setTitle(getResources().getString(R.string.new_user_succes));
            dialog3.setTitle("Succes!");
            // dialog1.setMessage(getResources().getString(R.string.new_user_mesaj_succes));
            dialog3.setMessage("A fost făcută rezervare la Vacamuu, data 30-06-2017, ora 12:00, 4 persoane.");
            //dialog1.setNeutralButton(getResources().getString(R.string.new_user_intra_in_aplicatie), new DialogInterface.OnClickListener() {
            dialog3.setNeutralButton("Confirma", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(ReservationActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            });

            dialog3.setNegativeButton("Renunta", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            AlertDialog dialog4 = dialog3.create();
            dialog4.show();
*/
            if(integer==0){
                Toast.makeText(ReservationActivity.this, "null", Toast.LENGTH_LONG).show();
           // } else if(integer==1 || integer==5) {
            } else if(integer==1 ) {
                AlertDialog.Builder dialog3 = new AlertDialog.Builder(ReservationActivity.this);

                //dialog1.setTitle(getResources().getString(R.string.new_user_succes));
                dialog3.setTitle("Atentie!");
                // dialog1.setMessage(getResources().getString(R.string.new_user_mesaj_succes));
                dialog3.setMessage("Nu exista mese disponibile.");
                //dialog1.setNeutralButton(getResources().getString(R.string.new_user_intra_in_aplicatie), new DialogInterface.OnClickListener() {
                dialog3.setNeutralButton("Alternativa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ReservationActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                });

                dialog3.setNegativeButton("Renunta", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                AlertDialog dialog4 = dialog3.create();
                dialog4.show();
            }else if(integer==2){
                //Toast.makeText(ReservationActivity.this, "succes", Toast.LENGTH_LONG).show();
                AlertDialog.Builder dialog5 = new AlertDialog.Builder(ReservationActivity.this);

                //dialog1.setTitle(getResources().getString(R.string.new_user_succes));
                dialog5.setTitle("Succes!");
                // dialog1.setMessage(getResources().getString(R.string.new_user_mesaj_succes));
                dialog5.setMessage("Exista masa disponibila.");
                //dialog1.setNeutralButton(getResources().getString(R.string.new_user_intra_in_aplicatie), new DialogInterface.OnClickListener() {
                dialog5.setNeutralButton("Confirma", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                });

                dialog5.setNegativeButton("Renunta", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                AlertDialog dialog6 = dialog5.create();
                dialog6.show();

            }else if(integer==3){
                Toast.makeText(ReservationActivity.this, "exista mese disponibile dar nu s a inserat in BD", Toast.LENGTH_LONG).show();
            }else if(integer==4){
                Toast.makeText(ReservationActivity.this, "nu exista mese care sa respecte conditia", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(ReservationActivity.this, "nu e bine", Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(integer);
        }
    }
}
