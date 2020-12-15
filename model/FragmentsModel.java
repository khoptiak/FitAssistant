package com.project.khopt.fitassistant.model;

import android.util.Log;

import com.project.khopt.fitassistant.source.RemoteDataSource;
import com.project.khopt.fitassistant.util.FragmentsContract;

import java.util.List;

public class FragmentsModel implements FragmentsContract.Model, FragmentsContract.Model.OnFinishedListener {


    RemoteDataSource mRemoteDataSource ;
    FragmentsContract.Presenter mPresenter;

    public FragmentsModel(FragmentsContract.Presenter presenter){

        this.mPresenter = presenter;

    }
    @Override
    public void getDataFromRepository() {
        Class className = mPresenter.checkFragment();
        initialiseDataSource(className);
        mRemoteDataSource.getFirebaseData(this, className);

    }


    @Override
    public void onFinished(List<Object> data) {
        List<Object> dataFirebase = data;
        Log.d("Fit", "OnFinished INSIDE" + " => " + data.size());
        mPresenter.sendDataToFragment(data);


    }

    private void initialiseDataSource(Class elementeClass){
        RemoteDataSource.initializeTableName(elementeClass);
        mRemoteDataSource = new RemoteDataSource();

    }

}