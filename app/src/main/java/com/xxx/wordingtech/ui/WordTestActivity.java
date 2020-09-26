package com.xxx.wordingtech.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.xxx.wordingtech.R;
import com.xxx.wordingtech.ui.word.WordFragment;

public class WordTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_test);
        FrameLayout frameLayout = findViewById(R.id.fl_container);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_container,new WordFragment(true), "wordTest")
                .commitNow();

    }
}