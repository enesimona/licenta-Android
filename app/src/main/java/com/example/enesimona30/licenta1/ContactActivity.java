package com.example.enesimona30.licenta1;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.enesimona30.licenta1.barcode.BarcodeCaptureActivity;

public class ContactActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private TextView tvContactTelefon, tvContactEmail;

    private Toolbar toolbar;
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private int mSelectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        tvContactTelefon=(TextView) findViewById(R.id.tv_contact_telefon);
        tvContactEmail=(TextView) findViewById(R.id.tv_contact_email);

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        mDrawer= (NavigationView) findViewById(R.id.main_drawer_contact);
        mDrawer.setNavigationItemSelectedListener(this);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout_contact);


        drawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        //default it set first item as selected
        mSelectedId=savedInstanceState == null ? R.id.item_restaurante: savedInstanceState.getInt("SELECTED_ID");
        itemSelection(mSelectedId);

        // MenuItem item=(MenuItem) findViewById(R.id.item_home);
        //  item.setVisible(false);
        mDrawer.getMenu().findItem(R.id.item_home).setVisible(false);
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
}
