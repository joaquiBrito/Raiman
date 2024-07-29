package Servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Map<Integer, Integer> puntajesRepetidos = new HashMap<>();
        List<Integer> posiblesPuntajes = new ArrayList<>();
        HashMap<String, List<Integer>> CartasYPuntos;
        for (Map.Entry<String, HashMap<String, List<Integer>>> palo : Mazo.entrySet()) {
            HashMap<String, List<Integer>> cartas = palo.getValue();
            for (Map.Entry<String, List<Integer>> carta : cartas.entrySet()) {

                String nombreCarta = carta.getKey();
                List<Integer> valores = carta.getValue();
                System.out.println("carta:" + carta);
                for (int valor : valores) {
                    if (valor <= puntajeNecesario) {

                        posiblesPuntajes.add(valor);
                        puntajesRepetidos.put(valor, puntajesRepetidos.getOrDefault(valor, 0) + 1);
                    }
                }
            }

        }
        System.out.println("cantidad de cartas :" + posiblesPuntajes.size());
        System.out.println("cantidad de cartas: " + contarCartas(Mazo));
        double probabilidad = (((double) posiblesPuntajes.size()) / contarCartas(Mazo)) * 100;
        System.out.println("la probabilidad de no pasarme de 21 del  " + probabilidad + "%");
        System.out.println("Posibles puntajes que suman al puntaje necesario: " + posiblesPuntajes);
        System.out.println("Repeticiones de puntajes:");
        for (Map.Entry<Integer, Integer> entry : puntajesRepetidos.entrySet()) {
            System.out.println("Puntaje: " + entry.getKey() + ", Repeticiones: " + entry.getValue());
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
