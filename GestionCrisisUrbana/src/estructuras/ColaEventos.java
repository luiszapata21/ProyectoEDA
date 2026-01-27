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
public class ColaEventos {
   
    private NodoEvento frente;
    private NodoEvento fin;

    public ColaEventos() {
        frente = null;
        fin = null;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public void insertar(Evento e) {
        NodoEvento nuevo = new NodoEvento(e);
        if (estaVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.liga = nuevo;
            fin = nuevo;
        }
    }

    public Evento eliminar() {
        if (estaVacia()) return null;
        Evento e = frente.info;
        frente = frente.liga;
        if (frente == null) fin = null;
        return e;
    }

    public NodoEvento getFrente() {
        return frente;
    }
}
