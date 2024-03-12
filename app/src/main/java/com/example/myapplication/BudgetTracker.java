package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class BudgetTracker extends Fragment {

    private SharedViewModel sharedViewModel;
    private ProgressBar progressBar;
    private TextView outputText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_budget_tracker, container, false);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        progressBar = rootView.findViewById(R.id.vertical_progressbar);
        outputText = rootView.findViewById(R.id.OutputText);

        // Set default text
        outputText.setText("No data available");


        sharedViewModel.getProgressBarLimit().observe(getViewLifecycleOwner(), limit -> {
            if (limit != null) {
                progressBar.setMax(limit); // Set maximum progress based on the limit
                outputText.setText("Monthly Limit: " + limit); // Display budget limit
            }
        });

        sharedViewModel.getCurrentProgressValue().observe(getViewLifecycleOwner(), progress -> {
            if (progress != null) {
                progressBar.setProgress(progress);
                outputText.append("\nCurrent Budget: " + progress); // Append current progress

                // Calculate 20% of the limit
                int twentyPercent = (int) (progressBar.getMax() * 0.2);

                // Show warning toast when current progress reaches 20%
                if (progress >= twentyPercent && progress < progressBar.getMax()) {
                    Toast.makeText(getContext(), "You've reached 20% of your budget limit", Toast.LENGTH_SHORT).show();
                }

                // Show warning toast when current progress reaches 0
                if (progress == 0) {
                    Toast.makeText(getContext(), "You've reached 0% of your budget limit", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }
}

