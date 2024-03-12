package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import android.widget.Switch;

public class InputExpenses extends Fragment {

    private SharedViewModel sharedViewModel;
    private int inputCurrentBudget;
    private Switch bottomSwitch;
    private TextInputEditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_input_expenses, container, false);

        // Initialize the SharedViewModel
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Find bottomSwitch
        bottomSwitch = rootView.findViewById(R.id.bottomSwitch);

        // Set the switch to checked state if you want InputBudget to be initially shown
        bottomSwitch.setChecked(false);

        // Set OnCheckedChangeListener for the switch
        bottomSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Switch is checked, navigate to InputBudget fragment
                navigateToFragment(new AccountFinances());
            } else {
                // Switch is unchecked, navigate to ConfigureBudget fragment
                navigateToFragment(new ConfigureBudget());
            }
        });

        // Set up the EditText
        setupEditText(rootView);

        return rootView;
    }

    private void setupEditText(View rootView) {
        TextInputLayout inputLayout = rootView.findViewById(R.id.InputBudgetCurrent);
        editText = inputLayout.findViewById(R.id.editText2);
        if (editText != null) {
            editText.setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    sendCurrentValue();
                    // Hide the keyboard
                    hideKeyboard();
                    return true;
                }
                return false;
            });
        }
    }

    private void sendCurrentValue() {
        String input = editText.getText().toString().trim();
        if (!input.isEmpty()) {
            try {
                inputCurrentBudget = Integer.parseInt(input);
                sharedViewModel.setCurrentProgressValue(inputCurrentBudget);
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Please enter a valid number", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Please enter a number", Toast.LENGTH_SHORT).show();
        }
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        // Define fragment animations
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
        // Replace the fragment
        transaction.replace(R.id.frame_layout, fragment)
                .addToBackStack(null)
                .commit();
    }
}
