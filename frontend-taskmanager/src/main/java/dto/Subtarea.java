package dto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Subtarea {
    private Long id;
    private Set<Usuario> empleadosAsignados = new HashSet<>();
    private Tarea tareaAsociada;
    private String nombre;
    private LocalDate fechaCreacion;

    // Constructor sin argumentos
    public Subtarea() {
    }

    // Constructor para testing y registro de usuarios
    public Subtarea(Set<Usuario> empleadosAsignados, LocalDate fechaCreacion, String nombre,
            Tarea tareaAsociada) {
        this.empleadosAsignados = empleadosAsignados;
        this.fechaCreacion = fechaCreacion;
        this.nombre = nombre;
        this.tareaAsociada = tareaAsociada;
    }

    @Override
    public String toString() {
        return "{\"id\":" + id + "\n\"nombre\":" + nombre + "\n" + //
                "\"fechaCreacion\":" + fechaCreacion +
                "\n" +
                "\"empleadosAsignados\":" + Arrays.toString(empleadosAsignados.toArray()) +
                "\n" +
                "\"tareaAsociada\":"
                + tareaAsociada.toString() + "}";
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Set<Usuario> getEmpleadosAsignados() {
        return empleadosAsignados;
    }

    public void setEmpleadosAsignados(Set<Usuario> empleadosAsignados) {
        this.empleadosAsignados = empleadosAsignados;
    }

    public Tarea getTareaAsociada() {
        return tareaAsociada;
    }

    public void setTareaAsociada(Tarea tareaAsociada) {
        this.tareaAsociada = tareaAsociada;
    }

}
