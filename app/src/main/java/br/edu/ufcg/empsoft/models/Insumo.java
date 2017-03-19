package br.edu.ufcg.empsoft.models;

/**
 * Created by stenio on 3/18/2017.
 */

public class Insumo {
    private String nome;
    private int cultivoMedio;
    private int diasCultivados;

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
