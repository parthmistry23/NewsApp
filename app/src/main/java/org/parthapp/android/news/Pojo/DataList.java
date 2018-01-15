package org.parthapp.android.news.Pojo;

/**
 * Created by parth on 12/31/2017.
 */
public class DataList {
    String urlToImage;
    private String title;
    String url;

    public DataList(String urlToImage, String title,String url) {
        this.urlToImage = urlToImage;
        this.title = title;
        this.url=url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
