package Lista.modelo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
public class ListaTareas implements Serializable {
    private String nombre;
    private final Date fechaCreacion;
    private List<Tarea> tareas = new ArrayList<>();
    public ListaTareas(String nombre,Date fechaCreacion) {
        this.nombre = nombre;
        this.fechaCreacion = new Date();
    }

    // Getters y setters para el atributo nombre

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
    }

    // Método para eliminar una tarea de la lista
    public void eliminarTarea(int indice) {
        if (indice >= 0 && indice < tareas.size()) {
            tareas.remove(indice);
        } else {
            System.out.println("Índice no válido. La tarea no ha sido eliminada.");
        }
    }

    // Método para obtener la lista de tareas
    public List<Tarea> obtenerTareas() {
        return tareas;
    }

    // Método para obtener el número de tareas en la lista
    public int obtenerNumeroTareas() {
        return tareas.size();
    }

    // Método para imprimir los nombres de las tareas en la lista
    public void imprimirNombresTareas() {
        List<Tarea> tareas = obtenerTareas();
        for (int i = 0; i < tareas.size(); i++) {
            Tarea tarea = tareas.get(i);
            System.out.println((i + 1) + ". " + tarea.getNombre());
        }
    }
    public void imprimirNombresTareasEliminar() {
        List<Tarea> tareas = obtenerTareas();
        for (int i = 0; i < tareas.size(); i++) {
            Tarea tarea = tareas.get(i);
            System.out.println((i + 1) + ". " + tarea.getNombre());
        }
    }

}

