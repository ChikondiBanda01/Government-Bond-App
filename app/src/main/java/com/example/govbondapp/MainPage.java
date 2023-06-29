package com.example.govbondapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.govbondapp.ui.face.FaceValueFragment;
import com.google.android.material.navigation.NavigationView;

public class MainPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Toolbar toolbar;
    DBHelper DB;
    TextView name;
    TextView email;
    private FragmentManager mFragmentManager;
    private Bundle mBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);


        //Initialise DB Helper
        DB = new DBHelper(MainPage.this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Get current intent
        Intent intent = getIntent();

        //Get user type extras fom intent
        String userType = intent.getStringExtra("USERTYPE");
        String emailAddress = intent.getStringExtra("EMAIL");

        mBundle = new Bundle();
        mBundle.putString("USERTYPE", userType);
        mBundle.putString("EMAIL", emailAddress);


        if (userType.equals("investor")) {
            hideItems();
            //mFragmentTransaction.add(R.id.frameLayout, mFragment).commit()

        }

        if (savedInstanceState == null) {
            WelcomeFragment welcomeFragment = new WelcomeFragment();
            welcomeFragment.setArguments(mBundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view,
                    welcomeFragment).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

    }

    private void hideItems() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        Menu getMenu = navigationView.getMenu();
        getMenu.findItem(R.id.nav_manage).setVisible(false);
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_faceValue:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view,
                        new FaceValueFragment()).commit();
                getSupportActionBar().setTitle("Face Value");
                break;
            case R.id.nav_coupon:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view,
                        new CouponFragment()).commit();
                break;
            case R.id.nav_cost:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view,
                        new CostFragment()).commit();
                break;
            case R.id.nav_price:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view,
                        new PriceFragment()).commit();
                break;
            case R.id.nav_manage:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view,
                        new ManageUsers()).commit();
                break;
            case R.id.nav_logout:
                Intent intent = new Intent(MainPage.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}