package com.application.care.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.application.care.R;
import com.application.care.model.SeekBarFactory;
import com.warkiz.widget.IndicatorSeekBar;

public class SettingsFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        SeekBarFactory.setView(root);


        final IndicatorSeekBar breakIndicatorSeekBar;
        final IndicatorSeekBar workIndicatorSeekBar;
        final IndicatorSeekBar longBreakIndicatorSeekBar;

        try {
//            Init bar
            workIndicatorSeekBar = SeekBarFactory.getInstance().getSeekBar(R.id.work_time);
            breakIndicatorSeekBar = SeekBarFactory.getInstance().getSeekBar(R.id.break_time);
            longBreakIndicatorSeekBar = SeekBarFactory.getInstance().getSeekBar(R.id.long_break_time);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return root;
    }


}