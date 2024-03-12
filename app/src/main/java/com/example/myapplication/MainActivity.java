package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.lifecycle.ViewModelProvider;
import com.example.myapplication.SharedViewModel;




public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private SharedViewModel sharedViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        // Initialize the default fragment as HomeFragment
        replaceFragment(new HomeFragment());

        // Set up bottom navigation item selected listener
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            if (item.getItemId() == R.id.menu_budget_tracker) {
                Integer progressBarLimit = sharedViewModel.getProgressBarLimit().getValue();
                if (progressBarLimit == null || progressBarLimit.intValue() == 0) {
                    runOnUiThread(() -> {
                        Toast.makeText(getApplicationContext(), "Please configure budget first", Toast.LENGTH_SHORT).show();
                    });
                    return false; // Do not navigate to the fragment
                } else {
                    selectedFragment = new BudgetTracker();
                }
            } else if (item.getItemId() == R.id.menu_configure_budget) {
                // Handle ConfigureBudget fragment here without passing any arguments
                selectedFragment = new ConfigureBudget();
            } else if (item.getItemId() == R.id.menu_bars) {
            // Handle ConfigureBudget fragment here without passing any arguments
            selectedFragment = new HomeFragment();
        } else if (item.getItemId() == R.id.menu_aF) {
                Integer progressBarLimit = sharedViewModel.getProgressBarLimit().getValue();
                if (progressBarLimit == null || progressBarLimit.intValue() == 0) {
                    runOnUiThread(() -> {
                        Toast.makeText(getApplicationContext(), "Please configure budget first", Toast.LENGTH_SHORT).show();
                    });
                    return false; // Do not navigate to the fragment
                } else {
                    selectedFragment = new AccountFinances();
                }
            } else if (item.getItemId() == R.id.menu_savings) {
                Integer progressBarLimit = sharedViewModel.getProgressBarLimit().getValue();
                if (progressBarLimit == null || progressBarLimit.intValue() == 0) {
                    runOnUiThread(() -> {
                        Toast.makeText(getApplicationContext(), "Please configure budget first", Toast.LENGTH_SHORT).show();
                    });
                    return false; // Do not navigate to the fragment
                } else {
                    selectedFragment = new Saving();
                }
            }

            if (selectedFragment != null) {
                replaceFragment(selectedFragment);
                return true;
            }
            return false;
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_navigation_menu, menu);

        // Get the menu item for "Budget Tracker"
        MenuItem menuItem = menu.findItem(R.id.menu_budget_tracker);

        // Modify the title of "Budget Tracker" to include a line break
        if (menuItem != null) {
            menuItem.setTitle("Budget\nTracker");
        }

        return super.onCreateOptionsMenu(menu);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        int selectedItemId = getSelectedItemId(); // Determine the appropriate menu item ID based on the current fragment
        if (selectedItemId != -1) {
            bottomNavigationView.setSelectedItemId(selectedItemId);
        }
    }

    private int getSelectedItemId() {
        // Determine the appropriate menu item ID based on the current fragment
        // For example, if the current fragment is HomeFragment, return R.id.menu_bars
        // If it's ConfigureBudgetFragment, return R.id.menu_configure_budget, and so on.
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout);
        if (currentFragment instanceof HomeFragment) {
            return R.id.menu_bars;
        } else if (currentFragment instanceof ConfigureBudget) {
            return R.id.menu_configure_budget;
        } else if (currentFragment instanceof BudgetTracker) {
            return R.id.menu_budget_tracker;
        } else if (currentFragment instanceof AccountFinances) {
            return R.id.menu_aF;
        } else if (currentFragment instanceof Saving) {
            return R.id.menu_savings;
        }
        return -1; // Default value, if the fragment is not one of the above
    }
}
