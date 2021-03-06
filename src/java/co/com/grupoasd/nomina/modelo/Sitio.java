/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import java.util.ArrayList;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pedro Rodríguez
 */
@XmlRootElement
public class Sitio {
    
    private int id;
    private Departamento departamento;
    private Municipio municipio;
    private String codigoDepartamento;
    private String codigoMunicipio;
    private String codigoZona; // Author: John Herrera; Date: 23/05/2022.
    private String codigoSitio;
    private String nombreSitio;

    private String direccionSitio;
    private String nombreRector;
    private String telefonoRector;
    private String emailRector;
    private String faxRector;
    private int cantidadSalones;
    private String barrio;
    private int idZona;
    private String instruccionesLlegada;
    private int categoriaSitio;
    private String codigoSede;
    private String nombreSede;
    private Departamento departamentoSede;
    private Municipio municipioSede;
    private String direccionSede;
    private String rectorSede;
    private String telefonoSede;
    private String emailSede;
    private int idPrueba;
    private String fechaReunionPrevia;
    private String horaReunionPrevia;
    private int nExaminandos;
    private String tomaAsistencia;
    private String medioPago;
    private TipoSitio tipoSitio;
    private EstadoSitio estadoSitio;
    private ArrayList<Sitio> pds;
    private float latitud;
    private float longitud;
    private String confirmado;
    private int desconectado;
    private int espolivalente;
    private String estado;
    private int iddivipol;
    
    public Sitio(){
        
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIddivipol() {
        return iddivipol;
    }

    public void setIddivipol(int iddivipol) {
        this.iddivipol = iddivipol;
    }

    /**
     * @return the codigoSitio
     */
    public String getCodigoSitio() {
        return codigoSitio;
    }

    public int getDesconectado() {
        return desconectado;
    }

    public void setDesconectado(int desconectado) {
        this.desconectado = desconectado;
    }

    public int getEspolivalente() {
        return espolivalente;
    }

    public void setEspolivalente(int espolivalente) {
        this.espolivalente = espolivalente;
    }

    /**
     * @param codigoSitio the codigoSitio to set
     */
    public void setCodigoSitio(String codigoSitio) {
        this.codigoSitio = codigoSitio;
    }

    /**
     * @return the nombreSitio
     */
    public String getNombreSitio() {
        return nombreSitio;
    }

    /**
     * @param nombreSitio the nombreSitio to set
     */
    public void setNombreSitio(String nombreSitio) {
        this.nombreSitio = nombreSitio;
    }

    /**
     * @return the codigoDepartamento
     */
    public Departamento getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento the codigoDepartamento to set
     */
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    /**
     * @return the codigoMunicipio
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
     * 
     * @return 
     */
    public String getCodigoZona() {
        return codigoZona;
    }
    
    /**
     * 
     * @param codigoZona 
     */
    public void setCodigoZona(String codigoZona) {
        this.codigoZona = codigoZona;
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


    public String getDireccionSitio() {
        return direccionSitio;
    }

    public void setDireccionSitio(String direccionSitio) {
        this.direccionSitio = direccionSitio;
    }

    public String getNombreRector() {
        return nombreRector;
    }

    public void setNombreRector(String nombreRector) {
        this.nombreRector = nombreRector;
    }

    public String getTelefonoRector() {
        return telefonoRector;
    }

    public void setTelefonoRector(String telefonoRector) {
        this.telefonoRector = telefonoRector;
    }

    public String getEmailRector() {
        return emailRector;
    }

    public void setEmailRector(String emailRector) {
        this.emailRector = emailRector;
    }

    public String getFaxRector() {
        return faxRector;
    }

    public void setFaxRector(String faxRector) {
        this.faxRector = faxRector;
    }

    public int getCantidadSalones() {
        return cantidadSalones;
    }

    public void setCantidadSalones(int cantidadSalones) {
        this.cantidadSalones = cantidadSalones;
    }

    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    public String getInstruccionesLlegada() {
        return instruccionesLlegada;
    }

    public void setInstruccionesLlegada(String instruccionesLlegada) {
        this.instruccionesLlegada = instruccionesLlegada;
    }

    public int getCategoriaSitio() {
        return categoriaSitio;
    }

    public void setCategoriaSitio(int categoriaSitio) {
        this.categoriaSitio = categoriaSitio;
    }

    public String getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(String codigoSede) {
        this.codigoSede = codigoSede;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public Departamento getDepartamentoSede() {
        return departamentoSede;
    }

    public void setDepartamentoSede(Departamento departamentoSede) {
        this.departamentoSede = departamentoSede;
    }

    public Municipio getMunicipioSede() {
        return municipioSede;
    }

    public void setMunicipioSede(Municipio municipioSede) {
        this.municipioSede = municipioSede;
    }

    public String getDireccionSede() {
        return direccionSede;
    }

    public void setDireccionSede(String direccionSede) {
        this.direccionSede = direccionSede;
    }

    public String getRectorSede() {
        return rectorSede;
    }

    public void setRectorSede(String rectorSede) {
        this.rectorSede = rectorSede;
    }

    public String getTelefonoSede() {
        return telefonoSede;
    }

    public void setTelefonoSede(String telefonoSede) {
        this.telefonoSede = telefonoSede;
    }

    public String getEmailSede() {
        return emailSede;
    }

    public void setEmailSede(String emailSede) {
        this.emailSede = emailSede;
    }

    public int getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(int idPrueba) {
        this.idPrueba = idPrueba;
    }

    public String getFechaReunionPrevia() {
        return fechaReunionPrevia;
    }

    public void setFechaReunionPrevia(String fechaReunionPrevia) {
        this.fechaReunionPrevia = fechaReunionPrevia;
    }

    public String getHoraReunionPrevia() {
        return horaReunionPrevia;
    }

    public void setHoraReunionPrevia(String horaReunionPrevia) {
        this.horaReunionPrevia = horaReunionPrevia;
    }

    public String getTomaAsistencia() {
        return tomaAsistencia;
    }

    public void setTomaAsistencia(String tomaAsistencia) {
        this.tomaAsistencia = tomaAsistencia;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public int getnExaminandos() {
        return nExaminandos;
    }

    public void setnExaminandos(int nExaminandos) {
        this.nExaminandos = nExaminandos;
    }

    public TipoSitio getTipoSitio() {
        return tipoSitio;
    }

    public void setTipoSitio(TipoSitio tipoSitio) {
        this.tipoSitio = tipoSitio;
    }



    public EstadoSitio getEstadoSitio() {
        return estadoSitio;
    }

    public void setEstadoSitio(EstadoSitio estadoSitio) {
        this.estadoSitio = estadoSitio;
    }

    public ArrayList<Sitio> getPds() {
        return pds;
    }

    public void setPds(ArrayList<Sitio> psd) {
        this.pds= psd;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public String getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(String confirmado) {
        this.confirmado = confirmado;
    }

    

    @Override
    public String toString() {
        return "Sitio{" + "id=" + id + ", departamento=" + departamento + ", municipio=" + municipio + ", codigoDepartamento=" + codigoDepartamento + ", codigoMunicipio=" + codigoMunicipio + ", codigoSitio=" + codigoSitio + ", nombreSitio=" + nombreSitio + ", direccionSitio=" + direccionSitio + ", nombreRector=" + nombreRector + ", telefonoRector=" + telefonoRector + ", emailRector=" + emailRector + ", faxRector=" + faxRector + ", cantidadSalones=" + cantidadSalones + ", barrio=" + barrio + ", idZona=" + idZona + ", instruccionesLlegada=" + instruccionesLlegada + ", categoriaSitio=" + categoriaSitio + ", codigoSede=" + codigoSede + ", nombreSede=" + nombreSede + ", departamentoSede=" + departamentoSede + ", municipioSede=" + municipioSede + ", direccionSede=" + direccionSede + ", rectorSede=" + rectorSede + ", telefonoSede=" + telefonoSede + ", emailSede=" + emailSede + ", idPrueba=" + idPrueba + ", fechaReunionPrevia=" + fechaReunionPrevia + ", horaReunionPrevia=" + horaReunionPrevia + ", nExaminandos=" + nExaminandos + ", tomaAsistencia=" + tomaAsistencia + ", medioPago=" + medioPago + '}';
    }

   
   
    
    
}
