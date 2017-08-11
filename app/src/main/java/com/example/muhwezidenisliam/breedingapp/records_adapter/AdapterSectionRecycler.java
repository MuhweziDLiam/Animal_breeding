package com.example.muhwezidenisliam.breedingapp.records_adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.muhwezidenisliam.breedingapp.R;
import com.example.muhwezidenisliam.breedingapp.add_records.Add_Breed;
import com.example.muhwezidenisliam.breedingapp.add_records.Add_Cattle;
import com.example.muhwezidenisliam.breedingapp.add_records.Add_Heifer;
import com.example.muhwezidenisliam.breedingapp.add_records.Add_Milk;
import com.example.muhwezidenisliam.breedingapp.generate_reports.Gen_Breed;
import com.example.muhwezidenisliam.breedingapp.generate_reports.Gen_Cattle;
import com.example.muhwezidenisliam.breedingapp.generate_reports.Gen_Heifer;
import com.example.muhwezidenisliam.breedingapp.generate_reports.Gen_Milk;
import com.example.muhwezidenisliam.breedingapp.view_records.View_Breed;
import com.example.muhwezidenisliam.breedingapp.view_records.View_Cattle;
import com.example.muhwezidenisliam.breedingapp.view_records.View_Heifer;
import com.example.muhwezidenisliam.breedingapp.view_records.View_Milk;
import com.intrusoft.sectionedrecyclerview.SectionRecyclerViewAdapter;

import java.util.List;

/**
 * Created by apple on 11/7/16.
 */

public class AdapterSectionRecycler extends SectionRecyclerViewAdapter<SectionHeader, Child, SectionViewHolder, ChildViewHolder> {

    Context context;

    public AdapterSectionRecycler(Context context, List<SectionHeader> sectionHeaderItemList) {
        super(context, sectionHeaderItemList);
        this.context = context;
    }

    @Override
    public SectionViewHolder onCreateSectionViewHolder(ViewGroup sectionViewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.parent_layout, sectionViewGroup, false);
        return new SectionViewHolder(view);
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.child_layout, childViewGroup, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindSectionViewHolder(SectionViewHolder sectionViewHolder, int sectionPosition, SectionHeader sectionHeader) {
        sectionViewHolder.name.setText(sectionHeader.sectionText);
    }

    @Override
    public void onBindChildViewHolder(ChildViewHolder childViewHolder, int sectionPosition, int childPosition, Child child) {
        childViewHolder.category_one.setText(child.getCategory_one());
        childViewHolder.category_two.setText(child.getCategory_two());
        childViewHolder.category_three.setText(child.getCategory_three());
        childViewHolder.category_four.setText(child.getCategory_four());
        childViewHolder.cat_one.setImageDrawable(child.getCat_one());
        childViewHolder.cat_two.setImageDrawable(child.getCat_two());
        childViewHolder.cat_three.setImageDrawable(child.getCat_three());
        childViewHolder.cat_four.setImageDrawable(child.getCat_four());

        if(sectionPosition==0) {
            childViewHolder.ll_one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, Add_Breed.class));
                }
            });

            childViewHolder.ll_two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, Add_Cattle.class));
                }
            });

            childViewHolder.ll_three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, Add_Heifer.class));
                }
            });

            childViewHolder.ll_four.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, Add_Milk.class));
                }
            });
        }
        else if (sectionPosition==1)
        {
            childViewHolder.ll_one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, View_Breed.class));
                }
            });

            childViewHolder.ll_two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, View_Cattle.class));

                }
            });

            childViewHolder.ll_three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, View_Heifer.class));
                }
            });

            childViewHolder.ll_four.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, View_Milk.class));
                }
            });
        }
        else
        {
            childViewHolder.ll_one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, Gen_Breed.class));
                }
            });

            childViewHolder.ll_two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, Gen_Cattle.class));

                }
            });

            childViewHolder.ll_three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, Gen_Heifer.class));
                }
            });

            childViewHolder.ll_four.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, Gen_Milk.class));
                }
            });
        }
    }
}
