package com.example.duan1nhom2.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1nhom2.DAO.KhoanThuDAO;
import com.example.duan1nhom2.DAO.NguoiDungDAO;
import com.example.duan1nhom2.DataBase.DataBase;
import com.example.duan1nhom2.Model.KhoanThu;
import com.example.duan1nhom2.Model.NguoiDung;
import com.example.duan1nhom2.R;

import java.util.List;

public class AdapterNguoiDung extends BaseAdapter {
    Activity context;
    List<NguoiDung> nguoiDungList;
    DataBase dataBase;
    NguoiDungDAO nguoiDungDAO;


    public AdapterNguoiDung(Activity context, List<NguoiDung> nguoiDungList) {
        this.context = context;
        this.nguoiDungList = nguoiDungList;
    }

    @Override
    public int getCount() {
        return nguoiDungList.size();
    }

    @Override
    public Object getItem(int position) {
        return nguoiDungList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_taikhoan, parent, false);
        final NguoiDung nguoiDung = (NguoiDung) getItem(position);
        final TextView update_username = convertView.findViewById(R.id.userName);
        final TextView update_fullname = convertView.findViewById(R.id.fullName);
        final TextView update_phone = convertView.findViewById(R.id.phoneNumber);
        final TextView update_email = convertView.findViewById(R.id.email);
        ImageView img_edtND = convertView.findViewById(R.id.edit_nguoidung);

        update_username.setText(nguoiDung.getUsername());
        update_phone.setText(nguoiDung.getPhone());
        update_fullname.setText(nguoiDung.getFullname());
        update_email.setText(nguoiDung.getEmail());

        img_edtND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final View view1 = LayoutInflater.from(context).inflate(R.layout.sua_tai_khoan, null);
                builder.setView(view1);
                nguoiDung.setUsername(update_username.getText().toString());
                nguoiDung.setFullname(update_fullname.getText().toString());
                nguoiDung.setEmail(update_email.getText().toString());
                nguoiDung.setPhone(update_phone.getText().toString());
                dataBase = new DataBase(parent.getContext());
                nguoiDungDAO = new NguoiDungDAO(dataBase);
                nguoiDungDAO.updateNguoiDung(nguoiDung);
                setDatachange(nguoiDungDAO.getAllNguoiDung());
            }
        });
        return convertView;

    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void setDatachange(List<NguoiDung> items) {
        this.nguoiDungList = items;
        notifyDataSetChanged();
    }
}