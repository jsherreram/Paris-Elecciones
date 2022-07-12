/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.common.util;

import java.util.Date;

/**
 *
 * @author ASD
 */
public enum EnumColumnsCsvPagos {

    id(15, String.class), tipoId(99, Integer.class), digitoVerificacion(1, Integer.class), nombres(100, String.class),
    formaPago(9, Integer.class), banco(9999, Integer.class), tipoCuenta(99, Integer.class), numeroCuenta(999999999, Integer.class),
    codOficina(9999, Integer.class), anio(9999, Integer.class), mes(99, Integer.class), dia(99, Integer.class), valorAPagar(10, String.class),
    concepto(40, String.class), departamento(20, String.class), municipio(20, String.class), codigoSitio(99999999, Integer.class),
    nombreSitio(100, String.class), nombreCargo(20, String.class), valorMunicipal(10, String.class), valorFluvial(10, String.class),
    valorRural(10, String.class), valorTraccionAnimal(10, String.class), ndiasTransporteInterno(10, String.class),
    valorInterno(10, String.class), valorAereo(10, String.class), pernocta(2, String.class), ndias(5, String.class),
    valorDia(10, String.class), valorTotal(10, String.class), estado(20, String.class), retencionFuente(5, String.class),
    reteIca(5, String.class), fechaPago(10, Date.class), referenciaPago(20, String.class), valorPago(10, String.class),
    bancoPago(9999, Integer.class), medioPago(10, String.class);

    private int length;
    private Class<?> tipo;

    private EnumColumnsCsvPagos(int length, Class<?> type) {
        this.length = length;
        this.tipo = type;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * @return the tipo
     */
    public Class<?> getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Class<?> tipo) {
        this.tipo = tipo;
    }
    

}
