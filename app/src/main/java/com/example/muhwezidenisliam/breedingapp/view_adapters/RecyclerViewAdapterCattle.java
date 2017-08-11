package com.example.muhwezidenisliam.breedingapp.view_adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.muhwezidenisliam.breedingapp.R;

import java.util.List;

/**
 * Created by HP ElitBook on 9/17/2016.
 */
public class RecyclerViewAdapterCattle extends RecyclerView.Adapter<RecyclerViewAdapterCattle.DataViewHolder> {

    List<Data_Cattle> dataCattleList;

    public RecyclerViewAdapterCattle(List<Data_Cattle> list){
        this.dataCattleList =list;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cattle_card,parent,false);
        DataViewHolder dataViewHolder= new DataViewHolder(view);
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {

        holder.animal_id_cattle.setText(dataCattleList.get(position).animal_id_cattle);
        holder.cattle_dob.setText(dataCattleList.get(position).cattle_dob);
        holder.breed_cattle.setText(dataCattleList.get(position).breed_cattle);
        holder.sex_cattle.setText(dataCattleList.get(position).sex_cattle);
        holder.weight_cattle.setText(dataCattleList.get(position).weight_cattle);


    }

    @Override
    public int getItemCount() {
        return dataCattleList.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder{

        TextView animal_id_cattle, cattle_dob, sex_cattle, breed_cattle, weight_cattle;
        CardView cardView;

        public DataViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView) itemView.findViewById(R.id.card_view_cattle);

            animal_id_cattle=(TextView) itemView.findViewById(R.id.animal_id_cattle);
            cattle_dob=(TextView) itemView.findViewById(R.id.cattle_dob);
            sex_cattle=(TextView) itemView.findViewById(R.id.sex_cattle);
            breed_cattle=(TextView) itemView.findViewById(R.id.breed_cattle);
            weight_cattle=(TextView) itemView.findViewById(R.id.weight_cattle);

        }



    }
}
