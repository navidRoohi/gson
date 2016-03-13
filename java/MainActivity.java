package com.example.navidroohibroojeni.g;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {


    ListView listview;
    Response responseObj;
    CustomAdapter adapter;
    String url = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?apikey=9htuhtcb4ymusd73d4z6jxcj";
    Gson gson;

    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.movielist);
        client = new AsyncHttpClient();


        client.get(MainActivity.this, url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String responsestr = new String(responseBody);

                gson = new Gson();
                responseObj = gson.fromJson(responsestr,Response.class);

                adapter = new CustomAdapter(MainActivity.this, responseObj.getMovies());
                listview.setAdapter(adapter);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }


}



