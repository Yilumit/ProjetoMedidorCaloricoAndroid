package br.edu.fateczl.projetomedidorcaloricoandroid.persistence;

import java.sql.SQLException;

public interface IAtividadeCalculadaDao {
    public AtividadeCalculadaDao open() throws SQLException;
    public void close();
}
