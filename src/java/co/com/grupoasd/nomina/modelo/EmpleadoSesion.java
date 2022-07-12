/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Grupo ASD
 */
public class EmpleadoSesion implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Integer idEmpleado;
    private String apellido1;
    private String apellido2;
    private String nombre1;
    private String nombre2;
    private String email;
    private String nrodoc;
    private Integer bloqueado;
    private List<Long> sitios;
    private List<PruebasxPersona> pruebas;
    private String rolActual;
    private Integer pruebaActual;
    private int estadoEncuesta;

    /**
     * @return the idEmpleado
     */
    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * @param idEmpleado the idEmpleado to set
     */
    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * @return the apellido1
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * @param apellido1 the apellido1 to set
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * @return the apellido2
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * @param apellido2 the apellido2 to set
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * @return the nombre1
     */
    public String getNombre1() {
        return nombre1;
    }

    /**
     * @param nombre1 the nombre1 to set
     */
    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    /**
     * @return the nombre2
     */
    public String getNombre2() {
        return nombre2;
    }

    /**
     * @param nombre2 the nombre2 to set
     */
    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the nrodoc
     */
    public String getNrodoc() {
        return nrodoc;
    }

    /**
     * @param nrodoc the nrodoc to set
     */
    public void setNrodoc(String nrodoc) {
        this.nrodoc = nrodoc;
    }

    /**
     * @return the bloqueado
     */
    public Integer getBloqueado() {
        return bloqueado;
    }

    /**
     * @param bloqueado the bloqueado to set
     */
    public void setBloqueado(Integer bloqueado) {
        this.bloqueado = bloqueado;
    }

    /**
     * @return the sitios
     */
    public List<Long> getSitios() {
        return sitios;
    }

    /**
     * @param sitios the sitios to set
     */
    public void setSitios(List<Long> sitios) {
        this.sitios = sitios;
    }

    /**
     * @return the pruebas
     */
    public List<PruebasxPersona> getPruebas() {
        return pruebas;
    }

    /**
     * @param pruebas the pruebas to set
     */
    public void setPruebas(List<PruebasxPersona> pruebas) {
        this.pruebas = pruebas;
    }

    /**
     * @return the rolActual
     */
    public String getRolActual() {
        return rolActual;
    }

    /**
     * @param rolActual the rolActual to set
     */
    public void setRolActual(String rolActual) {
        this.rolActual = rolActual;
    }

    /**
     * @return the pruebaActual
     */
    public Integer getPruebaActual() {
        return pruebaActual;
    }

    /**
     * @param pruebaActual the pruebaActual to set
     */
    public void setPruebaActual(Integer pruebaActual) {
        this.pruebaActual = pruebaActual;
    }

    public int getEstadoEncuesta() {
        return estadoEncuesta;
    }

    public void setEstadoEncuesta(int estadoEncuesta) {
        this.estadoEncuesta = estadoEncuesta;
    }

       
    
}
