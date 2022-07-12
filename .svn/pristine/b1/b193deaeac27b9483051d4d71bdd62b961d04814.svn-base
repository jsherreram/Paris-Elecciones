/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.prueba;

import co.com.grupoasd.nomina.dao.PruebaDao;
import co.com.grupoasd.nomina.modelo.Prueba;
import java.util.List;

/**
 *
 * @author Pedro Rodríguez
 */
public class PruebaBusiness  implements IPrueba{

    @Override
    public List<Prueba> listar() {
        PruebaDao pruebaDao = new PruebaDao();
        List<Prueba> Pruebas = pruebaDao.listar();
        return Pruebas;        
    }

    public List<Prueba> listarnocerradas() {
        PruebaDao pruebaDao = new PruebaDao();
        List<Prueba> Pruebas = pruebaDao.listarnocerradas();
        return Pruebas;        
    }

    public List<Prueba> listarOnDivitrans() {
        PruebaDao pruebaDao = new PruebaDao();
        List<Prueba> Pruebas = pruebaDao.listarOnDivitrans();
        return Pruebas;        
    }
    
    @Override
    public int Insertar(Prueba p) {
        PruebaDao pruebaDao = new PruebaDao();
        return pruebaDao.insertar(p);        
    }

    @Override
    public Prueba GetById(int id) {
        PruebaDao pruebaDao = new PruebaDao();
        return pruebaDao.getById(id);        
    }
    
    @Override
    public List<Prueba> listarxFecha(String fechaInicial, String fechaFinal) throws Exception {
        PruebaDao dao = new PruebaDao();
        return dao.listarxFecha(fechaInicial, fechaFinal);
    }

    @Override
    public List<Prueba> listarxEstado(String estado, List<Long> sitios) {
        return new PruebaDao().listarxEstado(estado, sitios);      
    }
    
    @Override
    public List<Prueba> listarAll(int idPrueba) {
            PruebaDao pruebaDao = new PruebaDao();
            List<Prueba> Pruebas = pruebaDao.listarAll(idPrueba);
            return Pruebas;        
    }

    @Override
    public Boolean Actualizar(Prueba p) {
            boolean result;
            PruebaDao dao = new PruebaDao();
            result = dao.Actualizar(p);
            return result;
    }
    
    
}
