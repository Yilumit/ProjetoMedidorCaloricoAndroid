package br.edu.fateczl.projetomedidorcaloricoandroid.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.projetomedidorcaloricoandroid.model.AtividadeCalculada;
import br.edu.fateczl.projetomedidorcaloricoandroid.model.AtividadeFisica;
import br.edu.fateczl.projetomedidorcaloricoandroid.model.ExercicioPersonalizado;
import br.edu.fateczl.projetomedidorcaloricoandroid.persistence.ExercicioPersonalizadoDao;

public class ExercicioPersonalizadoController implements IController<ExercicioPersonalizado> {
    private final ExercicioPersonalizadoDao exDao;

    public ExercicioPersonalizadoController(ExercicioPersonalizadoDao exDao) {
        this.exDao = exDao;
    }

    @Override
    public void inserir(ExercicioPersonalizado exercicioPersonalizado) throws SQLException {
        if (exDao.open() == null){
            exDao.open();
        }
        exDao.insert(exercicioPersonalizado);
        exDao.close();
    }

    @Override
    public void modificar(ExercicioPersonalizado exercicioPersonalizado) throws SQLException {
        if (exDao.open() == null){
            exDao.open();
        }
        exDao.update(exercicioPersonalizado);
        exDao.close();
    }

    @Override
    public void excluir(ExercicioPersonalizado exercicioPersonalizado) throws SQLException {
        if (exDao.open() == null){
            exDao.open();
        }
        exDao.delete(exercicioPersonalizado);
        exDao.close();
    }

    @Override
    public ExercicioPersonalizado buscar(ExercicioPersonalizado exercicioPersonalizado) throws SQLException {
        if (exDao.open() == null){
            exDao.open();
        }
        return exDao.findOne(exercicioPersonalizado);
    }

    @Override
    public List<ExercicioPersonalizado> listar() throws SQLException {
        if (exDao.open() == null) {
            exDao.open();
        }
        return exDao.findAll();
    }
//    @Override
//    public List<AtividadeFisica> trocarLista(List<ExercicioPersonalizado> exercicioPersonalizados) {
//        return new ArrayList<>(exercicioPersonalizados);
//    }
}
