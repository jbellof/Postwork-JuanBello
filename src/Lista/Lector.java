package Lista;

import java.util.Scanner;

public class Lector {
    private Scanner scanner;

    public Lector() {
        scanner = new Scanner(System.in);
    }

    public int leerOpcion() {
        int opcion = -1;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Ingrese un número.");
            }
        }

        return opcion;
    }

    public String leerTexto() {
        return scanner.nextLine();
    }
}

