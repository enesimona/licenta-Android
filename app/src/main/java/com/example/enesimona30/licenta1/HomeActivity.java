package com.example.enesimona30.licenta1;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enesimona30.licenta1.barcode.BarcodeCaptureActivity;
import com.example.enesimona30.licenta1.utils.Utilizator;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int BARCODE_READER_REQUEST_CODE = 1;


    private Button btnScaneaza;
    public static TextView tvHomeRezultat, tvHomePuncteCumulate, tvHomeBunVenit;
    public static Utilizator utilizator=new Utilizator();

    private Toolbar toolbar;
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private int mSelectedId;
    public static float puncte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        ReservationActivity.utilizatorCurent.setNume_utilizator(utilizator.getNume_utilizator().toString());
        ReservationActivity.utilizatorCurent.setId(utilizator.getId());
        tvHomeRezultat=(TextView) findViewById(R.id.tv_home_result);

        btnScaneaza=(Button) findViewById(R.id.btn_home_scaneaza_nota);
        btnScaneaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BarcodeCaptureActivity.class);
                startActivity(intent);
            }
        });

        tvHomePuncteCumulate=(TextView) findViewById(R.id.tv_home_puncte_cumulate);
        new CallWSgetPuncteCumulate().execute(utilizator.getId());



        tvHomeBunVenit=(TextView) findViewById(R.id.tv_home_bun_venit);
        tvHomeBunVenit.setText("Bun venit, "+utilizator.getNume_utilizator()+"!");

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        mDrawer= (NavigationView) findViewById(R.id.main_drawer);
        mDrawer.setNavigationItemSelectedListener(this);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);


        drawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        //default it set first item as selected
        mSelectedId=savedInstanceState == null ? R.id.item_restaurante: savedInstanceState.getInt("SELECTED_ID");
        itemSelection(mSelectedId);

       // MenuItem item=(MenuItem) findViewById(R.id.item_home);
      //  item.setVisible(false);
        mDrawer.getMenu().findItem(R.id.item_home).setVisible(false);
        mDrawer.getMenu().findItem(R.id.item_istoric_tranzactii).setVisible(false);

    }

    private void itemSelection(int mSelectedId) {
        switch(mSelectedId){

            case R.id.item_scaneaza_nota:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.item_restaurante:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.item_rezerva:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.item_setari_cont:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.item_contact:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.item_faq:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        if(menuItem.getItemId()==R.id.item_home) {
            menuItem.setVisible(false);
        }

        if(menuItem.getItemId()==R.id.item_istoric_tranzactii) {
            menuItem.setVisible(false);
        }

        menuItem.setChecked(true);
        mSelectedId=menuItem.getItemId();
        itemSelection(mSelectedId);

        Intent intent=null;
        if(menuItem.getItemId()==R.id.item_scaneaza_nota){
            intent=new Intent(getApplicationContext(), BarcodeCaptureActivity.class);
        }
        if(menuItem.getItemId()==R.id.item_restaurante){
            intent=new Intent(getApplicationContext(), RestaurantsActivity.class);
        }
        if(menuItem.getItemId()==R.id.item_setari_cont){
            intent=new Intent(getApplicationContext(), AccountSettingsActivity.class);
        }
        if(menuItem.getItemId()==R.id.item_rezerva){
            intent=new Intent(getApplicationContext(), ReservationActivity.class);
        }
        if(menuItem.getItemId()==R.id.item_faq){
            intent=new Intent(getApplicationContext(), FAQActivity.class);
        }
        if(menuItem.getItemId()==R.id.item_contact){
            intent=new Intent(getApplicationContext(), ContactActivity.class);
        }
        if(intent!=null){
            startActivity(intent);
        }
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //save selected item so it will remains same even after orientation change
        outState.putInt("SELECTED_ID",mSelectedId);
    }



    class CallWSgetPuncteCumulate extends AsyncTask<Integer, View, Float> {
       //  private static final String URL="http://192.168.0.28:8080/WS/WS?WSDL"; //xml care defineste serviciul web al nostru
      // private static final String URL="http://213.233.85.83:8080/WS/WS?WSDL";


         private static final String URL="http://172.20.10.2:8080/WS/WS?WSDL";

        //private static final String URL="http://172.20.10.2:8080/WS/WS?WSDL";

        // private static final String URL="http://192.168.1.7.:8080/WS/WS?WSDL";
        // private static final String URL="http://192.168.0.28./WS/WS?WSDL";
        // private static final String URL="http://78.97.154.53:8080/WS/WS?WSDL";
        //private static final String URL="http://78.97.154.53/WS/WS?WSDL";
        private static final String SOAP_ACTION="http://ws/getPuncteCumulate"; //actiune definita prin namespace ul si numele met
        private static final String NAMESPACE="http://ws/";
        private static final String METHOD_NAME="getPuncteCumulate";

        @Override
        protected Float doInBackground(Integer... params) {
            //fol param 0 pt a face un REQUEST
            //requestul e definit in bibiloteca de catre clasa soap object
            SoapObject request=new SoapObject(NAMESPACE, METHOD_NAME);

            //primeste ca param tipul de data primitiva pe care il trimite!!!
            SoapSerializationEnvelope envelop=new SoapSerializationEnvelope(SoapEnvelope.VER11);
            //in request putem sa adaugam cu add property cati param avem nevoie, in cazul nostru 1, NUMELE param

            request.addProperty("id", params[0]);


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
                Float status=Float.valueOf(response.toString());
                return status; //se returneaza aici in cazul in care totul a decurs bine
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Float b) {
            puncte=b;

                if(b!=0.00) {
                    tvHomePuncteCumulate.setText("Puncte Cumulate: "+b);
                    ResultActivity.puncteCumulate=b;
            }else{

            }
            super.onPostExecute(b);
        }
    }


}
