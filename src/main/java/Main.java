import Servicio.Conteo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HashMap<String, List<Integer>> Cartas;
        HashMap<String, HashMap<String, List<Integer>>> Mazo = new HashMap<>();
        String[] valores = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Reina", "Rey" };
        int[] puntaje = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };
        String[] palos = { "C", "D", "T", "E" };
        for (String palo : palos) {
            Cartas = new HashMap<>();
            for (int i = 0; i < valores.length; i++) {
                List<Integer> puntajes = new ArrayList<>();
                if (valores[i].equals("A")) {
                    puntajes.add(1);
                    puntajes.add(puntaje[i]);
                } else {
                    puntajes.add(puntaje[i]);
                }
                Cartas.put(valores[i], puntajes);
            }
            Mazo.put(palo, Cartas);
        }

        Conteo c = new  Conteo();


        c.probabilidad(Mazo, 10);
    }
}