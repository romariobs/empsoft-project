package br.edu.ufcg.empsoft.models;

import java.io.Serializable;

/**
 * Created by romario
 */

public class Fazenda implements Serializable {
    private String name;
    private String description;
    private String localization;

    public Fazenda() {
        setName("");
        setDescription("");
        setLocalization("");
    }

    public Fazenda(String name, String description) {
        setName(name);
        setDescription(description);
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
}
