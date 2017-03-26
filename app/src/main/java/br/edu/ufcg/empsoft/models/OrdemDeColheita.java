package br.edu.ufcg.empsoft.models;

/**
 * Created by JoseLucas on 25/03/2017.
 */

public class OrdemDeColheita {
    private String id;
    private String insumo;

    public OrdemDeColheita(){
        this.insumo = "";
    }

    public OrdemDeColheita(String insumo) {
        this.insumo = insumo;
    }

    public String getInsumo() {
        return insumo;
    }

    public void setInsumo(String insumo) {
        this.insumo = insumo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId(){
        return id;
    }
}
