package Juego;

import java.util.ArrayList;
import java.util.Scanner;

public class Jugador {

    public char[][] GraficoTablero;
    private Tablero MapaEnemigo;
    public ArrayList<Barco> PosicionBarcos;
    public int numBarcosRestantes;

    public Jugador() {
        MapaEnemigo = new Tablero();
        PosicionBarcos = new ArrayList<>();
        numBarcosRestantes = 0;
        GraficoTablero = new char[10][10];
    }

    public void CrearFlota() {
        int num5 = 1,
                num4 = 2,
                num3 = 3,
                num2 = 4;

        Barco barco = new Barco();

        while (num5 != 0 || num4 != 0 || num3 != 0 || num2 != 0) {
            System.out.println("Numeros de Barcos por crear");
            System.out.println("| 5 Casillas: " + num5
                    + "| 4 Casillas: " + num4
                    + "| 3 Casillas: " + num3
                    + "| 2 Casillas: " + num2);
            int num=-1;
            while (ComprobarBarcoMismaPosicion(barco)){
                num = barco.CrearBarco();
            }
            if (num >= 2 && num <= 5) {
                switch (num) {
                    case 5: {
                        if (num5 > 0) {
                            num5--;
                            PosicionBarcos.add(barco);
                            numBarcosRestantes++;
                        } else {
                            System.out.println("Ya has puesto todos los barcos de ese tamaño");
                        }

                        break;
                    }

                    case 4: {
                        if (num4 > 0) {
                            num4--;
                            PosicionBarcos.add(barco);
                            numBarcosRestantes++;
                        } else {
                            System.out.println("Ya has puesto todos los barcos de ese tamaño");
                        }
                        break;
                    }

                    case 3: {
                        if (num3 > 0) {
                            num3--;
                            PosicionBarcos.add(barco);
                            numBarcosRestantes++;
                        } else {
                            System.out.println("Ya has puesto todos los barcos de ese tamaño");
                        }
                        break;
                    }

                    case 2: {
                        if (num2 > 0) {
                            num2--;
                            PosicionBarcos.add(barco);
                            numBarcosRestantes++;
                        } else {
                            System.out.println("Ya has puesto todos los barcos de ese tamaño");
                        }
                        break;
                    }

                    default:
                        System.out.println("Barco erroneo");
                }
                System.out.println("barco creado");
            }
//            else {
//                System.out.println("Tamaño del Barco no valido o Barco diagonal no permitido");
//            }
        }
    }
 
    public void CrearFlotaAuto() throws InterruptedException {
        Barco barco;
        int numcreados = 0;
        for (int i = 0; i < 1; i++) {
            barco = new Barco(5);
            PosicionBarcos.add(barco);
//            System.out.println("barco 5 creado auto");
            numBarcosRestantes++;
        }

        //Crea barcos y comprueba que no exista ningun otro barco en esa posicion
        while (numcreados != 2) {
            barco = new Barco(4);
            if (!ComprobarBarcoMismaPosicion(barco)) {
                PosicionBarcos.add(barco);
//                System.out.println("barco 4 creado auto");
                numcreados++;
                numBarcosRestantes++;
            }
//            else {
//                System.out.println("Barco descartado");
//            }
        }
        numcreados = 0;

        while (numcreados != 3) {
            barco = new Barco(3);
            if (!ComprobarBarcoMismaPosicion(barco)) {
                PosicionBarcos.add(barco);
//                System.out.println("barco 3 creado auto");
                numcreados++;
                numBarcosRestantes++;
            }
//            else {
//                System.out.println("Barco descartado");
//            }
        }
        numcreados = 0;

        while (numcreados != 4) {
            barco = new Barco(2);
            if (!ComprobarBarcoMismaPosicion(barco)) {
                PosicionBarcos.add(barco);
//                System.out.println("barco 2 creado auto");
                numcreados++;
                numBarcosRestantes++;
            }
//            else {
//                System.out.println("Barco descartado");
//            }
        }
    }

    public boolean ComprobarBarcoMismaPosicion(Barco barco) {

        boolean found = false;

        for (int i = 0; i < PosicionBarcos.size() && !found; i++) {
            Barco b = PosicionBarcos.get(i);
            //Vertical
            if (b.orientacion == 1) {
                //Comprueba las posiciones de los barcos existentes
                for (int j = 0; j < b.lenght; j++) {
                    //Comprueba las posiciones del barco pasado por parametro
                    for (int k = 0; k < barco.lenght; k++) {
                        //Barco parametro Vertical
                        if (barco.orientacion == 1) {
                            if (b.posicion_ini.X == barco.posicion_ini.X
                                    && b.posicion_ini.Y + j == barco.posicion_ini.Y + k) {
                                found = true;
                            }
                        } //Barco parametro Horizontal
                        else {
                            if (b.posicion_ini.X == barco.posicion_ini.X
                                    && b.posicion_ini.Y + j == barco.posicion_ini.Y + k) {
                                found = true;
                            }
                        }
                    }
                }
            } //Horizontal
            else {
                //Comprueba las posiciones de los barcos existentes
                for (int j = 0; j < b.lenght; j++) {
                    //Comprueba las posiciones del barco pasado por parametro
                    for (int k = 0; k < barco.lenght; k++) {
                        //Barco parametro Vertical
                        if (barco.orientacion == 1) {
                            if (b.posicion_ini.X + j == barco.posicion_ini.X + k
                                    && b.posicion_ini.Y == barco.posicion_ini.Y) {
                                found = true;
                            }
                        } //Barco parametro Horizontal
                        else {
                            if (b.posicion_ini.X + j == barco.posicion_ini.X + k
                                    && b.posicion_ini.Y == barco.posicion_ini.Y) {
                                found = true;
                            }
                        }
                    }
                }
            }
        }

        return found;
    }

   
    public void MostrarGraficoTablero() {
        for (int i = 0; i < PosicionBarcos.size(); i++) {
            Barco b = PosicionBarcos.get(i);

//            System.out.println("(" + b.posicion_ini.Y + "," + b.posicion_ini.X + ")" + "(" + b.posicion_fin.Y + "," + b.posicion_fin.X + ") - " + b.lenght);
            //Vertical
            if (b.orientacion == 1) {
                for (int j = 0; j < b.lenght; j++) {
                    GraficoTablero[b.posicion_ini.Y + j][b.posicion_ini.X] = 'B';
                }
            } //Horizontal
            else {
                for (int j = 0; j < b.lenght; j++) {
                    GraficoTablero[b.posicion_ini.Y][b.posicion_ini.X + j] = 'B';
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("");
            for (int j = 0; j < 10; j++) {
                if (GraficoTablero[i][j] != 'B') {
                    GraficoTablero[i][j] = '*';
                }
                System.out.print(GraficoTablero[i][j]);
            }
        }

    }

    //Crea otro jugador que usará los metodos auto
    public void JugarPartida() throws InterruptedException {
        Jugador maquina = new Jugador();
        Scanner entradaEscaner = new Scanner(System.in);

        //Crea los barcos
        int opc = -1;

        while (opc != 1 && opc != 0) {
            System.out.println("Creacion Manual o Automatica(1/0)");
            opc = entradaEscaner.nextInt();
        }
        if (opc == 1) {
            CrearFlota();
        } else {
            CrearFlotaAuto();
        }
        System.out.println("\nFlota Creada");
        maquina.CrearFlotaAuto();
        System.out.println("\nFlota Enemiga Creada");

        //Rellena los barcos del tablero con los barcos del otro jugador para poder comprobar los disparos
        MapaEnemigo.UbicarBarcosEnemigo(maquina.PosicionBarcos);
        maquina.MapaEnemigo.UbicarBarcosEnemigo(PosicionBarcos);

        //Mientras existan barcos el juego se repite
        while (numBarcosRestantes != 0 && maquina.numBarcosRestantes != 0) {
            System.out.println("");
            MostrarGraficoTablero();
            //Disparas Tu
            opc = -1;
            while (opc != 1 && opc != 0) {
                System.out.println("Disparo Manual o Automatico(1/0)");
                opc = entradaEscaner.nextInt();
            }
            //Manual
            if (opc == 1) {
                Posicion p = new Posicion();

                System.out.println("Posicion X");
                p.X = entradaEscaner.nextInt();

                System.out.println("Posicion Y");
                p.Y = entradaEscaner.nextInt();

                switch (MapaEnemigo.Disparar(p)) {
                    case 0: {
                        System.out.println("Tu: Agua");
                        break;
                    }
                    case 1: {
                        System.out.println("Tu: Tocado");
                        break;
                    }
                    case 2: {
                        System.out.println("Tu: Hundido");
                        maquina.numBarcosRestantes--;
                        break;
                    }
                    default:
                        break;
                }
            } //Automatico
            else {
                switch (MapaEnemigo.DispararAuto()) {
                    case 0: {
                        System.out.println("Tu (Auto): Agua");
                        break;
                    }
                    case 1: {
                        System.out.println("Tu (Auto): Tocado");
                        break;
                    }
                    case 2: {
                        System.out.println("Tu (Auto): Hundido");
                        numBarcosRestantes--;
                        break;
                    }
                    default:
                        break;
                }
            }

            //Dispara la maquina
            switch (maquina.MapaEnemigo.DispararAuto()) {
                case 0: {
                    System.out.println("Enemigo: Agua");
                    break;
                }
                case 1: {
                    System.out.println("Enemigo: Tocado");
                    break;
                }
                case 2: {
                    System.out.println("Enemigo: Hundido");
                    numBarcosRestantes--;
                    break;
                }
                default:
                    break;
            }
        }
    }

    public void ComprobarPartidaExhaustivo() throws InterruptedException {
        Jugador maquina = new Jugador();

        //Crea los barcos
        CrearFlotaAuto();

        System.out.println("\nFlota Creada");
        maquina.CrearFlotaAuto();
        System.out.println("\nFlota Enemiga Creada");

        //Rellena los barcos del tablero con los barcos del otro jugador para poder comprobar los disparos
        MapaEnemigo.UbicarBarcosEnemigo(maquina.PosicionBarcos);
        maquina.MapaEnemigo.UbicarBarcosEnemigo(PosicionBarcos);

        //Mientras existan barcos el juego se repite
        System.out.println("");
        MostrarGraficoTablero();
        
        //Disparas Tu
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Posicion p = new Posicion();
                p.X = i;
                p.Y = j;
                switch (MapaEnemigo.Disparar(p)) {
                    case 0: {
                        System.out.println("Tu: Agua");
                        break;
                    }
                    case 1: {
                        System.out.println("Tu: Tocado");
                        break;
                    }
                    case 2: {
                        System.out.println("Tu: Hundido");
                        maquina.numBarcosRestantes--;
                        System.out.println("Barcos restantes: " + maquina.numBarcosRestantes);
                        break;
                    }
                    default:
                        break;
                }
            }
        }
        System.out.println("FIN");
    }

    public static void main(String[] args) throws InterruptedException {
        Jugador juego = new Jugador();
//        juego.CrearFlota(); //Funciona
//        juego.CrearFlotaAuto(); //Funciona
//        juego.MostrarGraficoTablero(); //Funciona
//        juego.JugarPartida();
        juego.ComprobarPartidaExhaustivo();
    }
}
