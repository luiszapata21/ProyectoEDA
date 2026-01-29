/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Random;
import modelo.Evento;

/**
 *
 * @author ASUS
 */
public class FallosElectricos {
    private static Random r = new Random();

    // ===== FALLOS PRINCIPALES =====
    private static String[][] fallos = {
        {"Apagón total", "CRÍTICO", "Apagón total en la ciudad"},
        {"Corte parcial", "MEDIO", "Corte en varios sectores"},
        {"Sobrecarga", "BAJO", "Fallo en cableado"}
    };

    // ===== cascadas =====
    private static String[][] cascadas = {
          {"Hospitales sin energía", "CRÍTICO", "Hospitales afectados"},
          {"Semáforos apagados", "CRÍTICO", "Caos vehicular"},
          {"Metro detenido", "MEDIO", "Transporte suspendido"},
          {"Agua interrumpida", "MEDIO", "Bombeo detenido"}
      };

    // ===== FALL0 PRINCIPAL ALEATORIO =====
    public static Evento generarFallo(int id) {
        int i = r.nextInt(fallos.length);
        return new Evento(
            id,
            fallos[i][0],
            fallos[i][1],
            fallos[i][2]
        );
    }

    // ===== UNA CASCADA ALEATORIA =====
    public static Evento generarCascada(int id) {
        int i = r.nextInt(cascadas.length);
        return new Evento(
            id,
            cascadas[i][0],
            cascadas[i][1],
            "Problema derivado del fallo eléctrico"
        );
    }
}
