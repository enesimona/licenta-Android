package com.example.enesimona30.licenta1.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enesimona30.licenta1.R;
import com.example.enesimona30.licenta1.RestaurantsActivity;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.Places;

import java.util.List;

/**
 * Created by enesimona30 on 10/03/17.
 */

public class RestaurantAdapter extends ArrayAdapter<Restaurant> implements Runnable{

    private List<Restaurant> list;
    private int resource;
    private Context context;
    private LayoutInflater inflater;


    public RestaurantAdapter(Context context, int resource, List<Restaurant> objects, LayoutInflater inflater) {
        super(context, resource, objects);

        this.list = objects;
        this.resource = resource;
        this.context = context;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row=inflater.inflate(resource,parent,false);

        TextView numeRest=(TextView) row.findViewById(R.id.tv_restaurant_row_nume);
        TextView adresaRest=(TextView) row.findViewById(R.id.tv_restaurant_row_adresa);
        final ImageView pozaRest=(ImageView) row.findViewById(R.id.iv_restaurant_row_image);

        final Restaurant restaurant=list.get(position);

        if(restaurant!=null){

            if(restaurant.getNumeRestaurant()!=null){
                numeRest.setText(restaurant.getNumeRestaurant());
            }else{
                numeRest.setText(row.getResources().getString(R.string.row_item_fara_nume));
            }

            if(restaurant.getAdresaRestaurant()!=null){
                adresaRest.setText(restaurant.getAdresaRestaurant());
            }else{
                adresaRest.setText(row.getResources().getString(R.string.row_item_fara_adresa));
            }

            PhotoTask photoTask= (PhotoTask) new PhotoTask(){

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    if(bitmap==null){
                        pozaRest.setImageResource(R.drawable.loading);
                    }else{
                        Bitmap resizedBitmap=getResizedBitmap(bitmap, 280, 100);
                        pozaRest.setImageBitmap(resizedBitmap);
                    }
                }
            }.execute(restaurant.getCod());

        }
        return row;
    }

    @Override
    public void run() {

    }

    class PhotoTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            if (params.length != 1) {
                return null;
            }
            final String placeId = params[0];

            PlacePhotoMetadataResult result = Places.GeoDataApi
                    .getPlacePhotos(RestaurantsActivity.mGoogleApiClient, placeId).await();

            if (result.getStatus().isSuccess()) {
                PlacePhotoMetadataBuffer photoMetadataBuffer = result.getPhotoMetadata();
                if (photoMetadataBuffer.getCount() > 0 && !isCancelled()) {
                    PlacePhotoMetadata photo = photoMetadataBuffer.get(0);
                    Bitmap image = photo.getPhoto(RestaurantsActivity.mGoogleApiClient).await()
                            .getBitmap();
                    return image;
                }
                photoMetadataBuffer.release();
            }
            return null;
        }
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

}
