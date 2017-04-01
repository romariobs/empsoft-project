package br.edu.ufcg.empsoft.models;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by romario
 */

public class Fazenda implements Serializable {
    private String id;
    private String name;
    private String description;
    private String localization;
    private int thumb;
    private ArrayList<Insumo> insumos;
    private ArrayList<PhotoItem> photoItems;
    private PhotoItem mainImage;

    public Fazenda() {
        setName("");
        setDescription("");
        setLocalization("");
        setInsumos(new ArrayList<Insumo>());
        setPhotoItems(new ArrayList<PhotoItem>());
        setMainImage(new PhotoItem(PhotoItem.DEFAULT_IMAGE));
    }

    public Fazenda(String name, String description) {
        this();
        setName(name);
        setDescription(description);
    }

    public void addInsumo(Insumo insumo) {
        insumos.add(insumo);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }

    public ArrayList<Insumo> getInsumos() {
        if (insumos == null)
            setInsumos(new ArrayList<Insumo>());
        return insumos;
    }

    public Insumo getInsumo(String nomeInsumo) {
        for (Insumo insumo: insumos)
            if (insumo.getNome().equals(nomeInsumo))
                return insumo;
        return null;
    }

    public void update(Insumo insumo) {
        Log.wtf("Fazenda.update insumo.isColhaParaMim", "" + insumo.isColhaParaMim());
        for (int i = 0; i < insumos.size(); i++) {
            Log.wtf("Fazenda.update i == insumo", insumos.get(i).getNome() + " " + insumo.getNome());
            if (insumos.get(i).equals(insumo)) {
                Log.wtf("Fazenda.update", "" + i);
                insumos.set(i, insumo);
                break;
            }
        }
    }

    public void setInsumos(ArrayList<Insumo> insumos) {
        this.insumos = insumos;
    }

    public ArrayList<PhotoItem> getPhotoItems() {
        return photoItems;
    }

    public void setPhotoItems(ArrayList<PhotoItem> photoItems) {
        if (photoItems == null || photoItems.isEmpty()) {
            photoItems = new ArrayList<>();
            photoItems.add(new PhotoItem("http://i.imgur.com/DvpvklR.png"));
            photoItems.add(new PhotoItem("http://i.imgur.com/Qmnkvit.jpg"));
            photoItems.add(new PhotoItem("http://i.imgur.com/KHt3tVJ.jpg"));
        }
        this.photoItems = photoItems;
    }

    public PhotoItem getMainImage() {
        return mainImage;
    }

    public void setMainImage(PhotoItem mainImage) {
        this.mainImage = mainImage;
    }
}