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
public class EmergenciaMedica {
    private static Random m = new Random(); // Objeto Random para seleccionar fallos y cascadas de forma aleatoria
    
    // ===== FALLOS PRINCIPALES =====
    // Matriz que almacena los eventos principales de emergencia médica
    // Cada fila contiene:
    // [0] Nombre del evento
    // [1] Nivel de gravedad
    // [2] Descripción del impacto
    private static String[][] fallosM = {
        {"Saturación hospitalaria", "CRÍTICO", "Muerte de pacientes"},
        {"Falta de ambulancias", "MEDIO", "Mayor perdida de victimas"},
        {"Retrasos administrativos", "BAJO", "Molestias en la población"}      
    };
    
     // ===== cascadas =====
    // Matriz que almacena los eventos derivados de una emergencia médica
    // Estos eventos se generan a partir del fallo principal
    private static String[][] cascadasM = {
          {"Atención retrasada", "CRÍTICO", "Mayor colapso hospitalario"},
          {"Camas no disponibles", "CRÍTICO", "Muerte de pacientes"},
          {"Dificultad de traslado", "MEDIO", "Ambulancias en mal estado"},
          {"Propagación de la crisis", "MEDIO", "Mayor cantidad de enfermos"}
    };
    
    public static Evento generarFalloM(int id){ // Método que genera un evento principal de emergencia médica
        int i = m.nextInt(fallosM.length); // Selecciona aleatoriamente uno de los fallos principales
        return new Evento( // Crea y retorna un nuevo objeto Evento con los datos seleccionados
        id,
        fallosM[i][0], // Nombre del evento
        fallosM[i][1], //Nivel de gravedad
        fallosM[i][2] //Descripción del evento
        );
    }
    
    public static Evento generarCascadaM(int id, String nivelFallo){ // Método que genera un evento derivado (cascada) a partir del nivel
         int i;
        while (true) { // Se repite hasta encontrar una cascada compatible con el nivel del fallo
            i = m.nextInt(cascadasM.length); // Selección aleatoria de una posible cascada
            String nivel = cascadasM[i][1];
            if (nivelFallo.equals("CRÍTICO")) break; // Si el fallo principal es CRÍTICO, se permite cualquier cascada
            if (nivelFallo.equals("MEDIO") && nivel.equals("MEDIO")) break; // Si el fallo es MEDIO, solo se permiten cascadas de nivel MEDIO
            if (nivelFallo.equals("BAJO") && nivel.equals("BAJO")) break; // Si el fallo es BAJO, solo se permiten cascadas de nivel BAJO
        }
        return new Evento( // Se crea y retorna el evento en cascada
            id,
            cascadasM[i][0], // Nombre del evento en cascada
            cascadasM[i][1], // Nivel de gravedad
            "Problema derivado de la emergencia médica"
        );
    }
}
