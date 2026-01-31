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
    private static Random r = new Random();

    // ===== FALLOS PRINCIPALES =====
    private static String[][] fallos = {
        {"Disturbios masivos", "CRÍTICO", "Perdoda de control de las personas"},
        {"Robo organizado", "MEDIO", "Miedo en la población"},
        {"Incidente aislado", "BAJO", "Ingresos delictivos de gente"}      
    };
    
    // ===== cascadas =====
    private static String[][] cascadas = {
          {"Centros comerciales llenos", "CRÍTICO", "Aglomeración de personas"},
          {"Robo a mano aramada", "CRÍTICO", "Estado peligroso en la ciudad"},
          {"Metro detenido", "MEDIO", "Transporte suspendido"},
          {"Agua interrumpida", "MEDIO", "Bombeo detenido"}
      };
}
