package br.edu.fateczl.projetomedidorcaloricoandroid.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.projetomedidorcaloricoandroid.model.ExercicioPersonalizado;

public class ExercicioPersonalizadoDao implements IExercicioPesonalizado, ICRUDDao<ExercicioPersonalizado> {
    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase database;

    public ExercicioPersonalizadoDao(Context context) {
        this.context = context;
    }

    @Override
    public IExercicioPesonalizado open() throws SQLException {
        gDao = new GenericDao(context);
        database = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    private static ContentValues getContentValues(ExercicioPersonalizado exercicioPersonalizado) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("codigo", exercicioPersonalizado.getCodigo());
        contentValues.put("nome", exercicioPersonalizado.getNome());
        contentValues.put("met", exercicioPersonalizado.getMet());
        contentValues.put("descricao", exercicioPersonalizado.getDescricao());
        contentValues.put("tipo", "Exercicio Personalizado");

        return contentValues;
    }
    @Override
    public void insert(ExercicioPersonalizado exercicioPersonalizado) throws SQLException {
        ContentValues contentValues = getContentValues(exercicioPersonalizado);
        database.insert("exercicio", null, contentValues);
    }

    @Override
    public int update(ExercicioPersonalizado exercicioPersonalizado) throws SQLException {
        ContentValues contentValues = getContentValues(exercicioPersonalizado);
        return database.update("exercicio", contentValues, "codigo = " +
                exercicioPersonalizado.getCodigo(), null);
    }

    @Override
    public void delete(ExercicioPersonalizado exercicioPersonalizado) throws SQLException {
        database.delete("exercicio", "codigo = " +
                exercicioPersonalizado.getCodigo(), null);
    }

    @SuppressLint("Range")
    @Override
    public ExercicioPersonalizado findOne(ExercicioPersonalizado exercicioPersonalizado) throws SQLException {
        String sql = "SELECT * FROM exercicio WHERE codigo =" + exercicioPersonalizado.getCodigo();
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (!cursor.isAfterLast()) {
            exercicioPersonalizado.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            exercicioPersonalizado.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            exercicioPersonalizado.setMet(cursor.getFloat(cursor.getColumnIndex("met")));
            exercicioPersonalizado.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            exercicioPersonalizado.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));

        }
        cursor.close();
        return exercicioPersonalizado;
    }

    @SuppressLint("Range")
    @Override
    public List<ExercicioPersonalizado> findAll() throws SQLException {
        ArrayList<ExercicioPersonalizado> exercicioPersonalizados = new ArrayList<>();
        String sql = "SELECT * FROM exercicio";
        try {
            Cursor cursor = database.rawQuery(sql, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            while (!cursor.isAfterLast()) {
                ExercicioPersonalizado exercicioPersonalizado = new ExercicioPersonalizado();
                exercicioPersonalizado.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                exercicioPersonalizado.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                exercicioPersonalizado.setMet(cursor.getFloat(cursor.getColumnIndex("met")));
                exercicioPersonalizado.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                exercicioPersonalizado.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));

                exercicioPersonalizados.add(exercicioPersonalizado);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e){
            Toast.makeText(context.getApplicationContext(), "Nao ha o que mostar", Toast.LENGTH_LONG).show();
        }
        return exercicioPersonalizados;
    }
}
