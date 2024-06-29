package org.aplicacao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Comanda {
    private int id;
    private int numero;
    private LocalDate data;
    private double valor;

    private static int id_seq=1;
    private boolean flag;
    private List<ItemComanda> itens;

    public Comanda(Comanda outro)
    {
        this.id=outro.getId();
        this.itens = outro.getItens();
        this.numero = outro.getNumero();
        this.data = outro.getData();
        this.valor = outro.getValor();
        this.flag= outro.getFlag();
    }
    public Comanda() {
        this(id_seq, 0,LocalDate.now(),0);
        id_seq++;
        flag=true;
        itens=new ArrayList<>();
    }

    public void setItens(List<ItemComanda> itens) {
        this.itens = itens;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public List<ItemComanda> getItens() {
        return itens;
    }

    public void addItem(ItemComanda item){
        valor+= item.quantidade()*item.produto().getValor();
        this.itens.add(item);
    }
    public void addItem(int quant, Produto produto)
    {
        ItemComanda item= new ItemComanda(quant, produto);
        this.valor+= produto.getValor()*quant;
        this.itens.add(item);
    }
    public Comanda(int id, int numero, LocalDate data, double valor) {
        this.id=id;
        this.numero=numero;
        this.data = data;
        this.valor = valor;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValorTotal()
    {
        double valor=0;
        for(ItemComanda item : itens )
            valor+= item.quantidade*item.produto.getValor();
        return valor;
    }
    public boolean getFlag()
    {
        return this.flag;
    }
    public boolean pagarComanda(double valor){
        if(this.valor>0)
        {
            if(valor>this.valor)
            {
                this.valor=0;
                flag=false;
                return false;
            }
            else {
                this.valor-=valor;
                if(this.valor>0){
                    return true;
                }
                else
                {
                    flag=false;
                    return false;
                }
            }

        }
        else return false;

    }

    @Override
    public String toString() {
        String str="***********************";
        str+="\nComanda id:"+id;
        str+="\nnúmero: "+numero;
        str+="\ndata: "+data;
        str+="\n***********************";
        for(ItemComanda item : itens )
            str+="\n"+item.quantidade+" "+item.produto.getNome()+" "+ item.produto.getValor();
        str+="\n***********************";
        str+="\nvalor total R$: "+this.getValorTotal();

        return str;
    }
    //inner class
    static record ItemComanda (int quantidade, Produto produto) {
        @Override
        public String toString() {
            return quantidade + " - " + produto.getNome();
        }
    }
}
