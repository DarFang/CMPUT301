package com.example.rollcount;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


public class histogramFragment extends DialogFragment {
    /*
    * controls the fragment of histogram, displays histogram on a new tab
    */
    private TextView sessionName;
    private GameSession Session;

    public AlertDialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.histogram, null);
        sessionName = view.findViewById(R.id.histogramText);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        Bundle bundle = getArguments();
        if(bundle != null) {
            Session = (GameSession) bundle.getSerializable("session");
            sessionName.setText(Session.getHistogram());
        }
        return builder.setView(view).setTitle("Histogram").setPositiveButton("OK", null).create();
    }
    static histogramFragment newInstance(GameSession session) {
        Bundle args = new Bundle();
        args.putSerializable("session", session);
        histogramFragment fragment = new histogramFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
