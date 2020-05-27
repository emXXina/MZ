package com.example.mz;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.Objects;

public class FirstFragment extends Fragment {

    private Counter counter = new Counter();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            counter.setCount(CountSaver.readCount(requireContext()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateCounterView(view);

        view.findViewById(R.id.button_plus).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  counter.addOne();
                  updateCounterView(requireView());
              }
        });

        view.findViewById(R.id.button_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter.subOne();
                updateCounterView(requireView());
            }
        });

        view.findViewById(R.id.button_zaehler_anpassen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getView().findViewById(R.id.button_zaehler_anpassen).setVisibility(View.INVISIBLE);
                getView().findViewById(R.id.button_minus).setVisibility(View.INVISIBLE);
                getView().findViewById(R.id.button_plus).setVisibility(View.INVISIBLE);
                getView().findViewById(R.id.textview_first).setVisibility(View.INVISIBLE);

                getView().findViewById(R.id.edit_counter).setVisibility(View.VISIBLE);
            }
        });

        EditText editCounter = view.findViewById(R.id.edit_counter);
        editCounter.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String input = v.getText().toString();
                short newCount = Short.parseShort(input);

                counter.setCount(newCount);
                updateCounterView(requireView());

                getView().findViewById(R.id.button_zaehler_anpassen).setVisibility(View.VISIBLE);
                getView().findViewById(R.id.button_minus).setVisibility(View.VISIBLE);
                getView().findViewById(R.id.button_plus).setVisibility(View.VISIBLE);
                getView().findViewById(R.id.textview_first).setVisibility(View.VISIBLE);

                getView().findViewById(R.id.edit_counter).setVisibility(View.INVISIBLE);

                return false;
            }
        });
    }

    private void updateCounterView(View view2) {
        TextView textView = view2.findViewById(R.id.textview_first);
        textView.setText(String.valueOf(counter.getCount()));

        try {
            CountSaver.saveCount(requireContext(), counter.getCount());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
