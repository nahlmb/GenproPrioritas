package com.genpro.genproprioritas.editBisnis;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.detailBisnis.DetailBisnisActivity;
import com.genpro.genproprioritas.main.MainActivity;
import com.genpro.genproprioritas.model.Bisnis;

public class EditBisnisActivity extends AppCompatActivity implements EditBisnisContract.View{
    EditBisnisPresenter presenter;
    EditText edtEditBisnis1, edtEditBisnis2, edtEditBisnis3, edtEditBisnis4, edtEditBisnis5, edtEditBisnis6, edtEditBisnis7, edtEditBisnis8;
    Spinner spnEditBisnis1, spnEditBisnis2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bisnis);

        presenter = new EditBisnisPresenter(this);

        //Edit Text Nya ini
        edtEditBisnis1 = findViewById(R.id.edt_bisnis_nama);
        edtEditBisnis2 = findViewById(R.id.edt_bisnis_tentang);
        edtEditBisnis3 = findViewById(R.id.edt_bisnis_karyawan);
        edtEditBisnis4 = findViewById(R.id.edt_bisnis_cabang);
        edtEditBisnis5 = findViewById(R.id.edt_bisnis_telp);
        edtEditBisnis6 = findViewById(R.id.edt_bisnis_merk);
        edtEditBisnis7 = findViewById(R.id.edt_bisnis_facebook);
        edtEditBisnis8 = findViewById(R.id.edt_bisnis_instagram);

        // Kalo Ini Spinner Nya Oke
        spnEditBisnis1 = findViewById(R.id.spn_bisnis_jenis);
        spnEditBisnis2 = findViewById(R.id.spn_bisnis_omset);

        setAllBisnisData();


    }

    @Override
    public void setAllBisnisData() {
        Bisnis.BisnisData bisnisData = (Bisnis.BisnisData) getIntent().getSerializableExtra("BISNIS_DATA_EDIT");
        edtEditBisnis1.setText(bisnisData.getNmUsaha());
        edtEditBisnis2.setText(bisnisData.getTentangUsaha());
        edtEditBisnis3.setText(bisnisData.getJmlKaryawan());
        edtEditBisnis4.setText(bisnisData.getJmlCabang());
        edtEditBisnis5.setText(bisnisData.getNoTlp());
        edtEditBisnis6.setText(bisnisData.getMerk());
        edtEditBisnis7.setText(bisnisData.getFacebook());
        edtEditBisnis8.setText(bisnisData.getInstagram());

        //spiners
        ArrayAdapter<CharSequence> adapterSpinnerJenisBisnis = ArrayAdapter.createFromResource(this, R.array.jenis_bisnis, android.R.layout.simple_spinner_item);
        adapterSpinnerJenisBisnis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnEditBisnis1.setAdapter(adapterSpinnerJenisBisnis);

        if (bisnisData.getNmBisnisLain() != null) {
            int spinnerPositionJenisBisnis = adapterSpinnerJenisBisnis.getPosition(bisnisData.getNmBisnisLain());
            spnEditBisnis1.setSelection(spinnerPositionJenisBisnis);

        }

        ArrayAdapter<CharSequence> adapterSpinnerOmsetTahunan = ArrayAdapter.createFromResource(this, R.array.omset_tahunan, android.R.layout.simple_spinner_item);
        adapterSpinnerOmsetTahunan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnEditBisnis2.setAdapter(adapterSpinnerOmsetTahunan);

        if (bisnisData.getOmsetTahunan() != null) {
            int spinnerPositionOmset = adapterSpinnerOmsetTahunan.getPosition(bisnisData.getOmsetTahunan());
            spnEditBisnis2.setSelection(spinnerPositionOmset);

        }



    }

    @Override
    public void updateBisnisDataClick() {
        //todo tugas yazid
        //String pushTentangBisnis = edtbisnis1.getText.toString;
        //Sting pushSpinnerJenisBisnis = spnEditBisnis1.getSelectedItem().toString();

        //todo gabung kan semua string kedalam satu array disini
        String[] arrayDataBisnis = {/*string1, string2, string3*/};

        //todo suruh presenter mem-push
        presenter.updateBisnisData(arrayDataBisnis);



    }

    @Override
    public void onBackPressed() {
        finish();

    }

    @Override
    public void recreate() {
        super.recreate();
    }
}
