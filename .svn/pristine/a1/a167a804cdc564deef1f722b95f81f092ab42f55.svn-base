/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.StatusCargue;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Rodríguez
 */
public class StatusCargueDao {

    private Operaciones conex = new Operaciones();

    public StatusCargueDao() {

    }

    public int insertar(StatusCargue s) {
        int id = 0;
        final Object[] result = new Object[1];
        byte[] bytes = new byte[1024];
        InputStream inputStream = new ByteArrayInputStream(bytes);
        String sql = " insert into status_cargue (usuario, idtipoCargue,"
                + " nombreArchivo, fechaHoraInicio, estadoStatus,"
                + " cantidadRegistrosTotal, cantidadRegistrosProcesadosOk,"
                + " cantidadRegistrosProcesadosError, ArchivoErrores,identificadorRegistro)"
                + " values (?,?,?,current_timestamp,?,?,?,?,?,?);";

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
                    Logger.getLogger(StatusCargueDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, sql, s.getUsuario(),
                s.getIdtipoCargue(),
                s.getNombreArchivo(),
                s.getEstadoStatus(),
                s.getCantidadRegistrosTotal(),
                s.getCantidadRegistrosProcesadosOk(),
                s.getCantidadRegistrosProcesadosError(),
                inputStream, s.getIdentificadorRegistro());

        id = (int) result[0];

        return id;
    }

    /**
     * Metodo para actualizar el avance del cargue de archivos
     *
     * @param s
     * @return
     */
    public Boolean actualizarAvance(StatusCargue s) {
        Boolean resultado = false;
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE status_cargue SET ");
        sql.append("cantidadRegistrosProcesadosOk = ").append(s.getCantidadRegistrosProcesadosOk());
        sql.append(",cantidadRegistrosProcesadosError = ").append(s.getCantidadRegistrosProcesadosError());
        sql.append(" WHERE id = ").append(s.getId());
        resultado = conex.ejecutar(sql.toString());
        return resultado;
    }

    public Boolean finalizar(StatusCargue s, StringBuilder sb) {
        byte[] bytes = sb.toString().getBytes();
        InputStream inputStream = new ByteArrayInputStream(bytes);

        Boolean resultado = false;

        String sql = " UPDATE status_cargue SET "
                + " estadoStatus = 1, fechaHoraFinal = current_timestamp, ArchivoErrores = ? "
                + " WHERE id = ? ";

        resultado = conex.ejecutarStatement(sql, inputStream, s.getId());

        return resultado;
    }

    public String getErrores(int idStatus) {

        try {
            final Object[] result = new Object[1];
            String sql = "SELECT ArchivoErrores FROM status_cargue where id = " + idStatus + ";";
            InputStream inputStream = null;
            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            result[0] = res.getBinaryStream("ArchivoErrores");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(StatusCargueDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            inputStream = (InputStream) result[0];
            return this.fromStream(inputStream);
        } catch (Exception e) {
            Logger.getLogger(StatusCargueDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return "";
    }

    public List<StatusCargue> listar(String usuario, int idTipoCargue) {
        final List<StatusCargue> statusCargues = new ArrayList<>();
        String sql = "SELECT * FROM status_cargue where usuario = '" + usuario + "' and idtipoCargue = " + idTipoCargue + " order by id desc ";

        try {

            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            StatusCargue s = new StatusCargue();
                            s.setId(res.getInt("id"));
                            s.setUsuario(res.getString("usuario"));
                            s.setIdtipoCargue(res.getInt("idtipoCargue"));
                            s.setNombreArchivo(res.getString("nombreArchivo"));
                            s.setFechaHoraInicio(res.getString("fechaHoraInicio"));
                            s.setFechaHoraFinal(res.getString("fechaHoraFinal"));
                            s.setEstadoStatus(res.getInt("estadoStatus"));
                            s.setCantidadRegistrosTotal(res.getInt("cantidadRegistrosTotal"));
                            s.setCantidadRegistrosProcesadosOk(res.getInt("cantidadRegistrosProcesadosOk"));
                            s.setCantidadRegistrosProcesadosError(res.getInt("cantidadRegistrosProcesadosError"));

                            if (s.getCantidadRegistrosProcesadosError() > 0 && s.getEstadoStatus() == 1) {
                                s.setMostrarImagenArchivoError("");
                            } else {
                                s.setMostrarImagenArchivoError("none");
                            }

                            if (s.getEstadoStatus() == 1) {
                                s.setEstado("TERMINADO");
                            } else {
                                s.setEstado("EN PROCESO");
                            }

                            statusCargues.add(s);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(StatusCargueDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(StatusCargueDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return statusCargues;
    }

    public String fromStream(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append(newLine);
        }
        return out.toString();
    }

    public List<StatusCargue> listar(String usuario, int idTipoCargue, int identificadorRegistro) {
        final List<StatusCargue> statusCargues = new ArrayList<>();
        String sql = "SELECT * FROM status_cargue where usuario = '" + usuario + "' and idtipoCargue = " + idTipoCargue + " and identificadorRegistro = " + identificadorRegistro + " ;";

        try {

            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            StatusCargue s = new StatusCargue();
                            s.setId(res.getInt("id"));
                            s.setUsuario(res.getString("usuario"));
                            s.setIdtipoCargue(res.getInt("idtipoCargue"));
                            s.setNombreArchivo(res.getString("nombreArchivo"));
                            s.setFechaHoraInicio(res.getString("fechaHoraInicio"));
                            s.setFechaHoraFinal(res.getString("fechaHoraFinal"));
                            s.setEstadoStatus(res.getInt("estadoStatus"));
                            s.setCantidadRegistrosTotal(res.getInt("cantidadRegistrosTotal"));
                            s.setCantidadRegistrosProcesadosOk(res.getInt("cantidadRegistrosProcesadosOk"));
                            s.setCantidadRegistrosProcesadosError(res.getInt("cantidadRegistrosProcesadosError"));
                            s.setIdentificadorRegistro(res.getInt("identificadorRegistro"));
                            if (s.getCantidadRegistrosProcesadosError() > 0 && s.getEstadoStatus() == 1) {
                                s.setMostrarImagenArchivoError("");
                            } else {
                                s.setMostrarImagenArchivoError("none");
                            }

                            if (s.getEstadoStatus() == 1) {
                                s.setEstado("TERMINADO");
                            } else {
                                s.setEstado("EN PROCESO");
                            }

                            statusCargues.add(s);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(StatusCargueDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(StatusCargueDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return statusCargues;
    }
}
