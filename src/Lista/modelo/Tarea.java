package Lista.modelo;
import java.util.Date;
import java.io.Serializable;
public class Tarea  implements Serializable {
    private String nombre;
    private final Date fechaCreacion;
    private boolean realizada;
    private Date fechaRealizacion;

    public Tarea(String nombre,Date fechaCreacion,boolean realizada,Date fechaRealizacion) {
        this.nombre = nombre;
        this.fechaCreacion = new Date();
        this.realizada = realizada;
        this.fechaRealizacion = fechaRealizacion;
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

    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    public void marcarComoRealizada() {
        if (!realizada) {
            realizada = true;
            fechaRealizacion = new Date();
        }
    }

    @Override
    public String toString() {
        String estadoTarea = realizada ? "Tarea completada" : "Tarea pendiente";
        return
                "Tarea='" + nombre + '\'' +
                " Fecha de Creación:" + fechaCreacion + "\n"+
                " Estado de la tarea:" + estadoTarea  + "\n"+
                " Fecha de Realización:" + (realizada ? fechaRealizacion : "Pendiente por terminar")+ "\n"+
                '}';
    }
}



