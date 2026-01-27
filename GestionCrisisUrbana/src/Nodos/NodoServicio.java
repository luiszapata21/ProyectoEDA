/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Nodos;

import modelo.Servicio;

/**
 *
 * @author ASUS
 */
public class NodoServicio {
    public Servicio servicio;
    public NodoServicio siguiente;

    public NodoServicio(Servicio s) {
        this.servicio = s;
        this.siguiente = null;
    }
}
