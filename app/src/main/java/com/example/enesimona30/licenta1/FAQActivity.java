package com.example.enesimona30.licenta1;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.enesimona30.licenta1.barcode.BarcodeCaptureActivity;
import com.example.enesimona30.licenta1.utils.IntrebariAdapter;
import com.example.enesimona30.licenta1.utils.Item;

import java.util.ArrayList;

public class FAQActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ListView lvFAQ;
    public static ArrayList<String> strings=new ArrayList<>();
    private TextView tvFaqText;

    private Toolbar toolbar;
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private int mSelectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        lvFAQ=(ListView) findViewById(R.id.lv_faq);
        tvFaqText=(TextView) findViewById(R.id.text1);

        //ArrayAdapter<String> adaper=new ArrayAdapter<String>(getApplication(), R.layout.faq_text_view, strings);

        IntrebariAdapter adaper=new IntrebariAdapter(getApplicationContext(), generateItemsList());

        String question1="I: Cum se pot castiga puncte reducere?";
        String answer1="R: Prin scanarea codului QR de pe nota de plata. Acest lucru e posibil prin apasarea butonului Scaneaza Nota din pagina principala a aplicatiei.";

        String question2="I: Ce pot face cu punctele acumulate din aplicatie?";
        String answer2="R: Punctele acumulate prin scanarea notei se pot folosi pentru achitarea partiala sau integrala a notei de plata actuala sau una viitoare.";

        String question3="I: Ce valoare au punctele reducere?";
        String answer3="R: Un punct reducere este echivalentul unui leu.";

        //String question4="Ce fac daca nu mi s-au incarcat ";

        strings.add(question1);
        strings.add(answer1);
        strings.add(question2);
        strings.add(answer2);
        strings.add(question3);
        strings.add(answer3);

        lvFAQ.setAdapter(adaper);

        /*
        for(int i=0;i<strings.size();i++){
            if(i%2==0){
                //lvFAQ.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                tvFaqText.setTextColor(getResources().getColor(R.color.colorOrange));
            }else if(i%2==1){
                //lvFAQ.setBackgroundColor(getResources().getColor(R.color.colorOrangeDarker));
                tvFaqText.setTextColor(getResources().getColor(R.color.colorOrangeDarker));
            }
        }
        */

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        mDrawer= (NavigationView) findViewById(R.id.main_drawer_faq);
        mDrawer.setNavigationItemSelectedListener(this);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout_faq);


        drawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        //default it set first item as selected
        mSelectedId=savedInstanceState == null ? R.id.item_restaurante: savedInstanceState.getInt("SELECTED_ID");
        itemSelection(mSelectedId);

        // MenuItem item=(MenuItem) findViewById(R.id.item_home);
        //  item.setVisible(false);
        mDrawer.getMenu().findItem(R.id.item_faq).setVisible(false);

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



    private ArrayList<Item> generateItemsList() {
        String itemNames[] = getResources().getStringArray(R.array.items_faq);

        ArrayList<Item> list = new ArrayList<>();

        for (int i = 0; i < itemNames.length; i++) {
            list.add(new Item(itemNames[i]));
        }

        return list;
    }
}
