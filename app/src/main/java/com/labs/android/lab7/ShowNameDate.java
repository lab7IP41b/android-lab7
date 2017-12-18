package com.labs.android.lab7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShowNameDate extends AppCompatActivity {

    private List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_name_date);
        setTitle("Product date");
    }

    public void buttonClick(View view) {
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker2);
        Date date = new Date(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());

        products = getIntent().getParcelableArrayListExtra("allProducts");
        ListView listView = (ListView) findViewById(R.id.listView1);

        List<Product> sorted = new ArrayList<>();
        for (Product product : products) {
            String exp = product.getExpiration();
            int year = Integer.parseInt(exp.substring(6,10));
            int month = Integer.parseInt(exp.substring(3,5));
            int day = Integer.parseInt(exp.substring(0,2));
            Date dateProduct = new Date(year, month, day);

            if (date.before(dateProduct)) {
                sorted.add(product);
            }
        }

        ArrayAdapter<Product> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sorted);
        listView.setAdapter(arrayAdapter);
    }
}
