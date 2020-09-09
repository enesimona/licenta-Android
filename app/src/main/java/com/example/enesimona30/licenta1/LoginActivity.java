package com.example.enesimona30.licenta1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enesimona30.licenta1.utils.Utilizator;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private Button btnTrimite;
    private TextView tvRecuperareParola;
    private EditText username, parola;

    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponents();
    }

    protected void initComponents(){
        /*
        btn=(Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this, RouletteActivity.class);
                startActivity(i);
            }
        });
*/
        btnTrimite=(Button) findViewById(R.id.btn_login_trimite);
        username=(EditText) findViewById(R.id.et_login_username);
        parola=(EditText) findViewById(R.id.et_login_parola);

        btnTrimite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CallWS().execute(username.getText().toString(), parola.getText().toString());
            }
        });

        tvRecuperareParola=(TextView) findViewById(R.id.tv_login_recuperare_parola);
        tvRecuperareParola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), PasswordRecoveryActivity.class);
                startActivity(intent);
            }
        });
    }

    class CallWS extends AsyncTask<String, View, Integer> {
       // private static final String URL="http://192.168.0.28.:8080/WS/WS?WSDL"; //xml care defineste serviciul web al nostru
      // private static final String URL="http://213.233.85.83:8080/WS/WS?WSDL";

        private static final String URL="http://172.20.10.2:8080/WS/WS?WSDL";


        //private static final String URL="http://172.20.10.2:8080/WS/WS?WSDL";

        // private static final String URL="http://192.168.1.7.:8080/WS/WS?WSDL";
       // private static final String URL="http://192.168.0.28./WS/WS?WSDL";
       // private static final String URL="http://78.97.154.53:8080/WS/WS?WSDL";
        //private static final String URL="http://78.97.154.53/WS/WS?WSDL";
        private static final String SOAP_ACTION="http://ws/authentification"; //actiune definita prin namespace ul si numele met
        private static final String NAMESPACE="http://ws/";
        private static final String METHOD_NAME="authentification";

        @Override
        protected Integer doInBackground(String... params) {
            //fol param 0 pt a face un REQUEST
            //requestul e definit in bibiloteca de catre clasa soap object
            SoapObject request=new SoapObject(NAMESPACE, METHOD_NAME);

            //primeste ca param tipul de data primitiva pe care il trimite!!!
            SoapSerializationEnvelope envelop=new SoapSerializationEnvelope(SoapEnvelope.VER11);
            //in request putem sa adaugam cu add property cati param avem nevoie, in cazul nostru 1, NUMELE param

            request.addProperty("nume_utilizator", params[0]);
            request.addProperty("parola", params[1]);

            //trimitem requestul
            envelop.setOutputSoapObject(request);
            envelop.dotNet=false;
            //creez modul de transport definit de clasa httpTransp..
            HttpTransportSE transport=new HttpTransportSE(URL);

            //chemam metoda din instanta de transport
            try {
                transport.call(SOAP_ACTION, envelop);
                //creem raspunsul si il obtinem
                SoapPrimitive response=(SoapPrimitive) envelop.getResponse();
                //Boolean status = Boolean.valueOf(response.toString());
                Integer status=Integer.valueOf(response.toString());
                return status; //se returneaza aici in cazul in care totul a decurs bine
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Integer b) {
            if(b==null)
            {
                Toast.makeText(getApplicationContext(), "Nu se trimite nimic!", Toast.LENGTH_SHORT).show();
            }
            else if(b!=-1) {

                Toast.makeText(getApplicationContext(), "Login Reusit!", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(i);
                HomeActivity.utilizator.setNume_utilizator(username.getText().toString());
                Integer integer=2;
                HomeActivity.utilizator.setId(integer);
                ResultActivity.utilizator.setId(b);
                ResultActivity.utilizator=new Utilizator();
                ResultActivity.utilizator.setId(integer);

            }else{
                Toast.makeText(getApplicationContext(), "Nume utilizator sau parola incorecta!", Toast.LENGTH_SHORT).show();
            }

            super.onPostExecute(b);
        }
    }



}
