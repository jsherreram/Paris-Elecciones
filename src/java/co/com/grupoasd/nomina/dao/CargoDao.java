/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Cargo;
import co.com.grupoasd.nomina.modelo.NivelCargo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import co.com.grupoasd.nomina.modelo.seguimiento.ResultSetConverter;
import org.json.JSONException;

/**
 * @author Pedro Rodriguez
 */
public class CargoDao {

    private Operaciones conex = new Operaciones();

    public CargoDao() {
    }

    /**
     * Permite registrar un cargo
     *
     * @param Cargo
     */
    public Boolean insertar(Cargo cargo) {
        Boolean resultado = false;

        StringBuilder sql = new StringBuilder();
        sql.append(" insert into cargos (codigoCargo,descripcion,codigoLogisys,nombrecarne,funciones,viaticos,consalon,capacitacion,nivel_cargo,esSuplente,esicfes) values (");
        sql.append("'").append(cargo.getCodigoCargo()).append("',");
        sql.append("'").append(cargo.getDescripcion()).append("',");
        sql.append("'").append(cargo.getCodigoLogisys()).append("',");
        sql.append("'").append(cargo.getFunciones()).append("',");
        sql.append("'").append(cargo.getNombrecarne()).append("',");
        sql.append("").append(cargo.getViaticos()).append(",");
        sql.append("").append(cargo.getConsalon()).append(",");
        sql.append("").append(cargo.getCapacitacion()).append(",");
        sql.append("").append(cargo.getNivel_cargo()).append(",");
        sql.append("").append(cargo.getEsSuplente()).append(",");
        sql.append("").append(cargo.getEsicfes()).append(");");

        resultado = conex.ejecutar(sql.toString());
        return resultado;
    }

    public List<Cargo> listar() {
        final List<Cargo> Cargos = new ArrayList<>();
        try {
            conex.consultar("SELECT c.*,n.descripcion  as desnivel FROM cargos c,nivel_cargo n where c.nivel_cargo=n.id order by c.descripcion", new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Cargo cargo = new Cargo();
                            cargo.setCodigoCargo(res.getString("codigoCargo"));
                            cargo.setDescripcion(res.getString("descripcion"));
                            cargo.setNombrecarne(res.getString("nombrecarne"));
                            cargo.setFunciones(res.getString("funciones"));
                            cargo.setViaticos(res.getInt("viaticos"));
                            cargo.setConsalon(res.getInt("consalon"));
                            cargo.setCapacitacion(res.getInt("capacitacion"));
                            //cargo.setIdorder(res.getInt("idorder"));
                            cargo.setNivel_cargo(res.getInt("nivel_cargo"));
                            cargo.setEsSuplente(res.getInt("esSuplente"));
                            cargo.setEquivalente_suplencia(res.getInt("equivalente_suplencia"));
                            cargo.setDesnivel(res.getString("desnivel"));
                            cargo.setEsicfes(res.getInt("esicfes"));
                            cargo.setPolivalente(res.getInt("polivalente"));
                            cargo.setEsgsa(res.getInt("esgsa"));
                            cargo.setBloqueadomonitoreo(res.getInt("bloqueadomonitoreo"));

                            Cargos.add(cargo);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Cargos;
    }

    public List<Cargo> listarCargosSuplencia() {
        final List<Cargo> Cargos = new ArrayList<>();
        try {
            conex.consultar("SELECT c.*,n.descripcion  as desnivel FROM cargos c,nivel_cargo n where c.nivel_cargo=n.id and esSuplente = 1 ", new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Cargo cargo = new Cargo();
                            cargo.setCodigoCargo(res.getString("codigoCargo"));
                            cargo.setDescripcion(res.getString("descripcion"));
                            cargo.setNombrecarne(res.getString("nombrecarne"));
                            cargo.setFunciones(res.getString("funciones"));
                            cargo.setViaticos(res.getInt("viaticos"));
                            cargo.setConsalon(res.getInt("consalon"));
                            cargo.setCapacitacion(res.getInt("capacitacion"));                            
                            cargo.setNivel_cargo(res.getInt("nivel_cargo"));
                            cargo.setEsSuplente(res.getInt("esSuplente"));
                            cargo.setDesnivel(res.getString("desnivel"));
                            cargo.setEsicfes(res.getInt("esicfes"));
                            Cargos.add(cargo);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Cargos;
    }

    public Cargo GetById(String id) {

        final Cargo cargo = new Cargo();
        id  =   this.validaNull(id);
        String sql = "SELECT * FROM cargos where codigoCargo = '" + id + "'";
        conex.consultar(sql, new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet res) {
                try {
                    while (res.next()) {
                        cargo.setCodigoCargo(res.getString("codigoCargo"));
                        cargo.setDescripcion(res.getString("descripcion"));
                        cargo.setNombrecarne(res.getString("nombrecarne"));
                        cargo.setViaticos(res.getInt("viaticos"));
                        cargo.setConsalon(res.getInt("consalon"));
                        cargo.setCapacitacion(res.getInt("capacitacion"));
                        //cargo.setIdorder(res.getInt("idorder"));
                        cargo.setEsSuplente(res.getInt("esSuplente"));
                        cargo.setNivel_cargo(res.getInt("nivel_cargo"));
                        cargo.setEsicfes(res.getInt("esicfes"));
                        cargo.setEsgsa(res.getInt("esgsa"));
                        cargo.setBloqueadomonitoreo(res.getInt("bloqueadomonitoreo"));
                        cargo.setPolivalente(res.getInt("polivalente"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        return cargo;
    }

    public List<NivelCargo> listarNivCargo() {
        final List<NivelCargo> Tipo = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select Id as nivel_cargo,descripcion as nivel_descripcion from nivel_cargo order by nivel_descripcion ");
            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            NivelCargo reg = new NivelCargo();
                            reg.setNivel_cargo(res.getInt("nivel_cargo"));
                            reg.setNivel_descripcion(res.getString("nivel_descripcion"));
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

   public Boolean Actualizar(Cargo cargo) {
          Boolean resultado     = false;

              StringBuilder sql=new StringBuilder();
              sql.append("  update cargos set ");
              sql.append("  descripcion = ? ");
              sql.append(", codigoLogisys = ? ");
              sql.append(", nombrecarne = ? ");
              sql.append(", viaticos = ? ");
              sql.append(", funciones = ? ");
              sql.append(", consalon = ? ");
              sql.append(", capacitacion = ? ");
              sql.append(", esSuplente = ? ");
              sql.append(", esicfes = ? ");
              sql.append(", nivel_cargo = ? ");
              sql.append("  where codigoCargo = ?");
              
              resultado = conex.ejecutar(sql.toString(),
                        cargo.getDescripcion(),
                        cargo.getCodigoLogisys(),
                        cargo.getNombrecarne(),
                        cargo.getViaticos(),
                        cargo.getFunciones(),
                        cargo.getConsalon(),
                        cargo.getCapacitacion(),
                        cargo.getEsSuplente(),
                        cargo.getEsicfes(),
                        cargo.getNivel_cargo(),
                        cargo.getCodigoCargo());
              return resultado;
      }

   private String validaNull(String texto) {
    if (texto == null) {
        return "";
    } else {
        return texto;
    }
    }

}
