package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.textfield.TextInputLayout;
import android.widget.AdapterView;

public class ConfigureBudget extends Fragment {

    private TextInputLayout numberInputLayout;
    private EditText numberInput;
    private SharedViewModel sharedViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_configure_budget, container, false);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        numberInputLayout = rootView.findViewById(R.id.InputBudgetMonthly);
        numberInput = numberInputLayout.getEditText();

        Spinner budgetSpinner = rootView.findViewById(R.id.spinnerBudgetRange);
        setupSpinner(budgetSpinner);

        // Set item selection listener for the spinner
        budgetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve the selected value from the spinner
                String selectedValue = parent.getItemAtPosition(position).toString();
                // Convert the selected value to an integer and set it as the progressBarLimit
                try {
                    int progressBarLimit = Integer.parseInt(selectedValue);
                    sharedViewModel.setProgressBarLimit(progressBarLimit);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        if (numberInput != null) {
            numberInput.setOnEditorActionListener((textView, actionId, keyEvent) -> {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE || (keyEvent != null && keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    String input = numberInput.getText().toString().trim();
                    if (!input.isEmpty()) {
                        try {
                            int progressBarLimit = Integer.parseInt(input);
                            sharedViewModel.setProgressBarLimit(progressBarLimit); // Send the value for progressBarLimit to the SharedViewModel
                        } catch (NumberFormatException e) {
                            Toast.makeText(getContext(), "Please enter a valid number", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Please enter a number", Toast.LENGTH_SHORT).show();
                    }
                    numberInput.setText("");
                    hideKeyboard();
                    handled = true;
                }
                return handled;
            });
        }

        return rootView;
    }

    private void setupSpinner(Spinner spinner) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.budget_range, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void hideKeyboard() {
        View view = requireActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.frame_layout, fragment)
                .addToBackStack(null)
                .commit();
    }
}



