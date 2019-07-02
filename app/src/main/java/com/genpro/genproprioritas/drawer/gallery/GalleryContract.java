package com.genpro.genproprioritas.drawer.gallery;

import com.genpro.genproprioritas.model.ResponseGallery;

import java.util.List;

public interface GalleryContract {
    interface View{
        void showGallery(List<ResponseGallery.DataItem> dataItems);
        void somethingFailed(String msg);
    }
    interface Contract{
        void getGallery();
    }
}
