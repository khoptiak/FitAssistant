package com.project.khopt.fitassistant.util;

import java.util.List;

public interface FragmentsContract {
    interface View {
        void setDataToFragment(List<Object> data);
        String getViewInfo();
    }

    interface Model{
        interface OnFinishedListener {
            // function to be called
            // once the Handler of Model class
            // completes its execution
            void onFinished(List<Object> data);
        }
        void getDataFromRepository();


    }

    interface Presenter{

        void sendDataToFragment(List<Object> data);
        void getDataFromModel();
        Class checkFragment();


    }
}
