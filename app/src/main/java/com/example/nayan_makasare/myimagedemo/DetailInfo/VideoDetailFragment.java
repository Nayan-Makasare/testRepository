package com.example.nayan_makasare.myimagedemo.DetailInfo;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v17.leanback.app.DetailsFragment;
import android.support.v17.leanback.widget.Action;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ClassPresenterSelector;
import android.support.v17.leanback.widget.DetailsOverviewRow;
import android.support.v17.leanback.widget.FullWidthDetailsOverviewRowPresenter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.SparseArrayObjectAdapter;
import android.util.Log;

import com.example.nayan_makasare.myimagedemo.BackGroundManager.PicasoBackgroundManager;
import com.example.nayan_makasare.myimagedemo.CardPresenter;
import com.example.nayan_makasare.myimagedemo.Model.Example;
import com.example.nayan_makasare.myimagedemo.Model.Model;
import com.example.nayan_makasare.myimagedemo.Presenter.DetailedDescriptionPresenter;
import com.example.nayan_makasare.myimagedemo.R;
import com.example.nayan_makasare.myimagedemo.Sever.ApiClient;
import com.example.nayan_makasare.myimagedemo.Sever.ApiInterface;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nayan_makasare on 26/3/18.
 */

public class VideoDetailFragment extends DetailsFragment
{

    private FullWidthDetailsOverviewRowPresenter fullWidthDetailsOverviewRowPresenter ;
    private PicasoBackgroundManager picasoBackgroundManager ;
    private Model modelObject ;
    private ApiInterface apiInterface;
    private DetailDescriptionTask detailDescriptionTask ;

    private  Response<Example> mresponse ;

    @Override
   public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        fullWidthDetailsOverviewRowPresenter = new FullWidthDetailsOverviewRowPresenter(new DetailedDescriptionPresenter());
        modelObject = (Model) getActivity().getIntent().getSerializableExtra("serialObj");
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Example> call = apiInterface.getExample();
        call.enqueue(new Callback<Example>()
        {
            @Override
            public void onResponse(Call<Example> call, final Response<Example> response)
            {
                mresponse = response ;

                MyTaskParams params = new MyTaskParams(modelObject , response);


                detailDescriptionTask = (DetailDescriptionTask) new DetailDescriptionTask().execute(params);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }

        });

        picasoBackgroundManager = new PicasoBackgroundManager(getActivity());
    }

    @Override
    public void onStop() {
        detailDescriptionTask.cancel(true);
        super.onStop();
    }



    class DetailDescriptionTask extends AsyncTask< MyTaskParams , Integer , DetailsOverviewRow>
    {


        @Override
        protected DetailsOverviewRow doInBackground(MyTaskParams... myTaskParams)
        {
            DetailsOverviewRow row = new DetailsOverviewRow(myTaskParams[0].ObjModel);

            try {
                Bitmap Poster = Picasso.with(getActivity())
                        .load(myTaskParams[0].ObjModel.getImageurl())
                        .centerCrop()
                        .resize(600 , 600)
                        .get();
                row.setImageBitmap(getActivity() , Poster);
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            return row;

        }

        @Override
        protected void onPostExecute(DetailsOverviewRow row)
        {
            super.onPostExecute(row);

            SparseArrayObjectAdapter sparseArrayObjectAdapter = new SparseArrayObjectAdapter();
            for (int i = 0; i<10; i++){
                sparseArrayObjectAdapter.set(i, new Action(i, "label1", "label2"));
            }
            row.setActionsAdapter(sparseArrayObjectAdapter);

            ArrayObjectAdapter listrowAdapter = new ArrayObjectAdapter(new CardPresenter(getActivity()));
            HeaderItem headerItem = new HeaderItem(0, "Related Videos");

            for (int j = 0; j < mresponse.body().getProperties().get(1).getImages().size(); j++)
            {

                Model obj = new Model();
                obj.setWidth(Integer.parseInt(mresponse.body().getProperties().get(1).getWidth()));
                obj.setHeight(Integer.parseInt(mresponse.body().getProperties().get(1).getHeight()));
                obj.setImageurl(mresponse.body().getImagebaseUrl()+mresponse.body().getProperties().get(1).getImages().get(j));
                obj.setTitle("Title" + j);
                obj.setContent("Content" + j);
                listrowAdapter.add(obj);
            }

            ClassPresenterSelector classPresenterSelector = new ClassPresenterSelector();

            fullWidthDetailsOverviewRowPresenter.setInitialState(FullWidthDetailsOverviewRowPresenter.STATE_SMALL);

            classPresenterSelector.addClassPresenter(DetailsOverviewRow.class , fullWidthDetailsOverviewRowPresenter);
            classPresenterSelector.addClassPresenter(ListRow.class , new ListRowPresenter());


            ArrayObjectAdapter adapter = new ArrayObjectAdapter(classPresenterSelector);

            adapter.add(row);
            adapter.add(new ListRow(headerItem , listrowAdapter));

            setAdapter(adapter);

        }
    }






    private static class MyTaskParams
    {
        Model ObjModel ;
        Response<Example> response ;

        MyTaskParams( Model ObjModel ,Response<Example> response )
        {
            this.ObjModel = ObjModel ;
            this.response = response ;
        }
    }
}
