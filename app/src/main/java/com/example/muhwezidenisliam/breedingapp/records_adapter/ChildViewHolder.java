package com.example.muhwezidenisliam.breedingapp.records_adapter;

import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.muhwezidenisliam.breedingapp.R;


/**
 * Created by apple on 11/7/16.
 */

public class ChildViewHolder extends RecyclerView.ViewHolder {

   ImageView cat_one, cat_two, cat_three, cat_four;
     TextView category_one, category_two, category_three, category_four;
    LinearLayout ll_one,ll_two,ll_three,ll_four;


    public ChildViewHolder(View itemView) {
        super(itemView);

        category_one = (TextView) itemView.findViewById(R.id.category_one);
        category_two = (TextView)itemView.findViewById(R.id.category_two);
        category_three = (TextView)itemView.findViewById(R.id.category_three);
        category_four = (TextView)itemView.findViewById(R.id.category_four);

        cat_one = (ImageView)itemView.findViewById(R.id.record_cat_one);
        cat_two = (ImageView)itemView.findViewById(R.id.record_cat_two);
        cat_three = (ImageView)itemView.findViewById(R.id.record_cat_three);
        cat_four = (ImageView)itemView.findViewById(R.id.record_cat_four);

        ll_one = (LinearLayout)itemView.findViewById(R.id.ll_one);
        ll_two = (LinearLayout)itemView.findViewById(R.id.ll_two);
        ll_three = (LinearLayout)itemView.findViewById(R.id.ll_three);
        ll_four = (LinearLayout)itemView.findViewById(R.id.ll_four);

    }
}
