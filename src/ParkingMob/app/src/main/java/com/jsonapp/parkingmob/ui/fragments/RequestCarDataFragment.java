package com.jsonapp.parkingmob.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsonapp.parkingmob.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RequestCarDataFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RequestCarDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RequestCarDataFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private TextInputEditText input_edit_plate;
    private TextInputEditText input_edit_customerNmae;
    private AppCompatButton btn_new_car;

    public RequestCarDataFragment() {
        // Required empty public constructor
    }

    public static RequestCarDataFragment newInstance() {
        RequestCarDataFragment fragment = new RequestCarDataFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_request_car_data, container, false);
        input_edit_plate = view.findViewById(R.id.input_edit_plate);
        input_edit_customerNmae = view.findViewById(R.id.input_edit_customerName);
        btn_new_car = view.findViewById(R.id.btn_new_car);

        btn_new_car.setOnClickListener(addNewCar);
        return view;
    }

    View.OnClickListener addNewCar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String plate = String.valueOf(input_edit_plate.getText());
            String customerName = String.valueOf(input_edit_customerNmae.getText());

            mListener.addNewCar(plate, customerName);
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void addNewCar(String plate, String custumerName);
    }
}
