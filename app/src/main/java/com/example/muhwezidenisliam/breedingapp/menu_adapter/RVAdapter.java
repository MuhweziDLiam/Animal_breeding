package com.example.muhwezidenisliam.breedingapp.menu_adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.muhwezidenisliam.breedingapp.R;
import com.example.muhwezidenisliam.breedingapp.add_records.Add_Breed;
import com.example.muhwezidenisliam.breedingapp.add_records.Add_Cattle;
import com.example.muhwezidenisliam.breedingapp.add_records.Add_Heifer;
import com.example.muhwezidenisliam.breedingapp.add_records.Add_Milk;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.DataViewHolder> {


    List<Person> dataList;
    Context context;

    public RVAdapter(List<Person> list, Context context){
        this.dataList=list;
        this.context = context;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        DataViewHolder dataViewHolder= new DataViewHolder(view);
        return dataViewHolder;
    }



    @Override
    public void onBindViewHolder(RVAdapter.DataViewHolder holder, final int position) {
        holder.person_photo.setImageResource(dataList.get(position).photoId);
        holder.person_name.setText(dataList.get(position).name);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position)
                {
                    case 0:
                        context.startActivity(new Intent(context, Add_Breed.class));
                        break;
                    case 1:
                        context.startActivity(new Intent(context, Add_Cattle.class));
                        break;
                    case 2:
                        context.startActivity(new Intent(context, Add_Heifer.class));
                        break;
                    case 3:
                        context.startActivity(new Intent(context, Add_Milk.class));
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder{
        ImageView person_photo;
        TextView person_name;
        CardView cardView;

        public DataViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView) itemView.findViewById(R.id.cv);
            person_photo =(ImageView) itemView.findViewById(R.id.person_photo);
            person_name=(TextView) itemView.findViewById(R.id.person_name);
        }



    }
}
