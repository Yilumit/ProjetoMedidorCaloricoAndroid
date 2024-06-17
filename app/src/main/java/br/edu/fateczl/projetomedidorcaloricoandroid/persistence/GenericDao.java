package br.edu.fateczl.projetomedidorcaloricoandroid.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GenericDao extends SQLiteOpenHelper {


    private static final String DATABASE = "MEDIDOR_DE_CALORIAS.DB";
    private static final int DATABASE_VER = 1;
    private static final String CREATE_TABLE_ATV =
            "CREATE TABLE atividade( " +
                "codigo INT NOT NULL PRIMARY KEY," +
                "nome VARCHAR(150) NOT NULL," +
                "met NUMERIC(2,2) NOT NULL," +
                "descricao VARCHAR(200)," +
                "tipo VARCHAR(60) );";
    private static final String CREATE_TABLE_EXPERSONA =
            "CREATE TABLE exercicio( " +
                    "codigo INT NOT NULL PRIMARY KEY," +
                    "nome VARCHAR(150) NOT NULL," +
                    "met NUMERIC(2,2) NOT NULL," +
                    "descricao VARCHAR(200)," +
                    "tipo VARCHAR(50) );";
    private static final String INSERT_INTO_ATIVIDADE =
            "INSERT INTO atividade(codigo, nome, met, descricao, tipo) VALUES " +
                    "(2020, 'Flexoes, abdominais, puxadas', 8.0, 'Calistenia, Exercicio de condicionamento', 'Exercicio Calculado'); " +
                    "(2050, 'Levantamento de peso', 6.0, 'Exercicio de condicionamento', 'Exercicio Calculado'), " +
                    "(2060, 'Exercicios em centros de saude', 5.5, 'Exercicio de condicionamento', 'Exercicio Calculado'), " +
                    "(2101, 'Alongamento', 2.5, 'Exercicio de condicionamento', 'Exercicio Calculado'), " +
                    "(2120, 'Hidroginastica', 4.0, 'Exercicio de condicionamento', 'Exercicio Calculado'), " +
                    "(12030, 'Correr, 8 km/h (7,5 min.km-1)', 8.0, 'Correr', 'Exercicio Calculado'), " +
                    "(12080, 'Correr, 12,0 km/h (5 min.km-1)', 12.5, 'Correr', 'Exercicio Calculado'), " +
                    "(12130, 'Correr, 17,5 km/h (3,4 min.km-1)', 18.0, 'Correr', 'Exercicio Calculado'), " +
                    "(12180, 'Basquetebol', 8.0, 'Esportes', 'Exercicio Calculado'), " +
                    "(12190, 'Judô, Jiu-jitsu, karatê, kick boxing, tae-kwon-do', 10.0, 'Esportes', 'Exercicio Calculado');";
    public GenericDao(Context context){
        super(context, DATABASE, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ATV);
        db.execSQL("INSERT INTO atividade(codigo, nome, met, descricao, tipo) VALUES " +
                "(2020, 'Flexoes, abdominais, puxadas', 8.0, 'Calistenia, Exercicio de condicionamento', 'Exercicio Calculado'); ");
        db.execSQL("INSERT INTO atividade(codigo, nome, met, descricao, tipo) VALUES " +
                "(2050, 'Levantamento de peso', 6.0, 'Exercicio de condicionamento', 'Exercicio Calculado');");
        db.execSQL("INSERT INTO atividade(codigo, nome, met, descricao, tipo) VALUES " +
                "(2060, 'Exercicios em centros de saude', 5.5, 'Exercicio de condicionamento', 'Exercicio Calculado');");
        db.execSQL("INSERT INTO atividade(codigo, nome, met, descricao, tipo) VALUES " +
                "(2101, 'Alongamento', 2.5, 'Exercicio de condicionamento', 'Exercicio Calculado');");
        db.execSQL("INSERT INTO atividade(codigo, nome, met, descricao, tipo) VALUES " +
                "(2120, 'Hidroginastica', 4.0, 'Exercicio de condicionamento', 'Exercicio Calculado');");
        db.execSQL("INSERT INTO atividade(codigo, nome, met, descricao, tipo) VALUES " +
                "(12030, 'Correr, 8 km/h (7,5 min.km-1)', 8.0, 'Correr', 'Exercicio Calculado');");
        db.execSQL("INSERT INTO atividade(codigo, nome, met, descricao, tipo) VALUES " +
                "(12080, 'Correr, 12,0 km/h (5 min.km-1)', 12.5, 'Correr', 'Exercicio Calculado');");
        db.execSQL("INSERT INTO atividade(codigo, nome, met, descricao, tipo) VALUES " +
                "(12130, 'Correr, 17,5 km/h (3,4 min.km-1)', 18.0, 'Correr', 'Exercicio Calculado');");
        db.execSQL("INSERT INTO atividade(codigo, nome, met, descricao, tipo) VALUES " +
                "(12180, 'Basquetebol', 8.0, 'Esportes', 'Exercicio Calculado');");
        db.execSQL("INSERT INTO atividade(codigo, nome, met, descricao, tipo) VALUES " +
                "(12190, 'Judô, Jiu-jitsu, karatê, kick boxing, tae-kwon-do', 10.0, 'Esportes', 'Exercicio Calculado');");
        db.execSQL(CREATE_TABLE_EXPERSONA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion){
            db.execSQL("DROP TABLE IF EXISTS atividade");
            db.execSQL("DROP TABLE IF EXISTS exercicio");
            onCreate(db);
        }
    }
}
