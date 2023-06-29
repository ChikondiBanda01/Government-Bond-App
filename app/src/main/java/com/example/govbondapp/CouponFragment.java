package com.example.govbondapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class CouponFragment extends Fragment {

    private Button calculateCostBtn;
    EditText faceValue;
    EditText couponRate;
    EditText price;
    TextView couponAmount;
    TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_coupon, container, false);

        calculateCostBtn = view.findViewById(R.id.calculateCouponBtn);
        faceValue = view.findViewById(R.id.FaceValue);
        couponRate = view.findViewById(R.id.couponRate);
        price = view.findViewById(R.id.price);
        couponAmount = view.findViewById(R.id.txtCouponAmount);
        mTextView = view.findViewById(R.id.textView4);

        calculateCostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getFaceValue = faceValue.getText().toString();
                Float fv = Float.parseFloat(getFaceValue);

                String getCouponRate = couponRate.getText().toString();
                Float cr = Float.parseFloat(getCouponRate);

                String getPrice = price.getText().toString();
                Float p = Float.parseFloat(getPrice);

                closeKeyboard();

                if(getFaceValue.isEmpty() || getCouponRate.isEmpty() || getPrice.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter all values", Toast.LENGTH_SHORT).show();
                } else{
                    mTextView.setText("Coupon Amount");
                    couponAmount.setText("K" + calculateCoupon(fv, cr, p));

                }
            }
        });
        return view;
    }

    private void closeKeyboard() {
        View view = this.getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imn = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imn.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private Double calculateCoupon(Float faceValue, Float couponRate , Float price) {
        Double amount = faceValue * ((couponRate / 100) * 0.5) * (price/100);
        amount = Math.round(amount * 100.0)/100.0;
        return amount;
    }

}
