package com.project.khopt.fitassistant.util;

import android.util.Log;

import com.project.khopt.fitassistant.model.BlogElement;
import com.project.khopt.fitassistant.model.ExerciseElement;
import com.project.khopt.fitassistant.model.FragmentsModel;
import com.project.khopt.fitassistant.model.NutritionElement;

import java.util.List;

public class FragmentsPresenter implements FragmentsContract.Presenter {

    FragmentsContract.Model mModel;
    FragmentsContract.View mView;

    public FragmentsPresenter(FragmentsContract.View mView){
        this.mView = mView;

    }

    @Override
    public void getDataFromModel() {
        Log.d("Fit", "getDataFromModel INSIDE" + " => " );
        mModel = new FragmentsModel(this);
        mModel.getDataFromRepository();

    }

    @Override
    public void sendDataToFragment(List<Object> data) {
        Log.d("Fit", "sendDataToFragment INSIDE" + " => " + data.size() );
        mView.setDataToFragment(data);
    }

    @Override
    public Class checkFragment() {
        String result = null;
        if(mView == null){
           return null;
        }
        result = mView.getClass().getSimpleName();
       //Log.d("Fit", "name class" + result);

        switch (result){
            case "BlogFragment":
                Log.d("Fit", "Class BlogFragment" );
                return BlogElement.class;
            case "NutritionFragment":
                Log.d("Fit", "Class NutritionFragment" );
                return NutritionElement.class;
            case "ExerciseFragment":
                Log.d("Fit", "Class ExerciseFragment" );
                return ExerciseElement.class;

        }

        return null;
    }
}
