package br.edu.ufcg.empsoft.models;

import java.io.Serializable;

/**
 * Created by stenio on 3/18/2017.
 */

public class Insumo implements Serializable{
    private String nome;
    private int cultivoMedio;
    private int diasCultivados;
    private Agendamento proximaColheita;
    private boolean colhaParaMim;

    public Insumo() {
        setNome("");
        setCultivoMedio(0);
        setDiasCultivados(0);
        setProximaColheita(null);
        setColhaParaMim(false);
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

    // Usei timestamp para facilitar o armazenamento no firebase
    /**
     * O timestamp para a proxima colheita. Se for 0, significa que nao ah colheita marcada.
     * @param proximaColheita
     */
    public void setProximaColheita(Agendamento proximaColheita) {
        this.proximaColheita = proximaColheita;
    }

    public Agendamento getProximaColheita() {
        return this.proximaColheita;
    }

    public boolean isColhaParaMim() {
        return colhaParaMim;
    }

    public void setColhaParaMim(boolean colhaParaMim) {
        this.colhaParaMim = colhaParaMim;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Insumo) {
            Insumo other = (Insumo) o;

            return other.getNome().equals(this.getNome());
        }
        return false;
    }
}