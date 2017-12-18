package com.labs.android.lab7.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.labs.android.lab7.R;

import java.util.Date;

public class FragmentTimeDemo extends Fragment {

    public FragmentTimeDemo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_time_demo, container, false);

        Button updateButton = (Button) view.findViewById(R.id.updateButton);
        final TextView updateBox = (TextView) view.findViewById(R.id.textBox);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String curDate = new Date().toString();
                updateBox.setText(curDate);
            }
        });
        return view;
    }

}
