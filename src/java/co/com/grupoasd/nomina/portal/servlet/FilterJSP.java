/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.portal.servlet;

import co.com.grupoasd.nomina.dao.FuncionDao;
import co.com.grupoasd.nomina.dao.FuncionGrupoDao;
import co.com.grupoasd.nomina.dao.GrupoDao;
import co.com.grupoasd.nomina.modelo.EmpleadoSesion;
import co.com.grupoasd.nomina.modelo.Funcion;
import co.com.grupoasd.nomina.modelo.Grupo;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrador
 */
public class FilterJSP implements Filter {
    

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession sesion = req.getSession();
        EmpleadoSesion emp = (EmpleadoSesion)sesion.getAttribute("empleado");        
        FuncionGrupoDao funcionGrupod = new FuncionGrupoDao();
        //croldan
        //agarra la ruta a la cual intenta entrar, se deja la tercera parte capturada, recordar formato: Icfes/panel/funcionalidad
        String[] partesDir = req.getRequestURI().toString().split("/");
        //croldan
        //Se envia el codigo de la funcionalidad y el codigo del grupo al que pertenece el usuario, si es true hace doFilter, si es false genera error de permisos de acceso
        if (new FuncionGrupoDao().existeFuncion(((Grupo)new GrupoDao().buscarxCodigo(emp.getRolActual())).getIdGrupo(), ((Funcion)new FuncionDao().buscarxCodigo(partesDir[4])).getIdFuncion()))                    
            chain.doFilter(request, response);
        else
            if (new FuncionGrupoDao().existeFuncion(((Grupo)new GrupoDao().buscarxCodigo(emp.getRolActual())).getIdGrupo(), ((Funcion)new FuncionDao().buscarxCodigo(partesDir[3])).getIdFuncion())){
                chain.doFilter(request, response);
            
                    }
            else{
            //croldan
            //Devuelve FORBIDDEN     
                
                res.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
    }

    @Override
    public void destroy() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
