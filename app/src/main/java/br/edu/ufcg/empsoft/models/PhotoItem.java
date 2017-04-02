package br.edu.ufcg.empsoft.models;

/**
 * Created by romariobs on 29/03/17.
 */

public class PhotoItem {

    private String mUrl;
    // IMAGEM DE FAZENDA REAL
    //public static String DEFAULT_IMAGE = "http://i.imgur.com/KHt3tVJ.jpg";
    // FAZENDA DESENHO
    public static String DEFAULT_IMAGE = "http://imgur.com/rszNFbw";

    public PhotoItem() {
        setUrl(DEFAULT_IMAGE);
    }

    public PhotoItem(String url) {
        mUrl = url;
    }

    public String getUrl() { return mUrl; }

    public void setUrl(String url) {
        mUrl = url;
    }

    @Override
    public String toString() {
        return mUrl;
    }
}
