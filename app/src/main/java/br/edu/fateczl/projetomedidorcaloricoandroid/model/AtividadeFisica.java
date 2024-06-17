package br.edu.fateczl.projetomedidorcaloricoandroid.model;

import androidx.annotation.NonNull;

public abstract class AtividadeFisica {
    protected String nome;
    protected int codigo;
    protected float met;
    protected String descricao;
    protected String tipo;


    public AtividadeFisica(float met) {
        this.met = met;
    }

    public AtividadeFisica(String nome, int codigo, float met, String descricao, String tipo) {
        this.nome = nome;
        this.codigo = codigo;
        this.met = met;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public AtividadeFisica() {
        super();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public float getMet() {
        return met;
    }

    public void setMet(float met) {
        this.met = met;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
