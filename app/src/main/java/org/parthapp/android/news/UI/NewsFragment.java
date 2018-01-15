package org.parthapp.android.news.UI;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.parthapp.android.news.Adapter.MyAdapter;
import org.parthapp.android.news.Helper.PaginationListener;
import org.parthapp.android.news.Network.Service;
import org.parthapp.android.news.Network.Client;
import org.parthapp.android.news.Pojo.DataList;
import org.parthapp.android.news.Pojo.Result;

import com.example.android.news.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by parth on 12/30/2017.
 */
public class NewsFragment extends android.support.v4.app.Fragment {
    private static final int PAGE_START = 1;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    LinearLayoutManager layoutManager;
    List<DataList> dataListList;
    String from = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    Service service;
    ProgressBar progressbar;
    String orderBy;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 5;
    private int currentPage = PAGE_START;

    public NewsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.news_fragment, container, false);
        progressbar = v.findViewById(R.id.progressBar);

        final String query = getArguments().getString("key1");
        dataListList = new ArrayList<>();
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        if (myAdapter == null) {
            myAdapter = new MyAdapter(this, dataListList);
            recyclerView.setAdapter(myAdapter);
            myAdapter.addAll(dataListList);
            myAdapter.notifyDataSetChanged();
        } else {
            myAdapter.addAll(dataListList);
            myAdapter.notifyItemInserted(dataListList.size() - 1);
            Log.e("TOTAL SIZE", String.valueOf(dataListList.size()));
        }

        recyclerView.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {

                progressbar.setVisibility(View.VISIBLE);
                isLoading = true;
                currentPage += 1;
                Log.e("CURRENTPAGE", String.valueOf(currentPage));
                // mocking network delay for API call
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPage(query, from, currentPage,orderBy);
                        isLoading=false;
                        progressbar.setVisibility(View.GONE);
                    }
                }, 1000);

            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        orderBy = sharedPrefs.getString(
                getString(R.string.settings_key),
                getString(R.string.settings_default)
        );

        service = Client.getClient().create(Service.class);
        loadFirstPage(query, from,orderBy);

        return v;
    }

    private void loadFirstPage(String query, String from, String orderBy) {

        service.getNews(query, from, currentPage,orderBy).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Response<Result> response, Retrofit retrofit) {

                try {
                    for (int i = 0; i < response.body().getArticles().size(); i++) {
                        String title = response.body().getArticles().get(i).getTitle();
                       // Log.e("Title", title.toString());
                        String urlToImage = response.body().getArticles().get(i).getUrlToImage();
                        String url = response.body().getArticles().get(i).getUrl();
                        dataListList.add(new DataList(urlToImage, title, url));
                    }
                    myAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("###",t.toString());
            }
        });
    }

    private void loadNextPage(String query, String from, int currentPage, String orderBy) {

        service.getNews(query, from, currentPage,orderBy).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Response<Result> response, Retrofit retrofit) {

                try {
                    for (int i = 0; i < response.body().getArticles().size(); i++) {
                        String title = response.body().getArticles().get(i).getTitle();
                       // Log.e("Title", title.toString());
                        String urlToImage = response.body().getArticles().get(i).getUrlToImage();
                        String url = response.body().getArticles().get(i).getUrl();
                        dataListList.add(new DataList(urlToImage, title, url));
                    }
                    myAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }
}
