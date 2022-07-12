package co.com.grupoasd.nomina.modelo;

import java.util.Date;

/**
 *
 * @author Grupo ASD
 */
public class EstadoPersonaPrueba {

    Integer id;
    String codigo;
    String descripcion;
    Date fechaActualiza;

    /**
     *
     */
    public EstadoPersonaPrueba() {
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     *
     * @param codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     *
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     *
     * @return
     */
    public Date getFechaActualiza() {
        return fechaActualiza;
    }

    /**
     *
     * @param fechaActualiza
     */
    public void setFechaActualiza(Date fechaActualiza) {
        this.fechaActualiza = fechaActualiza;
    }

}
