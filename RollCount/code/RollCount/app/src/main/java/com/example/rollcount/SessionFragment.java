package com.example.rollcount;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SessionFragment extends DialogFragment {
    /*
    creates a window to create a new session
     */
    private EditText Nametext, sidetext, dicetext;
    private CalendarView calender1;
    private OnFragmentInteractionListener listener;
    public interface OnFragmentInteractionListener {
        void onOkPressed(GameSession newGameSession);
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
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    String finalDate = formatter.format(date);
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String title = null;

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_session,null );
        Nametext = view.findViewById(R.id.nameText);
        sidetext = view.findViewById(R.id.sidesText);
        dicetext = view.findViewById(R.id.dicesText);
        calender1 = (CalendarView) view.findViewById(R.id.calendarView);

        calender1.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                finalDate = i + "/" + (i1+1) + "/" + i2;
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        Bundle bundle = getArguments();
        if(bundle!= null){
            title = "Edit Session";
        }
        else{
            title = "Add Session";
        }

        return builder
                .setView(view)
                .setTitle(title)
                .setNegativeButton("cancel", null)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialogInterface, int i){
                        String Name = Nametext.getText().toString();
                        int dice = Integer.parseInt(dicetext.getText().toString());
                        int side = Integer.parseInt(sidetext.getText().toString());
                        GameSession temp;
                        if(bundle!= null){
                            temp = (GameSession) bundle.getSerializable("session");
                            temp.setName(Name);
                        }
                        else{
                            listener.onOkPressed(new GameSession(getContext(),finalDate, Name, dice, side));
                        }


                    }
                }).create();

    }

    static SessionFragment newInstance(GameSession session) {
        Bundle args = new Bundle();
        args.putSerializable("session", session);
        SessionFragment fragment = new SessionFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
