/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Sitio;
import co.com.grupoasd.nomina.modelo.UsuarioSitio;
import co.com.grupoasd.nomina.modelo.seguimiento.ResultSetConverter;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import static javax.ws.rs.client.Entity.json;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Pedro Rodríguez
 */
public class UsuarioSitioDao {

    private Operaciones conex = new Operaciones();

    public List<Sitio> listar(String usuario, int idPrueba) {
        final List<Sitio> sitios = new ArrayList<>();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" select distinct a.idDivipol, b.codigopuesto, concat(b.codigopuesto,'-',b.nombrepuesto) as puesto \n");
            sql.append(" from usuario_sitio a left join divipol b on a.iddivipol = b.idDivipol \n");
            sql.append(" left join nombramiento n on a.iddivipol = n.idDivipol \n");
            sql.append(" where a.usuario =").append(usuario);
            sql.append(" and b.idPrueba = ").append(idPrueba);
      
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
                        Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return sitios;
    }

    public List<Sitio> listarsinCierre(String usuario, int idPrueba) {
        final List<Sitio> sitios = new ArrayList<>();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" select distinct a.idDivipol, b.codigopuesto, concat(b.codigopuesto,'-',b.nombrepuesto) as puesto \n");
            sql.append(" from usuario_sitio a left join divipol b on a.iddivipol = b.idDivipol \n");
            sql.append(" left join nombramiento n on a.iddivipol = n.idDivipol \n");
            sql.append(" where a.usuario =").append(usuario);
            sql.append(" and a.idDivipol not in(select c.idDivipol from cierre_asistencia_sesion c where c.idDivipol=a.idDivipol)");
            sql.append(" and b.idPrueba = ").append(idPrueba);
      
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
                        Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return sitios;
    }

    public List<Sitio> listarXEvento(String usuario, int codigoEvento) {
        final List<Sitio> sitios = new ArrayList<>();

        try {
            String sql = "select distinct a.idDivipol, b.codigopuesto, concat(b.codigopuesto,'-',nombrepuesto) as puesto \n"
                    + "from usuario_sitio a left join divipol b\n"
                    + "on a.iddivipol = b.idDivipol \n"
                    + "left join detalle_cargo_x_puesto_x_evento dcpe\n"
                    + "on b.idDivipol = dcpe.idDivipol\n"
                    + "and b.idPrueba = dcpe.idPrueba\n"
                    + "and b.codigoPuesto = dcpe.codigoPuesto\n"
                    + "where a.usuario = '" + usuario + "' and dcpe.codigoEvento = " + codigoEvento + ";";

            conex.consultar(sql, new Operaciones.OperacionConsulta() {

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
                        Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return sitios;
    }

    public UsuarioSitio GetByUsuario(long usuario) {

        final UsuarioSitio usuarioSitio = new UsuarioSitio();

        try {
            String sql = "select * from usuario_sitio where usuario = ? limit 1;";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        if (res.next()) {
                            usuarioSitio.setUsuario(res.getLong("usuario"));
                            usuarioSitio.setIdDivipol(res.getInt("iddivipol"));
                            usuarioSitio.setFechaActualiza(res.getTimestamp("fecha_actualiza"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, usuario);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return usuarioSitio;
    }

    public List<UsuarioSitio> GetSitiosByUsuario(Long usuario) {

        final List<UsuarioSitio> usuariosSitio = new ArrayList<>();

        try {
            String sql = "select usuario, divi.idDivipol, u.fecha_actualiza\n"
                    + "from usuario_sitio u left join divipol divi\n"
                    + "on u.iddivipol = divi.idDivipol\n"
                    + "where usuario = ? ;";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        res.beforeFirst();
                        while (res.next()) {
                            UsuarioSitio usuarioSitio = new UsuarioSitio();
                            usuarioSitio.setUsuario(res.getLong("usuario"));
                            usuarioSitio.setIdDivipol(res.getInt("iddivipol"));
                            usuarioSitio.setFechaActualiza(res.getTimestamp("fecha_actualiza"));
                            usuariosSitio.add(usuarioSitio);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, usuario);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return usuariosSitio;
    }    
    
    public List<UsuarioSitio> GetSitiosByUsuario(Integer usuario) {

        final List<UsuarioSitio> usuariosSitio = new ArrayList<>();

        try {
            String sql = "select usuario, divi.idDivipol, u.fecha_actualiza\n"
                    + "from usuario_sitio u left join divipol divi\n"
                    + "on u.iddivipol = divi.idDivipol\n"
                    + "where usuario = ? ;";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        res.beforeFirst();
                        while (res.next()) {
                            UsuarioSitio usuarioSitio = new UsuarioSitio();
                            usuarioSitio.setUsuario(res.getLong("usuario"));
                            usuarioSitio.setIdDivipol(res.getInt("iddivipol"));
                            usuarioSitio.setFechaActualiza(res.getTimestamp("fecha_actualiza"));
                            usuariosSitio.add(usuarioSitio);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, usuario);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return usuariosSitio;
    }

    public String listarArchivoPagoSubidoSitio(String usuario, final int idPrueba){
        final List<Sitio> sitios = new ArrayList<>();
        final JSONArray json = new JSONArray();

        try {
            String sql = " select  b.codigopuesto, b.nombrepuesto as puesto \n"
                    + " from usuario_sitio a left join divipol b\n"
                    + " on a.iddivipol = b.idDivipol \n"
                    + " where usuario = '" + usuario + "' and b.idPrueba = " + idPrueba + " ;";

            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            JSONObject sitio = new JSONObject();
                            sitio.put("codigoPuesto", res.getString("codigopuesto"));
                            sitio.put("puesto", res.getString("puesto"));

                            String sFichero = "/data/Elecciones/img/listas/pagos/" + idPrueba + "/" + res.getString("codigopuesto") + ".pdf";
                            File f = new File(sFichero);
                            if (f.exists()) {
                                sitio.put("archivo", "Si");
                            } else {
                                sitio.put("archivo", "No");
                            }
                            json.put(sitio);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    public String listarArchivoListadoAsistenciaSitio(String usuario, final int idPrueba, final String codEvento) {
        final List<Sitio> sitios = new ArrayList<>();
        final JSONArray json = new JSONArray();

        try {
            String sql = " select  b.codigopuesto, b.nombrepuesto as puesto \n"
                    + " from usuario_sitio a left join divipol b\n"
                    + " on a.iddivipol = b.idDivipol \n"
                    + " where usuario = '" + usuario + "' and b.idPrueba = " + idPrueba + " ;";

            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            JSONObject sitio = new JSONObject();
                            sitio.put("codigoPuesto", res.getString("codigopuesto"));
                            sitio.put("puesto", res.getString("puesto"));

                            String sFichero = "/data/Elecciones/img/listas/Listas_de_Asistencia/" + idPrueba + "/" + codEvento + "/" + res.getString("codigopuesto") + ".pdf";
                            File f = new File(sFichero);
                            if (f.exists()) {
                                sitio.put("archivo", "Si");
                            } else {
                                sitio.put("archivo", "No");
                            }
                            json.put(sitio);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    public boolean eliminarRegistros(long usuario,int idPrueba) {
        boolean resultado = false;
        StringBuilder sb = new StringBuilder();
        sb.append(" DELETE FROM usuario_sitio ");
        sb.append(" WHERE usuario = ? and iddivipol in(select iddivipol from divipol where idprueba = ? )");
        resultado = conex.ejecutar(sb.toString(), usuario,idPrueba);

        return resultado;
    }

    public boolean insertar(long iddivipol, long usuario) {

        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO usuario_sitio (");
        sb.append(" usuario,iddivipol, fecha_actualiza)");
        sb.append(" VALUES (?,?, current_timestamp)");

        return conex.ejecutar(sb.toString(), usuario, iddivipol);
    }

    /**
     * Obtiene el listado de sitios asignados a un usuario y los RPS asignados a
     * los sitios
     */
    public List<UsuarioSitio> GetSitiosYRpsPorUsuario(String usuario, int prueba) {

        final List<UsuarioSitio> usuariosSitio = new ArrayList<>();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select a.idDivipol, b.codigoPuesto, b.nombrePuesto, ");
            sql.append(" (select dt.nrodoc from detalle_cargo_x_puesto_x_evento dt where dt.codigoCargo in (12,33) and dt.idPrueba=");
            sql.append(prueba);
            sql.append(" and dt.idDivipol=b.idDivipol limit 1) as usuario ");
            sql.append(" from usuario_sitio a");
            sql.append(" left join divipol b on a.iddivipol = b.idDivipol ");
            sql.append(" where a.usuario =").append(usuario);
            sql.append(" and b.idPrueba =").append(prueba);

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            UsuarioSitio usuarioSitio = new UsuarioSitio();
                            usuarioSitio.setUsuario(res.getLong("usuario"));
                            Sitio sitio = new Sitio();
                            sitio.setId(res.getInt("idDivipol"));
                            sitio.setCodigoSitio(res.getString("codigoPuesto"));
                            sitio.setNombreSitio(res.getString("nombrePuesto"));
                            usuarioSitio.setSitio(sitio);

                            usuariosSitio.add(usuarioSitio);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return usuariosSitio;
    }

}
