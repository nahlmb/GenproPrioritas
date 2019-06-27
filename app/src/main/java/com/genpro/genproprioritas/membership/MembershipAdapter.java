package com.genpro.genproprioritas.membership;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.model.Member;
import com.genpro.genproprioritas.model.Membership;

import java.util.List;

public class MembershipAdapter extends RecyclerView.Adapter<MembershipAdapter.ViewHolder> {
    Context context;
    List<Member> members;

    public MembershipAdapter(Context context, List<Member> members) {
        this.context = context;
        this.members = members;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_member, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.namaUser.setText(members.get(i).getNamaDepan() + " " + members.get(i).getNamaBelakang());
        viewHolder.noAnggotaUser.setText("No Anggota : " + members.get(i).getNoAnggota());
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaUser, noAnggotaUser;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.findViewById(R.id.txt_member_name);
            itemView.findViewById(R.id.txt_no_anggota);
        }


    }
}
