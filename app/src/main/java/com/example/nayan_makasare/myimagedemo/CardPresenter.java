package com.example.nayan_makasare.myimagedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.BaseCardView;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nayan_makasare.myimagedemo.Model.Model;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.net.URI;

/**
 * Created by nayan_makasare on 23/3/18.
 */

public class CardPresenter extends Presenter
{

    Context c ;

    public CardPresenter(Context context)
    {
        c = context ;

    }


    class ViewHolder extends Presenter.ViewHolder
    {
        ImageCardView mimageCardView ;
        Target picassoImageTarget ;

        public ViewHolder(View view)
        {
            super(view);
            mimageCardView = (ImageCardView) view;
            picassoImageTarget = new PicassoImageViewTarget(mimageCardView);


        }

        public void updateCardViewImage(URI uri, int width , int height)
        {
            Picasso.with(c)
                    .load(uri.toString())
                    .resize(width , height)
                    .error(c.getResources().getDrawable(R.drawable.logo2))
                    .into(picassoImageTarget);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent)
    {


        ImageCardView mimageCardView = new ImageCardView(parent.getContext());
        mimageCardView.setFocusableInTouchMode(true);
        mimageCardView.setFocusable(true);
        mimageCardView.setBackgroundColor(parent.getContext().getResources().getColor(R.color.fastlane_background));
        mimageCardView.setCardType(BaseCardView.CARD_TYPE_INFO_OVER);
        return new ViewHolder(mimageCardView);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item)
    {
        Model modleobj = (Model) item;
        ((ViewHolder)viewHolder).mimageCardView.setTitleText(modleobj.getTitle());
        ((ViewHolder)viewHolder).mimageCardView.setContentText(modleobj.getContent());
        ((ViewHolder)viewHolder).mimageCardView.setMainImageDimensions(modleobj.getWidth() , modleobj.getHeight());
        ((ViewHolder)viewHolder).updateCardViewImage(modleobj.convertUrlTOUri(), modleobj.getWidth() , modleobj.getHeight());


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
