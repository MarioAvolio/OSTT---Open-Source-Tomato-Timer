package com.application.care;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager.widget.ViewPager;

import com.application.care.ui.home.HomeFragment;
import com.application.care.ui.settings.SettingsFragment;
import com.application.care.ui.slideshow.StatisticsFragment;
import com.application.care.util.HandlerAlert;
import com.application.care.util.HandlerSharedPreferences;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String HOME = "HOME";
    private static final String TAG = "MainActivity";
    private AppBarConfiguration mAppBarConfiguration;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        init HandlerSharedPreferences
        HandlerSharedPreferences.getInstance().setActivity(MainActivity.this);

//        init HandlerAlert
        HandlerAlert.setContext(MainActivity.this);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);


        Map<String, String> stringMap = new HashMap<>();
        tabLayout.setupWithViewPager(viewPager);

        prepareViewPager(viewPager);

        try {
            HandlerAlert.getInstance().showToast("Click the countdown to start");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addFragment(@NotNull Fragment fragment, @NotNull MainAdapter adapter, String name) {
        Bundle bundle = new Bundle();
        bundle.putString("title", name);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, name);
    }

    private void prepareViewPager(@NotNull ViewPager viewPager) {
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());


        addFragment(new HomeFragment(), adapter, "Home");
        addFragment(new SettingsFragment(), adapter, "Settings");

        Log.d(TAG, "prepareViewPager: make StatisticsFragment");
        addFragment(new StatisticsFragment(), adapter, "Statistics");

        viewPager.setAdapter(adapter);
    }

    private class MainAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();
        int[] imageList = {R.drawable.ic_baseline_home_24, R.drawable.ic_baseline_settings_24,
                R.drawable.ic_baseline_access_time_24};

        public MainAdapter(@NonNull @NotNull FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String s) {
            fragmentArrayList.add(fragment);
            stringArrayList.add(s);
        }

        @NonNull
        @NotNull
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        public CharSequence getPageTitle(int position) {
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),
                    imageList[position]);

//            set bound

            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());

            SpannableString spannableString = new SpannableString("     " + stringArrayList.get(position));

            ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);

            spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            return spannableString;
        }

    }
}