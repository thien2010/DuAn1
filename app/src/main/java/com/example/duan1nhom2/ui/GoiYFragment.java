package com.example.duan1nhom2.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1nhom2.R;


public class GoiYFragment extends Fragment {
    TextView tv_one, tv_two, tv_three, tv_four, tv_five, tv_six;
    Button btn_goiy;
    EditText edt_goiy;

    public GoiYFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goi_y, container, false);
        edt_goiy = view.findViewById(R.id.edt_goiy);
        tv_one = view.findViewById(R.id.hu1);
        tv_two = view.findViewById(R.id.hu2);
        tv_three = view.findViewById(R.id.hu3);
        tv_four = view.findViewById(R.id.hu4);
        tv_five = view.findViewById(R.id.hu5);
        tv_six = view.findViewById(R.id.hu6);
        btn_goiy = view.findViewById(R.id.btngoiy);
        btn_goiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_goiy.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Bạn chưa nhập số tiền!", Toast.LENGTH_SHORT).show();
                } else {
                    long goiy = Long.parseLong(edt_goiy.getText().toString());
                    tv_one.setText("1. Nhu cầu thiết yếu: " + (goiy * 55 / 100)+" VND");
                    tv_two.setText("2. Tiết kiệm: " + (goiy * 10 / 100)+" VND");
                    tv_three.setText("3. Giáo Dục: " + (goiy * 10 / 100)+" VND");
                    tv_four.setText("4. Hưởng Thụ: " + (goiy * 10 / 100)+" VND");
                    tv_five.setText("5. Cho đi: " + (goiy * 5 / 100));
                    tv_six.setText("3. Tự do tài chính: " + (goiy * 10 / 100)+" VND");
                }
            }
        });
        return view;
    }
}
