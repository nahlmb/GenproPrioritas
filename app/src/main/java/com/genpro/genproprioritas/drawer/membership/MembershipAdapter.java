package com.genpro.genproprioritas.drawer.membership;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.model.Membership;

import java.util.List;

public class MembershipAdapter extends RecyclerView.Adapter<MembershipAdapter.ViewHolder> {
    Context context;
    List<Membership.DataItem> data;

    public MembershipAdapter(Context context, List<Membership.DataItem> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_member, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if(data != null && data.size()>0){
            viewHolder.namaUser.setText(data.get(i).getNamaDepan() + " " + data.get(i).getNamaBelakang());
            viewHolder.noAnggotaUser.setText(data.get(i).getNoAnggota());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaUser, noAnggotaUser;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            namaUser = itemView.findViewById(R.id.txt_member_name_item);
            noAnggotaUser = itemView.findViewById(R.id.txt_no_anggota_item);
        }


    }
}
