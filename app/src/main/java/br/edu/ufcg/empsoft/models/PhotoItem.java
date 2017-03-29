package br.edu.ufcg.empsoft.models;

/**
 * Created by romariobs on 29/03/17.
 */

public class PhotoItem {

    private String mUrl;

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
