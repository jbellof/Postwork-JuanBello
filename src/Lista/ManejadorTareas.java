package Lista;
import Lista.modelo.ListaTareas;
import Lista.modelo.Tarea;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ManejadorTareas  {
    private Lector lector = new Lector();

    public void agregarNuevaTarea(ListaTareas listaTareas) {
        System.out.print("Nombre de la nueva tarea: ");
        String nombreTarea = lector.leerTexto();
        Date fechaActual = new Date();
        System.out.println("Fecha de Inicio: " + fechaActual);
        System.out.println("Ingrese el estado (true/false) para la variable 'realizada':");
        String entradaUsuario = lector.leerTexto();
        boolean realizada = Boolean.parseBoolean(entradaUsuario);
        System.out.println("El estado de la tarea es " + realizada);

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaFin = null;

        if (realizada) {
            System.out.println("Ingrese la fecha de finalizar la tarea (dd/MM/yyyy):");
            String fechaTerminada = lector.leerTexto();
            try {
                // Intenta analizar la cadena de fecha ingresada en un objeto Date
                fechaFin = formato.parse(fechaTerminada);

                // La fecha se ha analizado correctamente
                System.out.println("Fecha ingresada: " + formato.format(fechaFin));
            } catch (ParseException e) {
                // Manejo de error si la entrada no se pudo analizar como una fecha válida
                System.out.println("La entrada no es una fecha válida. Asegúrese de seguir el formato dd/MM/yyyy.");
                return; // Salir del método si la fecha no es válida
            }
        }

        Tarea nuevaTarea = new Tarea(nombreTarea, fechaActual, realizada, fechaFin);
        listaTareas.agregarTarea(nuevaTarea);
        System.out.println("Tarea agregada a la lista '" + listaTareas.getNombre() + "'.");
    }


    public void eliminarTarea(ListaTareas listaTareas) {
        if (listaTareas.obtenerNumeroTareas() > 0) {
            System.out.println("Tareas en la lista '" + listaTareas.getNombre() + "':");
            listaTareas.imprimirNombresTareas();
            System.out.print("Seleccione una tarea por su índice: ");
            int indiceTarea = lector.leerOpcion();

            if (indiceTarea >= 0 && indiceTarea < listaTareas.obtenerNumeroTareas()) {
                listaTareas.eliminarTarea(indiceTarea-1);
                System.out.println("Tarea eliminada.");
            } else {
                System.out.println("Índice no válido. La tarea no ha sido eliminada.");
            }
        } else {
            System.out.println("La lista de tareas está vacía.");
        }
    }

    public void marcarTareaComoRealizada(ListaTareas listaTareas) {
        if (listaTareas.obtenerNumeroTareas() > 0) {
            System.out.println("Tareas pendientes en la lista '" + listaTareas.getNombre() + "':");

            // Mostrar solo las tareas pendientes (realizada = false)
            int indice = 0;
            for (Tarea tarea : listaTareas.obtenerTareas()) {
                if (!tarea.isRealizada()) {
                    System.out.println(indice + ". " + tarea.getNombre());
                }
                indice++;
            }

            if (indice == 0) {
                System.out.println("No hay tareas pendientes en la lista.");
                return;
            }

            System.out.print("Seleccione una tarea por su índice para marcar como realizada: ");
            int indiceTarea = lector.leerOpcion();

            if (indiceTarea >= 0 && indiceTarea < listaTareas.obtenerNumeroTareas()) {
                Tarea tarea = listaTareas.obtenerTareas().get(indiceTarea);
                tarea.marcarComoRealizada();
                System.out.println("Tarea '" + tarea.getNombre() + "' marcada como realizada.");
            } else {
                System.out.println("Índice no válido. No se pudo marcar la tarea como realizada.");
            }
        } else {
            System.out.println("La lista de tareas está vacía.");
        }
    }

}