package com.xxx.wordingtech.util;

import android.content.Context;
import androidx.appcompat.app.AlertDialog;

import com.xxx.wordingtech.R;

import javax.inject.Singleton;

@Singleton
public class DialogUtil {
    static DialogUtil dialogUtil;
    public AlertDialog progressDialog;

    private DialogUtil() {

    }

    public void showProgressDialog(Context context) {
        progressDialog = new AlertDialog.Builder(context).setView(R.layout.loading_layout).setCancelable(false).create();
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public static DialogUtil getInstance() {
        if (dialogUtil == null)
            dialogUtil = new DialogUtil();
        return dialogUtil;
    }

}
