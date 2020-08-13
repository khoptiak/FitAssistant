package com.project.khopt.fitassistant;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;

import androidx.annotation.NonNull;

public class FirebaseConfiguration {

    private FirebaseAuth mAuth;
    private  boolean resultOFAuth;


    FirebaseConfiguration(){
        mAuth = FirebaseAuth.getInstance();
    }

    public boolean authentification(String email, String password){


        if(email.isEmpty())    return resultOFAuth;
        if(email.equals("") || password.equals("")) return resultOFAuth;

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d("Fit", "Authorized :" + task.isSuccessful());
                    resultOFAuth = true;
                }
                if (!task.isSuccessful()){
                    resultOFAuth = false;
                    Log.d("Fit", "ERROR authorization :" + task.getException() );
                }
            }
        });
        return resultOFAuth;
    }

    public boolean registration(final String email, String password){
        if(email.isEmpty())    return resultOFAuth;
        if(email.equals("") || password.equals("")) return resultOFAuth;

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                 if(task.isSuccessful()){
                     Log.d("Fit", "New user registered: " + email + task.isSuccessful());
                     resultOFAuth = true;
                 }
                if (!task.isSuccessful()){
                    resultOFAuth = false;
                    Log.d("Fit", "ERROR registration :" + task.getException() );
                }
            }
        });
        return resultOFAuth;
    }
}
