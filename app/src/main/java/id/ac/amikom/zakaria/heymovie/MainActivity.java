package id.ac.amikom.zakaria.heymovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ExampleAdapter.OnItemClickListener {
public static final String EXTRA_URL = "imageUrl";
public static final String EXTRA_TITLE = "movieTitle";
public static final String EXTRA_OVERVIEW = "overview";
public static final String EXTRA_DATES = "releaseDate";
    public String url;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refresh();
        url = "https://api.themoviedb.org/3/movie/now_playing?api_key=c1e34dfc177b5e14430c509ed62e5567&language=id";
        parseJSON();
    }


    public void refresh(){
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        int id = item.getItemId(); {
            if (id==R.id.langID) {
                refresh();
                url = "https://api.themoviedb.org/3/movie/now_playing?api_key=c1e34dfc177b5e14430c509ed62e5567&language=id";
                parseJSON();
            }

            else if(id==R.id.langEN) {
                refresh();
                url = "https://api.themoviedb.org/3/movie/now_playing?api_key=c1e34dfc177b5e14430c509ed62e5567&language=en-US";
                parseJSON();

            }
            return super.onOptionsItemSelected(item);
        }
    }

    private void parseJSON() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String movieTitle = hit.getString("title");
                                String imageUrl = "https://image.tmdb.org/t/p/w500" + hit.getString("poster_path");
                                String releaseDate = hit.getString("release_date");
                                String overview = hit.getString("overview");

                                mExampleList.add(new ExampleItem(imageUrl, movieTitle, releaseDate, overview));
                            }

                            mExampleAdapter = new ExampleAdapter(MainActivity.this, mExampleList);
                            mRecyclerView.setAdapter(mExampleAdapter);
                            mExampleAdapter.setOnItemClickListener(MainActivity.this);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }
    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        ExampleItem clickedItem = mExampleList.get(position);
        detailIntent.putExtra(EXTRA_URL, clickedItem.getImageUrl());
        detailIntent.putExtra(EXTRA_TITLE, clickedItem.getTitle());
        detailIntent.putExtra(EXTRA_OVERVIEW, clickedItem.getOverview());
        detailIntent.putExtra(EXTRA_DATES, clickedItem.getReleaseDate());

        startActivity(detailIntent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }




}