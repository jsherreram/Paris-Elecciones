/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

/**
 *
 * @author Pedro Rodríguez
 */
public class RegistroEvento {
    
    private int codigoEvento = 0;
    private int valor = 0;
    private String nombreCargo = "";
    private String formaTomaAsistencia = "";
    
    public RegistroEvento(){
    }

    /**
     * @return the codigoEvento
     */
    public int getCodigoEvento() {
        return codigoEvento;
    }

    /**
     * @param codigoEvento the codigoEvento to set
     */
    public void setCodigoEvento(int codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

  
    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * @return the nombreCargo
     */
    public String getNombreCargo() {
        return nombreCargo;
    }

    /**
     * @param nombreCargo the nombreCargo to set
     */
    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }

    /**
     * @return the formaTomaAsistencia
     */
    public String getFormaTomaAsistencia() {
        return formaTomaAsistencia;
    }

    /**
     * @param formaTomaAsistencia the formaTomaAsistencia to set
     */
    public void setFormaTomaAsistencia(String formaTomaAsistencia) {
        this.formaTomaAsistencia = formaTomaAsistencia;
    }
    
}
