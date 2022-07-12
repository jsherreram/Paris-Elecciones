/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pedro Rodriguez
 */
@XmlRootElement
public class Evento {
    
    private int codigoEvento;
    private int idprueba;
    private String nombre;
    private boolean esCapacitacion;
    private String codigoLogisys;
    private String tipoSesion;
    private Prueba prueba;
    private Date fecha;
    private String hora_inicial;
    private Date fecha_final;
    private String hora_final;
    private int activo;
    private int tomaAsistencia;
    private int esPenitenciaria;
    private String coddepartamento;
    private int iddivipol;
    private String codcargo;
    private int cantidadcapacitados;
    private int esEleccion;
    private int disponibles;
    private Cargo cargo ;

    public Evento(){
        
    }

    public String getCodcargo() {
        return codcargo;
    }

    public void setCodcargo(String codcargo) {
        this.codcargo = codcargo;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public int getEsPenitenciaria() {
        return esPenitenciaria;
    }

    public String getCoddepartamento() {
        return coddepartamento;
    }

    public void setCoddepartamento(String coddepartamento) {
        this.coddepartamento = coddepartamento;
    }

    public int getIddivipol() {
        return iddivipol;
    }

    public void setIddivipol(int iddivipol) {
        this.iddivipol = iddivipol;
    }

    public String getCodCargo() {
        return codcargo;
    }

    public void setCodCargo(String codcargo) {
        this.codcargo = codcargo;
    }

    public int getCantidadcapacitados() {
        return cantidadcapacitados;
    }

    public void setCantidadcapacitados(int cantidadcapacitados) {
        this.cantidadcapacitados = cantidadcapacitados;
    }

    public void setEsPenitenciaria(int esPenitenciaria) {
        this.esPenitenciaria = esPenitenciaria;
    }

    public int getTomaAsistencia() {
        return tomaAsistencia;
    }

    public void setTomaAsistencia(int tomaAsistencia) {
        this.tomaAsistencia = tomaAsistencia;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getIdprueba() {
        return idprueba;
    }

    public void setIdprueba(int idprueba) {
        this.idprueba = idprueba;
    }

    public String getCodigoLogisys() {
        return codigoLogisys;
    }

    public void setCodigoLogisys(String codigoLogisys) {
        this.codigoLogisys = codigoLogisys;
    }

    public Date getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(Date fecha_final) {
        this.fecha_final = fecha_final;
    }

    public String getHora_inicial() {
        return hora_inicial;
    }

    public void setHora_inicial(String hora_inicial) {
        this.hora_inicial = hora_inicial;
    }

    public String getHora_final() {
        return hora_final;
    }

    public void setHora_final(String hora_final) {
        this.hora_final = hora_final;
    }

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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the esCapacitacion
     */
    public boolean isEsCapacitacion() {
        return esCapacitacion;
    }

    
    public int esCapacitacion() {
        if(this.esCapacitacion)
        {
            return 1;
        }else
        {
            return 0;
        }        
    }
    
    /**
     * @param esCapacitacion the esCapacitacion to set
     */
    
    public void setEsCapacitacion(boolean esCapacitacion) {
        this.esCapacitacion = esCapacitacion;
    }
    
    public void setEsCapacitacion(int esCapacitacion) {
        
        if(esCapacitacion == 1)
        {
            this.esCapacitacion = true;
        }else
        {
            this.esCapacitacion = false;
        }
    }

    public String getTipoSesion() {
        return tipoSesion;
    }

    public void setTipoSesion(String tipoSesion) {
        this.tipoSesion = tipoSesion;
    }

    public Prueba getPrueba() {
        return prueba;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }

    public int getEsEleccion() {
        return esEleccion;
    }

    public void setEsEleccion(int esEleccion) {
        this.esEleccion = esEleccion;
    }
    
}
