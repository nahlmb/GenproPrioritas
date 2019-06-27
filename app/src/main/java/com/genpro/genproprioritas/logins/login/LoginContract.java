package com.genpro.genproprioritas.logins.login;

public interface LoginContract {

    interface View {
        void showLoading();
        void hideLoading();
        void loginSucces(String userId);
        void loginFailed(String message);
        void goToRegister();
        void goToMain();

    }

    interface Presenter{
        void loginPush(String email, String password);

    }
}
