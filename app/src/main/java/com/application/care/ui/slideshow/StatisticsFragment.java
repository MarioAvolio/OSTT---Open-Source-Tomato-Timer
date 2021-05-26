package com.application.care.ui.slideshow;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.application.care.R;
import com.application.care.data.HandlerDB;
import com.application.care.model.WorkTime;
import com.application.care.util.HandlerColor;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatisticsFragment extends Fragment {

    private static final String TAG = "StatisticsFragment";
    private final List<String> labels = new ArrayList<>(Arrays.asList(
            "Jan",
            "Feb",
            "Mar",
            "Apr",
            "May",
            "Jun",
            "Jul",
            "Aug",
            "Sep",
            "Oct",
            "Nov",
            "Dec"
    ));
    private HorizontalBarChart chart;
    private List<WorkTime> allWorkTimes;
    private List<BarEntry> entries;

    @RequiresApi(api = Build.VERSION_CODES.P)
    public View onCreateView(@NotNull @NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_statistics, container, false);

        chart = root.findViewById(R.id.chart);
        Log.d(TAG, "onCreateView:  chart " + chart.toString());

        try {
            setColor();
            initChart();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private void setColor() throws Exception {
        chart.setGridBackgroundColor(HandlerColor.getInstance().getColorFromColorString(R.color.firstColor));
    }

    private void manageDataSet(@NotNull BarDataSet dataSet) throws Exception {
        dataSet.setColor(HandlerColor.getInstance().getColorFromColorString(R.color.firstColor));
        dataSet.setValueTextColor(HandlerColor.getInstance().getColorFromColorString(R.color.firstColor)); // styling
        dataSet.setValueTextSize(12);
        BarData lineData = new BarData(dataSet);
        lineData.setBarWidth(0.2f);
        chart.setData(lineData);

    }

    private void manageAxis(@NotNull XAxis axis) throws Exception {
        axis.setValueFormatter(new IndexAxisValueFormatter(labels));
        axis.setTextColor(HandlerColor.getInstance().getColorFromColorString(R.color.firstColor));
        axis.setDrawAxisLine(false);
        axis.setDrawGridLines(false);
        axis.setGranularity(1f);
        axis.setLabelCount(labels.size());

        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setLabelRotationAngle(270);
    }

    private void initChart() throws Exception {

        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);

        allWorkTimes = HandlerDB.getInstance().getAllWorkTimes();
        entries = new ArrayList<>();

//        for (WorkTime data : allWorkTimes) {
//            // turn your data into Entry objects
//            entries.add(new BarEntry(data.getMonth(), data.getTime()));
//        }

        for (int i = 0; i < labels.size(); ++i) {
            Log.d(TAG, "onCreateView: " + i);
            entries.add(new BarEntry(i, i));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Work Time"); // add entries to dataset
        manageDataSet(dataSet);

        XAxis xAxis = chart.getXAxis();

        YAxis yAxis = chart.getAxisRight();

        manageAxis(xAxis);

        chart.setFitBars(true);
        chart.getDescription().setText("");
        chart.invalidate(); // refresh
    }
}