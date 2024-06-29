package org.aplicacao;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class ComandaPainel extends FlowPane {
    private Caixa caixa;
    private boolean flag = false;
    private int larg=160;
    private int alt=140;

    public ComandaPainel(Caixa caixa){
        super();
        this.caixa=caixa;
        inicializaComponenteN();
        inicializaComponente1();
    }

    private void inicializaComponenteN() {
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color:#B0BEC5");
        this.setVgap(15);
        this.setHgap(15);
        Button btAdd = new Button("+");
        Button btMenu = new Button("M");
        btMenu.setStyle("-fx-background-color:#29B6F6;-fx-text-fill:yellow");
        btMenu.setFont(new Font("Arial", 60));
        btMenu.setPrefSize(160, 140);
        btMenu.setOnAction(e -> {
            menu();
        });
        btAdd.setPrefSize(160, 140);
        btAdd.setStyle("-fx-background-color:#29B6F6;-fx-text-fill:yellow");
        btAdd.setFont(new Font("Arial", 60));
        btAdd.setOnAction(e -> {
            criarComanda();
        });
        this.getChildren().add(btMenu);
        this.getChildren().add(btAdd);
    }
    private void inicializaComponente1(){

        for (Comanda comanda: caixa.getComandas())
        {
            if(comanda.getFlag())
            {
                Button bt=new Button(""+comanda.getNumero());
                bt.setPrefSize(larg,alt);
                if(comanda.getFlag()&&comanda.getValor()==0)
                {
                    bt.setStyle("-fx-background-color:#F06292;-fx-text-fill:white");
                }
                else if(comanda.getFlag()){
                    bt.setStyle("-fx-background-color:#26C6DA;-fx-text-fill:white");
                }
                else
                {
                    bt.setStyle("-fx-background-color:#FF9800;-fx-text-fill:white");
                }
                bt.setFont(new Font("Arial",48));
                this.getChildren().add(bt);
                bt.setOnAction(e->{
                    abrirComanda(comanda);
                });
            }
        }
    }
    private void inicializaComponente2(){

        for (Comanda comanda: caixa.getComandas())
        {
                Button bt=new Button(""+comanda.getNumero());
                bt.setPrefSize(larg,alt);
                if(comanda.getFlag()&&comanda.getValorTotal()==0)
                {
                    bt.setStyle("-fx-background-color:#F06292;-fx-text-fill:white");
                }
                else if(comanda.getFlag()){
                    bt.setStyle("-fx-background-color:#26C6DA;-fx-text-fill:white");
                }
                else
                {
                    bt.setStyle("-fx-background-color:#FF9800;-fx-text-fill:white");
                }
                bt.setFont(new Font("Arial",48));
                this.getChildren().add(bt);
                bt.setOnAction(e->{
                    abrirComanda(comanda);
                });

        }
    }

    private void menu() {
       FlowPane painel = new FlowPane();
        painel.setAlignment(Pos.CENTER);
        painel.setStyle("-fx-background-color:#B0BEC5");
        painel.setVgap(15);
        painel.setHgap(15);
        Button btOrd = new Button("O");
        Button btTam = new Button("T");
        Button btShow = new Button("S");
        btOrd.setStyle("-fx-background-color:#29B6F6;-fx-text-fill:yellow");
        btOrd.setFont(new Font("Arial",60));
        btOrd.setPrefSize(160,140);
        btOrd.setOnAction(e->{
            ordena();
        });
        btTam.setStyle("-fx-background-color:#29B6F6;-fx-text-fill:yellow");
        btTam.setFont(new Font("Arial",60));
        btTam.setPrefSize(160,140);
        btTam.setOnAction(e->{
            tamanho();
        });
        btShow.setStyle("-fx-background-color:#29B6F6;-fx-text-fill:yellow");
        btShow.setFont(new Font("Arial",60));
        btShow.setPrefSize(160,140);
        btShow.setOnAction(e->{
            this.getChildren().clear();
            inicializaComponenteN();
            if(flag)
            {
                inicializaComponente1();
            }
            else {
                inicializaComponente2();
            }
            flag=!flag;
        });

        painel.getChildren().addAll(btTam,btShow,btOrd);
        Scene scene = new Scene(painel,545,510);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private void tamanho() {
        if(larg>20 && alt>20)
        {
            larg=larg/2;
            alt=alt/2;
        }
        this.getChildren().clear();
        inicializaComponenteN();
        if(flag)
        {
            inicializaComponente1();
        }
        else {
            inicializaComponente2();
        }
        flag=!flag;
    }

    private void ordena() {
        int i=0,j;
        Comanda aux;
        List<Comanda> lista = caixa.getComandas();
        for (i=0;i<lista.size();i++) {
            for (j=i;j<lista.size();j++)
                if(lista.get(i).getNumero()>lista.get(j).getNumero()){
                    aux=lista.get(i);
                    lista.set(i,lista.get(j));
                    lista.set(j,aux);
                }
        }
        this.getChildren().clear();
        inicializaComponenteN();
        if(flag)
        {
            inicializaComponente1();
        }
        else {
            inicializaComponente2();
        }
    }

    private void criarComanda() {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setContentText("Informe o numero da comanda");
        String aux = textInputDialog.showAndWait().get();
        Comanda comanda = caixa.novaComanda(Integer.parseInt(aux));
        Scene scene = new Scene(new ComandaForm(comanda,caixa),545,510);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        this.getChildren().clear();
        inicializaComponenteN();
        if(flag)
        {
            inicializaComponente2();
        }
        else inicializaComponente1();
    }

    private void abrirComanda(Comanda comanda) {
        Scene scene = new Scene(new ComandaForm(comanda,caixa),545,510);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        this.getChildren().clear();
        inicializaComponenteN();
        if(flag)
        {
            inicializaComponente2();
        }
        else inicializaComponente1();

    }
}
