package co.com.grupoasd.nomina.negocio.estadopersonaprueba;

import co.com.grupoasd.nomina.dao.EstadoPersonaPruebaDAO;
import co.com.grupoasd.nomina.modelo.EmpleadoPruebaEstado;
import co.com.grupoasd.nomina.modelo.EstadoPersonaPrueba;
import java.util.List;

/**
 *
 * @author Grupo ASD
 */
public class EstadoPersonaPruebaController implements IEstadoPersonaPrueba {

    /**
     * 
     * @return 
     */
    @Override
    public List<EstadoPersonaPrueba> getAll() {
        EstadoPersonaPruebaDAO dao = new EstadoPersonaPruebaDAO();
        return dao.getAll();
    }

    @Override
    public EmpleadoPruebaEstado getEstadoActual(int nrodoc, int idPrueba, String codigoCargo) {
        EstadoPersonaPruebaDAO dao = new EstadoPersonaPruebaDAO();
        return dao.getEstadoActual(nrodoc, idPrueba, codigoCargo);        
    }

}
