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
        while (!found && i<PosicionBarcosEnemigos.size()-1) {
            if (b.ComprobarPosicionBarco(p)) {
                found = true;
                result = 1;
            } else {
                i++;
                b = PosicionBarcosEnemigos.get(i);
            }
        }
        //Comprueba que si ha hundido el barco
        if (found && b.ComprobarBarco(DisparosRealizados)) {
            result = 2;
        }
        System.out.println("Disparando a (" + p.X + "," + p.Y + ")");
        return result;
    }

    //Genera una posicion aleatoria y dispara
    public int DispararAuto() {
        Posicion p = new Posicion();
        Random aleatorio = new Random(System.currentTimeMillis());

        boolean posible = false;
        while (!posible) {            
            int numero = aleatorio.nextInt(10);
            p.X = numero;
            aleatorio.setSeed(System.currentTimeMillis()+24);
            numero = aleatorio.nextInt(10);
            p.Y = numero;
            
            int cont=0;
            for (int i = 0; i < DisparosRealizados.size() && cont==0; i++) {
                if((DisparosRealizados.get(i).X == p.X) && (DisparosRealizados.get(i).Y == p.Y))
                    cont++;
            }
            if (cont==0)
                posible=true;
        }
        
        return Disparar(p);
    }

}
