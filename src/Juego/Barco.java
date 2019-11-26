package Juego;

import java.util.Scanner;

public class Barco {
    private Posicion posicion_ini;
    private Posicion posicion_fin;
    private int lenght; //Tamaño del barco
    
    public Barco(){
    //Crea un objeto barco vacio
    }
    
    public Barco(Posicion inicial, Posicion ultima){
    //Crea un barco mediante los parametros dados
       posicion_ini.posicionX = inicial.posicionX;
       posicion_ini.posicionY = inicial.posicionY;
       
       posicion_fin.posicionX = ultima.posicionX;
       posicion_fin.posicionY = ultima.posicionY;
       
       lenght = (int) Math.sqrt((ultima.posicionX-inicial.posicionX) + (ultima.posicionY-inicial.posicionY));
    }
    
    public void CrearBarco(){
    //Crea el barco preguntando por pantalla donde crearlo
        Scanner entradaEscaner = new Scanner (System.in);
        
        System.out.println("Posicion X inicial");
        posicion_ini.posicionX=entradaEscaner.nextInt();
        
        System.out.println("Posicion Y inicial");
        posicion_ini.posicionY=entradaEscaner.nextInt();
        
        System.out.println("Posicion X final");
        posicion_fin.posicionX=entradaEscaner.nextInt();
        
        System.out.println("Posicion Y final");
        posicion_fin.posicionY=entradaEscaner.nextInt();
        
        lenght= (int) Math.sqrt((posicion_fin.posicionX-posicion_ini.posicionX) + (posicion_fin.posicionY-posicion_ini.posicionY));
    }
    
    public void ComprobarBarco(){
        
    }
}
