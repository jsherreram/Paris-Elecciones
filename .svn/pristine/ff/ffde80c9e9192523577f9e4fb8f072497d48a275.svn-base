/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.gestionmediopago;

import co.com.grupoasd.nomina.dao.GestionPagosDao;
import co.com.grupoasd.nomina.modelo.MiAsistencia;
import java.util.List;

/**
 *
 * @author ASD
 */
public class MiAsistenciaBussines implements IMiAsistencia {

    @Override
    public List<MiAsistencia> listar(int idEmp, int idPrueba, String cargoNombre) {
        GestionPagosDao gestiondao = new GestionPagosDao();
        List<MiAsistencia> lstAsistencia = gestiondao.listarAsistencia(idEmp,idPrueba,cargoNombre);
        return lstAsistencia;
    }
    
}
