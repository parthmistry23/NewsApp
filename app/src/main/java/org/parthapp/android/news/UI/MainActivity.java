package org.parthapp.android.news.UI;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.news.R;

import org.parthapp.android.news.Helper.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        NewsFragment newsFragment = new NewsFragment();
        Bundle arguments = new Bundle();
        arguments.putString("key1", "news");
        newsFragment.setArguments(arguments);
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_container, newsFragment);
        transaction.commit();
        getSupportActionBar().setTitle("Breaking News");

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.local:
                        replaceFragment("news");
                        getSupportActionBar().setTitle("Breaking News");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.international:
                        replaceFragment("international");
                        getSupportActionBar().setTitle("International");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.sports:
                        replaceFragment("sports");
                        getSupportActionBar().setTitle("Sports");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.entertainment:
                        replaceFragment("entertainment");
                        getSupportActionBar().setTitle("Entertainment");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.health:
                        replaceFragment("health");
                        getSupportActionBar().setTitle("Health");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.food:
                        replaceFragment("food");
                        getSupportActionBar().setTitle("Food");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.art:
                        replaceFragment("art");
                        getSupportActionBar().setTitle("Art");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.technology:
                        replaceFragment("technology");
                        getSupportActionBar().setTitle("Technology");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                }
                return true;
            }
        });

    }

    private void replaceFragment(String query) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle arguments = new Bundle();
        arguments.putString("key1", query);
        newsFragment.setArguments(arguments);
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, newsFragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id= item.getItemId();
        if(id==R.id.action_settings){
            Intent settingsIntent= new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}
