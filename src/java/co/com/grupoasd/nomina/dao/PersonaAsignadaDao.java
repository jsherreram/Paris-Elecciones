package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.conexion.Operaciones.OperacionAtomica;
import co.com.grupoasd.nomina.modelo.Capacitacion;
import co.com.grupoasd.nomina.modelo.Cargo;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.Estado;
import co.com.grupoasd.nomina.modelo.Evento;
import co.com.grupoasd.nomina.modelo.Municipio;
import co.com.grupoasd.nomina.modelo.PersonaAsignada;
import co.com.grupoasd.nomina.modelo.Prueba;
import co.com.grupoasd.nomina.modelo.Sitio;
import co.com.grupoasd.nomina.modelo.seguimiento.ResultSetConverter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Pedro Rodriguez
 */
public class PersonaAsignadaDao {

    private Operaciones conex = new Operaciones();
    private AsistenciaDao asistenciaDao = new AsistenciaDao();

    public PersonaAsignadaDao() {

    }

    public List<PersonaAsignada> listar(final int idEvento, final String idDepartamento, final String idMunicipio, final String idCargo, final String idZona, final String idPuesto) {

        final List<PersonaAsignada> personasAsignadas = new ArrayList<>();
        try {
            String sql;

            sql = " select * "
                    + " from detalle_cargo_x_puesto_x_evento "
                    + " where codigoDepartamento = ? "
                    + " and codigoMunicipio = ? "
                    + " and codigoZona = ? "
                    + " and codigoPuesto = ? "
                    + " and codigoCargo = ? "
                    + " and codigoEvento = ? ";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            PersonaAsignada personaAsignada = new PersonaAsignada();
                            Municipio municipio = new MunicipioDao().GetById(idDepartamento, idMunicipio);

                            personaAsignada.setMunicipio(municipio);
                            personaAsignada.setIddivipol(res.getInt("iddivipol"));
                            personaAsignada.setZona(idZona);
                            personaAsignada.setPuesto(idPuesto);
                            personaAsignada.setUbicacion(res.getString("ubicacion"));
                            personaAsignada.setCargo(new CargoDao().GetById(idCargo));
                            personaAsignada.setEvento(new EventoDao().GetById(idEvento));

                            int idEmp = new EmpleadoDao().GetIdByNumeroDocumento(res.getLong("nrodoc"));
                            personaAsignada.setEmpleado(new EmpleadoDao().GetById(idEmp));

                            personaAsignada.setEstado(new EstadoDao().GetById(res.getString("estado")));
                            personaAsignada.setUsuario(res.getString("usuario"));
                            personaAsignada.setFecha(res.getDate("fecha"));

                            personaAsignada.setAsistio(asistenciaDao.tieneAsistencia(res.getInt("codigoevento"), res.getInt("iddivipol"), res.getInt("nrodoc")));

                            personaAsignada.setId(res.getInt("id"));

                            personaAsignada.setCantidadasistio(res.getInt("cantidadasistio"));
                            personaAsignada.setCantidadnoasistio(res.getInt("cantidadnoasistio"));

                            personaAsignada.setConsecutivo(res.getInt("consecutivo"));

                            personasAsignadas.add(personaAsignada);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, idDepartamento,
                    idMunicipio,
                    idZona,
                    idPuesto,
                    idCargo,
                    idEvento);

        } catch (Exception e) {
            Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return personasAsignadas;
    }
    
      public List<PersonaAsignada> listarPorCargoEventoMunicipio(final int idEvento,  final String idMunicipio, final String idCargo, final String codigoDepartamento) {

        final List<PersonaAsignada> personasAsignadas = new ArrayList<>();
        try {
            StringBuilder sql=new  StringBuilder();

            sql.append(" select dcpe.*, d.nombrePuesto  from detalle_cargo_x_puesto_x_evento dcpe ");
            sql.append(" inner join divipol d on d.iddivipol=dcpe.iddivipol ");
            sql.append(" where  codigoCargo = ? and codigoEvento = ? and dcpe.codigoDepartamento=? ");
            if(idMunicipio!=null && !idMunicipio.equals("0")){
              sql.append(" and dcpe.codigoMunicipio='").append(idMunicipio).append("'");
            }
            sql.append(" order by dcpe.codigopuesto, dcpe.ubicacion");
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            PersonaAsignada personaAsignada = new PersonaAsignada();
                            Municipio municipio = new MunicipioDao().GetById(res.getString("codigodepartamento"), res.getString("codigomunicipio"));
                            personaAsignada.setMunicipio(municipio);
                            personaAsignada.setIddivipol(res.getInt("iddivipol"));
                            personaAsignada.setPuesto(res.getString("codigopuesto"));
                            personaAsignada.setUbicacion(res.getString("ubicacion"));
                            personaAsignada.setEvento(new EventoDao().GetById(idEvento));
                            int idEmp = new EmpleadoDao().GetIdByNumeroDocumento(res.getLong("nrodoc"));
                            personaAsignada.setEmpleado(new EmpleadoDao().GetById(idEmp));
                            personaAsignada.setEstado(new EstadoDao().GetById(res.getString("estado")));
                            personaAsignada.setUsuario(res.getString("usuario"));
                            personaAsignada.setFecha(res.getDate("fecha"));
                            personaAsignada.setId(res.getInt("id"));
                            personaAsignada.setNombrePuesto(res.getString("nombrePuesto"));
                            personasAsignadas.add(personaAsignada);
                            
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString(), idCargo,
                    idEvento,
                    codigoDepartamento);

        } catch (Exception e) {
            Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return personasAsignadas;
    }

    public List<PersonaAsignada> listar(int nrodoc) {

        final List<PersonaAsignada> personasAsignadas = new ArrayList<>();
        try {
            String sql;

            sql = " select codigodepartamento, codigomunicipio, codigozona, codigopuesto, ubicacion, codigocargo, codigoevento, nrodoc, asistio, estado, usuario, fecha, id, cantidadasistio, cantidadnoasistio, consecutivo "
                    + " from detalle_cargo_x_puesto_x_evento "
                    + " where nrodoc = ? ";
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            PersonaAsignada personaAsignada = new PersonaAsignada();
                            Municipio municipio = new MunicipioDao().GetById(res.getString("codigodepartamento"), res.getString("codigomunicipio"));

                            personaAsignada.setMunicipio(municipio);
                            personaAsignada.setIddivipol(res.getInt("iddivipol"));
                            personaAsignada.setZona(res.getString("codigozona"));
                            personaAsignada.setPuesto(res.getString("codigopuesto"));
                            personaAsignada.setUbicacion(res.getString("ubicacion"));
                            personaAsignada.setCargo(new CargoDao().GetById(res.getString("codigocargo")));
                            personaAsignada.setEvento(new EventoDao().GetById(res.getInt("codigoevento")));

                            int idEmp = new EmpleadoDao().GetIdByNumeroDocumento(res.getLong("nrodoc"));
                            personaAsignada.setEmpleado(new EmpleadoDao().GetById(idEmp));

                            personaAsignada.setEstado(new EstadoDao().GetById(res.getString("estado")));
                            personaAsignada.setUsuario(res.getString("usuario"));
                            personaAsignada.setFecha(res.getDate("fecha"));

                            personaAsignada.setAsistio(asistenciaDao.tieneAsistencia(res.getInt("codigoevento"), res.getInt("iddivipol"), res.getInt("nrodoc")));

                            personaAsignada.setId(res.getInt("id"));

                            personaAsignada.setCantidadasistio(res.getInt("cantidadasistio"));
                            personaAsignada.setCantidadnoasistio(res.getInt("cantidadnoasistio"));

                            personaAsignada.setConsecutivo(res.getInt("consecutivo"));

                            personaAsignada.setAsistenciabiometrica(asistenciaDao.tieneAsistenciaBiometrica(res.getInt("codigoevento"), res.getInt("iddivipol"), res.getInt("nrodoc")));

                            personasAsignadas.add(personaAsignada);

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, nrodoc);

        } catch (Exception e) {
            Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return personasAsignadas;
    }

    public List<PersonaAsignada> listar(int idEvento, String usuario) {

        final List<PersonaAsignada> personasAsignadas = new ArrayList<>();
        try {
            String sql;

            sql = " select * "
                    + " from detalle_cargo_x_puesto_x_evento "
                    + " where codigoevento = " + idEvento + " and iddivipol in( "
                    + " 	select iddivipol "
                    + " 	from usuario_sitio "
                    + " 	where usuario =  '" + usuario + "'"
                    + " ) ";

            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            PersonaAsignada personaAsignada = new PersonaAsignada();

                            Municipio municipio = new MunicipioDao().GetById(res.getString("codigodepartamento"), res.getString("codigomunicipio"));

                            personaAsignada.setMunicipio(municipio);
                            personaAsignada.setIddivipol(res.getInt("iddivipol"));

                            personaAsignada.setZona(res.getString("codigozona"));
                            personaAsignada.setPuesto(res.getString("codigopuesto"));
                            personaAsignada.setUbicacion(res.getString("ubicacion"));
                            personaAsignada.setCargo(new CargoDao().GetById(res.getString("codigocargo")));
                            personaAsignada.setEvento(new EventoDao().GetById(res.getInt("codigoevento")));

                            int idEmp = new EmpleadoDao().GetIdByNumeroDocumento(res.getLong("nrodoc"));
                            personaAsignada.setEmpleado(new EmpleadoDao().GetById(idEmp));

                            personaAsignada.setEstado(new EstadoDao().GetById(res.getString("estado")));
                            personaAsignada.setUsuario(res.getString("usuario"));
                            personaAsignada.setFecha(res.getDate("fecha"));
                            personaAsignada.setAsistio(asistenciaDao.tieneAsistencia(res.getInt("codigoevento"), res.getInt("iddivipol"), res.getInt("nrodoc")));
                            personaAsignada.setId(res.getInt("id"));

                            personaAsignada.setCantidadasistio(res.getInt("cantidadasistio"));
                            personaAsignada.setCantidadnoasistio(res.getInt("cantidadnoasistio"));

                            personaAsignada.setConsecutivo(res.getInt("consecutivo"));

                            personaAsignada.setAsistenciabiometrica(asistenciaDao.tieneAsistenciaBiometrica(res.getInt("codigoevento"), res.getInt("iddivipol"), res.getInt("nrodoc")));

                            personasAsignadas.add(personaAsignada);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return personasAsignadas;
    }

    public List<PersonaAsignada> listarXSitioEvento(int idEvento, int idDivipol) {

        final List<PersonaAsignada> personasAsignadas = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder();

            sql.append(" select Id, codigodepartamento, codigomunicipio idDivipol,codigocargo, nrodoc, estado, codigoevento   ");
            sql.append(" from detalle_cargo_x_puesto_x_evento ");
            sql.append(" where codigoevento =").append(idEvento).append(" and idDivipol = ").append(idDivipol);

            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            PersonaAsignada personaAsignada = new PersonaAsignada();

                            Municipio municipio = new MunicipioDao().GetById(res.getString("codigodepartamento"), res.getString("codigomunicipio"));
                            personaAsignada.setMunicipio(municipio);
                            personaAsignada.setIddivipol(res.getInt("idDivipol"));
                            personaAsignada.setCargo(new CargoDao().GetById(res.getString("codigocargo")));

                            int idEmp = new EmpleadoDao().GetIdByNumeroDocumento(res.getLong("nrodoc"));
                            personaAsignada.setEmpleado(new EmpleadoDao().GetById(idEmp));
                            personaAsignada.setEstado(new EstadoDao().GetById(res.getString("estado")));
                            personaAsignada.setAsistio(asistenciaDao.tieneAsistencia(res.getInt("codigoevento"), res.getInt("idDivipol"), res.getLong("nrodoc")));
                            personaAsignada.setId(res.getInt("id"));
                            personaAsignada.setAsistenciabiometrica(asistenciaDao.tieneAsistenciaBiometrica(res.getInt("codigoevento"), res.getInt("idDivipol"), res.getLong("nrodoc")));
                            personasAsignadas.add(personaAsignada);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return personasAsignadas;
    }

    public List<PersonaAsignada> listarXSitio(int idEvento, String codigoSitio) {

        final List<PersonaAsignada> personasAsignadas = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select d.id, d.idPrueba, d.codigoDepartamento,d.estado, d.usuario, d.fecha, d.codigoMunicipio, d.idDivipol, d.consecutivo, d.codigoZona, d.codigoPuesto, d.ubicacion,\n");
            sql.append(" c.codigoCargo, c.descripcion as nombrecargo, ev.codigoEvento, ev.nombre as nombreevento, \n");
            sql.append(" e.idEmpleado, e.apellido1, e.apellido2, e.nombre1, e.nombre2, e.nrodoc, e.celular, ifnull(asis.biometrico, 0) as biometrico,\n");
            sql.append(" (case when asis.biometrico is null then 0 else 1 end) as asistio, asis.estado as validado ");
            sql.append(" from detalle_cargo_x_puesto_x_evento  d\n");
            sql.append("inner join municipio m on m.codigoMunicipio=d.codigoMunicipio\n");
            sql.append("left join empleado e on e.nrodoc=d.nrodoc\n");
            sql.append("left join cargos c on c.codigoCargo=d.codigoCargo\n");
            sql.append("left join evento ev on ev.codigoEvento=d.codigoEvento\n");
            sql.append("left join asistencia asis on d.codigoEvento=asis.codigoevento and e.idEmpleado=asis.idempleado and d.idDivipol=asis.iddivipol\n");
            sql.append("where d.codigoevento =").append(idEvento);
            sql.append(" and d.codigopuesto = '").append(codigoSitio).append("'");
            sql.append(" order by c.codigoCargo, d.ubicacion ");

            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            PersonaAsignada personaAsignada = new PersonaAsignada();

                            Municipio municipio = new Municipio();
                            municipio.setCodigoMunicipio("codigoMunicipio");
                            personaAsignada.setMunicipio(municipio);
                            personaAsignada.setIddivipol(res.getInt("idDivipol"));
                            personaAsignada.setZona(res.getString("codigoZona"));
                            personaAsignada.setPuesto(res.getString("codigoPuesto"));
                            personaAsignada.setUbicacion(res.getString("ubicacion"));
                            Cargo cargo = new Cargo();
                            cargo.setCodigoCargo(res.getString("codigoCargo"));
                            cargo.setDescripcion(res.getString("nombrecargo"));
                            personaAsignada.setCargo(cargo);
                            Evento evento = new Evento();
                            evento.setCodigoEvento(res.getInt("codigoEvento"));
                            evento.setNombre(res.getString("nombreevento"));
                            Prueba prueba = new Prueba();
                            prueba.setIdprueba(res.getInt("idPrueba"));
                            evento.setPrueba(prueba);
                            personaAsignada.setEvento(evento);
                            Empleado empleado = new Empleado();
                            empleado.setIdEmpleado(res.getInt("idEmpleado"));
                            empleado.setApellido1(res.getString("apellido1"));
                            empleado.setApellido2(res.getString("apellido2"));
                            empleado.setNombre1(res.getString("nombre1"));
                            empleado.setNombre2(res.getString("nombre2"));
                            empleado.setNrodoc(res.getLong("nrodoc"));
                            personaAsignada.setEmpleado(empleado);
                            personaAsignada.setAsistio(res.getBoolean("asistio"));
                            personaAsignada.setId(res.getInt("id"));
                            personaAsignada.setConsecutivo(res.getInt("consecutivo"));
                            personaAsignada.setAsistenciabiometrica(res.getInt("biometrico"));
                            personaAsignada.setUsuario(res.getString("usuario"));
                            personaAsignada.setFecha(res.getDate("fecha"));
                            Estado estado = new Estado();
                            estado.setCodigoEstado(res.getInt("estado"));
                            personaAsignada.setEstado(estado);
                            personaAsignada.setValidado(res.getBoolean("validado"));
                            personasAsignadas.add(personaAsignada);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return personasAsignadas;
    }

    public PersonaAsignada getPersonaAsignada(final int idEvento, final String idDepartamento, final String idMunicipio, final String idCargo, final String idZona, final String idPuesto, final String idUbicacion) {

        final PersonaAsignada personaAsignada = new PersonaAsignada();
        try {
            String sql;

            sql = " select * "
                    + " from detalle_cargo_x_puesto_x_evento "
                    + " where codigoDepartamento = ? "
                    + " and codigoMunicipio = ? "
                    + " and codigoZona = ? "
                    + " and codigoPuesto = ? "
                    + " and codigoCargo = ? "
                    + " and codigoEvento = ? "
                    + " and ubicacion = ? ";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Municipio municipio = new MunicipioDao().GetById(idDepartamento, idMunicipio);
                            personaAsignada.setIddivipol(res.getInt("iddivipol"));
                            personaAsignada.setMunicipio(municipio);
                            personaAsignada.setZona(idZona);
                            personaAsignada.setPuesto(idPuesto);
                            personaAsignada.setUbicacion(idUbicacion);
                            personaAsignada.setCargo(new CargoDao().GetById(idCargo));
                            personaAsignada.setEvento(new EventoDao().GetById(idEvento));

                            int idEmp = new EmpleadoDao().GetIdByNumeroDocumento(res.getLong("nrodoc"));
                            personaAsignada.setEmpleado(new EmpleadoDao().GetById(idEmp));

                            personaAsignada.setEstado(new EstadoDao().GetById(res.getString("estado")));
                            personaAsignada.setUsuario(res.getString("usuario"));
                            personaAsignada.setFecha(res.getDate("fecha"));
                            personaAsignada.setAsistio(asistenciaDao.tieneAsistencia(res.getInt("codigoevento"), res.getInt("iddivipol"), res.getInt("nrodoc")));
                            personaAsignada.setCantidadasistio(res.getInt("cantidadasistio"));
                            personaAsignada.setCantidadnoasistio(res.getInt("cantidadnoasistio"));
                            personaAsignada.setAsistenciabiometrica(asistenciaDao.tieneAsistenciaBiometrica(res.getInt("codigoevento"), res.getInt("iddivipol"), res.getInt("nrodoc")));

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, idDepartamento,
                    idMunicipio,
                    idZona,
                    idPuesto,
                    idCargo,
                    idEvento,
                    idUbicacion);

        } catch (Exception e) {
            Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return personaAsignada;
    }

    public PersonaAsignada getById(int id) {

        final PersonaAsignada personaAsignada = new PersonaAsignada();
        try {
            String sql;

            sql = " select * "
                    + " from detalle_cargo_x_puesto_x_evento "
                    + " where id = ? ";
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {

                    try {
                        while (res.next()) {
                            Municipio municipio = new MunicipioDao().GetById(res.getString("codigoDepartamento"), res.getString("codigoMunicipio"));
                            personaAsignada.setMunicipio(municipio);
                            personaAsignada.setIddivipol(res.getInt("iddivipol"));
                            personaAsignada.setZona(res.getString("codigoZona"));
                            personaAsignada.setPuesto(res.getString("codigoPuesto"));
                            personaAsignada.setUbicacion(res.getString("ubicacion"));
                            personaAsignada.setCargo(new CargoDao().GetById(res.getString("codigoCargo")));
                            personaAsignada.setEvento(new EventoDao().GetById(res.getInt("codigoEvento")));

                            int idEmp = new EmpleadoDao().GetIdByNumeroDocumento(res.getLong("nrodoc"));
                            personaAsignada.setEmpleado(new EmpleadoDao().GetByIdSinImagenHuella(idEmp));

                            personaAsignada.setEstado(new EstadoDao().GetById(res.getString("estado")));
                            personaAsignada.setUsuario(res.getString("usuario"));
                            personaAsignada.setFecha(res.getDate("fecha"));

                            personaAsignada.setAsistio(asistenciaDao.tieneAsistencia(res.getInt("codigoevento"), res.getInt("iddivipol"), res.getLong("nrodoc")));

                            Sitio sitio = new SitioDao().BuscarSitioPorId(res.getInt("iddivipol"));
                            personaAsignada.setIdTipoSitio(sitio.getTipoSitio().getIdTipoSitio());

                            personaAsignada.setId(res.getInt("id"));
                            personaAsignada.setSalon(res.getString("salon"));
                            personaAsignada.setCantidadasistio(res.getInt("cantidadasistio"));
                            personaAsignada.setCantidadnoasistio(res.getInt("cantidadnoasistio"));
                            personaAsignada.setAsistenciabiometrica(asistenciaDao.tieneAsistenciaBiometrica(res.getInt("codigoevento"), res.getInt("iddivipol"), res.getLong("nrodoc")));
                            personaAsignada.setOrden(res.getInt("orden"));

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, id);

        } catch (Exception e) {
            Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return personaAsignada;
    }

    public PersonaAsignada getPersonaAsignada(long idEvento, long nroDoc) {

        final PersonaAsignada personaAsignada = new PersonaAsignada();
        try {
            StringBuilder sql = new StringBuilder();

            sql.append(" select d.*,dv1.espolivalente, dv1.idTipoSitio ");
            sql.append(" from detalle_cargo_x_puesto_x_evento d,divipol dv1 ");
            sql.append(" where codigoEvento = ? and nrodoc = ? and d.idDivipol = dv1.idDivipol");

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Municipio municipio = new MunicipioDao().GetById(res.getString("codigoDepartamento"), res.getString("codigoMunicipio"));
                            personaAsignada.setMunicipio(municipio);
                            personaAsignada.setIddivipol(res.getInt("iddivipol"));
                            personaAsignada.setZona(res.getString("codigoZona"));
                            personaAsignada.setPuesto(res.getString("codigoPuesto"));
                            personaAsignada.setUbicacion(res.getString("ubicacion"));
                            personaAsignada.setCargo(new CargoDao().GetById(res.getString("codigoCargo")));
                            personaAsignada.setEvento(new EventoDao().GetById(res.getInt("codigoEvento")));

                            int idEmp = new EmpleadoDao().GetIdByNumeroDocumento(res.getLong("nrodoc"));
                            personaAsignada.setEmpleado(new EmpleadoDao().GetById(idEmp));

                            personaAsignada.setEstado(new EstadoDao().GetById(res.getString("estado")));
                            personaAsignada.setUsuario(res.getString("usuario"));
                            personaAsignada.setFecha(res.getDate("fecha"));
                            personaAsignada.setAsistio(asistenciaDao.tieneAsistencia(res.getInt("codigoevento"), res.getInt("iddivipol"), res.getInt("nrodoc")));
                            personaAsignada.setId(res.getInt("id"));

                            personaAsignada.setCantidadasistio(res.getInt("cantidadasistio"));
                            personaAsignada.setCantidadnoasistio(res.getInt("cantidadnoasistio"));

                            personaAsignada.setAsistenciabiometrica(asistenciaDao.tieneAsistenciaBiometrica(res.getInt("codigoevento"), res.getInt("iddivipol"), res.getInt("nrodoc")));
                            personaAsignada.setEspolivalente(res.getInt("espolivalente"));
                            personaAsignada.setIdTipoSitio(res.getInt("idTipoSitio"));

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString(), idEvento, nroDoc);

        } catch (Exception e) {
            Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return personaAsignada;
    }

    public PersonaAsignada getPersonaAsignadaTipo(int idPrueba, int codigoEvento, long nroDoc) {

        final PersonaAsignada personaAsignada = new PersonaAsignada();
        try {
            StringBuilder sql = new StringBuilder();

            sql.append(" select d.*,0 as espolivalente,0 as idTipoSitio  from detalle_cargo_x_puesto_x_evento d ");
            sql.append(" where d.idprueba=").append(idPrueba);
            sql.append(" and   d.codigoEvento in (select distinct e1.codigoEvento from evento e,evento e1  ");
            sql.append(" where e.codigoEvento=").append(codigoEvento).append(" and e.tipoSesion=e1.tipoSesion and e.idprueba=e1.idprueba) ");
            sql.append(" and   d.nrodoc=").append(nroDoc);

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Municipio municipio = new MunicipioDao().GetById(res.getString("codigoDepartamento"), res.getString("codigoMunicipio"));
                            personaAsignada.setMunicipio(municipio);
                            personaAsignada.setIddivipol(res.getInt("iddivipol"));
                            personaAsignada.setZona(res.getString("codigoZona"));
                            personaAsignada.setPuesto(res.getString("codigoPuesto"));
                            personaAsignada.setUbicacion(res.getString("ubicacion"));
                            personaAsignada.setCargo(new CargoDao().GetById(res.getString("codigoCargo")));
                            personaAsignada.setEvento(new EventoDao().GetById(res.getInt("codigoEvento")));

                            int idEmp = new EmpleadoDao().GetIdByNumeroDocumento(res.getLong("nrodoc"));
                            personaAsignada.setEmpleado(new EmpleadoDao().GetById(idEmp));

                            personaAsignada.setEstado(new EstadoDao().GetById(res.getString("estado")));
                            personaAsignada.setUsuario(res.getString("usuario"));
                            personaAsignada.setFecha(res.getDate("fecha"));
                            personaAsignada.setAsistio(asistenciaDao.tieneAsistencia(res.getInt("codigoevento"), res.getInt("iddivipol"), res.getInt("nrodoc")));
                            personaAsignada.setId(res.getInt("id"));

                            personaAsignada.setCantidadasistio(res.getInt("cantidadasistio"));
                            personaAsignada.setCantidadnoasistio(res.getInt("cantidadnoasistio"));

                            personaAsignada.setAsistenciabiometrica(asistenciaDao.tieneAsistenciaBiometrica(res.getInt("codigoevento"), res.getInt("iddivipol"), res.getInt("nrodoc")));
                            personaAsignada.setEspolivalente(res.getInt("espolivalente"));
                            personaAsignada.setIdTipoSitio(res.getInt("idTipoSitio"));

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());

        } catch (Exception e) {
            Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return personaAsignada;
    }

    /* asigna a una persona */
    public Boolean actualizar(PersonaAsignada personaAsignada) {
        Boolean resultado = false;
        try {
            String sql;

            sql = " update detalle_cargo_x_puesto_x_evento set nrodoc = ?, estado = ?, usuario = ?, fecha = current_date, asistio = ?, cantidadasistio = ?, cantidadnoasistio = ? "
                    + " where id = ?;";

            resultado = conex.ejecutar(sql,
                    personaAsignada.getEmpleado().getNrodoc(),
                    personaAsignada.getEstado().getCodigoEstado(),
                    personaAsignada.getUsuario(),
                    personaAsignada.isAsistio(),
                    personaAsignada.getCantidadasistio(),
                    personaAsignada.getCantidadnoasistio(),
                    personaAsignada.getId());

            //codigo para replicar el nombramiento en la misma fecha
            /*
            if (resultado == true)
            {
                sql =   " UPDATE detalle_cargo_x_puesto_x_evento a, detalle_cargo_x_puesto_x_evento b\n" +
                        " SET a.nrodoc = b.nrodoc, a.estado = b.estado \n" +
                        " WHERE a.iddivipol = b.iddivipol\n" +
                        " AND a.codigoCargo = b.codigoCargo\n" +
                        " AND a.ubicacion = b.ubicacion\n" +
                        " AND a.id <> b.id\n" +
                        " AND b.id = ?\n" +
                        " AND a.codigoevento in(\n" +
                        "		select codigoevento \n" +
                        "		from evento \n" +
                        "		where idprueba = b.idPrueba \n" +
                        "		and fecha >= (select fecha from evento where codigoevento = b.codigoEvento)\n" +
                        "		); ";
                resultado = conex.ejecutar(sql, personaAsignada.getId());
            }*/
        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return resultado;
    }

    /* agrega grupos de personas asignadas*/
    public Boolean cargarAgendaCapacitacion(final co.com.grupoasd.nomina.modelo.Capacitacion agendaCapacitacion) {
        Boolean resultado = false;

        OperacionAtomica operation = new Operaciones.OperacionAtomica() {

            @Override
            public void ejecutar(Connection cn) {

                try {

                    cn.setAutoCommit(false);

                    Statement stmt = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    String sql = " insert into cargo_x_puesto_x_evento "
                            + " (codigoDepartamento, codigoMunicipio, codigoZona, "
                            + " codigoPuesto, codigoCargo, codigoEvento, cantidad, "
                            + " nombrecapacitador, fechacapacitacion, horainicialcapacitacion, "
                            + " horafinalcapacitacion, saloncapacitacion )"
                            + " values ('" + agendaCapacitacion.getIdDepartamento() + "','" + agendaCapacitacion.getIdMunicipio() + "','" + agendaCapacitacion.getIdZona() + "','" + agendaCapacitacion.getIdPuesto() + "',"
                            + "'" + agendaCapacitacion.getIdCargo() + "'," + agendaCapacitacion.getIdEvento() + "," + agendaCapacitacion.getCantidad()
                            + ",'" + agendaCapacitacion.getNombreCapacitador().toUpperCase() + "'"
                            + ",'" + agendaCapacitacion.getFecha() + "'"
                            + ",'" + agendaCapacitacion.getHoraInicial() + "'"
                            + ",'" + agendaCapacitacion.getHoraFinal() + "'"
                            + ",'" + agendaCapacitacion.getSalon() + "'"
                            + "); \n ";
                    stmt.executeUpdate(sql);

                    for (int i = 1; i <= agendaCapacitacion.getCantidad(); i++) {
                        String ubicacion = String.format("%03d", i);

                        sql = " insert into detalle_cargo_x_puesto_x_evento "
                                + " (codigoDepartamento, codigoMunicipio, codigoZona, codigoPuesto, codigoCargo, codigoEvento, ubicacion, estado) "
                                + " values ('" + agendaCapacitacion.getIdDepartamento() + "', '" + agendaCapacitacion.getIdMunicipio() + "', '" + agendaCapacitacion.getIdZona() + "', '" + agendaCapacitacion.getIdPuesto() + "', '" + agendaCapacitacion.getIdCargo() + "', " + agendaCapacitacion.getIdEvento() + ", '" + ubicacion + "', 0); \n ";

                        stmt.executeUpdate(sql);
                    }

                    cn.commit();

                } catch (SQLException ex) {
                    Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };

        conex.ejecutarOperacion(operation);

        return resultado;
    }

    /* agregar los detalles de cargo x puesto x evento*/
    public Boolean cargarDetallesTransmision(final int idEvento, final String codigoCargo) {
        Boolean resultado = false;

        try {
            OperacionAtomica operation = new Operaciones.OperacionAtomica() {
                @Override
                public void ejecutar(Connection connection) {
                    try {
                        String codigoDepartamento;
                        String codigoMunicipio;
                        String codigoZona;
                        String codigoPuesto;
                        int cantidad;
                        String ubicacion;
                        int cantidadMesas = 0;
                        int rangoInicial = 0;
                        int rangoFinal = 0;

                        String sql = " select codigoDepartamento, codigoMunicipio, codigoZona, codigoPuesto, cantidad "
                                + " from cargo_x_puesto_x_evento "
                                + " where codigoCargo = '" + codigoCargo + "' "
                                + " and codigoEvento = " + idEvento;

                        PreparedStatement consulta = connection.prepareStatement(sql);
                        ResultSet rs = consulta.executeQuery();

                        while (rs.next()) {
                            cantidad = rs.getInt("cantidad");
                            codigoDepartamento = rs.getString("codigoDepartamento");
                            codigoMunicipio = rs.getString("codigoMunicipio");
                            codigoZona = rs.getString("codigoZona");
                            codigoPuesto = rs.getString("codigoPuesto");

                            sql = " select cantidadMesas "
                                    + " from divipol "
                                    + " where codigoDepartamento = '" + codigoDepartamento + "' "
                                    + " and codigoMunicipio = '" + codigoMunicipio + "' "
                                    + " and codigoZona = '" + codigoZona + "' "
                                    + " and codigoPuesto = '" + codigoPuesto + "' ";

                            consulta = connection.prepareStatement(sql);
                            ResultSet rsMesas = consulta.executeQuery();

                            while (rsMesas.next()) {
                                cantidadMesas = rsMesas.getInt(1);
                            }

                            double cantidadXPersona = Math.ceil((double) cantidadMesas / (double) cantidad);

                            int cantidadCadaUno = (int) cantidadXPersona;

                            for (int i = 1; i <= cantidad; i++) {

                                if (i == 1) {
                                    rangoInicial = 1;
                                    rangoFinal = cantidadCadaUno;
                                } else if (i == cantidad) {
                                    rangoInicial = rangoFinal + 1;
                                    rangoFinal = cantidadMesas;
                                } else {
                                    rangoInicial = rangoFinal + 1;
                                    rangoFinal = rangoFinal + cantidadCadaUno;
                                }

                                if (cantidadMesas > 99) {
                                    ubicacion = String.format("%03d", rangoInicial) + "-" + String.format("%03d", rangoFinal);
                                } else {
                                    ubicacion = String.format("%02d", rangoInicial) + "-" + String.format("%02d", rangoFinal);
                                }

                                sql = " insert into detalle_cargo_x_puesto_x_evento "
                                        + " (codigoDepartamento, codigoMunicipio, codigoZona, codigoPuesto, codigoCargo, codigoEvento, ubicacion, estado) "
                                        + " values ('" + codigoDepartamento + "', '" + codigoMunicipio + "', '" + codigoZona + "', '" + codigoPuesto + "', '" + codigoCargo + "', " + idEvento + ", '" + ubicacion + "', 0) ";

                                consulta = connection.prepareStatement(sql);
                                consulta.executeUpdate();
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            };
            conex.ejecutarOperacion(operation);
            resultado = true;

        } catch (Exception ex) {
            Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultado;
    }

    public Boolean cargarDetalleCargos(final int idEvento) {
        Boolean resultado = false;

        OperacionAtomica operation = new Operaciones.OperacionAtomica() {
            co.com.grupoasd.nomina.modelo.Capacitacion capacitacion;

            @Override
            public void ejecutar(Connection connection) {

                try {
                    String sql;

                    sql = " select * from cargo_x_departamento ";
                    PreparedStatement consulta = connection.prepareStatement(sql);

                    ResultSet res = consulta.executeQuery();

                    int totalDeReceptores = 0;
                    int receptoresPorCoordinador = 30;
                    int rangoInicial;
                    int rangoFinal;
                    String puesto;
                    String dpto;
                    String mpio;
                    int cantidad;

                    while (res.next()) {

                        if (res.getString("codigocargo").equals("5")) {
                            dpto = res.getString("codigodepartamento");

                            if (dpto.equals("15")) {
                                mpio = "004";
                            } else {
                                mpio = "001";
                            }

                            totalDeReceptores = res.getInt("cantidad");

                            //si los receptores son menos o igual a la cantidad de receptores x coordinador
                            if (totalDeReceptores <= receptoresPorCoordinador) {
                                rangoInicial = 1;
                                rangoFinal = totalDeReceptores;
                                puesto = String.format("%04d", rangoInicial) + "-" + String.format("%04d", rangoFinal);

                                sql = " insert into cargo_x_puesto_x_evento "
                                        + " (codigoDepartamento, codigoMunicipio, codigoZona, "
                                        + " codigoPuesto, codigoCargo, codigoEvento, cantidad) "
                                        + " values ('" + dpto + "','" + mpio + "','" + puesto + "','" + puesto + "','5', " + idEvento + "  ," + totalDeReceptores + ") ";

                                consulta = connection.prepareStatement(sql);
                                consulta.executeUpdate();
                            } else {
                                rangoInicial = 1;
                                rangoFinal = receptoresPorCoordinador;
                                puesto = String.format("%04d", rangoInicial) + "-" + String.format("%04d", rangoFinal);

                                while (rangoFinal < totalDeReceptores) {
                                    cantidad = rangoFinal - rangoInicial + 1;
                                    sql = " insert into cargo_x_puesto_x_evento "
                                            + " (codigoDepartamento, codigoMunicipio, codigoZona, "
                                            + " codigoPuesto, codigoCargo, codigoEvento, cantidad) "
                                            + " values ('" + dpto + "','" + mpio + "','" + puesto + "','" + puesto + "','5'," + idEvento + "," + cantidad + ") ";
                                    consulta = connection.prepareStatement(sql);
                                    consulta.executeUpdate();

                                    if ((rangoFinal + receptoresPorCoordinador) >= totalDeReceptores) {
                                        rangoInicial = rangoFinal + 1;
                                        rangoFinal = totalDeReceptores;
                                        puesto = String.format("%04d", rangoInicial) + "-" + String.format("%04d", rangoFinal);

                                        cantidad = rangoFinal - rangoInicial + 1;
                                        sql = " insert into cargo_x_puesto_x_evento "
                                                + " (codigoDepartamento, codigoMunicipio, codigoZona, "
                                                + " codigoPuesto, codigoCargo, codigoEvento, cantidad) "
                                                + " values ('" + dpto + "','" + mpio + "','" + puesto + "','" + puesto + "','5'," + idEvento + "," + cantidad + ") ";
                                        consulta = connection.prepareStatement(sql);
                                        consulta.executeUpdate();
                                    } else {
                                        rangoInicial = rangoFinal + 1;
                                        rangoFinal = rangoFinal + receptoresPorCoordinador;
                                        puesto = String.format("%04d", rangoInicial) + "-" + String.format("%04d", rangoFinal);
                                    }
                                }
                            }

                            cargarDetalles(dpto, idEvento, "5");

                        } else {
                            capacitacion = new Capacitacion();
                            capacitacion.setIdDepartamento(res.getString("codigodepartamento"));
                            if (res.getString("codigodepartamento").equals("15")) {
                                capacitacion.setIdMunicipio("004");
                            } else {
                                capacitacion.setIdMunicipio("001");
                            }
                            capacitacion.setCantidad(res.getInt("cantidad"));
                            capacitacion.setIdCargo(res.getString("codigocargo"));
                            capacitacion.setIdEvento(idEvento);
                            capacitacion.setNombreCapacitador(" ");
                            capacitacion.setSalon(" ");
                            capacitacion.setHoraInicial(" ");
                            capacitacion.setHoraFinal(" ");
                            capacitacion.setFecha(" ");
                            capacitacion.setIdZona("0000");
                            capacitacion.setIdPuesto("0000");
                            cargarAgendaCapacitacion(capacitacion);
                        }
                    }

                } catch (Exception e) {
                    Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, e);
                }
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        conex.ejecutarOperacion(operation);
        return resultado;
    }

    /* agregar los detalles de cargo x puesto x evento*/
    public Boolean cargarDetalles(final String codigoDepartamento, final int idEvento, final String codigoCargo) {

        final Object[] result = new Object[1];
        OperacionAtomica operation = new Operaciones.OperacionAtomica() {

            @Override
            public void ejecutar(Connection conex) {

                Boolean resultado = true;
                String sql;
                String codigoMunicipio;
                String codigoZona;
                String codigoPuesto;
                int cantidad;
                String ubicacion;

                try {
                    sql = " select codigoMunicipio, codigoZona, codigoPuesto, cantidad "
                            + " from cargo_x_puesto_x_evento "
                            + " where codigoDepartamento = '" + codigoDepartamento + "' "
                            + " and codigoCargo = '" + codigoCargo + "' "
                            + " and codigoEvento = " + idEvento;

                    PreparedStatement consulta = conex.prepareStatement(sql);
                    ResultSet rs = consulta.executeQuery();

                    while (rs.next()) {
                        cantidad = rs.getInt("cantidad");
                        codigoMunicipio = rs.getString("codigoMunicipio");
                        codigoZona = rs.getString("codigoZona");
                        codigoPuesto = rs.getString("codigoPuesto");

                        int inicial;

                        if (codigoCargo.equals("5")) {
                            String rangos[] = codigoPuesto.split("-");
                            inicial = Integer.parseInt(rangos[0].trim());
                            cantidad = Integer.parseInt(rangos[1].trim());
                        } else {
                            inicial = 1;
                        }

                        for (int i = inicial; i <= cantidad; i++) {
                            ubicacion = String.format("%04d", i);
                            sql = " insert into detalle_cargo_x_puesto_x_evento "
                                    + " (codigoDepartamento, codigoMunicipio, codigoZona, codigoPuesto, codigoCargo, codigoEvento, ubicacion, estado) "
                                    + " values ('" + codigoDepartamento + "', '" + codigoMunicipio + "', '" + codigoZona + "', '" + codigoPuesto + "', '" + codigoCargo + "', " + idEvento + ", '" + ubicacion + "', 0) ";

                            consulta = conex.prepareStatement(sql);
                            consulta.executeUpdate();
                        }
                    }

                    resultado = true;
                    result[0] = resultado;

                } catch (Exception ex) {
                    Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        };

        conex.ejecutarOperacion(operation);
        return (Boolean) result[0];
    }

    /* Consulta el listado de personas asignadas en un sitio para un evento */
    public String listarAsistenciaXSitio(int idEvento, String codigoSitio) {
        final Object[] result = new Object[1];
        final JSONArray json = new JSONArray();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" select dcpe.id, car.descripcion, dcpe.nrodoc, emp.apellido1, emp.apellido2, emp.nombre1, emp.nombre2, emp.celular, emp.telefono, \n");
            sql.append(" case when asis.biometrico is null then 0 else 1 end as asistio, dcpe.salon, \n");
            sql.append(" car.esicfes, car.esSuplente, emp.idEmpleado, dcpe.numeroSilla, dcpe.ubicacion \n");
            sql.append(" from detalle_cargo_x_puesto_x_evento dcpe left join empleado emp\n");
            sql.append(" on dcpe.nrodoc = emp.nrodoc\n");
            sql.append(" left join asistencia asis\n");
            sql.append(" on dcpe.codigoevento = asis.codigoevento\n");
            sql.append(" and dcpe.iddivipol = asis.iddivipol\n");
            sql.append(" and emp.idEmpleado = asis.idempleado\n");
            sql.append(" left join cargos car\n");
            sql.append(" on dcpe.codigocargo = car.codigoCargo\n");
            sql.append(" where dcpe.codigoevento = ? \n");
            sql.append(" and dcpe.codigopuesto = ? ");

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            JSONObject personaAsig = new JSONObject();
                            personaAsig.put("id", res.getString("id"));
                            personaAsig.put("descripcion", res.getString("descripcion"));
                            personaAsig.put("nrodoc", res.getString("nrodoc") != null ? res.getString("nrodoc") : '0');
                            personaAsig.put("apellido1", res.getString("apellido1"));
                            personaAsig.put("apellido2", res.getString("apellido2"));
                            personaAsig.put("nombre1", res.getString("nombre1"));
                            personaAsig.put("nombre2", res.getString("nombre2"));
                            personaAsig.put("celular", res.getString("celular"));
                            personaAsig.put("telefono", res.getString("telefono"));
                            personaAsig.put("asistio", res.getString("asistio"));
                            personaAsig.put("salon", res.getString("salon"));
                            personaAsig.put("esicfes", res.getString("esicfes"));
                            personaAsig.put("esSuplente", res.getString("esSuplente"));
                            personaAsig.put("orden", res.getString("numeroSilla"));
                            personaAsig.put("ubicacion", res.getString("ubicacion"));

                            personaAsig.put("documentoCompleto", new EmpleadoDao().consultarDocumentosEmpleados(res.getInt("idEmpleado")));

                            json.put(personaAsig);
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString(), idEvento, codigoSitio);

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    /* Consulta el listado de personas asignadas en un sitio para un evento */
    public String resumenAsistenciaXSitio(int idEvento, String codigoSitio) {

        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            String sql;
            sql = " select b.codigocargo, b.nombrecargo, \n"
                    + " (SELECT count(dcpe.id)\n"
                    + " FROM detalle_cargo_x_puesto_x_evento dcpe\n"
                    + " LEFT JOIN empleado emp ON dcpe.nrodoc = emp.nrodoc\n"
                    + " LEFT JOIN asistencia asis ON dcpe.codigoevento = asis.codigoevento \n"
                    + " AND dcpe.iddivipol = asis.iddivipol AND emp.idEmpleado = asis.idempleado\n"
                    + " LEFT JOIN cargos car ON dcpe.codigocargo = car.codigoCargo\n"
                    + " WHERE dcpe.codigoevento = b.codigoevento \n"
                    + " AND dcpe.codigopuesto = b.codigopuesto\n"
                    + " AND asis.biometrico is null\n"
                    + " AND dcpe.codigocargo = b.codigocargo) as noasistio,\n"
                    + " (SELECT count(dcpe.id)\n"
                    + " FROM detalle_cargo_x_puesto_x_evento dcpe\n"
                    + " LEFT JOIN empleado emp ON dcpe.nrodoc = emp.nrodoc\n"
                    + " LEFT JOIN asistencia asis ON dcpe.codigoevento = asis.codigoevento \n"
                    + " AND dcpe.iddivipol = asis.iddivipol AND emp.idEmpleado = asis.idempleado\n"
                    + " LEFT JOIN cargos car ON dcpe.codigocargo = car.codigoCargo\n"
                    + " WHERE dcpe.codigoevento = b.codigoevento\n"
                    + " AND dcpe.codigopuesto = b.codigopuesto\n"
                    + " AND asis.biometrico is not null\n"
                    + " AND dcpe.codigocargo = b.codigocargo) as asistio\n"
                    + " from (\n"
                    + " select distinct dcpe.codigocargo, dcpe.codigoEvento, dcpe.codigopuesto, car.descripcion as nombrecargo\n"
                    + " FROM detalle_cargo_x_puesto_x_evento dcpe left join cargos car\n"
                    + " on dcpe.codigoCargo = car.codigoCargo\n"
                    + " WHERE dcpe.codigoevento = ?	\n"
                    + " AND dcpe.codigopuesto = ?) as b ";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, idEvento, codigoSitio);

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    /* Consulta una personas asignada en un sitio para un evento */
    public String getAsignacion(int idEvento, String codigoSitio, long nrodoc) {
        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            String sql;
            sql = " select dcpe.id, dcpe.iddivipol, car.descripcion, dcpe.nrodoc, \n"
                    + " emp.apellido1, emp.apellido2, emp.nombre1, emp.nombre2, emp.idEmpleado, dcpe.salon\n"
                    + " from detalle_cargo_x_puesto_x_evento dcpe left join empleado emp\n"
                    + " on dcpe.nrodoc = emp.nrodoc\n"
                    + " left join asistencia asis\n"
                    + " on dcpe.codigoevento = asis.codigoevento\n"
                    + " and dcpe.iddivipol = asis.iddivipol\n"
                    + " and emp.idEmpleado = asis.idempleado\n"
                    + " left join cargos car\n"
                    + " on dcpe.codigocargo = car.codigoCargo\n"
                    + " where dcpe.codigoevento = ? \n"
                    + " and dcpe.codigopuesto = ? \n"
                    + " and dcpe.nrodoc = ? ";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, idEvento, codigoSitio, nrodoc);

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    public List<PersonaAsignada> listarCargosAsignados(int idDcpe, int idPrueba) {

        StringBuilder sql = new StringBuilder();

        PersonaAsignada persona = this.getById(idDcpe);

        Cargo c = new CargoDao().GetById(persona.getCargo().getCodigoCargo());
        if (persona.getMunicipio().getCodigoMunicipio().equals("11001") && c.getEsicfes() == 1) {

            sql.append("select distinct c.codigoCargo, c.descripcion from detalle_cargo_x_puesto_x_evento d ");
            sql.append("inner join cargos  c on c.codigoCargo=d.codigoCargo ");
            sql.append(" where d.idPrueba=? and c.nivel_cargo= ");
            sql.append(c.getNivel_cargo());
            sql.append(" and d.codigoCargo !=? and d.codigoPuesto=? and d.codigoEvento=?");

        } else {
            sql.append("select distinct c.codigoCargo, c.descripcion from detalle_cargo_x_puesto_x_evento d ");
            sql.append("inner join cargos  c on c.codigoCargo=d.codigoCargo ");
            sql.append(" where d.idPrueba=? and c.esicfes=0 ");
            sql.append(" and d.codigoCargo !=? and d.codigoPuesto=? and d.codigoEvento=?");
        }
        final List<PersonaAsignada> listado = new ArrayList<>();
        try {

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            PersonaAsignada personaAsignada = new PersonaAsignada();
                            Cargo cargo = new Cargo();
                            cargo.setCodigoCargo(res.getString("codigoCargo"));
                            cargo.setDescripcion(res.getString("descripcion"));
                            personaAsignada.setCargo(cargo);
                            listado.add(personaAsignada);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString(), idPrueba, persona.getCargo().getCodigoCargo(), persona.getPuesto(), persona.getEvento().getCodigoEvento());

        } catch (Exception e) {
            Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return listado;
    }

    public List<PersonaAsignada> listarPersonasAsignadasaCargo(int idEvento, int idPrueba, String codigoCargo, String codigoPuesto) {

        final List<PersonaAsignada> listado = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select * from detalle_cargo_x_puesto_x_evento  ");
            sql.append(" where idPrueba=? ");
            sql.append(" and codigoCargo =? and codigoPuesto=? and codigoEvento=? ");

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            PersonaAsignada personaAsignada = new PersonaAsignada();
                            if (res.getString("nrodoc") != null && res.getLong("nrodoc") != 0) {
                                int id = new EmpleadoDao().GetIdByNumeroDocumento(res.getLong("nrodoc"));
                                personaAsignada.setEmpleado(new EmpleadoDao().GetById(id));
                            } else {;
                                Empleado empleado = new Empleado();
                                empleado.setNombre1("Sin asignar");
                                personaAsignada.setEmpleado(empleado);
                            }

                            personaAsignada.setId(res.getInt("id"));
                            listado.add(personaAsignada);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString(), idPrueba, codigoCargo, codigoPuesto, idEvento);

        } catch (Exception e) {
            Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return listado;
    }

    /* asigna a una persona */
    public Boolean actualizarPersonaCargo(int id, Long nrodoc) {
        Boolean resultado = false;
        try {
            StringBuilder sql = new StringBuilder();

            sql.append(" update detalle_cargo_x_puesto_x_evento set nrodoc = ? where id = ?");

            resultado = conex.ejecutar(sql.toString(), nrodoc, id);

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return resultado;
    }

    /* desasigna a una persona */
    public Boolean desasignarPersonaDcpe(int id) {
        Boolean resultado = false;
        try {
            StringBuilder sql = new StringBuilder();

            sql.append(" update detalle_cargo_x_puesto_x_evento set nrodoc=0, estado=0 where id = ? ");

            resultado = conex.ejecutar(sql.toString(), id);

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return resultado;
    }

    /* cambia el sal??n de una persona*/
    public Boolean actualizarPersonaSalon(int id, String salon) {
        Boolean resultado = false;
        try {
            StringBuilder sql = new StringBuilder();

            sql.append(" update detalle_cargo_x_puesto_x_evento set salon = ? where id = ?");

            resultado = conex.ejecutar(sql.toString(), salon, id);

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return resultado;
    }

    public List<PersonaAsignada> listarSalonesAsignados(int idEvento, int idPrueba, String salon, String codigoPuesto, String codigoCargo) {

        if (salon == null) {
            salon = "";
        }
        Cargo cargo = new CargoDao().GetById(codigoCargo);

        final List<PersonaAsignada> listado = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select d.id, d.salon, d.codigoCargo  from detalle_cargo_x_puesto_x_evento d ");
            sql.append(" inner join cargos c on c.codigoCargo=d.codigoCargo ");

            if (cargo.getNivel_cargo() == 7 && cargo.getEsSuplente() != 1) {
                sql.append("  where d.idPrueba=? and c.nivel_cargo=7 ");
            } else {
                sql.append(" where d.idPrueba=? and d.codigoCargo=").append(codigoCargo);
            }
            sql.append(" and d.salon!=? and d.codigoPuesto=? and d.codigoEvento=? order by d.salon");

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            PersonaAsignada personaAsignada = new PersonaAsignada();
                            personaAsignada.setSalon(res.getString("salon"));
                            personaAsignada.setId(res.getInt("id"));
                            personaAsignada.setCargo(new CargoDao().GetById(res.getString("codigoCargo")));
                            listado.add(personaAsignada);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString(), idPrueba, salon, codigoPuesto, idEvento);

        } catch (Exception e) {
            Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return listado;
    }

    public PersonaAsignada buscarPersonaAsignadaSalon(int id) {

        final PersonaAsignada personaAsignada = new PersonaAsignada();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select d.nrodoc, d.codigoCargo, d.salon, d.id from detalle_cargo_x_puesto_x_evento d ");
            sql.append(" where  d.id=? limit 1");

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            if (res.getString("nrodoc") != null) {
                                int id = new EmpleadoDao().GetIdByNumeroDocumento(res.getLong("nrodoc"));
                                personaAsignada.setEmpleado(new EmpleadoDao().GetById(id));
                            } else {
                                Empleado empleado = new Empleado();
                                empleado.setNombre1("Sin asignar Persona");
                                personaAsignada.setEmpleado(empleado);
                            }
                            personaAsignada.setCargo(new CargoDao().GetById(res.getString("codigoCargo")));
                            personaAsignada.setSalon(res.getString("salon"));
                            personaAsignada.setId(res.getInt("id"));

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString(), id);

        } catch (Exception e) {
            Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return personaAsignada;
    }

    /**
     * Busca el historial donde ha sido asignada(trabajado) una persona
     */
    public List<PersonaAsignada> listarHistorialLaboralPersona(int idEmpleado) {

        final List<PersonaAsignada> personasAsignadas = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select d.*, di.nombrePuesto ");
            sql.append("from detalle_cargo_x_puesto_x_evento d ");
            sql.append("left join empleado e on e.nrodoc=d.nrodoc ");
            sql.append("left join divipol di on di.idDivipol=d.idDivipol ");
            sql.append("where e.idEmpleado=");
            sql.append(idEmpleado);

            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            PersonaAsignada personaAsignada = new PersonaAsignada();

                            Municipio municipio = new MunicipioDao().GetById(res.getString("codigodepartamento"), res.getString("codigomunicipio"));

                            personaAsignada.setMunicipio(municipio);
                            personaAsignada.setPuesto(res.getString("codigopuesto"));
                            personaAsignada.setUbicacion(res.getString("nombrePuesto"));
                            personaAsignada.setCargo(new CargoDao().GetById(res.getString("codigocargo")));
                            personaAsignada.setEvento(new EventoDao().GetById(res.getInt("codigoevento")));

                            personaAsignada.setEstado(new EstadoDao().GetById(res.getString("estado")));
                            personaAsignada.setUsuario(res.getString("usuario"));
                            personaAsignada.setFecha(res.getDate("fecha"));
                            personaAsignada.setSalon(res.getString("salon"));
                            personaAsignada.setAsistio(asistenciaDao.tieneAsistencia(res.getInt("codigoevento"), res.getInt("iddivipol"), res.getInt("nrodoc")));
                            personaAsignada.setId(res.getInt("id"));
                            personasAsignadas.add(personaAsignada);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return personasAsignadas;
    }

    public PersonaAsignada buscarPersonaAsignadaPorSitioCargo(int idPrueba, String cargo, int idDivipol) {

        final PersonaAsignada personaAsignada = new PersonaAsignada();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select * from detalle_cargo_x_puesto_x_evento d ");
            sql.append(" where d.idPrueba=? and d.codigoCargo=? ");
            sql.append(" and d.idDivipol=? limit 1");

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            if (res.getString("nrodoc") != null) {
                                int id = new EmpleadoDao().GetIdByNumeroDocumento(res.getLong("nrodoc"));
                                personaAsignada.setEmpleado(new EmpleadoDao().GetById(id));
                            }
                            personaAsignada.setCargo(new CargoDao().GetById(res.getString("codigoCargo")));

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString(), idPrueba, cargo, idDivipol);

        } catch (Exception e) {
            Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return personaAsignada;
    }

    /**
     * Busca personas asignadas como titulares y que no tengan asistencia en un
     * evento especifico
     */
    public List<PersonaAsignada> titularesAsignadosSinAsistencia(int codigoEvento, String usuario) {

        final List<PersonaAsignada> listado = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select em.idEmpleado, dt.idDivipol, dt.id, dt.codigoCargo ");
            sql.append("  from detalle_cargo_x_puesto_x_evento dt ");
            sql.append(" left join empleado em on dt.nrodoc=em.nrodoc  ");
            sql.append(" left join cargos c on c.codigoCargo=dt.codigoCargo ");
            sql.append(" where  c.esSuplente=0 and dt.codigoEvento= ");
            sql.append(codigoEvento);
            sql.append(" and dt.idDivipol in( ");
            sql.append(" select iddivipol ");
            sql.append(" from usuario_sitio ");
            sql.append(" where usuario =");
            sql.append(usuario);
            sql.append(") and (idempleado is null or idempleado not in ( select asi.idempleado from asistencia asi where asi.codigoevento= ");
            sql.append(codigoEvento);
            sql.append(" and asi.iddivipol= dt.idDivipol))");

            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            PersonaAsignada personaAsignada = new PersonaAsignada();
                            personaAsignada.setCargo(new CargoDao().GetById(res.getString("codigoCargo")));
                            personaAsignada.setEmpleado(new EmpleadoDao().GetById(res.getInt("idEmpleado")));
                            personaAsignada.setIddivipol(res.getInt("idDivipol"));
                            personaAsignada.setId(res.getInt("id"));
                            listado.add(personaAsignada);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return listado;
    }

    /**
     * Busca personas asignadas como titulares y que no tengan asistencia en un
     * evento especifico
     */
    public List<PersonaAsignada> suplentesAsignadosConAsistencia(int codigoEvento, String usuario, int nivelCargo, int idDivipol) {

        final List<PersonaAsignada> listado = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select em.idEmpleado, dt.idDivipol, dt.id, dt.codigoCargo ");
            sql.append(" from empleado em ");
            sql.append(" left join detalle_cargo_x_puesto_x_evento dt on dt.nrodoc=em.nrodoc ");
            sql.append(" left join cargos c on c.codigoCargo=dt.codigoCargo ");
            sql.append(" where  c.esSuplente=1 and c.nivel_cargo=").append(nivelCargo);
            sql.append(" and dt.codigoEvento=").append(codigoEvento);
            sql.append(" and dt.idDivipol in( ");
            sql.append(" select iddivipol ");
            sql.append(" from usuario_sitio ");
            sql.append(" where usuario =").append(usuario);
            sql.append(" ) and idempleado in ( select idempleado from asistencia where codigoevento=").append(codigoEvento);
            sql.append(" and iddivipol=").append(idDivipol).append(")");

            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            PersonaAsignada personaAsignada = new PersonaAsignada();
                            personaAsignada.setCargo(new CargoDao().GetById(res.getString("codigoCargo")));
                            personaAsignada.setEmpleado(new EmpleadoDao().GetById(res.getInt("idEmpleado")));
                            personaAsignada.setIddivipol(res.getInt("idDivipol"));
                            personaAsignada.setId(res.getInt("id"));
                            listado.add(personaAsignada);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return listado;
    }

    /**
     * Busca personas asignadas como suplentes y que tengan asistencia en un
     * evento capacitacion dado
     *
     * @param codigoEvento
     * @param idPrueba
     * @return
     */
    public JSONArray buscarSuplentesConAsistenciaCapacitacion(int codigoEvento, int idPrueba) {
        final Object[] result = new Object[1];
        JSONArray json = new JSONArray();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select distinct dt.id, c.codigoCargo, c.descripcion, concat(em.nombre1,' ', em.nombre2) as nombres, concat(em.apellido1,' ', ifnull(em.apellido2, '')) as apellidos,");
            sql.append(" em.nrodoc, em.idEmpleado, em.salones, case epe.disponibilidad_viaje when 0 then 'No' else 'Si' end as viaja, em.latitud, em.longitud ");
            sql.append(" from detalle_cargo_x_puesto_x_evento dt ");
            sql.append(" left join cargos c on c.codigoCargo=dt.codigoCargo ");
            sql.append(" inner join empleado em on em.nrodoc=dt.nrodoc ");
            sql.append(" left join empleado_x_prueba_x_estado epe on epe.idempleado=em.idEmpleado");
            sql.append(" where dt.codigoEvento=").append(codigoEvento);
            sql.append(" and (c.esSuplente=1 or c.nivel_cargo=11) and epe.idprueba=").append(idPrueba);
            sql.append(" and em.idEmpleado in (");
            sql.append("select asis.idempleado from asistencia asis where asis.codigoevento=dt.codigoEvento) ");

            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            json = (JSONArray) result[0];
        } catch (Exception e) {
            Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json;
    }

    public String personalEnCapacitacion(int usuario, int idPrueba) {
        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" select em.nrodoc,concat(ifnull(em.apellido1,''),' ',ifnull(em.apellido2,''),' ',ifnull(em.nombre1,''),' ',ifnull(em.nombre2,'')) nombrePersonal, ");
            sql.append(" case when length(trim(em.celular))=10 then em.celular else em.telefono end as telefono,mr.nombre as residencia, ");
            sql.append(" dv.nombrePuesto as secapacitoen,e.nombre as Salon ,e.fecha,e.hora_inicial, ");
            sql.append(" case ifnull(a.biometrico,9) when 1 Then 'Biometrico' when 0 Then 'Manual' else 'Sin Asistencia' end MedioAsistencia,ifnull(a.fecha_actualiza,'No Registra') fecha_asistencia, ");
            sql.append(" case when (select count(*) from detalle_cargo_x_puesto_x_evento d1,evento e1  ");
            sql.append(" 		     where d1.idPrueba=d.idPrueba and d1.codigoEvento=e1.codigoEvento and e1.tipoSesion='SIMULACRO' and d1.nrodoc=d.nrodoc)> 0  ");
            sql.append(" Then 'SI' Else 'NO' end AsignadoEnsimulacro, ");
            sql.append(" case when (select count(*) from detalle_cargo_x_puesto_x_evento d1,evento e1  ");
            sql.append(" where d1.idPrueba=d.idPrueba and d1.codigoEvento=e1.codigoEvento and e1.tipoSesion='ELECCION' and d1.nrodoc=d.nrodoc)> 0  ");
            sql.append(" Then 'SI' Else 'NO' end AsignadoEleccion ");
            sql.append(" from detalle_cargo_x_puesto_x_evento d ");
            sql.append(" inner join evento e                on e.codigoEvento = d.codigoEvento and e.tipoSesion = 'CAPACITACION' ");
            sql.append(" inner join usuario_departamento ud on ud.usuario=").append(usuario).append(" and ud.idPrueba=d.idPrueba and ud.codigoDepartamento=d.codigoDepartamento ");
            sql.append(" inner join empleado em             on em.nrodoc=d.nrodoc ");
            sql.append(" inner join divipol dv              on dv.idDivipol=d.idDivipol ");
            sql.append(" left  join municipio mr            on mr.codigoMunicipio=em.codigoMunicipio and mr.codigoDepartamento=d.codigoDepartamento ");
            sql.append(" left  join asistencia a		on a.iddivipol=d.idDivipol and a.idempleado=em.idEmpleado and a.codigoevento=e.codigoEvento ");
            sql.append(" where d.idPrueba=").append(idPrueba);

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    public void seguimientoPersonal_Capacitacion(int usuario, int idPrueba) {

        final StringBuilder texto = new StringBuilder();
        StringBuilder sql = new StringBuilder();
        String nombreArchivo = "/data//Seguimiento_Personal_Capacitacion_" + idPrueba + ".csv";
        texto.append("Documento;Personal;Cargo;Telefono;Reside;Sitio;Salon;Fecha;Hora;Asistencia;Hora;Simulacro Asignado;Eleccion Asignado\n");

        sql.append(" select em.nrodoc,concat(ifnull(em.apellido1,''),' ',ifnull(em.apellido2,''),' ',ifnull(em.nombre1,''),' ',ifnull(em.nombre2,'')) nombrePersonal, ");
        sql.append(" case when length(trim(em.celular))=10 then em.celular else em.telefono end as telefono,mr.nombre as residencia, ");
        sql.append(" dv.nombrePuesto as secapacitoen,e.nombre as Salon ,e.fecha,e.hora_inicial, ");
        sql.append(" case ifnull(a.biometrico,9) when 1 Then 'Biometrico' when 0 Then 'Manual' else 'Sin Asistencia' end MedioAsistencia,ifnull(a.fecha_actualiza,'No Registra') fecha_asistencia, ");
        sql.append(" case when (select count(*) from detalle_cargo_x_puesto_x_evento d1,evento e1  ");
        sql.append(" 		     where d1.idPrueba=d.idPrueba and d1.codigoEvento=e1.codigoEvento and e1.tipoSesion='SIMULACRO' and d1.nrodoc=d.nrodoc)> 0  ");
        sql.append(" Then 'SI' Else 'NO' end AsignadoEnsimulacro, ");
        sql.append(" case when (select count(*) from detalle_cargo_x_puesto_x_evento d1,evento e1  ");
        sql.append(" where d1.idPrueba=d.idPrueba and d1.codigoEvento=e1.codigoEvento and e1.tipoSesion='ELECCION' and d1.nrodoc=d.nrodoc)> 0  ");
        sql.append(" Then 'SI' Else 'NO' end AsignadoEleccion,ca.descripcion ");
        sql.append(" from detalle_cargo_x_puesto_x_evento d ");
        sql.append(" inner join evento e                on e.codigoEvento = d.codigoEvento and e.tipoSesion = 'CAPACITACION' ");
        sql.append(" inner join usuario_departamento ud on ud.usuario=").append(usuario).append(" and ud.idPrueba=d.idPrueba and ud.codigoDepartamento=d.codigoDepartamento ");
        sql.append(" inner join empleado em             on em.nrodoc=d.nrodoc ");
        sql.append(" inner join cargos ca               on ca.codigoCargo=d.codigoCargo ");
        sql.append(" inner join divipol dv              on dv.idDivipol=d.idDivipol ");
        sql.append(" left  join municipio mr            on mr.codigoMunicipio=em.codigoMunicipio and mr.codigoDepartamento=d.codigoDepartamento ");
        sql.append(" left  join asistencia a		on a.iddivipol=d.idDivipol and a.idempleado=em.idEmpleado and a.codigoevento=e.codigoEvento ");
        sql.append(" where d.idPrueba=").append(idPrueba);
        try {
            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            texto.append(validaNull(res.getString("nrodoc")) + ";");
                            texto.append(validaNull(res.getString("nombrePersonal")) + ";");
                            texto.append(validaNull(res.getString("descripcion")) + ";");
                            texto.append(validaNull(res.getString("telefono")) + ";");
                            texto.append(validaNull(res.getString("residencia")) + ";");
                            texto.append(validaNull(res.getString("secapacitoen")) + ";");
                            texto.append(validaNull(res.getString("Salon")) + ";");
                            texto.append(validaNull(res.getString("fecha")) + ";");
                            texto.append(validaNull(res.getString("hora_inicial")) + ";");
                            texto.append(validaNull(res.getString("MedioAsistencia")) + ";");
                            texto.append(validaNull(res.getString("fecha_asistencia")) + ";");
                            texto.append(validaNull(res.getString("AsignadoEnsimulacro")) + ";");
                            texto.append(validaNull(res.getString("AsignadoEleccion")) + ";\n");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            FileWriter fwriter = new FileWriter(nombreArchivo);
            fwriter.write(texto.toString());
            fwriter.flush();
            fwriter.close();

        } catch (Exception e) {
            Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private String validaNull(String texto) {
        if (texto == null) {
            return "";
        } else {
            return texto;
        }

    }

}
