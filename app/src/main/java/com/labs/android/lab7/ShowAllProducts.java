package com.labs.android.lab7;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.labs.android.lab7.Fragments.ShowNameProduct;
import com.labs.android.lab7.lab7.DataBaseHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShowAllProducts extends AppCompatActivity {

    ListView userList;
    DataBaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_products);
        //
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //
        userList = (ListView)findViewById(R.id.listView1);
        databaseHelper = new DataBaseHelper(getApplicationContext());
        //
        setTitle("Show All Items");
    }

    @Override
    public void onResume() {
        super.onResume();
        // открываем подключение
        db = databaseHelper.getReadableDatabase();
        //получаем данные из бд в виде курсора
        userCursor =  db.rawQuery("select * from "+ DataBaseHelper.TABLE, null);
        // определяем, какие столбцы из курсора будут выводиться в ListView
        String[] headers = new String[] {DataBaseHelper.COLUMN_ID, DataBaseHelper.COLUMN_NAME,
                DataBaseHelper.COLUMN_MANUFACTURER, DataBaseHelper.COLUMN_PRICE,
                DataBaseHelper.COLUMN_EXPIRATION, DataBaseHelper.COLUMN_AMOUNT};
        // создаем адаптер, передаем в него курсор
        userAdapter = new SimpleCursorAdapter(this, R.layout.item,
                userCursor, headers, new int[]{R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6}, 0);
        //
        userList.setAdapter(userAdapter);
        //
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor cursor = ((SimpleCursorAdapter) adapterView.getAdapter()).getCursor();
                cursor.moveToPosition(i);
                long idProduct = cursor.getLong(cursor.getColumnIndex(DataBaseHelper.COLUMN_ID));

                Intent intent = new Intent(ShowAllProducts.this, EditProductMenu.class);
                intent.putExtra("idproduct", idProduct);
                startActivityForResult(intent, 1);
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
                Intent intent = new Intent(ShowAllProducts.this, ShowAllProducts.class);
                startActivityForResult(intent, 1);
                return true;
            case R.id.search_by_name:
                Intent intent2 = new Intent(ShowAllProducts.this, ShowNameProduct.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void buttonClickAddNewProduct(View view) {
        Intent intent = new Intent(ShowAllProducts.this, AddNewProduct.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("[ ShowAllProducts ]", "Was destroyed");
        db.close();
        userCursor.close();
    }
}
