package com.project.khopt.fitassistant.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.project.khopt.fitassistant.R;
import com.project.khopt.fitassistant.model.AuthenticationModel;
import com.project.khopt.fitassistant.util.AuthenticationContract;
import com.project.khopt.fitassistant.util.AuthenticationPresenter;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class Authentication extends AppCompatActivity implements AuthenticationContract.View{

    private TextInputLayout iLEmail;
    private TextInputLayout iLPassword;

    private AuthenticationContract.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        iLEmail = (TextInputLayout) findViewById(R.id.tIEmail);
        iLPassword = (TextInputLayout) findViewById(R.id.tIPassword);

        iLPassword.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if ( id == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER) {

                    hidingKeyBoard(iLPassword);
                    iLPassword.clearFocus();

                    return true;
                }
                return false;
            }
        });

        mPresenter = new AuthenticationPresenter(this, new AuthenticationModel());

        if(mPresenter.isAuthorized()) {
            Log.d("Fit", "user is authorised");
            openNextActivity();
        }


    }

    public void signInExistingUser(View view){
        mPresenter.onLogInButtonClick();
    }

    public void registerNewUser(View view){
        mPresenter.onRegisterButtonClick();
    }

    @Override
    public Map<String, String> getUserData() {

        String email = iLEmail.getEditText().getText().toString();
        String password  = iLPassword.getEditText().getText().toString();


        if(isValid(email, password)) {
            Map<String, String> data = new HashMap<>();
            data.put("email" , iLEmail.getEditText().getText().toString());
            data.put("password" , iLPassword.getEditText().getText().toString());
            return data;
        }
        return null;
    }

    @Override
    public void openNextActivity(){
        Intent intent = new Intent(Authentication.this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    public boolean isValid(String email, String password) {
        if(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
                && password.length() > 5)
            return true;
        else {
            makeToastMessage(" Invalid email or password!");
        }
        return false;
    }

    @Override
    public void makeToastMessage(String string) {
        Toast.makeText(this,  string, Toast.LENGTH_LONG).show();
    }

    private  void hidingKeyBoard(View view){
        view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Delete after testing
      //  mPresenter.onDestroy();
    }


}

