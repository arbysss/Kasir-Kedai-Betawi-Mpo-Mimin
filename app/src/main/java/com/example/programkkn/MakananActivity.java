package com.example.programkkn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.programkkn.activity.MainActivity;

public class MakananActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makanan);
    }

    public void kembali(View view) {
        startActivity(new Intent(MakananActivity.this,MainActivity.class));
    }
}
