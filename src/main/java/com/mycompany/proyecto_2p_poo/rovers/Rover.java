/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_2p_poo.rovers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.ImageView;

/**
 *
 * @author samu_
 */
public abstract class Rover implements RoverI {
    protected String nombre;
    private double ubicacionx;
    private double ubicaciony;
    protected ImageView img;
    double carga;
    double grados;
    public Rover(String nombre,double ubicacionx, double ubicaciony, ImageView img, double grados){
        this.nombre=nombre;
        this.ubicacionx=ubicacionx;
        this.ubicaciony=ubicaciony;
        this.img=img;
        carga=100;
        this.grados=grados;
    }
   public String getNombre(){
       return nombre;
   }
   public double getUbicacionx(){
       return ubicacionx;
   }
   public double getUbicaciony(){
       return ubicaciony;
   }
   public void setUbicacionx(double ubicacionx){
       this.ubicacionx=ubicacionx;
   }
   public void setUbicaciony (double ubicaciony){
       this.ubicaciony=ubicaciony;
   }
    public static List<Rover> obtenerRovers(){
        
            ImageView imgvw=null;
        
        List<Rover> rovers=new ArrayList<>();
        try{
        BufferedReader bf= new BufferedReader(new FileReader(Constantes.ARCHIVOS+"/rovers-1.txt"));
        String linea;
        ArrayList<String> nombres=new ArrayList<>();
        while((linea=bf.readLine())!= null){
            String[] partes=linea.split(",");
            if(!nombres.contains(partes[0])){
                if (partes[3].equals("solar")){
                    nombres.add(partes[0]);
                    RoversEnergiaSolar rover= new RoversEnergiaSolar(partes[0],Double.parseDouble(partes[2]),Double.parseDouble(partes[1]),imgvw,Double.valueOf(partes[4]));
                    rovers.add(rover);
                }else{
                    RoversEnergiaEolica rover= new RoversEnergiaEolica(partes[0],Double.parseDouble(partes[2]),Double.parseDouble(partes[1]),imgvw,Double.valueOf(partes[4]));
                    rovers.add(rover);
                    nombres.add(partes[0]);
                }
            }
            else{
                for (Rover l: rovers){
                    if(l.getNombre().equals(partes[0])){
                        l.setUbicaciony(Double.parseDouble(partes[1]));
                        l.setUbicacionx(Double.parseDouble(partes[2]));
                        }
                    }
                }
        }
        }catch(IOException ex){
            System.out.print("Error en el sistema");
        }
        return rovers;
    }
    public String toString(){
        return nombre;
    }
    public void setImg(ImageView img){
        this.img=img;
    }
    public ImageView getImg(){
        return img;
    }
    public double getGrados(){
        return grados;
    }
    public void setGrados(double grados){
        this.grados=grados;
    }
}
