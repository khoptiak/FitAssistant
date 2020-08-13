package com.project.khopt.fitassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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

public class Authentication extends AppCompatActivity {

    private FirebaseConfiguration firebaseConfig;
   // private Button button;
    private  TextInputLayout iLEmail;
    private TextInputLayout iLPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        iLEmail = (TextInputLayout) findViewById(R.id.tIEmail);
        iLPassword = (TextInputLayout) findViewById(R.id.tIPassword);
        firebaseConfig = new FirebaseConfiguration();
       // button = (Button) findViewById(R.id.btnLogIn);



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


    }

    public void signInExistingUser(View view) {
        attemptLogin();
    }

    public void attemptLogin() {
        String email = iLEmail.getEditText().getText().toString();
        String password = iLPassword.getEditText().getText().toString();

        if(firebaseConfig.authentification(email, password)){
            Intent intent = new Intent(Authentication.this, MainActivity.class);
            finish();
            startActivity(intent);
        }

           // Toast.makeText(getApplicationContext(), "E-mail or password is wrong ", Toast.LENGTH_LONG).show();


    }

    public  void registerNewUser(View view){
        String email = iLEmail.getEditText().getText().toString();
        String password = iLPassword.getEditText().getText().toString();

        if(firebaseConfig.registration(email, password)){
            Intent intent = new Intent(Authentication.this, MainActivity.class);
            finish();
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), "Registration failed ", Toast.LENGTH_LONG).show();
        }


    }



}
