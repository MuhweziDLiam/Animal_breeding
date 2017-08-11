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
public class RecyclerViewAdapterBreed extends RecyclerView.Adapter<RecyclerViewAdapterBreed.DataViewHolder> {

    List<Data_Breed> dataBreedList;

    public RecyclerViewAdapterBreed(List<Data_Breed> list){
        this.dataBreedList =list;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.breeding_card,parent,false);
        DataViewHolder dataViewHolder= new DataViewHolder(view);
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        holder.animal_id_breed.setText(dataBreedList.get(position).animal_id_breed);
        holder.service_date.setText(dataBreedList.get(position).service_date);
        holder.number_service.setText(dataBreedList.get(position).number_service);
        holder.dam.setText(dataBreedList.get(position).dam);


    }

    @Override
    public int getItemCount() {
        return dataBreedList.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder{

        TextView animal_id_breed,service_date,number_service,dam;
        CardView cardViewBreed;

        public DataViewHolder(View itemView) {
            super(itemView);
            cardViewBreed=(CardView) itemView.findViewById(R.id.card_view_breed);
            animal_id_breed=(TextView) itemView.findViewById(R.id.animal_id_breed);
            service_date=(TextView) itemView.findViewById(R.id.service_date);
            number_service = (TextView) itemView.findViewById(R.id.number_service);
            dam = (TextView) itemView.findViewById(R.id.dam_type_type);
        }



    }
}
