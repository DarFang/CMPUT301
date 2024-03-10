package com.example.listycity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.io.Serializable;

public class AddCityFragment extends DialogFragment {
    private EditText cityName;
    private EditText provinceName;
    private OnFragmentInteractionListener listener;
    public interface OnFragmentInteractionListener {
        void onOkPressed(City newCity);
    }
    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener){
            listener = (OnFragmentInteractionListener) context;

        }
        else{
            throw new RuntimeException(context.toString()+ " must implement OnFragmentInteractionListener");
        }
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String title = null;
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_city_fragment_layout,null );
        cityName = view.findViewById(R.id.cityEditText);
        provinceName = view.findViewById(R.id.provinceEditText);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        Bundle bundle = getArguments();
        if(bundle!= null){
            title = "Edit City";
        }
        else{
            title = "Add City";
        }
        return builder
                .setView(view)
                .setTitle(title)
                .setNegativeButton("cancel", null)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialogInterface, int i){
                        String city = cityName.getText().toString();
                        String province = provinceName.getText().toString();
                        City temp;
                        if(bundle!= null){
                            temp = (City) bundle.getSerializable("city");
                            temp.setProvince(province);
                            temp.setCity(city);
                        }
                        else{
                            listener.onOkPressed(new City(city, province));
                        }


                    }
        }).create();

    }
    static AddCityFragment newInstance(City city) {
        Bundle args = new Bundle();
        args.putSerializable("city", city);
        AddCityFragment fragment = new AddCityFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
