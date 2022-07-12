package co.com.grupoasd.nomina.negocio.estadopersonaprueba;

import co.com.grupoasd.nomina.modelo.EmpleadoPruebaEstado;
import co.com.grupoasd.nomina.modelo.EstadoPersonaPrueba;
import java.util.List;

/**
 *
 * @author Grupo ASD
 */
public interface IEstadoPersonaPrueba {

    /**
     *
     * @return
     */
    public List<EstadoPersonaPrueba> getAll();
    
    public EmpleadoPruebaEstado getEstadoActual (int nrodoc, int idPrueba, String codigoCargo);
    
}
