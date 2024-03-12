package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        CardView cv1 = rootView.findViewById(R.id.C1);
        CardView cv2 = rootView.findViewById(R.id.C2);
        CardView cv3 = rootView.findViewById(R.id.C3);
        CardView cv4 = rootView.findViewById(R.id.C4);

        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);

        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Clicked Cv1");
                // Replace with the fragment you want to navigate to
                Fragment fragment = new BudgetTracker();
                replaceFragment(fragment);
                bottomNavigationView.setSelectedItemId(R.id.menu_budget_tracker);
            }
        });

        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Clicked Cv2");
                // Replace with the fragment you want to navigate to
                Fragment fragment = new ConfigureBudget();
                replaceFragment(fragment);
                bottomNavigationView.setSelectedItemId(R.id.menu_configure_budget);
            }
        });

        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Clicked Cv3");
                // Replace with the fragment you want to navigate to
                Fragment fragment = new AccountFinances();
                replaceFragment(fragment);
                bottomNavigationView.setSelectedItemId(R.id.menu_aF);
            }
        });

        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Clicked Cv4");
                // Replace with the fragment you want to navigate to
                Fragment fragment = new Saving();
                replaceFragment(fragment);
                bottomNavigationView.setSelectedItemId(R.id.menu_savings);
            }
        });

        return rootView;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
