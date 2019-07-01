package com.genpro.genproprioritas.drawer.gallery;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;

public class GalleryPresenter implements GalleryContract.Contract {
    GalleryContract.View view;

    public GalleryPresenter(GalleryContract.View view) {
        this.view = view;
    }

    @Override
    public void getGallery() {
        AndroidNetworking.get("https://api.unsplash.com/photos/")
                .addQueryParameter("client_id", "81a3132f8fc8eea2a3893cb05862cc084cd935f29dfcb616031cfc8dac1ee4c5")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Response:", response.toString());
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
                });
    }
}
