package com.genpro.genproprioritas.drawer.gallery;

public interface GalleryContract {
    interface View{
        void showGallery(String url);
    }
    interface Contract{
        void getGallery();
    }
}
