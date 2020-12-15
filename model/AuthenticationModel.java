package com.project.khopt.fitassistant.model;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.project.khopt.fitassistant.util.AuthenticationContract;

import androidx.annotation.NonNull;

public class AuthenticationModel implements AuthenticationContract.Model {

    private FirebaseAuth mAuth;

    public  AuthenticationModel(){
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void signInExistedUser(final AuthenticationContract.Model.OnFinishedListener onFinishedListener, User userModel) {

        mAuth.signInWithEmailAndPassword(userModel.getUserEmail(), userModel.getUserPassword())
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Log.d("Fit", "Authorized :" + task.isSuccessful() + mUser.toString());
                            onFinishedListener.onFinished();

                        }

                    }
                });


    }

    @Override
    public void createNewUser(final OnFinishedListener onFinishedListener, User userModel) {

        mAuth.createUserWithEmailAndPassword(userModel.getUserEmail(), userModel.getUserPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            onFinishedListener.onFinished();
                        }
                    }
                });
    }

    //return true if user is authorized, false - if not
    @Override
    public boolean userAuthentificationState() {

        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            return true;
        }
        return false;
    }

    public  void signOut(){
        FirebaseAuth.getInstance().signOut();
    }
}
