package com.example.govbondapp.ui.face;

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

import com.example.govbondapp.R;

public class FaceValueFragment extends Fragment {

    private Button calculateFVBtn;
    EditText bondPrice;
    EditText bondCost;
    TextView costTxt;
    TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_face_value, container, false);

        calculateFVBtn = view.findViewById(R.id.calculateCostBtn);
        bondPrice = view.findViewById(R.id.bondPrice);
        bondCost = view.findViewById(R.id.bondCost);
        costTxt = view.findViewById(R.id.txtCost);
        mTextView = view.findViewById(R.id.textView4);

        calculateFVBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();

                String getBondCost = bondCost.getText().toString();
                Double bc = Double.parseDouble(getBondCost);

                String getBondPrice = bondPrice.getText().toString();
                Double bp = Double.parseDouble(getBondPrice);

                if (getBondPrice.isEmpty() || getBondCost.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter all values", Toast.LENGTH_SHORT).show();
                } else {
                    costTxt.setText("K" + calculateFaceValue(bc, bp));
                    mTextView.setText("Face Value");
                }
            }
        });


        return view;
    }

    private Double calculateFaceValue(Double bc, Double bp) {
        double cost = (bc * 100) / bp;
        cost = Math.round(cost * 100.0)/100.0;
        return cost;
    }

    private void closeKeyboard() {
        View view = this.getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imn = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imn.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}