package Lista;
import Lista.modelo.ListaTareas;
import Lista.modelo.Tarea;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.charset.Charset;
public class ListasTareas implements Serializable {
    private List<ListaTareas> listasDeTareas = new ArrayList<>();
    private Lector lector = new Lector();

    public void agregarListaTareas(ListaTareas lista) {
        listasDeTareas.add(lista);
    }

    public void eliminarListaTareas(int indice) {
        if (indice >= 0 && indice < listasDeTareas.size()) {
            listasDeTareas.remove(indice);
        } else {
            System.out.println("Índice no válido. La lista de tareas no ha sido eliminada.");
        }
    }

    public ListaTareas obtenerListaTareas(int indice) {
        if (indice >= 0 && indice < listasDeTareas.size()) {
            return listasDeTareas.get(indice);
        } else {
            System.out.println("Índice no válido. No se pudo obtener la lista de tareas.");
            return null;
        }
    }

    public void mostrarListasTareas() {
        System.out.println("Listas de tareas disponibles:");
        for (int i = 0; i < listasDeTareas.size(); i++) {
            System.out.println(i + ". " + listasDeTareas.get(i).getNombre());
        }
    }

    public void crearListaTareas() {
        System.out.print("Nombre de la nueva lista de tareas: ");
        String nombreLista = lector.leerTexto();
        Date fechaActual = new Date();
        ListaTareas nuevaLista = new ListaTareas(nombreLista, fechaActual);
        agregarListaTareas(nuevaLista);
        System.out.println("Lista de tareas creada.");

        // Pregunta si desea agregar tareas a la lista recién creada
        System.out.print("¿Desea agregar tareas a esta lista? (S/N): ");
        String respuesta = lector.leerTexto();

        if (respuesta.equalsIgnoreCase("S")) {
            while (true) {
                System.out.print("Nombre de la nueva tarea (o 'salir' para volver al menú): ");
                String nuevaTarea = lector.leerTexto();

                if (nuevaTarea.equalsIgnoreCase("salir")) {
                    break; // Finalizar la adición de tareas
                }

                // Solicitar el estado de la tarea (realizada o no)
                boolean realizada = false;
                while (true) {
                    System.out.print("¿La tarea está realizada? (true/false): ");
                    String inputRealizada = lector.leerTexto();
                    if (inputRealizada.equalsIgnoreCase("true")) {
                        realizada = true;
                        break;
                    } else if (inputRealizada.equalsIgnoreCase("false")) {
                        realizada = false;
                        break;
                    } else {
                        System.out.println("Ingrese 'true' o 'false' para indicar si la tarea está realizada.");
                    }
                }

                Date fechaFin = null;

                if (realizada) {
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    while (true) {
                        System.out.print("Ingrese la fecha de finalización de la tarea (dd/MM/yyyy): ");
                        String fechaTerminada = lector.leerTexto();
                        try {
                            fechaFin = formato.parse(fechaTerminada);

                            // Verificar si la fecha de finalización es posterior a la fecha actual
                            if (fechaFin.after(fechaActual)) {
                                break; // Fecha válida, salir del bucle
                            } else {
                                System.out.println("La fecha de finalización debe ser posterior a la fecha actual.");
                            }
                        } catch (ParseException e) {
                            System.out.println("La entrada no es una fecha válida. Asegúrese de seguir el formato dd/MM/yyyy.");
                        }
                    }
                }

                Tarea tarea = new Tarea(nuevaTarea, fechaActual, realizada, fechaFin);
                nuevaLista.agregarTarea(tarea);
                System.out.println("Tarea agregada a la lista '" + nuevaLista.getNombre() + "'.");
            }
        }

        System.out.println("Lista de tareas creada.");
    }




    public void verTareasDeLaLista() {
        mostrarListasTareas();
        System.out.print("Seleccione una lista por su índice: ");
        int indiceLista = lector.leerOpcion();

        ListaTareas listaSeleccionada = obtenerListaTareas(indiceLista);

        if (listaSeleccionada != null) {
            List<Tarea> tareas = listaSeleccionada.obtenerTareas();
            if (!tareas.isEmpty()) {
                System.out.println("Tareas en la lista '" + listaSeleccionada.getNombre() + "':");
                for (int i = 0; i < tareas.size(); i++) {
                    Tarea tarea = tareas.get(i);
                    System.out.println((i + 1) + ". " + tarea.toString());
                }
            } else {
                System.out.println("La lista de tareas está vacía.");
            }
        } else {
            System.out.println("Lista no encontrada.");
        }
    }

    public void verListasCreadas() {
        System.out.println("Listas de tareas disponibles:");
        for (int i = 0; i < listasDeTareas.size(); i++) {
            System.out.println((i + 1) + ". " + listasDeTareas.get(i).getNombre());
        }
    }
    public void gestionarTareas() {
        Scanner scanner = new Scanner(System.in);
        ManejadorTareas manejadorTareas = new ManejadorTareas();

        while (true) {
            System.out.println("Menú para actualizar  Tareas:");
            System.out.println("1. Seleccionar lista de tareas");
            System.out.println("2. Regresar al menú principal");
            System.out.print("Elija una opción: ");

            int opcion = lector.leerOpcion();

            switch (opcion) {
                case 1:
                    ListaTareas listaSeleccionada = seleccionarListaTareas();
                    if (listaSeleccionada != null) {
                        while (true) {
                            System.out.println("Gestionando lista '" + listaSeleccionada.getNombre() + "':");
                            System.out.println("1. Agregar nueva tarea");
                            System.out.println("2. Eliminar tarea");
                            System.out.println("3. Marcar tarea como realizada");
                            System.out.println("4. Regresar al menú anterior");
                            System.out.print("Elija una opción: ");

                            int opcionGestion = lector.leerOpcion();

                            switch (opcionGestion) {
                                case 1:
                                    manejadorTareas.agregarNuevaTarea(listaSeleccionada);
                                    break;

                                case 2:
                                    manejadorTareas.eliminarTarea(listaSeleccionada);
                                    break;

                                case 3:
                                    manejadorTareas.marcarTareaComoRealizada(listaSeleccionada);
                                    break;

                                case 4:
                                    return; // Regresar al menú anterior

                                default:
                                    System.out.println("Opción no válida. Por favor, elija una opción válida.");
                            }
                        }
                    } else {
                        System.out.println("Lista no encontrada.");
                    }
                    break;

                case 2:
                    return; // Regresar al menú principal

                default:
                    System.out.println("Opción no válida. Por favor, elija una opción válida.");
            }
        }
    }




    public void eliminarListaTareas() {
        mostrarListasTareas();
        System.out.println("Eliminar listas de tareas.");
        System.out.print("Indique el indice de la lista de tareas: ");
        int indiceLista = lector.leerOpcion();

        if (indiceLista >= 0 && indiceLista < listasDeTareas.size()) {
            ListaTareas listaEliminar = listasDeTareas.get(indiceLista);
            eliminarListaTareas(indiceLista);
            System.out.println("Se eliminó la lista de tareas: '" + listaEliminar.getNombre() + "'.");
        } else {
            System.out.println("Índice no válido. La lista de tareas no ha sido eliminada.");
        }
    }



    public ListaTareas seleccionarListaTareas() {
        System.out.println("Listas de tareas disponibles:");
        for (int i = 0; i < listasDeTareas.size(); i++) {
            System.out.println((i + 1) + ". " + listasDeTareas.get(i).getNombre());
        }

        System.out.print("Seleccione una lista de tareas por su número: ");
        int numeroLista = lector.leerOpcion();

        if (numeroLista >= 1 && numeroLista <= listasDeTareas.size()) {
            return listasDeTareas.get(numeroLista - 1);
        } else {
            System.out.println("Número de lista no válido.");
            return null;
        }
    }


    public void guardarListaTareas() {
        try {
            // Preguntar al usuario cuál lista de tareas desea guardar
            mostrarListasTareas();
            System.out.print("Seleccione una lista por su índice para guardar: ");

            int indiceLista = lector.leerOpcion();

            ListaTareas listaGuardar = obtenerListaTareas(indiceLista);

            if (listaGuardar != null) {
                // Crear un archivo con el nombre de la lista de tareas
                String nombreArchivo = listaGuardar.getNombre() + ".txt";


                String rutaCompleta = "C:\\Users\\xaico\\IdeaProjects\\HolaMundo\\RegistroDescargas\\" + nombreArchivo;

                try (FileWriter writer = new FileWriter(rutaCompleta, Charset.forName("utf-8"), true);
                     BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

                    // Escribir el nombre de la lista y su fecha de creación en el archivo
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                    bufferedWriter.write("Nombre de la lista: " + listaGuardar.getNombre());
                    bufferedWriter.newLine();
                    bufferedWriter.write("Fecha de creación:" + formatoFecha.format(listaGuardar.getFechaCreacion()));
                    bufferedWriter.newLine();

                    // Escribir las tareas de la lista en el archivo
                    List<Tarea> tareas = listaGuardar.obtenerTareas();


                    for (int i = 0; i < tareas.size(); i++) {
                        Tarea tarea = tareas.get(i);
                        bufferedWriter.newLine();
                        bufferedWriter.write("- Nombre de la Tarea: " + tarea.getNombre());
                        bufferedWriter.newLine();
                        bufferedWriter.write("  Fecha de creación: " + formatoFecha.format(tarea.getFechaCreacion()));
                        bufferedWriter.newLine();
                        bufferedWriter.write("  Realizada: " + (tarea.isRealizada() ? "Tarea completada" : "Tarea pendiente"));
                        bufferedWriter.newLine();
                        bufferedWriter.write("  Fecha de realización: " + (tarea.isRealizada() ? formatoFecha.format(tarea.getFechaRealizacion()) : "Pendiente por terminar"));
                        bufferedWriter.newLine();
                        bufferedWriter.newLine();
                    }

                    System.out.println("Lista de tareas guardada correctamente en el archivo '" + nombreArchivo + "'.");
                } catch (IOException e) {
                    System.out.println("Error al guardar la lista de tareas: " + e.getMessage());
                }
            } else {
                System.out.println("Lista no encontrada.");
            }
        } catch (Exception e) {
            System.out.println("Error al guardar la lista de tareas: " + e.getMessage());
        }
    }
    public void cargarListaTareasDesdeArchivo(String nombreArchivo) {
        try (FileReader reader = new FileReader(nombreArchivo, Charset.forName("utf-8"));
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            String linea;
            String nombreLista = null;
            Date fechaCreacion = null;
            ListaTareas listaCargada = null;
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

            while ((linea = bufferedReader.readLine()) != null) {
                if (linea.startsWith("Nombre de la lista: ")) {
                    nombreLista = linea.substring("Nombre de la lista: ".length());
                } else if (linea.startsWith("Fecha de creación: ")) {
                    fechaCreacion = formatoFecha.parse(linea.substring("Fecha de creación: ".length()));
                    listaCargada = new ListaTareas(nombreLista, fechaCreacion);
                } else if (linea.startsWith("- Nombre de la Tarea: ")) {
                    String nombreTarea = linea.substring("- Nombre de la Tarea: ".length());
                    Date fechaTarea = formatoFecha.parse(bufferedReader.readLine().substring("  Fecha de creación: ".length()));
                    boolean realizada = bufferedReader.readLine().contains("Tarea completada");
                    Date fechaRealizacion = null;
                    if (realizada) {
                        fechaRealizacion = formatoFecha.parse(bufferedReader.readLine().substring("  Fecha de realización: ".length()));
                    }

                    // Crear y agregar la tarea a la lista cargada
                    Tarea tarea = new Tarea(nombreTarea, fechaTarea, realizada, fechaRealizacion);
                    listaCargada.agregarTarea(tarea);
                }
            }

            if (listaCargada != null) {
                agregarListaTareas(listaCargada);
                System.out.println("Lista de tareas cargada correctamente desde el archivo '" + nombreArchivo + "'.");
            } else {
                System.out.println("El archivo no contiene datos válidos para una lista de tareas.");
            }
        } catch (IOException e) {
            System.out.println("Error al cargar la lista de tareas desde el archivo: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Error al parsear las fechas desde el archivo: " + e.getMessage());
        }
    }

}
