package com.project.khopt.fitassistant.source;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.project.khopt.fitassistant.util.FragmentsContract;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class RemoteDataSource {

    private FirebaseFirestore mFirebaseFirestore;
    static String dataBaseTableName;


    public void getFirebaseData(final FragmentsContract.Model.OnFinishedListener onFinishedListener,
                                final Class elementClass ) {


        mFirebaseFirestore = FirebaseFirestore.getInstance();
        final List<Object> blogElementList = new ArrayList<>();

        mFirebaseFirestore.collection(dataBaseTableName).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Object blogElement = document.toObject(elementClass);
                                blogElementList.add(blogElement);
                                //Log.d("Fit", document.getId() + " => " + document.getData());
                                if (blogElementList.size() == task.getResult().size()) {
                                //    Log.d("Fit", "sport list INSIDE" + " => " + blogElementList.size());
                                    onFinishedListener.onFinished(blogElementList);
                                }
                            }

                        } else {
                            Log.d("Fit", "Error getting documents: ", task.getException());
                        }
                    }

                });
    }

    public static void initializeTableName(Class elementeClass){
        switch (elementeClass.getSimpleName()){
            case "BlogElement":
                dataBaseTableName = "blog";
                break;
            case "ExerciseElement":
                dataBaseTableName = "video";
                break;
            case "NutritionElement":
                dataBaseTableName = "nutrition";
                break;
        }
    }
}


