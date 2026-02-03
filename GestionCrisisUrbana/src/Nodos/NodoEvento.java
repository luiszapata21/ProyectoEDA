/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Nodos;

import modelo.Evento;

/**
 *
 * @author ASUS
 */

public class NodoEvento {
    public Evento info;
    public NodoEvento liga;

    public NodoEvento(Evento e) {
        this.info = e;
        this.liga = null;
    }
}

