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
    private NodoEvento frente; // Referencia al primer nodo de la cola (frente)
    private NodoEvento fin; // Referencia al último nodo de la cola (fin)
    private int tamanio; // Variable que almacena el número total de elementos en la cola

    public ColaEventos() { // Constructor de la clase ColaEventos e inicializar la cola como vacía
        frente = null; 
        fin = null;
        tamanio = 0;
    }

    public void insertar(Evento e) { // El evento se agrega siempre al final de la cola
        NodoEvento nuevo = new NodoEvento(e); // Se crea un nuevo nodo con la información del evento
        if (frente == null) { // Si la cola está vacía, el nuevo nodo será tanto el frente como el fin de la cola
            frente = nuevo;
            fin = nuevo;
        } else { // Si la cola ya tiene elementos, se enlaza el nodo actual del fin con el nuevo nodo y se actualiza el fin
            fin.liga = nuevo;
            fin = nuevo;
        }
        tamanio++; // Se incrementa el tamaño de la cola
    }

    
    public Evento eliminar() { // Método que elimina y retorna el evento del frente de la cola
    // Sigue el principio FIFO (First In, First Out)
        if (frente == null) return null; // Si la cola está vacía, no hay elementos para eliminar
        Evento e = frente.info; // Se obtiene el evento almacenado en el frente
        frente = frente.liga; // El frente avanza al siguiente nodo
        tamanio--; // Se decrementa el tamaño de la cola
        if (frente == null) { // Si después de eliminar la cola queda vacía, el fin también se establece como null
            fin = null;
        }
        return e; // Se retorna el evento eliminado

    }
    public Evento verFrente() { // Método que permite visualizar el evento que está en el frente sin eliminarlo de la cola
        if (frente == null) return null;
        return frente.info;
    }
    public boolean estaVacia() { // Método que verifica si la cola está vacía
        return frente == null;
    }
    public int getTamanio() { // Método que retorna el número total de eventos en la cola
        return tamanio;
    }
    public NodoEvento getFrente() { // Método que retorna el nodo que está en el frente de la cola
        return frente;
    }
}
