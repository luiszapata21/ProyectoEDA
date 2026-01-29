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
    private int tamanio;

    public ColaEventos() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    public void insertar(Evento e) {
        NodoEvento nuevo = new NodoEvento(e);

        if (frente == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.liga = nuevo;
            fin = nuevo;
        }
        tamanio++;
    }

    public Evento eliminar() {
        if (frente == null) return null;

        Evento e = frente.info;
        frente = frente.liga;
        tamanio--;

        if (frente == null) {
            fin = null;
        }
        return e;
    }

    public Evento verFrente() {
        if (frente == null) return null;
        return frente.info;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    public NodoEvento getFrente() {
        return frente;
    }
}
