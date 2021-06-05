package com.application.care.ui.statistics;

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
import com.application.care.util.HandlerColor;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

    @RequiresApi(api = Build.VERSION_CODES.P)
    public View onCreateView(@NotNull @NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_statistics, container, false);

        chart = root.findViewById(R.id.chart);
        Log.d(TAG, "onCreateView:  chart " + chart.toString());


        try {
            removeGrid();
            initChart();
            setAxisColor(chart.getAxisLeft());

        } catch (Exception e) {
            e.printStackTrace();
        }


        return root;
    }

    private void removeGrid() throws Exception {
        chart.getXAxis().setDrawGridLines(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisRight().setDrawGridLines(false);
    }

    private void setAxisColor(@NotNull AxisBase axisBase) throws Exception {
        axisBase.setAxisLineColor(HandlerColor.getInstance().getColorFromColorString(R.color.secondColor));
        axisBase.setTextColor(HandlerColor.getInstance().getColorFromColorString(R.color.secondColor));
    }

    private void manageDataSet(@NotNull BarDataSet dataSet) throws Exception {
        dataSet.setColor(HandlerColor.getInstance().getColorFromColorString(R.color.firstColor));
        dataSet.setValueTextColor(HandlerColor.getInstance().getColorFromColorString(R.color.secondColor)); // styling
        dataSet.setValueTextSize(12);
        BarData lineData = new BarData(dataSet);
        lineData.setBarWidth(0.2f);
        chart.setData(lineData);

    }

    private void manageAxis(@NotNull XAxis axis) throws Exception {
        axis.setValueFormatter(new IndexAxisValueFormatter(labels));
        axis.setTextColor(HandlerColor.getInstance().getColorFromColorString(R.color.thirdColor));
        axis.setDrawAxisLine(false);
        axis.setDrawGridLines(false);
        axis.setGranularity(1f);
        axis.setLabelCount(labels.size());

        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setLabelRotationAngle(270);
    }

    private void initChart() throws Exception {


        Log.d(TAG, "initChart: ");
//        manage zoom and scale
        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);

//        remove bot axis
        chart.getAxisRight().setDrawAxisLine(false);
        chart.getAxisRight().setDrawLabels(false);

        Map<Integer, Float> allTimeDates = HandlerDB.getInstance().getAllTimeMonth();
        List<BarEntry> entries = new ArrayList<>();

//        for (Integer month : allTimeDates.keySet()) {
//            BarEntry barEntry = new BarEntry(month, allTimeDates.get(month));
//            entries.add(barEntry);
//        }

        /*
         *   INSERT RANDOM VALUES TO TEST BARCHART
         * */
        Random random = new Random();
        for (int i = 0; i < labels.size(); ++i) {
            BarEntry barEntry = new BarEntry(i, random.nextInt(10));
            entries.add(barEntry);
        }


        BarDataSet dataSet = new BarDataSet(entries, "Work Time"); // add entries to dataset
        manageDataSet(dataSet);

        manageAxis(chart.getXAxis());
        chart.setFitBars(true);
        chart.getDescription().setText("");
        chart.invalidate(); // refresh
    }
}