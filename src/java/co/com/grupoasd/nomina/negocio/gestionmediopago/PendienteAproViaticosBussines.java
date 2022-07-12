/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.gestionmediopago;

import co.com.grupoasd.nomina.dao.GestionPagosDao;
import co.com.grupoasd.nomina.modelo.PendienteAprobarViatico;
import java.util.List;

/**
 *
 * @author Wilfer
 */
public class PendienteAproViaticosBussines implements IPendienteAprobarViaticos{
    @Override
    public List<PendienteAprobarViatico> listar(int idEmp,int idPrueba, String codCargo) {
        GestionPagosDao gestiondao = new GestionPagosDao();
        List<PendienteAprobarViatico> MisViaticos = gestiondao.AprobarViaticos(idEmp,idPrueba,codCargo);
        return MisViaticos;
    }
}
