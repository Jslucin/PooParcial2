/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_2p_poo.rovers;

import com.mycompany.proyecto_2p_poo.App;
import java.util.ArrayList;
import javafx.scene.image.ImageView;

/**
 *
 * @author samu_
 */
public class RoversEnergiaEolica extends Rover {
    public static String[] minerales={"",",hematita ",",filosilicatos" , ",goethita", ",jarosita", ",minerales de sulfato de hierro" , ",sílice" ,"opalina","yeso"};   
    public RoversEnergiaEolica(String nombre,double ubicacionx, double ubicaciony,ImageView img,double grados){
        super(nombre,ubicacionx,ubicaciony,img,grados);
    }
    public void avanzar(){
        double angulo=img.getRotate();
        double ubx=2*Math.cos(angulo*Math.PI/180) +getUbicacionx();
        double uby=2*Math.sin(angulo*Math.PI/180) +getUbicaciony();
        setUbicacionx(ubx);
        setUbicaciony(uby);
        img.setX(ubx);
        img.setY(uby);
        carga=carga-0.2;
        
    }
    public void girar(double grados){
        double angulo=img.getRotate();
        double nuevo=angulo+grados;
        img.setRotate(nuevo);
        setGrados(nuevo);
    }
    public void dirigirse(double x, double y){
        Moverse move= new Moverse(x,y);
        
        move.run();
        
    }

    @Override
    public String sensar() {
        double i=1;
        ArrayList<Double> lista=new ArrayList<>();
        ArrayList<String> mineobt= new ArrayList<>();
        String sensar="Mineral sensado: ";
        while(i!=0){
            i=Math.floor(Math.random()*(9-0)+0);
            lista.add(i);
            if (!lista.contains(i)){
                int v=(int)Math.round(i);
                mineobt.add(minerales[v]);
            }
        }
        for (String l: mineobt){
            sensar=sensar+l;
        }
        return sensar;
    }

    @Override
    public String cargar() {
        double angulo=img.getRotate()+90;
        girar(angulo);
        carga=100;
        return "Despliegue de molinos";
    }
    class Moverse implements Runnable{
        private double x;
        private double y;
        Moverse(double x, double y){
            this.x=x;
            this.y=y;
        }
        public void run(){
            double angulo=Math.atan(y-getUbicaciony()/x-getUbicacionx())-img.getRotate();
            girar(angulo);
            while(x!=getUbicacionx() & y!=getUbicaciony() & x>getUbicacionx() & y>getUbicaciony()){
                avanzar();
            }
        }
    }
    
}
