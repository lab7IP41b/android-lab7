package com.labs.android.lab7;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.labs.android.lab7.lab7.DataBaseHelper;

public class EditProductMenu extends AppCompatActivity {

    DataBaseHelper sqlHelper;
    SQLiteDatabase db;
    private long idProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product_menu);
        //
        Intent intent = getIntent();
        idProduct = intent.getExtras().getLong("idproduct");
        //
        sqlHelper = new DataBaseHelper(this);
        db = sqlHelper.getWritableDatabase();
        Cursor query = db.rawQuery("SELECT * FROM products where " + DataBaseHelper.COLUMN_ID + "=" + String.valueOf(idProduct), null);

        if(query.moveToFirst() && query.getCount() >= 1) {
            do {
                TextView textView = (TextView) findViewById(R.id.editText28);
                textView.setText(String.valueOf(query.getInt(0)));
                textView = (TextView) findViewById(R.id.editText29);
                textView.setText(query.getString(1));
                textView = (TextView) findViewById(R.id.editText30);
                textView.setText(String.valueOf(query.getInt(2)));
                textView = (TextView) findViewById(R.id.editText31);
                textView.setText(String.valueOf(query.getString(3)));
                textView = (TextView) findViewById(R.id.editText34);
                textView.setText(String.valueOf(query.getDouble(4)));
                textView = (TextView) findViewById(R.id.editText35);
                textView.setText(query.getString(5));
                textView = (TextView) findViewById(R.id.editText24);
                textView.setText(String.valueOf(query.getInt(6)));
            } while (false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    public void buttonClickEditProduct(View view) {
        try {
            int id = Integer.parseInt(((TextView) findViewById(R.id.editText28)).getText().toString());
            String name = ((TextView) findViewById(R.id.editText29)).getText().toString();
            int upc = Integer.parseInt(((TextView) findViewById(R.id.editText30)).getText().toString());
            String manufacturer = ((TextView) findViewById(R.id.editText31)).getText().toString();
            double price = Double.parseDouble(((TextView) findViewById(R.id.editText34)).getText().toString());
            String expiration = ((TextView) findViewById(R.id.editText35)).getText().toString();
            int amount = Integer.parseInt(((TextView) findViewById(R.id.editText24)).getText().toString());

            db.execSQL("UPDATE products set " +
                    DataBaseHelper.COLUMN_NAME + "='" + name + "', " +
                    DataBaseHelper.COLUMN_UPC + "=" + upc + ", " +
                    DataBaseHelper.COLUMN_MANUFACTURER + "='" + manufacturer + "', " +
                    DataBaseHelper.COLUMN_PRICE + "=" + price + ", " +
                    DataBaseHelper.COLUMN_EXPIRATION + "='" + expiration + "', " +
                    DataBaseHelper.COLUMN_AMOUNT + "=" + amount + " " +
                    " where " + DataBaseHelper.COLUMN_ID + "=" + idProduct
            );
            Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("[ EditMenu ]", "exception in creating object to update");
            Toast.makeText(getApplicationContext(), "Wrong fields", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(this, ShowAllProducts.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void buttonClickDeleteProduct(View view) {
        DialogFragment customDialog = new CustomDialog();
        customDialog.show(getSupportFragmentManager(), "custom");
    }

    public void okClicked() {
        db.execSQL("DELETE FROM products WHERE _id =" + idProduct + ";");
        Intent intent = new Intent(this, ShowAllProducts.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void cancelClicked() {
        Intent intent = new Intent(this, ShowAllProducts.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
