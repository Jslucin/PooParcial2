/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_2p_poo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author samu_
 */
public class PlanificarrutasController implements Initializable {

    @FXML
    private TextField crtbuscar;
    @FXML
    private GridPane Lista;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void generarrutas(KeyEvent event) {
        if (event.getCode() == event.getCode().ENTER) {
            String obtenido=crtbuscar.getText();
            if(obtenido.contains(",")){
                String partes[]=obtenido.split(",");
                
            }
        }
    }
    
}
