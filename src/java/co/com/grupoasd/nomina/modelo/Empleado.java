/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Pedro Rodriguez
 */
@XmlRootElement
public class Empleado {

    private int idEmpleado;
    private Municipio municipio;
    private long nrodoc;
    private String tipodoc;
    private String apellido1;
    private String apellido2;
    private String nombre1;
    private String nombre2;
    private String direccion;
    private String telefono;
    private String celular;
    private String email;
    private String codigoActividad;
    private Estado estado;
    private EstadoEmpleado estadoIcfes;
    private String usuarioCrea;
    private Date fechaCrea;
    private String usuarioModifica;
    private Date fechaModifica;
    private boolean imagen;
    private String display;
    private String observacion;
    private Date fechaNacimiento;
    private Date fechaIngreso;
    private String fechaUltimaAplicacion;
    private String telefono2;
    private String telefono3;
    private String telefono4;
    private String telefono5;
    private String celular2;
    private String celular3;
    private String celular4;
    private String celular5;
    private String email2;
    private String email3;
    private String email4;
    private String email5;
    private int nivelacademico;
    private Double promedio;
    private String semestre;
    private int codigocarrera;
    private int experienciahuellas;
    private int intepretacionsenas;
    private int manejodiscapacidad;
    private int bilingue;
    private int idmediopago;
    private int estadoicfes;
    private Double ultimaevaluacion;
    private int cargo;
    private Cargo cargoobj;
    private int imgdociden;
    private int imgcertestudio;
    private int imgafiliaeps;
    private int imgrut;
    private String genero;
    private String clave; // Author: John Steak Herrera Moreno; Date: 29/06/2022.
    private String idpuesto;
    private int updateSimple;
    private int codigoEstado;
    private byte[] huella;
    private String huellaBase64;
    private int idDivipolAsistencia;
    private byte[] imagenHuella;
    private String imagenHuellaBase64;
    private MunicipioDane municipioDane;
    private LocalidadDane localidad;
    private ZonaIcfes zona;
    private String barrio;
    private float latitud;
    private float longitud;
    private String confirmado;
    private String tipoBase;
    private int idOperador;
    private MunicipioDane municipioRut;
    
    
    /**
     *
     */
    public Empleado() {

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
     * @return the nrodoc
     */
    public long getNrodoc() {
        return nrodoc;
    }

    /**
     * @param nrodoc the nrodoc to set
     */
    public void setNrodoc(long nrodoc) {
        this.nrodoc = nrodoc;
    }

    /**
     * @return the tipodoc
     */
    public String getTipodoc() {
        return tipodoc;
    }

    /**
     * @param tipodoc the tipodoc to set
     */
    public void setTipodoc(String tipodoc) {
        this.tipodoc = tipodoc;
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
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
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
     * @return the codigoActividad
     */
    public String getCodigoActividad() {
        return codigoActividad;
    }

    /**
     * @param codigoActividad the codigoActividad to set
     */
    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
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
     * @return the usuarioCrea
     */
    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    /**
     * @param usuarioCrea the usuarioCrea to set
     */
    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    /**
     * @return the fechaCrea
     */
    public Date getFechaCrea() {
        return fechaCrea;
    }

    /**
     * @param fechaCrea the fechaCrea to set
     */
    public void setFechaCrea(Date fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    /**
     * @return the usuarioModifica
     */
    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    /**
     * @param usuarioModifica the usuarioModifica to set
     */
    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    /**
     * @return the fechaModifica
     */
    public Date getFechaModifica() {
        return fechaModifica;
    }

    /**
     * @param fechaModifica the fechaModifica to set
     */
    public void setFechaModifica(Date fechaModifica) {
        this.fechaModifica = fechaModifica;
    }

    /**
     * @return the idEmpleado
     */
    public int getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * @param idEmpleado the idEmpleado to set
     */
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * @return the imagen
     */
    public boolean isImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(boolean imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the fechaIngreso
     */
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * @param fechaIngreso the fechaIngreso to set
     */
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * @return the fechaUltimaAplicacion
     */
    public String getFechaUltimaAplicacion() {
        return fechaUltimaAplicacion;
    }

    /**
     * @param fechaUltimaAplicacion the fechaUltimaAplicacion to set
     */
    public void setFechaUltimaAplicacion(String fechaUltimaAplicacion) {
        this.fechaUltimaAplicacion = fechaUltimaAplicacion;
    }

    /**
     * @return the telefono2
     */
    public String getTelefono2() {
        return telefono2;
    }

    /**
     * @param telefono2 the telefono2 to set
     */
    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    /**
     * @return the telefono3
     */
    public String getTelefono3() {
        return telefono3;
    }

    /**
     * @param telefono3 the telefono3 to set
     */
    public void setTelefono3(String telefono3) {
        this.telefono3 = telefono3;
    }

    /**
     * @return the telefono4
     */
    public String getTelefono4() {
        return telefono4;
    }

    /**
     * @param telefono4 the telefono4 to set
     */
    public void setTelefono4(String telefono4) {
        this.telefono4 = telefono4;
    }

    /**
     * @return the telefono5
     */
    public String getTelefono5() {
        return telefono5;
    }

    /**
     * @param telefono5 the telefono5 to set
     */
    public void setTelefono5(String telefono5) {
        this.telefono5 = telefono5;
    }

    /**
     * @return the celular2
     */
    public String getCelular2() {
        return celular2;
    }

    /**
     * @param celular2 the celular2 to set
     */
    public void setCelular2(String celular2) {
        this.celular2 = celular2;
    }

    /**
     * @return the celular3
     */
    public String getCelular3() {
        return celular3;
    }

    /**
     * @param celular3 the celular3 to set
     */
    public void setCelular3(String celular3) {
        this.celular3 = celular3;
    }

    /**
     * @return the celular4
     */
    public String getCelular4() {
        return celular4;
    }

    /**
     * @param celular4 the celular4 to set
     */
    public void setCelular4(String celular4) {
        this.celular4 = celular4;
    }

    /**
     * @return the celular5
     */
    public String getCelular5() {
        return celular5;
    }

    /**
     * @param celular5 the celular5 to set
     */
    public void setCelular5(String celular5) {
        this.celular5 = celular5;
    }

    /**
     * @return the email2
     */
    public String getEmail2() {
        return email2;
    }

    /**
     * @param email2 the email2 to set
     */
    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    /**
     * @return the email3
     */
    public String getEmail3() {
        return email3;
    }

    /**
     * @param email3 the email3 to set
     */
    public void setEmail3(String email3) {
        this.email3 = email3;
    }

    /**
     * @return the email4
     */
    public String getEmail4() {
        return email4;
    }

    /**
     * @param email4 the email4 to set
     */
    public void setEmail4(String email4) {
        this.email4 = email4;
    }

    /**
     * @return the email5
     */
    public String getEmail5() {
        return email5;
    }

    /**
     * @param email5 the email5 to set
     */
    public void setEmail5(String email5) {
        this.email5 = email5;
    }

    /**
     * @return the nivelacademico
     */
    public int getNivelacademico() {
        return nivelacademico;
    }

    /**
     * @param nivelacademico the nivelacademico to set
     */
    public void setNivelacademico(int nivelacademico) {
        this.nivelacademico = nivelacademico;
    }

    /**
     * @return the promedio
     */
    public Double getPromedio() {
        return promedio;
    }

    /**
     * @param promedio the promedio to set
     */
    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    /**
     * @return the semestre
     */
    public String getSemestre() {
        return semestre;
    }

    /**
     * @param semestre the semestre to set
     */
    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    /**
     * @return the codigocarrera
     */
    public int getCodigocarrera() {
        return codigocarrera;
    }

    /**
     * @param codigocarrera the codigocarrera to set
     */
    public void setCodigocarrera(int codigocarrera) {
        this.codigocarrera = codigocarrera;
    }

    /**
     * @return the experienciahuellas
     */
    public int getExperienciahuellas() {
        return experienciahuellas;
    }

    /**
     * @param experienciahuellas the experienciahuellas to set
     */
    public void setExperienciahuellas(int experienciahuellas) {
        this.experienciahuellas = experienciahuellas;
    }

    /**
     * @return the intepretacionsenas
     */
    public int getIntepretacionsenas() {
        return intepretacionsenas;
    }

    /**
     * @param intepretacionsenas the intepretacionsenas to set
     */
    public void setIntepretacionsenas(int intepretacionsenas) {
        this.intepretacionsenas = intepretacionsenas;
    }

    /**
     * @return the manejodiscapacidad
     */
    public int getManejodiscapacidad() {
        return manejodiscapacidad;
    }

    /**
     * @param manejodiscapacidad the manejodiscapacidad to set
     */
    public void setManejodiscapacidad(int manejodiscapacidad) {
        this.manejodiscapacidad = manejodiscapacidad;
    }

    /**
     * @return the bilingue
     */
    public int getBilingue() {
        return bilingue;
    }

    /**
     * @param bilingue the bilingue to set
     */
    public void setBilingue(int bilingue) {
        this.bilingue = bilingue;
    }

    public int getIdmediopago() {
        return idmediopago;
    }

    public void setIdmediopago(int idmediopago) {
        this.idmediopago = idmediopago;
    }

    /**
     * @return the estadoicfes
     */
    public int getEstadoicfes() {
        return estadoicfes;
    }

    /**
     * @param estadoicfes the estadoicfes to set
     */
    public void setEstadoicfes(int estadoicfes) {
        this.estadoicfes = estadoicfes;
    }

    /**
     * @return the ultimaevaluacion
     */
    public Double getUltimaevaluacion() {
        return ultimaevaluacion;
    }

    /**
     * @param ultimaevaluacion the ultimaevaluacion to set
     */
    public void setUltimaevaluacion(Double ultimaevaluacion) {
        this.ultimaevaluacion = ultimaevaluacion;
    }

    /**
     * @return the cargo
     */
    public int getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the imgdociden
     */
    public int getImgdociden() {
        return imgdociden;
    }

    /**
     * @param imgdociden the imgdociden to set
     */
    public void setImgdociden(int imgdociden) {
        this.imgdociden = imgdociden;
    }

    /**
     * @return the imgcertestudio
     */
    public int getImgcertestudio() {
        return imgcertestudio;
    }

    /**
     * @param imgcertestudio the imgcertestudio to set
     */
    public void setImgcertestudio(int imgcertestudio) {
        this.imgcertestudio = imgcertestudio;
    }

    /**
     * @return the imgafiliaeps
     */
    public int getImgafiliaeps() {
        return imgafiliaeps;
    }

    /**
     * @param imgafiliaeps the imgafiliaeps to set
     */
    public void setImgafiliaeps(int imgafiliaeps) {
        this.imgafiliaeps = imgafiliaeps;
    }

    /**
     * @return the imgrut
     */
    public int getImgrut() {
        return imgrut;
    }

    /**
     * @param imgrut the imgrut to set
     */
    public void setImgrut(int imgrut) {
        this.imgrut = imgrut;
    }

    /**
     * @return the idpuesto
     */
    public String getIdpuesto() {
        return idpuesto;
    }

    /**
     * @param idpuesto the idpuesto to set
     */
    public void setIdpuesto(String idpuesto) {
        this.idpuesto = idpuesto;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return the updateSimple
     */
    public int getUpdateSimple() {
        return updateSimple;
    }

    /**
     * @param updateSimple the updateSimple to set
     */
    public void setUpdateSimple(int updateSimple) {
        this.updateSimple = updateSimple;
    }

    /**
     * @return the codigoEstado
     */
    public int getCodigoEstado() {
        return codigoEstado;
    }

    /**
     * @param codigoEstado the codigoEstado to set
     */
    public void setCodigoEstado(int codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    /**
     *
     * @return
     */
    public Cargo getCargoobj() {
        return cargoobj;
    }

    /**
     *
     * @param cargoobj
     */
    public void setCargoobj(Cargo cargoobj) {
        this.cargoobj = cargoobj;
    }

    public byte[] getHuella() {
        return huella;
    }

    public void setHuella(byte[] huella) {
        this.huella = huella;
    }

    /**
     * @return the idDivipolAsistencia
     */
    public int getIdDivipolAsistencia() {
        return idDivipolAsistencia;
    }

    /**
     * @param idDivipolAsistencia the idDivipolAsistencia to set
     */
    public void setIdDivipolAsistencia(int idDivipolAsistencia) {
        this.idDivipolAsistencia = idDivipolAsistencia;
    }


    public byte[] getImagenHuella() {
        return imagenHuella;
    }

    public void setImagenHuella(byte[] imagenHuella) {
        this.imagenHuella = imagenHuella;
    }

    public String getHuellaBase64() {
        return huellaBase64;
    }

    public void setHuellaBase64(String huellaBase64) {
        this.huellaBase64 = huellaBase64;
    }

    public MunicipioDane getMunicipioDane() {
        return municipioDane;
    }

    public void setMunicipioDane(MunicipioDane municipioDane) {
        this.municipioDane = municipioDane;
    }

    public LocalidadDane getLocalidad() {
        return localidad;
    }

    public void setLocalidad(LocalidadDane localidad) {
        this.localidad = localidad;
    }

    public ZonaIcfes getZona() {
        return zona;
    }

    public void setZona(ZonaIcfes zona) {
        this.zona = zona;
    }

    /**
     * @return the imagenHuellaBase64
     */
    public String getImagenHuellaBase64() {
        return imagenHuellaBase64;
    }

    /**
     * @param imagenHuellaBase64 the imagenHuellaBase64 to set
     */
    public void setImagenHuellaBase64(String imagenHuellaBase64) {
        this.imagenHuellaBase64 = imagenHuellaBase64;
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
    
      public String getTipoBase() {
        return tipoBase;
    }

    public void setTipoBase(String tipoBase) {
        this.tipoBase = tipoBase;
    }

    public EstadoEmpleado getEstadoIcfes() {
        return estadoIcfes;
    }

    public void setEstadoIcfes(EstadoEmpleado estadoIcfes) {
        this.estadoIcfes = estadoIcfes;
    }
    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public int getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(int idOperador) {
        this.idOperador = idOperador;
    }

    public MunicipioDane getMunicipioRut() {
        return municipioRut;
    }

    public void setMunicipioRut(MunicipioDane municipioRut) {
        this.municipioRut = municipioRut;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
   
}
