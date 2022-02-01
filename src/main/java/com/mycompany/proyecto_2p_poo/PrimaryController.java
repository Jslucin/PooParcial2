package com.mycompany.proyecto_2p_poo;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class PrimaryController {

    @FXML
    private void explorar(MouseEvent event) throws IOException {
        App.setRoot("Explorar");
    }

    @FXML
    private void planificarrutas(MouseEvent event) throws IOException{
        App.setRoot("Planificarrutas");
    }

    @FXML
    private void verreportes(MouseEvent event) throws IOException {
        App.setRoot("Verreportes");
    }

    @FXML
    private void salir(MouseEvent event) {
        Platform.exit();
    }
}
