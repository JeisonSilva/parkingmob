package com.jsonapp.parkingmob.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsonapp.parkingmob.Parking.CarDto;
import com.jsonapp.parkingmob.R;

import java.util.List;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarViewHolder> {

    private List<CarDto> carDtos;

    public CarsAdapter(List<CarDto> carDtos) {
        this.carDtos = carDtos;
    }

    public static class  CarViewHolder extends RecyclerView.ViewHolder{
        public AppCompatTextView txt_plate_car;
        public AppCompatTextView txt_consumer_user;
        public AppCompatButton compat_btn_confirm;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_plate_car = itemView.findViewById(R.id.txt_car_plate);
            txt_consumer_user = itemView.findViewById(R.id.txt_consumer_user);
            compat_btn_confirm = itemView.findViewById(R.id.compat_btn_confirm_car);
        }
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rc_item_car, viewGroup, false);
        CarViewHolder viewHolder = new CarViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder carViewHolder, int i) {
        carViewHolder.txt_plate_car.setText(String.format("Placa: %s", carDtos.get(i).getPlate()));
        carViewHolder.txt_consumer_user.setText(String.format("Client: %s", carDtos.get(i).getCustomerName()));
    }

    @Override
    public int getItemCount() {
        return this.carDtos.size();
    }
}
