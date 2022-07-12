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
public class EnvioCorreo {

    private String usuarioSMTP;
    private String passSMTP;
    private String cuerpoCorreo;
    private String tituloCorreo;
    private String correoRemite;
    private String correoDestino;

    /**
     * @return the usuarioSMTP
     */
    public String getUsuarioSMTP() {
        return usuarioSMTP;
    }

    /**
     * @param usuarioSMTP the usuarioSMTP to set
     */
    public void setUsuarioSMTP(String usuarioSMTP) {
        this.usuarioSMTP = usuarioSMTP;
    }

    /**
     * @return the passSMTP
     */
    public String getPassSMTP() {
        return passSMTP;
    }

    /**
     * @param passSMTP the passSMTP to set
     */
    public void setPassSMTP(String passSMTP) {
        this.passSMTP = passSMTP;
    }

    /**
     * @return the cuerpoCorreo
     */
    public String getCuerpoCorreo() {
        return cuerpoCorreo;
    }

    /**
     * @param cuerpoCorreo the cuerpoCorreo to set
     */
    public void setCuerpoCorreo(String cuerpoCorreo) {
        this.cuerpoCorreo = cuerpoCorreo;
    }

    /**
     * @return the tituloCorreo
     */
    public String getTituloCorreo() {
        return tituloCorreo;
    }

    /**
     * @param tituloCorreo the tituloCorreo to set
     */
    public void setTituloCorreo(String tituloCorreo) {
        this.tituloCorreo = tituloCorreo;
    }

    /**
     * @return the correoRemite
     */
    public String getCorreoRemite() {
        return correoRemite;
    }

    /**
     * @param correoRemite the correoRemite to set
     */
    public void setCorreoRemite(String correoRemite) {
        this.correoRemite = correoRemite;
    }

    /**
     * @return the correoDestino
     */
    public String getCorreoDestino() {
        return correoDestino;
    }

    /**
     * @param correoDestino the correoDestino to set
     */
    public void setCorreoDestino(String correoDestino) {
        this.correoDestino = correoDestino;
    }

}
