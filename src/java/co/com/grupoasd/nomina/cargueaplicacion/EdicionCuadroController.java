/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.cargueaplicacion;
import co.com.grupoasd.nomina.dao.EdicionCuadroDao;
import co.com.grupoasd.nomina.dto.DataFiltros;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author Pedro Rodríguez
 */
public class EdicionCuadroController {
    
    public EdicionCuadroController()
    {
    }
    
    public JSONArray ConsultarCuadroAplicacion(int idPrueba, String codigoSitio){
        EdicionCuadroDao dao = new EdicionCuadroDao();
        return dao.ConsultarEstadoCuadroSinEvento(idPrueba, codigoSitio);
    }
    public JSONArray ConsultarCuadroPrueba(int idPrueba, String usuario){
        EdicionCuadroDao dao = new EdicionCuadroDao();
        return dao.ConsultarCuadroPrueba(idPrueba, usuario);
    }
    
    public JSONArray ConsultarFiltros(DataFiltros data) throws JSONException{
        EdicionCuadroDao dao = new EdicionCuadroDao();
        return dao.ConsultarFiltro(data);
    }

    public JSONArray Consulta(DataFiltros data) throws JSONException{
        EdicionCuadroDao dao = new EdicionCuadroDao();
        return dao.Consulta(data);
    }
    
}
