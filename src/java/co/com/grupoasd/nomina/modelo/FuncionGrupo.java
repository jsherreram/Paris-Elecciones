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
public class FuncionGrupo {
    private Long idGrupo;
    private Long idFuncion;
    private Integer activo;

    /**
     * @return the idGrupo
     */
    public Long getIdGrupo() {
        return idGrupo;
    }

    /**
     * @param idGrupo the idGrupo to set
     */
    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    /**
     * @return the idFuncion
     */
    public Long getIdFuncion() {
        return idFuncion;
    }

    /**
     * @param idFuncion the idFuncion to set
     */
    public void setIdFuncion(Long idFuncion) {
        this.idFuncion = idFuncion;
    }

    /**
     * @return the activo
     */
    public Integer getActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(Integer activo) {
        this.activo = activo;
    }
}
