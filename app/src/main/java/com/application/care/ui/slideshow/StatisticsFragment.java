package com.application.care.ui.slideshow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.application.care.R;
import com.application.care.data.HandlerDB;
import com.application.care.model.WorkTime;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StatisticsFragment extends Fragment {

    private static final String TAG = "StatisticsFragment";
    private StatisticsViewModel statisticsViewModel;

    public View onCreateView(@NotNull @NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: ");
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_statistics, container, false);
        LineChart chart = container.findViewById(R.id.chart);
        try {
            List<WorkTime> allWorkTimes = HandlerDB.getInstance().getAllWorkTimes();
            List<Entry> entries = new ArrayList<>();
            for (WorkTime data : allWorkTimes) {
                // turn your data into Entry objects
                entries.add(new Entry(data.getTime(), data.getTime()));
            }

            for (int i = 0; i < 100; ++i) {
                Log.d(TAG, "onCreateView: " + i);
                entries.add(new Entry(i, i));
            }

            LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
//            dataSet.setColor();
//            dataSet.setValueTextColor(...); // styling\

            LineData lineData = new LineData(dataSet);
            chart.setData(lineData);
            chart.invalidate(); // refresh

        } catch (Exception e) {
            e.printStackTrace();
        }


        return root;
    }
}