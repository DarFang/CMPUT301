package com.example.rollcount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomList extends ArrayAdapter<GameSession> {
    /*
    List contenets for the session details under main activity
     */
    private ArrayList<GameSession> sessions;
    private Context context;
    public CustomList(Context context, ArrayList<GameSession> sessions) {
        super(context, 0, sessions);
        this.sessions = sessions;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.content, parent, false);
        }
        GameSession session  = sessions.get(position);
        TextView NameText = view.findViewById(R.id.name_text);
        TextView dateText = view.findViewById(R.id.date_text);
        TextView diceText = view.findViewById(R.id.dice_text);
        NameText.setText(session.getName());
        dateText.setText(session.getDate());
        diceText.setText(session.getDice());
        return view;
    }
}
