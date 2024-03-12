package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class AccountFinances extends Fragment {
    private SharedViewModel sharedViewModel;

    private TextView outputTextAccountFinance;
    private static final String TAG = "HomeFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account_finances, container, false);


        CardView cvf1 = rootView.findViewById(R.id.CvAf1);
        CardView cvf2 = rootView.findViewById(R.id.CvAf2);


        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);

        // Initialize the SharedViewModel
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Find the TextView
        outputTextAccountFinance = rootView.findViewById(R.id.budget);

        // Set default text
        outputTextAccountFinance.setText("Current Budget: No data available");

        // Observe the currentProgressValue
        sharedViewModel.getCurrentProgressValue().observe(getViewLifecycleOwner(), progress -> {
            if (progress != null) {
                // Update the TextView with the progress value
                outputTextAccountFinance.setText("Current Progress: " + progress);
            }
        });

        cvf1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Clicked Cv1");
                // Replace with the fragment you want to navigate to
                Fragment fragment = new InputIncome();
                replaceFragment(fragment);
            }
        });

        cvf2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Clicked Cv2");
                // Replace with the fragment you want to navigate to
                Fragment fragment = new InputExpenses();
                replaceFragment(fragment);
            }
        });

        sharedViewModel.getCurrentProgressValue().observe(getViewLifecycleOwner(), progress -> {
            if (progress != null) {
                outputTextAccountFinance.setText("Current Progress: " + progress); // Set current progress
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