/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;
import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Prueba;
import co.com.grupoasd.nomina.modelo.TipoPruebaEsp;
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
 * @author Pedro Rodríguez
 */
public class PruebaDao {

    private Operaciones conex = new Operaciones();

    public PruebaDao() {
    }

    public int insertar(Prueba prueba) {
                    StringBuilder sql = new StringBuilder();
                    sql.append(" insert into prueba (idprueba,nombre,tprueba,estadoprueba,dias,fechaaplicacion,fecha_final_aplicacion,fecha_inicio_convocatoria,fecha_final_convocatoria, ");
                    sql.append(" vtiger_campo_estado,vtiger_campo_texto,vtiger_campo_cargo, ");
                    sql.append(" fecha_actualiza,vtiger_campo_municipio,vtiger_campo_nodo,texto_convocatoria,idEstadoPrueba) Values ( 0,");
                    sql.append("'").append(prueba.getNombre()).append("',");
                    sql.append("").append(prueba.getTprueba()).append(",");
                    //sql.append("(select Idtprueba from tipoprueba where tprueba='").append(prueba.getTnombreprueba()).append("'),");
                    sql.append("'").append(prueba.getCodigoEstadoPrueba()).append("',");
                    sql.append("'").append(prueba.getDias()).append("',");
                    sql.append("?,?,?,?,");
                    sql.append("'").append(prueba.getVtiger_campo_estado()).append("',");
                    sql.append("'").append(prueba.getVtiger_campo_texto()).append("',");
                    sql.append("'").append(prueba.getVtiger_campo_cargo()).append("',");
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
                    }, sql.toString(),prueba.getFechaaplicacion(),prueba.getFecha_final_aplicacion(),
                            prueba.getFecha_inicio_convocatoria(),prueba.getFecha_final_convocatoria());
            return ((int) result[0]);
        }

    public List<Prueba> listarOnDivitrans() {
        final List<Prueba> Pruebas = new ArrayList<>();

        try {
            String sql = " select * from prueba where idprueba in(select distinct idprueba from divitrans) ";
            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Prueba prueba = new Prueba();
                            
                            prueba.setIdprueba(res.getInt("idprueba"));
                            prueba.setNombre(res.getString("nombre"));
                            prueba.setTprueba(res.getInt("tprueba"));
                            prueba.setEstadoprueba(res.getString("estadoprueba"));
                            prueba.setDias(res.getInt("dias"));
                            prueba.setVtiger_campo_estado(res.getString("vtiger_campo_estado"));
                            prueba.setVtiger_campo_texto(res.getString("vtiger_campo_texto"));
                            prueba.setVtiger_campo_cargo(res.getString("vtiger_campo_cargo"));
                            prueba.setFechaaplicacion(res.getDate("fechaaplicacion"));
                            Pruebas.add(prueba);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PruebaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(PruebaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Pruebas;
    }

    public List<Prueba> listar() {
        final List<Prueba> Pruebas = new ArrayList<>();

        try {
            String sql = "select * from prueba where estadoprueba = 'EN CONVOCATORIA' ";
            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Prueba prueba = new Prueba();
                            
                            prueba.setIdprueba(res.getInt("idprueba"));
                            prueba.setNombre(res.getString("nombre"));
                            prueba.setTprueba(res.getInt("tprueba"));
                            prueba.setEstadoprueba(res.getString("estadoprueba"));
                            prueba.setDias(res.getInt("dias"));
                            prueba.setVtiger_campo_estado(res.getString("vtiger_campo_estado"));
                            prueba.setVtiger_campo_texto(res.getString("vtiger_campo_texto"));
                            prueba.setVtiger_campo_cargo(res.getString("vtiger_campo_cargo"));
                            prueba.setFechaaplicacion(res.getDate("fechaaplicacion"));
                            Pruebas.add(prueba);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PruebaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (Exception e) {
            Logger.getLogger(PruebaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Pruebas;
    }
    
    public List<Prueba> listarAll(int idPrueba) {
        final List<Prueba> Pruebas = new ArrayList<>();
        try {
            String s1 = Integer.toString(idPrueba);
            StringBuilder sql = new StringBuilder();
            sql.append(" select p.idprueba,p.nombre,p.tprueba,p.estadoprueba,p.dias, ");
            sql.append(" p.vtiger_campo_estado,p.vtiger_campo_texto,p.vtiger_campo_cargo,p.fechaaplicacion, ");
            sql.append(" p.fecha_final_aplicacion,p.fecha_final_convocatoria,p.fecha_inicio_convocatoria, ");
            sql.append(" p.puede_cancelar,p.fecha_actualiza,tp.tprueba as tnombreprueba,tp.descripcion as tpruebadescripcion, ep.idEstadoPrueba,");
            sql.append(" p.vtiger_campo_nodo,p.vtiger_campo_municipio,ep.descripcion as descripcionestprueba,ep.codigoEstadoPrueba,p.texto_convocatoria ");
            sql.append(" from prueba p,tipoprueba tp,estadosPrueba ep ");
            sql.append(" where p.tprueba = tp.Idtprueba and p.estadoprueba = ep.codigoEstadoPrueba ");
            if (idPrueba > 0) {
                sql.append(" and   p.idprueba= ");
                sql.append(s1);
            }
            sql.append(" order by ifnull(p.fechaaplicacion,'12/31/2010') ");

            conex.consultar(new Operaciones.OperacionConsulta(){
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Prueba prueba = new Prueba();
                            prueba.setIdprueba(res.getInt("idprueba"));
                            prueba.setNombre(res.getString("nombre"));
                            prueba.setTprueba(res.getInt("tprueba"));
                            prueba.setEstadoprueba(res.getString("estadoprueba"));
                            prueba.setDias(res.getInt("dias"));
                            prueba.setVtiger_campo_estado(res.getString("vtiger_campo_estado"));
                            prueba.setVtiger_campo_texto(res.getString("vtiger_campo_texto"));
                            prueba.setVtiger_campo_cargo(res.getString("vtiger_campo_cargo"));
                            prueba.setFechaaplicacion(res.getDate("fechaaplicacion"));
                            prueba.setTnombreprueba(res.getString("tnombreprueba"));
                            prueba.setTpruebadescripcion(res.getString("tpruebadescripcion"));
                            prueba.setFecha_final_aplicacion(res.getDate("fecha_final_aplicacion"));
                            prueba.setFecha_final_convocatoria(res.getDate("fecha_final_convocatoria"));
                            prueba.setFecha_inicio_convocatoria(res.getDate("fecha_inicio_convocatoria"));
                            prueba.setVtiger_campo_nodo(res.getString("vtiger_campo_nodo"));
                            prueba.setVtiger_campo_municipio(res.getString("vtiger_campo_municipio"));
                            prueba.setDescripcionestprueba(res.getString("descripcionestprueba"));
                            prueba.setCodigoEstadoPrueba(res.getString("codigoEstadoPrueba"));
                            prueba.setTexto_convocatoria(res.getString("texto_convocatoria"));
                            prueba.setIdEstadoPrueba(res.getInt("idEstadoPrueba"));
                            Pruebas.add(prueba);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PruebaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString());
        } catch (Exception e) {
            Logger.getLogger(PruebaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Pruebas;
    }

    public Prueba getById(int id) {
        final Prueba prueba = new Prueba();

        try {
            String sql = "select * from prueba where idprueba = " + id;
            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            prueba.setIdprueba(res.getInt("idprueba"));
                            prueba.setNombre(res.getString("nombre"));
                            prueba.setTprueba(res.getInt("tprueba"));
                            prueba.setEstadoprueba(res.getString("estadoprueba"));
                            prueba.setDias(res.getInt("dias"));
                            prueba.setVtiger_campo_estado(res.getString("vtiger_campo_estado"));
                            prueba.setVtiger_campo_texto(res.getString("vtiger_campo_texto"));
                            prueba.setVtiger_campo_cargo(res.getString("vtiger_campo_cargo"));
                            prueba.setFechaaplicacion(res.getDate("fechaaplicacion"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PruebaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(PruebaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return prueba;
    }

    public String listarTipPrueba() {
        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            StringBuilder sql=new StringBuilder();
            sql.append("select Idtprueba,tprueba,descripcion,usuario,fecha,activo from tipoprueba where activo = 1 order by tprueba ");
            conex.consultar(sql.toString(),new Operaciones.OperacionConsulta() {
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

    public List<TipoPruebaEsp> listarTipPruebaEsp(){
        final List<TipoPruebaEsp> Tipo = new ArrayList<>();
        try {
            StringBuilder sql=new StringBuilder();
            sql.append("select Idtprueba as tprueba,tprueba as tipo,descripcion,usuario,fecha,activo from tipoprueba where activo = 1 order by tipo ");
            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            TipoPruebaEsp reg = new TipoPruebaEsp();
                            reg.setTprueba(res.getInt("tprueba"));
                            reg.setTipo(res.getString("tipo"));
                            reg.setDescripcion(res.getString("descripcion"));
                            Tipo.add(reg);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PruebaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(PruebaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Tipo;
    }
    
    public String listarEstPrueba() {
        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            StringBuilder sql=new StringBuilder();
            sql.append("select idEstadoPrueba,codigoEstadoPrueba,descripcion,activo from estadosPrueba where activo='A' order by descripcion ");
            conex.consultar(sql.toString(),new Operaciones.OperacionConsulta() {
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
    
    public List<Prueba> listarxFecha(String fechaInicial, String fechaFinal) {
        final List<Prueba> Pruebas = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        try {
            sb.append("select idprueba,nombre,a.tprueba,b.descripcion,estadoprueba,fechaaplicacion ");
            sb.append("from prueba a inner join tipoprueba b on a.tprueba = b.idtprueba ");
            sb.append("where fechaaplicacion between '");
            sb.append(fechaInicial);
            sb.append("' and '");
            sb.append(fechaFinal);
            sb.append("'");

            conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Prueba prueba = new Prueba();

                            prueba.setIdprueba(res.getInt("idprueba"));
                            prueba.setNombre(res.getString("nombre"));
                            prueba.setTprueba(res.getInt("tprueba"));
                            prueba.setEstadoprueba(res.getString("estadoprueba"));
                            prueba.setTipoPrueba(res.getString("descripcion"));
                            prueba.setFechaaplicacion(res.getDate("fechaaplicacion"));
                            Pruebas.add(prueba);

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PruebaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(PruebaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Pruebas;
    }
    
    public List<Prueba> listarxEstado(String estado,List<Long> sitios) {
        final List<Prueba> Pruebas = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        try {
            sb.append("select idprueba,nombre,a.tprueba,b.descripcion,estadoprueba,fechaaplicacion ");
            sb.append("from prueba a inner join tipoprueba b on a.tprueba = b.idtprueba ");
            sb.append("where trim(estadoprueba) = trim('");
            sb.append(estado);            
            sb.append("') union ");
            sb.append("select idprueba,nombre,a.tprueba,b.descripcion,estadoprueba,fechaaplicacion ");
            sb.append("from prueba a ");
            sb.append("inner join tipoprueba b on a.tprueba = b.idtprueba ");
            sb.append("inner join estadosPrueba c on c.idEstadoPrueba = a.idEstadoPrueba and trim(c.codigoEstadoPrueba) = trim('");            
            sb.append(estado);
            sb.append("');");                               
                    
            conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Prueba prueba = new Prueba();
                            
                            prueba.setIdprueba(res.getInt("idprueba"));
                            prueba.setNombre(res.getString("nombre"));
                            prueba.setTprueba(res.getInt("tprueba"));
                            prueba.setEstadoprueba(res.getString("estadoprueba"));
                            prueba.setTipoPrueba(res.getString("descripcion"));                            
                            prueba.setFechaaplicacion(res.getDate("fechaaplicacion"));
                            Pruebas.add(prueba);
                            
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PruebaDao.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(PruebaDao.class
                    .getName()).log(Level.SEVERE, null, e);
        }
        return Pruebas;
    }
    
    public Boolean Actualizar(Prueba prueba) {
          Boolean resultado     = false;

              StringBuilder sql=new StringBuilder();
              sql.append("  update prueba p,estadosPrueba ep set ");
              sql.append("  p.nombre = ? ");
              sql.append(", p.dias = ? ");
              sql.append(", p.fecha_final_aplicacion = ? ");
              sql.append(", p.fecha_final_convocatoria = ? ");
              sql.append(", p.fecha_inicio_convocatoria = ? ");
              sql.append(", p.vtiger_campo_estado = ? ");
              sql.append(", p.vtiger_campo_texto = ? ");
              sql.append(", p.vtiger_campo_cargo = ? ");
              sql.append(", p.fechaaplicacion = ? ");
              sql.append(", p.vtiger_campo_municipio = ? ");
              sql.append(", p.vtiger_campo_nodo = ? ");
              sql.append(", p.texto_convocatoria = ? ");
              sql.append(", p.idEstadoPrueba = ep.idEstadoPrueba ");
              sql.append(", p.estadoprueba=ep.codigoEstadoPrueba,p.tprueba= ? ");
              sql.append("  where p.idprueba= ? ");
              sql.append("  and ep.descripcion= ? ");
              
              resultado = conex.ejecutar(sql.toString(),
                        prueba.getNombre(),
                        prueba.getDias(),
                        prueba.getFecha_final_aplicacion(),
                        prueba.getFecha_final_convocatoria(),
                        prueba.getFecha_inicio_convocatoria(),
                        prueba.getVtiger_campo_estado(),
                        prueba.getVtiger_campo_texto(),
                        prueba.getVtiger_campo_cargo(),
                        prueba.getFechaaplicacion(),
                        prueba.getVtiger_campo_municipio(),
                        prueba.getVtiger_campo_nodo(),
                        prueba.getTexto_convocatoria(),
                        prueba.getTprueba(),
                        prueba.getIdprueba(),
                        prueba.getCodigoEstadoPrueba());
              return resultado;
      }

    public List<Prueba> listarnocerradas() {
        final List<Prueba> Pruebas = new ArrayList<>();

        try {
            String sql = "select * from prueba where estadoprueba <> 'CERRADO' order by fechaaplicacion desc";
            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Prueba prueba = new Prueba();
                            
                            prueba.setIdprueba(res.getInt("idprueba"));
                            prueba.setNombre(res.getString("nombre"));
                            prueba.setTprueba(res.getInt("tprueba"));
                            prueba.setEstadoprueba(res.getString("estadoprueba"));
                            prueba.setDias(res.getInt("dias"));
                            prueba.setVtiger_campo_estado(res.getString("vtiger_campo_estado"));
                            prueba.setVtiger_campo_texto(res.getString("vtiger_campo_texto"));
                            prueba.setVtiger_campo_cargo(res.getString("vtiger_campo_cargo"));
                            prueba.setFechaaplicacion(res.getDate("fechaaplicacion"));
                            Pruebas.add(prueba);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PruebaDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (Exception e) {
            Logger.getLogger(PruebaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Pruebas;
    }
}
