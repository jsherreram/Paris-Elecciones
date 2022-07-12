/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Evento;
import co.com.grupoasd.nomina.modelo.InformacionConsultaOrden;
import co.com.grupoasd.nomina.modelo.Prueba;
import co.com.grupoasd.nomina.modelo.seguimiento.ResultSetConverter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author Pedro Rodriguez
 */
public class EventoDao {

    private Operaciones conex = new Operaciones();
    PruebaDao pruebadao = new PruebaDao();

    public EventoDao() {
    }

    public List<Evento> listar() {
        final List<Evento> Eventos = new ArrayList<>();

        try {
            String sql = "select * from evento";

            conex.consultar(sql, new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Evento evento = new Evento();
                            evento.setCodigoEvento(res.getInt("codigoEvento"));
                            evento.setIdprueba(res.getInt("idprueba"));
                            evento.setNombre(res.getString("nombre"));
                            evento.setEsCapacitacion(res.getInt("esCapacitacion"));
                            evento.setCodigoLogisys(res.getString("codigoLogisys"));
                            evento.setTipoSesion(res.getString("tipoSesion"));
                            evento.setPrueba(pruebadao.getById(res.getInt("idprueba")));
                            evento.setFecha(res.getDate("fecha"));
                            evento.setHora_inicial(res.getString("hora_inicial"));
                            evento.setFecha_final(res.getDate("fecha_final"));
                            evento.setHora_final(res.getString("hora_final"));
                            evento.setTomaAsistencia(res.getInt("tomaAsistencia"));
                            evento.setEsPenitenciaria(res.getInt("esPenitenciaria"));
                            evento.setEsEleccion(res.getInt("esEleccion"));
                            Eventos.add(evento);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (Exception e) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Eventos;
    }

    public List<Evento> listar(int idPrueba, String usuario) {
        final List<Evento> Eventos = new ArrayList<>();
        try {
            String sql = "select distinct c.*\n"
                    + "from usuario_sitio a left join detalle_cargo_x_puesto_x_evento b\n"
                    + "on a.iddivipol = b.idDivipol\n"
                    + "left join evento c\n"
                    + "on b.codigoevento = c.codigoevento\n"
                    + "left join divipol d\n"
                    + "on b.idDivipol = d.idDivipol\n"
                    + "where a.usuario = '" + usuario + "'\n"
                    + "and c.idPrueba = " + idPrueba + "  \n"
                    + "and c.tomaAsistencia = 1;";
            conex.consultar(sql,
                    new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {

                            Evento evento = new Evento();
                            evento.setPrueba(pruebadao.getById(res.getInt("idprueba")));
                            evento.setCodigoEvento(res.getInt("codigoEvento"));
                            evento.setNombre(res.getString("nombre"));
                            evento.setFecha(res.getDate("fecha"));
                            evento.setEsCapacitacion(res.getInt("esCapacitacion"));
                            Eventos.add(evento);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (Exception e) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Eventos;
    }

    public Evento GetById(int id) {

        final Evento evento = new Evento();

        try {
            StringBuilder sb = new StringBuilder();
            sb.append("select * from evento where codigoEvento = ");
            sb.append(id);
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            evento.setPrueba(pruebadao.getById(res.getInt("idprueba")));
                            evento.setCodigoEvento(res.getInt("codigoEvento"));
                            evento.setNombre(res.getString("nombre"));
                            evento.setFecha(res.getDate("fecha"));
                            evento.setEsCapacitacion(res.getInt("esCapacitacion"));
                            evento.setTipoSesion(res.getString("tipoSesion"));
                            evento.setTomaAsistencia(res.getInt("tomaAsistencia"));
                            evento.setEsPenitenciaria(res.getInt("esPenitenciaria"));
                            evento.setHora_inicial(res.getString("hora_inicial"));
                            evento.setCargo(new CargoDao().GetById(res.getString("codcargo")));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sb.toString());

        } catch (Exception e) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return evento;
    }

    public Evento GetById(Long id) {

        final Evento evento = new Evento();

        try {
            StringBuilder sb = new StringBuilder();
            sb.append("select * from evento where codigoEvento = ");
            sb.append(id);
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            evento.setPrueba(pruebadao.getById(res.getInt("idprueba")));
                            evento.setCodigoEvento(res.getInt("codigoEvento"));
                            evento.setNombre(res.getString("nombre"));
                            evento.setFecha(res.getDate("fecha"));
                            evento.setEsCapacitacion(res.getInt("esCapacitacion"));
                            evento.setTipoSesion(res.getString("tipoSesion"));
                            evento.setTomaAsistencia(res.getInt("tomaAsistencia"));
                            evento.setEsPenitenciaria(res.getInt("esPenitenciaria"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sb.toString());

        } catch (Exception e) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return evento;
    }

    public Evento GetEventoNombramiento(Prueba prueba) {

        final Evento evento = new Evento();

        try {
            String sql = "select * from evento where idprueba = ?  and tiposesion = 'NOMBRAMIENTO';";
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            evento.setPrueba(pruebadao.getById(res.getInt("idprueba")));
                            evento.setCodigoEvento(res.getInt("codigoEvento"));
                            evento.setNombre(res.getString("nombre"));
                            evento.setFecha(res.getDate("fecha"));
                            evento.setEsCapacitacion(res.getInt("esCapacitacion"));
                            evento.setTipoSesion(res.getString("tipoSesion"));
                            evento.setTomaAsistencia(res.getInt("tomaAsistencia"));
                            evento.setEsPenitenciaria(res.getInt("esPenitenciaria"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, prueba.getIdprueba());

        } catch (Exception e) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return evento;
    }

    public List<Evento> listar(int idPrueba) {
        final List<Evento> Eventos = new ArrayList<>();
        try {
            conex.consultar("select *\n"
                    + "from evento\n"
                    + "where idprueba = " + idPrueba + " and tipoSesion != 'NOMBRAMIENTO';",
                    new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Evento evento = new Evento();
                            evento.setCodigoEvento(res.getInt("codigoEvento"));
                            evento.setNombre(res.getString("nombre"));
                            evento.setFecha(res.getDate("fecha"));
                            evento.setEsCapacitacion(res.getInt("esCapacitacion"));
                            evento.setTomaAsistencia(res.getInt("tomaAsistencia"));
                            evento.setEsPenitenciaria(res.getInt("esPenitenciaria"));
                            Eventos.add(evento);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (Exception e) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Eventos;
    }

    public List<Long> retornaEventosxPrueba(Long prueba) throws Exception {
        final ArrayList<Long> eventos = new ArrayList<Long>();
        StringBuilder sb = new StringBuilder();
        sb.append("select distinct codigoEvento from evento where idprueba =");
        sb.append(prueba);

        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        Long evento;
                        evento = resultado.getLong(1);
                        eventos.add(evento);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        return eventos;
    }

    public InformacionConsultaOrden consultaOrdenxEvento(Long idEvento, String sitio) {
        ArrayList<InformacionConsultaOrden> consulta = new ArrayList<InformacionConsultaOrden>();
        final InformacionConsultaOrden info = new InformacionConsultaOrden();
        Evento evento = new Evento();
        evento = new EventoDao().GetById(Integer.valueOf(idEvento.toString()));
        StringBuilder sb = new StringBuilder();
        sb.append("select distinct c.nombre, ");
        sb.append("b.nombre, ");
        sb.append("a.codigoPuesto, ");
        sb.append("d.nombrePuesto, ");
        sb.append("(select x.nombrePuesto ");
        sb.append("from relacion_pds z ");
        sb.append("inner join divipol x on(z.idDivipolPds = x.idDivipol) ");
        sb.append("where z.idDivipolSitio = d.idDivipol ");
        sb.append("and z.idPrueba = ");
        sb.append(evento.getPrueba().getIdprueba());
        sb.append(" and z.prioridad = 1 limit 1) as pds, ");
        sb.append("concat(ifnull(e.apellido1,''),' ',ifnull(e.apellido2,''),' ',ifnull(e.nombre1,''),' ',ifnull(e.nombre2,'')) nombresPersonal, ");
        sb.append("a.nrodoc as documentoPersonal ");
        sb.append("from detalle_cargo_x_puesto_x_evento a ");
        sb.append("inner join evento b on (b.codigoEvento = a.codigoEvento) ");
        sb.append("inner join prueba c on (c.idPrueba = b.idPrueba) ");
        sb.append("inner join divipol d on (d.idDivipol = a.idDivipol) ");
        sb.append("inner join empleado e on (e.nrodoc = a.nrodoc) ");
        sb.append("where a.idDivipol = '");
        sb.append(sitio);
        sb.append("' ");
        sb.append("and a.codigoEvento = ");
        sb.append(idEvento);
        sb.append(" and a.codigoCargo in (1,12) ");
        sb.append("order by a.codigoCargo ");
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        info.setNombrePrueba(resultado.getString(1));
                        info.setNombreSesion(resultado.getString(2));
                        info.setCodigoSitio(resultado.getString(3));
                        info.setNombreSitio(resultado.getString(4));
                        info.setNombrePds(resultado.getString(5));
                        if (resultado.getRow() == 1) {
                            info.setNombreDelegado(resultado.getString(6));
                            info.setIdentificacionDelegado(resultado.getString(7));
                        }
                        if (resultado.getRow() == 2) {
                            info.setNombreRepresentante(resultado.getString(6));
                            info.setIdentificacionRepresentante(resultado.getString(7));
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return info;
    }

    public InformacionConsultaOrden consultaOrdenxEventoOrden(String idOrden) {
        ArrayList<InformacionConsultaOrden> consulta = new ArrayList<InformacionConsultaOrden>();
        final InformacionConsultaOrden info = new InformacionConsultaOrden();
        StringBuilder sb = new StringBuilder();
        sb.append("select c.nombre, ");
        sb.append("b.nombre, ");
        sb.append("a.codigoPuesto, ");
        sb.append("d.nombrePuesto, ");
        sb.append("(select x.nombrePuesto ");
        sb.append("from relacion_pds z ");
        sb.append("inner join divipol x on(z.idDivipolPds = x.idDivipol) ");
        sb.append("where z.idDivipolSitio = d.idDivipol ");
        sb.append("and z.prioridad = 1 ) as pds, ");
        sb.append("concat(ifnull(e.apellido1,''),' ',ifnull(e.apellido2,''),' ',ifnull(e.nombre1,''),' ',ifnull(e.nombre2,'')) nombresPersonal, ");
        sb.append("a.nrodoc as documentoPersonal ");
        sb.append("from detalle_cargo_x_puesto_x_evento a ");
        sb.append("inner join evento b on (b.codigoEvento = a.codigoEvento) ");
        sb.append("inner join prueba c on (c.idPrueba = b.idPrueba) ");
        sb.append("inner join divipol d on (d.codigoPuesto = a.codigoPuesto) ");
        sb.append("inner join empleado e on (e.nrodoc = a.nrodoc) ");
        sb.append("where a.idDivipol = (select idDivipolSitio from ordenSuplencia where idOrden = " + idOrden + " ) ");
        sb.append("and a.codigoEvento = (select codigoEvento from ordenSuplencia where idOrden = " + idOrden + " ) ");
        sb.append("and a.codigoCargo in (1,12) ");
        sb.append("order by a.codigoCargo ");
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        info.setNombrePrueba(resultado.getString(1));
                        info.setNombreSesion(resultado.getString(2));
                        info.setCodigoSitio(resultado.getString(3));
                        info.setNombreSitio(resultado.getString(4));
                        info.setNombrePds(resultado.getString(5));
                        if (resultado.getRow() == 1) {
                            info.setNombreDelegado(resultado.getString(6));
                            info.setIdentificacionDelegado(resultado.getString(7));
                        }
                        if (resultado.getRow() == 2) {
                            info.setNombreRepresentante(resultado.getString(6));
                            info.setIdentificacionRepresentante(resultado.getString(7));
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return info;
    }

    public List<Evento> listarAll(int idPrueba) {
        final List<Evento> Eventos = new ArrayList<>();

        try {
            String s1 = Integer.toString(idPrueba);
            StringBuilder sql = new StringBuilder();
            sql.append(" select * from evento ");
            if (idPrueba > 0) {
                sql.append(" where idprueba = ").append(s1);
            }
            sql.append(" order by fecha,hora_inicial ");

            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Evento evento = new Evento();
                            evento.setCodigoEvento(res.getInt("codigoEvento"));
                            evento.setIdprueba(res.getInt("idprueba"));
                            evento.setNombre(res.getString("nombre"));
                            evento.setEsCapacitacion(res.getInt("esCapacitacion"));
                            evento.setCodigoLogisys(res.getString("codigoLogisys"));
                            evento.setTipoSesion(res.getString("tipoSesion"));
                            evento.setPrueba(pruebadao.getById(res.getInt("idprueba")));
                            evento.setFecha(res.getDate("fecha"));
                            evento.setHora_inicial(res.getString("hora_inicial"));
                            evento.setFecha_final(res.getDate("fecha_final"));
                            evento.setHora_final(res.getString("hora_final"));
                            evento.setActivo(res.getInt("activo"));
                            evento.setTomaAsistencia(res.getInt("tomaAsistencia"));
                            evento.setEsPenitenciaria(res.getInt("esPenitenciaria"));
                            evento.setEsEleccion(res.getInt("esEleccion"));
                            Eventos.add(evento);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());
        } catch (Exception e) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Eventos;
    }

    public List<Evento> listarDepartamento(int idPrueba, String usuario) {
        final List<Evento> Eventos = new ArrayList<>();

        try {
            String s1 = Integer.toString(idPrueba);
            StringBuilder sql = new StringBuilder();
            CargoDao cargo = null;
            sql.append(" select e.*, (select count(*) from detalle_cargo_x_puesto_x_evento d where (nrodoc is null or nrodoc='') and d.codigoEvento=e.codigoEvento) as disponibles from evento e");
            if (idPrueba > 0) {
                sql.append(" where idprueba =").append(s1);
                sql.append(" and coddepartamento in(select codigoDepartamento from usuario_departamento ");
                sql.append(" where idPrueba=").append(s1).append(" and usuario=").append(usuario).append(")");
            }
            sql.append(" order by fecha,hora_inicial ");

            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Evento evento = new Evento();
                            evento.setCodigoEvento(res.getInt("codigoEvento"));
                            evento.setIdprueba(res.getInt("idprueba"));
                            evento.setNombre(res.getString("nombre"));
                            evento.setEsCapacitacion(res.getInt("esCapacitacion"));
                            evento.setCodigoLogisys(res.getString("codigoLogisys"));
                            evento.setTipoSesion(res.getString("tipoSesion"));
                            evento.setPrueba(pruebadao.getById(res.getInt("idprueba")));
                            evento.setFecha(res.getDate("fecha"));
                            evento.setHora_inicial(res.getString("hora_inicial"));
                            evento.setFecha_final(res.getDate("fecha_final"));
                            evento.setHora_final(res.getString("hora_final"));
                            evento.setActivo(res.getInt("activo"));
                            evento.setTomaAsistencia(res.getInt("tomaAsistencia"));
                            evento.setEsPenitenciaria(res.getInt("esPenitenciaria"));
                            evento.setCoddepartamento(res.getString("coddepartamento"));
                            evento.setIddivipol(res.getInt("iddivipol"));
                            evento.setCodCargo(res.getString("codcargo"));
                            evento.setCantidadcapacitados(res.getInt("cantidadcapacitados"));
                            evento.setCargo(new CargoDao().GetById(res.getString("codcargo")));
                            evento.setDisponibles(res.getInt("disponibles"));
                            Eventos.add(evento);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());
        } catch (Exception e) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Eventos;
    }

    public List<Evento> listarEvento(int idEvento) {
        final List<Evento> Eventos = new ArrayList<>();

        try {
            String s1 = Integer.toString(idEvento);
            StringBuilder sql = new StringBuilder();
            sql.append(" select * from evento ");
            if (idEvento != 0) {
                sql.append(" where codigoEvento = ").append(s1);
            }
            sql.append(" order by fecha,nombre ");

            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Evento evento = new Evento();
                            evento.setCodigoEvento(res.getInt("codigoEvento"));
                            evento.setIdprueba(res.getInt("idprueba"));
                            evento.setNombre(res.getString("nombre"));
                            evento.setEsCapacitacion(res.getInt("esCapacitacion"));
                            evento.setCodigoLogisys(res.getString("codigoLogisys"));
                            evento.setTipoSesion(res.getString("tipoSesion"));
                            evento.setPrueba(pruebadao.getById(res.getInt("idprueba")));
                            evento.setFecha(res.getDate("fecha"));
                            evento.setHora_inicial(res.getString("hora_inicial"));
                            evento.setFecha_final(res.getDate("fecha_final"));
                            evento.setHora_final(res.getString("hora_final"));
                            evento.setActivo(res.getInt("activo"));
                            evento.setTomaAsistencia(res.getInt("tomaAsistencia"));
                            evento.setEsPenitenciaria(res.getInt("esPenitenciaria"));
                            evento.setCoddepartamento(res.getString("coddepartamento"));
                            evento.setIddivipol(res.getInt("iddivipol"));
                            evento.setCodCargo(res.getString("codcargo"));
                            evento.setCantidadcapacitados(res.getInt("cantidadcapacitados"));
                            evento.setEsEleccion(res.getInt("esEleccion"));
                            evento.setCargo(new CargoDao().GetById(res.getString("codcargo")));
                            Eventos.add(evento);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());
        } catch (Exception e) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Eventos;
    }

    public String listarTipSesion() {
        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select tipoSesion from tipo_sesion where activo = 1 ");
            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {
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

    public int insertar(Prueba prueba) {
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into prueba (idprueba,nombre,tprueba,estadoprueba,dias,fecha_final_aplicacion,fecha_final_convocatoria, ");
        sql.append(" fecha_inicio_convocatoria,vtiger_campo_estado,vtiger_campo_texto,vtiger_campo_cargo,fechaaplicacion, ");
        sql.append(" fecha_actualiza,vtiger_campo_municipio,vtiger_campo_nodo,texto_convocatoria,idEstadoPrueba) Values ( 0,");
        sql.append("'").append(prueba.getNombre()).append("',");
        sql.append("").append(prueba.getTprueba()).append(",");
        //sql.append("(select Idtprueba from tipoprueba where tprueba='").append(prueba.getTnombreprueba()).append("'),");
        sql.append("'").append(prueba.getCodigoEstadoPrueba()).append("',");
        sql.append("'").append(prueba.getDias()).append("',");
        sql.append("").append(prueba.getFecha_final_aplicacion()).append(",");
        sql.append("").append(prueba.getFecha_final_convocatoria()).append(",");
        sql.append("").append(prueba.getFecha_inicio_convocatoria()).append(",");
        sql.append("'").append(prueba.getVtiger_campo_estado()).append("',");
        sql.append("'").append(prueba.getVtiger_campo_texto()).append("',");
        sql.append("'").append(prueba.getVtiger_campo_cargo()).append("',");
        sql.append("").append(prueba.getFechaaplicacion()).append(",");
        sql.append("current_date,");
        sql.append("'").append(prueba.getVtiger_campo_municipio()).append("',");
        sql.append("'").append(prueba.getVtiger_campo_nodo()).append("',");
        sql.append("'").append(prueba.getTexto_convocatoria()).append("',");
        sql.append("(select idEstadoPrueba from estadosPrueba where codigoEstadoPrueba='").append(prueba.getCodigoEstadoPrueba()).append("'))");

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
                    Logger.getLogger(PruebaDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, sql.toString());
        return ((int) result[0]);
    }

    public int insertar(Evento evento) {
        int siguiente = this.siguiente();
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into evento (codigoEvento,idprueba,nombre,esCapacitacion,codigoLogisys,tipoSesion,fecha,"
                + "hora_inicial,fecha_final,hora_final,tomaAsistencia,esPenitenciaria,coddepartamento,idDivipol,codcargo,"
                + "cantidadcapacitados) Values(");
        sql.append(Integer.toString(siguiente)).append(",");
        sql.append("").append(evento.getIdprueba()).append(",");
        sql.append("'").append(evento.getNombre()).append("',");
        sql.append("").append(evento.esCapacitacion()).append(",");
        sql.append("'").append(evento.getCodigoLogisys()).append("',");
        sql.append("'").append(evento.getTipoSesion()).append("',");
        sql.append("?,");
        sql.append("'").append(evento.getHora_inicial()).append("',");
        sql.append("?,");
        sql.append("'").append(evento.getHora_final()).append("',");
        sql.append("").append(evento.getTomaAsistencia()).append(",");
        sql.append("").append(evento.getEsPenitenciaria()).append(",");
        sql.append("'").append(evento.getCoddepartamento()).append("',");
        sql.append("").append(evento.getIddivipol()).append(",");
        sql.append("'").append(evento.getCodCargo()).append("',");
        sql.append("").append(evento.getCantidadcapacitados()).append(")");

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
                    Logger.getLogger(PruebaDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, sql.toString(), evento.getFecha(), evento.getFecha_final());
//        }, sql.toString());
        evento.setCodigoEvento(siguiente);
        this.Actualizar(evento);
        return (siguiente);
    }

    /**
     * Author: John Steak Herrera Moreno 
     * Date: 05/05/2022
     * 
     * @param evento
     * @return 
     */
    public int insertarEvento(Evento evento) {
        int siguiente = this.siguiente();
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO evento (codigoEvento, idprueba, nombre, esCapacitacion, codigoLogisys, tipoSesion, "
                + "fecha, hora_inicial, fecha_final, hora_final, tomaAsistencia, esPenitenciaria, activo, "
                + "fecha_actualiza, abrirsesion, coddepartamento, cantidadcapacitados, esEleccion, "
                + "requiereAsistenciaCtaCobro, codigoeventoPadre) VALUES (");
        sql.append(Integer.toString(siguiente)).append(", ");
        sql.append("").append(evento.getIdprueba()).append(", ");
        sql.append("'").append(evento.getNombre()).append("', ");
        sql.append("0, ");
        sql.append("'0', ");
        sql.append("'").append(evento.getTipoSesion()).append("', ");
        sql.append("?, ");
        sql.append("'").append(evento.getHora_inicial()).append("', ");
        sql.append("?, ");
        sql.append("'").append(evento.getHora_final()).append("', ");
        sql.append("b'0', ");
        sql.append("b'0', ");
        sql.append("b'1', ");
        sql.append("CURRENT_TIMESTAMP, ");
        sql.append("b'0', ");
        sql.append("'99', ");
        sql.append("0, ");
        sql.append("").append(evento.getEsEleccion()).append(", ");
        sql.append("0, ");
        sql.append("0)");

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
                    Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, sql.toString(), evento.getFecha(), evento.getFecha_final());
        evento.setCodigoEvento(siguiente);
        this.actualizarEvento(evento);
        return (siguiente);
    }

    public Boolean Actualizar(Evento evento) {
        Boolean resultado = false;
        StringBuilder sql = new StringBuilder();
        sql.append("  update evento p set ");
        sql.append("  p.nombre = ? ");
        sql.append(", p.esCapacitacion = ? ");
        sql.append(", p.codigoLogisys = ? ");
        sql.append(", p.tipoSesion = ? ");
        sql.append(", p.fecha = ? ");
        sql.append(", p.hora_inicial = ? ");
        sql.append(", p.fecha_final = ? ");
        sql.append(", p.hora_final = ? ");
        sql.append(", p.activo = ? ");
        sql.append(", p.tomaAsistencia = ? ");
        sql.append(", p.esPenitenciaria = ? ");
        sql.append(", p.coddepartamento = ? ");
        sql.append(", p.iddivipol = ? ");
        sql.append(", p.codcargo = ? ");
        sql.append(", p.cantidadcapacitados = ? ");
        sql.append("  where p.codigoEvento= ? ");
        resultado = conex.ejecutar(sql.toString(),
                evento.getNombre(),
                evento.esCapacitacion(),
                evento.getCodigoLogisys(),
                evento.getTipoSesion(),
                evento.getFecha(),
                evento.getHora_inicial(),
                evento.getFecha_final(),
                evento.getHora_final(),
                evento.getActivo(),
                evento.getTomaAsistencia(),
                evento.getEsPenitenciaria(),
                evento.getCoddepartamento(),
                evento.getIddivipol(),
                evento.getCodCargo(),
                evento.getCantidadcapacitados(),
                evento.getCodigoEvento());
        return resultado;
    }

    /**
     * Author: John Steak Herrera Moreno 
     * Date: 05/05/2022
     * 
     * @param evento
     * @return 
     */
    public Boolean actualizarEvento(Evento evento) {
        Boolean resultado = false;
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE evento e SET");
        sql.append(" e.nombre = UPPER(?");
        sql.append("), e.tipoSesion = UPPER(?");
        sql.append("), e.fecha = ?");
        sql.append(", e.hora_inicial = ?");
        sql.append(", e.fecha_final = ?");
        sql.append(", e.hora_final = ?");
        sql.append(", e.esEleccion = ?");
        sql.append(" WHERE e.codigoEvento = ?");
        resultado = conex.ejecutar(sql.toString(),
                evento.getNombre(),
                evento.getTipoSesion(),
                evento.getFecha(),
                evento.getHora_inicial(),
                evento.getFecha_final(),
                evento.getHora_final(),
                evento.getEsEleccion(),
                evento.getCodigoEvento());
        return resultado;
    }

    public Boolean ActualizarCapacitacionDcpe(Evento evento) {
        Boolean resultado = false;
        StringBuilder sql = new StringBuilder();
        sql.append("call sp_cuadro_capacitacion_elecciones (").append(evento.getIdprueba()).append(",");
        sql.append(evento.getIddivipol()).append(",'");
        sql.append(evento.getCodCargo()).append("',");
        sql.append(evento.getCodigoEvento()).append(",");
        sql.append(evento.getCantidadcapacitados()).append(")");
        resultado = conex.ejecutar(sql.toString());
        return resultado;
    }

    public int siguiente() {
        final Object id[] = new Object[1];
        id[0] = 0;
        try {
            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            id[0] = res.getInt("siguiente");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, "select max(codigoEvento)+1 as siguiente from evento");
        } catch (Exception e) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return (int) id[0];
    }

    public Evento GetEventoActualParaTomaAsistencia(int idPrueba, int idDivipol) {

        final Evento evento = new Evento();
        //StringBuilder sb = new StringBuilder();

        try {
            String sql = " select * \n"
                    + " from evento e\n"
                    + " Where e.idprueba = ?\n"
                    + " and   e.codigoEvento not in (select c.codigoEvento from cierre_asistencia_sesion c\n"
                    + "                            where  c.idprueba = ? and c.idDivipol = ? )\n"
                    + " and e.tomaasistencia = 1\n"
                    + " and e.codigoEvento in (select distinct d.codigoEvento from detalle_cargo_x_puesto_x_evento d \n"
                    + " where d.idprueba=e.idprueba and d.iddivipol = ? )\n"
                    + " order by e.fecha,e.hora_inicial\n"
                    + " limit 1;";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            evento.setPrueba(pruebadao.getById(res.getInt("idprueba")));
                            evento.setCodigoEvento(res.getInt("codigoEvento"));
                            evento.setNombre(res.getString("nombre"));
                            evento.setFecha(res.getDate("fecha"));
                            evento.setEsCapacitacion(res.getInt("esCapacitacion"));
                            evento.setTipoSesion(res.getString("tipoSesion"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, idPrueba, idPrueba, idDivipol, idDivipol);

        } catch (Exception e) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return evento;
    }

    public List<Evento> listarEventosAplicacion(int idPrueba) {
        final List<Evento> Eventos = new ArrayList<>();

        try {

            StringBuilder sql = new StringBuilder();
            sql.append(" select * from evento where idprueba= ");
            sql.append(idPrueba);
            sql.append(" and (tipoSesion = 'SIMULACRO' || tipoSesion='ELECCION')");
            sql.append(" order by fecha ");

            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Evento evento = new Evento();
                            evento.setCodigoEvento(res.getInt("codigoEvento"));
                            evento.setIdprueba(res.getInt("idprueba"));
                            evento.setNombre(res.getString("nombre"));
                            evento.setEsCapacitacion(res.getInt("esCapacitacion"));
                            evento.setCodigoLogisys(res.getString("codigoLogisys"));
                            evento.setTipoSesion(res.getString("tipoSesion"));
                            evento.setPrueba(pruebadao.getById(res.getInt("idprueba")));
                            evento.setFecha(res.getDate("fecha"));
                            evento.setHora_inicial(res.getString("hora_inicial"));
                            evento.setFecha_final(res.getDate("fecha_final"));
                            evento.setHora_final(res.getString("hora_final"));
                            evento.setActivo(res.getInt("activo"));
                            evento.setTomaAsistencia(res.getInt("tomaAsistencia"));
                            evento.setEsPenitenciaria(res.getInt("esPenitenciaria"));
                            Eventos.add(evento);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());
        } catch (Exception e) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Eventos;
    }

    public List<Evento> listarEventosAplicacion(int idPrueba, String usuario) {
        final List<Evento> Eventos = new ArrayList<>();

        try {

            StringBuilder sql = new StringBuilder();
            sql.append(" select * \n");
            sql.append(" from evento\n");
            sql.append(" where esCapacitacion = 0\n");
            sql.append(" and idprueba=").append(idPrueba);
            sql.append(" and codigoevento in(\n");
            sql.append("	select distinct codigoevento\n");
            sql.append("	from detalle_cargo_x_puesto_x_evento\n");
            sql.append("	where codigodepartamento in(\n");
            sql.append("                     select codigodepartamento\n");
            sql.append("		     from usuario_departamento\n");
            sql.append("                     where usuario = ").append(usuario);
            sql.append(" ) \n").append(") order by fecha ");

            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Evento evento = new Evento();
                            evento.setCodigoEvento(res.getInt("codigoEvento"));
                            evento.setIdprueba(res.getInt("idprueba"));
                            evento.setNombre(res.getString("nombre"));
                            evento.setEsCapacitacion(res.getInt("esCapacitacion"));
                            evento.setCodigoLogisys(res.getString("codigoLogisys"));
                            evento.setTipoSesion(res.getString("tipoSesion"));
                            evento.setPrueba(pruebadao.getById(res.getInt("idprueba")));
                            evento.setFecha(res.getDate("fecha"));
                            evento.setHora_inicial(res.getString("hora_inicial"));
                            evento.setFecha_final(res.getDate("fecha_final"));
                            evento.setHora_final(res.getString("hora_final"));
                            evento.setActivo(res.getInt("activo"));
                            evento.setTomaAsistencia(res.getInt("tomaAsistencia"));
                            evento.setEsPenitenciaria(res.getInt("esPenitenciaria"));
                            Eventos.add(evento);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());
        } catch (Exception e) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Eventos;
    }

    public List<Evento> listarEventosCapacitacion(int idPrueba, String usuario) {
        final List<Evento> Eventos = new ArrayList<>();

        try {

            StringBuilder sql = new StringBuilder();
            sql.append(" select e.* from evento e");
            sql.append(" left join usuario_departamento u on u.codigoDepartamento=e.coddepartamento and u.idPrueba = e.idprueba");
            sql.append(" where e.idprueba= ");
            sql.append(idPrueba);
            sql.append(" and e.esCapacitacion=1 and u.usuario=").append(usuario);
            sql.append(" order by e.fecha");

            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Evento evento = new Evento();
                            evento.setCodigoEvento(res.getInt("codigoEvento"));
                            evento.setIdprueba(res.getInt("idprueba"));
                            evento.setNombre(res.getString("nombre"));
                            evento.setEsCapacitacion(res.getInt("esCapacitacion"));
                            evento.setCodigoLogisys(res.getString("codigoLogisys"));
                            evento.setTipoSesion(res.getString("tipoSesion"));
                            evento.setPrueba(pruebadao.getById(res.getInt("idprueba")));
                            evento.setFecha(res.getDate("fecha"));
                            evento.setHora_inicial(res.getString("hora_inicial"));
                            evento.setFecha_final(res.getDate("fecha_final"));
                            evento.setHora_final(res.getString("hora_final"));
                            evento.setActivo(res.getInt("activo"));
                            evento.setTomaAsistencia(res.getInt("tomaAsistencia"));
                            evento.setEsPenitenciaria(res.getInt("esPenitenciaria"));
                            evento.setCargo(new CargoDao().GetById(res.getString("codcargo")));
                            Eventos.add(evento);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());
        } catch (Exception e) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Eventos;
    }

    private String validaNull(String texto) {
        if (texto == null) {
            return "";
        } else {
            return texto;
        }
    }

    public List<Evento> listarEventoPorSitio(int idDivipol) {
        final List<Evento> Eventos = new ArrayList<>();
        try {

            StringBuilder sql = new StringBuilder();
            sql.append(" select distinct e.* from detalle_cargo_x_puesto_x_evento d ");
            sql.append(" inner join evento e on e.codigoEvento=d.codigoEvento\n");
            sql.append(" where d.idDivipol=").append(idDivipol);
            sql.append(" order by e.fecha ");

            conex.consultar(sql.toString(),
                    new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {

                            Evento evento = new Evento();
                            evento.setPrueba(pruebadao.getById(res.getInt("idprueba")));
                            evento.setCodigoEvento(res.getInt("codigoEvento"));
                            evento.setNombre(res.getString("nombre"));
                            evento.setFecha(res.getDate("fecha"));
                            evento.setEsCapacitacion(res.getInt("esCapacitacion"));
                            evento.setCargo(new CargoDao().GetById(res.getString("codcargo")));
                            evento.setHora_inicial(res.getString("hora_inicial"));
                            Eventos.add(evento);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (Exception e) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Eventos;
    }

    public List<Evento> listarEventoNoCapacitacionPorSitio(int idDivipol) {
        final List<Evento> Eventos = new ArrayList<>();
        try {

            StringBuilder sql = new StringBuilder();
            sql.append("select distinct e.* from detalle_cargo_x_puesto_x_evento d ");
            sql.append(" inner join evento e on e.codigoEvento=d.codigoEvento\n");
            sql.append(" where e.esCapacitacion=0 and d.idDivipol=").append(idDivipol);
            sql.append(" order by e.fecha ");

            conex.consultar(sql.toString(),
                    new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {

                            Evento evento = new Evento();
                            evento.setPrueba(pruebadao.getById(res.getInt("idprueba")));
                            evento.setCodigoEvento(res.getInt("codigoEvento"));
                            evento.setNombre(res.getString("nombre"));
                            evento.setFecha(res.getDate("fecha"));
                            evento.setEsCapacitacion(res.getInt("esCapacitacion"));
                            evento.setCargo(new CargoDao().GetById(res.getString("codcargo")));
                            evento.setHora_inicial(res.getString("hora_inicial"));
                            Eventos.add(evento);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (Exception e) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Eventos;
    }

    public List<Evento> listarEventosPorDepartamento(int idPrueba, String codigoDepartamento) {
        final List<Evento> Eventos = new ArrayList<>();
        StringBuilder sql = new StringBuilder();

        sql.append(" select distinct e.codigoEvento, e.idprueba, e.nombre, e.fecha, e.codcargo, e.esCapacitacion, e.hora_inicial ");
        sql.append(" from detalle_cargo_x_puesto_x_evento d ");
        sql.append(" inner join evento e on e.codigoEvento=d.codigoEvento ");
        sql.append(" where d.codigoDepartamento='").append(codigoDepartamento);
        sql.append("' and e.idprueba=").append(idPrueba);
        sql.append(" order by e.fecha ");

        try {
            conex.consultar(sql.toString(),
                    new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Evento evento = new Evento();
                            evento.setPrueba(pruebadao.getById(res.getInt("idprueba")));
                            evento.setCodigoEvento(res.getInt("codigoEvento"));
                            evento.setNombre(res.getString("nombre"));
                            evento.setFecha(res.getDate("fecha"));
                            evento.setEsCapacitacion(res.getInt("esCapacitacion"));
                            evento.setCargo(new CargoDao().GetById(res.getString("codcargo")));
                            evento.setHora_inicial(res.getString("hora_inicial"));
                            Eventos.add(evento);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (Exception e) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Eventos;
    }

    /**
     * Author: John Steak Herrera Moreno 
     * Date: 17/05/2022
     * 
     * @param idPrueba
     * @return 
     */
    public List<Evento> findAllEventosByIdPruebaNoCapacitacion(int idPrueba) {
        final List<Evento> eventos = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM evento e");
            sql.append(" WHERE e.idprueba = ").append(idPrueba);
            sql.append(" AND e.esCapacitacion = 0");
            sql.append(" ORDER BY e.fecha, e.hora_inicial, e.nombre");
            conex.consultar(sql.toString(),
                    new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Evento evento = new Evento();
                            evento.setPrueba(pruebadao.getById(res.getInt("idprueba")));
                            evento.setCodigoEvento(res.getInt("codigoEvento"));
                            evento.setNombre(res.getString("nombre"));
                            evento.setEsCapacitacion(res.getInt("esCapacitacion"));
                            evento.setCargo(new CargoDao().GetById(res.getString("codcargo")));
                            eventos.add(evento);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (Exception e) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return eventos;
    }

}
