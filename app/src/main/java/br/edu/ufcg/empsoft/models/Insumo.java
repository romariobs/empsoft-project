package br.edu.ufcg.empsoft.models;

import java.io.Serializable;

/**
 * Created by stenio on 3/18/2017.
 */

public class Insumo implements Serializable{
    private String nome;
    private int cultivoMedio;
    private int diasCultivados;

    public Insumo() {
        setNome("");
        setCultivoMedio(0);
        setDiasCultivados(0);
    }

    public Insumo(String nome, int cultivoMedio, int diasCultivados) {
        setNome(nome);
        setCultivoMedio(cultivoMedio);
        setDiasCultivados(diasCultivados);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCultivoMedio() {
        return cultivoMedio;
    }

    public void setCultivoMedio(int cultivoMedio) {
        this.cultivoMedio = cultivoMedio;
    }

    public int getDiasCultivados() {
        return diasCultivados;
    }

    public void setDiasCultivados(int diasCultivados) {
        this.diasCultivados = diasCultivados;
    }
}