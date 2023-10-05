package Lista;

import Lista.modelo.ListaTareas;

public class Main {
    public static void main(String[] args) {
        ListasTareas listasTareas = new ListasTareas();
        Lector lector = new Lector();

        while (true) {
            System.out.println("Menú de Opciones:");
            System.out.println("1. Crear nueva lista de tareas");
            System.out.println("2. Ver listas de tareas");
            System.out.println("3. Ver tareas de lista");
            System.out.println("4. Actualizar lista de tareas");
            System.out.println("5. Eliminar lista de tareas");
            System.out.println("6. Guardar y Salir"); // Modificar la última opción del menú
            System.out.print("Elija una opción: ");

            int opcion = lector.leerOpcion();

            switch (opcion) {
                case 1:
                    listasTareas.crearListaTareas();
                    break;

                case 2:
                    listasTareas.verListasCreadas();
                    break;

                case 3:
                    listasTareas.verTareasDeLaLista();
                    break;

                case 4:
                    listasTareas.gestionarTareas();
                    break;

                case 5:
                    listasTareas.eliminarListaTareas();
                    break;

                case 6:
                    listasTareas.guardarListaTareas();
                    System.out.println("Guardando y saliendo del programa.");
                    System.exit(0);
                    break;

                case 7:
                    System.out.println("Saliendo del programa sin guardar.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, elija una opción válida.");
            }
        }
    }

}
