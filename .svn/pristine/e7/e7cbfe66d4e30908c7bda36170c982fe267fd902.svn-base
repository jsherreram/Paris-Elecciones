/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.gestionmediopago;

import co.com.grupoasd.nomina.dao.GestionPagosDao;
import co.com.grupoasd.nomina.modelo.MisViaticos;
import java.util.List;

/**
 *
 * @author ASD
 */
public class MisViaticosBussines implements IMedioViaticos {

    @Override
    public List<MisViaticos> listar(int idEmp,int idPrueba, String cargoNombre) {
        GestionPagosDao gestiondao = new GestionPagosDao();
        List<MisViaticos> MisViaticos = gestiondao.listarViaticos(idEmp,idPrueba,cargoNombre);
        return MisViaticos;
    }
}
