package org.aplicacao;

public class Main {
    public static void main(String[] args) {
        Caixa caixa=new Caixa();

        //adicionando um novo produto
        caixa.addProduto(new Produto("kibe",7,caixa.getCategorias().get(1)));
        //recuperando todos os produtos
        for(Produto p : caixa.getProdutos())
            System.out.println(p.getId()+" "+p.getNome());
        //criando novas comandas
        Comanda nova;
        nova=caixa.novaComanda(100);
        System.out.println("ID da nova comanda: "+nova.getId());
        nova=caixa.novaComanda(12);
        System.out.println("ID da nova comanda: "+nova.getId());
        // adicionando o produto de id 2 na comanda de id 1
        Comanda aux=caixa.getComanda(1);
        aux.addItem(3, caixa.getProdutoId(2));
        aux.addItem(2, caixa.getProdutoId(1));
        aux.addItem(5, caixa.getProdutoId(3));

        System.out.println(aux);

    }
}