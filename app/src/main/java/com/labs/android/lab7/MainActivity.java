package com.labs.android.lab7;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.labs.android.lab7.Fragments.ShowNameProduct;
import com.labs.android.lab7.lab7.ActivityOnStock;
import com.labs.android.lab7.lab7.ShowManufacturer;
import com.labs.android.lab7.lab7.ShowNamePrice;
import com.labs.android.lab7.lab7.ShowNameProd;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static String TAG = "[ Main Activity ]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Was created");
        setContentView(R.layout.activity_main);
        setTitle("Main Activity");
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_all: {
                        Intent intent = new Intent(MainActivity.this, ShowAllProducts.class);
                        startActivity(intent);
                        return true;
                    }
                    case R.id.nav_search: {
                        Intent intent2 = new Intent(MainActivity.this, ShowNameProduct.class);
                        startActivity(intent2);
                    }
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //TextView headerView = (TextView) findViewById(R.id.header);
        switch(id){
            case R.id.show_all :
                Intent intent = new Intent(MainActivity.this, ShowAllProducts.class);
                startActivityForResult(intent, 1);
                return true;
            case R.id.search_by_name:
                Intent intent2 = new Intent(MainActivity.this, ShowNameProduct.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Was started");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Was paused");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Was resumed");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Was stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Was destroyed");
    }

    public void buttonClickShowAll(View view) {
        Intent intent = new Intent(MainActivity.this, ShowAllProducts.class);
        startActivityForResult(intent, 1);
    }

    public void buttonClickShowName(View view) {
        Intent intent = new Intent(MainActivity.this, ShowNameProd.class);
        startActivity(intent);
    }

    public void buttonClickShowNameDate(View view) {
        Intent intent = new Intent(MainActivity.this, ShowNameDate.class);
        startActivity(intent);
    }

    public void buttonClickShowNamePrice(View view) {
        Intent intent = new Intent(MainActivity.this, ShowNamePrice.class);
        startActivity(intent);
    }

    public void buttonClickShowManufacturer(View view) {
        Intent intent = new Intent(MainActivity.this, ShowManufacturer.class);
        startActivity(intent);
    }

    public void buttonClickShowStock(View view) {
        Intent intent = new Intent(MainActivity.this, ActivityOnStock.class);
        startActivity(intent);
    }

    /**
     * Saves collection to binary file.
     * @param context
     */
    private void saveDataToFile(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput("products.data", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            //oos.writeObject(products);
            Log.d(TAG, "Collection was saved to file");
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads collection from binary file.
     * If any error occurs, loads default (x5) collection.
     * @param context
     * @return Product[] array.
     */
    private Product[] getDataFromFile(Context context) {
        Product[] temp = null;
        try {
            InputStream inputStream = context.openFileInput("products.data");

            if ( inputStream != null ) {
                ObjectInputStream ois = new ObjectInputStream(inputStream);
                temp = (Product[]) ois.readObject();
                ois.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "exc in readin, loading default collection..");
            Product[] products = new Product[] {
                    new Product(0, "first product", 1234, "Belavia", 25.0, "30.10.2017", 1),
                    new Product(1, "second thing", 5462, "Trello", 10.0, "30.08.2017", 7),
                    new Product(2, "one", 5523, "Aawd", 10.0, "30.09.2017", 7),
                    new Product(3, "one", 5522, "AGAD", 10.0, "30.10.2017", 7),
                    new Product(4, "two", 4123, "aAWD", 10.0, "30.12.2017", 7)
            };
            return products;
        }
        return temp;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void buttonClickMaps(View view) {
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
