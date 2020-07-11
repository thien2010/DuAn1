package com.example.duan1nhom2.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1nhom2.DAO.KhoanChiDAO;
import com.example.duan1nhom2.DataBase.DataBase;
import com.example.duan1nhom2.Model.KhoanChi;
import com.example.duan1nhom2.R;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

public class AdapterKhoanChi extends BaseAdapter {
    Activity context;
    List<KhoanChi> khoanChiList;
    DataBase dataBase;
    KhoanChiDAO khoanChiDAO;

    public AdapterKhoanChi(Activity context, List<KhoanChi> khoanChiList) {
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chi, parent, false);
        final KhoanChi khoanChi = (KhoanChi) getItem(position);

        TextView tv_ngaychi = convertView.findViewById(R.id.ngayChi);
        TextView tv_name = convertView.findViewById(R.id.nameChi);
        TextView tv_sotien = convertView.findViewById(R.id.sotienChi);
        ImageView img_tchi = convertView.findViewById(R.id.img_chi);
        ImageView img_delete = convertView.findViewById(R.id.delete_Chi);
        ImageView img_update = convertView.findViewById(R.id.update_Chi);

        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatter.format(khoanChi.getNgaychi());

        tv_ngaychi.setText(s);
        tv_name.setText("Chi: "+khoanChi.getNamechi());
        tv_sotien.setText("Số Tiền: "+khoanChi.getSotienchi() + " $");

        img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn Có Muốn Xóa Không")
                        .setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dataBase = new DataBase(parent.getContext());
                                khoanChiDAO = new KhoanChiDAO(dataBase);
                                khoanChiDAO.deleteKhoanChi(khoanChi.getIdchi());
                                setDatachange(khoanChiDAO.getAllKhoanChi());
                            }
                        })
                        .setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.create().show();
            }
        });

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
