package com.example.enesimona30.licenta1;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enesimona30.licenta1.utils.Restaurant;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

@SuppressWarnings("deprecation")
public class InfoRestaurantActivity extends AppCompatActivity{
    private TextView tvNumeRestaurant, tvAdresaRestaurant, tvNumarTelefonRestaurant, tvUriWebSiteRestaurat;
    private RatingBar ratingBar;
    private android.app.Fragment fragment;
    private Button btnInfoRestaurantRezerva;

    private Gallery gallery;
    public static Restaurant restautantSelectat=new Restaurant();
    Integer[] imageIDs = {
            R.drawable.primus1,
            R.drawable.primus2,
            R.drawable.primus3

    };

    boolean mShowMap;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_restaurant);

        tvNumeRestaurant = (TextView) findViewById(R.id.tv_info_restaurant_nume);
        tvAdresaRestaurant = (TextView) findViewById(R.id.tv_info_restaurant_adresa);
        tvNumarTelefonRestaurant = (TextView) findViewById(R.id.tv_info_restaurant_nr_tel);
        tvUriWebSiteRestaurat = (TextView) findViewById(R.id.tv_info_restaurant_web_uri);

        tvNumeRestaurant.setText(restautantSelectat.getNumeRestaurant().toString());
        tvAdresaRestaurant.setText("Adresa: " + restautantSelectat.getAdresaRestaurant().toString());
        tvNumarTelefonRestaurant.setText("Numar telefon: " + restautantSelectat.getNumarTelefon().toString());
        tvUriWebSiteRestaurat.setText("Web site: " + restautantSelectat.getWebSiteUri().toString());

        ratingBar = (RatingBar) findViewById(R.id.ratingBar_info_restaurant);
        ratingBar.setRating(restautantSelectat.getRating());

        gallery = (Gallery) findViewById(R.id.gallery_info_restaurant);
        gallery.setSpacing(2);
        final GalleryImageAdapter galleryImageAdapter= new GalleryImageAdapter(this);
        gallery.setAdapter(galleryImageAdapter);


        fragment=(android.app.Fragment) getFragmentManager().findFragmentById(R.id.info_restaurant_map);


       // map = ((MapFragment) getFragmentManager().findFragmentById(R.id.fragment_map))
       //         .getMap();

        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FirstFragment fragment = new FirstFragment();
        fragmentTransaction.add(R.id.info_restaurant_map, fragment);
        fragmentTransaction.commit();

/*
        if (map != null) {
            Marker hamburg = map.addMarker(new MarkerOptions().position(new LatLng(restautantSelectat.getLatitudine(), restautantSelectat.getLongitudine()))
                    .title(restautantSelectat.getNumeRestaurant()));

        }
        */
       // ReservationActivity.restaurant.setNumeRestaurant(restautantSelectat.getNumeRestaurant().toString());
        btnInfoRestaurantRezerva=(Button) findViewById(R.id.btn_info_restaurant_rezerva);
        btnInfoRestaurantRezerva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), ReservationActivity.class);
                startActivity(intent);
            }
        });

        FirstFragment.rest.setLatitudine(restautantSelectat.getLatitudine());
        FirstFragment.rest.setLongitudine(restautantSelectat.getLongitudine());
        FirstFragment.rest.setNumeRestaurant(restautantSelectat.getNumeRestaurant());
        FirstFragment.rest.setAdresaRestaurant(restautantSelectat.getAdresaRestaurant());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    /*
    private boolean checkPlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE).show();
            }

            return false;
        }

        return true;
    }
    */


/*
    public class ImageAdapter extends BaseAdapter {
        private Context context;
        private int itemBackground;
        public ImageAdapter(Context c)
        {
            context = c;
            // sets a grey background; wraps around the images
            TypedArray a =obtainStyledAttributes(R.styleable.MyGallery);
            itemBackground = a.getResourceId(R.styleable.MyGallery_android_galleryItemBackground, 0);
            a.recycle();
        }
        // returns the number of images
        public int getCount() {
            return imageIDs.length;
        }
        // returns the ID of an item
        public Object getItem(int position) {
            return position;
        }
        // returns the ID of an item
        public long getItemId(int position) {
            return position;
        }
        // returns an ImageView view
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(imageIDs[position]);
            imageView.setLayoutParams(new Gallery.LayoutParams(100, 100));
            imageView.setBackgroundResource(itemBackground);
            return imageView;
        }
    }
*/

    public class GalleryImageAdapter extends BaseAdapter
    {
        private Context mContext;

        public GalleryImageAdapter(Context context)
        {
            mContext = context;
        }

        public int getCount() {
            return mImageIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }


        // Override this method according to your need
        public View getView(int index, View view, ViewGroup viewGroup)
        {
            // TODO Auto-generated method stub
            ImageView i = new ImageView(mContext);

            i.setImageResource(mImageIds[index]);
            i.setLayoutParams(new Gallery.LayoutParams(350, 350));

            i.setScaleType(ImageView.ScaleType.FIT_CENTER);

            return i;
        }

        public Integer[] mImageIds = {
                R.drawable.primus1,
                R.drawable.primus2,
                R.drawable.primus3,

        };

    }

}
