/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.CantidadPds;
import co.com.grupoasd.nomina.modelo.Pds;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CristianAlexander
 */
public class PdsDao {

    private Operaciones conex = new Operaciones();

    public List<Pds> listarpdsxSitio(String sitio, int prueba) throws Exception {
        final ArrayList<Pds> listaPds = new ArrayList<Pds>();
        StringBuilder sb = new StringBuilder();
        sb.append("select idDivipolSitio,idDivipolPds,prioridad,c.nombrePuesto ");
        sb.append("from relacion_pds a ");
        sb.append("inner join divipol c on (a.idDivipolPds = c.iddivipol and c.idPrueba = a.idPrueba) ");
        sb.append("where a.idDivipolSitio = ");
        sb.append(sitio);
        sb.append(" and a.idPrueba = ");
        sb.append(prueba);        
        sb.append(" order by prioridad ");
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        Pds pds = new Pds();
                        pds.setIdDivipolSitio(resultado.getLong(1));
                        pds.setIdDivipolPds(resultado.getLong(2));
                        pds.setPrioridad(resultado.getInt(3));
                        pds.setNombrePds(resultado.getString(4));
                        listaPds.add(pds);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PdsDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return listaPds;
    }

    public CantidadPds consultarCantidadVacantes(String sitio, String cargo, String evento) {
        StringBuilder sb = new StringBuilder();
        final ArrayList<Long> documentos = new ArrayList<Long>();
        final CantidadPds cantidad = new CantidadPds();
        sb.append("select distinct a.nrodoc  ");
        sb.append("from detalle_cargo_x_puesto_x_evento a ");
        sb.append("inner join empleado b on (a.nrodoc = b.nrodoc and a.nrodoc > 0) ");
        sb.append("where codigoCargo = ");
        sb.append(cargo);
        sb.append(" and idDivipol = ");
        sb.append(sitio);
        sb.append(" and codigoEvento = ");
        sb.append(evento);
        sb.append(" and (select count(*) from suplenciaxdetalle z where z.nrodoc = a.nrodoc and idEstadoAtencionOrden in (1,2,3)) < 1 ");
        sb.append(" and (select count(0) from asistencia x where x.codigoevento = a.codigoevento  and x.iddivipol = a.idDivipol  and x.idEmpleado = b.idEmpleado) > 0");

        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();

                    while (resultado.next()) {
                        documentos.add(resultado.getLong(1));
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(PdsDao.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        cantidad.setCantidad(documentos.size());
        cantidad.setNrodocs(documentos);
        return cantidad;
    }

    public Pds buscarPdsxSitioxPrioridad(int idsitio, int idpds, int prioridad) throws Exception {
        final Pds pds = new Pds();
        StringBuilder sb = new StringBuilder();
        sb.append("select * ");
        sb.append("from relacion_pds a ");
        sb.append("where  idDivipolSitio=");
        sb.append(idsitio);

        if (idpds != 0) {
          sb.append(" and idDivipolPds=");
          sb.append(idpds);
        }
        if (prioridad != 0) {
          sb.append(" and prioridad=");
          sb.append(prioridad);
        }
        
        sb.append(" limit 1");
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        pds.setIdDivipolSitio(resultado.getLong("idDivipolSitio"));
                        pds.setIdDivipolPds(resultado.getLong("idDivipolPds"));
                        pds.setPrioridad(resultado.getInt("prioridad"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PdsDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return pds;
    }

}
