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

import com.example.duan1nhom2.DAO.KhoanChiDAO;
import com.example.duan1nhom2.DAO.KhoanThuDAO;
import com.example.duan1nhom2.DataBase.DataBase;
import com.example.duan1nhom2.R;


public class NamFragment extends Fragment {
    DataBase dataBase;
    KhoanChiDAO khoanChiDAO;
    KhoanThuDAO khoanThuDAO;
    TextView tv_thu_nam, tv_chi_nam, tv_tichluy_nam;
    Button btn_date_nam;
    EditText edt_thongke_nam;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nam, container, false);
        tv_thu_nam = view.findViewById(R.id.tv_thu_nam);
        tv_chi_nam = view.findViewById(R.id.tv_chi_nam);
        edt_thongke_nam = view.findViewById(R.id.edt_thongke_nam);
        tv_tichluy_nam = view.findViewById(R.id.tv_tichluy_thang);
        btn_date_nam = view.findViewById(R.id.btn_date_nam);
        tv_tichluy_nam = view.findViewById(R.id.tv_tichluy_nam);
        btn_date_nam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nam = edt_thongke_nam.getText().toString();
                if (nam.isEmpty()) {
                    Toast.makeText(getContext(), "Bạn chưa nhập năm!", Toast.LENGTH_SHORT).show();
                } else {
                    int year = Integer.valueOf(nam);
                    if (year > 0) {
                        dataBase = new DataBase(getContext());
                        khoanChiDAO = new KhoanChiDAO(dataBase);
                        khoanThuDAO = new KhoanThuDAO(dataBase);
                        long tienthunam = khoanThuDAO.tienThuNam(nam);
                        int tienchinam = khoanChiDAO.tienChiNam(nam);
                        long tichluynam = tienthunam - tienchinam;
                        tv_thu_nam.setText(" + " + tienthunam + " VND");
                        tv_chi_nam.setText(" - " + tienchinam + " VND");
                        tv_tichluy_nam.setText(" " + tichluynam + " VND");
                    }
                }
            }
        });
        return view;
    }
}
