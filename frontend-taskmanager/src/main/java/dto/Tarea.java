package dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Tarea {
    private Long id;
    private String nombre;
    private LocalDate fechaCreacion;
    private Set<Usuario> empleadosAsignados = new HashSet<>();
    private Usuario gestorEncargado;
    private String estadoTarea;
    private Set<Subtarea> subtareas;
    private Set<Comentario> comentarios;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\":").append("\"").append(id).append("\"");
        sb.append("\"nombre\":").append("\"").append(nombre).append("\"");
        sb.append("\"fechaCreacion\":").append("\"").append(fechaCreacion).append("\"");
        sb.append("\"empleadosAsignados\":").append("\"").append(empleadosAsignados).append("\"");
        sb.append("\"gestorEncargado\":").append("\"").append(gestorEncargado).append("\"");
        sb.append("\"estadoTarea\"").append("\"").append(estadoTarea).append("\"");
        sb.append('}');
        return sb.toString();
    }

    // Constructor sin argumentos
    public Tarea() {
    }

    // Constructor para testing y registro de usuarios
    public Tarea(String nombre, LocalDate fechaCreacion, Set<Usuario> empleadosAsignados, Usuario gestorEncargado,
            String estadoTarea) {
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.empleadosAsignados = empleadosAsignados;
        this.gestorEncargado = gestorEncargado;
        this.estadoTarea = estadoTarea;
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

    public Usuario getGestorEncargado() {
        return gestorEncargado;
    }

    public void setGestorEncargado(Usuario gestorEncargado) {
        this.gestorEncargado = gestorEncargado;
    }

    public String getEstadoTarea() {
        return estadoTarea;
    }

    public void setEstadoTarea(String estadoTarea) {
        this.estadoTarea = estadoTarea;
    }

    public Set<Subtarea> getSubtareas() {
        return subtareas;
    }

    public void setSubtareas(Set<Subtarea> subtareas) {
        this.subtareas = subtareas;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

}
