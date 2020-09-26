package com.xxx.wordingtech;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.orhanobut.hawk.Hawk;
import com.xxx.wordingtech.manager.di.AppModule;
import com.xxx.wordingtech.manager.di.component.AppComponent;
import com.xxx.wordingtech.manager.di.component.DaggerAppComponent;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainApplication extends Application {
    private static MainApplication app;
    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
        app = this;
        //初始化全局AppComponent,主要对module里面单利和app处于一个生命周期
        appComponent = DaggerAppComponent.builder().appModule(new AppModule()).build();
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5f3cc191");
    }

    public static MainApplication getApplication() {
        return app;
    }

    public static Context getAppContext() {
        return app.getApplicationContext();
    }

    public void showToast(String s) {
        AndroidSchedulers.mainThread().scheduleDirect(() -> Toast.makeText(MainApplication.getAppContext(), s, Toast.LENGTH_SHORT).show());
    }

}
