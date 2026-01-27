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
public class PilaLIFO {
    
  private Evento[] pila;
    private int cima;

    public PilaLIFO() {
        pila = new Evento[100];
        cima = -1;
    }

    public void push(Evento e) {
        if (cima < pila.length - 1) {
            pila[++cima] = e;
        }
    }

    public Evento pop() {
        if (cima == -1) return null;
        return pila[cima--];
    }

    public Evento verCima() {
        if (cima == -1) return null;
        return pila[cima];
    }

    public boolean estaVacia() {
        return cima == -1;
    }
    
     public Evento[] getPila() {
        return pila;
    }

    public int getCima() {
        return cima;
    }
    
}
