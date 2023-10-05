package Lista;


public class Main {
    public static void main(String[] args) {
        ListasTareas listasTareas = new ListasTareas();
        Lector lector = new Lector();

        while (true) {
            System.out.println("\n==SELECCIONA UNA OPCIÓN==\n");
            System.out.println("1. Crear nueva lista de tareas");
            System.out.println("2. Ver listas de tareas");
            System.out.println("3. Ver tareas de lista");
            System.out.println("4. Actualizar lista de tareas");
            System.out.println("5. Eliminar lista de tareas");
            System.out.println("6. Guardar lista de tareas en archivo");
            System.out.println("7. Cargar lista de tareas desde archivo");
            System.out.println("8. Salir");
            System.out.print("Elija una opción: ");
            System.out.println("\n");

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
                    System.out.println("Guardando lista de tareas en archivo.");
                    break;

                case 7:
                    System.out.print("Ingrese la ruta del archivo a cargar: ");
                    String rutaArchivo = lector.leerTexto();
                    listasTareas.cargarListaTareasDesdeArchivo(rutaArchivo);
                    break;

                case 8:
                    System.out.println("Saliendo del programa.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, elija una opción válida.");
            }
        }
    }
}
