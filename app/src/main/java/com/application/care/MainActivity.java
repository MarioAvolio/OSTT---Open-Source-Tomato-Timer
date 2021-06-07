package com.application.care;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.application.care.ui.home.HomeFragment;
import com.application.care.ui.settings.SettingsFragment;
import com.application.care.ui.statistics.StatisticsFragment;
import com.application.care.util.HandlerAlert;
import com.application.care.util.HandlerSharedPreferences;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final String HOME = "HOME";
    private static final String TAG = "MainActivity";
    private Map<Integer, IconText> iconTextMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: ");


//        init HandlerSharedPreferences
        HandlerSharedPreferences.setActivity(MainActivity.this);

//        init HandlerAlert
        HandlerAlert.setContext(MainActivity.this);

        iconTextMap = new HashMap<>();


        FragmentManager fragmentManager = getSupportFragmentManager();
        ViewPager2 viewPager2 = findViewById(R.id.view_pager);
//        OrdersPagerAdapterFactory ordersPagerAdapterFactory = new OrdersPagerAdapterFactory(fragmentManager, getLifecycle());

        OrdersPagerAdapterFactory ordersPagerAdapterFactory = new OrdersPagerAdapterFactory(MainActivity.this);
        viewPager2.setAdapter(ordersPagerAdapterFactory);


        TabLayout tabLayout = findViewById(R.id.tab_layout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull @NotNull TabLayout.Tab tab, int position) {

                IconText iconText = getIconText(position);
                tab.setText(iconText.getName());
                tab.setIcon(iconText.getIcon());
            }
        }
        );
        tabLayoutMediator.attach();
    }

    @NotNull
    private IconText getIconText(int position) {
        if (iconTextMap.containsKey(position)) {
            return Objects.requireNonNull(iconTextMap.get(position));
        } else {
            IconText iconText = null;
            switch (position) {
                case 1:
                    iconText = new IconText(R.drawable.ic_baseline_settings_24, "Settings");
                    break;
                case 2:
                    iconText = new IconText(R.drawable.ic_baseline_access_time_24, "Statistics");
                    break;
                default:
                    iconText = new IconText(R.drawable.ic_baseline_home_24, "Home");
                    break;
            }

            iconTextMap.put(position, iconText);
            return Objects.requireNonNull(iconText);
        }
    }

    private static class OrdersPagerAdapterFactory extends FragmentStateAdapter {

        private final Map<Integer, Fragment> integerFragmentMap;

        public OrdersPagerAdapterFactory(@NonNull @NotNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
            integerFragmentMap = new HashMap<>();
        }

        public OrdersPagerAdapterFactory(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
            Log.d(TAG, "OrdersPagerAdapterFactory: ");
            integerFragmentMap = new HashMap<>();
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {

            Log.d(TAG, "createFragment: " + position);
            if (integerFragmentMap.containsKey(position)) {
                return Objects.requireNonNull(integerFragmentMap.get(position));
            }

            Fragment fragment = null;
            switch (position) {
                case 2:
                    Log.d(TAG, "createFragment: StatisticsFragment");
                    fragment = new StatisticsFragment();
                    break;
                case 1:
                    Log.d(TAG, "createFragment: SettingsFragment");
                    fragment = new SettingsFragment();
                    break;
                default:
                    Log.d(TAG, "createFragment: Default");
                    fragment = new HomeFragment();
                    break;
            }

            integerFragmentMap.put(position, fragment);
            return fragment;
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }

    private class IconText {
        private final int icon;
        private final String name;

        public IconText(int icon, String name) {
            this.icon = icon;
            this.name = name;
        }

        public int getIcon() {
            return icon;
        }

        public String getName() {
            return name;
        }
    }
}