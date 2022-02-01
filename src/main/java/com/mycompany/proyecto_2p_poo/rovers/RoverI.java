/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.proyecto_2p_poo.rovers;

/**
 *
 * @author samu_
 */
public interface RoverI {
    public void avanzar();
    public void girar(double grados);
    public void dirigirse(double x, double y);
    public String sensar();
    public String cargar();
    
}
