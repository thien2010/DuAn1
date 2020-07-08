package com.example.duan1nhom2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1nhom2.Model.KhoanChi;
import com.example.duan1nhom2.R;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

public class AdapterKhoanChi extends BaseAdapter {
    Context context;
    List<KhoanChi> khoanChiList;

    public AdapterKhoanChi(Context context, List<KhoanChi> khoanChiList) {
        this.context = context;
        this.khoanChiList = khoanChiList;
    }

    @Override
    public int getCount() {
        return khoanChiList.size();
    }

    @Override
    public KhoanChi getItem(int position) {
        return khoanChiList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chi, parent, false);
        KhoanChi khoanChi = (KhoanChi) getItem(position);

        TextView tv_ngaychi = convertView.findViewById(R.id.ngayChi);
        TextView tv_name = convertView.findViewById(R.id.nameChi);
        TextView tv_sotien = convertView.findViewById(R.id.sotienChi);
        ImageView img_tchi = convertView.findViewById(R.id.img_chi);
        ImageView img_delete = convertView.findViewById(R.id.delete_Chi);
        ImageView img_update = convertView.findViewById(R.id.update_Chi);

        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatter.format(khoanChi.getNgaychi());

        tv_ngaychi.setText("Ngày Chi: "+ s);
        tv_name.setText("Tên Khoản Chi: "+khoanChi.getNamechi());
        tv_sotien.setText("Số Tiền: "+khoanChi.getSotienchi());

        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void setDatachange(List<KhoanChi> items) {
        this.khoanChiList = items;
        notifyDataSetChanged();
    }
}
