/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.usuario;

import co.com.grupoasd.nomina.common.util.SendMailUtil;
import co.com.grupoasd.nomina.dao.UsuarioDAO;
import co.com.grupoasd.nomina.modelo.EmpleadoSesion;
import co.com.grupoasd.nomina.modelo.EnvioCorreo;
import co.com.grupoasd.nomina.modelo.ParametrosCorreo;
import co.com.grupoasd.parametroscorreo.SingletonCorreo;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Grupo ASD
 */
public class UsuarioController implements IUsuario {

    @Override
    public Boolean recuperarContraseña(String usuario, Boolean esId) throws Exception{
        EnvioCorreo correo = new EnvioCorreo();
        String direccionCorreo = "";
        String clave = "";
        String claveSHA = "";
        StringBuilder sb = new StringBuilder();
        ParametrosCorreo parametros = new ParametrosCorreo();
        HashMap correos = new HashMap();
        SingletonCorreo correosS = SingletonCorreo.getInstance();
        correos = correosS.getCorreos();
        parametros = (ParametrosCorreo) correos.get("recuperacontrasena");
        try{
            UsuarioDAO dao = new UsuarioDAO();
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            Random randomGenerator = new Random();
            clave = String.valueOf(randomGenerator.nextInt(999999));
            md.update(clave.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb1 = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb1.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            
            claveSHA = sb1.toString();
            
            if(esId){
                dao.obtenerContraseñaxIdUsuario(usuario, claveSHA);
                direccionCorreo = dao.obtenerCorreoUsuario(usuario);
            }
            else
            {
                dao.obtenerContraseña(usuario, claveSHA);
                return false;
            }    
            
            correo.setCorreoDestino(direccionCorreo);
            correo.setCorreoRemite(parametros.getUserEmail());
            correo.setUsuarioSMTP(parametros.getUserEmailSmtp());
            correo.setPassSMTP(parametros.getPasswordEmail());
            correo.setTituloCorreo("Recuperacion de contraseña");
            sb.append("La contraseña es:  ");
            sb.append(clave);
            sb.append("    El sistema solicitará un cambio en su próximo ingreso.");
            correo.setCuerpoCorreo(sb.toString());
            new SendMailUtil().enviarCorreo(correo,parametros);
            return true;
        }
        catch(Exception e){
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
        //return clave;
    }
    
    
    @Override
    public void establecerContraseñaUsuarioNuevo(String usuario, Boolean esId) throws Exception{
        EnvioCorreo correo = new EnvioCorreo();
        String direccionCorreo = "";
        String clave = "";
        String claveSHA = "";
//        ParametrosCorreo parametros = new ParametrosCorreo();
//        HashMap correos = new HashMap();
//        SingletonCorreo correosS = SingletonCorreo.getInstance();
//        correos = correosS.getCorreos();
//        parametros = (ParametrosCorreo) correos.get("creacionusuario");
        StringBuilder sb = new StringBuilder();
        try{
            UsuarioDAO dao = new UsuarioDAO();
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            Random randomGenerator = new Random();
            clave = String.valueOf(randomGenerator.nextInt(999999));
            md.update(clave.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb1 = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
             sb1.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            
            claveSHA = sb1.toString();
            
            if(esId){
                dao.obtenerContraseñaxIdUsuario(usuario, claveSHA);
                direccionCorreo = dao.obtenerCorreoUsuario(usuario);
            }
            else
            {
                dao.obtenerContraseña(usuario, claveSHA);
                return;
            }    
//            correo.setCorreoDestino(direccionCorreo);
//            
//            correo.setCorreoRemite(parametros.getUserEmail());
//            correo.setUsuarioSMTP(parametros.getUserEmailSmtp());
//            correo.setPassSMTP(parametros.getPasswordEmail());
//            correo.setTituloCorreo("Creación de Usuario Paris Elecciones");
//            
//            sb.append("Bienvenido! Se ha creado un usuario para que acceda al sistema PARIS \n");
//            sb.append("Por favor acceda al siguiente link: http://190.60.206.155/Paris \n");
//            sb.append("En usuario ingrese su Número de identificación \n");
//            sb.append("La contraseña es: ");
//            sb.append(clave);
//            sb.append("  ");
//            correo.setCuerpoCorreo(sb.toString());
//            new SendMailUtil().enviarCorreo(correo,parametros);
        }
        catch(Exception e){
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    

    
    
    public static void main (String[]args) throws Exception{
        new UsuarioController().recuperarContraseña("119874",true);
                                                     
        
    }

    @Override
    public Boolean esContraseñaReseteada(String usuario) throws Exception {
        return new UsuarioDAO().esContraseñaReseteada(usuario);
    }

    @Override
    public Boolean cambiarContrasena(String nuevoPassword, String usuario) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        Boolean respuesta = false;
        
        md.update(nuevoPassword.getBytes());
        byte byteData[] = md.digest();
        StringBuffer sb1 = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb1.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        respuesta = new UsuarioDAO().cambiarContraseña(sb1.toString(), usuario);
        
        return respuesta;
    }

    @Override
    public Boolean bloqueaUsuario(String id) throws Exception {
        return new UsuarioDAO().bloqueaUsuario(id);
    }

    @Override
    public Boolean compruebaCambioContrasena(EmpleadoSesion empleado) throws Exception {
        return new UsuarioDAO().verificaCambio(empleado);
    }

    @Override
    public Boolean estaBloqueado(EmpleadoSesion empleado) throws Exception {
        return new UsuarioDAO().estaBloqueado(empleado);
    }
    
}
