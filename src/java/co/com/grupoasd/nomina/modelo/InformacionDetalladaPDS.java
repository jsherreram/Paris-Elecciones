/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

/**
 *
 * @author Grupo ASD
 */
public class InformacionDetalladaPDS {
    private String cargo;
    private Integer asistentes;
    private Integer disponibles;
    private Integer despachados;
    private Integer devueltos;
    private String sitio;

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the asistentes
     */
    public Integer getAsistentes() {
        return asistentes;
    }

    /**
     * @param asistentes the asistentes to set
     */
    public void setAsistentes(Integer asistentes) {
        this.asistentes = asistentes;
    }

    /**
     * @return the disponibles
     */
    public Integer getDisponibles() {
        return disponibles;
    }

    /**
     * @param disponibles the disponibles to set
     */
    public void setDisponibles(Integer disponibles) {
        this.disponibles = disponibles;
    }

    /**
     * @return the despachados
     */
    public Integer getDespachados() {
        return despachados;
    }

    /**
     * @param despachados the despachados to set
     */
    public void setDespachados(Integer despachados) {
        this.despachados = despachados;
    }

    /**
     * @return the devueltos
     */
    public Integer getDevueltos() {
        return devueltos;
    }

    /**
     * @param devueltos the devueltos to set
     */
    public void setDevueltos(Integer devueltos) {
        this.devueltos = devueltos;
    }

    /**
     * @return the sitio
     */
    public String getSitio() {
        return sitio;
    }

    /**
     * @param sitio the sitio to set
     */
    public void setSitio(String sitio) {
        this.sitio = sitio;
    }
    
}
