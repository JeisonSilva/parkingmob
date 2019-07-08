package com.jsonapp.parkingmob.ui.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsonapp.parkingmob.R;

public class RegisterCarParkingDialogFragment extends DialogFragment {

    private static final String DIALOG_TAG = "DIALOG_PLATE";
    private RegisterCarInterface registerListener;
    private AppCompatButton btn_new_car;
    private TextInputEditText input_edit_plate;
    private TextInputEditText input_edit_customerName;

    public static RegisterCarParkingDialogFragment newInstance() {

        Bundle args = new Bundle();

        RegisterCarParkingDialogFragment fragment = new RegisterCarParkingDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public interface RegisterCarInterface{
        void addCar(String plate, String consumerName);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.from(getActivity()).inflate(R.layout.fragment_request_car_data, container, false);
        input_edit_plate = view.findViewById(R.id.input_edit_plate);
        input_edit_customerName = view.findViewById(R.id.input_edit_customerName);
        btn_new_car = view.findViewById(R.id.btn_new_car);

        btn_new_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plate = String.valueOf(input_edit_plate.getText());
                String customerName = String.valueOf(input_edit_customerName.getText());

                registerListener.addCar(plate, customerName);

                dismiss();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof RegisterCarInterface)
            this.registerListener = (RegisterCarInterface)context;
    }

    public void openDialog(FragmentManager fm){
        if(fm.findFragmentByTag(DIALOG_TAG) == null)
            show(fm, DIALOG_TAG);
    }
}
