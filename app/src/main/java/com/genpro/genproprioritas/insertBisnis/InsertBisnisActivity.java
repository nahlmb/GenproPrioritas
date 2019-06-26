package com.genpro.genproprioritas.insertBisnis;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.genpro.genproprioritas.R;

public class InsertBisnisActivity extends AppCompatActivity implements InsertBisnisContract.View {
    InsertBisnisPresenter presenter;
    EditText edtInsertBisnis1, edtInsertBisnis2, edtInsertBisnis3, edtInsertBisnis4, edtInsertBisnis5, edtInsertBisnis6, edtInsertBisnis7, edtInsertBisnis8, edtInsertBisnis9;
    Spinner spnInsertBisnis1, spnInsertBisnis2;
    Button saveBisnisButton;
    SharedPreferences userInfoSharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_bisnis);

        //setup
        presenter = new InsertBisnisPresenter(this);
        userInfoSharedpref = getSharedPreferences("userInfo", MODE_PRIVATE);

        edtInsertBisnis1 = findViewById(R.id.edt_bisnis_nama_bisnis);
        edtInsertBisnis2 = findViewById(R.id.edt_bisnis_tentang_bisnis);
        edtInsertBisnis3 = findViewById(R.id.edt_bisnis_karyawan_bisnis);
        edtInsertBisnis4 = findViewById(R.id.edt_bisnis_cabang_bisnis);
        edtInsertBisnis5 = findViewById(R.id.edt_bisnis_telp_bisnis);
        edtInsertBisnis6 = findViewById(R.id.edt_bisnis_merk_bisnis);
        edtInsertBisnis7 = findViewById(R.id.edt_bisnis_facebook_bisnis);
        edtInsertBisnis8 = findViewById(R.id.edt_bisnis_instagram_bisnis);
        edtInsertBisnis9 = findViewById(R.id.edt_nama_bisnis_lain_bisnis);


        // spinner
        spnInsertBisnis1 = findViewById(R.id.spn_bisnis_jenis_bisnis);
        spnInsertBisnis2 = findViewById(R.id.spn_bisnis_omset_bisnis);

        //click event
        saveBisnisButton = findViewById(R.id.btn_save_new_bisnis);
        saveBisnisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonBuatBisnisBaruClicked();
            }
        });
    }

    @Override
    public void pushNewBisnis(String[] dataBisnisBaru) {
        presenter.pushNewBisnis(dataBisnisBaru);
    }

    @Override
    public void buttonBuatBisnisBaruClicked() {
        if(edtInsertBisnis1.getText().length()>0
                && edtInsertBisnis2.getText().length()>0
                && edtInsertBisnis3.getText().length()>0
                && edtInsertBisnis4.getText().length()>0
                && edtInsertBisnis5.getText().length()>0
                && edtInsertBisnis6.getText().length()>0
                && edtInsertBisnis7.getText().length()>0
                && edtInsertBisnis8.getText().length()>0
                && edtInsertBisnis9.getText().length()>0
        ){
            getArrayDataBisnis();
        }else{
            somethingFailed("Lengkapi data data bisnis mu..");
        }


    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void success() {
        onBackPressed();
    }

    @Override
    public void getArrayDataBisnis() {
        String pushUserId = userInfoSharedpref.getString("userId", "");
        Log.d("userid", pushUserId);
        String pushNamaUsaha = edtInsertBisnis1.getText().toString();
        String pushTentangUsaha = edtInsertBisnis2.getText().toString();
        String pushJumlahKaryawan = edtInsertBisnis3.getText().toString();
        String pushJumlahCabang = edtInsertBisnis4.getText().toString();
        String pushNomorTelepon = edtInsertBisnis5.getText().toString();
        String pushMerk = edtInsertBisnis6.getText().toString();
        String pushFacebook = edtInsertBisnis7.getText().toString();
        String pushInstagram = edtInsertBisnis8.getText().toString();
        String pushNamaLainUsaha = edtInsertBisnis9.getText().toString();


        String pushSpinnerJenisBisnis = spnInsertBisnis1.getSelectedItem().toString();
        String pushSpinnerOmsetTahunan = spnInsertBisnis2.getSelectedItem().toString();

        //todo gabung kan semua string kedalam satu array disini
        String[] arrayDataBisnisBaru = {
                pushUserId,
                pushNamaUsaha,
                pushSpinnerJenisBisnis,
                pushTentangUsaha,
                pushJumlahKaryawan,
                pushJumlahCabang,
                pushNomorTelepon,
                pushSpinnerOmsetTahunan,
                pushMerk,
                pushFacebook,
                pushInstagram,
                pushNamaLainUsaha
        };

        pushNewBisnis(arrayDataBisnisBaru);

    }


    @Override
    public void somethingFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
