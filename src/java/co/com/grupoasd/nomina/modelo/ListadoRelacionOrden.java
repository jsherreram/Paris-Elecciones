/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

/**
 *
 * @author CristianAlexander
 */
public class ListadoRelacionOrden {
        private Long idsuplenciadetalle;
        private String descripcion;
        private Long nrodoc;
        private String apellidos;
        private String nombres;
        private Integer idDivipolPds;
        private Long codigoCargo;
        private String placa;
        private String nombreConductor;

    /**
     * @return the idsuplenciadetalle
     */
    public Long getIdsuplenciadetalle() {
        return idsuplenciadetalle;
    }

    /**
     * @param idsuplenciadetalle the idsuplenciadetalle to set
     */
    public void setIdsuplenciadetalle(Long idsuplenciadetalle) {
        this.idsuplenciadetalle = idsuplenciadetalle;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the nrodoc
     */
    public Long getNrodoc() {
        return nrodoc;
    }

    /**
     * @param nrodoc the nrodoc to set
     */
    public void setNrodoc(Long nrodoc) {
        this.nrodoc = nrodoc;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the idDivipolPds
     */
    public Integer getIdDivipolPds() {
        return idDivipolPds;
    }

    /**
     * @param idDivipolPds the idDivipolPds to set
     */
    public void setIdDivipolPds(Integer idDivipolPds) {
        this.idDivipolPds = idDivipolPds;
    }

    /**
     * @return the codigoCargo
     */
    public Long getCodigoCargo() {
        return codigoCargo;
    }

    /**
     * @param codigoCargo the codigoCargo to set
     */
    public void setCodigoCargo(Long codigoCargo) {
        this.codigoCargo = codigoCargo;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the nombreConductor
     */
    public String getNombreConductor() {
        return nombreConductor;
    }

    /**
     * @param nombreConductor the nombreConductor to set
     */
    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }
}
