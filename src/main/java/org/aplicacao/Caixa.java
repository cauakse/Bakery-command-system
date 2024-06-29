package org.aplicacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Caixa {
    private List<Categoria> categorias;
    private List<Produto> produtos;
    private List<Comanda> comandas;

    public Caixa() {
        categorias=new ArrayList<>();
        produtos=new ArrayList<>();
        comandas=new ArrayList<>();
        adicionarProdutos();
    }

    private void adicionarProdutos() {
        categorias.addAll(Arrays.asList(new Categoria("doces"),new Categoria("salgados"),
                new Categoria("bebidas"),new Categoria("paes")));
        produtos.addAll(Arrays.asList(
                new Produto("carolina",2.5,categorias.get(0)),
                new Produto("coxinha",8,categorias.get(1)),
                new Produto("enroladinho",8,categorias.get(1)),
                new Produto("coca lata",5,categorias.get(2)),
                new Produto("misto quente",11,categorias.get(3))));
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }


    public List<Produto> getProdutos() {
        return produtos;
    }


    public void addProduto(Produto produto)
    {
        produtos.add(produto);
    }
    public Comanda novaComanda(int numero)
    {
        Comanda nova=new Comanda();
        nova.setNumero(numero);
        comandas.add(nova);
        return nova;
    }
    public Comanda getComanda(int id)
    {
        Comanda comanda=null;
        for (Comanda c : comandas)
        {
            if (c.getId()==id)
                comanda=c;
        }
        return comanda;
    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public Produto getProdutoId(int id) {
        Produto produto=null;
        for (Produto p : produtos)
        {
            if (p.getId()==id)
                produto=p;
        }
        return produto;
    }
}
