package com.labs.android.lab7;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.labs.android.lab7.lab7.DataBaseHelper;

public class AddNewProduct extends AppCompatActivity {

    DataBaseHelper sqlHelper;
    SQLiteDatabase db;
    long userId=0;

    static final String STATE_SCORE = "playerScore";
    static final String STATE_LEVEL = "playerLevel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);

        EditText customEditText = (EditText) findViewById(R.id.editText8);
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(5);
        customEditText.setFilters(FilterArray);
        customEditText.setLineSpacing(2, 1);

        if (savedInstanceState != null) {
            //mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
            //mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
        }
        //
        sqlHelper = new DataBaseHelper(this);
        db = sqlHelper.getWritableDatabase();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        //savedInstanceState.putInt(STATE_SCORE, mCurrentScore);
        //savedInstanceState.putInt(STATE_LEVEL, mCurrentLevel);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void buttonClickAddProduct(View view) {
        try {
            int id = Integer.parseInt(((TextView) findViewById(R.id.editText8)).getText().toString());
            String name = ((TextView) findViewById(R.id.editText7)).getText().toString();
            int upc = Integer.parseInt(((TextView) findViewById(R.id.editText6)).getText().toString());
            String manufacturer = ((TextView) findViewById(R.id.editText5)).getText().toString();
            double price = Double.parseDouble(((TextView) findViewById(R.id.editText4)).getText().toString());
            String expiration = ((TextView) findViewById(R.id.editText2)).getText().toString();
            int amount = Integer.parseInt(((TextView) findViewById(R.id.editText)).getText().toString());
            //
            ContentValues cv = new ContentValues();
            cv.put(DataBaseHelper.COLUMN_NAME, name);
            cv.put(DataBaseHelper.COLUMN_UPC, upc);
            cv.put(DataBaseHelper.COLUMN_MANUFACTURER, manufacturer);
            cv.put(DataBaseHelper.COLUMN_PRICE, price);
            cv.put(DataBaseHelper.COLUMN_EXPIRATION, expiration);
            cv.put(DataBaseHelper.COLUMN_AMOUNT, amount);

            if (userId > 0) {
                db.update(DataBaseHelper.TABLE, cv, DataBaseHelper.COLUMN_ID + "=" + String.valueOf(userId), null);
            } else {
                db.insert(DataBaseHelper.TABLE, null, cv);
            }
            //
            Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("[ EditMenu ]", "exception in creating object to update");
            Toast.makeText(getApplicationContext(), "Wrong fields", Toast.LENGTH_SHORT).show();
        }
        db.close();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
