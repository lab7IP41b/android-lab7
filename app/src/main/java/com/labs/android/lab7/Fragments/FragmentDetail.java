package com.labs.android.lab7.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.labs.android.lab7.Product;
import com.labs.android.lab7.R;

public class FragmentDetail extends Fragment {

    Product selectedProduct;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_detail, container, false);

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        FragmentTimeDemo timeDemo = new FragmentTimeDemo();
        ft.replace(R.id.timer_container, timeDemo);
        // добавить в транзакцию стек возврата
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        ft.commit();
        return view;
    }

    // обновление текстового поля
    public void setProduct(Product item) {
        selectedProduct = item;
        TextView view = (TextView) getView().findViewById(R.id.detailsText);
        String stringInfo = "Product{" +
                "id=" + item.getId() +
                ", name='" + item.getName() + '\'' +
                ", upc=" + item.getUpc() +
                ", manufacturer='" + item.getManufacturer() + '\'' +
                ", price=" + item.getPrice() +
                ", expiration='" + item.getExpiration() + '\'' +
                ", amount=" + item.getAmount() +
                '}';
        view.setText(stringInfo);
    }
}
