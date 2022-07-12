/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.portal.servlet;

import co.com.grupoasd.biometria.icfes.Huella;
import co.com.grupoasd.biometria.notificacionServidor.RequestIdentification;
import co.com.grupoasd.nomina.dao.UsuarioSitioDao;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.UsuarioSitio;
import co.com.grupoasd.nomina.negocio.empleado.IEmpleadoImpl;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Clase cliente de prueba que permite llamar la funcionalidad de Identificación
 * de biometria
 *
 * @author Administrador
 */
@WebServlet(name = "Identificacion", urlPatterns = {"/Identificacion"})
public class RequestIdentificationC extends RequestIdentification {

    private IEmpleadoImpl empleado = new IEmpleadoImpl();
    private UsuarioSitioDao usuarioSitio= new UsuarioSitioDao();

    /**
     * Metodo donde se añaden los parametros a enviar al componente de biometría
     */
    @Override
    public void addParameters(HttpServletRequest request) {
        //Se envian todas las huellas contra las que se quiere comparar    
        String idEvento = request.getParameter("idEvento");
        String usuario = request.getUserPrincipal().getName();
        
        
        List<Empleado> lstEmpleados;
        if (request.isUserInRole("coordinador") || request.isUserInRole("capacitador"))
        {
            List<UsuarioSitio> oUsuarioSitos = usuarioSitio.GetSitiosByUsuario(Integer.parseInt(request.getUserPrincipal().getName()));
            lstEmpleados = empleado.getUsuariosYHuellasSitios(Integer.parseInt(idEvento), oUsuarioSitos);
        }else
        {
            //se obtiene el idDivipol según el usuario autenticado
            UsuarioSitio oUsuario =usuarioSitio.GetByUsuario(Integer.parseInt(request.getUserPrincipal().getName()));

            //se traen todos los empleados que estan hbilitados para un evento y sitio particular
            lstEmpleados = empleado.getUsuariosYHuellas(Integer.parseInt(idEvento), oUsuario.getIdDivipol());
        }
        
       //lisatdo con el objeto Huella propio del plugin de Biometría
        List<Huella> lstHuellas = new ArrayList<Huella>();

        for (Empleado empleado : lstEmpleados) {
            Huella huella = new Huella();
            huella.setHuella(empleado.getHuella());
 
            //Llave de la huella
           huella.setLlave(MessageFormat.format("{0};{1};{2};{3};{4}", 
                    String.valueOf(empleado.getIdEmpleado()),
                    String.valueOf(Integer.parseInt(idEvento)),
                    String.valueOf(empleado.getIdDivipolAsistencia()),
                    String.valueOf("X"),
                    String.valueOf(usuario)));
      
            //huella.setLlave("sss");
            lstHuellas.add(huella);
        }
        
        super.getMessageRequest().setLstHuellas(lstHuellas);
    }

   

}
