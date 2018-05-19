package com.zzhoujay.htmldemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CodeViewActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_view);

        mTextView = findViewById(R.id.textView);

        Intent intent = getIntent();
        Uri data = intent.getData();
        System.out.println(data);
    }
}
