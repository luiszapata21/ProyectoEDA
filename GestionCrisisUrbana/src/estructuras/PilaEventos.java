/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

import Nodos.NodoEvento;
import modelo.Evento;

/**
 *
 * @author ASUS
 */
public class PilaEventos {
    
     private NodoEvento cima;

    public boolean estaVacia() {
        return cima == null;
    }

    public NodoEvento getCima() {
        return cima;
    }
    
    

    public void apilar(Evento e) {
        NodoEvento nuevo = new NodoEvento(e);
        nuevo.liga = cima;
        cima = nuevo;
    }

    public Evento desapilar() {
        if (estaVacia()) return null;
        Evento e = cima.info;
        cima = cima.liga;
        return e;
    }
}
