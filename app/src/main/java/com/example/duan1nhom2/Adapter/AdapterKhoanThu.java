package com.example.duan1nhom2.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1nhom2.DAO.KhoanThuDAO;
import com.example.duan1nhom2.DataBase.DataBase;
import com.example.duan1nhom2.Model.KhoanThu;
import com.example.duan1nhom2.R;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

public class AdapterKhoanThu extends BaseAdapter {
    Context context;
    List<KhoanThu> khoanThuList;

    public AdapterKhoanThu(Context context, List<KhoanThu> khoanThuList) {
        this.context = context;
        this.khoanThuList = khoanThuList;
    }

    @Override
    public int getCount() {
        return khoanThuList.size();
    }

    @Override
    public KhoanThu getItem(int position) {
        return khoanThuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_thu, parent, false);
        final KhoanThu khoanThu = (KhoanThu) getItem(position);
        TextView tv_ngaythu = convertView.findViewById(R.id.ngayThu);
        TextView tv_name = convertView.findViewById(R.id.nameThu);
        TextView tv_sotien = convertView.findViewById(R.id.sotienThu);
        ImageView img_thu = convertView.findViewById(R.id.img_thu);
        ImageView img_delete = convertView.findViewById(R.id.delete_Thu);
        ImageView img_update = convertView.findViewById(R.id.update_Thu);
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatter.format(khoanThu.getNgaythu());
        tv_ngaythu.setText("Ngày Thu: "+ s);
        tv_name.setText("Tên Khoản Thu: "+khoanThu.getNamethu());
        tv_sotien.setText("Số Tiền: "+khoanThu.getSotien());


        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void setDatachange(List<KhoanThu> items) {
        this.khoanThuList = items;
        notifyDataSetChanged();
    }
}
