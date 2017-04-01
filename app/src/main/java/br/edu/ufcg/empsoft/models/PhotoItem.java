package br.edu.ufcg.empsoft.models;

/**
 * Created by romariobs on 29/03/17.
 */

public class PhotoItem {

    private String mUrl;
    public static String DEFAULT_IMAGE = "http://i.imgur.com/KHt3tVJ.jpg";

    public PhotoItem() {
        setUrl(DEFAULT_IMAGE);
    }

    public PhotoItem(String url) {
        mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
