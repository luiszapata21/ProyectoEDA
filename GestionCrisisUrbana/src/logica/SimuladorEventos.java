/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import estructuras.ColaEventos;
import estructuras.PilaEventos;
import modelo.Evento;

/**
 *
 * @author ASUS
 */
public class SimuladorEventos {
    private ColaEventos cola;
    private PilaEventos pila;

    public SimuladorEventos(ColaEventos cola, PilaEventos pila) {
        this.cola = cola;
        this.pila = pila;
    }

    public void atenderEventoConTiempo(Runnable onAtendido) {

        int tiempo = 2000 + (int)(Math.random() * 4000);
        new javax.swing.Timer(tiempo, ev -> {
            if (!cola.estaVacia()) {
                Evento e = cola.eliminar();
                pila.apilar(e);
                onAtendido.run();
            }
            ((javax.swing.Timer) ev.getSource()).stop();
        }).start();
    }
}
