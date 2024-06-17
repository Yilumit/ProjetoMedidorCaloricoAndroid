package br.edu.fateczl.projetomedidorcaloricoandroid.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.projetomedidorcaloricoandroid.model.AtividadeCalculada;
import br.edu.fateczl.projetomedidorcaloricoandroid.persistence.AtividadeCalculadaDao;

public class AtividadeCalculadaController implements IController<AtividadeCalculada> {

    private final AtividadeCalculadaDao cDao;

    public AtividadeCalculadaController(AtividadeCalculadaDao cDao) {
        this.cDao = cDao;
    }


    @Override
    public void inserir(AtividadeCalculada atividadeCalculada) throws SQLException {
        if (cDao.open() == null){
            cDao.open();
        }
        cDao.insert(atividadeCalculada);
        cDao.close();
    }

    @Override
    public void modificar(AtividadeCalculada atividadeCalculada) throws SQLException {
        if (cDao.open() == null){
            cDao.open();
        }
        cDao.update(atividadeCalculada);
        cDao.close();
    }

    @Override
    public void excluir(AtividadeCalculada atividadeCalculada) throws SQLException {
        if (cDao.open() == null){
            cDao.open();
        }
        cDao.delete(atividadeCalculada);
        cDao.close();
    }

    @Override
    public AtividadeCalculada buscar(AtividadeCalculada atividadeCalculada) throws SQLException {
        if (cDao.open() == null){
            cDao.open();
        }
        return cDao.findOne(atividadeCalculada);

    }

    @Override
    public List<AtividadeCalculada> listar() throws SQLException {
        if (cDao.open() == null){
            cDao.open();
        }


        return cDao.findAll();
    }
}
