package com.example.duan1nhom2.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.duan1nhom2.DAO.KhoanThuDAO;
import com.example.duan1nhom2.DataBase.DataBase;
import com.example.duan1nhom2.Model.KhoanThu;
import com.example.duan1nhom2.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ThongKeFragment extends Fragment {
    KhoanThuDAO khoanThuDAO;
    DataBase dataBase;
    List<KhoanThu> thuList;
    public ThongKeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thong_ke, container, false);
//        dataBase = new DataBase(getActivity());
//        khoanThuDAO = new KhoanThuDAO(dataBase);
//        thuList = khoanThuDAO.getAllKhoanThu();
//
//        Button btn_datepiker = view.findViewById(R.id.btn_datePicker1);
//
//        btn_datepiker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar calendar = Calendar.getInstance();
//                int year = calendar.get(Calendar.YEAR);
//                int month = calendar.get(Calendar.MONTH);
//                final int day = calendar.get(Calendar.DAY_OF_MONTH);
//                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        calendar.set(year, month, dayOfMonth);
//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                        date.setText(simpleDateFormat.format(calendar.getTime()));
//                    }
//                }, year, month, day);
//                datePickerDialog.show();
//            }
//        });
////        int thungay = khoanThuDAO.tongThuTheoNgay(Integer.parseInt(date.getText().toString()));
////        tongthutheongay.setText("Tong thu theo ngay: " + thungay);
        return view;
    }
}
