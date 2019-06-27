package com.genpro.genproprioritas.logins.register;

public interface RegisterContract {
    interface View {
        void registerSucces();
        void registerFailed(String message);
        void showLogin();
        void hideLogin();
    }

    interface Presenter {
        void pushRegister(String userName, String email, String password);
    }
}
