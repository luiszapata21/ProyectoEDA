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
    
     private NodoEvento cima; // Referencia al nodo que se encuentra en la cima de la pila, representa el último evento agregado

    public boolean estaVacia() { // Método que verifica si la pila está vacía
        return cima == null;
    }

    public NodoEvento getCima() { // Método que retorna el nodo que se encuentra en la cima de la pila
        return cima;
    }
    
    // Método que agrega un nuevo evento a la pila
    // El evento se inserta en la cima siguiendo el principio LIFO
    public void apilar(Evento e) {
        NodoEvento nuevo = new NodoEvento(e); // Se crea un nuevo nodo con la información del evento
        nuevo.liga = cima; // El nuevo nodo apunta al nodo que antes era la cima
        cima = nuevo; // Se actualiza la cima para que apunte al nuevo nodo
    }

    public Evento desapilar() { // Método que elimina y retorna el evento que está en la cima de la pila
        if (estaVacia()) return null; // Si la pila está vacía, no hay elementos para retirar
        Evento e = cima.info; // Se obtiene el evento almacenado en la cima
        cima = cima.liga; // La cima pasa a apuntar al siguiente nodo
        return e; // Se retorna el evento eliminado
    }
}
