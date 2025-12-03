package dto;

import java.time.LocalDateTime;

public class Comentario {
    private Long id;
    private Usuario usuario;
    private Tarea tarea;
    private Long idTarea;
    private Long idUsuario;
    private String contenido;
    private LocalDateTime fechaHoraSubida;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Long idTarea) {
        this.idTarea = idTarea;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFechaHoraSubida() {
        return fechaHoraSubida;
    }

    public void setFechaHoraSubida(LocalDateTime fechaHoraSubida) {
        this.fechaHoraSubida = fechaHoraSubida;
    }

    public Comentario() {
    }

    public Comentario(String contenido, LocalDateTime fechaHoraSubida, Long idTarea, Long idUsuario) {
        this.contenido = contenido;
        this.fechaHoraSubida = fechaHoraSubida;
        this.idTarea = idTarea;
        this.idUsuario = idUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Comentario{");
        sb.append("id=").append(id);
        sb.append(", idTarea=").append(idTarea);
        sb.append(", idUsuario=").append(idUsuario);
        sb.append(", contenido=").append(contenido);
        sb.append(", fechaHoraSubida=").append(fechaHoraSubida);
        sb.append('}');
        return sb.toString();
    }

}
