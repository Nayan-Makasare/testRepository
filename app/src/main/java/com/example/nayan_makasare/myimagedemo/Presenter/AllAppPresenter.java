package com.example.nayan_makasare.myimagedemo.Presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.view.View;
import android.view.ViewGroup;

import com.example.nayan_makasare.myimagedemo.BackGroundManager.PicasoBackgroundManager;
import com.example.nayan_makasare.myimagedemo.Model.AllAppModel;
import com.example.nayan_makasare.myimagedemo.Model.TempModel;
import com.example.nayan_makasare.myimagedemo.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.net.URI;

/**
 * Created by nayan_makasare on 27/3/18.
 */

public class AllAppPresenter extends Presenter
{
    Context c ;

    public AllAppPresenter(Context c)
    {
        this.c = c ;
    }


    class ViewHolder extends Presenter.ViewHolder
    {
        ImageCardView mimageCardView;
        Target piccasoTarget ;


        public ViewHolder(View view)
        {
            super(view);
            mimageCardView = (ImageCardView)view;
            piccasoTarget = new PicassoImageViewTarget(mimageCardView);
        }

        public void updateAppImage(URI uri)
        {
            Picasso.with(c)
                    .load(uri.toString())
                    .resize(100 ,100)
                    .centerCrop()
                    .into(piccasoTarget);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent)
    {
        ImageCardView mimageCardView = new ImageCardView(parent.getContext());
        mimageCardView.setFocusable(true);
        mimageCardView.setFocusableInTouchMode(true);
        mimageCardView.setBackgroundColor(parent.getContext().getResources().getColor(R.color.card_background));
        mimageCardView.setMainImageDimensions(200,200);
        return new ViewHolder(mimageCardView);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item)
    {
        TempModel Allobj = (TempModel) item;
        ((ViewHolder)viewHolder).mimageCardView.setMainImage(Allobj.getIcon());
        ((ViewHolder)viewHolder).mimageCardView.setTitleText(Allobj.getAppName());

    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {

    }


    public class PicassoImageViewTarget implements Target
    {
        ImageCardView mimageCardView ;

        public PicassoImageViewTarget(ImageCardView mimageCardView)
        {
            this.mimageCardView = mimageCardView;
        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from)
        {
            Drawable bitmapDrawable = new BitmapDrawable(c.getResources() , bitmap);
            mimageCardView.setMainImage(bitmapDrawable);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
            mimageCardView.setMainImage(errorDrawable);
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    }


}
