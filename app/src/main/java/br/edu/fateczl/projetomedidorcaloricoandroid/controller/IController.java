package br.edu.fateczl.projetomedidorcaloricoandroid.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.projetomedidorcaloricoandroid.model.AtividadeCalculada;
import br.edu.fateczl.projetomedidorcaloricoandroid.model.AtividadeFisica;

public interface IController<T> {
    public void inserir(T t) throws SQLException;
    public void modificar(T t) throws SQLException;
    public void excluir(T t) throws SQLException;
    public T buscar(T t) throws SQLException;
    public List<T> listar() throws SQLException;

}
