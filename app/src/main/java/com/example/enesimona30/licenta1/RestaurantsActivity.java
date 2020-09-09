package com.example.enesimona30.licenta1;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.enesimona30.licenta1.barcode.BarcodeCaptureActivity;
import com.example.enesimona30.licenta1.utils.Restaurant;
import com.example.enesimona30.licenta1.utils.RestaurantAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;

import java.util.ArrayList;


public class RestaurantsActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks /*, NavigationView.OnNavigationItemSelectedListener*/ {

    private ListView lvRestaurante;
    public static ArrayList<Restaurant> restaurante=new ArrayList<>();
    private int selectedPosition;
    private static ArrayList<String> placeIds=new ArrayList<>();

    public static GoogleApiClient mGoogleApiClient;
    private static final int GOOGLE_API_CLIENT_ID = 0;
    private int i;


    private Toolbar toolbar;
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private int mSelectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        initComponents();


        /*
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        mDrawer= (NavigationView) findViewById(R.id.main_drawer_restaurants);
        mDrawer.setNavigationItemSelectedListener(this);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout_restaurants);


        drawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        //default it set first item as selected
        mSelectedId=savedInstanceState == null ? R.id.item_home : savedInstanceState.getInt("SELECTED_ID");
        itemSelection(mSelectedId);

        // MenuItem item=(MenuItem) findViewById(R.id.item_home);
        //  item.setVisible(false);
        mDrawer.getMenu().findItem(R.id.item_restaurante).setVisible(false);
        */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    /*
    private void itemSelection(int mSelectedId) {
        switch(mSelectedId){

            case R.id.item_scaneaza_nota:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.item_home:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.item_rezerva:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.item_setari_cont:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.item_ajutor:
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
        if(menuItem.getItemId()==R.id.item_restaurante) {
            menuItem.setVisible(false);
        }

        menuItem.setChecked(true);
        mSelectedId=menuItem.getItemId();
        itemSelection(mSelectedId);

        Intent intent=null;
        if(menuItem.getItemId()==R.id.item_scaneaza_nota){
            intent=new Intent(getApplicationContext(), BarcodeCaptureActivity.class);
        }
        if(menuItem.getItemId()==R.id.item_home){
            intent=new Intent(getApplicationContext(), HomeActivity.class);
        }
        if(menuItem.getItemId()==R.id.item_setari_cont){
            intent=new Intent(getApplicationContext(), AccountSettingsActivity.class);
        }
        if(menuItem.getItemId()==R.id.item_rezerva){
            intent=new Intent(getApplicationContext(), ReservationActivity.class);
        }
        if(menuItem.getItemId()==R.id.item_ajutor){
            intent=new Intent(getApplicationContext(), ContactActivity.class);
        }
        if(intent!=null){
            startActivity(intent);
        }
        return true;
        //  return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //save selected item so it will remains same even after orientation change
        outState.putInt("SELECTED_ID",mSelectedId);
    }
    */

    private void initComponents() {

        lvRestaurante = (ListView) findViewById(R.id.lv_restaurante);

        placeIds.add("ChIJ6fpLJab4sUARQ5RK4c2qhbU");
        placeIds.add("ChIJxfAKCA4CskAR8ZGtgPGG80c");
        placeIds.add("ChIJVxr600ICskARI-3tQmBZ7Pc");
        placeIds.add("ChIJ5Ub2zv8BskARyM9jdNcSsj4");
        placeIds.add("ChIJ68NBw03_sUAR7aAWDZRlx3c");

        placeIds.add("ChIJ3c_z5k__sUARO8loIyBwmG4");
        placeIds.add("ChIJ82d2nVL_sUARpY3mnhJOcbQ");
        placeIds.add("ChIJwXfea0D_sUARVKdY1hnDhco");
        placeIds.add("ChIJeZBHy2D_sUARwMCzD2VyaaY");
        placeIds.add("ChIJXwHA8ED_sUARilV890J6uKE");

        final RestaurantAdapter restaurantAdapter = new RestaurantAdapter(getApplicationContext(),
                R.layout.lv_restaurant_row, restaurante, getLayoutInflater());
        lvRestaurante.setAdapter(restaurantAdapter);

        mGoogleApiClient = new GoogleApiClient.Builder(RestaurantsActivity.this)
                .addApi(Places.GEO_DATA_API)
                .enableAutoManage(this, GOOGLE_API_CLIENT_ID, this)
                .addConnectionCallbacks(this)
                .build();

        for (final String placeId : placeIds) {

            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi.getPlaceById(mGoogleApiClient, placeId);

            placeResult.setResultCallback(new ResultCallback<PlaceBuffer>() {
                @Override
                public void onResult(PlaceBuffer places) {
                    if (places.getStatus().isSuccess() && places.getCount() > 0) {
                        final Place myPlace = places.get(0);

                            Restaurant restaurantNou = new Restaurant(myPlace.getAddress().toString(),
                                    placeId.toString(), myPlace.getLatLng().latitude, myPlace.getLatLng().longitude,
                                    myPlace.getPhoneNumber().toString(), myPlace.getName().toString(),
                                          myPlace.getRating(), myPlace.getWebsiteUri().toString());

                            restaurante.add(restaurantNou);
                            RestaurantAdapter restaurantAdapter= (RestaurantAdapter) lvRestaurante.getAdapter();
                            restaurantAdapter.notifyDataSetChanged();
                    } else {
                        Log.e("Place not found", "Place not found");
                    }
                    places.release();
                }
            });
        }


        lvRestaurante.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(), InfoRestaurantActivity.class);

                InfoRestaurantActivity.restautantSelectat.setNumeRestaurant(restaurante.get(position).getNumeRestaurant());
                InfoRestaurantActivity.restautantSelectat.setNumarTelefon(restaurante.get(position).getNumarTelefon());
                InfoRestaurantActivity.restautantSelectat.setAdresaRestaurant(restaurante.get(position).getAdresaRestaurant());
                InfoRestaurantActivity.restautantSelectat.setRating(restaurante.get(position).getRating());
                InfoRestaurantActivity.restautantSelectat.setWebSiteUri(restaurante.get(position).getWebSiteUri());
                InfoRestaurantActivity.restautantSelectat.setLongitudine(restaurante.get(position).getLongitudine());
                InfoRestaurantActivity.restautantSelectat.setLatitudine(restaurante.get(position).getLatitudine());
                startActivity(intent);
            }
        });

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("connection failed", "Google Places API connection failed with error code: "
                + connectionResult.getErrorCode());

        Toast.makeText(this,
                "Google Places API connection failed with error code:" +
                        connectionResult.getErrorCode(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i("conexiune reusita", "Google Places API connected.");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e("conexiune suspendata", "Google Places API connection suspended.");
    }
/*
     public class PhotoTask extends AsyncTask<String, Void, Bitmap> {


        @Override
        protected Bitmap doInBackground(String... params) {
            if (params.length != 1) {
                return null;
            }
            final String placeId = params[0];

            PlacePhotoMetadataResult result = Places.GeoDataApi
                    .getPlacePhotos(mGoogleApiClient, placeId).await();

            if (result.getStatus().isSuccess()) {
                PlacePhotoMetadataBuffer photoMetadataBuffer = result.getPhotoMetadata();
                if (photoMetadataBuffer.getCount() > 0 && !isCancelled()) {
                    PlacePhotoMetadata photo = photoMetadataBuffer.get(0);
                    // Bitmap image = photo.getScaledPhoto(mGoogleApiClient, mWidth, mHeight).await()
                    //          .getBitmap();
                    Bitmap image = photo.getPhoto(mGoogleApiClient).await()
                            .getBitmap();

                    return image;
                }
                photoMetadataBuffer.release();
            }
            return null;
        }


     }

    private void getDataOverUrl(){
        PhotoTask photoTask=new PhotoTask(){

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if(bitmap==null){

                    Toast.makeText(getApplicationContext(), "nu se ia poza buna", Toast.LENGTH_SHORT).show();

                }else{
                    //setam bitmapul corespunzator restaurantului
                  //  restaurante.get(0).setPoza(bitmap);
                 //   RestaurantAdapter restaurantAdapter= (RestaurantAdapter) lvRestaurante.getAdapter();
                 //   restaurantAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "se ia poza buna", Toast.LENGTH_SHORT).show();
                }
            }
        };
         //   photoTask.execute(placeIds.get(i));
    }
*/
}

