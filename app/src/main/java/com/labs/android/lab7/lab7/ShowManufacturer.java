package com.labs.android.lab7.lab7;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.labs.android.lab7.EditProductMenu;
import com.labs.android.lab7.R;

public class ShowManufacturer extends AppCompatActivity {

    ListView userList;
    DataBaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_name_manufacturer);
        setTitle("Product by manufacturer");
        //
        userList = (ListView)findViewById(R.id.listView1);
        databaseHelper = new DataBaseHelper(getApplicationContext());
    }

    public void buttonClick(View view) {
        EditText editText = (EditText) findViewById(R.id.editText11);
        String name = editText.getText().toString();

        db = databaseHelper.getReadableDatabase();
        userCursor =  db.rawQuery("select * from "+ DataBaseHelper.TABLE + " where " + DataBaseHelper.COLUMN_MANUFACTURER + "='" + name + "'", null);
        String[] headers = new String[] {DataBaseHelper.COLUMN_ID, DataBaseHelper.COLUMN_NAME,
                DataBaseHelper.COLUMN_MANUFACTURER, DataBaseHelper.COLUMN_PRICE,
                DataBaseHelper.COLUMN_EXPIRATION, DataBaseHelper.COLUMN_AMOUNT};
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

                Intent intent = new Intent(ShowManufacturer.this, EditProductMenu.class);
                intent.putExtra("idproduct", idProduct);
                startActivityForResult(intent, 1);
            }
        });

        db.close();
    }
}
