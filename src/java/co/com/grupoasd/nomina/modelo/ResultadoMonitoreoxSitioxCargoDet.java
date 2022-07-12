/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import java.util.ArrayList;

/**
 *
 * @author Grupo ASD
 */
public class ResultadoMonitoreoxSitioxCargoDet {
    private Long codigoCargo;
    private String cargo;
    private String COLOR;
    private ResultadoMonitoreoxSitioxCargoDet1 detalleFinal;
    /*private Integer cantidadRequeridos, asistieron,suplentes,suplentesasistieron;*/            
    /*private ArrayList<ResultadoMonitoreoxSitioxCargoDet1> detalleFinal = new ArrayList<ResultadoMonitoreoxSitioxCargoDet1>();*/
    
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
     * @return the COLOR
     */
    public String getCOLOR() {
        return COLOR;
    }

    /**
     * @param COLOR the COLOR to set
     */
    public void setCOLOR(String COLOR) {
        this.COLOR = COLOR;
    }

    /**
     * @return the detalleFinal
     */
    public ResultadoMonitoreoxSitioxCargoDet1 getDetalleFinal() {
        return detalleFinal;
    }

    /**
     * @param detalleFinal the detalleFinal to set
     */
    public void setDetalleFinal(ResultadoMonitoreoxSitioxCargoDet1 detalleFinal) {
        this.detalleFinal = detalleFinal;
    }  
}
