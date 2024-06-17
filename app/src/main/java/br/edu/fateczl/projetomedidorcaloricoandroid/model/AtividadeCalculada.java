package br.edu.fateczl.projetomedidorcaloricoandroid.model;

import androidx.annotation.NonNull;

public class AtividadeCalculada extends AtividadeFisica {


    public AtividadeCalculada(String nome, int codigo, float met, String descricao, String tipo) {
        super(nome, codigo, met, descricao, tipo);
    }

    public AtividadeCalculada() {
        super();
    }

    @NonNull
    @Override
    public String toString() {
        return codigo + "-" + nome + " - " + descricao;
    }
}
