package com.jsonapp.parkingmob.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsonapp.parkingmob.Parking.CarDto;
import com.jsonapp.parkingmob.R;
import com.jsonapp.parkingmob.ui.adapters.CarsAdapter;

import java.io.Serializable;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ParkingManangerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ParkingManangerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ParkingManangerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "cars";

    // TODO: Rename and change types of parameters
    private List<CarDto> mParam1;

    private OnFragmentInteractionListener mListener;
    private FloatingActionButton fb_addCar;
    private RecyclerView rc_cars;

    public ParkingManangerFragment() {
        // Required empty public constructor
    }

    public static ParkingManangerFragment newInstance(List<CarDto> carDtos) {
        ParkingManangerFragment fragment = new ParkingManangerFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("cars", (Serializable) carDtos);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (List<CarDto>) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parking_mananger, container, false);
        fb_addCar = view.findViewById(R.id.fb_add_car);
        rc_cars = view.findViewById(R.id.rc_cars);

        if (getArguments() != null) {
            mParam1 = (List<CarDto>) getArguments().getSerializable(ARG_PARAM1);

            CarsAdapter carsAdapter = new CarsAdapter(this.mParam1);
            rc_cars.setLayoutManager(new LinearLayoutManager(getContext()));
            rc_cars.setAdapter(carsAdapter);
        }

        fb_addCar.setOnClickListener(addNewCar);
        return view;
    }

    View.OnClickListener addNewCar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestCarData();
        }
    };

    private void requestCarData() {
        if(mListener instanceof OnFragmentInteractionListener)
            mListener.requestCarData();
    }

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
        void requestCarData();
    }
}
