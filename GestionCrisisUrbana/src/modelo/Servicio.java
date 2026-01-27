/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import estructuras.ListaServicios;

/**
 *
 * @author ASUS
 */
public class Servicio {
    private String nombre;
    private int salud; // 0 - 100

    public Servicio(String nombre) {
        this.nombre = nombre;
        this.salud = 100;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSalud() {
        return salud;
    }

    public void afectar(int valor) {
        salud -= valor;
        if (salud < 0) salud = 0;
    }

    public int getNivel() {
        if (salud <= 30) return 3;   // crítico
        if (salud <= 60) return 2;   // medio
        return 1;                    // bajo
    }
}

