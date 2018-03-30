package com.example.nayan_makasare.myimagedemo;

import android.content.Context;
import android.support.v17.leanback.widget.BaseCardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by nayan_makasare on 23/3/18.
 */

public class MyCardView extends BaseCardView
{
    public MyCardView(Context context)
    {
        super(context);

        LayoutInflater.from(context).inflate(R.layout.square_image_card_view , this);

        setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean b)
            {
                ImageView mainImage = findViewById(R.id.person_photo);
                if(b)
                {
                    TextView title = findViewById(R.id.titletext);
                    title.setVisibility(VISIBLE);

                    TextView content = findViewById(R.id.contentText);
                    content.setVisibility(VISIBLE);
                }

            }
        });

        //setFocusable(true);

    }
}
