package com.genpro.genproprioritas.genproBisnis.bisnis;

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

public class AdapterListBisnis2 extends RecyclerView.Adapter<AdapterListBisnis2.ViewHolder> {
    Context context;
    List<Bisnis.BisnisData> bisnisData;
    BisnisContract.View view;

    public AdapterListBisnis2(Context context, List<Bisnis.BisnisData> bisnisData, BisnisContract.View view) {
        this.context = context;
        this.bisnisData = bisnisData;
        this.view = view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_bisnis, viewGroup, false);
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
            namaUsaha = itemView.findViewById(R.id.txt_nama_usaha_user_bisnis);
            jenisUsaha = itemView.findViewById(R.id.txt_jenis_usaha_user_bisnis);
            prevDetailUsaha = itemView.findViewById(R.id.txt_prev_detail_usaha_bisnis);
        }
    }
}
