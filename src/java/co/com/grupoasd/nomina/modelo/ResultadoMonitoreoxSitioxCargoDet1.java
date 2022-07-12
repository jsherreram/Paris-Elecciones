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
public class ResultadoMonitoreoxSitioxCargoDet1 {

    private Integer cantidadRequeridos, asistieron, suplentes, suplentesasistieron;
    private String COLOR = "dropdown-toggle btn btn-success";
    //No es que sean finales, se colocan en mayúsculas por que así estaban antes
    private Double ASISTENCIA;
    private Double REQUERIDOS;
    private Double ASISTIERON;
    private Double SUPLENTES;
    private Double SUPLENTESASISTIERON;
    private Double SUPLENTESSOLICITADOS;
    private String titulopreasignados;
    private String titulodespachados;
    private String titulodevueltos;
    private String titulorecibidos;
    private String titulodevueltosrecibidos;
    private String tituloperdidos;
    private Double itemsEstadoSuplencia;
    private double porcentajeAsistencia;
    private Double preasignados;
    private Double despachados;
    private Double devueltos;
    private Double recibidos;
    private Double devueltoRecibido;
    private Double perdido;
    private String asistenciaFormato;
    private Double solicitados;

    /**
     * @return the cantidadRequeridos
     */
    public Integer getCantidadRequeridos() {
        return cantidadRequeridos;
    }

    /**
     * @param cantidadRequeridos the cantidadRequeridos to set
     */
    public void setCantidadRequeridos(Integer cantidadRequeridos) {
        this.cantidadRequeridos = cantidadRequeridos;
    }

    /**
     * @return the asistieron
     */
    public Integer getAsistieron() {
        return asistieron;
    }

    /**
     * @param asistieron the asistieron to set
     */
    public void setAsistieron(Integer asistieron) {
        this.asistieron = asistieron;
    }

    /**
     * @return the suplentes
     */
    public Integer getSuplentes() {
        return suplentes;
    }

    /**
     * @param suplentes the suplentes to set
     */
    public void setSuplentes(Integer suplentes) {
        this.suplentes = suplentes;
    }

    /**
     * @return the suplentesasistieron
     */
    public Integer getSuplentesasistieron() {
        return suplentesasistieron;
    }

    /**
     * @param suplentesasistieron the suplentesasistieron to set
     */
    public void setSuplentesasistieron(Integer suplentesasistieron) {
        this.suplentesasistieron = suplentesasistieron;
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
     * @return the ASISTENCIA
     */
    public Double getASISTENCIA() {
        return ASISTENCIA;
    }

    /**
     * @param ASISTENCIA the ASISTENCIA to set
     */
    public void setASISTENCIA(Double ASISTENCIA) {
        this.ASISTENCIA = ASISTENCIA;
    }

    /**
     * @return the REQUERIDOS
     */
    public Double getREQUERIDOS() {
        return REQUERIDOS;
    }

    /**
     * @param REQUERIDOS the REQUERIDOS to set
     */
    public void setREQUERIDOS(Double REQUERIDOS) {
        this.REQUERIDOS = REQUERIDOS;
    }

    /**
     * @return the ASISTIERON
     */
    public Double getASISTIERON() {
        return ASISTIERON;
    }

    /**
     * @param ASISTIERON the ASISTIERON to set
     */
    public void setASISTIERON(Double ASISTIERON) {
        this.ASISTIERON = ASISTIERON;
    }

    /**
     * @return the SUPLENTES
     */
    public Double getSUPLENTES() {
        return SUPLENTES;
    }

    /**
     * @param SUPLENTES the SUPLENTES to set
     */
    public void setSUPLENTES(Double SUPLENTES) {
        this.SUPLENTES = SUPLENTES;
    }

    /**
     * @return the SUPLENTESASISTIERON
     */
    public Double getSUPLENTESASISTIERON() {
        return SUPLENTESASISTIERON;
    }

    /**
     * @param SUPLENTESASISTIERON the SUPLENTESASISTIERON to set
     */
    public void setSUPLENTESASISTIERON(Double SUPLENTESASISTIERON) {
        this.SUPLENTESASISTIERON = SUPLENTESASISTIERON;
    }

    /**
     * @return the SUPLENTESSOLICITADOS
     */
    public Double getSUPLENTESSOLICITADOS() {
        return SUPLENTESSOLICITADOS;
    }

    /**
     * @param SUPLENTESSOLICITADOS the SUPLENTESSOLICITADOS to set
     */
    public void setSUPLENTESSOLICITADOS(Double SUPLENTESSOLICITADOS) {
        this.SUPLENTESSOLICITADOS = SUPLENTESSOLICITADOS;
    }

    /**
     * @return the itemsEstadoSuplencia
     */
    public Double getItemsEstadoSuplencia() {
        return itemsEstadoSuplencia;
    }

    /**
     * @param itemsEstadoSuplencia the itemsEstadoSuplencia to set
     */
    public void setItemsEstadoSuplencia(Double itemsEstadoSuplencia) {
        this.itemsEstadoSuplencia = itemsEstadoSuplencia;
    }

    /**
     * @return the porcentajeAsistencia
     */
    public double getPorcentajeAsistencia() {
        return porcentajeAsistencia;
    }

    /**
     * @param porcentajeAsistencia the porcentajeAsistencia to set
     */
    public void setPorcentajeAsistencia(double porcentajeAsistencia) {
        this.porcentajeAsistencia = porcentajeAsistencia;
    }

    /**
     * @return the preasignados
     */
    public Double getPreasignados() {
        return preasignados;
    }

    /**
     * @param preasignados the preasignados to set
     */
    public void setPreasignados(Double preasignados) {
        this.preasignados = preasignados;
    }

    /**
     * @return the despachados
     */
    public Double getDespachados() {
        return despachados;
    }

    /**
     * @param despachados the despachados to set
     */
    public void setDespachados(Double despachados) {
        this.despachados = despachados;
    }

    /**
     * @return the devueltos
     */
    public Double getDevueltos() {
        return devueltos;
    }

    /**
     * @param devueltos the devueltos to set
     */
    public void setDevueltos(Double devueltos) {
        this.devueltos = devueltos;
    }

    /**
     * @return the recibidos
     */
    public Double getRecibidos() {
        return recibidos;
    }

    /**
     * @param recibidos the recibidos to set
     */
    public void setRecibidos(Double recibidos) {
        this.recibidos = recibidos;
    }

    /**
     * @return the devueltoRecibido
     */
    public Double getDevueltoRecibido() {
        return devueltoRecibido;
    }

    /**
     * @param devueltoRecibido the devueltoRecibido to set
     */
    public void setDevueltoRecibido(Double devueltoRecibido) {
        this.devueltoRecibido = devueltoRecibido;
    }

    /**
     * @return the perdido
     */
    public Double getPerdido() {
        return perdido;
    }

    /**
     * @param perdido the perdido to set
     */
    public void setPerdido(Double perdido) {
        this.perdido = perdido;
    }

    /**
     * @return the titulopreasignados
     */
    public String getTitulopreasignados() {
        return titulopreasignados;
    }

    /**
     * @param titulopreasignados the titulopreasignados to set
     */
    public void setTitulopreasignados(String titulopreasignados) {
        this.titulopreasignados = titulopreasignados;
    }

    /**
     * @return the titulodespachados
     */
    public String getTitulodespachados() {
        return titulodespachados;
    }

    /**
     * @param titulodespachados the titulodespachados to set
     */
    public void setTitulodespachados(String titulodespachados) {
        this.titulodespachados = titulodespachados;
    }

    /**
     * @return the titulodevueltos
     */
    public String getTitulodevueltos() {
        return titulodevueltos;
    }

    /**
     * @param titulodevueltos the titulodevueltos to set
     */
    public void setTitulodevueltos(String titulodevueltos) {
        this.titulodevueltos = titulodevueltos;
    }

    /**
     * @return the titulorecibidos
     */
    public String getTitulorecibidos() {
        return titulorecibidos;
    }

    /**
     * @param titulorecibidos the titulorecibidos to set
     */
    public void setTitulorecibidos(String titulorecibidos) {
        this.titulorecibidos = titulorecibidos;
    }

    /**
     * @return the titulodevueltosrecibidos
     */
    public String getTitulodevueltosrecibidos() {
        return titulodevueltosrecibidos;
    }

    /**
     * @param titulodevueltosrecibidos the titulodevueltosrecibidos to set
     */
    public void setTitulodevueltosrecibidos(String titulodevueltosrecibidos) {
        this.titulodevueltosrecibidos = titulodevueltosrecibidos;
    }

    /**
     * @return the tituloperdidos
     */
    public String getTituloperdidos() {
        return tituloperdidos;
    }

    /**
     * @param tituloperdidos the tituloperdidos to set
     */
    public void setTituloperdidos(String tituloperdidos) {
        this.tituloperdidos = tituloperdidos;
    }

    /**
     * @return the asistenciaFormato
     */
    public String getAsistenciaFormato() {
        return asistenciaFormato;
    }

    /**
     * @param asistenciaFormato the asistenciaFormato to set
     */
    public void setAsistenciaFormato(String asistenciaFormato) {
        this.asistenciaFormato = asistenciaFormato;
    }

    /**
     * @return the solicitados
     */
    public Double getSolicitados() {
        return solicitados;
    }

    /**
     * @param solicitados the solicitados to set
     */
    public void setSolicitados(Double solicitados) {
        this.solicitados = solicitados;
    }

}
