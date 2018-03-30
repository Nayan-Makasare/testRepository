package com.example.nayan_makasare.myimagedemo.Presenter;

import android.support.v17.leanback.widget.AbstractDetailsDescriptionPresenter;

import com.example.nayan_makasare.myimagedemo.Model.Model;

/**
 * Created by nayan_makasare on 29/3/18.
 */

public class DetailedDescriptionPresenter extends AbstractDetailsDescriptionPresenter
{

    /**
     * Binds the data from the item to the ViewHolder.  The item is typically associated with
     * a {@link DetailsOverviewRow} or {@link PlaybackControlsRow}.
     *
     * @param vh   The ViewHolder for this details description view.
     * @param item The item being presented.
     */
    @Override
    protected void onBindDescription(ViewHolder vh, Object item)
    {

        Model obj = (Model)item ;

        if (obj != null)
        {
            vh.getTitle().setText(obj.getTitle());
            vh.getSubtitle().setText(obj.getContent());
            vh.getBody().setText(obj.getDescription());
        }


    }
}
