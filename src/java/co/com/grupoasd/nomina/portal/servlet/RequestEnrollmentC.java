/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.portal.servlet;

import co.com.grupoasd.biometria.notificacionServidor.RequestEnrollment;
import java.util.Random;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;


/**
 * Clase cliente de prueba que permite llamar la funcionalidad para enrolar de
 * biometria
 *
 * @author Administrador
 */

@WebServlet(name = "Enrolamiento", urlPatterns = {"/Enrolamiento"})
public class RequestEnrollmentC extends RequestEnrollment {

    private Random rnd = new Random(0);

    /**
     * Método que añade los parametros a ser enviados al componente de biometría
     */
    @Override
    public void addParameters(HttpServletRequest request) {
        
        String llave = request.getParameter("llave");
        
        
        //Se coloca la llave con la que se va ha relacionar la huella
         
        super.getMessageRequest().setLlave(llave);
    }
   
}
