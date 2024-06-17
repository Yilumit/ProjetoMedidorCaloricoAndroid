package br.edu.fateczl.projetomedidorcaloricoandroid.model;

import androidx.annotation.NonNull;

public class ExercicioPersonalizado extends AtividadeFisica {

    public ExercicioPersonalizado() {
        super();
    }

    @NonNull
    @Override
    public String toString() {
        return codigo + "-" + nome + " - " + descricao;
    }
}
