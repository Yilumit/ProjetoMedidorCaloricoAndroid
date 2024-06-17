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

import br.edu.fateczl.projetomedidorcaloricoandroid.model.AtividadeCalculada;

public class AtividadeCalculadaDao implements IAtividadeCalculadaDao, ICRUDDao<AtividadeCalculada> {

    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase database;
    public AtividadeCalculadaDao(Context context) {
        this.context = context;
    }

    @Override
    public AtividadeCalculadaDao open() throws SQLException {
        gDao = new GenericDao(context);
        database = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }
    private static ContentValues getContentValues(AtividadeCalculada atividadeCalculada) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("codigo", atividadeCalculada.getCodigo());
        contentValues.put("nome", atividadeCalculada.getNome());
        contentValues.put("met", atividadeCalculada.getMet());
        contentValues.put("descricao", atividadeCalculada.getDescricao());
        contentValues.put("tipo", "Atividade Calculada");


        return contentValues;
    }
    @Override
    public void insert(AtividadeCalculada atividadeCalculada) throws SQLException {
        ContentValues contentValues = getContentValues(atividadeCalculada);
        database.insert("atividade", null, contentValues);
    }

    @Override
    public int update(AtividadeCalculada atividadeCalculada) throws SQLException {
        ContentValues contentValues = getContentValues(atividadeCalculada);
        return database.update("atividade", contentValues, "codigo = " +
                atividadeCalculada.getCodigo(), null);
    }

    @Override
    public void delete(AtividadeCalculada atividadeCalculada) throws SQLException {
        database.delete("atividade", "codigo = " +
                atividadeCalculada.getCodigo(), null);
    }

    @SuppressLint("Range")
    @Override
    public AtividadeCalculada findOne(AtividadeCalculada atividadeCalculada) throws SQLException {
        String sql = "SELECT * FROM atividade WHERE codigo =" + atividadeCalculada.getCodigo();
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (!cursor.isAfterLast()) {
            atividadeCalculada.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            atividadeCalculada.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            atividadeCalculada.setMet(cursor.getFloat(cursor.getColumnIndex("met")));
            atividadeCalculada.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            atividadeCalculada.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
        }
        cursor.close();
        return atividadeCalculada;
    }

    @SuppressLint("Range")
    @Override
    public List<AtividadeCalculada> findAll() throws SQLException {
        ArrayList<AtividadeCalculada> atividadeCalculadas = new ArrayList<>();
        String sql = "SELECT * FROM atividade";
        try {
            Cursor cursor = database.rawQuery(sql, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            while (!cursor.isAfterLast()) {
                AtividadeCalculada atividadeCalculada = new AtividadeCalculada();
                atividadeCalculada.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                atividadeCalculada.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                atividadeCalculada.setMet(cursor.getFloat(cursor.getColumnIndex("met")));
                atividadeCalculada.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                atividadeCalculada.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));

                atividadeCalculadas.add(atividadeCalculada);
                cursor.moveToNext();
            }

            cursor.close();
        } catch (Exception e) {
            Toast.makeText(context.getApplicationContext(), "Nao ha o que mostar", Toast.LENGTH_LONG).show();
        }
        return atividadeCalculadas;
    }
}
