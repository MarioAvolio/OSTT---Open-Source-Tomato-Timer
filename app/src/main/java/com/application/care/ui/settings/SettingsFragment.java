package com.application.care.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.application.care.R;
import com.application.care.util.SeekBarFactory;

public class SettingsFragment extends Fragment {


    private static final String TAG = "SettingsFragment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        SeekBarFactory.setView(root);
        try {
            SeekBarFactory.getInstance(); //init
        } catch (Exception e) {
            e.printStackTrace();
        }

        return root;


    }
}