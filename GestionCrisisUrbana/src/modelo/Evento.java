/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalTime;

/**
 *
 * @author ASUS
 */
public class Evento {
    private int id;
    private String tipo;        // Eléctrico, Agua, Salud, etc
    private String nivel;          // 3 = crítico, 2 = medio, 1 = bajo
    private String descripcion;

    public Evento(int id, String tipo, String nivel, String descripcion) {
        this.id = id;
        this.tipo = tipo;
        this.nivel = nivel;
        this.descripcion=descripcion;   
    }
    
    
    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNivel() {
        return nivel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
       public String toString() {
           return        tipo + " | Nivel: " + nivel + " | " + descripcion;
       }
       
       
}

