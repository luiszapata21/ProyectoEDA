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
    private int id; //Identificador unico del evento
    private String tipo;        // Tipo de evento (eléctrico, agia, salu, etc.)
    private String nivel;          // Nivel de gravedad (CRÍTICO, MEDIO, BAJO)
    private String descripcion; //Descripción del evento

    //Permite crear un nuevo evento asignando sus atributos principales
    public Evento(int id, String tipo, String nivel, String descripcion) {
        this.id = id;
        this.tipo = tipo;
        this.nivel = nivel;
        this.descripcion=descripcion;   
    }
    
    
    public int getId() { //Método que retorna el identificador del evento
        return id;
    }

    public String getTipo() { //Método que retorna el tipo de evento
        return tipo;
    }

    public String getNivel() { //Método que retorna el nivel de gravedad del evento
        return nivel;
    }

    public String getDescripcion() { //Método que retorna la descripción del evento
        return descripcion;
    }

    @Override
       public String toString() {
           return        tipo + " | Nivel: " + nivel + " | " + descripcion;
       }
       
       
}

