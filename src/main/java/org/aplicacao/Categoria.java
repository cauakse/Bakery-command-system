package org.aplicacao;

public class Categoria {
    private int id;
    private String nome;
    private static int id_seq=1;

    public Categoria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Categoria(String nome) {
        this(id_seq,nome);
        id_seq++;
    }

    public Categoria() {
        this(0,"");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
