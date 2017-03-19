package br.edu.ufcg.empsoft.models;

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
    private ArrayList<Insumo> insumos;

    public Fazenda() {
        setName("");
        setDescription("");
        setLocalization("");
        setInsumos(new ArrayList<Insumo>());
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

    public ArrayList<Insumo> getInsumos() {
        if (insumos == null)
            setInsumos(new ArrayList<Insumo>());
        return insumos;
    }

    public void setInsumos(ArrayList<Insumo> insumos) {
        this.insumos = insumos;
    }
}