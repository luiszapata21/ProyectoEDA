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
public class ColapsoVial {
    private static Random c = new Random(); // Objeto Random para seleccionar fallos y cascadas de forma aleatoria
    
    // ===== FALLOS PRINCIPALES =====
    // Matriz que almacena los eventos principales de colapso vial
    // Cada fila contiene:
    // [0] Nombre del evento
    // [1] Nivel de gravedad
    // [2] Descripción del impacto
    private static String[][] fallosC = {
        {"Colapso vial", "CRÍTICO", "Congestión masiva"},
        {"Semáforos Dañados", "MEDIO", "Cierre de vías"},
        {"Congestión leve", "BAJO", "Tráfico concurrente"}      
    };
    
    // ===== cascadas =====
    // Matriz que almacena los eventos derivados de un colapso vial
    // Estos eventos se generan a partir del fallo principal
    private static String[][] cascadasC = {
          {"Robo a mano armada", "CRÍTICO", "Gente en la calle"},
          {"Múltiples accidentes", "CRÍTICO", "Personas heridas"},
          {"Sistema de semáforos dañado", "MEDIO", "Inmobilidad de carros"},
          {"Malestar social", "MEDIO", "Accidentes concurrentes"}
    };
    
    public static Evento generarFalloC(int id){ // Método que genera un evento principal de colapso vial
        int i = c.nextInt(fallosC.length); // Selecciona aleatoriamente uno de los fallos principales
        return new Evento( // Crea y retorna un nuevo objeto Evento con los datos seleccionados
        id,
        fallosC[i][0], // Nombre del evento
        fallosC[i][1], //Nivel de gravedad
        fallosC[i][2] //Descripción del evento
        );
    }
    
    public static Evento generarCascadaP(int id, String nivelFallo){ // Método que genera un evento derivado (cascada) a partir del nivel
        int i;
        while (true) { // Se repite hasta encontrar una cascada compatible con el nivel del fallo
            i = c.nextInt(cascadasC.length); // Selección aleatoria de una posible cascada
            String nivel = cascadasC[i][1];
            if (nivelFallo.equals("CRÍTICO")) break; // Si el fallo principal es CRÍTICO, se permite cualquier cascada
            if (nivelFallo.equals("MEDIO") && nivel.equals("MEDIO")) break; // Si el fallo es MEDIO, solo se permiten cascadas de nivel MEDIO
            if (nivelFallo.equals("BAJO") && nivel.equals("BAJO")) break; // Si el fallo es BAJO, solo se permiten cascadas de nivel BAJO
        }
        return new Evento( // Se crea y retorna el evento en cascada
            id,
            cascadasC[i][0], // Nombre del evento en cascada
            cascadasC[i][1], // Nivel de gravedad
            "Problema derivado del colapso vial"
        );
    }
}
