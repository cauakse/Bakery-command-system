package org.aplicacao;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class ComandaForm extends AnchorPane {
    private Label lbNumComanda , lbValorComanda , lbDataComanda;
    private ComboBox <Produto> cbProdutos;
    private Spinner <Integer> spQtde;
    private Button btAdicionar,btConfirmar,btCancelar,btPagar;
    private ListView lvItens;
    private Comanda comanda=null;
    private Caixa caixa=null;

    public ComandaForm(Comanda comanda, Caixa caixa) {
        super();
        this.comanda=comanda;
        this.caixa=caixa;
        inicializaComponentes();
    }

    private void inicializaComponentes() {
        AnchorPane painel = new AnchorPane();
        painel.setStyle("-fx-background-color:orange");
        painel.setLayoutX(10);
        painel.setLayoutY(15);
        painel.setPrefSize(525,100);
        Label label = new Label("Comanda");
        label.setLayoutX(15);
        label.setLayoutY(5);
        label.setFont(new Font("Arial",20));
        label.setStyle("-fx-text-fill:white");
        lbNumComanda= new Label("0");
        lbNumComanda.setFont(new Font("Arial",40));
        lbNumComanda.setStyle("-fx-text-fill:white");
        lbNumComanda.setLayoutX(35);
        lbNumComanda.setLayoutY(30);
        lbDataComanda = new Label("99/99/9999");
        lbDataComanda.setFont(new Font("Arial",15));
        lbDataComanda.setStyle("-fx-text-fill:white");
        lbDataComanda.setLayoutX(15);
        lbDataComanda.setLayoutY(80);
        Label label1 = new Label("R$");
        label1.setStyle("-fx-text-fill:white");
        label1.setFont(new Font("Arial",20));
        label1.setLayoutY(15);
        label1.setLayoutX(460);
        lbValorComanda= new Label("0,00");
        lbValorComanda.setFont(new Font("Arial",40));
        lbValorComanda.setStyle("-fx-text-fill:white");
        lbValorComanda.setLayoutY(40);
        lbValorComanda.setLayoutX(430);
        this.getChildren().add(painel);
        cbProdutos= new ComboBox();
        cbProdutos.setLayoutX(10);
        cbProdutos.setLayoutY(140);
        cbProdutos.setPrefWidth(300);
        spQtde= new Spinner(1,12,1);
        spQtde.setLayoutY(140);
        spQtde.setLayoutX(320);
        spQtde.setPrefWidth(75);
        btAdicionar=new Button("+");
        btAdicionar.setLayoutX(450);
        btAdicionar.setLayoutY(140);
        btAdicionar.setPrefWidth(80);
        lvItens=new ListView();
        lvItens.setLayoutX(10);
        lvItens.setLayoutY(180);
        lvItens.setPrefSize(525,240);
        btConfirmar=new Button("Confirmar");
        btConfirmar.setLayoutX(320);
        btConfirmar.setLayoutY(450);
        btCancelar= new Button("Cancelar");
        btCancelar.setLayoutX(400);
        btCancelar.setLayoutY(450);
        btPagar= new Button("Pagar");
        btPagar.setLayoutX(260);
        btPagar.setLayoutY(450);
        Comanda comandae = new Comanda(this.comanda);
        this.getChildren().addAll(cbProdutos,spQtde,btAdicionar,lvItens,btConfirmar,btCancelar,btPagar);
        btPagar.setOnAction(e->{
            pagarComanda(comandae);
        });
        lbNumComanda.setText(""+comanda.getNumero());
        lbDataComanda.setText(""+comanda.getData().toString());
        lbValorComanda.setText(""+comanda.getValorTotal());
        painel.getChildren().addAll(label,lbNumComanda,lbDataComanda,label1,lbValorComanda);
        cbProdutos.setItems(FXCollections.observableList(caixa.getProdutos()));
        List<Comanda.ItemComanda> itens = new ArrayList<>();
        btConfirmar.setOnAction(e->{
            confirmar(comandae,itens);
        });
        btAdicionar.setOnAction(e->{
            adicionarItem(itens);
        });
        btCancelar.setOnAction(e->{
            cancelar(itens);
        });
        lvItens.setItems(FXCollections.observableList(comanda.getItens()));
    }

    private void pagarComanda(Comanda comandae) {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setContentText("Informe valor do pagamento");
        double valorPago = Double.parseDouble(textInputDialog.showAndWait().get());
        while (comandae.pagarComanda(valorPago))
        {
            textInputDialog.setContentText("Valor insuficiente, faltam: "+comandae.getValor());
            lbValorComanda.setText(""+comandae.getValor());
            valorPago= Double.parseDouble(textInputDialog.showAndWait().get());
        }
        lbValorComanda.setText(""+comandae.getValor());
    }

    private void confirmar(Comanda comandae, List<Comanda.ItemComanda> itens){
        this.comanda.setId(comandae.getId());
        this.comanda.setValor(comandae.getValorTotal());
        this.comanda.setNumero(comandae.getNumero());
        this.comanda.setData(comandae.getData());
        this.comanda.setFlag(comandae.getFlag());
        for (int i=0;i<itens.size()-1;i++) {
            this.comanda.addItem(itens.get(i));}
        this.getScene().getWindow().hide();
    }

    private void cancelar(List<Comanda.ItemComanda> itens){
        List<Comanda.ItemComanda> lista = comanda.getItens();
        double valor=comanda.getValorTotal();
        for (int i=0;i< itens.size();i++) {
            lista.remove(itens.get(i));
            valor-=itens.get(i).produto().getValor()*itens.get(i).quantidade();
        }
        comanda.setItens(lista);
        comanda.setValor(valor);
        this.getScene().getWindow().hide();
    }

    private void adicionarItem(List<Comanda.ItemComanda> itens) {
        Comanda.ItemComanda item = new Comanda.ItemComanda(spQtde.getValue() , cbProdutos.getSelectionModel().getSelectedItem());
        itens.add(item);
        comanda.addItem(spQtde.getValue() , cbProdutos.getSelectionModel().getSelectedItem());
        lvItens.setItems(FXCollections.observableList(comanda.getItens()));
        lbValorComanda.setText(""+comanda.getValorTotal());
    }
}
