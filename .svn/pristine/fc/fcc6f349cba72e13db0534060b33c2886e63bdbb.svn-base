/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.common.util.SqlUtil;
import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Cargo;
import co.com.grupoasd.nomina.modelo.Departamento;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.Estado;
import co.com.grupoasd.nomina.modelo.Municipio;
import co.com.grupoasd.nomina.modelo.Nombramiento;
import co.com.grupoasd.nomina.modelo.Sitio;
import co.com.grupoasd.nomina.modelo.ZonaIcfes;
import co.com.grupoasd.nomina.modelo.wrapper.NombramientoCargo;
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
public class NombramientoDao {

    private Operaciones conex = new Operaciones();

    public NombramientoDao() {

    }

    public List<Nombramiento> listar(final int idEvento, final String idDepartamento, final String idMunicipio, final String idCargo, final String idZona, final String idPuesto) {

        final List<Nombramiento> nombramientos = new ArrayList<>();

        String sql;

        sql = " select ubicacion, nrodoc, asistio, estado, usuario, fecha, id, cantidadasistio, cantidadnoasistio, consecutivo, sepuederepetir "
                + " from nombramiento "
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
                        Nombramiento nombramiento = new Nombramiento();
                        Municipio municipio = new MunicipioDao().GetById(idDepartamento, idMunicipio);

                        nombramiento.setMunicipio(municipio);
                        nombramiento.setZona(idZona);
                        nombramiento.setPuesto(idPuesto);
                        nombramiento.setUbicacion(res.getString("ubicacion"));
                        nombramiento.setCargo(new CargoDao().GetById(idCargo));
                        nombramiento.setEvento(new EventoDao().GetById(idEvento));
                        nombramiento.setEmpleado(new EmpleadoDao().GetByNumeroDocumento(res.getLong("nrodoc"), idDepartamento));
                        nombramiento.setEstado(new EstadoDao().GetById(res.getString("estado")));
                        nombramiento.setUsuario(res.getString("usuario"));
                        nombramiento.setFecha(res.getDate("fecha"));
                        nombramiento.setId(res.getInt("id"));
                        nombramiento.setSepuederepetir(res.getInt("sepuederepetir"));

                        nombramientos.add(nombramiento);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(NombramientoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, sql, idDepartamento,
                idMunicipio,
                idZona,
                idPuesto,
                idCargo,
                idEvento);

        return nombramientos;
    }

    public List<Nombramiento> listar(final int idEvento, final String codigSitio) {

        final List<Nombramiento> nombramientos = new ArrayList<>();
        try {
            String sql;

            sql = " select ubicacion, nrodoc, asistio, estado, usuario, fecha, id, codigodepartamento, codigomunicipio ,codigocargo, consecutivo, sepuederepetir, salon"
                    + " from nombramiento "
                    + " where codigoPuesto = ? "
                    + " and codigoEvento = ? ";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Nombramiento nombramiento = new Nombramiento();
                            //Municipio municipio = new Municipio();

                            Municipio municipio = new MunicipioDao().GetById(res.getString("codigodepartamento"), res.getString("codigomunicipio"));

                            nombramiento.setMunicipio(municipio);
                            nombramiento.setZona("0000");
                            nombramiento.setPuesto(codigSitio);
                            nombramiento.setUbicacion(res.getString("ubicacion"));
                            nombramiento.setCargo(new CargoDao().GetById(res.getString("codigocargo")));
                            nombramiento.setEvento(new EventoDao().GetById(idEvento));
                            nombramiento.setEmpleado(new EmpleadoDao().GetByNumeroDocumento(res.getLong("nrodoc"), res.getString("codigodepartamento")));
                            nombramiento.setEstado(new EstadoDao().GetById(res.getString("estado")));
                            nombramiento.setUsuario(res.getString("usuario"));
                            nombramiento.setFecha(res.getDate("fecha"));
                            nombramiento.setId(res.getInt("id"));
                            nombramiento.setSepuederepetir(res.getInt("sepuederepetir"));
                            nombramiento.setSalon(res.getString("salon"));

                            nombramientos.add(nombramiento);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(NombramientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, codigSitio, idEvento);

        } catch (Exception e) {
            Logger.getLogger(NombramientoDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return nombramientos;
    }

    public List<Nombramiento> listarNombramiento(final int idPrueba, final int idDivipol) {

        final List<Nombramiento> nombramientos = new ArrayList<>();
        try {
            String sql;

            sql = " select ubicacion, nrodoc, asistio, estado, usuario, fecha, id, codigodepartamento, codigomunicipio ,codigocargo, consecutivo, sepuederepetir, salon, codigopuesto"
                    + " from nombramiento "
                    + " where iddivipol = ? "
                    + " and idprueba = ? ";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Nombramiento nombramiento = new Nombramiento();
                            //Municipio municipio = new Municipio();

                            Municipio municipio = new MunicipioDao().GetById(res.getString("codigodepartamento"), res.getString("codigomunicipio"));

                            nombramiento.setMunicipio(municipio);
                            nombramiento.setZona("0000");
                            nombramiento.setPuesto(res.getString("codigopuesto"));
                            nombramiento.setUbicacion(res.getString("ubicacion"));
                            nombramiento.setCargo(new CargoDao().GetById(res.getString("codigocargo")));
                            nombramiento.setEvento(new EventoDao().GetById(0));
                            nombramiento.setEmpleado(new EmpleadoDao().GetByNumeroDocumento(res.getLong("nrodoc"), res.getString("codigodepartamento")));
                            nombramiento.setEstado(new EstadoDao().GetById(res.getString("estado")));
                            nombramiento.setUsuario(res.getString("usuario"));
                            nombramiento.setFecha(res.getDate("fecha"));
                            nombramiento.setId(res.getInt("id"));
                            nombramiento.setSepuederepetir(res.getInt("sepuederepetir"));
                            nombramiento.setSalon(res.getString("salon"));

                            nombramientos.add(nombramiento);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(NombramientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, idDivipol, idPrueba);

        } catch (Exception e) {
            Logger.getLogger(NombramientoDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return nombramientos;
    }

    /**
     * Lista los nombramientos existentes y el salón correspondiente para una
     * prueba y sit
     *
     * @param idPrueba
     * @param codigSitio
     * @return
     */
    public List<Nombramiento> listarNombramientoSitioCargo(final int idPrueba, final String codigSitio) {

        final List<Nombramiento> nombramientos = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder();

            sql.append("  select distinct n.idDivipol, n.idPrueba, n.nrodoc, '' as usuario, 0 as id, 0 as estado, ");
            sql.append("  n.codigodepartamento, n.codigomunicipio, n.codigocargo,");
            sql.append(" ( select a.salon from asignacion_salon a inner join empleado e on e.idEmpleado=a.idempleado ");
            sql.append("  where a.iddivipol=n.idDivipol and a.idprueba=n.idPrueba ");
            sql.append("  and e.nrodoc=n.nrodoc limit 1) as salon ");
            sql.append("  from detalle_cargo_x_puesto_x_evento n ");
            sql.append("  inner join cargos c on c.codigoCargo=n.codigocargo ");
            sql.append("  where c.nivel_cargo=3 and c.esSuplente=0 ");
            sql.append("  and n.codigoPuesto = ? ");
            sql.append(" and n.idPrueba = ?  and n.nrodoc is not null");

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Nombramiento nombramiento = new Nombramiento();
                            //Municipio municipio = new Municipio();

                            Municipio municipio = new MunicipioDao().GetById(res.getString("codigodepartamento"), res.getString("codigomunicipio"));
                            nombramiento.setMunicipio(municipio);
                            nombramiento.setZona("0000");
                            nombramiento.setPuesto(codigSitio);
                            nombramiento.setCargo(new CargoDao().GetById(res.getString("codigocargo")));
                            int em = new EmpleadoDao().GetIdByNumeroDocumento(res.getLong("nrodoc"));
                            nombramiento.setEmpleado(new EmpleadoDao().GetById(em));
                            nombramiento.setUsuario(res.getString("usuario"));
                            nombramiento.setId(res.getInt("id"));
                            nombramiento.setSalon(res.getString("salon"));
                            nombramiento.setIdPrueba(res.getInt("idPrueba"));
                            nombramiento.setIddivipol(res.getInt("idDivipol"));
                            nombramiento.setEstado(new EstadoDao().GetById(res.getString("estado")));

                            nombramientos.add(nombramiento);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(NombramientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString(), codigSitio, idPrueba);

        } catch (Exception e) {
            Logger.getLogger(NombramientoDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return nombramientos;
    }

    /* asigna a una persona */
    public Boolean actualizar(Nombramiento nombramiento) {
        Boolean resultado = false;

        try {
            String sql;

            sql = " update nombramiento set nrodoc = ?, estado = ?, usuario = ?, fecha = current_date "
                    + " where id = ? ";

            resultado = conex.ejecutar(sql, nombramiento.getEmpleado().getNrodoc(),
                    nombramiento.getEstado().getCodigoEstado(),
                    nombramiento.getUsuario(),
                    nombramiento.getId());

        } catch (Exception e) {
            Logger.getLogger(NombramientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return resultado;
    }

    public Nombramiento getNombramiento(long idPrueba, long nroDoc) {
        
        final Nombramiento nombramiento = new Nombramiento();
        StringBuilder sql=new StringBuilder();

        sql.append(" select distinct n.codigoDepartamento, n.codigoMunicipio, n.codigoZona, n.codigoPuesto,");
        sql.append(" n.ubicacion, n.codigoEvento,n.codigoCargo,n.nrodoc,n.asistio,n.estado,n.usuario,n.fecha,n.id,d.espolivalente,c.polivalente ");
        sql.append(" from nombramiento n,divipol d,cargos c ");
        sql.append(" where n.idPrueba =").append(idPrueba);
        sql.append(" and n.nrodoc =").append(nroDoc);
        sql.append(" and d.idDivipol=n.idDivipol and c.codigoCargo=n.codigoCargo");

        conex.consultar(new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet res) {

                try {
                    while (res.next()) {
                        Municipio municipio = new MunicipioDao().GetById(res.getString("codigoDepartamento"), res.getString("codigoMunicipio"));
                        nombramiento.setMunicipio(municipio);
                        nombramiento.setZona(res.getString("codigoZona"));
                        nombramiento.setPuesto(res.getString("codigoPuesto"));
                        nombramiento.setUbicacion(res.getString("ubicacion"));
                        nombramiento.setCargo(new CargoDao().GetById(res.getString("codigoCargo")));
                        nombramiento.setEvento(new EventoDao().GetById(res.getInt("codigoEvento")));
                        nombramiento.setEmpleado(new EmpleadoDao().GetByNumeroDocumento(res.getLong("nrodoc"), municipio.getDepartamento().getCodigo()));
                        nombramiento.setEstado(new EstadoDao().GetById(res.getString("estado")));
                        nombramiento.setUsuario(res.getString("usuario"));
                        nombramiento.setFecha(res.getDate("fecha"));
                        nombramiento.setAsistio(res.getBoolean("asistio"));
                        nombramiento.setId(res.getInt("id"));
                        nombramiento.setEspolivalente(res.getInt("espolivalente"));
                        nombramiento.setCargoespolivalente(res.getInt("polivalente"));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(NombramientoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, sql.toString());

        return nombramiento;
    }


    public Nombramiento getIdNombramiento(long idPrueba, String codigoPuesto, String codigoCargo, int consecutivo) {
        
        final Nombramiento nombramiento = new Nombramiento();
        StringBuilder sql=new StringBuilder();

        sql.append(" select n.id ");
        sql.append(" from nombramiento n ");
        sql.append(" where n.idPrueba =").append(idPrueba);
        sql.append(" and n.codigopuesto =").append(codigoPuesto);
        sql.append(" and n.codigocargo =").append(codigoCargo);
        sql.append(" and n.consecutivo =").append(consecutivo);

        conex.consultar(new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet res) {

                try {
                    while (res.next()) {
                        nombramiento.setId(res.getInt("id"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(NombramientoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, sql.toString());

        return nombramiento;
    }

    
    
    public Nombramiento getById(int id) {

        final Nombramiento nombramiento = new Nombramiento();
        try {
            String sql;

            sql = " select codigoDepartamento, codigoMunicipio, codigoZona, codigoPuesto,"
                    + " ubicacion, codigoEvento,  codigoCargo, nrodoc, asistio,"
                    + " estado, usuario, fecha, id, sepuederepetir, estado "
                    + " from nombramiento "
                    + " where id = ? ";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Municipio municipio = new MunicipioDao().GetById(res.getString("codigoDepartamento"), res.getString("codigoMunicipio"));
                            nombramiento.setMunicipio(municipio);
                            nombramiento.setZona(res.getString("codigoZona"));
                            nombramiento.setPuesto(res.getString("codigoPuesto"));
                            nombramiento.setUbicacion(res.getString("ubicacion"));
                            nombramiento.setCargo(new CargoDao().GetById(res.getString("codigoCargo")));
                            nombramiento.setEvento(new EventoDao().GetById(res.getInt("codigoEvento")));
                            nombramiento.setEmpleado(new EmpleadoDao().GetByNumeroDocumento(res.getLong("nrodoc"), municipio.getDepartamento().getCodigo()));
                            nombramiento.setEstado(new EstadoDao().GetById(res.getString("estado")));
                            nombramiento.setUsuario(res.getString("usuario"));
                            nombramiento.setFecha(res.getDate("fecha"));
                            nombramiento.setId(res.getInt("id"));
                            nombramiento.setSepuederepetir(res.getInt("sepuederepetir"));
                            Estado estado = new Estado();
                            estado.setCodigoEstado(res.getInt("estado"));
                            nombramiento.setEstado(estado);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(NombramientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, id);

        } catch (Exception e) {
            Logger.getLogger(PersonaAsignadaDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return nombramiento;
    }

    public int getNombramientoDisponible(int codigoEvento, String codigoCargo, String codigoSitio) {

        final Object[] idNombramiento = new Object[1];
        idNombramiento[0] = 0;

        try {
            String sql;

            sql = " select id "
                    + " from nombramiento "
                    + " where codigoevento = ? "
                    + " and codigocargo = ? "
                    + " and codigopuesto = ? "
                    + " and (nrodoc is null or nrodoc = 0) "
                    + " order by id, ubicacion limit 1 ";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            idNombramiento[0] = res.getInt("id");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(NombramientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, codigoEvento, codigoCargo, codigoSitio);

        } catch (Exception e) {
            Logger.getLogger(NombramientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return (int) idNombramiento[0];
    }

    public List<Nombramiento> listarCapacitacion(final int idEvento, final String codigSitio) {

        final List<Nombramiento> nombramientos = new ArrayList<>();
        try {
            String sql;

            sql = " select ubicacion, nrodoc, asistio, estado, usuario, fecha, id, \n"
                    + "codigodepartamento, codigomunicipio ,codigocargo, consecutivo, sepuederepetir, codigopuesto\n"
                    + "from nombramiento \n"
                    + "where codigoPuesto in(\n"
                    + "	select ubicacion\n"
                    + "	from detalle_cargo_x_puesto_x_evento\n"
                    + "	where codigoPuesto = '" + codigSitio + "' \n"
                    + "		and codigoEvento in(\n"
                    + "		select codigoevento\n"
                    + "		from evento\n"
                    + "		where idprueba in(\n"
                    + "		select idprueba\n"
                    + "		from evento\n"
                    + "		where codigoevento = " + idEvento + " \n"
                    + "		)\n"
                    + "		and tiposesion = 'CAPACITACION')	\n"
                    + ")\n"
                    + "and codigoevento = " + idEvento + " \n"
                    + "and codigocargo in(\n"
                    + "	select distinct codigocargo\n"
                    + "	from detalle_cargo_x_puesto_x_evento\n"
                    + "	where codigoPuesto = '" + codigSitio + "'\n"
                    + "		and codigoEvento in(\n"
                    + "		select codigoevento\n"
                    + "		from evento\n"
                    + "		where idprueba in(\n"
                    + "		select idprueba\n"
                    + "		from evento\n"
                    + "		where codigoevento = " + idEvento + " \n"
                    + "		)\n"
                    + "		and tiposesion = 'CAPACITACION')\n"
                    + ") ";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Nombramiento nombramiento = new Nombramiento();
                            //Municipio municipio = new Municipio();

                            Municipio municipio = new MunicipioDao().GetById(res.getString("codigodepartamento"), res.getString("codigomunicipio"));

                            nombramiento.setMunicipio(municipio);
                            nombramiento.setZona("0000");
                            nombramiento.setPuesto(res.getString("codigopuesto"));
                            nombramiento.setUbicacion(res.getString("ubicacion"));
                            nombramiento.setCargo(new CargoDao().GetById(res.getString("codigocargo")));
                            nombramiento.setEvento(new EventoDao().GetById(idEvento));
                            nombramiento.setEmpleado(new EmpleadoDao().GetByNumeroDocumento(res.getLong("nrodoc"), res.getString("codigodepartamento")));
                            nombramiento.setEstado(new EstadoDao().GetById(res.getString("estado")));
                            nombramiento.setUsuario(res.getString("usuario"));
                            nombramiento.setFecha(res.getDate("fecha"));
                            nombramiento.setId(res.getInt("id"));
                            nombramiento.setSepuederepetir(res.getInt("sepuederepetir"));

                            nombramientos.add(nombramiento);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(NombramientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql);

        } catch (Exception e) {
            Logger.getLogger(NombramientoDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return nombramientos;
    }

    /**
     * Metodo DAO para obtener los nombramientos por cargo segun los parametros
     * ingresados, Obligatorios: idPrueba,codigoDepto,codigoCargo
     *
     * @param idPrueba
     * @param codigoDepartamento
     * @param codigoCargo
     * @param codigoMunicipio
     * @param idZona
     * @param cantidadSalones
     * @return
     */
    public List<NombramientoCargo> getNombramientoCargoFiltros(int idPrueba, int codigoDepartamento,
            int codigoCargo, int codigoMunicipio, int idZona, int cantidadSalones) {
        final List<NombramientoCargo> nombramientos = new ArrayList<>();
        try {
            StringBuilder consulta = new StringBuilder("select ");
            consulta.append("nm.id as idNombramiento, ");
            consulta.append("dp.codigoPuesto as codigoPuesto, ");
            consulta.append("dp.nombrePuesto as nombrePuesto, ");
            consulta.append("dp.idDivipol as idDivipol, ");
            consulta.append("dp.cantidadSalones as cantidadSalones, ");
            consulta.append("zi.nombre as zonaNombre, ");
            consulta.append("dp.latitud as sitioLongitud, ");
            consulta.append("dp.longitud as sitioLatitud, ");
            consulta.append("em.idEmpleado as idEmpleado, ");
            consulta.append("em.nrodoc as empleadoDocumento, ");
            consulta.append("em.nombre1 as nombre1Empleado, ");
            consulta.append("em.nombre2 as nombre2Empleado, ");
            consulta.append("em.apellido1 as apellido1Empleado, ");
            consulta.append("em.apellido2 as apellido2Empleado, ");
            consulta.append("cg.codigoCargo as codigoCargo, cg.nivel_cargo as nivelCargo, ");
            consulta.append("mun.codigoMunicipio as codigoMunicipio, ");
            consulta.append("mun.nombre as nombreMunicipio, ");
            consulta.append("dpto.codigo as codigoDepartamento, ");
            consulta.append("dpto.nombre as nombreDepartamento ");
            consulta.append("from nombramiento nm ");
            consulta.append("inner join divipol dp on nm.idDivipol = dp.idDivipol ");
            consulta.append("inner join departamento dpto on dp.codigoDepartamento = dpto.codigo ");
            consulta.append("inner join municipio mun on dp.codigoMunicipio = mun.codigoMunicipio ");
            consulta.append("left join empleado em on nm.nrodoc = em.nrodoc ");
            consulta.append("left join zona_icfes zi on dp.idZona = zi.idzona ");
            consulta.append("inner join cargos cg on nm.codigoCargo = cg.codigoCargo ");
            consulta.append("where nm.idPrueba = ").append(idPrueba);
            SqlUtil.appendParameterWithAnd(consulta, "dp.codigoDepartamento", codigoDepartamento);
            SqlUtil.appendParameterWithAnd(consulta, "cg.codigoCargo", codigoCargo);
            SqlUtil.appendParameterWithAnd(consulta, "dp.codigoMunicipio", codigoMunicipio);
            SqlUtil.appendParameterWithAnd(consulta, "zi.idZona", idZona);
            SqlUtil.appendParameterWithAnd(consulta, "dp.cantidadSalones", cantidadSalones);

            conex.consultar(consulta.toString(), new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet resultado) {
                    try {
                        while (resultado.next()) {
                            NombramientoCargo nombramiento = new NombramientoCargo();
                            nombramiento.setId(resultado.getInt("idNombramiento"));
                            ZonaIcfes zona = new ZonaIcfes();
                            zona.setNombre(resultado.getString("zonaNombre"));
                            nombramiento.setZonaIcfes(zona);
                            Sitio sitio = new Sitio();
                            sitio.setId(resultado.getInt("idDivipol"));
                            sitio.setCodigoSitio(resultado.getString("codigoPuesto"));
                            sitio.setNombreSitio(resultado.getString("nombrePuesto"));
                            sitio.setCantidadSalones(resultado.getInt("cantidadSalones"));
                            sitio.setLatitud(resultado.getFloat("sitioLongitud"));
                            sitio.setLongitud(resultado.getFloat("sitioLatitud"));
                            Departamento dpto = new Departamento();
                            dpto.setCodigo(resultado.getString("codigoDepartamento"));
                            dpto.setNombre(resultado.getString("nombreDepartamento"));
                            Municipio muni = new Municipio();
                            muni.setCodigoMunicipio(resultado.getString("codigoMunicipio"));
                            muni.setNombre(resultado.getString("nombreMunicipio"));
                            sitio.setDepartamento(dpto);
                            sitio.setMunicipio(muni);
                            nombramiento.setDivipol(sitio);
                            Empleado empleado = new Empleado();
                            empleado.setIdEmpleado(resultado.getInt("idEmpleado"));
                            empleado.setNrodoc(resultado.getLong("empleadoDocumento"));
                            empleado.setNombre1(resultado.getString("nombre1Empleado"));
                            empleado.setNombre2(resultado.getString("nombre2Empleado"));
                            empleado.setApellido1(resultado.getString("apellido1Empleado"));
                            empleado.setApellido2(resultado.getString("apellido2Empleado"));
                            Cargo cargo = new Cargo(resultado.getString("codigoCargo"));
                            cargo.setNivel_cargo(resultado.getInt("nivelCargo"));
                            empleado.setCargoobj(cargo);
                            nombramiento.setEmpleado(empleado);
                            Estado estado = new Estado();
                            estado.setCodigoEstado(resultado.getLong("empleadoDocumento") > 0 ? 1 : 0);
                            nombramiento.setNrodocEmpleado(String.valueOf(resultado.getLong("empleadoDocumento")));
                            nombramiento.setEstado(estado);
                            //Se adiciona nombramiento a la lista
                            nombramientos.add(nombramiento);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(NombramientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            Logger.getLogger(NombramientoDao.class.getName()).log(Level.INFO, consulta.toString());
        } catch (Exception e) {
            Logger.getLogger(NombramientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return nombramientos;
    }

    /**
     * Metodo Dao encargado de consultar en BD el nombramiento por id
     *
     * @param id
     * @return
     */
    public NombramientoCargo getNombramientoCargoById(int id) {
        StringBuilder consulta = new StringBuilder("select ");
        consulta.append("nm.id as idNombramiento, ");
        consulta.append("dp.codigoPuesto as codigoPuesto, ");
        consulta.append("dp.nombrePuesto as nombrePuesto, ");
        consulta.append("dp.idDivipol as idDivipol, ");
        consulta.append("dp.cantidadSalones as cantidadSalones, ");
        consulta.append("zi.nombre as zonaNombre, ");
        consulta.append("dp.latitud as sitioLongitud, ");
        consulta.append("dp.longitud as sitioLatitud, ");
        consulta.append("em.idEmpleado as idEmpleado, ");
        consulta.append("em.nrodoc as empleadoDocumento, ");
        consulta.append("em.nombre1 as nombre1Empleado, ");
        consulta.append("em.nombre2 as nombre2Empleado, ");
        consulta.append("em.apellido1 as apellido1Empleado, ");
        consulta.append("em.apellido2 as apellido2Empleado, ");
        consulta.append("mun.codigoMunicipio as codigoMunicipio, ");
        consulta.append("mun.nombre as nombreMunicipio, ");
        consulta.append("dpto.codigo as codigoDepartamento, ");
        consulta.append("dpto.nombre as nombreDepartamento ");
        consulta.append("from nombramiento nm ");
        consulta.append("inner join divipol dp on nm.idDivipol = dp.idDivipol ");
        consulta.append("inner join departamento dpto on dp.codigoDepartamento = dpto.codigo ");
        consulta.append("inner join municipio mun on dp.codigoMunicipio = mun.codigoMunicipio ");
        consulta.append("left join empleado em on nm.nrodoc = em.nrodoc ");
        consulta.append("left join zona_icfes zi on dp.idZona = zi.idzona ");
        consulta.append("inner join cargos cg on nm.codigoCargo = cg.codigoCargo ");
        consulta.append("where nm.id = ").append(id);
        final NombramientoCargo nombramiento = new NombramientoCargo();
        try {
            conex.consultar(consulta.toString(), new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet resultado) {
                    try {
                        while (resultado.next()) {
                            nombramiento.setId(resultado.getInt("idNombramiento"));
                            ZonaIcfes zona = new ZonaIcfes();
                            zona.setNombre(resultado.getString("zonaNombre"));
                            nombramiento.setZonaIcfes(zona);
                            Sitio sitio = new Sitio();
                            sitio.setId(resultado.getInt("idDivipol"));
                            sitio.setCodigoSitio(resultado.getString("codigoPuesto"));
                            sitio.setNombreSitio(resultado.getString("nombrePuesto"));
                            sitio.setCantidadSalones(resultado.getInt("cantidadSalones"));
                            sitio.setLatitud(resultado.getFloat("sitioLongitud"));
                            sitio.setLongitud(resultado.getFloat("sitioLatitud"));
                            Departamento dpto = new Departamento();
                            dpto.setCodigo(resultado.getString("codigoDepartamento"));
                            dpto.setNombre(resultado.getString("nombreDepartamento"));
                            Municipio muni = new Municipio();
                            muni.setCodigoMunicipio(resultado.getString("codigoMunicipio"));
                            muni.setNombre(resultado.getString("nombreMunicipio"));
                            sitio.setDepartamento(dpto);
                            sitio.setMunicipio(muni);
                            nombramiento.setDivipol(sitio);
                            Empleado empleado = new Empleado();
                            empleado.setIdEmpleado(resultado.getInt("idEmpleado"));
                            empleado.setNrodoc(resultado.getLong("empleadoDocumento"));
                            empleado.setNombre1(resultado.getString("nombre1Empleado"));
                            empleado.setNombre2(resultado.getString("nombre2Empleado"));
                            empleado.setApellido1(resultado.getString("apellido1Empleado"));
                            empleado.setApellido2(resultado.getString("apellido2Empleado"));
                            nombramiento.setEmpleado(empleado);
                            Estado estado = new Estado();
                            estado.setCodigoEstado(resultado.getLong("empleadoDocumento") > 0 ? 1 : 0);
                            nombramiento.setNrodocEmpleado(String.valueOf(resultado.getLong("empleadoDocumento")));
                            nombramiento.setEstado(estado);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(NombramientoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (Exception e) {
            Logger.getLogger(NombramientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return nombramiento;
    }
}
