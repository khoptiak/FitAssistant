package com.project.khopt.fitassistant.util;


import android.util.Log;

import com.project.khopt.fitassistant.model.User;

public class AuthenticationPresenter implements AuthenticationContract.Presenter, AuthenticationContract.Model.OnFinishedListener{

    private AuthenticationContract.View mView;
    private  AuthenticationContract.Model mModel;


    public AuthenticationPresenter(AuthenticationContract.View view, AuthenticationContract.Model model) {
        mView = view;
        mModel = model;
    }

    @Override
    public void onFinished() {
        // here we get  after Firebase authentification  in model class finished

        if(mView != null){
            mView.makeToastMessage("Success!");
            mView.openNextActivity();
        }
    }

    @Override
    public void onLogInButtonClick() throws NullPointerException{

        User user = new User(mView.getUserData().get("email"), mView.getUserData().get("password"));
        mModel.signInExistedUser(this, user);

    }

    @Override
    public void onRegisterButtonClick() {
        User user = new User(mView.getUserData().get("email"), mView.getUserData().get("password"));
        mModel.createNewUser(this, user);
    }



    @Override
    public boolean isAuthorized(){
        Log.d("Fit", "isAuthorized() " + mModel.userAuthentificationState());
        return mModel.userAuthentificationState();
    }

    @Override
    public void onDestroy() {
        mView = null;
        mModel.signOut();
    }
}
