package com.genpro.genproprioritas.drawer.gallery;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.genpro.genproprioritas.model.ResponseGallery;

public class GalleryPresenter implements GalleryContract.Contract {
    GalleryContract.View view;

    public GalleryPresenter(GalleryContract.View view) {
        this.view = view;
    }

    @Override
    public void getGallery() {
        AndroidNetworking.post("http://genprodev.lavenderprograms.com/apigw/galeri/get_pictures")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(ResponseGallery.class, new ParsedRequestListener<ResponseGallery>() {
                    @Override
                    public void onResponse(ResponseGallery response) {
                        if(view!=null){
                            view.showGallery(response.getData());
                            Log.d("galeri", response.getData().toString());
                        }else {
                            view.somethingFailed("tidak ada data");
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        view.somethingFailed(anError.getLocalizedMessage());
                    }
                });



                /* .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Galeri:", response.toString());
                        try {
                            String imageUrl = response.getJSONObject(1).getJSONObject("urls").getString("regular");
                            Log.d("urls", imageUrl);
                            view.showGallery(imageUrl);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                }); */
    }
}
