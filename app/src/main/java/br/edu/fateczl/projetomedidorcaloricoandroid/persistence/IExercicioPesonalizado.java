package br.edu.fateczl.projetomedidorcaloricoandroid.persistence;

import java.sql.SQLException;

public interface IExercicioPesonalizado {
    public IExercicioPesonalizado open() throws SQLException;
    public void close();
}