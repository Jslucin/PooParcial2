/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_2p_poo;

import com.mycompany.proyecto_2p_poo.rovers.Constantes;
import com.mycompany.proyecto_2p_poo.rovers.Crateres;
import com.mycompany.proyecto_2p_poo.rovers.Rover;
import com.mycompany.proyecto_2p_poo.rovers.RoversEnergiaEolica;
import com.mycompany.proyecto_2p_poo.rovers.RoversEnergiaSolar;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author samu_
 */
public class ExplorarController implements Initializable {

    @FXML
    private ComboBox<Rover> comborovers;
    @FXML
    private TextField textfi;
    @FXML
    private TextArea Zonacomandos;
    @FXML
    private Pane panel;
    private  List<Rover> rovers;
    List<Crateres> craters;
    @FXML
    private Pane paneldatos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try{
            craters=Crateres.obtenerCrateres();
            for(Crateres i: craters){
                Circle c= new Circle(i.getRadio()/2,Color.TRANSPARENT);
                c.setStroke(Color.RED);
                StackPane st= new StackPane();
                st.getChildren().addAll(c);
                panel.getChildren().addAll(st);
                st.setLayoutX(i.getCoordsx()/2);
                st.setLayoutY(i.getCoordsy()/2);
                st.setOnMouseClicked((MouseEvent ev)->{
                    paneldatos.getChildren().clear();
                    ev.consume();
                    Label datoscra=new Label();
                    datoscra.setText("  Crater: "+i.getNombre()+"\n  Radio: "+i.getRadio()+"\n  PosicionX: " +i.getCoordsx()+"\n  PosicionY: "+i.getCoordsy());
                    paneldatos.getChildren().add(datoscra);
                });
               
            }
            rovers=Rover.obtenerRovers();
            comborovers.getItems().addAll(rovers);
                
        }catch (IOException ex){
            System.out.print("Error en el sistema");
        }
        
   
    }
    

    @FXML
    private void cargaracciones(ActionEvent event) {
        for(Rover i: rovers){
            if (i.getImg()!=null){
                panel.getChildren().remove(i.getImg());
            }
        }
        Rover roveractual=comborovers.getValue();
        try{
            Image image=new Image(App.class.getResource("rover.png").openStream(),30,30,true,true);
            ImageView imgvw= new ImageView(image);
            roveractual.setImg(imgvw);
            panel.getChildren().add(imgvw);
            imgvw.setLayoutX(roveractual.getUbicacionx());
            imgvw.setLayoutY(roveractual.getUbicaciony());
            
        }catch (IOException ex){
            System.out.print("Error en el sistema");
            
        }
        
    }
    public void Actualizaarchivo(Rover roveractual) throws IOException{
        BufferedWriter bf= new BufferedWriter(new FileWriter(Constantes.ARCHIVOS+"/rovers-1.txt",false));
        String linea;
        for (Rover t: rovers){
            if(t.getNombre().equals(roveractual.getNombre())){
                if(t instanceof RoversEnergiaSolar){
                    linea=t.getNombre()+","+String.valueOf(roveractual.getUbicaciony())+","+String.valueOf(roveractual.getUbicacionx())+",solar"+","+String.valueOf(roveractual.getGrados());
                }
                else{
                    linea=t.getNombre()+","+String.valueOf(roveractual.getUbicaciony())+","+String.valueOf(roveractual.getUbicacionx())+",eolica,"+String.valueOf(roveractual.getGrados());
                }
            }
            else{
                if(t instanceof RoversEnergiaSolar){
                    linea=t.getNombre()+","+String.valueOf(t.getUbicaciony())+","+String.valueOf(t.getUbicacionx())+",solar,"+String.valueOf(t.getGrados());
                }
                else{
                    linea=t.getNombre()+","+String.valueOf(t.getUbicaciony())+","+String.valueOf(t.getUbicacionx())+",eolica,"+String.valueOf(t.getGrados());
                }
                        
            }
            bf.write(linea);
            bf.newLine();
            bf.flush();//para que se escriba inmediatamente en el archi
        }
        bf.close();
    }
    public void Actualizarcomandos(String accion){
        String textobase=Zonacomandos.getText();
        String linea;
        if (textobase!=null){
            linea=accion+"\n"+textobase;
            
        }else{
            linea=accion;
        }
        Zonacomandos.setText(linea);
    }
    @FXML
    private void Iniciarcomandos(KeyEvent event) {
        if (event.getCode() == event.getCode().ENTER) {
            try{
                Rover roveractual=comborovers.getValue();
                String accion= textfi.getText();
                textfi.clear();
                
                if(accion.toLowerCase().equals("avanzar")){
                    
                    roveractual.avanzar();
                    Actualizaarchivo(roveractual);
                    Actualizarcomandos(accion);
                }
                
                if(accion.contains(":")){
                    String[] partes=accion.split(":");
                    if (partes[0].toLowerCase().equals("girar")){
                        try{
                            roveractual.girar(Double.parseDouble(partes[1]));
                            Actualizaarchivo(roveractual);
                            Actualizarcomandos(accion);
                        }catch(NumberFormatException ex){
                            Alert alerta1=new Alert(Alert.AlertType.ERROR);
                            alerta1.setContentText("No es un número");
                            alerta1.show();
                        }
                    }else if(partes[0].toLowerCase().equals("dirigirse")){
                        if(partes[1].contains(",")){
                            String[] coords=partes[1].split(",");
                            try{
                                roveractual.dirigirse(Double.parseDouble(coords[0]), Double.parseDouble(coords[1]));
                                Actualizaarchivo(roveractual);
                                Actualizarcomandos(accion);
                            }catch(NumberFormatException ex){
                                Alert alerta2=new Alert(Alert.AlertType.ERROR);
                                alerta2.setContentText("No es un número");
                                alerta2.show();     
                            }
                        }
                    }
                }
                    if(accion.toLowerCase().equals("sensar")){
                        for(Crateres l: craters){
                            Circle circulo=new Circle(l.getCoordsx(),l.getCoordsy(),l.getRadio());
                            if(circulo.intersects(roveractual.getUbicacionx(), roveractual.getUbicaciony(),30,30)){
                                System.out.print("eo");
                            }
                        }   


                    }
                    if(accion.toLowerCase().equals("cargar")){
                        roveractual.cargar();
                        Actualizaarchivo(roveractual);
                        Actualizarcomandos(accion);
                    }
                
            }catch(IOException ex){
                System.out.print("Error en el sistema");
            }
        }
    }
    }