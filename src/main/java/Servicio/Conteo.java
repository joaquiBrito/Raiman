package Servicio;

import java.util.*;

public class Conteo {
    public HashMap robarCarta(HashMap<String, HashMap<String, List<Integer>>> Mazo, String palo, String carta) {
        HashMap<String, List<Integer>> cartas = Mazo.get(palo);
        if (cartas != null) {
            if (cartas.remove(carta) != null) {
            } else {
                System.out.println("La carta '" + carta + "' no se encuentra en el palo '" + palo + "'.");
            }
        } else {
            System.out.println("El palo '" + palo + "' no se encuentra en el mazo.");
        }
        return Mazo;
    }

    public void probabilidad(HashMap<String, HashMap<String, List<Integer>>> Mazo, int puntaje) {
        int puntajeMaximo = 21;
        int puntajeNecesario = puntajeMaximo - puntaje;
        Map<String, Integer> cartasRepetidas = new HashMap<>();
        List<Integer> posiblesPuntajes = new ArrayList<>();
        HashMap<String, Double> ProbabilidadPorCarta = new HashMap<>();
        double probabilidaCarta;
        for (Map.Entry<String, HashMap<String, List<Integer>>> palo : Mazo.entrySet()) {
            HashMap<String, List<Integer>> cartas = palo.getValue();
            for (Map.Entry<String, List<Integer>> carta : cartas.entrySet()) {

                String nombreCarta = carta.getKey();
                List<Integer> valores = carta.getValue();
                for (int valor : valores) {
                    if (nombreCarta.equals("As")) {
                        // Si el As puede sumarte 11 sin pasarte, úsalo como 11, de lo contrario úsalo como 1
                        if (puntaje + 11 <= puntajeMaximo) {
                            valor = 11;
                        } else {
                            valor = 1;
                        }
                    }

                    if (valor <= puntajeNecesario) {
                        cartasRepetidas.put(nombreCarta, cartasRepetidas.getOrDefault(nombreCarta, 0) + 1);
                        posiblesPuntajes.add(valor);
                    }
                }
            }

        }
        double probabilidadDeNoPasrDe21 = (((double) posiblesPuntajes.size()) / contarCartas(Mazo)) * 100;
        for (Map.Entry<String, Integer> entry : cartasRepetidas.entrySet()) {
            probabilidaCarta = ((double) (entry.getValue()) / contarCartas(Mazo)) * 100;
            ProbabilidadPorCarta.put(entry.getKey(),probabilidaCarta);


        }
        System.out.println("la probabilidad de no pasarme de 21 del  " + probabilidadDeNoPasrDe21 + "%");
        for (Map.Entry<String, Double> entry : ProbabilidadPorCarta.entrySet()) {
            System.out.println("Carta: " + entry.getKey() + ", Probabilidad De Salir: " + entry.getValue() + "%");
        }
    }

    public int contarCartas(HashMap<String, HashMap<String, List<Integer>>> Mazo) {
        int totalCartas = 0;
        for (Map.Entry<String, HashMap<String, List<Integer>>> palo : Mazo.entrySet()) {
            HashMap<String, List<Integer>> cartas = palo.getValue();
            totalCartas += cartas.size();
        }
        return totalCartas;
    }
}
