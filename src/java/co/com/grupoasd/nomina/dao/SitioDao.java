/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Departamento;
import co.com.grupoasd.nomina.modelo.Prueba;
import co.com.grupoasd.nomina.modelo.Sitio;
import co.com.grupoasd.nomina.modelo.seguimiento.ResultSetConverter;
import co.com.grupoasd.nomina.portal.util.Normalizacion;
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
public class SitioDao {

    private Operaciones conex = new Operaciones();

    public SitioDao() {

    }

    /**
     * Permite registrar un Empleado
     *
     * @param sitio
     */
    public boolean insertarSitio(Sitio sitio) {

        //final int idEmpleado = 0;
        boolean inserto;
        Departamento dpto = new DepartamentoDao().GetById(sitio.getDepartamento().getCodigo());

        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO divipol (");
        sql.append(" idPrueba, codigoDepartamento, codigoMunicipio,");
        sql.append(" codigoPuesto, nombreDepartamento,nombreMunicipio,nombrePuesto,");
        sql.append(" direccionPuesto,nombreRector,telefono, idTipoSitio, ");
        sql.append(" email, fax, cantidadSalones, fecha_actualiza, barrio, idZona, instruccionesLlegada,");
        sql.append(" idCategoriaSitio, codigoSede, nombreSede,departamentoSede, municipioSede,");
        sql.append(" direccionSede, rectorSede, nExaminandos, idEstadoSitio,");
        sql.append(" telefonoSede, emailSede, fecha, hora, medioPago, tomaAsistencia, latitud, longitud, confirmado,desconectado,espolivalente)");
        sql.append(" VALUES (");
        sql.append(sitio.getIdPrueba());
        sql.append(",'");
        sql.append(dpto.getCodigo());
        sql.append("','");
        sql.append(sitio.getMunicipio().getCodigoMunicipio());
        sql.append("','");
        sql.append(sitio.getCodigoSitio());
        sql.append("','");
        sql.append(dpto.getNombre());
        sql.append("','");
        sql.append(sitio.getMunicipio().getNombre());
        sql.append("','");
        sql.append(sitio.getNombreSitio() != null ? sitio.getNombreSitio().toUpperCase().trim() : "");
        sql.append("','");
        sql.append(sitio.getDireccionSitio() != null ? sitio.getDireccionSitio().toUpperCase().trim() : "");
        sql.append("','");
        sql.append(sitio.getNombreRector() != null ? sitio.getNombreRector().toUpperCase().trim() : "");
        sql.append("','");
        sql.append(sitio.getTelefonoRector() != null ? sitio.getTelefonoRector() : "");
        sql.append("',");
        sql.append(sitio.getTipoSitio().getIdTipoSitio());
        sql.append(",'");
        sql.append(sitio.getEmailRector() != null ? sitio.getEmailRector() : "");
        sql.append("','");
        sql.append(sitio.getFaxRector() != null ? sitio.getFaxRector() : "");
        sql.append("',");
        sql.append(sitio.getCantidadSalones());
        sql.append(",");
        sql.append("current_timestamp,'");
        sql.append(sitio.getBarrio() != null ? sitio.getBarrio() : "");
        sql.append("',");
        sql.append(sitio.getIdZona());
        sql.append(",'");
        sql.append(sitio.getInstruccionesLlegada() != null ? sitio.getInstruccionesLlegada() : "");
        sql.append("',");
        sql.append(sitio.getCategoriaSitio());
        sql.append(",'");
        sql.append(sitio.getCodigoSede() != null ? sitio.getCodigoSede() : "");
        sql.append("','");
        sql.append(sitio.getNombreSede() != null ? sitio.getNombreSede().toUpperCase().trim() : "");
        sql.append("','");
        sql.append(sitio.getDepartamentoSede().getCodigo() != null ? sitio.getDepartamentoSede().getCodigo() : "");
        sql.append("','");
        sql.append(sitio.getMunicipioSede().getCodigoMunicipio());
        sql.append("','");
        sql.append(sitio.getDireccionSede() != null ? sitio.getDireccionSede().toUpperCase().trim() : "");
        sql.append("','");
        sql.append(sitio.getRectorSede() != null ? sitio.getRectorSede().toUpperCase().trim() : "");
        sql.append("',");
        sql.append(sitio.getnExaminandos());
        sql.append(",");
        sql.append(sitio.getEstadoSitio().getIdEstadoSitio() != 0 ? sitio.getEstadoSitio().getIdEstadoSitio() : 2);
        sql.append(",'");
        sql.append(sitio.getTelefonoSede() != null ? sitio.getTelefonoSede() : "");
        sql.append("','");
        sql.append(sitio.getEmailSede() != null ? sitio.getEmailSede() : "");
        sql.append("','");
        sql.append(sitio.getFechaReunionPrevia());
        sql.append("','");
        sql.append(sitio.getHoraReunionPrevia());
        sql.append("','");
        sql.append(sitio.getMedioPago());
        sql.append("','");
        sql.append(sitio.getTomaAsistencia());
        sql.append("','");
        sql.append(sitio.getLatitud());
        sql.append("','");
        sql.append(sitio.getLongitud());
        sql.append("','");
        sql.append(sitio.getConfirmado());
        sql.append("',");
        sql.append(sitio.getDesconectado());
        sql.append(",");
        sql.append(sitio.getEspolivalente());
        sql.append(")");
        inserto = conex.ejecutar(sql.toString());

        return inserto;
    }

    public boolean actualizarSitio(Sitio sitio) {

        Departamento dpto = new DepartamentoDao().GetById(sitio.getDepartamento().getCodigo());

        boolean inserto;
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE divipol SET ");
        sql.append(" codigoDepartamento=?, codigoMunicipio=?,");
        sql.append(" codigoPuesto=?, nombreDepartamento=?,nombreMunicipio=?,nombrePuesto=?,");
        sql.append(" direccionPuesto=?,nombreRector=?,telefono=?, fecha_actualiza = current_timestamp,");
        sql.append(" email=?, fax=?, cantidadSalones=?, barrio=?, idZona=?, instruccionesLlegada=?,");
        sql.append(" idCategoriaSitio=?, codigoSede=?, nombreSede=?,departamentoSede=?, municipioSede=?,");
        sql.append(" direccionSede=?, rectorSede=?,  idTipoSitio=?, nExaminandos=?,");
        sql.append(" telefonoSede=?, emailSede=?, fecha=?, hora=?, medioPago=?, tomaAsistencia=?, idEstadoSitio=?, latitud=?, longitud=?, confirmado=?, desconectado=?, espolivalente=?");
        sql.append(" where idDivipol=?");

        inserto = conex.ejecutar(sql.toString(),
                sitio.getDepartamento().getCodigo(),
                sitio.getMunicipio().getCodigoMunicipio(),
                sitio.getCodigoSitio(),
                dpto.getNombre(),
                sitio.getMunicipio().getNombre(),
                sitio.getNombreSitio(),
                sitio.getDireccionSitio(),
                sitio.getNombreRector(),
                sitio.getTelefonoRector(),
                sitio.getEmailRector(),
                sitio.getFaxRector(),
                sitio.getCantidadSalones(),
                sitio.getBarrio(),
                sitio.getIdZona(),
                sitio.getInstruccionesLlegada(),
                sitio.getCategoriaSitio(),
                sitio.getCodigoSede(),
                sitio.getNombreSede(),
                sitio.getDepartamentoSede().getCodigo(),
                sitio.getMunicipioSede().getCodigoMunicipio(),
                sitio.getDireccionSede(),
                sitio.getRectorSede(),
                sitio.getTipoSitio().getIdTipoSitio(),
                sitio.getnExaminandos(),
                sitio.getTelefonoRector(),
                sitio.getEmailSede(),
                sitio.getFechaReunionPrevia(),
                sitio.getHoraReunionPrevia(),
                sitio.getMedioPago(),
                sitio.getTomaAsistencia(),
                sitio.getEstadoSitio().getIdEstadoSitio(),
                sitio.getLatitud(),
                sitio.getLongitud(),
                sitio.getConfirmado(),
                sitio.getDesconectado(),
                sitio.getEspolivalente(),
                sitio.getId()
        );

        if (inserto) {
            if (sitio.getPds().size() > 0) {
                this.actualizarPDSSitio(sitio.getPds(), sitio.getId(), sitio.getIdPrueba());
            }
        }

        return inserto;

    }

    private void actualizarPDSSitio(ArrayList<Sitio> pds, int id, int idPrueba) {

        ArrayList<Sitio> pdsExistentesBD = (ArrayList<Sitio>) this.listarPDsAsignadosASitio(id);
        ArrayList<Sitio> d = new ArrayList<Sitio>(pdsExistentesBD);

        int prioridad = 0;
        for (Sitio pdsNuevo : pds) {
            prioridad += 1;
            boolean inserta = true;

            for (Sitio existente : pdsExistentesBD) {
                if (pdsNuevo.getId() == existente.getId()) {
                    inserta = false;
                    this.actualizarPrioridadPDSSitio(id, existente.getId(), prioridad, idPrueba);
                    d.remove(existente);
                }
            }

            if (inserta) {
                insertarPDSSitio(id, pdsNuevo.getId(), prioridad, idPrueba);
            }

        }

        if (d.size() > 0) {
            for (Sitio sitioe : d) {
                this.eliminarPDSSitio(id, sitioe.getId(), idPrueba);
            }
        }
    }

    private void actualizarPrioridadPDSSitio(int sitio, int pds, int prioridad, int prueba) {

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE relacion_pds set prioridad=? where idDivipolSitio=? and idDivipolPds=? and idPrueba=?");
        conex.ejecutar(sql.toString(), prioridad, sitio, pds, prueba);

    }

    public boolean insertarPDSSitio(int sitio, int pds, int prioridad, int prueba) {

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO relacion_pds (idDivipolSitio, idDivipolPds, prioridad, idPrueba) ");
        sql.append(" VALUES(?,?,?,?)");
        boolean inserto = conex.ejecutar(sql.toString(), sitio, pds, prioridad, prueba);
        return inserto;

    }

    private void eliminarPDSSitio(int sitio, int pds, int prueba) {

        StringBuilder sql = new StringBuilder();
        sql.append("DELETE from relacion_pds where idDivipolSitio=? and idDivipolPds=? and idPrueba=? ");
        conex.ejecutar(sql.toString(), sitio, pds, prueba);

    }

    public Sitio GetById(final int idDivipol) {

        final Sitio sitio = new Sitio();

        try {

            String sql = " select * from divipol where iddivipol = ? ; ";
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            sitio.setId(res.getInt("idDivipol"));
                            sitio.setCodigoSitio(res.getString("codigopuesto"));
                            sitio.setNombreSitio(res.getString("nombrepuesto"));
                            sitio.setCodigoDepartamento(res.getString("codigodepartamento"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, idDivipol);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return sitio;
    }

    public Sitio GetById(final String codigoSitio) {

        final Sitio sitio = new Sitio();

        try {

            String sql = " select * from divipol d left join prueba p on d.idPrueba = p.idprueba \n"
                    + " where codigopuesto = ? order by p.fechaaplicacion desc limit 1; ";
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            sitio.setId(res.getInt("idDivipol"));
                            sitio.setCodigoSitio(codigoSitio);
                            sitio.setNombreSitio(res.getString("nombrepuesto"));
                            sitio.setCodigoDepartamento(res.getString("codigodepartamento"));
                            sitio.setDireccionSitio(res.getString("direccionPuesto"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, codigoSitio);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return sitio;
    }

    public Sitio BuscarSitioPorId(final int idDivipol) {

        final Sitio sitio = new Sitio();

        try {

            StringBuilder sql = new StringBuilder();
            sql.append(" select * from divipol where idDivipol=");
            sql.append(idDivipol);
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            sitio.setCodigoSitio(res.getString("codigopuesto"));
                            sitio.setNombreSitio(res.getString("nombrePuesto"));
                            sitio.setId(res.getInt("idDivipol"));
                            sitio.setIdPrueba(res.getInt("idPrueba"));
                            sitio.setDireccionSitio(res.getString("direccionPuesto"));
                            sitio.setNombreRector(res.getString("nombreRector"));
                            sitio.setTelefonoRector(res.getString("telefono"));
                            sitio.setFaxRector(res.getString("fax"));
                            sitio.setEmailRector(res.getString("email"));
                            sitio.setDepartamento(new DepartamentoDao().GetById(res.getString("codigoDepartamento")));
                            sitio.setMunicipio(new MunicipioDao().GetById(res.getString("codigoDepartamento"), res.getString("codigoMunicipio")));
                            sitio.setFechaReunionPrevia(res.getString("fecha"));
                            sitio.setHoraReunionPrevia(res.getString("hora"));
                            sitio.setCantidadSalones(res.getInt("cantidadSalones"));
                            sitio.setBarrio(res.getString("barrio"));
                            sitio.setIdZona(res.getInt("idZona"));
                            sitio.setInstruccionesLlegada(res.getString("instruccionesLlegada"));
                            sitio.setCategoriaSitio(res.getInt("idCategoriaSitio"));
                            sitio.setCodigoSede(res.getString("codigoSede"));
                            sitio.setNombreSede(res.getString("nombreSede"));
                            sitio.setDepartamentoSede(new DepartamentoDao().GetById(res.getString("departamentoSede")));
                            sitio.setMunicipioSede(new MunicipioDao().GetById(res.getString("departamentoSede"), res.getString("municipioSede")));
                            sitio.setDireccionSede(res.getString("direccionSede"));
                            sitio.setRectorSede(res.getString("rectorSede"));
                            sitio.setTelefonoSede(res.getString("emailSede"));
                            sitio.setEmailSede(res.getString("emailSede"));
                            sitio.setTomaAsistencia(res.getString("tomaAsistencia"));
                            sitio.setMedioPago(res.getString("medioPago"));
                            sitio.setnExaminandos(res.getInt("nExaminandos"));
                            sitio.setTipoSitio(new TipoSitioDao().GetById(res.getInt("idTipoSitio")));
                            sitio.setLatitud(res.getFloat("latitud"));
                            sitio.setLongitud(res.getFloat("longitud"));
                            sitio.setConfirmado(res.getString("confirmado"));
                            sitio.setEstadoSitio(new EstadoSitioDao().GetEstadoSitioById(res.getInt("idEstadoSitio")));
                            sitio.setEspolivalente(res.getInt("espolivalente"));
                            sitio.setDesconectado(res.getInt("desconectado"));

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return sitio;
    }

    public Sitio GetSitio(String codigoSitio, Prueba prueba) throws Exception {
        StringBuilder sb = new StringBuilder();
        final Object[] respuesta = new Object[1];
        Sitio sitio = new Sitio();
        respuesta[0] = sitio;

        sb.append("select * from divipol where codigoPuesto = '");
        sb.append(codigoSitio);
        sb.append("'");
        sb.append(" and idPrueba = ");
        sb.append(prueba.getIdprueba());

        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet res) {
                try {
                    if (res.next()) {
                        Sitio sitio = new Sitio();

                        sitio.setId(res.getInt("iddivipol"));
                        sitio.setCodigoSitio(res.getString("codigopuesto"));
                        sitio.setCodigoMunicipio(res.getString("codigomunicipio"));
                        sitio.setCodigoDepartamento(res.getString("codigodepartamento"));
                        sitio.setNombreSitio(res.getString("nombrepuesto"));
                        sitio.setLatitud(res.getFloat("latitud"));
                        sitio.setLongitud(res.getFloat("longitud"));
                        sitio.setDireccionSitio(res.getString("direccionPuesto"));
                        sitio.setCantidadSalones(res.getInt("cantidadSalones"));

                        respuesta[0] = sitio;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DivipolDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return (Sitio) respuesta[0];
    }

    public List<Sitio> listarSitiosPrueba(int idPrueba) {
        final List<Sitio> sitios = new ArrayList<>();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" select idDivipol, codigopuesto, concat(codigopuesto,'-',nombrepuesto) as puesto \n");
            sql.append(" from divipol ");
            sql.append(" where idPrueba =");
            sql.append(idPrueba);

            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Sitio sitio = new Sitio();
                            sitio.setCodigoSitio(res.getString("codigopuesto"));
                            sitio.setNombreSitio(res.getString("puesto"));
                            sitio.setId(res.getInt("idDivipol"));
                            sitios.add(sitio);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return sitios;
    }

    public List<Sitio> consultarSitios(int idPrueba, String codigoSitio, int tipo, String departamento, String municipio) {
        final List<Sitio> sitios = new ArrayList<>();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" select * from divipol where idPrueba=");
            sql.append(idPrueba);

            if (codigoSitio.trim().length() != 0) {
                sql.append(" and codigoPuesto='");
                sql.append(codigoSitio);
                sql.append("'");
            }
            if (tipo > 0) {
                sql.append(" and idTipoSitio=");
                sql.append(tipo);
            }
            if (!departamento.equals("0")) {
                sql.append(" and codigodepartamento='");
                sql.append(departamento);
                sql.append("'");
            }

            if (!municipio.equals("0")) {
                sql.append(" and  codigomunicipio='");
                sql.append(municipio);
                sql.append("'");
            }

            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Sitio sitio = new Sitio();
                            sitio.setCodigoSitio(res.getString("codigopuesto"));
                            sitio.setNombreSitio(res.getString("nombrepuesto"));
                            sitio.setId(res.getInt("idDivipol"));
                            sitio.setDireccionSitio(res.getString("direccionPuesto"));
                            sitio.setNombreRector(res.getString("nombreRector"));
                            sitio.setTelefonoRector(res.getString("telefono"));
                            sitio.setFaxRector(res.getString("fax"));
                            sitio.setTipoSitio(new TipoSitioDao().GetById(res.getInt("idTipoSitio")));
                            sitio.setEmailRector(res.getString("email"));
                            sitio.setDepartamento(new DepartamentoDao().GetById(res.getString("codigodepartamento")));
                            sitio.setMunicipio(new MunicipioDao().GetById(res.getString("codigodepartamento"), res.getString("codigomunicipio")));
                            sitios.add(sitio);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return sitios;
    }

    public List<Sitio> listarPDdsSinAsignarASitio(int idDivipol, int idPrueba) {

        final List<Sitio> sitios = new ArrayList<>();

        Sitio sitio = this.BuscarSitioPorId(idDivipol);
        StringBuilder sql = new StringBuilder();
        sql.append("select * from divipol d ");
        sql.append("inner join tipo_sitio ts on d.idTipoSitio=ts.idTipoSitio ");
        sql.append("where ts.nombre='Punto de suplencia PDS' ");
        sql.append(" and d.idPrueba=");
        sql.append(idPrueba);
        sql.append(" and d.codigoMunicipio='");
        sql.append(sitio.getMunicipio().getCodigoMunicipio());
        sql.append("' and d.idDivipol not in ");
        sql.append("(select rp.idDivipolPds from relacion_pds rp where  rp.idDivipolSitio=");
        sql.append(idDivipol).append(")");

        try {
            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Sitio sitio = new Sitio();
                            sitio.setCodigoSitio(res.getString("codigopuesto"));
                            sitio.setNombreSitio(res.getString("nombrepuesto"));
                            sitio.setId(res.getInt("idDivipol"));
                            sitios.add(sitio);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return sitios;

    }

    public List<Sitio> listarPDsAsignadosASitio(int idDivipol) {

        final List<Sitio> sitios = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("select d.codigoPuesto, d.nombrePuesto, d.idDivipol, r.prioridad\n");
        sql.append(" from divipol d\n");
        sql.append(" inner join relacion_pds r on r.idDivipolPds=d.idDivipol \n");
        sql.append(" inner join tipo_sitio ts on d.idTipoSitio=ts.idTipoSitio  \n");
        sql.append(" where ts.nombre='Punto de suplencia PDS' and r.idDivipolSitio=");
        sql.append(idDivipol).append(" order by r.prioridad");

        try {
            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Sitio sitio = new Sitio();
                            sitio.setCodigoSitio(res.getString("codigoPuesto"));
                            sitio.setNombreSitio(res.getString("nombrePuesto"));
                            sitio.setId(res.getInt("idDivipol"));
                            sitios.add(sitio);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return sitios;
    }

    public List<Sitio> listarHistorialContactosSitio(String codigoSitio) {

        final List<Sitio> sitios = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("select d.nombreRector, d.telefono, d.fax, d.email,");
        sql.append("p.nombre as prueba from divipol d ");
        sql.append("inner join prueba p on p.idprueba = d.idPrueba ");
        sql.append("where d.codigoPuesto ='");
        sql.append(codigoSitio);
        sql.append("'");

        try {
            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Sitio sitio = new Sitio();
                            sitio.setNombreRector(res.getString("nombreRector"));
                            sitio.setTelefonoRector(res.getString("telefono"));
                            sitio.setFaxRector(res.getString("fax"));
                            sitio.setEmailRector(res.getString("email"));
                            sitio.setNombreSede(res.getString("prueba"));
                            sitios.add(sitio);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return sitios;
    }

    public List<Sitio> filtrarPorGeolocalizacion(int tipoFiltro, int prueba, int municipio) {

        final List<Sitio> sitios = new ArrayList<>();

        try {
            // 1 - total sitios, 2 - Total geolocalizados, 2 - Total ubicados
            StringBuilder sql = new StringBuilder("SELECT * FROM divipol where idPrueba = ");
            sql.append(prueba).append(" AND codigoMunicipio = ").append(municipio);

            switch (tipoFiltro) {
                case 1:
                    sql.append(" and latitud = 0 and longitud = 0 and confirmado != '2'"); // No geolocalizados
                    break;
                case 2:
                    sql.append(" and latitud != 0 and longitud != 0 and confirmado = '0'");// No confirmados
                    break;
                case 3:
                    sql.append(" and confirmado = '2'");// No ubicados
                    break;
            }

            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Sitio sitio = new Sitio();
                            sitio.setCodigoSitio(res.getString("codigopuesto"));
                            sitio.setNombreSitio(res.getString("nombrepuesto"));
                            sitio.setId(res.getInt("idDivipol"));
                            sitio.setDireccionSitio(Normalizacion.normalizarDireccion(res.getString("direccionPuesto")));
                            sitio.setNombreRector(res.getString("nombreRector"));
                            sitio.setTelefonoRector(res.getString("telefono"));
                            sitio.setFaxRector(res.getString("fax"));
                            sitio.setEmailRector(res.getString("email"));
                            sitio.setDepartamento(new DepartamentoDao().GetById(res.getString("codigodepartamento")));
                            sitio.setMunicipio(new MunicipioDao().GetById(res.getString("codigodepartamento"), res.getString("codigomunicipio")));
                            sitios.add(sitio);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return sitios;
    }

    public int getTotalSitios(int tipoFiltro, int municipio, int prueba) {

        // 1 - total sitios, 2 - Total geolocalizados, 3 - Total confirmados, 4 - Total no ubicados
        StringBuilder sql = new StringBuilder("SELECT count(0) as cantidad FROM divipol where idPrueba=? AND codigoMunicipio = ? ");
        final Object id[] = new Object[1];
        id[0] = 0;

        switch (tipoFiltro) {
            case 1:
                break;
            case 2:
                sql.append("and latitud != 0 and longitud != 0");
                break;
            case 3:
                sql.append("and confirmado = '1'");
                break;
            case 4:
                sql.append("and confirmado = '2'");
                break;

        }
        try {
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            id[0] = res.getInt("cantidad");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString(), prueba, municipio);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return (int) id[0];
    }

    public boolean actualizarCoordenadas(Sitio sitio) {

        boolean inserto;
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE divipol SET ");
        sql.append(" latitud=?, longitud=?, confirmado=? ");
        sql.append(" where idDivipol=?");

        inserto = conex.ejecutar(sql.toString(),
                sitio.getLatitud(),
                sitio.getLongitud(),
                sitio.getConfirmado(),
                sitio.getId()
        );

        return inserto;
    }

    /**
     * Metodo DAO encargado de consultar los sitios PDS segun los sitios
     * asignados al usuario
     *
     * @param idPrueba
     * @return
     */
    public List<Sitio> getPDSByPrueba(int idPrueba) {
        final List<Sitio> listSitios = new ArrayList<>();
        StringBuilder consulta = new StringBuilder();
        consulta.append("select idDivipol as idSitio ,nombrePuesto as nombrePuesto, codigoPuesto as codigoPuesto ");
        consulta.append("from divipol rpds ");
        consulta.append("where idTipoSitio = 5 ");
        consulta.append("and idPrueba = ").append(idPrueba);
        Logger.getLogger(SitioDao.class.getName()).log(Level.INFO, consulta.toString());
        conex.consultar(consulta.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    while (resultado.next()) {
                        Sitio sitio = new Sitio();
                        sitio.setId(resultado.getInt("idSitio"));
                        sitio.setNombreSitio(resultado.getString("nombrePuesto"));
                        sitio.setCodigoSitio(resultado.getString("codigoPuesto"));
                        listSitios.add(sitio);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return listSitios;
    }

    public String ListarSitioUsuario(int idPrueba, String usuario) {

        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" select d.idDivipol,d.codigoPuesto, concat(d.codigoPuesto,' - ',d.nombreMunicipio,' - ',d.nombrePuesto) puesto  ");
            sql.append(" from divipol d ");
            sql.append(" inner join usuario_departamento ud on ud.idPrueba=d.idPrueba and ud.usuario=").append(usuario).append(" and ud.codigoDepartamento=d.codigoDepartamento ");
            sql.append(" where d.idPrueba = ").append(idPrueba).append("");
            sql.append(" order by d.codigoPuesto ");

            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());
            json = (JSONArray) result[0];
        } catch (Exception e) {
            Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    public String buscarSitiosDepartamentoPrueba(int idPrueba, String departamento, int idEvento) {

        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" select d.idDivipol,d.codigoPuesto, d.nombrePuesto,");
            sql.append(" (select count(*) from sitio_evento_validado s where s.iddivipol=d.idDivipol and s.codigoevento=");
            sql.append(idEvento).append(") as sitiorevisado");
            sql.append(" from divipol d  ");
            sql.append(" where idPrueba=").append(idPrueba);
            sql.append(" and codigoDepartamento='").append(departamento).append("'");
            sql.append(" and idDivipol in (select a.iddivipol from asistencia a where a.codigoevento=");
            sql.append(idEvento).append(")");
            sql.append(" order by d.codigoPuesto ");

            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());
            json = (JSONArray) result[0];
        } catch (Exception e) {
            Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    /**
     * Author: John Steak Herrera Moreno 
     * Date: 02/06/2022
     * 
     * @param idDivipol
     * @return 
     */
    public Sitio findById(int idDivipol) {
        final Sitio sitio = new Sitio();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM divipol WHERE idDivipol = ");
            sql.append(idDivipol);
            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            sitio.setId(res.getInt("idDivipol"));
                            sitio.setIdPrueba(res.getInt("idPrueba"));
                            sitio.setCodigoDepartamento(res.getString("codigoDepartamento"));
                            sitio.setCodigoMunicipio(res.getString("codigoMunicipio"));
                            sitio.setCodigoZona(res.getString("codigoZona"));
                            sitio.setCodigoSitio(res.getString("codigoPuesto"));
                            sitio.setNombreSitio(res.getString("nombrePuesto"));
                            sitio.setIddivipol(res.getInt("idDivipol"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());
        } catch (Exception e) {
            Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return sitio;
    }

}
