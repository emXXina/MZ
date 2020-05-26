package com.example.mz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  counter.addOne();
                  updateCounterView(requireView());
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
