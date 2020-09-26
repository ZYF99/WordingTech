package com.xxx.wordingtech.util;

import android.content.res.ColorStateList;
import android.net.Uri;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.xxx.wordingtech.MainApplication;
import com.xxx.wordingtech.R;

public class ImageUtil {
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, Uri uri) {
        Glide.with(imageView.getContext()).load(uri).into(imageView);
    }

    @BindingAdapter("gender")
    public static void getGenderDrawable(ImageView imageView, String gender) {
        if (gender != null) {
            if (gender.equals("F")) {
                Glide.with(imageView.getContext()).load(R.drawable.icon_genderfemale).into(imageView);
                imageView.setImageTintList(
                        ColorStateList.valueOf(
                                ContextCompat.getColor(
                                        MainApplication.getAppContext(),
                                        R.color.colorFemale
                                )
                        )
                );
            } else {
                Glide.with(imageView.getContext()).load(R.drawable.icon_gendermale).into(imageView);
                imageView.setImageTintList(
                        ColorStateList.valueOf(
                                ContextCompat.getColor(
                                        MainApplication.getAppContext(),
                                        R.color.colorMale
                                )
                        )
                );
            }
        }
    }
}
