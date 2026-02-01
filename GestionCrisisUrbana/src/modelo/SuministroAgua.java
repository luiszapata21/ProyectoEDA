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
          {"SALUD pública riesgo", "CRÍTICO", "Infecciones posibles"},   
          {"Disturbios por sed", "MEDIO", "Protestas en calles"},     
          {"Limpieza Metro parada", "BAJO", "Suciedad en transporte"} 
    };

    public static Evento generarFallo(int id) {
        int i = r.nextInt(fallos.length);
        return new Evento(id, fallos[i][0], fallos[i][1], fallos[i][2]);
    }

    public static Evento generarCascada(int id, String nivelFallo) {
        int i;
        while (true) {
            i = r.nextInt(cascadas.length);
            String nivel = cascadas[i][1];

            if (nivelFallo.equals("CRÍTICO")) break;
            if (nivelFallo.equals("MEDIO") && !nivel.equals("CRÍTICO")) break;
            if (nivelFallo.equals("BAJO") && nivel.equals("BAJO")) break;
        }

        return new Evento(
            id,
            cascadas[i][0],
            cascadas[i][1],
            "Problema derivado del suministro de agua"
        );
    }
}