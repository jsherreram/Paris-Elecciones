/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.common.util.SqlUtil;
import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Cargo;
import co.com.grupoasd.nomina.modelo.DetalleCargoPuestoEvento;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.Evento;
import co.com.grupoasd.nomina.modelo.Prueba;
import co.com.grupoasd.nomina.modelo.Sitio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class DetalleCargoPuestoEventoDao {

    private Operaciones conex = new Operaciones();

    /**
     * Registra una persona en un evento y para un sitio determinado
     *
     * @param nroDoc
     * @param codEvento
     * @param idDivipol
     * @return
     */
    public boolean realizarRegistroAsistencia(int nroDoc, int codEvento, int idDivipol, boolean asistenciaXBiometria) {
        try {

            String sql = "update  detalle_cargo_x_puesto_x_evento det   "
                    + "set asistio=1, "
                    + "asistenciaBiometrica=?, "
                    + "fechaAsistencia= current_timestamp "
                    + "where nrodoc = ? and idDivipol= ? and codigoEvento=?";

            return conex.ejecutar(sql, asistenciaXBiometria ? 1 : 0, nroDoc, idDivipol, codEvento);

        } catch (Exception e) {
            Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, e);

        }
        return false;
    }

    /**
     * Metodo Dao encargado de obtener la informacion de la tabla
     * detalle_cargo_x_puesto_x_evento
     *
     * @param documento
     * @param idDcpe
     * @param idPrueba
     * @return
     */
    public DetalleCargoPuestoEvento getByDocumentoAndEvento(long documento, long idDcpe, int idPrueba) {
        final DetalleCargoPuestoEvento detalleCargoPuestoEvento = new DetalleCargoPuestoEvento();
        StringBuilder consulta = new StringBuilder("select ");
        consulta.append("dcpe.id as id, dp.idDivipol as idSitio, dp.codigoPuesto as codigoPuesto, ");
        consulta.append("dp.nombrePuesto as nombrePuesto, dcpe.idPrueba as idPrueba, cg.codigoCargo as codigoCargo, ");
        consulta.append("cg.descripcion as nombreCargo, cg.nivel_cargo as nivelCargo, ");
        consulta.append("em.nrodoc as nroDocEmpleado, em.nombre1 as nombre1, em.nombre2 as nombre2, ");
        consulta.append("em.apellido1 as apellido1, em.apellido2 as apellido2, em.idEmpleado as idEmpleado ");
        consulta.append("from detalle_cargo_x_puesto_x_evento dcpe ");
        consulta.append("inner join cargos cg on dcpe.codigoCargo = cg.codigoCargo ");
        consulta.append("inner join divipol dp on dcpe.idDivipol = dp.idDivipol ");
        consulta.append("inner join empleado em on dcpe.nrodoc = em.nrodoc ");
        consulta.append("where dcpe.codigoEvento = (select codigoEvento from detalle_cargo_x_puesto_x_evento where id = ").append(idDcpe).append(")");
        consulta.append(" and dcpe.nrodoc = ").append(documento);
        SqlUtil.appendParameterWithAnd(consulta, "dcpe.idPrueba", idPrueba);
        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.INFO, consulta.toString());
        try {
            conex.consultar(consulta.toString(), new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet resultado) {
                    try {
                        while (resultado.next()) {
                            detalleCargoPuestoEvento.setId(resultado.getLong("id"));
                            Sitio divipol = new Sitio();
                            divipol.setId(resultado.getInt("idSitio"));
                            divipol.setCodigoSitio(resultado.getString("codigoPuesto"));
                            divipol.setNombreSitio(resultado.getString("nombrePuesto"));
                            detalleCargoPuestoEvento.setDivipol(divipol);
                            Prueba prueba = new Prueba();
                            prueba.setIdprueba(resultado.getInt("idPrueba"));
                            detalleCargoPuestoEvento.setPrueba(prueba);
                            Cargo cargo = new Cargo();
                            cargo.setCodigoCargo(resultado.getString("codigoCargo"));
                            cargo.setDescripcion(resultado.getString("nombreCargo"));
                            cargo.setNivel_cargo(resultado.getInt("nivelCargo"));
                            detalleCargoPuestoEvento.setCargo(cargo);
                            Empleado empleado = new Empleado();
                            empleado.setIdEmpleado(resultado.getInt("idEmpleado"));
                            empleado.setNrodoc(resultado.getLong("nroDocEmpleado"));
                            empleado.setNombre1(resultado.getString("nombre1"));
                            empleado.setNombre2(resultado.getString("nombre2"));
                            empleado.setApellido1(resultado.getString("apellido1"));
                            empleado.setApellido2(resultado.getString("apellido2"));
                            detalleCargoPuestoEvento.setEmpleado(empleado);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(DetalleCargoPuestoEventoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (Exception e) {
            Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return detalleCargoPuestoEvento;
    }

    /**
     * Metodo encargado de consultar la informacion de la tabla segun el id y el
     * id de la prueba
     *
     * @param idDcpe
     * @param idPrueba (opcional)
     * @return
     */
    public DetalleCargoPuestoEvento getById(long idDcpe, int idPrueba) {
        final DetalleCargoPuestoEvento detalleCargoPuestoEvento = new DetalleCargoPuestoEvento();
        StringBuilder consulta = new StringBuilder("select ");
        consulta.append("dcpe.id as id, dp.idDivipol as idSitio, dp.codigoPuesto as codigoPuesto, ");
        consulta.append("dp.nombrePuesto as nombrePuesto, dcpe.idPrueba as idPrueba, cg.codigoCargo as codigoCargo, ");
        consulta.append("cg.descripcion as nombreCargo, cg.nivel_cargo as nivelCargo, ");
        consulta.append("em.nrodoc as nroDocEmpleado, em.nombre1 as nombre1, em.nombre2 as nombre2, ");
        consulta.append("em.apellido1 as apellido1, em.apellido2 as apellido2, em.idEmpleado as idEmpleado ");
        consulta.append("from detalle_cargo_x_puesto_x_evento dcpe ");
        consulta.append("inner join cargos cg on dcpe.codigoCargo = cg.codigoCargo ");
        consulta.append("inner join divipol dp on dcpe.idDivipol = dp.idDivipol ");
        consulta.append("left join empleado em on dcpe.nrodoc = em.nrodoc ");
        consulta.append("where dcpe.id = ").append(idDcpe);
        SqlUtil.appendParameterWithAnd(consulta, "dcpe.idPrueba", idPrueba);
        System.out.println("getDcpeById = " + consulta);
        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.INFO, consulta.toString());
        try {
            conex.consultar(consulta.toString(), new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet resultado) {
                    try {
                        while (resultado.next()) {
                            detalleCargoPuestoEvento.setId(resultado.getLong("id"));
                            Sitio divipol = new Sitio();
                            divipol.setId(resultado.getInt("idSitio"));
                            divipol.setCodigoSitio(resultado.getString("codigoPuesto"));
                            divipol.setNombreSitio(resultado.getString("nombrePuesto"));
                            detalleCargoPuestoEvento.setDivipol(divipol);
                            Prueba prueba = new Prueba();
                            prueba.setIdprueba(resultado.getInt("idPrueba"));
                            detalleCargoPuestoEvento.setPrueba(prueba);
                            Cargo cargo = new Cargo();
                            cargo.setCodigoCargo(resultado.getString("codigoCargo"));
                            cargo.setDescripcion(resultado.getString("nombreCargo"));
                            cargo.setNivel_cargo(resultado.getInt("nivelCargo"));
                            detalleCargoPuestoEvento.setCargo(cargo);
                            Empleado empleado = new Empleado();
                            empleado.setIdEmpleado(resultado.getInt("idEmpleado"));
                            empleado.setNrodoc(resultado.getLong("nroDocEmpleado"));
                            empleado.setNombre1(resultado.getString("nombre1"));
                            empleado.setNombre2(resultado.getString("nombre2"));
                            empleado.setApellido1(resultado.getString("apellido1"));
                            empleado.setApellido2(resultado.getString("apellido2"));
                            detalleCargoPuestoEvento.setEmpleado(empleado);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(DetalleCargoPuestoEventoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (Exception e) {
            Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return detalleCargoPuestoEvento;
    }

    /**
     * Metodo Dao encargao de asignar a un empleado por nrodoc a una
     * detalle_cargo_x_puesto_x_evento
     *
     * @param documento
     * @param idDcpe
     * @return
     */
    public boolean asignarDetalleCargoEvento(long documento, long idDcpe) {
        StringBuilder update = new StringBuilder();
        update.append("update detalle_cargo_x_puesto_x_evento ");
        update.append("set nrodoc = ").append(documento);
        update.append(" where id = ").append(idDcpe);
        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.INFO, update.toString());
        return conex.ejecutar(update.toString());
    }

    /**
     * Author: John Steak Herrera Moreno 
     * Date: 10/06/2022
     *
     * @param idPrueba
     * @param idEvento
     * @param idDivipol
     * @param idCargo
     * @param ubicacion
     * @return
     */
    public DetalleCargoPuestoEvento findEventoAdicional(int idPrueba, int idEvento, long idDivipol, String idCargo, String ubicacion) {

        final DetalleCargoPuestoEvento dcpe = new DetalleCargoPuestoEvento();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM detalle_cargo_x_puesto_x_evento");
            if (idPrueba > 0) {
                sql.append(" WHERE idPrueba = ").append(idPrueba);
                sql.append(" AND codigoEvento = ").append(idEvento);
                sql.append(" AND idDivipol = ").append(idDivipol);
                sql.append(" AND codigoCargo = ").append(idCargo);
                sql.append(" AND ubicacion = ").append(ubicacion);
            }
            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            dcpe.setId(res.getLong("id"));
                            dcpe.setPrueba(new PruebaDao().getById(res.getInt("idprueba")));
                            dcpe.setEvento(new EventoDao().GetById(res.getInt("codigoEvento")));
                            dcpe.setDivipol(new SitioDao().GetById(res.getInt("idDivipol")));
                            dcpe.setCargo(new CargoDao().GetById(res.getString("codigoCargo")));
                            dcpe.setUbicacion(res.getString("ubicacion"));
                            dcpe.setOrden(res.getInt("orden"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(DetalleCargoPuestoEventoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());
        } catch (Exception e) {
            Logger.getLogger(DetalleCargoPuestoEventoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return dcpe;
    }

    /**
     * Author: John Steak Herrera Moreno 
     * Date: 18/05/2022
     *
     * @param detalleCargoPuestoEvento
     * @return
     */
    public long insertDetalleCargoPuestoEvento(DetalleCargoPuestoEvento detalleCargoPuestoEvento) {
        long siguiente = 0;
        StringBuilder sql;

        int idPrueba = detalleCargoPuestoEvento.getPrueba().getIdprueba();
        int idEvento = detalleCargoPuestoEvento.getEvento().getCodigoEvento();
        int idSitio = detalleCargoPuestoEvento.getDivipol().getId();
        String idCargo = detalleCargoPuestoEvento.getCargo().getCodigoCargo();
        String ubi = detalleCargoPuestoEvento.getUbicacion();

        DetalleCargoPuestoEvento dcpe = findEventoAdicional(idPrueba, idEvento, idSitio, idCargo, ubi);

        if (dcpe != null && dcpe.getId() == 0) {
            int cantidad = Integer.parseInt(detalleCargoPuestoEvento.getUbicacion());

            for (int i = 1; i <= cantidad; i++) {
                siguiente = this.siguiente();
                sql = new StringBuilder();

                String ubicacion = String.format("%03d", i);

                sql.append("INSERT INTO detalle_cargo_x_puesto_x_evento (id, idDivipol, idPrueba, codigoDepartamento, "
                        + "codigoMunicipio, codigoZona, codigoPuesto, codigoCargo, codigoEvento, ubicacion, estado, "
                        + "usuario, asistio, cantidadasistio, cantidadnoasistio, codigotarifa, observacion, consecutivo, "
                        + "orden, asistenciaBiometrica, numeroSilla, fecha_actualiza) VALUES (");
                sql.append(Long.toString(siguiente)).append(", ");
                sql.append("").append(detalleCargoPuestoEvento.getDivipol().getId()).append(", ");
                sql.append("").append(detalleCargoPuestoEvento.getPrueba().getIdprueba()).append(", ");
                sql.append("'").append(detalleCargoPuestoEvento.getDivipol().getCodigoDepartamento()).append("', ");
                sql.append("'").append(detalleCargoPuestoEvento.getDivipol().getCodigoMunicipio()).append("', ");
                sql.append("'").append(detalleCargoPuestoEvento.getDivipol().getCodigoZona()).append("', "); // codigoZona, se crea atributo en la clase modelo para poder acceder a su valor.
                sql.append("'").append(detalleCargoPuestoEvento.getDivipol().getCodigoSitio()).append("', "); // codigoPuesto.
                sql.append("'").append(detalleCargoPuestoEvento.getCargo().getCodigoCargo()).append("', ");
                sql.append("").append(detalleCargoPuestoEvento.getEvento().getCodigoEvento()).append(", ");
                sql.append("'").append(ubicacion).append("', "); // ubicacion = # de registros a insertar.
                sql.append("0, ");
                sql.append("'root@%', ");
                sql.append("0, ");
                sql.append("0, ");
                sql.append("0, ");
                sql.append("0, ");
                sql.append("'0', ");
                sql.append("0, ");
                sql.append("").append(i).append(", ");
                sql.append("b'0', ");
                sql.append("0, ");
                sql.append("CURRENT_TIMESTAMP)");
                final Object result[] = new Object[1];
                conex.ejecutar(new Operaciones.OperacionEjecutar() {
                    @Override
                    public void llaveGenerada(ResultSet generatedKeys) {
                        try {
                            if (generatedKeys.next()) {
                                result[0] = (int) generatedKeys.getLong(1);
                            } else {
                                result[0] = 0;
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(DetalleCargoPuestoEventoDao.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }, sql.toString());
                detalleCargoPuestoEvento.setId(siguiente);
            }
        }
        return (siguiente);
    }

    /**
     * Author: John Steak Herrera Moreno 
     * Date: 18/05/2022
     * 
     * @return 
     */
    private long siguiente() {
        final Object id[] = new Object[1];
        id[0] = 0;
        try {
            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            id[0] = res.getLong("siguiente");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(DetalleCargoPuestoEventoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, "SELECT MAX(id) + 1 AS siguiente FROM detalle_cargo_x_puesto_x_evento");
        } catch (Exception e) {
            Logger.getLogger(DetalleCargoPuestoEventoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return (long) id[0];
    }

}
