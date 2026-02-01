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
    private static Random r = new Random();

    // ===== FALLOS PRINCIPALES (Afectan solo al AGUA) =====
    private static String[][] fallos = {
        {"Contaminacion agua", "CRÍTICO", "Salud colapsara"},
        {"Presion baja ", "MEDIO", "Salud afectada"},
        {"AGUA suspension sectorial", "BAJO", "Molestias locales"}
    };

    // ===== CASCADAS  =====
    private static String[][] cascadas = {
          {"SALUD pública riesgo", "MEDIO", "Infecciones posibles"},   
          {"Disturbios por sed", "MEDIO", "Protestas en calles"},     
          {"Limpieza Metro parada", "BAJO", "Suciedad en transporte"} 
    };

    public static Evento generarFallo(int id) {
        int i = r.nextInt(fallos.length);
        return new Evento(id, fallos[i][0], fallos[i][1], fallos[i][2]);
    }

    public static Evento generarCascada(int id) {
        int i = r.nextInt(cascadas.length);
        // El primer parámetro (cascadas[i][0]) es el TIPO.
        // Al pasar "Hospitales sin agua", la vista lo detectará como daño a SALUD.
        return new Evento(
            id,
            cascadas[i][0], 
            cascadas[i][1],
            "Problema derivado del suministro de agua"
        );
    }
}