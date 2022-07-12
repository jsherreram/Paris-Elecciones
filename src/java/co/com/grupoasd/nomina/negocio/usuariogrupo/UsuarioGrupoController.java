/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.usuariogrupo;

import co.com.grupoasd.nomina.common.util.SendMailUtil;
import co.com.grupoasd.nomina.dao.UsuarioGrupoDao;
import co.com.grupoasd.nomina.modelo.EnvioCorreo;
import co.com.grupoasd.nomina.modelo.ParametrosCorreo;
import co.com.grupoasd.nomina.modelo.Usuario;
import co.com.grupoasd.nomina.modelo.UsuarioGrupo;
import co.com.grupoasd.parametroscorreo.SingletonCorreo;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Grupo ASD
 */
public class UsuarioGrupoController implements IUsuarioGrupo {
    
    public static void main(String[]args){
        UsuarioGrupo grupo = new UsuarioGrupo();
        List<UsuarioGrupo> usuarioGrupos = new ArrayList<UsuarioGrupo>();
        grupo.setGrupo("rector");
        Usuario usuario = new Usuario();
        
        usuario.setCorreo("prodriguez@grupoasd.com.co");
        usuario.setPasswd("123456789");
        grupo.setUsuario(usuario);
        usuarioGrupos.add(grupo);
        grupo = new UsuarioGrupo();
        usuario = new Usuario();
        grupo.setGrupo("rector");
        
        usuario.setCorreo("croldan@grupoasd.com.co");
        usuario.setPasswd("123456789");
        grupo.setUsuario(usuario);
        usuarioGrupos.add(grupo);
        grupo = new UsuarioGrupo();
        usuario = new Usuario();
        grupo.setGrupo("rector");
        
        usuario.setCorreo("krozel.sephiroth@gmail.com");
        usuario.setPasswd("123456789");
        grupo.setUsuario(usuario);
        usuarioGrupos.add(grupo);
        new UsuarioGrupoController().asignarGrupo(usuarioGrupos);
    }

    @Override
    public void asignarGrupo(List<UsuarioGrupo> usuarioGrupos) {
        UsuarioGrupoDao dao = new UsuarioGrupoDao();
        dao.asignarGrupos(usuarioGrupos);        
//        ParametrosCorreo parametros = new ParametrosCorreo();
//        HashMap correos = new HashMap();
//        SingletonCorreo correosS = SingletonCorreo.getInstance();
//        correos = correosS.getCorreos();
//        parametros = (ParametrosCorreo) correos.get("recuperacontrasena");
//        for(UsuarioGrupo grupo : usuarioGrupos){            
//            //supongo que se llamara "rector"... considerar colocoarle a la tabla una equivalencia en códigos
//            //Envia el correo de creación de usuario
//            if(grupo.getGrupo().equals("rector")){
//                EnvioCorreo correo = new EnvioCorreo();
//                correo.setCorreoDestino(grupo.getUsuario().getCorreo());
//                correo.setCorreoRemite(parametros.getUserEmail());
//                correo.setUsuarioSMTP(parametros.getUserEmailSmtp());
//                correo.setPassSMTP(parametros.getPasswordEmail());                
//                correo.setTituloCorreo("Información importante - proceso ICFES -- PRUEBA");
//                correo.setCuerpoCorreo("Señor /*hace falta el campo en la bd*/, su usuario como rector de xxx ha sido creado, a continuación informamos el usuario y la contraseña...");
//                
//                new SendMailUtil().enviarCorreo(correo,parametros);
//            }                
//        }
    }
    
    
}
