/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;


import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 *
 * @author Pedro Rodriguez
 */
@XmlRootElement
public class PersonaAsignada {
    
    private int iddivipol;
    private Municipio municipio;
    private String zona;
    private String puesto;
    private String nombrePuesto;
    private Cargo cargo;
    private Evento evento;
    private String ubicacion;
    private Empleado empleado;
    private Estado estado;
    private String usuario;
    private Date fecha;
    private int id;
    private boolean asistio;
  
    private int cantidadasistio;
    private int cantidadnoasistio;
    
    private boolean modificable;
    private String display;
    private String display2;
    private String display3;
    
    private int consecutivo;
    private int orden;
    
    private int asistenciabiometrica;
    private String salon;
    
    private int idTipoSitio;
    private int espolivalente;
    private boolean validado;
   
    public PersonaAsignada()
    {
        
    }

    public int getEspolivalente() {
        return espolivalente;
    }
    
    public void setEspolivalente(int espolivalente) {
        this.espolivalente = espolivalente;
    }

    /**
     * @return the municipio
     */
    public Municipio getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    /**
     * @return the zona
     */
    public String getZona() {
        return zona;
    }

    /**
     * @param zona the zona to set
     */
    public void setZona(String zona) {
        this.zona = zona;
    }

    /**
     * @return the puesto
     */
    public String getPuesto() {
        return puesto;
    }

    /**
     * @param puesto the puesto to set
     */
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    /**
     * @return the cargo
     */
    public Cargo getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the evento
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * @param evento the evento to set
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    /**
     * @return the ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * @return the estado
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
     * @return the empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    /**
     * @return the asistio
     */
    public boolean isAsistio() {
        return asistio;
    }

    /**
     * @param asistio the asistio to set
     */
    public void setAsistio(boolean asistio) {
        this.asistio = asistio;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the modificable
     */
    public boolean isModificable() {
        return modificable;
    }

    /**
     * @param modificable the modificable to set
     */
    public void setModificable(boolean modificable) {
        this.modificable = modificable;
    }

    /**
     * @return the display
     */
    public String getDisplay() {
        return display;
    }

    /**
     * @param display the display to set
     */
    public void setDisplay(String display) {
        this.display = display;
    }

    /**
     * @return the display2
     */
    public String getDisplay2() {
        return display2;
    }

    /**
     * @param display2 the display2 to set
     */
    public void setDisplay2(String display2) {
        this.display2 = display2;
    }

    /**
     * @return the cantidadasistio
     */
    public int getCantidadasistio() {
        return cantidadasistio;
    }

    /**
     * @param cantidadasistio the cantidadasistio to set
     */
    public void setCantidadasistio(int cantidadasistio) {
        this.cantidadasistio = cantidadasistio;
    }

    /**
     * @return the cantidadnoasistio
     */
    public int getCantidadnoasistio() {
        return cantidadnoasistio;
    }

    /**
     * @param cantidadnoasistio the cantidadnoasistio to set
     */
    public void setCantidadnoasistio(int cantidadnoasistio) {
        this.cantidadnoasistio = cantidadnoasistio;
    }

    /**
     * @return the display3
     */
    public String getDisplay3() {
        return display3;
    }

    /**
     * @param display3 the display3 to set
     */
    public void setDisplay3(String display3) {
        this.display3 = display3;
    }

    /**
     * @return the consecutivo
     */
    public int getConsecutivo() {
        return consecutivo;
    }

    /**
     * @param consecutivo the consecutivo to set
     */
    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    /**
     * @return the asistenciabiometrica
     */
    public int getAsistenciabiometrica() {
        return asistenciabiometrica;
    }

    /**
     * @param asistenciabiometrica the asistenciabiometrica to set
     */
    public void setAsistenciabiometrica(int asistenciabiometrica) {
        this.asistenciabiometrica = asistenciabiometrica;
    }

    /**
     * @return the iddivipol
     */
    public int getIddivipol() {
        return iddivipol;
    }

    /**
     * @param iddivipol the iddivipol to set
     */
    public void setIddivipol(int iddivipol) {
        this.iddivipol = iddivipol;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    /**
     * @return the orden
     */
    public int getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getIdTipoSitio() {
        return idTipoSitio;
    }

    public void setIdTipoSitio(int idTipoSitio) {
        this.idTipoSitio = idTipoSitio;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public String getNombrePuesto() {
        return nombrePuesto;
    }

    public void setNombrePuesto(String nombrePuesto) {
        this.nombrePuesto = nombrePuesto;
    }

    
}
