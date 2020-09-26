package com.xxx.wordingtech.util;

import android.app.Activity;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class PermissionUtil {
    //检查权限,
    //有就执行onNext,没有就去获取
    //获取失败执行传入的操作
    public static Observable<Permission> rxCheckPermission(
            Activity activity,
            final Action action,
            String... permission) {

        return new RxPermissions(activity).requestEach(permission)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) action.run();
                    }
                });
    }
}
