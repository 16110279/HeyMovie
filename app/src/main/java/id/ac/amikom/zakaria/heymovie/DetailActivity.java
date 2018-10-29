package id.ac.amikom.zakaria.heymovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static id.ac.amikom.zakaria.heymovie.MainActivity.EXTRA_DATES;
import static id.ac.amikom.zakaria.heymovie.MainActivity.EXTRA_OVERVIEW;
import static id.ac.amikom.zakaria.heymovie.MainActivity.EXTRA_TITLE;
import static id.ac.amikom.zakaria.heymovie.MainActivity.EXTRA_URL;


public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String movieTitle = intent.getStringExtra(EXTRA_TITLE);
        String overview = intent.getStringExtra(EXTRA_OVERVIEW);
        String releaseDate = intent.getStringExtra(EXTRA_DATES);

        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textViewTitle = findViewById(R.id.text_title);
        TextView textViewRelease = findViewById(R.id.text_release);
        TextView textViewOverview = findViewById(R.id.text_overview);

        Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView);
        textViewTitle.setText(movieTitle);
        textViewRelease.setText("Release : " + releaseDate);
        textViewOverview.setText(overview);

    }
}
