package com.labs.android.lab7.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import com.labs.android.lab7.Product;
import com.labs.android.lab7.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FragmentProductList extends Fragment {

    private OnFragmentInteractionListener mListener;
    private List<Product> products;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_fragment_product_list, container, false);
        Button button = (Button) view.findViewById(R.id.button1);
        // задаем обработчик кнопки
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCompleteTextView editText = (AutoCompleteTextView) view.findViewById(R.id.multiTextView);

                String name = editText.getText().toString();

                if (name != null) {
                    ListView listView = (ListView) view.findViewById(R.id.listView1);

                    List<Product> sorted = new ArrayList<>();
                    for (Product product : products) {
                        if (product.getName().equals(name)) {
                            sorted.add(product);
                        }
                    }

                    ArrayAdapter<Product> arrayAdapter = new ArrayAdapter<Product>(inflater.getContext(), android.R.layout.simple_list_item_1, sorted);
                    listView.setAdapter(arrayAdapter);
                    //
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Product product = (Product) adapterView.getItemAtPosition(i);
                            updateDetail(product);
                        }
                    });
                }
            }
        });
        //
        products = getActivity().getIntent().getParcelableArrayListExtra("allProducts");
        //
        String[] words = new String[products.size()];

        Set<String> names = new HashSet<>();

        int i = 0;
        for (Product product : products) {
            names.add(product.getName());
        }
        words = names.toArray(new String[names.size()]);

        AutoCompleteTextView macTv = (AutoCompleteTextView) view.findViewById(R.id.multiTextView);
        ArrayAdapter<String> aaStr = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_dropdown_item_1line, words);
        macTv.setAdapter(aaStr);

        return view;
    }

    interface OnFragmentInteractionListener {
        void onFragmentInteraction(Product product);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }
    public void updateDetail(Product product) {
        // генерируем некоторые данные
        //String curDate = new Date().toString();
        // Посылаем данные Activity
        mListener.onFragmentInteraction(product);
    }
}
