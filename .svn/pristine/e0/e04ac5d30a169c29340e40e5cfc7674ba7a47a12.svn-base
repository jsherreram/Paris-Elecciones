package co.com.grupoasd.nomina.negocio.prueba;

import co.com.grupoasd.nomina.modelo.EmpleadoSesion;
import co.com.grupoasd.nomina.modelo.Prueba;
import java.util.List;

/**
 *
 * @author Grupo ASD
 */
public interface IPrueba {
    public List<Prueba> listar();

    public List<Prueba> listarnocerradas();

    public int Insertar(Prueba p);

    public Prueba GetById(int id);

    public List<Prueba> listarxFecha(String fechaInicial, String fechaFinal) throws Exception;

    public List<Prueba> listarxEstado(String Estado,List<Long> sitios);

    public List<Prueba> listarOnDivitrans();

    public List<Prueba> listarAll(int idPrueba);

    public Boolean Actualizar (Prueba p);

}

