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
public class RecyclerViewAdapterMilk extends RecyclerView.Adapter<RecyclerViewAdapterMilk.DataViewHolder> {

    List<Data_Milk> dataMilkList;

    public RecyclerViewAdapterMilk(List<Data_Milk> list){
        this.dataMilkList =list;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.milk_card,parent,false);
        DataViewHolder dataViewHolder= new DataViewHolder(view);
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {

        holder.animal_id_milk.setText(dataMilkList.get(position).animal_id_milk);
        holder.milking_date.setText(dataMilkList.get(position).milking_date);
        holder.milking_times.setText(dataMilkList.get(position).milking_times);
        holder.milk_produced.setText(dataMilkList.get(position).milk_produced);

    }

    @Override
    public int getItemCount() {
        return dataMilkList.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder{
        TextView animal_id_milk, milking_date, milking_times, milk_produced;

        CardView cardView;

        public DataViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView) itemView.findViewById(R.id.card_view_milk);

            animal_id_milk=(TextView) itemView.findViewById(R.id.animal_id_milk);
            milking_date=(TextView) itemView.findViewById(R.id.milking_date);
            milking_times = (TextView) itemView.findViewById(R.id.milking_times);
            milk_produced = (TextView) itemView.findViewById(R.id.milk_produced);
        }



    }
}
