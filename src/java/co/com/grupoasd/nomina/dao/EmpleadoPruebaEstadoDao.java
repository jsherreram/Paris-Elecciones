/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.common.util.SqlUtil;
import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.dto.BusquedaPersonasDto;
import co.com.grupoasd.nomina.modelo.Cargo;
import co.com.grupoasd.nomina.modelo.ConfirmarAsistencia;
import co.com.grupoasd.nomina.modelo.ConfirmarAsistenciaVO;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.EmpleadoPruebaEstado;
import co.com.grupoasd.nomina.modelo.Sitio;
import co.com.grupoasd.nomina.modelo.seguimiento.ResultSetConverter;
import co.com.grupoasd.nomina.modelo.wrapper.EmpleadoPruebaEstadoCargo;
import co.com.grupoasd.nomina.modelo.wrapper.EmpleadoPruebaJoin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.query.SQLBetweenBaseClause;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author Pedro Rodríguez
 */
public class EmpleadoPruebaEstadoDao {

    private Operaciones conex = new Operaciones();

    public List<ConfirmarAsistenciaVO> listar(String usuario) {
        final List<ConfirmarAsistenciaVO> eventos = new ArrayList<ConfirmarAsistenciaVO>();
        StringBuilder sb = new StringBuilder();

        try {

            sb.append("select distinct b.nombre,");
            sb.append("a.fecha,");
            sb.append("a.idprueba,");
            sb.append("d.descripcion, ");
            sb.append("e.descripcion as cargo, ");
            sb.append("b.dias ");
            sb.append("from empleado_x_prueba_x_estado a ");
            sb.append("inner join prueba b on (b.idprueba = a.idprueba) ");
            sb.append("inner join empleado c on (c.idEmpleado = a.idempleado) ");
            sb.append("inner join tipoprueba d on (b.tprueba = d.Idtprueba) ");
            sb.append("inner join cargos e on (e.codigoCargo = a.codigoCargo) ");
            sb.append("where a.idestpersona = 3 ");
            sb.append(" and a.idempleado = ");
            sb.append(usuario);
            sb.append((";"));

            conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        res.beforeFirst();
                        while (res.next()) {
                            ConfirmarAsistenciaVO evento = new ConfirmarAsistenciaVO();
                            evento.setNombrePrueba(res.getString("nombre"));
                            evento.setFechaAplicacion(res.getString("fecha"));
                            evento.setCodigoPrueba(res.getInt("idprueba"));
                            evento.setTipoPrueba(res.getString("descripcion"));
                            evento.setCargo(res.getString("cargo"));
                            evento.setDias(res.getInt("dias"));
                            eventos.add(evento);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoPruebaEstadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return eventos;
    }

    public Boolean actualizarAsistencia(ConfirmarAsistencia asistencia) {
        StringBuilder sb = new StringBuilder();

        sb.append("update empleado_x_prueba_x_estado set ");
        sb.append(" idestpersona = ");
        sb.append(asistencia.getEstado());
        if (asistencia.getEstado().equals(7)) {
            sb.append(", fecha_disponibilidad_inicial = '");
            sb.append(asistencia.getFechaDisponibilidadInicial());
            sb.append("', fecha_disponibilidad_final = '");
            sb.append(asistencia.getFechaDisponibilidadFinal());
            sb.append("'");
        }
        sb.append(", disponibilidad_viaje = ");
        sb.append(asistencia.getDisponibilidadViaje());
        sb.append(", disponibilidad_rural = ");
        sb.append(asistencia.getDisponibilidadRural());
        sb.append(", disponibilidad_urbana = ");
        sb.append(asistencia.getDisponibilidadUrbana());
        sb.append(", trabajo_actual = ");
        sb.append(asistencia.getTrabajoActual());
        sb.append(", disponibilidad_capcacitacion = ");
        sb.append(asistencia.getDisponibilidadCapacitacion());
        sb.append(", observaciones = '");
        sb.append(asistencia.getObservaciones());
        sb.append("' ");
        sb.append("where idempleado = ");
        sb.append(asistencia.getIdUsuario());
        sb.append(" and idprueba = ");
        sb.append(asistencia.getIdPrueba());
        sb.append(";");

        return conex.ejecutar(sb.toString());

    }

    /**
     * Obtiene el empleado por ID añade el nombre del cargo y el nombre del
     * documento
     *
     * @param id
     * @return
     */
    public EmpleadoPruebaJoin GetAllById(int idEmpleado, String codCargo) {

        final EmpleadoPruebaJoin empleado = new EmpleadoPruebaJoin();
        try {
            String sql = "SELECT tipo_iden.descripcion as desIden,empl.* , cargo.descripcion as descCargo FROM empleado empl "
                    + "inner join tipo_iden  on empl.tipodoc=tipo_iden.codigo "
                    + "inner join (select descripcion from  cargos where codigoCargo=?) cargo "
                    + "where idEmpleado = ?";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            empleado.setIdEmpleado(res.getInt("idEmpleado"));
                            empleado.setMunicipio(new MunicipioDao().GetById(res.getString("codigoDepartamento"), res.getString("codigoMunicipio")));
                            empleado.setNrodoc(res.getInt("nrodoc"));
                            empleado.setTipodoc(res.getString("tipodoc"));
                            empleado.setApellido1(res.getString("apellido1"));
                            empleado.setApellido2(res.getString("apellido2"));
                            empleado.setNombre1(res.getString("nombre1"));
                            empleado.setNombre2(res.getString("nombre2"));
                            empleado.setDireccion(res.getString("direccion"));
                            empleado.setTelefono(res.getString("telefono"));
                            empleado.setCelular(res.getString("celular"));
                            empleado.setEmail(res.getString("email"));
                            empleado.setCodigoActividad(res.getString("codigoActividad"));
                            empleado.setEstado(new EstadoDao().GetById(res.getString("codigoEstado")));
                            empleado.setUsuarioCrea(res.getString("usuarioCrea"));
                            empleado.setFechaCrea(res.getDate("fechaCrea"));
                            empleado.setUsuarioModifica(res.getString("usuarioModifica"));
                            empleado.setFechaModifica(res.getDate("fechaModifica"));
                            empleado.setImagen(res.getBoolean("imagen"));
                            empleado.setObservacion(res.getString("observacion"));
                            empleado.setCodigoEstado(res.getInt("codigoEstado"));
                            empleado.setFechaNacimiento(res.getDate("fechaNacimiento"));
                            empleado.setFechaIngreso(res.getDate("fechaIngreso"));
                            empleado.setFechaUltimaAplicacion(res.getString("fechaUltimaAplicacion"));
                            empleado.setTelefono2(res.getString("telefono2"));
                            empleado.setTelefono3(res.getString("telefono3"));
                            empleado.setTelefono4(res.getString("telefono4"));
                            empleado.setTelefono5(res.getString("telefono5"));
                            empleado.setCelular2(res.getString("celular2"));
                            empleado.setCelular3(res.getString("celular3"));
                            empleado.setCelular4(res.getString("celular4"));
                            empleado.setCelular5(res.getString("celular5"));
                            empleado.setEmail2(res.getString("email2"));
                            empleado.setEmail3(res.getString("email3"));
                            empleado.setEmail4(res.getString("email4"));
                            empleado.setEmail5(res.getString("email5"));
                            empleado.setNivelacademico(res.getInt("nivelacademico"));
                            empleado.setPromedio(res.getDouble("promedio"));
                            empleado.setSemestre(res.getString("semestre"));
                            empleado.setCodigocarrera(res.getInt("codigocarrera"));
                            empleado.setExperienciahuellas(res.getInt("experienciahuellas"));
                            empleado.setIntepretacionsenas(res.getInt("intepretacionsenas"));
                            empleado.setManejodiscapacidad(res.getInt("manejodiscapacidad"));
                            empleado.setBilingue(res.getInt("bilingue"));
                            empleado.setEstadoicfes(res.getInt("estadoicfes"));
                            empleado.setUltimaevaluacion(res.getDouble("ultimaevaluacion"));
                            empleado.setCargo(res.getInt("cargo"));

                            empleado.setImgdociden(res.getInt("imgdociden"));
                            empleado.setImgcertestudio(res.getInt("imgcertestudio"));
                            empleado.setImgafiliaeps(res.getInt("imgafiliaeps"));
                            empleado.setImgrut(res.getInt("imgrut"));
                            empleado.setIdpuesto(res.getString("idpuesto"));
                            empleado.setGenero(res.getString("genero"));

                            empleado.setNombreCargo(res.getString("descCargo"));
                            empleado.setNombreDocumento(res.getString("desIden"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoPruebaEstadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, codCargo, idEmpleado);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return empleado;
    }

    /**
     * Se traen los datos de la tabla EmpleadoPruebaEstado solo se traen los que
     * se necesitan, si se requieren mas campos se deben añadir a la consulta
     *
     * @param idEmpleado
     * @param idPrueba
     * @param idCargo
     * @return
     */
    public EmpleadoPruebaEstado getByID(int idEmpleado, int idPrueba, String idCargo) {

        final EmpleadoPruebaEstado empleadoPrueba = new EmpleadoPruebaEstado();

        String sql = "SELECT pr.nombre as nombrePrueba,pr.tprueba as codPrueba, emp.* "
                + "FROM empleado_x_prueba_x_estado emp  "
                + " inner join prueba pr on pr.idprueba=emp.idprueba "
                + "WHERE idempleado=? and emp.idprueba=? and codigoCargo=? ";

        conex.consultar(new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet result) {
                try {
                    if (result.next()) {
                        empleadoPrueba.setIdEmpleado(result.getInt("idempleado"));
                        empleadoPrueba.setIdPrueba(result.getInt("idprueba"));
                        empleadoPrueba.setIdestpersona(result.getInt("idestpersona"));
                        empleadoPrueba.setCodigoCargo(result.getString("codigoCargo"));
                        empleadoPrueba.setIdMedioPago(result.getInt("idmedio_pago"));
                        empleadoPrueba.setIdBanco(result.getInt("idBanco"));
                        empleadoPrueba.setNumeroCuenta(result.getString("numeroCuenta"));
                        empleadoPrueba.setTipoCuenta(result.getInt("idTipoCuenta"));
                        empleadoPrueba.setNombrePrueba(result.getString("nombrePrueba"));
                        empleadoPrueba.setTipoPrueba(result.getInt("codPrueba"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(EmpleadoPruebaEstadoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, sql, idEmpleado, idPrueba, idCargo);

        return empleadoPrueba;
    }

    /**
     * Actualiza el medio de pago para un Empleado
     *
     * @param empleado
     */
    public boolean actualizarMedioPago(EmpleadoPruebaJoin empleado) {

        String sql = " UPDATE empleado_x_prueba_x_estado SET "
                + "idmedio_pago=?,"
                + "idBanco=?,"
                + "numeroCuenta=?,"
                + "idTipoCuenta=? "
                + "WHERE idempleado=? and idprueba=? and codigoCargo=?";

        return conex.ejecutar(sql, empleado.getPrueba().getIdMedioPago(),
                empleado.getPrueba().getIdBanco(),
                empleado.getPrueba().getNumeroCuenta(),
                empleado.getPrueba().getTipoCuenta(),
                empleado.getIdEmpleado(),
                empleado.getPrueba().getIdPrueba(),
                empleado.getPrueba().getCodigoCargo());

    }

    public List<EmpleadoPruebaEstado> listarPruebas(int usuario) {
        final List<EmpleadoPruebaEstado> eventos = new ArrayList<EmpleadoPruebaEstado>();
        StringBuilder sb = new StringBuilder();
        try {
            String sql = "SELECT epe.id,pru.nombre,car.descripcion AS cargo, car.codigoCargo, pru.fechaaplicacion,pru.idprueba,tip.tprueba,epp.codigo AS estado, \n"
                    + "case (select count(*) from cierre_asistencia_sesion c where c.idprueba=epe.idprueba and c.idDivipol=epe.iddivipol) when 0 then 0 else 1 end  as bloqueado \n"
                    + "FROM empleado_x_prueba_x_estado epe \n"
                    + "JOIN prueba pru ON (pru.idprueba = epe.idprueba) \n"
                    + "JOIN tipoprueba tip ON (pru.tprueba = tip.Idtprueba) \n"
                    + "LEFT OUTER JOIN cargos car ON (car.codigoCargo = epe.codigoCargo) \n"
                    + "JOIN estado_persona_prueba epp ON (epe.idestpersona = epp.idestpersona)\n"
                    + "WHERE epe.idempleado = ? order by pru.fechaaplicacion desc";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            EmpleadoPruebaEstado empleadoPruebaEstado = new EmpleadoPruebaEstado();
                            empleadoPruebaEstado.setId(res.getInt("id"));
                            empleadoPruebaEstado.setNombre(res.getString("nombre"));
                            empleadoPruebaEstado.setCargo(res.getString("cargo"));
                            empleadoPruebaEstado.setFechaAplicacion(res.getDate("fechaaplicacion"));
                            empleadoPruebaEstado.setIdprueba(res.getInt("idprueba"));
                            empleadoPruebaEstado.setNombreTipoPrueba(res.getString("tprueba"));
                            empleadoPruebaEstado.setEstado(res.getString("estado"));
                            empleadoPruebaEstado.setCodigoCargo(res.getString("codigoCargo"));
                            empleadoPruebaEstado.setBloqueado(res.getInt("bloqueado"));
                            eventos.add(empleadoPruebaEstado);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoPruebaEstadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }, sql, usuario);

        } catch (Exception e) {
            Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return eventos;
    }

    /**
     *
     * @param usuario
     * @param prueba
     * @param cargo
     * @return
     */
    public EmpleadoPruebaEstado getPrueba(int usuario, int prueba, String cargo) {
        final EmpleadoPruebaEstado empleadoPruebaEstado = new EmpleadoPruebaEstado();
        StringBuilder sb = new StringBuilder();

        try {
            String sql = "SELECT pru.nombre,car.descripcion AS cargo,pru.fechaaplicacion,pru.idprueba,tip.tprueba,epp.codigo AS estado\n"
                    + "FROM empleado_x_prueba_x_estado epe \n"
                    + "JOIN prueba pru ON (pru.idprueba = epe.idprueba) \n"
                    + "JOIN tipoprueba tip ON (pru.tprueba = tip.Idtprueba) \n"
                    + "LEFT OUTER JOIN cargos car ON (car.codigoCargo = epe.codigoCargo) \n"
                    + "JOIN estado_persona_prueba epp ON (epe.idestpersona = epp.idestpersona)\n"
                    + "WHERE epe.idempleado = ? and epe.idprueba and epe.codigoCargo = ?";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {

                            empleadoPruebaEstado.setNombre(res.getString("nombre"));
                            empleadoPruebaEstado.setCargo(res.getString("cargo"));
                            empleadoPruebaEstado.setFechaAplicacion(res.getDate("fechaaplicacion"));
                            empleadoPruebaEstado.setIdPrueba(res.getInt("idprueba"));
                            empleadoPruebaEstado.setNombreTipoPrueba(res.getString("tprueba"));
                            empleadoPruebaEstado.setEstado(res.getString("estado"));

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoPruebaEstadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, usuario, prueba, cargo);

        } catch (Exception e) {
            Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return empleadoPruebaEstado;
    }

    /**
     *
     * @param id
     * @return
     */
    public EmpleadoPruebaEstado getPruebaById(int id) {
        final EmpleadoPruebaEstado empleadoPruebaEstado = new EmpleadoPruebaEstado();
        StringBuilder sb = new StringBuilder();

        try {
            String sql = "SELECT epe.idempleado,epe.id,pru.nombre,car.descripcion AS cargo,pru.fechaaplicacion,pru.idprueba,tip.tprueba,epp.codigo AS estado,epe.idestpersona \n"
                    + "FROM empleado_x_prueba_x_estado epe \n"
                    + "JOIN prueba pru ON (pru.idprueba = epe.idprueba) \n"
                    + "JOIN tipoprueba tip ON (pru.tprueba = tip.Idtprueba) \n"
                    + "LEFT OUTER JOIN cargos car ON (car.codigoCargo = epe.codigoCargo) \n"
                    + "JOIN estado_persona_prueba epp ON (epe.idestpersona = epp.idestpersona)\n"
                    + "WHERE epe.id = ? ";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            empleadoPruebaEstado.setIdEmpleado(res.getInt("idempleado"));
                            empleadoPruebaEstado.setId(res.getInt("id"));
                            empleadoPruebaEstado.setNombre(res.getString("nombre"));
                            empleadoPruebaEstado.setCargo(res.getString("cargo"));
                            empleadoPruebaEstado.setFechaAplicacion(res.getDate("fechaaplicacion"));
                            empleadoPruebaEstado.setIdPrueba(res.getInt("idprueba"));
                            empleadoPruebaEstado.setNombreTipoPrueba(res.getString("tprueba"));
                            empleadoPruebaEstado.setEstado(res.getString("estado"));
                            empleadoPruebaEstado.setIdestpersona(res.getInt("idestpersona"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoPruebaEstadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, id);

        } catch (Exception e) {
            Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return empleadoPruebaEstado;
    }

    public int insertar(EmpleadoPruebaEstado empleadoPruebaEstado) {
        int idResult = 0;
        final Object result[] = new Object[1];

        String sql = " insert into empleado_x_prueba_x_estado \n"
                + " (idempleado, idprueba, idestpersona, iddivipol, fecha, hora, usuario, codigocargo) \n"
                + " values (?,?,?,null,current_timestamp,current_timestamp,?,?)";
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
                    Logger.getLogger(EmpleadoPruebaEstadoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, sql, empleadoPruebaEstado.getIdEmpleado(),
                empleadoPruebaEstado.getIdprueba(),
                empleadoPruebaEstado.getIdestpersona(),
                empleadoPruebaEstado.getUsuario(),
                empleadoPruebaEstado.getCodigoCargo());

        idResult = (int) result[0];

        return idResult;
    }

    public Boolean getExistEmpleadoEnPrueba(int idPersona, int idPrueba) {
        final Object result[] = new Object[1];
        result[0] = false;
        String sql = "select idempleado from empleado_x_prueba_x_estado where idempleado = ? and idprueba = ? ";
        conex.consultar(new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet res) {
                try {
                    while (res.next()) {
                        result[0] = true;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(EmpleadoPruebaEstadoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, sql, idPersona, idPrueba);

        return (boolean) result[0];
    }

    /**
     * Actualiza el estado de un usuario-cargo en una prueba
     *
     * @param epe
     * @return
     */
    public boolean actualizarEstado(EmpleadoPruebaEstado epe) {
        Boolean resultado = false;

        PreparedStatement sentencia = null;

        String sql = "UPDATE empleado_x_prueba_x_estado SET idestpersona=?, usuario = ? WHERE id=? ";

        resultado = conex.ejecutar(sql, epe.getIdestpersona(), epe.getUsuario(), epe.getId());

        return resultado;
    }

    public String listarPruebasEmpleado(int id) {

        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            String sql = "SELECT epe.id,pru.nombre,car.descripcion, car.codigoCargo, pru.fechaaplicacion,pru.idprueba,tip.tprueba,epp.codigo,\n"
                    + "case (select count(*) from tarea_confirmacion tc where tc.id_empleado=epe.idempleado and tc.cod_cargo=car.codigoCargo \n"
                    + " and tc.tipo='contrato' and tc.id_prueba=epe.idprueba) when 0 then 'No' else 'Si' end as firmo_contrato,\n"
                    + "case (select count(*) from tarea_confirmacion tc where tc.id_empleado=epe.idempleado and tc.cod_cargo=car.codigoCargo\n"
                    + "and tc.tipo='cuenta de cobro' and tc.id_prueba=epe.idprueba) when 0 then 'No' else 'Si' end as firmo_cobro\n"
                    + "FROM empleado_x_prueba_x_estado epe \n"
                    + "JOIN prueba pru ON (pru.idprueba = epe.idprueba) \n"
                    + "JOIN tipoprueba tip ON (pru.tprueba = tip.Idtprueba) \n"
                    + "LEFT OUTER JOIN cargos car ON (car.codigoCargo = epe.codigoCargo) \n"
                    + "JOIN estado_persona_prueba epp ON (epe.idestpersona = epp.idestpersona)\n"
                    + "WHERE epe.idempleado =" + id + " order by pru.fechaaplicacion DESC";

            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {

                        result[0] = ResultSetConverter.convert(res);

                    } catch (SQLException ex) {
                        Logger.getLogger(TareaConfirmacionDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(TareaConfirmacionDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(TareaConfirmacionDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();

    }

    public String buscarJsonFuncionariosConRoles(int nrodoc, String apellido1, String nombre1, String rol, int idPrueba) {
        final Object[] result = new Object[1];
        JSONArray json = null;
        boolean filtro = false;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select * from (");
            sql.append(" SELECT e.idEmpleado,e.tipodoc, e.nrodoc,e.apellido1,e.apellido2,e.nombre1,e.nombre2,e.email  ");
            sql.append(" ,(SELECT GROUP_CONCAT(g.descripcion  separator ', ') from usuario_grupo u  ");
            sql.append(" inner join grupos g on g.codigoGrupo=u.GRUPO ");
            sql.append(" where u.USUARIO=e.nrodoc and u.idprueba=? and u.activo=1 ");
            sql.append(" ) as roles ");
            if (rol != null) {
                sql.append(" ,(SELECT count(*) from usuario_grupo u   ");
                sql.append(" where u.USUARIO=e.nrodoc and u.idprueba=");
                sql.append(idPrueba);
                sql.append(" and u.activo=1  and u.GRUPO='");
                sql.append(rol);
                sql.append("') as contieneRol  ");
            }

            sql.append(" FROM empleado e   ");

            if (nrodoc > 0) {

                sql.append(" where e.nrodoc = ");
                sql.append(nrodoc);
                filtro = true;
            }

            if (apellido1 != null) {
                if (filtro) {
                    sql.append(" and ");
                } else {
                    sql.append(" where ");
                    filtro = true;
                }

                sql.append(" (e.apellido1 like '%");
                sql.append(apellido1);
                sql.append("%' ");

                sql.append(" or e.apellido2 like '%");
                sql.append(apellido1);
                sql.append("%' )");
            }

            if (nombre1 != null) {
                if (filtro) {
                    sql.append(" and ");
                } else {
                    sql.append(" where ");
                    filtro = true;
                }

                sql.append(" ( e.nombre1 like '%");
                sql.append(nombre1);
                sql.append("%' ");

                sql.append(" or e.nombre2 like '%");
                sql.append(nombre1);
                sql.append("%' )");
            }
            sql.append(" group by e.idEmpleado order by e.apellido1,e.apellido2 ");
            sql.append("  ) as res ");

            if (rol != null) {
                sql.append(" where  contieneRol >0");
            }

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString(), idPrueba);

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    public String buscarJsonFuncionario(int idEmpleado, int idPrueba) {
        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT e.idEmpleado,e.tipodoc, e.nrodoc,e.apellido1,e.apellido2,e.nombre1,e.nombre2,e.email,es.descripcion,e.estadoicfes ");
            sql.append(" ,(SELECT GROUP_CONCAT(u.GRUPO) from usuario_grupo u where  ");
            sql.append(" u.USUARIO=e.nrodoc and u.idprueba=? and u.activo=1  ");
            sql.append(" ) as roles  ");
            sql.append(" ,(SELECT  GROUP_CONCAT(ud.codigoDepartamento)  from usuario_departamento ud where ud.usuario=e.nrodoc and ud.idPrueba= ? ");
            sql.append(" ) as numDep  ");
            sql.append(" FROM empleado e   ");
            sql.append(" inner join estadoempleado es on e.estadoicfes=es.codigoestado ");
            sql.append(" where e.idEmpleado=?  ");
            sql.append(" group by e.idEmpleado  ");

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString(), idPrueba, idPrueba, idEmpleado);

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    public String buscarJsonFuncionarioCedula(long nroDoc, int idPrueba) {
        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT e.idEmpleado,e.tipodoc, e.nrodoc,e.apellido1,e.apellido2,e.nombre1,e.nombre2,e.email,es.descripcion,e.estadoicfes ");
            sql.append(" ,(SELECT GROUP_CONCAT(u.GRUPO) from usuario_grupo u where  ");
            sql.append(" u.USUARIO=e.nrodoc and u.idprueba=? and u.activo=1  ");
            sql.append(" ) as roles  ");
            sql.append(" ,(SELECT  GROUP_CONCAT(ud.codigoDepartamento)  from usuario_departamento ud where ud.usuario=e.nrodoc and ud.idPrueba= ? ");
            sql.append(" ) as numDep  ");
            sql.append(" FROM empleado e  ");
            sql.append(" inner join estadoempleado es on e.estadoicfes=es.codigoestado ");
            sql.append(" where e.nrodoc=?  ");
            sql.append(" group by e.idEmpleado ");

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString(), idPrueba, idPrueba, nroDoc);

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    /**
     * Metodo Dao encargado de consultar el empleado en la tabla
     * empleado_x_prueba_x_estado, con el idEmpleado y estado para posterior
     * validacion
     *
     * @param idPersona
     * @param idPrueba
     * @param codigoCargo
     * @return
     */
    public EmpleadoPruebaEstado getEmpleadoEnPrueba(int idPersona, int idPrueba, String codigoCargo) {
        final EmpleadoPruebaEstado empleado = new EmpleadoPruebaEstado();
        String sql = "select id, idEmpleado,idestpersona from empleado_x_prueba_x_estado where idempleado = ? and idprueba = ? and codigocargo = ?";
        conex.consultar(new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet res) {
                try {
                    while (res.next()) {
                        empleado.setId(res.getInt("id"));
                        empleado.setIdEmpleado(res.getInt("idEmpleado"));
                        empleado.setIdestpersona(res.getInt("idestpersona"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(EmpleadoPruebaEstadoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, sql, idPersona, idPrueba, codigoCargo);

        return empleado;
    }

    /**
     *
     * @param nrodoc
     * @param idPrueba
     * @param descripcionCargo (opcional)
     * @return
     */
    public EmpleadoPruebaEstado getByDocumento(long nrodoc, int idPrueba, String descripcionCargo) {
        final EmpleadoPruebaEstado empleado = new EmpleadoPruebaEstado();
        StringBuilder sql = new StringBuilder("select ");
        sql.append("eps.id as idEps, eps.idEmpleado as idEmpleado, eps.idestpersona as idEstadoPersona, ");
        sql.append("cg.codigoCargo as codigoCargo, cg.descripcion as nombreCargo, cg.nivel_cargo as nivelCargo ");
        sql.append("from empleado_x_prueba_x_estado eps ");
        sql.append("inner join empleado em on eps.idEmpleado = em.idEmpleado ");
        sql.append("inner join cargos cg on eps.codigoCargo = cg.codigoCargo ");
        sql.append("where em.nrodoc = ").append(nrodoc);
        sql.append(" and eps.idprueba = ").append(idPrueba);
        SqlUtil.appendParameterWithLike(sql, "cg.descripcion", descripcionCargo, SqlUtil.LIKE);
        Logger.getLogger(EmpleadoPruebaEstadoDao.class.getName()).log(Level.INFO, sql.toString());
        conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet res) {
                try {
                    while (res.next()) {
                        empleado.setId(res.getInt("idEps"));
                        empleado.setIdEmpleado(res.getInt("idEmpleado"));
                        empleado.setIdestpersona(res.getInt("idEstadoPersona"));
                        Cargo cargo = new Cargo();
                        cargo.setCodigoCargo(res.getString("codigoCargo"));
                        cargo.setDescripcion(res.getString("nombreCargo"));
                        cargo.setNivel_cargo(res.getInt("nivelCargo"));
                        empleado.setCargoObj(cargo);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(EmpleadoPruebaEstadoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        return empleado;
    }

    /**
     * Metodo Dao para buscar las personas que esten pre inscritas y buscar su
     * detalle de ubicacion y salones
     *
     * @param dtoFiltros
     * @return
     */
    public List<EmpleadoPruebaEstadoCargo> buscarPersonasParaAsignar(BusquedaPersonasDto dtoFiltros) {
        final List<EmpleadoPruebaEstadoCargo> lista = new ArrayList<>();
        try {
            String consultaDistancia = buildDistanceQuery(dtoFiltros);

            StringBuilder consulta = new StringBuilder("select ");
            consulta.append("epe.id as idEmpleadoPruebaEstado, ");
            consulta.append("em.idEmpleado as idEmpleado, ");
            consulta.append("em.nrodoc as nroDocumento, ");
            consulta.append("em.apellido1 as apellido1, ");
            consulta.append("em.apellido2 as apellido2, ");
            consulta.append("em.nombre1 as nombre1, ");
            consulta.append("em.nombre2 as nombre2, ");
            consulta.append("epe.disponibilidad_viaje as viajar, ");
            consulta.append("em.longitud as longitud, ");
            consulta.append("em.latitud as latitud, ");
            consulta.append("em.direccion as direccion, ");
            consulta.append("(").append(consultaDistancia).append(") as distancia, ");
            consulta.append("em.salones as salones ");
            consulta.append("from empleado_x_prueba_x_estado epe ");
            consulta.append("inner join empleado em on em.idEmpleado = epe.idempleado ");
            consulta.append("inner join cargos cg on epe.codigoCargo = cg.codigoCargo ");
            consulta.append(" where epe.idprueba = ").append(dtoFiltros.getIdPrueba());
            consulta.append(" and cg.nivel_cargo = ").append(dtoFiltros.getCodCargo());
            consulta.append(" and epe.idestpersona = 7 ");
            if (dtoFiltros.getViajar() == 0) {
                consulta.append(" and (epe.disponibilidad_viaje = 0 or epe.disponibilidad_viaje is null) ");
            } else if (dtoFiltros.getViajar() == 1) {
                consulta.append(" and epe.disponibilidad_viaje = ").append(dtoFiltros.getViajar()).append(" ");
            }
            SqlUtil.appendParameterWithAnd(consulta, "em.DaneDepartamento", dtoFiltros.getCodDeptoDane());
            SqlUtil.appendParameterWithAnd(consulta, "em.DaneMunicipio", dtoFiltros.getCodigoMunDane());
            if (dtoFiltros.getCantidadSalones() > 0 || dtoFiltros.getDistancia() > 0) {
                StringBuilder builderHaving = new StringBuilder();
                SqlUtil.appendParameterHaving(builderHaving, "salones", dtoFiltros.getCantidadSalones(), SqlUtil.GREATER_THAN);
                SqlUtil.appendParameterHaving(builderHaving, "distancia", dtoFiltros.getDistancia(), SqlUtil.LESS_THAN);
                consulta.append(builderHaving.toString());
            }
            Logger.getLogger(EmpleadoPruebaEstadoDao.class.getName()).log(Level.INFO, consulta.toString());
            conex.consultar(consulta.toString(), new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet resultado) {
                    try {
                        while (resultado.next()) {
                            EmpleadoPruebaEstadoCargo empleadoPrueba = new EmpleadoPruebaEstadoCargo();
                            empleadoPrueba.setId(resultado.getInt("idEmpleadoPruebaEstado"));
                            Empleado empleado = new Empleado();
                            empleado.setIdEmpleado(resultado.getInt("idEmpleado"));
                            empleado.setNrodoc(resultado.getLong("nroDocumento"));
                            empleado.setApellido1(resultado.getString("apellido1"));
                            empleado.setApellido2(resultado.getString("apellido2"));
                            empleado.setNombre1(resultado.getString("nombre1"));
                            empleado.setNombre2(resultado.getString("nombre2"));
                            empleado.setLongitud(resultado.getFloat("longitud"));
                            empleado.setLatitud(resultado.getFloat("latitud"));
                            empleado.setDireccion(resultado.getString("direccion"));
                            empleadoPrueba.setEmpleado(empleado);
                            empleadoPrueba.setId(resultado.getInt("idEmpleadoPruebaEstado"));
                            empleadoPrueba.setDistancia(resultado.getDouble("distancia"));
                            empleadoPrueba.setSalones(resultado.getInt("salones"));
                            lista.add(empleadoPrueba);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoPruebaEstadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (Exception e) {
            Logger.getLogger(EmpleadoPruebaEstadoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;

    }

    /**
     * Metodo encargado de generar la consulta de la distancia
     *
     * @param dtoFiltros
     * @return
     */
    private String buildDistanceQuery(BusquedaPersonasDto dtoFiltros) {
        StringBuilder consultaDistancia = new StringBuilder("SELECT ");
        consultaDistancia.append("TRUNCATE(6371 * ACOS(COS(RADIANS(");
        consultaDistancia.append(dtoFiltros.getSitio().getLatitud()).append("))");
        consultaDistancia.append("* COS(RADIANS(em.latitud))");
        consultaDistancia.append("* COS(RADIANS(");
        consultaDistancia.append(dtoFiltros.getSitio().getLongitud()).append(")");
        consultaDistancia.append("- RADIANS(em.longitud))");
        consultaDistancia.append("+ SIN(RADIANS(");
        consultaDistancia.append(dtoFiltros.getSitio().getLatitud()).append("))");
        consultaDistancia.append("* SIN(RADIANS(em.latitud))),2)");
        return consultaDistancia.toString();
    }

    /**
     *
     * @param idEmpleado
     * @param idPrueba
     * @param nivelCargo
     * @return
     */
    public EmpleadoPruebaEstado getEmpleadoEnPruebaNivelCargo(int idEmpleado, int idPrueba, int nivelCargo) {
        final EmpleadoPruebaEstado empleado = new EmpleadoPruebaEstado();
        StringBuilder sql = new StringBuilder();
        sql.append("select eps.id, eps.idEmpleado,eps.idestpersona ");
        sql.append("from empleado_x_prueba_x_estado eps ");
        sql.append("inner join cargos cg on eps.codigoCargo = cg.codigoCargo ");
        sql.append("where idempleado = ").append(idEmpleado).append(" and idprueba = ").append(idPrueba);
        SqlUtil.appendParameterWithAnd(sql, "cg.nivel_cargo", nivelCargo);
        Logger.getLogger(EmpleadoPruebaEstadoDao.class.getName()).log(Level.INFO, sql.toString());
        
        conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet res) {
                try {
                    while (res.next()) {
                        empleado.setId(res.getInt("id"));
                        empleado.setIdEmpleado(res.getInt("idEmpleado"));
                        empleado.setIdestpersona(res.getInt("idestpersona"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(EmpleadoPruebaEstadoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        return empleado;
    }

}
