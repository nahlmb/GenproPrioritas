package com.genpro.genproprioritas.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.model.Bisnis;

import java.util.List;

public class AdapterListBisnis extends RecyclerView.Adapter<AdapterListBisnis.ViewHolder> {
    Context context;
    List<Bisnis.BisnisData> bisnisData;
    MainContract.View view;

    public AdapterListBisnis(Context context, List<Bisnis.BisnisData> bisnisData, MainContract.View view) {
        this.context = context;
        this.bisnisData = bisnisData;
        this.view = view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_bisnis_black, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.jenisUsaha.setText(bisnisData.get(i).getNmBisnisLain());
        viewHolder.namaUsaha.setText(bisnisData.get(i).getNmUsaha());
        viewHolder.prevDetailUsaha.setText(bisnisData.get(i).getTentangUsaha());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.goToDetailBisnis(bisnisData.get(i), i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bisnisData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaUsaha, jenisUsaha, prevDetailUsaha;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaUsaha = itemView.findViewById(R.id.txt_nama_usaha_user);
            jenisUsaha = itemView.findViewById(R.id.txt_jenis_usaha_user);
            prevDetailUsaha = itemView.findViewById(R.id.txt_prev_detail_usaha);
        }
    }
}
