package com.example.nayan_makasare.myimagedemo;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.app.DetailsFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.util.Log;
import android.widget.Toast;

import com.example.nayan_makasare.myimagedemo.BackGroundManager.PicasoBackgroundManager;
import com.example.nayan_makasare.myimagedemo.BackGroundManager.SimpleBackgroundManager;
import com.example.nayan_makasare.myimagedemo.DetailInfo.DetailsActivity;
import com.example.nayan_makasare.myimagedemo.Model.AllAppModel;
import com.example.nayan_makasare.myimagedemo.Model.Example;
import com.example.nayan_makasare.myimagedemo.Model.Example2;
import com.example.nayan_makasare.myimagedemo.Model.Model;
import com.example.nayan_makasare.myimagedemo.Model.TempModel;
import com.example.nayan_makasare.myimagedemo.Presenter.AllAppPresenter;
import com.example.nayan_makasare.myimagedemo.Sever.ApiClient;
import com.example.nayan_makasare.myimagedemo.Sever.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nayan_makasare on 23/3/18.
 */

public class MainFragment extends BrowseFragment
{

    private ApiInterface apiInterface;
    ArrayObjectAdapter mRowsAdapter;
    //private static SimpleBackgroundManager simpleBackgroundManager = null;
    private static PicasoBackgroundManager picasoBackgroundManager = null;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        setUIElements();



        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Example> call = apiInterface.getExample();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, final Response<Example> response)
            {

                loadRows(response);

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }

        });



      // simpleBackgroundManager = new SimpleBackgroundManager(getActivity());
       picasoBackgroundManager = new PicasoBackgroundManager(getActivity());
        itemSelected();




    }


    public void setUIElements()
    {
        //setBadgeDrawable(getActivity().getResources().getDrawable(R.drawable.logo2));
        setTitle("Hello Nayan!!"); // Badge, when set, takes precedent
        // over title
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);
        // set fastLane (or headers) background color
        setBrandColor(getResources().getColor(R.color.fastlane_background));
        // set search icon color
        setSearchAffordanceColor(getResources().getColor(R.color.search_opaque));
    }


    private void loadRows(Response<Example> response) {

        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());

        /*cardItem Presenter*/



        for (int i = 0; i < response.body().getProperties().size(); i++)
        {

            if(i<3)
            {
                HeaderItem cardItemPresenterHeader = new HeaderItem(i, response.body().getProperties().get(i).getDimension());
                CardPresenter mCardViewPresenter = new CardPresenter(getActivity());
                ArrayObjectAdapter cardRowAdapter = new ArrayObjectAdapter(mCardViewPresenter);


                for (int j = 0; j < response.body().getProperties().get(i).getImages().size(); j++)
                {

                    Model obj = new Model();
                    obj.setWidth(Integer.parseInt(response.body().getProperties().get(i).getWidth()));
                    obj.setHeight(Integer.parseInt(response.body().getProperties().get(i).getHeight()));
                    obj.setImageurl(response.body().getImagebaseUrl()+response.body().getProperties().get(i).getImages().get(j));
                    obj.setTitle("Title" + j);
                    obj.setContent("Content" + j);
                    cardRowAdapter.add(obj);

                }
                mRowsAdapter.add(new ListRow(cardItemPresenterHeader, cardRowAdapter));

            }
            if(i==3)
            {
                HeaderItem cardItemPresenterHeader = new HeaderItem(i, response.body().getProperties().get(i).getDimension());
                CardPresenter mCardViewPresenter = new CardPresenter(getActivity());
                ArrayObjectAdapter cardRowAdapter = new ArrayObjectAdapter(mCardViewPresenter);
                for(int k = 0 ; k< response.body().getProperties().get(i).getMash().size() ; k++)
                {
                    Model obj = new Model();
                    obj.setWidth(Integer.parseInt(response.body().getProperties().get(i).getMash().get(k).getWidth()));
                    obj.setHeight(Integer.parseInt(response.body().getProperties().get(i).getMash().get(k).getHeight()));
                    obj.setImageurl(response.body().getImagebaseUrl()+response.body().getProperties().get(i).getMash().get(k).getLink());
                    obj.setTitle("Title" + k);
                    obj.setContent("Content" + k);
                    cardRowAdapter.add(obj);

                }
                mRowsAdapter.add(new ListRow(cardItemPresenterHeader, cardRowAdapter));
            }
        }


        //AllAppRow

        HeaderItem headerItem = new HeaderItem(4,"All Application");
        AllAppPresenter appPresenter = new AllAppPresenter(getActivity());

        ArrayObjectAdapter mAllAdatper = new ArrayObjectAdapter(appPresenter);
        List<TempModel> list = getApp();

        for(int l = 0 ; l<list.size()  ; l++)
        {
            TempModel tobj = list.get(l);
             mAllAdatper.add(tobj);
        }
        mRowsAdapter.add(new ListRow(headerItem, mAllAdatper));

        /* set */
        setAdapter(mRowsAdapter);
    }


    public void itemSelected()
    {
        setOnItemViewSelectedListener(new OnItemViewSelectedListener()
        {
            @Override
            public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {

                if (item instanceof Model)
                {
                    //simpleBackgroundManager.updateBackgroundImage(getActivity().getResources().getDrawable(R.drawable.back));

                    picasoBackgroundManager.uploadImage(((Model) item).getImageurl());

                }



            }
        });




        setOnItemViewClickedListener(new OnItemViewClickedListener()
        {
            @Override
            public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row)
            {

                if(item instanceof TempModel)
                {
                    String packname = ((TempModel) item).getPackname();

                    Intent launchIntent = getActivity().getPackageManager().getLaunchIntentForPackage(packname);
                    if (launchIntent != null) {
                        startActivity(launchIntent);//null pointer check in case package name was not found
                    }

                }


                if (item instanceof Model)
                {
                    Model movie = (Model) item;
                   // Log.d(TAG, "Item: " + item.toString());


                    movie.setDescription("The only difference between a normal variable and a final variable is that we can re-assign value to a normal variable but we cannot change the value of a final variable once assigned. Hence " +
                            "final variables must be used only for the values that we want to remain constant throughout the execution of program.");
                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
                    intent.putExtra("serialObj" , movie);

                    getActivity().startActivity(intent);
                }

            }
        });
    }





    public List<TempModel> getApp()
    {


        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager pm = getActivity().getPackageManager();


        List<ResolveInfo> pkgAppsList = pm.queryIntentActivities( mainIntent, 0);

        List<TempModel> temp = new ArrayList<>();

        for(int i = 0 ; i<pkgAppsList.size() ; i++)
        {

            TempModel tobj = new TempModel();
            tobj.setAppName((String) pkgAppsList.get(i).loadLabel(pm));
            tobj.setIcon(pkgAppsList.get(i).loadIcon(pm));
            tobj.setPackname(pkgAppsList.get(i).activityInfo.packageName.toString());
            temp.add(tobj);
        }
        return temp;

    }


}