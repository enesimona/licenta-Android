package com.example.enesimona30.licenta1.utils;



import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.enesimona30.licenta1.FAQActivity;
import com.example.enesimona30.licenta1.R;

import java.util.ArrayList;

/**
 * Created by enesimona30 on 26/06/17.
 */

public class IntrebariAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Item> items;

    public IntrebariAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size(); //returns total item in the list
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); //returns the item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null || convertView.getTag()==null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.faq_text_view, parent, false);
            viewHolder = new ViewHolder(convertView);
          //  viewHolder.itemName=(TextView) convertView.findViewById(R.id.text1);
            convertView.setTag(viewHolder);

        } else {
          //  viewHolder = (ViewHolder) convertView.getTag();
            viewHolder=(ViewHolder) convertView.getTag();
        }

        Item currentItem = (Item) getItem(position);
        viewHolder.getItemName().setText(currentItem.getItemName());
        //TextView tv=(TextView) convertView.findViewById(R.id.text1);
        //tv.setText(currentItem.getItemName());

        if (position % 2 == 1) {
            //convertView.setBackgroundColor(Color.BLUE);
            viewHolder.getItemName().setTypeface(null, Typeface.NORMAL);
            viewHolder.getItemName().setTextSize(18);
        } else {
            viewHolder.getItemName().setTypeface(null, Typeface.BOLD_ITALIC);
            viewHolder.getItemName().setTextSize(22);
            //convertView.setBackgroundColor(Color.CYAN);
        }


        return convertView;
    }

    //ViewHolder inner class
    private class ViewHolder {
        TextView itemName;

        public ViewHolder(View view) {
            itemName = (TextView) view.findViewById(R.id.tv_faq);
        }

        public TextView getItemName() {
            return itemName;
        }

        public void setItemName(TextView itemName) {
            this.itemName = itemName;
        }
    }
}