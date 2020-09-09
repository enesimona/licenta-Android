package com.example.enesimona30.licenta1.barcode;

import android.content.Context;

import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;

/**
 * Created by enesimona30 on 11/04/17.
 */

class BarcodeTrackerFactory implements MultiProcessor.Factory<Barcode> {
    private Context mContext;

    BarcodeTrackerFactory(Context context) {
        mContext = context;
    }

    @Override
    public Tracker<Barcode> create(Barcode barcode) {
        return new BarcodeTracker(mContext);
    }
}
