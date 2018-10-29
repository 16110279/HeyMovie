package id.ac.amikom.zakaria.heymovie;

public class ExampleItem {
    private String mImageUrl;
    private String mTitle;
    private String mRelease;
    private String mOverview;



    public ExampleItem(String imageUrl, String title, String releaseDate, String overview) {
        mImageUrl = imageUrl;
        mTitle = title;
        mRelease = releaseDate;
        mOverview = overview;

    }

    public String getImageUrl() {
        return mImageUrl;
    }
    public String getTitle() {
        return mTitle;
    }
    public String getReleaseDate() {
        return mRelease;
    }
    public String getOverview() {
        return mOverview;
    }



}