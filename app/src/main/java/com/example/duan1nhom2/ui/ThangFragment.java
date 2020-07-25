package com.example.duan1nhom2.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan1nhom2.DAO.KhoanChiDAO;
import com.example.duan1nhom2.DAO.KhoanThuDAO;
import com.example.duan1nhom2.DataBase.DataBase;
import com.example.duan1nhom2.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ThangFragment extends Fragment {
    DataBase dataBase;
    KhoanChiDAO khoanChiDAO;
    KhoanThuDAO khoanThuDAO;
    TextView tv_thu_thang, tv_chi_thang, tv_tichluy_thang;
    Button btn_date_thang;
    EditText edt_thongke_thang;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thang, container, false);
        tv_thu_thang = view.findViewById(R.id.tv_thu_thang);
        tv_chi_thang = view.findViewById(R.id.tv_chi_thang);
        edt_thongke_thang = view.findViewById(R.id.edt_thongke_thang);
        tv_tichluy_thang = view.findViewById(R.id.tv_tichluy_thang);
        btn_date_thang = view.findViewById(R.id.btn_date_thang);

        btn_date_thang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBase = new DataBase(getContext());
                khoanThuDAO = new KhoanThuDAO(dataBase);
                khoanChiDAO = new KhoanChiDAO(dataBase);
                String thang = edt_thongke_thang.getText().toString();
                int month = Integer.valueOf(thang);
                if (month <= 0 || month > 12) {
                    Toast.makeText(getContext(), "Tháng phải lớn hơn 0 và nhỏ hơn 12", Toast.LENGTH_SHORT).show();
                } else {
                    if (month > 0 && month < 10) {
                        thang = "0" + thang;
                    }
                    int sotienthu = khoanThuDAO.tienThuThang(thang);
                    int sotienchi = khoanChiDAO.tienChiThang(thang);
                    int tichluythang = sotienthu - sotienchi;
                    tv_thu_thang.setText(" + " + sotienthu + " $");
                    tv_chi_thang.setText(" - " + sotienchi + " $");
                    tv_tichluy_thang.setText(" " + tichluythang + " $");
                }

            }
        });
        return view;
    }
}
