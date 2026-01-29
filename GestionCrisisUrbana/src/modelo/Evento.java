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
    private LocalTime hora;

    public Evento(int id, String tipo, String nivel, String descripcion) {
        this.id = id;
        this.tipo = tipo;
        this.nivel = nivel;
        this.descripcion=descripcion;
        this.hora = LocalTime.now();
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

    public LocalTime getHora() {
        return hora;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    
    

    @Override
       public String toString() {
            //"[" + hora + "] 
           return        tipo + " | Nivel: " + nivel + " | " + descripcion;
       }
       
       
       
       
       
       
       
       
       
       
       /*





       ESTO ES UNA PRUEBA XD

**
       
       
       
       
       
       
       
   */ 
}

