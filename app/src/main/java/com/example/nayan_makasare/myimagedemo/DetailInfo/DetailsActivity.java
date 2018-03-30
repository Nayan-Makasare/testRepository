package com.example.nayan_makasare.myimagedemo.DetailInfo;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nayan_makasare.myimagedemo.MainActivity;
import com.example.nayan_makasare.myimagedemo.R;

public class DetailsActivity extends FragmentActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        Uri data = intent.getData();

        String host = data.getHost();
        String sheme = data.getScheme();


        if(host == "nayanmakasare" && sheme == "http"  )
        {
            Intent main = new Intent(DetailsActivity.this, MainActivity.class);
            startActivity(main);
        }
    }



}