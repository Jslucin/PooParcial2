/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_2p_poo.rovers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author samu_
 */
public class Crateres {
    private int id;
    private String nombre;
    private double coordsy;
    private double coordsx;
    private double radio;
    public Crateres(int id, String nombre,double coordsy, double coordsx, double radio){
        this.id=id;
        this.nombre=nombre;
        this.coordsy=coordsy;
        this.coordsx=coordsx;
        this.radio=radio;
    }
    public static List<Crateres> obtenerCrateres() throws IOException {
        List<Crateres> crateres=new ArrayList<>();
        BufferedReader bf= new BufferedReader(new FileReader (Constantes.ARCHIVOS+"/crateres_info.txt"));
        String linea;
        while((linea=bf.readLine())!=null){
            String[] partes = linea.split(",");
            int idn=Integer.valueOf(partes[0]);
            String nombren=partes[1];
            double valory=Double.valueOf(partes[2]);
            double valorx=Double.valueOf(partes[3]);
            double radion=Double.valueOf(partes[4]);
            crateres.add(new Crateres(idn,nombren,valory,valorx,radion));
            }
        return crateres;
    }
    public int getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public double getCoordsy(){
        return coordsy;
    }
    public double getCoordsx(){
        return coordsx;
    }
    public double getRadio(){
        return radio;
    }
    public static void main(String[] args){
        try{
            List<Crateres> crateres=obtenerCrateres();
            for(Crateres i: crateres){
                System.out.print(i.getNombre());
            }
        }catch (IOException ex){
            System.out.print("EO");
        }
    }
}
