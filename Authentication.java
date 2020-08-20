package com.project.khopt.fitassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Authentication extends AppCompatActivity {

    private Button button;
    private  TextInputLayout iLEmail;
    private TextInputLayout iLPassword;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        iLEmail = (TextInputLayout) findViewById(R.id.tIEmail);
        iLPassword = (TextInputLayout) findViewById(R.id.tIPassword);

        mAuth = FirebaseAuth.getInstance();
        button = (Button) findViewById(R.id.btnLogIn);



//        iLPassword.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
//                if (id == R.integer.login || id == EditorInfo.IME_NULL) {
//                    attemptLogin();
//                    return true;
//                }
//                return false;
//            }
//        });


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


    }


    private  void hidingKeyBoard(View view){
        view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public void signInExistingUser(View view) {
        String email = iLEmail.getEditText().getText().toString();
        String password = iLPassword.getEditText().getText().toString();

        if(email.isEmpty())    return;
        if(email.equals("") || password.equals("")) return;
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    mUser = mAuth.getCurrentUser();
                    Intent intent = new Intent(Authentication.this, MainActivity.class);
                    finish();
                    startActivity(intent);
                    Log.d("Fit", "Authorized :" + task.isSuccessful() + mUser.toString());

                }else {
                    Toast.makeText(getApplicationContext(), "E-mail or password is wrong ", Toast.LENGTH_LONG).show();
                    Log.d("Fit", "ERROR authorization :" + task.getException() );
                }

            }

        });

    }


    public  void registerNewUser(View view){
        String email = iLEmail.getEditText().getText().toString();
        String password = iLPassword.getEditText().getText().toString();
        if(email.isEmpty())    return;
        if(email.equals("") || password.equals("")) return;

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mUser = mAuth.getCurrentUser();
                    Log.d("Fit", "New user registered: " + mUser.getEmail() + task.isSuccessful());
                    Intent intent = new Intent(Authentication.this, MainActivity.class);
                    finish();
                    startActivity(intent);

                }
                if (!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "E-mail is wrong ", Toast.LENGTH_LONG).show();
                    Log.d("Fit", "ERROR registration :" + task.getException() );
                }
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAuth.signOut();
    }
}
