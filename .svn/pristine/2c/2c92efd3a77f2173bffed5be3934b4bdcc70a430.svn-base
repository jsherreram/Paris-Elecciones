/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.dto.CallAttribute;
import co.com.grupoasd.nomina.dto.CallInfo;
import co.com.grupoasd.nomina.modelo.Campagna;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Rodríguez
 */
public class CampagnaDao {

    private Operaciones conex = new Operaciones();

    public CampagnaDao() {
    }

    public List<Campagna> listar() {
        final List<Campagna> lstCampagna = new ArrayList<>();

        try {
            StringBuilder query = new StringBuilder();
            query.append(" select id, name "
                    + " from cpp_campaign "
                    + " where estatus not in('T') "
                    + " order by id; ");

            conex.consultar(query.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Campagna campagna = new Campagna();
                            campagna.setIdcampagna(res.getInt("id"));
                            campagna.setNombre(res.getString("name"));
                            lstCampagna.add(campagna);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(CampagnaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (Exception e) {
            Logger.getLogger(CampagnaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return lstCampagna;
    }

    /**
     * Metodo Dao encargado de llamar al procedimiento almacenado que realiza la
     * insercion en la tablas para la gestion de Dinomic
     *
     * @param idPrueba
     * @param idCampagna
     * @param idEstado
     * @param idCargo
     * @param idNodo
     * @param idMunicipio
     * @param urlConvocatoria
     * @return
     */
    public List<CallInfo> consultarDatosCampagna(int idPrueba, int idCampagna, int idEstado,
            int idCargo, String idNodo, String idMunicipio, String urlConvocatoria) {
        final List<CallInfo> infoLlamdas = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("select e.celular as celular, ");
        sql.append("concat(e.nombre1,' ',e.nombre2,' ',e.apellido1,' ',e.apellido2) as nombres, ");
        sql.append("e.nrodoc as nrodoc , m.nombre as ciudad, ca.descripcion as cargo, ");
        sql.append("case when idestpersona = 3 then ");
        sql.append("concat('").append(urlConvocatoria).append("encuesta/index.jsp#/',md5(concat(eps.id,'-', eps.idempleado)),'/1') ");
        sql.append("else ");
        sql.append("concat('").append(urlConvocatoria).append("asistenciaReunionPrevia/index.jsp#/', md5(concat(eps.id,'-', eps.idempleado))) ");
        sql.append("end as link ");
        sql.append("from empleado_x_prueba_x_estado eps ");
        sql.append("left join empleado e on eps.idempleado = e.idEmpleado ");
        sql.append("left join municipio_dane m on m.codigoMunicipio = e.DaneMunicipio ");
        sql.append("left join cargos ca on ca.codigoCargo = eps.codigoCargo ");
        sql.append("left join divipol divi on eps.iddivipol = divi.idDivipol ");
        sql.append("where eps.idprueba = ").append(idPrueba);
        sql.append(" and idestpersona = ").append(idEstado);
        sql.append(" and case when '").append(idNodo).append("' != '0' then divi.codigoDepartamento = '").append(idNodo).append("' else 1 = 1 end ");
        sql.append(" and case when '").append(idMunicipio).append("' != '0' then divi.codigoMunicipio = '").append(idMunicipio).append("' else 1 = 1 end ");
        sql.append(" and case when ").append(idCargo).append(" != '0' then eps.codigoCargo = ").append(idCargo).append(" else 1 = 1 end ");
        sql.append(" and length(trim(replace(e.celular,' ',''))) = 10 ");
        sql.append(" and e.celular not like '%00000000%' ");

        Logger.getLogger(CampagnaDao.class.getName()).log(Level.INFO, sql.toString());
        conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    while (resultado.next()) {
                        CallInfo callInfo = new CallInfo();
                        callInfo.setCelular(resultado.getString("celular"));
                        List<CallAttribute> listaAtributos = new ArrayList<>();
                        listaAtributos.add(new CallAttribute("NOMBRE", resultado.getString("nombres")));
                        listaAtributos.add(new CallAttribute("CARGO", resultado.getString("cargo")));
                        listaAtributos.add(new CallAttribute("LINK", resultado.getString("link")));
                        listaAtributos.add(new CallAttribute("DOCUMENTO", resultado.getString("nrodoc")));
                        listaAtributos.add(new CallAttribute("CIUDAD", resultado.getString("ciudad")));
                        callInfo.setListAttributes(listaAtributos);
                        infoLlamdas.add(callInfo);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CampagnaDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return infoLlamdas;
    }

    /**
     * Metodo Dao que consulta los datos a ser insertados en la campagna
     *
     * @param idPrueba
     * @param idCampagna
     * @param idProgreso
     * @param idEvento
     * @return
     */
    public List<CallInfo> consultarDatosCampagnaEncuesta(int idPrueba, int idCampagna, int idProgreso, String idEvento) {
        final List<CallInfo> infoLlamdas = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        sql.append("em.celular as celular, ");
        sql.append("concat(em.nombre1,' ',em.nombre2,' ',em.apellido1,' ',em.apellido2) as nombres, ");
        sql.append("em.nrodoc as nrodoc, ");
        sql.append("dp.nombrePuesto as nombreSitio, ");
        sql.append("dp.codigoPuesto as codigoSitio, ");
        sql.append("dcpe.codigoCargo as cargo, ");
        sql.append("concat('http://paris.operadorlogisticoicfes.com/Icfes/panel/ReportarEncuesta/main.jsp#/Encuesta/',ev.codigoEvento,'/',dp.idDivipol,'/1/1/1') as link ");
        sql.append("from divipol dp ");
        sql.append("left join detalle_cargo_x_puesto_x_evento dcpe on dcpe.idDivipol = dp.idDivipol ");
        sql.append("inner join empleado em on dcpe.nrodoc = em.nrodoc ");
        sql.append("inner join evento ev on dcpe.codigoEvento = ev.codigoEvento ");
        sql.append("left join encuesta en on en.idDivipol = dp.idDivipol ");
        sql.append("where dcpe.idPrueba = ").append(idPrueba);
        sql.append(" and dcpe.codigoCargo = '12' ");
        sql.append(" and ev.codigoEvento = ").append(idEvento);
        sql.append(" and char_length(dp.codigoPuesto) = 8 ");
        sql.append(" and ");
        sql.append(" case ").append(idProgreso);
        sql.append(" when 1 then en.idEncuesta is null  ");
        sql.append(" when 2 then (en.idEncuesta is null or en.estadoEncuesta = 1) ");
        sql.append(" when 3 then (en.idEncuesta is null or en.estadoEncuesta in (1,2)) ");
        sql.append(" else 1 = 0 ");
        sql.append(" end ");
        sql.append(" and length(trim(replace(em.celular,' ',''))) = 10 ");
        sql.append(" and em.celular not like '%00000000%' ");
        Logger.getLogger(CampagnaDao.class.getName()).log(Level.INFO, sql.toString());
        conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    while (resultado.next()) {
                        CallInfo callInfo = new CallInfo();
                        callInfo.setCelular(resultado.getString("celular"));
                        List<CallAttribute> listaAtributos = new ArrayList<>();
                        listaAtributos.add(new CallAttribute("NOMBRE", resultado.getString("nombres")));
                        listaAtributos.add(new CallAttribute("DOCUMENTO", resultado.getString("nrodoc")));
                        listaAtributos.add(new CallAttribute("NOMBRE SITIO", resultado.getString("nombreSitio")));
                        listaAtributos.add(new CallAttribute("CODIGO SITIO", resultado.getString("codigoSitio")));
                        listaAtributos.add(new CallAttribute("CODIGO CARGO", resultado.getString("cargo")));
                        listaAtributos.add(new CallAttribute("LINK", resultado.getString("link")));
                        callInfo.setListAttributes(listaAtributos);
                        infoLlamdas.add(callInfo);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CampagnaDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return infoLlamdas;
    }

}
