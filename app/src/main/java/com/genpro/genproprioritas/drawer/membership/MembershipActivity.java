package com.genpro.genproprioritas.drawer.membership;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.model.Membership;

import java.util.List;

public class MembershipActivity extends AppCompatActivity implements MembershipContract.View {
    MembershipPresenter presenter;
    RecyclerView rvMembership;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership);

        presenter = new MembershipPresenter(this);
        presenter.getMembers();


        rvMembership = findViewById(R.id.rv_membership);
        back = findViewById(R.id.back_membership);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMembers(List<Membership.DataItem> dataItems) {
        if(dataItems != null && dataItems.size()>0){
            MembershipAdapter membershipAdapter = new MembershipAdapter(this, dataItems);
            rvMembership.setAdapter(membershipAdapter);
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            rvMembership.setLayoutManager(staggeredGridLayoutManager);
            rvMembership.setNestedScrollingEnabled(true);
            membershipAdapter.notifyDataSetChanged();
        }else {
            rvMembership.setAdapter(null);
        }

    }

    @Override
    public void somethingFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
