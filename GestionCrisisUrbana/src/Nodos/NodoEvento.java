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
    public Evento info;// Atributo que almacena la información del evento y cada nodo contiene un objeto de tipo Evento
    public NodoEvento liga; // Permite enlazar este nodo con otro formando una lista enlazada

    public NodoEvento(Evento e) { // Recibe un objeto Evento y lo asigna al nodo
        this.info = e; // Se guarda el evento dentro del nodo
        this.liga = null; // Inicialmente el nodo no apunta a ningún otro nodo
    }
}

