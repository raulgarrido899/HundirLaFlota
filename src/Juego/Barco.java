package Juego;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Barco {

    public Posicion posicion_ini;
    public Posicion posicion_fin;
    public int lenght; //Tamaño del barco
    public int orientacion;

//Crea un objeto barco vacio
    public Barco() {
        posicion_ini = new Posicion();
        posicion_fin = new Posicion();
        lenght = 0;
        orientacion = -1;
    }
    //Crea barcos de forma aleatoria
    public Barco(int longitud) throws InterruptedException {
        posicion_ini = new Posicion();
        posicion_fin = new Posicion();
        
        Random aleatorio = new Random(System.currentTimeMillis());
        orientacion = aleatorio.nextInt(1); //0 Horizontal, 1 Vertical
        lenght = longitud;
        boolean valido = false;

        while (!valido) {
            Thread.sleep(50);
            posicion_ini.X = aleatorio.nextInt(10) + 1;
            aleatorio.setSeed(System.currentTimeMillis() + 8);
            posicion_ini.Y = aleatorio.nextInt(10);
            switch (longitud) {
                case 2: {
                    if (orientacion == 1) {
                        posicion_fin.X = posicion_ini.X;
                        posicion_fin.Y = posicion_ini.Y + 1;
                    } else {
                        posicion_fin.X = posicion_ini.X + 1;
                        posicion_fin.Y = posicion_ini.Y;
                    }
                    break;
                }
                case 3: {
                    if (orientacion == 1) {
                        posicion_fin.X = posicion_ini.X;
                        posicion_fin.Y = posicion_ini.Y + 2;
                    } else {
                        posicion_fin.X = posicion_ini.X + 2;
                        posicion_fin.Y = posicion_ini.Y;
                    }
                    break;
                }
                case 4: {
                    if (orientacion == 1) {
                        posicion_fin.X = posicion_ini.X;
                        posicion_fin.Y = posicion_ini.Y + 3;
                    } else {
                        posicion_fin.X = posicion_ini.X + 3;
                        posicion_fin.Y = posicion_ini.Y;
                    }
                    break;
                }
                case 5: {
                    if (orientacion == 1) {
                        posicion_fin.X = posicion_ini.X;
                        posicion_fin.Y = posicion_ini.Y + 4;
                    } else {
                        posicion_fin.X = posicion_ini.X + 4;
                        posicion_fin.Y = posicion_ini.Y;
                    }
                    break;
                }
                default: {
                    System.out.println("Longitud no permitida");
                    break;
                }
            }

            if (posicion_ini.X > 9 || posicion_ini.Y > 9 || posicion_fin.X > 9 || posicion_fin.Y > 9) {
                valido = false;
            } else {
                valido = true;
            }
        }

//        System.out.println("Variables para crear un barco: (" + posicion_ini.X + "," + posicion_ini.Y + ")"
//                + " (" + posicion_fin.X + "," + posicion_fin.Y + ")");
    }

    //Crea el barco preguntando por pantalla donde crearlo
    //No barcos diagonales
    public int CrearBarco() {
        Scanner entradaEscaner = new Scanner(System.in);

        System.out.println("Posicion X inicial");
        posicion_ini.X = entradaEscaner.nextInt();

        System.out.println("Posicion Y inicial");
        posicion_ini.Y = entradaEscaner.nextInt();

        System.out.println("Posicion X final");
        posicion_fin.X = entradaEscaner.nextInt();

        System.out.println("Posicion Y final");
        posicion_fin.Y = entradaEscaner.nextInt();

        if (posicion_ini.X == posicion_fin.X) {
            //El barco está orientado verticalmente (X no cambia)
            lenght = posicion_fin.Y - posicion_ini.Y + 1;
            orientacion = 1;
        } else {
            //El barco está orientado Horizontalmente (Y no cambia)
            lenght = posicion_fin.X - posicion_ini.X + 1;
            orientacion = 0;
        }

        return lenght;
    }

    //Comprueba si el barco está en la posicion "p"
    //Devuelve true si "p" se encuentra entre las posiciones iniciales y finales independientemente del sentido en el que se haya creado el baroo
    public boolean ComprobarPosicionBarco(Posicion p) {

        return (p.X >= posicion_ini.X) && (p.X <= posicion_fin.X)
                || (p.Y >= posicion_ini.Y) && (p.Y <= posicion_fin.Y);
    }

    //Devuelve true  si el barco está destruido
    //Devuelve false si el barco sigue vivo
    public boolean ComprobarBarco(ArrayList<Posicion> disparos) {
        int disparosAlBarco = 0;
        for (int i = 0; i < disparos.size(); i++) {
            if (ComprobarPosicionBarco(disparos.get(i))) {
                disparosAlBarco++;
            }
        }

        return disparosAlBarco == lenght;
    }

}
