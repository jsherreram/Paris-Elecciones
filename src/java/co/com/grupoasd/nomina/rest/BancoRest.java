/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.modelo.Banco;
import co.com.grupoasd.nomina.negocio.mediopago.BancoController;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Pedro Rodríguez
 */

@Path("/banco")
public class BancoRest {

    @GET
    @Path("/listar")
    @Produces({"application/json", "application/xml"})
    public List<Banco> listar() {
        List<Banco> lstBanco = new ArrayList<>();
        try {
            BancoController objController = new BancoController();
            lstBanco = objController.getListBancos();
        } catch (Exception ex) {
            Logger.getLogger(BancoRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstBanco;
    }

}
