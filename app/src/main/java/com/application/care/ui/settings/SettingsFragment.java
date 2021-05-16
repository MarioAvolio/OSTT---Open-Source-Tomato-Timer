package com.application.care.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.application.care.R;
import com.warkiz.widget.IndicatorSeekBar;

public class SettingsFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        IndicatorSeekBar seekBar = null;
        IndicatorSeekBar.with(requireContext());
        try {
            seekBar.setIndicatorTextFormat("${PROGRESS} %");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return root;
    }

    private void saveSettings(String id, ViewGroup containter) {
        IndicatorSeekBar indicatorSeekBar = containter.findViewById(R.)
    }
}