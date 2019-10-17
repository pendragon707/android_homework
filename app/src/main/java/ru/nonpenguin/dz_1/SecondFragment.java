package ru.nonpenguin.dz_1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {

    public static String NUMBER_KEY = "Number";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.second_fragment, container, false);

        TextView textview = view.findViewById(R.id.item);
        Integer number =  getArguments().getInt(NUMBER_KEY);

        textview.setText(String.valueOf(number));
        textview.setTextColor(number % 2 == 0 ? Color.RED : Color.BLUE);
        return view;
    }
}
