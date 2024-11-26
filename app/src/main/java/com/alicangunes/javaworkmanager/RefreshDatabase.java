package com.alicangunes.javaworkmanager;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class RefreshDatabase extends Worker {
    Context mycontext;
    public RefreshDatabase(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.mycontext = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        Data data = getInputData();
        int myNumber = data.getInt("intKey", 0);
        refteshDatabase(myNumber);
        return Result.success();
    }

    private void refteshDatabase(int myNumber) {
        SharedPreferences sharedPreferences = mycontext.getSharedPreferences("com.alicangunes.javaworkmanager", Context.MODE_PRIVATE);
        int mySavedNumber = sharedPreferences.getInt("myNumber", 0);
        mySavedNumber += myNumber;
        System.out.println(mySavedNumber);
        sharedPreferences.edit().putInt("myNumber", mySavedNumber).apply();
    }
}
