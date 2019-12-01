package Juego;

import java.util.ArrayList;
import java.util.Random;

public class Tablero {

    private ArrayList<Barco> PosicionBarcosEnemigos;
    private ArrayList<Posicion> DisparosRealizados;

    public Tablero() {
        PosicionBarcosEnemigos = new ArrayList<>();
        DisparosRealizados = new ArrayList<>();
    }

    public void UbicarBarcosEnemigo(ArrayList<Barco> barco) {
        PosicionBarcosEnemigos.addAll(barco);
    }

    //Devuelve 0 si el disparo da en agua
    //Devuelve 1 si el disparo da en un barco
    //Devuelve 2 si el disparo hunde el barco
    public int Disparar(Posicion p) {
        DisparosRealizados.add(p);
        int result = 0;

        boolean found = false;
        int i = 0;
        Barco b = PosicionBarcosEnemigos.get(i);
        //Comprueba que ha tocado algun barco
        while (!found) {
            if (b.ComprobarPosicionBarco(p)) {
                found = true;
                result = 1;
            } else {
                i++;
                b = PosicionBarcosEnemigos.get(i);
            }
        }
        //Comprueba que si ha hundido el barco
        if (b.ComprobarBarco(DisparosRealizados)) {
            result = 2;
        }

        return result;
    }

    //Genera una posicion aleatoria y dispara
    public int DispararAuto() {
        Posicion p = new Posicion();
        Random aleatorio = new Random(System.currentTimeMillis());

        do {
            int numero = aleatorio.nextInt(10) + 1;
            p.X = numero;

            aleatorio.setSeed(System.currentTimeMillis());
            numero = aleatorio.nextInt(10) + 1;
            p.Y = numero;
        } while (DisparosRealizados.contains(p));

        return Disparar(p);
    }

}
