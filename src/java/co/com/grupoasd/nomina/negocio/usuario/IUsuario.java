/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.usuario;

import co.com.grupoasd.nomina.modelo.EmpleadoSesion;

/**
 *
 * @author Grupo ASD
 */
public interface IUsuario {
    public Boolean recuperarContrase├▒a(String usuario, Boolean esId) throws Exception;
    public void establecerContrase├▒aUsuarioNuevo(String usuario, Boolean esId) throws Exception;
    public Boolean esContrase├▒aReseteada(String usuario) throws Exception;
    public Boolean cambiarContrasena(String nuevoPassword, String usuario) throws Exception;
    public Boolean bloqueaUsuario(String id) throws Exception;
    public Boolean compruebaCambioContrasena(EmpleadoSesion empleado) throws Exception;
    public Boolean estaBloqueado(EmpleadoSesion empleado) throws Exception;
}
