package com.genpro.genproprioritas.drawer.gallery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.model.ResponseGallery;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    Context context;
    List<ResponseGallery.DataItem> dataItems;

    public GalleryAdapter(Context context) {
        this.context = context;
    }

    public void setDataItems(List<ResponseGallery.DataItem> dataItems) {
        this.dataItems = dataItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_gallery_viewholder, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(context).load(dataItems.get(i).getUrl()).into(viewHolder.imgGaleri);
        viewHolder.tvTanggal.setText(dataItems.get(i).getDate());
        viewHolder.tvJudul.setText(dataItems.get(i).getJudul());
    }

    @Override
    public int getItemCount() {
        return dataItems.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgGaleri;
        TextView tvJudul, tvTanggal;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //todo inisialiasi object
            imgGaleri = itemView.findViewById(R.id.img_gallery);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);

        }
    }
}
