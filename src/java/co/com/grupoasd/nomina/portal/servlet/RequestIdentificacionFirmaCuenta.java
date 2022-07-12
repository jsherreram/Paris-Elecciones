/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.portal.servlet;

import co.com.grupoasd.biometria.icfes.Huella;
import co.com.grupoasd.biometria.icfes.VOMessageMatchResponse;
import co.com.grupoasd.biometria.notificacionServidor.RequestIdentification;
import co.com.grupoasd.nomina.dao.UsuarioSitioDao;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.EmpleadoSesion;
import co.com.grupoasd.nomina.modelo.UsuarioSitio;
import co.com.grupoasd.nomina.modelo.wrapper.Llave;
import co.com.grupoasd.nomina.negocio.empleado.IEmpleadoImpl;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Clase cliente de prueba que permite llamar la funcionalidad de Identificación
 * de biometria
 *
 * @author Administrador
 */
@WebServlet(name = "IdentificacionCuenta", urlPatterns = {"/IdentificacionCuenta"})
public class RequestIdentificacionFirmaCuenta extends RequestIdentification {

    private IEmpleadoImpl empleado = new IEmpleadoImpl();
 

    /**
     * Metodo donde se añaden los parametros a enviar al componente de biometría
     */
    @Override
    public void addParameters(HttpServletRequest request) {
        //Se envian todas las huellas contra las que se quiere comparar    
        int id =Integer.parseInt(request.getParameter("idEmpleado"));
        int idPrueba=Integer.parseInt(request.getParameter("idPrueba"));
        String cargo=request.getParameter("codCargo");
        String tipo=request.getParameter("tipo");
        
        
       //lisatdo con el objeto Huella propio del plugin de Biometría
        List<Huella> lstHuellas = new ArrayList<Huella>();

        Empleado empl=empleado.getHuellaEmpleadoById(id);
        
            Huella huella = new Huella();
            huella.setHuella(empl.getHuella());
            
            //Llave de la huella
           huella.setLlave(MessageFormat.format("{0};{1};{2};{3};{4}", 
                    String.valueOf(empl.getNrodoc()),
                    String.valueOf(idPrueba),
                    String.valueOf(cargo),
                    String.valueOf(tipo),
                    String.valueOf(id)));

            //huella.setLlave("sss");
            lstHuellas.add(huella);

        super.getMessageRequest().setLstHuellas(lstHuellas);
    }

   

}
