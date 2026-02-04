/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.Random;

/**
 * 
 * @author Iza
 */
public class SuministroAgua {
    private static Random r = new Random(); // Objeto Random para seleccionar fallos y cascadas de forma aleatoria

    // ===== FALLOS PRINCIPALES (Afectan solo al AGUA) =====
    // Matriz que almacena los eventos principales de suministro de agua
    // Cada fila contiene:
    // [0] Nombre del evento
    // [1] Nivel de gravedad
    // [2] Descripción del impacto
    private static String[][] fallos = {
        {"Contaminacion agua", "CRÍTICO", "Salud colapsara"},
        {"Presion baja ", "MEDIO", "Salud afectada"},
        {"AGUA suspension sectorial", "BAJO", "Molestias locales"}
    };

    // ===== CASCADAS  =====
    // Matriz que almacena los eventos derivados de un suministro de agua
    // Estos eventos se generan a partir del fallo principal
    private static String[][] cascadas = {
          {"SALUD pública riesgo", "CRÍTICO", "Infecciones posibles"},   
          {"Disturbios por sed", "MEDIO", "Protestas en calles"},     
          {"Limpieza Metro parada", "BAJO", "Suciedad en transporte"} 
    };

    public static Evento generarFallo(int id) { // Método que genera un evento principal de suminsitro de agua
        int i = r.nextInt(fallos.length); // Selecciona aleatoriamente uno de los fallos principales
        return new Evento(id, fallos[i][0], fallos[i][1], fallos[i][2]); // Crea y retorna un nuevo objeto Evento con los datos seleccionados
    }

    public static Evento generarCascada(int id, String nivelFallo) { // Método que genera un evento derivado (cascada) a partir del nivel
        int i;
        while (true) { // Se repite hasta encontrar una cascada compatible con el nivel del fallo
            i = r.nextInt(cascadas.length); // Selección aleatoria de una posible cascada
            String nivel = cascadas[i][1];

            if (nivelFallo.equals("CRÍTICO")) break; // Si el fallo principal es CRÍTICO, se permite cualquier cascada
            if (nivelFallo.equals("MEDIO") && !nivel.equals("CRÍTICO")) break; // Si el fallo es MEDIO, solo se permiten cascadas de nivel MEDIO
            if (nivelFallo.equals("BAJO") && nivel.equals("BAJO")) break; // Si el fallo es BAJO, solo se permiten cascadas de nivel BAJO
        }

        return new Evento( // Se crea y retorna el evento en cascada
            id,
            cascadas[i][0], // Nombre del evento en cascada
            cascadas[i][1], // Nivel de gravedad
            "Problema derivado del suministro de agua"
        );
    }
}