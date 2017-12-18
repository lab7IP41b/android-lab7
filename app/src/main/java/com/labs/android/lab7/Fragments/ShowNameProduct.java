package com.labs.android.lab7.Fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.labs.android.lab7.Product;
import com.labs.android.lab7.R;

public class ShowNameProduct extends AppCompatActivity implements FragmentProductList.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_name_product);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Search by name");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onFragmentInteraction(Product item) {
        FragmentDetail fragment = (FragmentDetail) getSupportFragmentManager().findFragmentById(R.id.fragment7);
        if (fragment != null && fragment.isInLayout()) {
            fragment.setProduct(item);
        }
    }
}
