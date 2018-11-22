package com.mad.trafficclient.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.content.Intent;
import android.widget.ImageView;

import com.mad.trafficclient.R;

public class ImageActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_image);

        initUI();
    }

    private void initUI() {
        MyImageView imageView = findViewById(R.id.img);
        Intent intent = getIntent();
        int img = intent.getIntExtra("img",0);
        imageView.setImageResource(img);
    }
}
