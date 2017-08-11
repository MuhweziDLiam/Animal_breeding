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
public class RecyclerViewAdapterHeifer extends RecyclerView.Adapter<RecyclerViewAdapterHeifer.DataViewHolder> {

    List<Data_Heifer> dataHeiferList;

    public RecyclerViewAdapterHeifer(List<Data_Heifer> list){
        this.dataHeiferList =list;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.heifer_card,parent,false);
        DataViewHolder dataViewHolder= new DataViewHolder(view);
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {

        holder.animal_id_heifer.setText(dataHeiferList.get(position).animal_id_heifer);
        holder.type_of_dam.setText(dataHeiferList.get(position).type_of_dam);
        holder.weight_heifer.setText(dataHeiferList.get(position).weight_heifer);
        holder.heifer_feeding_behavior.setText(dataHeiferList.get(position).heifer_feeding_behavior);


    }

    @Override
    public int getItemCount() {
        return dataHeiferList.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder{

        TextView animal_id_heifer, type_of_dam, weight_heifer, heifer_feeding_behavior;
        CardView cardView;

        public DataViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView) itemView.findViewById(R.id.card_view_heifer);

            animal_id_heifer=(TextView) itemView.findViewById(R.id.animal_id_heifer);
            type_of_dam =(TextView) itemView.findViewById(R.id.type_of_dam);
            weight_heifer =(TextView) itemView.findViewById(R.id.weight_heifer);
            heifer_feeding_behavior =(TextView) itemView.findViewById(R.id.heifer_feeding_behavior);
        }



    }
}
