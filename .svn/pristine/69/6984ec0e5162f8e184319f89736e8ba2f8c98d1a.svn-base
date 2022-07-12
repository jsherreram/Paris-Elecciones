/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.gestionmediopago;

import co.com.grupoasd.nomina.dao.GestionPagosDao;
import co.com.grupoasd.nomina.modelo.MiAsignacion;
import co.com.grupoasd.nomina.modelo.MiAsistencia;
import co.com.grupoasd.nomina.modelo.MiCoordinador;
import java.util.List;

/**
 *
 * @author ASD
 */
public class MiAsignacionBussines implements IAsignacion {

    @Override
    public List<MiAsignacion> listar(int idEmp, int idPrueba, String cargoNombre) {
        GestionPagosDao gestiondao = new GestionPagosDao();
        List<MiAsignacion> lstMiAsignacion = gestiondao.listarAsignacion(idEmp,idPrueba,cargoNombre);
        MiCoordinador coordinador;
        for (int i = 0; i < lstMiAsignacion.size(); i++) {
                    coordinador=gestiondao.listarCoordinador(lstMiAsignacion.get(i).getIddivipol(),lstMiAsignacion.get(i).getId());
                    lstMiAsignacion.get(i).setCelular((coordinador.getCelular()));
                    lstMiAsignacion.get(i).setNodnombres((coordinador.getNodnombres()));
                    lstMiAsignacion.get(i).setEmail((coordinador.getEmail()));
		}
        return lstMiAsignacion;
    }
    
}
