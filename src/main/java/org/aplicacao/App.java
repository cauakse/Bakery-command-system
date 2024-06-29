package org.aplicacao;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        Caixa caixa=new Caixa();

        Comanda nova;
        nova=caixa.novaComanda(100);
        nova=caixa.novaComanda(24);
        nova=caixa.novaComanda(13);
        nova=caixa.novaComanda(52);
        nova=caixa.novaComanda(12);
        nova=caixa.novaComanda(1);

        nova.addItem(3, caixa.getProdutoId(2));
        nova.addItem(2, caixa.getProdutoId(1));
        nova.addItem(5, caixa.getProdutoId(3));

        var scene = new Scene(new ComandaPainel(caixa), 720, 640);
        stage.setScene(scene);
        stage.setOnCloseRequest(e->{
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Sair da Aplicação");
            alerta.setContentText("Deseja realmente fechar a aplicação?");
            Optional<ButtonType> buttonType = alerta.showAndWait();
            if(buttonType.get()==ButtonType.OK)
                stage.close();
            else e.consume();
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}