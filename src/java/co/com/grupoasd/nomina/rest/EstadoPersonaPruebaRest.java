package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.modelo.EstadoPersonaPrueba;
import co.com.grupoasd.nomina.negocio.estadopersonaprueba.EstadoPersonaPruebaController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


@Path("/estadoPersonaPrueba")
public class EstadoPersonaPruebaRest {
    
    @GET
    @Produces({"application/json","application/xml"})
    public List<EstadoPersonaPrueba> getAll(){
        EstadoPersonaPruebaController controller = new EstadoPersonaPruebaController();
        return controller.getAll();
    }
    
}
