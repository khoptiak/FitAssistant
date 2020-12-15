package com.project.khopt.fitassistant.util;

import com.project.khopt.fitassistant.model.User;

import java.util.Map;

public interface AuthenticationContract {

    interface View {
        Map<String, String> getUserData();
        void makeToastMessage(String string);
        void openNextActivity();

    }

    interface Model{
        interface OnFinishedListener {
            // function to be called
            // once the Handler of Model class
            // completes its execution
            void onFinished();
        }

        void signInExistedUser(AuthenticationContract.Model.OnFinishedListener onFinishedListener, User userModel);
        void createNewUser(AuthenticationContract.Model.OnFinishedListener onFinishedListener, User userModel);
        boolean userAuthentificationState();
        void signOut();
    }

    interface Presenter{

        // method to be called when
        // the button is clicked
        void onLogInButtonClick();
        void onRegisterButtonClick();
        boolean isAuthorized();
        // method to destroy
        // lifecycle of MainActivity
        void onDestroy();

    }
}
