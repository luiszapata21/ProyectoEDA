/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

import Nodos.NodoServicio;
import modelo.Servicio;

/**
 *
 * @author ASUS
 */
public class ListaServicios {
    private NodoServicio cabeza;

    public ListaServicios() {
        cabeza = null;
    }

    public void agregarServicio(Servicio s) {
        NodoServicio nuevo = new NodoServicio(s);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
    }

    public Servicio buscarServicio(String nombre) {
        NodoServicio aux = cabeza;
        while (aux != null) {
            if (aux.servicio.getNombre().equals(nombre)) {
                return aux.servicio;
            }
            aux = aux.siguiente;
        }
        return null;
    }

    public NodoServicio getCabeza() {
        return cabeza;
    }
}
