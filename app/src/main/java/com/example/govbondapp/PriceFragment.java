package com.example.govbondapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PriceFragment extends Fragment {

    private Button calculatePriceBtn;
    Spinner mSpinner;
    EditText couponRate;
    EditText interestRate;
    TextView bond;
    TextView price;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_price, container, false);

        calculatePriceBtn = view.findViewById(R.id.calculatePriceBtn);
        mSpinner = view.findViewById(R.id.spinnerTenure);
        couponRate = view.findViewById(R.id.couponRate);
        interestRate = view.findViewById(R.id.interestRate);
        bond = view.findViewById(R.id.bond);
        price = view.findViewById(R.id.txtPrice);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.Tenures,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        calculatePriceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n;
                String getTenure = mSpinner.getSelectedItem().toString();
                switch (getTenure) {
                    case "2 years":
                        n = 4;
                        break;
                    case "3 years":
                        n = 6;
                        break;
                    case "5 years":
                        n = 10;
                        break;
                    case "7 years":
                        n = 14;
                        break;
                    case "10 years":
                        n = 20;
                        break;
                    case "15 years":
                        n = 30;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + getTenure);
                }

                String getCouponRate = couponRate.getText().toString();
                Float cr = Float.parseFloat(getCouponRate);

                String getInterestRate = interestRate.getText().toString();
                Float ir = Float.parseFloat(getInterestRate);
                ir /= 100;

                closeKeyboard();

                if (getCouponRate.isEmpty() || getInterestRate.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter all values", Toast.LENGTH_SHORT).show();
                } else {
                    bond.setText("Bond Price(per K100)");
                    price.setText("K" + calculatePrice(n, cr, ir));
                }
            }
        });

        return view;
    }

    private Double calculatePrice(int n, Float cr, Float ir) {
        Double price = 0.0;
        double denominator = 1 + (ir * 0.5);
        for (int i = 1; i < n; i++) {
            price += (cr * 0.5) / Math.pow(denominator, i);
        }
        price += ((cr * 0.5) + 100) / Math.pow(denominator, n);
        price = Math.round(price * 100.0)/100.0;
        return price;
    }

    private void closeKeyboard() {
        View view = this.getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imn = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imn.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
