/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Random;

/**
 *
 * @author ASUS
 */
public class FallosElectricos {
    private static Random r = new Random(); // Objeto Random para seleccionar fallos y cascadas de forma aleatoria

    // ===== FALLOS PRINCIPALES =====
    // Matriz que almacena los eventos principales de fallos eléctricos
    // Cada fila contiene:
    // [0] Nombre del evento
    // [1] Nivel de gravedad
    // [2] Descripción del impacto
    private static String[][] fallos = {
        {"Apagón total", "CRÍTICO", "Apagón total en la ciudad"},
        {"Corte parcial", "MEDIO", "Corte en varios sectores"},
        {"Sobrecarga", "BAJO", "Fallo en cableado"}
    };

    // ===== cascadas =====
    // Matriz que almacena los eventos derivados de un fallo eléctrico
    // Estos eventos se generan a partir del fallo principal
    private static String[][] cascadas = {
          {"Hospitales sin energía", "CRÍTICO", "Hospitales afectados"},
          {"Semáforos apagados", "CRÍTICO", "Caos vehicular"},
          {"Metro detenido", "MEDIO", "Transporte suspendido"},
          {"Agua interrumpida", "MEDIO", "Bombeo detenido"}
    };

    // ===== FALL0 PRINCIPAL ALEATORIO =====
    public static Evento generarFallo(int id) { // Método que genera un evento principal de fallo eléctrico
        int i = r.nextInt(fallos.length); // Selecciona aleatoriamente uno de los fallos principales
        return new Evento( // Crea y retorna un nuevo objeto Evento con los datos seleccionados
            id,
            fallos[i][0], // Nombre del evento
            fallos[i][1], //Nivel de gravedad
            fallos[i][2] //Descripción del evento
        );
    }

    // ===== UNA CASCADA ALEATORIA -----
    public static Evento generarCascada(int id, String nivelFallo) { // Método que genera un evento derivado (cascada) a partir del nivel
        int i;
        while (true) { // Se repite hasta encontrar una cascada compatible con el nivel del fallo
            i = r.nextInt(cascadas.length); // Selección aleatoria de una posible cascada
            String nivelCascada = cascadas[i][1]; 
            if (nivelFallo.equals("CRÍTICO")) break; // Si el fallo principal es CRÍTICO, se permite cualquier cascada
            if (nivelFallo.equals("MEDIO") && nivelCascada.equals("MEDIO")) break; // Si el fallo es MEDIO, solo se permiten cascadas de nivel MEDIO
            if (nivelFallo.equals("BAJO") && nivelCascada.equals("MEDIO")) break; // Si el fallo es BAJO, solo se permiten cascadas de nivel BAJO
        }
        return new Evento( // Se crea y retorna el evento en cascada
            id,
            cascadas[i][0], // Nombre del evento en cascada
            cascadas[i][1], // Nivel de gravedad
            "Problema derivado del fallo eléctrico"
        );
    }
}
