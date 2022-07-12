package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Rodríguez
 */
public class AsistenciaDao {

    private Operaciones conex = new Operaciones();

    public AsistenciaDao() {

    }

    public boolean RegistrarAsistencia(int idEmpleado, int codEvento, int idDivipol,  boolean asistenciaXBiometria, String usuario) {
        try {

            String sql;

            Boolean exis = ValidaTieneAsistencia(codEvento, idDivipol, idEmpleado);

            if (exis) {
                sql = " update asistencia set biometrico = ?, usuario = ?, estado=? "
                        + " where codigoevento = ? "
                        + " and idempleado = ? "
                        + " and iddivipol = ?;";
                return conex.ejecutar(sql, asistenciaXBiometria ? 1 : 0, usuario, asistenciaXBiometria ? 1 : 0, codEvento, idEmpleado, idDivipol);
            } else {
                sql = " insert into asistencia (codigoevento, iddivipol, idempleado, biometrico, usuario, estado) "
                        + " values (?,?,?,?,?,?);";
                return conex.ejecutar(sql, codEvento, idDivipol, idEmpleado, asistenciaXBiometria ? 1 : 0, usuario, asistenciaXBiometria ? 1 : 0);
            }

        } catch (Exception e) {
            Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public boolean EliminarAsistencia(int idEmpleado, int codEvento, int idDivipol) {

        try {
            String sql = " delete from asistencia where codigoevento = ? and  iddivipol = ? and  idempleado = ? and biometrico = 0 ";
            return conex.ejecutar(sql, codEvento, idDivipol, idEmpleado);
        } catch (Exception e) {
            Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public Boolean ValidaTieneAsistencia(int codigoevento, int iddivipol, int idempleado) {
        final Object id[] = new Object[1];
        id[0] = false;
        try {

            String sql = " select count(id) as asistio "
                    + " from asistencia "
                    + " where codigoevento = ? "
                    + " and idempleado = ? "
                    + " and iddivipol = ? ";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            int asistio = res.getInt("asistio");
                            if (asistio > 0) {
                                id[0] = true;
                            } else {
                                id[0] = false;
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, codigoevento, idempleado, iddivipol);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return (Boolean) id[0];
    }

    public Boolean tieneAsistencia(int codigoevento, int iddivipol, long nrodoc) {
        final Object id[] = new Object[1];
        id[0] = false;
        try {

            String sql = " select count(id) as asistio "
                    + " from asistencia "
                    + " where codigoevento = ? "
                    + " and idempleado = (select idempleado from empleado where nrodoc = ? )"
                    + " and iddivipol = ?";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            int asistio = res.getInt("asistio");
                            if (asistio > 0) {
                                id[0] = true;
                            } else {
                                id[0] = false;
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, codigoevento, nrodoc, iddivipol);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return (Boolean) id[0];
    }

    public int tieneAsistenciaBiometrica(int codigoevento, int iddivipol, long nrodoc) {
        final Object id[] = new Object[1];
        id[0] = 0;
        try {

            String sql = " select case when biometrico is null then 0 else biometrico end as biometrico "
                    + " from ("
                    + " select sum(biometrico) as biometrico"
                    + " from asistencia "
                    + " where codigoevento = ?"
                    + " and idempleado = (select idempleado from empleado where nrodoc = ?)"
                    + " and iddivipol = ?"
                    + " and biometrico = 1) as b ";

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {

                            int asistenciaBiometrica = res.getInt("biometrico");
                            if (asistenciaBiometrica > 0) {
                                id[0] = 1;
                            } else {
                                id[0] = 0;
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql, codigoevento, nrodoc, iddivipol);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return (int) id[0];
    }

    public boolean actualizarDivipolAsistencia(int idEmpleado, int codEvento, int idDivipol) {
        try {

            StringBuilder sql = new StringBuilder();
            sql.append(" update asistencia set iddivipol=").append(idDivipol);
            sql.append(" where codigoevento = ").append(codEvento);
            sql.append(" and idempleado =").append(idEmpleado);

            return conex.ejecutar(sql.toString());

        } catch (Exception e) {
            Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    /*Guarda si la asistencia manual fue revisada */
    public boolean guardaValidacionAsistencia(int idEmpleado, int codEvento, int idDivipol, boolean validado, String usuario) {
        try {

            StringBuilder sql = new StringBuilder();
            sql.append(" update asistencia set estado=?, usuario_valida=?");
            sql.append(" where codigoevento=? and idempleado=? and iddivipol=? ");

            return conex.ejecutar(sql.toString(), validado ? 1 : 0, usuario, codEvento, idEmpleado, idDivipol);

        } catch (Exception e) {
            Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }
}
