/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Random;

/**
 *
 * @author lagui
 */
public class BrechaSeguridad {
    private static Random s = new Random(); // Objeto Random para seleccionar fallos y cascadas de forma aleatoria

    // ===== FALLOS PRINCIPALES =====
    // Matriz que almacena los eventos principales de brecha de seguridad
    // Cada fila contiene:
    // [0] Nombre del evento
    // [1] Nivel de gravedad
    // [2] Descripción del impacto
    private static String[][] fallosP = {
        {"Disturbios masivos", "CRÍTICO", "Perdida de control de las personas"},
        {"Robo organizado", "MEDIO", "Miedo en la población"},
        {"Incidente aislado", "BAJO", "Ingresos delictivos de gente"}      
    };
    
    // ===== Cascadas =====
    // Matriz que almacena los eventos derivados de una brecha de seguridad
    // Estos eventos se generan a partir del fallo principal
    private static String[][] cascadasP = {
          {"Centros comerciales llenos", "CRÍTICO", "Aglomeración de personas"},
          {"Robo a mano armada", "CRÍTICO", "Estado peligroso en la ciudad"},
          {"Miedo Ciudadano", "MEDIO", "Ciudad inestable"},
          {"Negocios desprotegidos", "MEDIO", "Vacunadores"}
    };
    
    public static Evento generarFalloP(int id){  // Método que genera un evento principal de brecha de seguridad
        int i = s.nextInt(fallosP.length); // Selecciona aleatoriamente uno de los fallos principales
        return new Evento(  // Crea y retorna un nuevo objeto Evento con los datos seleccionados
        id,
        fallosP[i][0], // Nombre del evento
        fallosP[i][1], //Nivel de gravedad
        fallosP[i][2] // Descripción del evento
        );
    }
    
    public static Evento generarCascadaP(int id, String nivelFallo){ // Método que genera un evento derivado (cascada) a partir del nivel
    //de gravedad del fallo principal
         int i;
        while (true) { // Se repite hasta encontrar una cascada compatible con el nivel del fallo
            i = s.nextInt(cascadasP.length); // Selección aleatoria de una posible cascada
            String nivel = cascadasP[i][1];
            if (nivelFallo.equals("CRÍTICO")) break; // Si el fallo principal es CRÍTICO, se permite cualquier cascada
            if (nivelFallo.equals("MEDIO") && nivel.equals("MEDIO")) break; // Si el fallo es MEDIO, solo se permiten cascadas de nivel MEDIO
            if (nivelFallo.equals("BAJO") && nivel.equals("BAJO")) break; // Si el fallo es BAJO, solo se permiten cascadas de nivel BAJO
        }
        return new Evento(  // Se crea y retorna el evento en cascada
            id,
            cascadasP[i][0], // Nombre del evento en cascada
            cascadasP[i][1], // Nivel de gravedad
            "Problema derivado de la brecha de seguridad"
        );
    }
}
