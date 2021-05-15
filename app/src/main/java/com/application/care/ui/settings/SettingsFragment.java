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
        IndicatorSeekBar.with(getContext());
        try {
            seekBar.setIndicatorTextFormat("${PROGRESS} %");
        } catch (Exception e) {
            e.printStackTrace();
        }

//        seekBar.customSectionTrackColor(new ColorCollector() {
//            @Override
//            public boolean collectSectionTrackColor(int[] colorIntArr) {
//                //the length of colorIntArray equals section count
//                colorIntArr[0] = HandlerColor.getInstance().getColorFromColorString(R.color.color_blue);
//                colorIntArr[1] = HandlerColor.getInstance().getColorFromColorString(R.color.color_gray);
//                colorIntArr[2] = Color.parseColor("#FF4081");
//                return true; //True if apply color , otherwise no change
//            }
//        });


        return root;
    }
}