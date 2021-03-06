/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Defuncion;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.EmpleadoSesion;
import co.com.grupoasd.nomina.modelo.Estado;
import co.com.grupoasd.nomina.modelo.EstadoEmpleado;
import co.com.grupoasd.nomina.modelo.Municipio;
import co.com.grupoasd.nomina.modelo.seguimiento.ResultSetConverter;
import co.com.grupoasd.nomina.modelo.PruebasxPersona;
import co.com.grupoasd.nomina.portal.util.Filtro;
import co.com.grupoasd.nomina.portal.util.Normalizacion;
import java.io.File;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Pedro Rodriguez
 */
public class EmpleadoDao {

    private Operaciones conex = new Operaciones();
    
    public EmpleadoDao() {

    }

    /**
     * Permite registrar un Empleado
     *
     * @param Empleado
     */
    public int insertar(Empleado empleado) {
        //final int idEmpleado = 0;
        final Object[] idEmpleado = new Object[1];
        String sql
                = " INSERT INTO empleado ("
                + " codigoDepartamento,codigoMunicipio,nrodoc,"
                + " tipodoc,apellido1,apellido2,nombre1,"
                + " nombre2,direccion,telefono,celular,"
                + " email,codigoActividad,codigoEstado,usuarioCrea, fechaCrea,"
                + " usuarioModifica, fechaModifica, imagen,observacion,"
                + " fechaNacimiento, fechaIngreso,\n"
                + " fechaUltimaAplicacion, telefono2,\n"
                + " telefono3, telefono4,\n"
                + " telefono5, celular2,\n"
                + " celular3, celular4,\n"
                + " celular5, email2,\n"
                + " email3, email4,\n"
                + " email5, nivelacademico,\n"
                + " promedio, semestre,\n"
                + " codigocarrera, experienciahuellas,\n"
                + " intepretacionsenas, manejodiscapacidad,\n"
                + " bilingue, viajar,\n"
                + " estadoicfes, ultimaevaluacion, cargo,idpuesto)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, current_timestamp,'',current_timestamp,0,' ',?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        conex.ejecutar(new Operaciones.OperacionEjecutar() {

            @Override
            public void llaveGenerada(ResultSet generatedKeys) {
                try {
                    if (generatedKeys.next()) {
                        idEmpleado[0] = (int) generatedKeys.getLong(1);
                    } else {
                        idEmpleado[0] = 0;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, sql, empleado.getMunicipio().getDepartamento().getCodigo(),
                empleado.getMunicipio().getCodigoMunicipio(),
                empleado.getNrodoc(),
                empleado.getTipodoc(),
                empleado.getApellido1().toUpperCase(),
                empleado.getApellido2() != null ? empleado.getApellido2().toUpperCase().trim() : " ",
                empleado.getNombre1().toUpperCase().trim(),
                empleado.getNombre2() != null ? empleado.getNombre2().toUpperCase().trim() : "",
                empleado.getDireccion() != null ? empleado.getDireccion().toUpperCase().trim() : " ",
                empleado.getTelefono(),
                empleado.getCelular(),
                empleado.getEmail() != null ? empleado.getEmail().toUpperCase() : "",
                empleado.getCodigoActividad(),
                empleado.getEstado().getCodigoEstado(),
                empleado.getUsuarioCrea(),
                empleado.getFechaNacimiento(),
                empleado.getFechaIngreso(),
                empleado.getFechaUltimaAplicacion(),
                empleado.getTelefono2(),
                empleado.getTelefono3(),
                empleado.getTelefono4(),
                empleado.getTelefono5(),
                empleado.getCelular2(),
                empleado.getCelular3(),
                empleado.getCelular4(),
                empleado.getCelular5(),
                empleado.getEmail2(),
                empleado.getEmail3(),
                empleado.getEmail4(),
                empleado.getEmail5(),
                empleado.getNivelacademico(),
                empleado.getPromedio(),
                empleado.getSemestre(),
                empleado.getCodigocarrera(),
                empleado.getExperienciahuellas(),
                empleado.getIntepretacionsenas(),
                empleado.getManejodiscapacidad(),
                empleado.getBilingue(),
                empleado.getEstadoicfes(),
                empleado.getUltimaevaluacion(),
                empleado.getCargo(),
                empleado.getIdpuesto()
        );

        return (int) idEmpleado[0];
    }

    /**
     * Permite registrar un Empleado
     *
     * @param Empleado
     */
    public int insertarDatosPrincipales(Empleado empleado) {
        //final int idEmpleado = 0;
        final Object[] idEmpleado = new Object[1];
        String sql
                = " INSERT INTO empleado ("
                + " codigoDepartamento,codigoMunicipio,nrodoc,"
                + " tipodoc,apellido1,apellido2,nombre1,"
                + " nombre2,direccion,celular,"
                + " email,idpuesto,huella,imagenHuella,fecha_huellaimagen, DaneMunicipio)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP(),?)";
        conex.ejecutar(new Operaciones.OperacionEjecutar() {

            @Override
            public void llaveGenerada(ResultSet generatedKeys) {
                try {
                    if (generatedKeys.next()) {
                        idEmpleado[0] = (int) generatedKeys.getLong(1);
                    } else {
                        idEmpleado[0] = 0;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, sql, empleado.getMunicipio().getDepartamento().getCodigo(),
                empleado.getMunicipio().getCodigoMunicipio(),
                empleado.getNrodoc(),
                empleado.getTipodoc(),
                empleado.getApellido1().toUpperCase(),
                empleado.getApellido2() != null ? empleado.getApellido2().toUpperCase().trim() : " ",
                empleado.getNombre1().toUpperCase().trim(),
                empleado.getNombre2() != null ? empleado.getNombre2().toUpperCase().trim() : "",
                empleado.getDireccion() != null ? empleado.getDireccion().toUpperCase().trim() : " ",
                empleado.getCelular(),
                empleado.getEmail() != null ? empleado.getEmail().toUpperCase() : "",
                empleado.getIdpuesto(),
                empleado.getHuella(),
                empleado.getImagenHuella(),
                empleado.getMunicipio().getCodigoMunicipio()
        );

        return (int) idEmpleado[0];
    }

    /**
     * Permite registrar un Empleado con los datos basicos y el municipio seg??n
     * el dane
     *
     * @param empleado
     */
    public int insertarDatosBasicos(Empleado empleado) {

        //final int idEmpleado = 0;
        final Object[] idEmpleado = new Object[1];
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO empleado (codigoDepartamento,codigoMunicipio,nrodoc,tipodoc,");
        sql.append("apellido1,apellido2,nombre1, nombre2,direccion,celular, email,DaneMunicipio, id_operador,");
        sql.append("usuarioCrea, fechaCrea, usuarioModifica, fechaNacimiento, genero, telefono, codigoActividad, idzona, idpuesto, id_municipio_rut)");
        sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,current_timestamp,?,?,?,?,?,?,?,?)");
        conex.ejecutar(new Operaciones.OperacionEjecutar() {

            @Override
            public void llaveGenerada(ResultSet generatedKeys) {
                try {
                    if (generatedKeys.next()) {
                        idEmpleado[0] = (int) generatedKeys.getLong(1);
                    } else {
                        idEmpleado[0] = 0;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, sql.toString(), empleado.getMunicipioDane().getDepartamento().getCodigo(),
                empleado.getMunicipioDane().getCodigoMunicipio(),
                empleado.getNrodoc(),
                empleado.getTipodoc(),
                empleado.getApellido1().toUpperCase(),
                empleado.getApellido2() != null ? empleado.getApellido2().toUpperCase().trim() : " ",
                empleado.getNombre1().toUpperCase().trim(),
                empleado.getNombre2() != null ? empleado.getNombre2().toUpperCase().trim() : "",
                empleado.getDireccion() != null ? empleado.getDireccion().toUpperCase().trim() : " ",
                empleado.getCelular(),
                empleado.getEmail() != null ? empleado.getEmail().toUpperCase() : "",
                empleado.getMunicipioDane().getCodigoMunicipio(),
                empleado.getIdOperador(),
                empleado.getUsuarioCrea(),
                empleado.getUsuarioCrea(),
                empleado.getFechaNacimiento(),
                empleado.getGenero(),
                empleado.getTelefono(),
                empleado.getCodigoActividad(),
                empleado.getIdmediopago(),
                empleado.getIdpuesto(),
                empleado.getMunicipioRut()!=null?empleado.getMunicipioRut().getCodigoMunicipio():""
        );

        return (int) idEmpleado[0];
    }

    /**
     * Permite registrar un Empleado con huella desde el rol RDS
     *
     * @param Empleado
     */
    public int insertarDatosPrincipalesRDS(Empleado empleado) {
        //final int idEmpleado = 0;
        final Object[] idEmpleado = new Object[1];
        String sql
                = " INSERT INTO empleado ("
                + " codigoDepartamento,codigoMunicipio,nrodoc,"
                + " tipodoc,apellido1,apellido2,nombre1,"
                + " nombre2,direccion,celular,"
                + " email,idpuesto,huella,imagenHuella,genero, fechaNacimiento,fecha_huellaimagen, DaneMunicipio)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP(), ?)";
        conex.ejecutar(new Operaciones.OperacionEjecutar() {

            @Override
            public void llaveGenerada(ResultSet generatedKeys) {
                try {
                    if (generatedKeys.next()) {
                        idEmpleado[0] = (int) generatedKeys.getLong(1);
                    } else {
                        idEmpleado[0] = 0;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, sql, empleado.getMunicipio().getDepartamento().getCodigo(),
                empleado.getMunicipio().getCodigoMunicipio(),
                empleado.getNrodoc(),
                empleado.getTipodoc(),
                empleado.getApellido1().toUpperCase(),
                empleado.getApellido2() != null ? empleado.getApellido2().toUpperCase().trim() : " ",
                empleado.getNombre1().toUpperCase().trim(),
                empleado.getNombre2() != null ? empleado.getNombre2().toUpperCase().trim() : "",
                empleado.getDireccion() != null ? empleado.getDireccion().toUpperCase().trim() : " ",
                empleado.getCelular(),
                empleado.getEmail() != null ? empleado.getEmail().toUpperCase() : "",
                empleado.getIdpuesto(),
                empleado.getHuella(),
                empleado.getImagenHuella(),
                empleado.getGenero(),
                empleado.getFechaNacimiento(),
                empleado.getMunicipio().getCodigoMunicipio()
        );

        return (int) idEmpleado[0];
    }

    // Idzona es el idmedio de pago
    public Boolean actualizar(Empleado empleado) {
        Boolean resultado = false;

        String sql
                = " UPDATE empleado SET "
                + " codigoDepartamento=?, codigoMunicipio=?, DaneMunicipio=?, nrodoc =?,"
                + " tipodoc = ?,apellido1=?,apellido2=?,nombre1=?,"
                + " nombre2=?,direccion=?,telefono=?,celular=?,"
                + " email=?,codigoEstado=?, "
                + " usuarioModifica=?, fechaModifica = current_timestamp, imagen = ?, observacion =?, "
                + " fechaNacimiento=?, fechaIngreso=?, "
                + " fechaUltimaAplicacion=?, "
                + " estadoicfes=?, ultimaevaluacion=?, cargo=? , idpuesto = ?,"
                + " idzona=?, genero=?, codigoActividad=?, id_operador=?, id_municipio_rut=? "
                + " WHERE idEmpleado = ? ";

        int estado;
        Empleado empleadoBD = this.GetByIdSinImagenHuella(empleado.getIdEmpleado());
        if (empleadoBD.getIdmediopago() == empleado.getIdmediopago()) {
            estado = empleado.getEstado().getCodigoEstado() != 0 ? 1 : 0;
        } else {
            estado = empleado.getEstado().getCodigoEstado();
        }

        resultado = conex.ejecutar(sql,
                empleado.getMunicipioDane().getDepartamento().getCodigo(),
                empleado.getMunicipioDane().getCodigoMunicipio(),
                empleado.getMunicipioDane().getCodigoMunicipio(),
                empleado.getNrodoc(),
                empleado.getTipodoc(),
                empleado.getApellido1().toUpperCase(),
                empleado.getApellido2() != null ? empleado.getApellido2().toUpperCase() : " ",
                empleado.getNombre1().toUpperCase(),
                empleado.getNombre2() != null ? empleado.getNombre2().toUpperCase() : " ",
                empleado.getDireccion() != null ? empleado.getDireccion().toUpperCase() : "",
                empleado.getTelefono(),
                empleado.getCelular(),
                empleado.getEmail() != null ? empleado.getEmail().toUpperCase() : "",
                estado,
                empleado.getUsuarioCrea(),
                empleado.isImagen(),
                empleado.getObservacion(),
                empleado.getFechaNacimiento(),
                empleado.getFechaIngreso(),
                empleado.getFechaUltimaAplicacion(),
                empleado.getEstadoIcfes().getCodigoEstado(),
                empleado.getUltimaevaluacion(),
                empleado.getCargo(),
                empleado.getIdpuesto(),
                empleado.getIdmediopago(),
                empleado.getGenero(),
                empleado.getCodigoActividad(),
                empleado.getIdOperador(),
                empleado.getMunicipioRut().getCodigoMunicipio(),
                empleado.getIdEmpleado());

        return resultado;
    }

    public Boolean actualizarDatosPrincipales(Empleado empleado) {
        Boolean resultado = false;

        String sql
                = " UPDATE empleado SET "
                + " codigoDepartamento =?, codigoMunicipio =?, nrodoc =?,"
                + " tipodoc = ?,apellido1=?,apellido2=?,nombre1=?,"
                + " nombre2=?,direccion=?,telefono=?,celular=?, "
                + " email=? , idpuesto = ? "
                + " WHERE idEmpleado = ? ";

        resultado = conex.ejecutar(sql,
                empleado.getMunicipio().getDepartamento().getCodigo(),
                empleado.getMunicipio().getCodigoMunicipio(),
                empleado.getNrodoc(),
                empleado.getTipodoc(),
                empleado.getApellido1().toUpperCase(),
                empleado.getApellido2() != null ? empleado.getApellido2().toUpperCase() : " ",
                empleado.getNombre1().toUpperCase(),
                empleado.getNombre2() != null ? empleado.getNombre2().toUpperCase() : " ",
                empleado.getDireccion() != null ? empleado.getDireccion().toUpperCase() : "",
                empleado.getTelefono(),
                empleado.getCelular(),
                empleado.getEmail() != null ? empleado.getEmail().toUpperCase() : "",
                empleado.getIdpuesto(),
                empleado.getIdEmpleado());
        return resultado;
    }

    public Boolean actualizarDatosPrincipalesPistoleo(Empleado empleado) {
        Boolean resultado = false;

        String sql
                = " UPDATE empleado SET "
                + " apellido1=?, apellido2=?, nombre1=?, "
                + " nombre2=?, genero=?, fechaNacimiento=? "
                + " WHERE idEmpleado = ? ";

        resultado = conex.ejecutar(sql,
                empleado.getApellido1().toUpperCase(),
                empleado.getApellido2() != null ? empleado.getApellido2().toUpperCase() : " ",
                empleado.getNombre1().toUpperCase(),
                empleado.getNombre2() != null ? empleado.getNombre2().toUpperCase() : " ",
                empleado.getGenero(),
                empleado.getFechaNacimiento(),
                empleado.getIdEmpleado());
        return resultado;
    }

    public Boolean actualizarDatosPrincipalesRDS(Empleado empleado) {
        Boolean resultado = false;

        String sql
                = " UPDATE empleado SET "
                + " codigoDepartamento =?, codigoMunicipio =?, nrodoc =?,"
                + " tipodoc = ?,apellido1=?,apellido2=?,nombre1=?,"
                + " nombre2=?,direccion=?,telefono=?,celular=?, "
                + " email=? , idpuesto = ?, huella = ?,imagenHuella=?, genero=?, fechaNacimiento=?,fecha_huellaimagen=CURRENT_TIMESTAMP(), "
                + " DaneMunicipio = ? "
                + " WHERE idEmpleado = ? ";

        resultado = conex.ejecutar(sql,
                empleado.getMunicipio().getDepartamento().getCodigo(),
                empleado.getMunicipio().getCodigoMunicipio(),
                empleado.getNrodoc(),
                empleado.getTipodoc(),
                empleado.getApellido1().toUpperCase(),
                empleado.getApellido2() != null ? empleado.getApellido2().toUpperCase() : " ",
                empleado.getNombre1().toUpperCase(),
                empleado.getNombre2() != null ? empleado.getNombre2().toUpperCase() : " ",
                empleado.getDireccion() != null ? empleado.getDireccion().toUpperCase() : "",
                empleado.getTelefono(),
                empleado.getCelular(),
                empleado.getEmail() != null ? empleado.getEmail().toUpperCase() : "",
                empleado.getIdpuesto(),
                empleado.getHuella(),
                empleado.getImagenHuella(),
                empleado.getGenero(),
                empleado.getFechaNacimiento(),
                empleado.getMunicipio().getCodigoMunicipio(),
                empleado.getIdEmpleado());
        return resultado;
    }

    public Boolean actualizarDatosBasicos(Empleado empleado) {
        Boolean resultado = false;
        Municipio m = new MunicipioDao().GetByCodigo(empleado.getMunicipioDane().getCodigoMunicipio());
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE empleado SET ");
        sql.append(" codigoDepartamento =?, codigoMunicipio =?, nrodoc =?,");
        sql.append(" tipodoc = ?,apellido1=?,apellido2=?,nombre1=?,");
        sql.append("nombre2=?,direccion=?,telefono=?,celular=?,");
        sql.append("email=? , genero=?, fechaNacimiento=?, DaneMunicipio=?");
        sql.append(" WHERE idEmpleado = ? ");

        resultado = conex.ejecutar(sql.toString(),
                m.getDepartamento().getCodigo(),
                m.getCodigoMunicipio(),
                empleado.getNrodoc(),
                empleado.getTipodoc(),
                empleado.getApellido1().toUpperCase(),
                empleado.getApellido2() != null ? empleado.getApellido2().toUpperCase().trim() : " ",
                empleado.getNombre1().toUpperCase().trim(),
                empleado.getNombre2() != null ? empleado.getNombre2().toUpperCase().trim() : "",
                empleado.getDireccion() != null ? empleado.getDireccion().toUpperCase().trim() : " ",
                empleado.getTelefono(),
                empleado.getCelular(),
                empleado.getEmail() != null ? empleado.getEmail().toUpperCase() : "",
                empleado.getGenero(),
                empleado.getFechaNacimiento(),
                empleado.getMunicipioDane().getCodigoMunicipio(),
                empleado.getIdEmpleado());
        return resultado;
    }

    public Boolean actualizarEstadoEmpleado(int estado, int idEmpleado, String observaciones) {
        Boolean resultado = false;

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE empleado SET ");
        sql.append(" codigoEstado=?, observacion=? ");
        sql.append(" WHERE idEmpleado = ? ");

        resultado = conex.ejecutar(sql.toString(),
                estado,
                observaciones,
                idEmpleado);
        return resultado;
    }

    public List<Empleado> listar(String codigoDepartamento) {
        final List<Empleado> Empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleado where codigoDepartamento = '" + codigoDepartamento + "'";
        try {
            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Empleado empleado = new Empleado();

                            empleado.setIdEmpleado(res.getInt("idEmpleado"));

                            empleado.setMunicipio(new MunicipioDao().GetById(res.getString("codigoDepartamento"), res.getString("codigoMunicipio")));
                            empleado.setNrodoc(res.getLong("nrodoc"));
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
                            empleado.setUsuarioModifica(res.getString("usuarioModifica"));
                            empleado.setFechaModifica(res.getDate("fechaModifica"));
                            empleado.setImagen(res.getBoolean("imagen"));

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
                            empleado.setGenero(res.getString("genero"));
                            Empleados.add(empleado);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Empleados;
    }

    private String formatearFecha(String fecha) {
        String fechaOut = "1900/01/01";
        String fechaIn = fecha;
        try {
            Locale currentLocale = new Locale("us", "US");
            java.util.Date fechaDate;

            //1979-11-10T05:00:00.000Z
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", currentLocale);
            fechaDate = formatter.parse(fechaIn);

            formatter = new SimpleDateFormat("yyy/MM/dd", currentLocale);
            fechaOut = formatter.format(fechaDate);

        } catch (ParseException ex) {
            Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fechaOut;
    }

    public List<Empleado> listar(String codigoDepartamento, int codigoEstado) {
        final List<Empleado> Empleados = new ArrayList<>();

        try {

            String sql;

            if (codigoEstado == 0) {
                sql = "SELECT *, (case when codigoestado = 0 then 'EN PROCESO' else 'INCONSISTENTE' end ) as estado FROM empleado where codigoDepartamento = '" + codigoDepartamento + "' and codigoEstado in(0,3) ";
            } else {
                sql = "SELECT * FROM empleado where codigoDepartamento = '" + codigoDepartamento + "' and codigoEstado = " + codigoEstado;
            }

            conex.consultar(sql,
                    new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Empleado empleado = new Empleado();

                            empleado.setIdEmpleado(res.getInt("idEmpleado"));

                            empleado.setMunicipio(new MunicipioDao().GetById(res.getString("codigoDepartamento"), res.getString("codigoMunicipio")));
                            empleado.setNrodoc(res.getLong("nrodoc"));
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
                            empleado.setUsuarioModifica(res.getString("usuarioModifica"));
                            empleado.setFechaModifica(res.getDate("fechaModifica"));
                            empleado.setImagen(res.getBoolean("imagen"));

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

                            Empleados.add(empleado);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Empleados;
    }

    public String listarJson(String codigoDepartamento, int codigoEstado) {

        final Object[] resultado = new Object[1];
        JSONArray json = null;
        try {
            String sql;
            if (codigoEstado == 0) {
                sql = "SELECT * , (case when codigoestado = 0 then 'EN PROCESO' else 'INCONSISTENTE' end ) as estado FROM empleado where codigoDepartamento = '" + codigoDepartamento + "' and codigoEstado in(0,3) ";
            } else {
                sql = "SELECT * FROM empleado where codigoDepartamento = '" + codigoDepartamento + "' and codigoEstado = " + codigoEstado + " ";
            }

            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        resultado[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });

            json = (JSONArray) resultado[0];

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    public String listarJson(String codigoDepartamento, int nrodoc, String apellido1, String apellido2, String nombre1, String nombre2, String idpuesto) {
        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            String sql;
            String sqlWhere = "";

            sql = "SELECT * FROM empleado where codigoDepartamento = '" + codigoDepartamento + "' and ";

            if (nrodoc > 0) {
                sqlWhere += " and nrodoc like '%" + nrodoc + "%' ";
            }

            if (!apellido1.equals("undefined")) {
                sqlWhere += " and apellido1 like '%" + apellido1 + "%' ";
            }

            if (!apellido2.equals("undefined")) {
                sqlWhere += " and apellido2 like '%" + apellido2 + "%' ";
            }

            if (!nombre1.equals("undefined")) {
                sqlWhere += " and nombre1 like '%" + nombre1 + "%' ";
            }

            if (!nombre2.equals("undefined")) {
                sqlWhere += " and nombre2 like '%" + nombre2 + "%' ";
            }

            if (!idpuesto.equals("undefined")) {
                sqlWhere += " and idpuesto like '%" + idpuesto + "%' ";
            }

            if (sqlWhere.length() > 2) {
                sql += sqlWhere.substring(4);
            }

            conex.consultar(sql, new Operaciones.OperacionConsulta() {

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
            });

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    public String listarJsonPorSitio(String idpuesto, int idPrueba) {
        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            String sql;

            sql = " select * "
                    + " from empleado  "
                    + " where idpuesto = '" + idpuesto + "' "
                    + " and nrodoc not in( "
                    + " select nrodoc "
                    + " from nombramiento nom left join divipol divi "
                    + " on nom.idDivipol = divi.idDivipol "
                    + " where divi.idPrueba = " + idPrueba
                    + " and divi.codigoPuesto = '" + idpuesto + "' "
                    + " and nrodoc > 0) ";

            conex.consultar(sql, new Operaciones.OperacionConsulta() {

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
            });

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(SeguimientoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    public Empleado GetById(Long id) {

        final Empleado empleado = new Empleado();
        try {

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            empleado.setIdEmpleado(res.getInt("idEmpleado"));
                            empleado.setMunicipio(new MunicipioDao().GetById(res.getString("codigoDepartamento"), res.getString("codigoMunicipio")));
                            empleado.setMunicipioDane(new MunicipioDaneDao().GetMunicipioDaneById(res.getString("DaneMunicipio")));
                            empleado.setNrodoc(res.getLong("nrodoc"));
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
                            empleado.setEstadoIcfes(new EstadoEmpleadoDao().GetById(res.getInt("estadoicfes")));
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
                            empleado.setCargoobj(new CargoDao().GetById(res.getString("cargo")));
                            empleado.setImgdociden(res.getInt("imgdociden"));
                            empleado.setImgcertestudio(res.getInt("imgcertestudio"));
                            empleado.setImgafiliaeps(res.getInt("imgafiliaeps"));
                            empleado.setImgrut(res.getInt("imgrut"));
                            empleado.setIdpuesto(res.getString("idpuesto"));
                            empleado.setGenero(res.getString("genero"));
                            empleado.setHuella(res.getBytes("huella"));
                            empleado.setImagenHuella(res.getBytes("imagenHuella"));
                            empleado.setLocalidad(new LocalidadDaneDao().GetLocalidadDaneById(res.getInt("idLocalidad")));
                            empleado.setZona(new ZonaIcfesDao().GetZonaById(res.getInt("idzona")));
                            empleado.setTipoBase(res.getString("tipo_base"));

                            if (empleado.getHuella() != null) {
                                empleado.setHuellaBase64(Base64.getEncoder().encodeToString(empleado.getHuella()));
                            }

                            if (empleado.getImagenHuella() != null) {
                                empleado.setImagenHuellaBase64(Base64.getEncoder().encodeToString(empleado.getImagenHuella()));
                            }

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            },
                    "SELECT * FROM empleado where idEmpleado = ?", id);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return empleado;
    }

    public Empleado GetById(int id) {

        final Empleado empleado = new Empleado();
        try {

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            empleado.setIdEmpleado(res.getInt("idEmpleado"));
                            empleado.setMunicipio(new MunicipioDao().GetById(res.getString("codigoDepartamento"), res.getString("codigoMunicipio")));
                            empleado.setMunicipioDane(new MunicipioDaneDao().GetMunicipioDaneById(res.getString("DaneMunicipio")));
                            empleado.setMunicipioRut(new MunicipioDaneDao().GetMunicipioDaneById(res.getString("id_municipio_rut")));
                            empleado.setNrodoc(res.getLong("nrodoc"));
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
                            empleado.setEstadoIcfes(new EstadoEmpleadoDao().GetById(res.getInt("estadoicfes")));
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
                            empleado.setCargoobj(new CargoDao().GetById(res.getString("cargo")));
                            empleado.setImgdociden(res.getInt("imgdociden"));
                            empleado.setImgcertestudio(res.getInt("imgcertestudio"));
                            empleado.setImgafiliaeps(res.getInt("imgafiliaeps"));
                            empleado.setImgrut(res.getInt("imgrut"));
                            empleado.setIdpuesto(res.getString("idpuesto"));
                            empleado.setIdmediopago(res.getInt("idzona"));
                            empleado.setGenero(res.getString("genero"));
                            empleado.setHuella(res.getBytes("huella"));
                            empleado.setImagenHuella(res.getBytes("imagenHuella"));
                            empleado.setLocalidad(new LocalidadDaneDao().GetLocalidadDaneById(res.getInt("idLocalidad")));
                            empleado.setZona(new ZonaIcfesDao().GetZonaById(res.getInt("idzona")));
                            empleado.setTipoBase(res.getString("tipo_base"));
                            empleado.setIdOperador(res.getInt("id_operador"));
                            if (empleado.getHuella() != null) {
                                empleado.setHuellaBase64(Base64.getEncoder().encodeToString(empleado.getHuella()));
                            }

                            if (empleado.getImagenHuella() != null) {
                                empleado.setImagenHuellaBase64(Base64.getEncoder().encodeToString(empleado.getImagenHuella()));
                            }

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            },
                    "SELECT * FROM empleado where idEmpleado = ?", id);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return empleado;
    }

    public Empleado GetByIdSinImagenHuella(int id) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT em.idEmpleado, em.DaneMunicipio, em.nrodoc, em.tipodoc, em.nombre1, em.nombre2, em.codigoDepartamento, em.codigoMunicipio, ");
        sql.append("em.apellido1, em.apellido2, em.direccion, em.direccion, em.telefono, em.celular, em.email, em.estadoicfes, em.observacion, em.huella,");
        sql.append("em.codigoActividad, em.observacion, em.fechaNacimiento, em.imgdociden, em.imgrut, em.genero, em.codigoEstado, em.idzona, em.idpuesto, ");
        sql.append(" em.usuarioCrea, em.fechaCrea,em.fechaModifica, em.usuarioModifica, em.usuarioModifica,em.codigoEstado, em.id_municipio_rut ");
        sql.append(" FROM empleado em inner join tipo_iden t on t.codigo=em.tipodoc where idEmpleado = ? ");
        final Empleado empleado = new Empleado();
        try {

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            empleado.setIdEmpleado(res.getInt("idEmpleado"));
                            empleado.setMunicipio(new MunicipioDao().GetById(res.getString("codigoDepartamento"), res.getString("codigoMunicipio")));
                            empleado.setMunicipioDane(new MunicipioDaneDao().GetMunicipioDaneById(res.getString("DaneMunicipio")));
                            empleado.setMunicipioRut(new MunicipioDaneDao().GetMunicipioDaneById(res.getString("id_municipio_rut")));
                            empleado.setNrodoc(res.getLong("nrodoc"));
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
                            empleado.setObservacion(res.getString("observacion"));
                            empleado.setFechaNacimiento(res.getDate("fechaNacimiento"));
                            empleado.setImgdociden(res.getInt("imgdociden"));
                            empleado.setImgrut(res.getInt("imgrut"));
                            empleado.setGenero(res.getString("genero"));
                            empleado.setEstado(new EstadoDao().GetById(res.getString("codigoEstado")));
                            empleado.setIdmediopago(res.getInt("idzona"));
                            empleado.setIdpuesto(res.getString("idpuesto"));
                            empleado.setUsuarioCrea(res.getString("usuarioCrea"));
                            empleado.setFechaCrea(res.getDate("fechaCrea"));
                            empleado.setUsuarioModifica(res.getString("usuarioModifica"));
                            empleado.setFechaModifica(res.getDate("fechaModifica"));
                            empleado.setEstado(new EstadoDao().GetById(res.getString("codigoEstado")));
                            empleado.setEstadoicfes(res.getInt("estadoicfes"));
                            empleado.setEstadoIcfes(new EstadoEmpleadoDao().GetById(res.getInt("estadoicfes")));
                            empleado.setObservacion(res.getString("observacion"));
                            empleado.setHuella(res.getBytes("huella"));

                            if (empleado.getHuella() != null) {
                                empleado.setHuellaBase64(Base64.getEncoder().encodeToString(empleado.getHuella()));
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString(), id);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return empleado;
    }

    public Empleado GetByNumeroDocumento2(long nroDocumento, String tipoDoc) {

        final Empleado empleado = new Empleado();
        try {
            String sql = "SELECT idEmpleado FROM empleado where nrodoc = ? and tipodoc= ?";
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            empleado.setIdEmpleado(res.getInt("idEmpleado"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql,
                    nroDocumento,
                    tipoDoc);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return empleado;
    }

    public Empleado GetByNumeroDocumento(long nroDocumento, String codigoDepartamento) {

        final Empleado empleado = new Empleado();
        try {
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            empleado.setIdEmpleado(res.getInt("idEmpleado"));
                            empleado.setMunicipio(new MunicipioDao().GetByCodigo(res.getString("codigoMunicipio")));
                            empleado.setMunicipioDane(new MunicipioDaneDao().GetMunicipioDaneById(res.getString("DaneMunicipio")));
                            empleado.setNrodoc(res.getLong("nrodoc"));
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
                            empleado.setHuella(res.getBytes("huella"));
                            if (empleado.getHuella() != null) {
                                empleado.setHuellaBase64(Base64.getEncoder().encodeToString(empleado.getHuella()));
                            }

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, "SELECT * FROM empleado where nrodoc = ?",
                    nroDocumento);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return empleado;
    }

    public int GetIdByNumeroDocumento(long nroDocumento) {

        final Object id[] = new Object[1];
        id[0] = 0;
        try {
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            id[0] = res.getInt("idEmpleado");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, "SELECT idEmpleado FROM empleado where nrodoc = ?", nroDocumento);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return (int) id[0];
    }

    public Boolean ActulizarImagen(int id, String campo) {
        Boolean resultado = false;

        resultado = conex.ejecutar(
                " UPDATE empleado SET " + campo + " = 1 "
                + " WHERE idEmpleado = ? ", id);

        return resultado;
    }

    public EmpleadoSesion obtenerEmpleadoSesion(String cedula) {

        final EmpleadoSesion empleado = new EmpleadoSesion();
        final ArrayList<Long> sitios = new ArrayList<Long>();
        final ArrayList<PruebasxPersona> pruebasUsuario = new ArrayList<PruebasxPersona>();

        StringBuilder sb = new StringBuilder();
        StringBuilder sitiosQuery = new StringBuilder();
        StringBuilder pruebas = new StringBuilder();
        sb.append("select idEmpleado,apellido1,apellido2,nombre1,nombre2,email,nrodoc,bloqueado from empleado where nrodoc = '");
        sb.append(cedula);
        sb.append("';");
        sitiosQuery.append("select iddivipol from usuario_sitio where usuario = ");
        sitiosQuery.append(cedula);

        pruebas.append("select grupo,idprueba from usuario_grupo where usuario = ");
        pruebas.append(cedula);
        pruebas.append(" and activo = 1");

        try {
            conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {

                        if (res.first()) {
                            int col = 0;
                            empleado.setIdEmpleado(res.getInt(++col));
                            empleado.setApellido1(res.getString(++col));
                            empleado.setApellido2(res.getString(++col));
                            empleado.setNombre1(res.getString(++col));
                            empleado.setNombre2(res.getString(++col));
                            empleado.setEmail(res.getString(++col));
                            empleado.setNrodoc(String.valueOf(res.getInt(++col)));
                            empleado.setBloqueado(res.getInt(++col));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            conex.consultar(sitiosQuery.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet resultado) {
                    try {
                        resultado.beforeFirst();
                        while (resultado.next()) {
                            sitios.add(resultado.getLong(1));
                        }
                        empleado.setSitios(sitios);
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (Exception e) {
            Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            conex.consultar(pruebas.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet resultado) {
                    try {
                        resultado.beforeFirst();
                        while (resultado.next()) {
                            PruebasxPersona pruebaU = new PruebasxPersona();
                            pruebaU.setGrupo(resultado.getString(1));
                            pruebaU.setPrueba(resultado.getInt(2));
                            pruebasUsuario.add(pruebaU);
                        }
                        empleado.setPruebas(pruebasUsuario);
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (Exception e) {
            Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return empleado;
    }

    /**
     * Se actualiza la huella y la imagen de un empleado
     *
     * @param idEmpleado
     * @param huella
     * @param imagen
     * @return
     */
    public boolean actualizarHuella(int idEmpleado, byte[] huella, byte[] imagen) {
        Boolean resultado = false;

        resultado = conex.ejecutar(
                " UPDATE empleado SET huella=?, imagenHuella=?,fecha_huellaimagen=CURRENT_TIMESTAMP() "
                + " WHERE idEmpleado = ? ",
                huella,
                imagen,
                idEmpleado);
        return resultado;
    }

    /**
     * Trae todos los usuarios habilitados para una prueba y un sitio
     *
     * @param idEvento
     * @param idDivipol
     * @return
     */
    public List<Empleado> GetHuellasByEventoYDivipol(int idEvento, int idDivipol) {

        final List<Empleado> lstEmpleados = new ArrayList<>();
        conex.consultar(new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet result) {
                try {
                    while (result.next()) {
                        Empleado empleado = new Empleado();
                        empleado.setIdEmpleado(result.getInt("idempleado"));
                        empleado.setNrodoc(result.getInt("nrodoc"));
                        empleado.setHuella(result.getBytes("huella"));
                        empleado.setIdDivipolAsistencia(result.getInt("iddivipol"));
                        lstEmpleados.add(empleado);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, "SELECT emp.idempleado, emp.nrodoc, emp.huella, det.iddivipol   "
                + "FROM detalle_cargo_x_puesto_x_evento det "
                + "inner join empleado emp on emp.nrodoc=det.nrodoc "
                + "where codigoEvento = ? and idDivipol= ?"
                + " and emp.huella  is not null ",
                idEvento,
                idDivipol);

        return lstEmpleados;

    }

    public Empleado getHuellaEmpleadoById(int id) {

        final Empleado empleado = new Empleado();
        try {

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {

                            empleado.setNrodoc(res.getInt("nrodoc"));
                            empleado.setHuella(res.getBytes("huella"));

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            },
                    "SELECT emp.nrodoc, emp.huella   "
                    + "FROM empleado emp "
                    + "where idEmpleado = ? ", id);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return empleado;
    }

    public Empleado obtenerCargoEmpleadoSesion(int id) {

        final Empleado empleado = new Empleado();
        StringBuilder sb = new StringBuilder();
        sb.append("select e.idEmpleado, epe.codigoCargo from empleado e  INNER JOIN empleado_x_prueba_x_estado epe ON epe.idempleado=e.idEmpleado where e.idEmpleado=");
        sb.append(id);
        sb.append(";");

        try {
            conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            empleado.setIdEmpleado(res.getInt("idEmpleado"));
                            empleado.setCargo(res.getInt("codigoCargo"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return empleado;
    }

    public String listarHistorialArchivosEmpleado(int idEmpleado, String tipoDocumento) {

        final JSONArray json = new JSONArray();
        String ubicacionArchivo = "/data/Elecciones/img/" + tipoDocumento;
        File directorio = new File(ubicacionArchivo);
        try {

            String[] lista;
            lista = directorio.list(new Filtro(idEmpleado + ""));
            if (lista.length >= 0) {
                for (int i = 0; i < lista.length; i++) {

                    JSONObject sitio = new JSONObject();
                    File fichero = new File(ubicacionArchivo, lista[i]);
                    sitio.put("fecha", fichero.lastModified());
                    sitio.put("nombre", lista[i]);
                    json.put(sitio);
                }
            }

        } catch (Exception e) {
            Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    public List<Empleado> filtrarPorGeolocalizacion(int tipoFiltro, int departamento, int nivelCargo, int prueba) {

        final List<Empleado> empleados = new ArrayList<>();

        try {
            // 1 - total sitios, 2 - Total geolocalizados, 2 - Total ubicados
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT e.idEmpleado, e.nrodoc, e.apellido1, e.direccion, e.daneMunicipio, barrio  ");
            sql.append("FROM empleado e INNER JOIN municipio_dane m ON e.DaneMunicipio = m.codigoMunicipio ");
            sql.append("INNER JOIN empleado_x_prueba_x_estado ep ON e.idEmpleado = ep.idEmpleado ");
            sql.append("WHERE ep.idPrueba = ").append(prueba).append(" and ep.codigoCargo IN (SELECT codigoCargo FROM cargos WHERE nivel_cargo = ").append(nivelCargo).append(") ");
            sql.append("AND m.codigoDepartamento = ");
            sql.append(departamento);
            switch (tipoFiltro) {
                case 1:
                    sql.append(" and latitud = 0 and longitud = 0 and confirmado != '2' and direccion != ''"); // No geolocalizados
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
                            Empleado empleado = new Empleado();
                            empleado.setIdEmpleado(res.getInt("idEmpleado"));
                            empleado.setNrodoc(res.getLong("nrodoc"));
                            empleado.setApellido1(res.getString("apellido1"));
                            empleado.setDireccion(Normalizacion.normalizarDireccion(res.getString("direccion")));
                            empleado.setBarrio(res.getString("barrio"));
                            //empleado.setMunicipio(new DepartamentoDao().GetById(res.getString("codigodepartamento")));
                            empleado.setMunicipioDane(new MunicipioDaneDao().GetMunicipioDaneById(res.getString("daneMunicipio")));
                            empleados.add(empleado);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return empleados;
    }

    public int getTotalGeolocalizados(int tipoFiltro, int departamento, int nivelCargo, int prueba) {
        // 1 - total sitios, 2 - Total geolocalizados, 3 - Total confirmados, 4 - Total no ubicados
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT count(0) as cantidad ");
        sql.append("FROM empleado e INNER JOIN municipio_dane m ON e.DaneMunicipio = m.codigoMunicipio ");
        sql.append("INNER JOIN empleado_x_prueba_x_estado ep ON e.idEmpleado = ep.idEmpleado ");
        sql.append("where ep.idPrueba = ? and ep.codigoCargo IN (SELECT codigoCargo FROM cargos WHERE nivel_cargo = ?) and ");
        sql.append("e.direccion != '' and m.codigoDepartamento = ? ");

        final Object id[] = new Object[1];
        id[0] = 0;

        switch (tipoFiltro) {
            case 1:
                break;
            case 2:
                sql.append("and latitud !=0 and longitud !=0");
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
            }, sql.toString(), prueba, nivelCargo, departamento);

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return (int) id[0];
    }

    public boolean actualizarCoordenadas(Empleado empleado) {

        boolean inserto;
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE empleado SET ");
        sql.append(" latitud=?, longitud=?, confirmado=? ");
        sql.append(" where idEmpleado=?");

        inserto = conex.ejecutar(sql.toString(),
                empleado.getLatitud(),
                empleado.getLongitud(),
                empleado.getConfirmado(),
                empleado.getIdEmpleado()
        );

        return inserto;
    }

    /**
     * Metodo recursivo que permite relizar la asignacion de personas de forma
     * automatica
     *
     * @param distancia, distancia por la cual se debe realizar la busqueda. Su
     * valor inicial es 0.5 millas. Por cada iteraci??n aumenta 0.5 millas su
     * valor.
     * @param nivelCargo
     * @return
     */
    public boolean asignarMasivo(float dist, int nivelCarg, int idPrueba, int codigoMunicipio) {

        final StringBuilder sqlEmpleados = new StringBuilder("");

        final float distancia = dist;
        final int nivelCargo = nivelCarg;
        final int prueba = idPrueba;
        final int municipio = codigoMunicipio;

        // Consulta que permite obtener los sitios que tiene disponibilidad.
        // Incluye el id del sitio, la latitud, la longitus y la cantidad de vacantes
        String sqlSitios = "SELECT n.idDivipol, d.latitud, d.longitud, COUNT(*) AS cantidad \n"
                + "FROM nombramiento n inner join divipol d on n.idDivipol = d.idDivipol \n"
                + "WHERE n.codigoMunicipio = " + municipio + " AND \n"
                + "n.idPrueba = " + prueba + " AND \n"
                + "n.codigoCargo IN (SELECT codigoCargo FROM cargos WHERE nivel_cargo = " + nivelCargo + ") AND \n"
                + "(n.nrodoc IS NULL or n.nrodoc = 0) \n"
                + "GROUP BY n.idDivipol \n"
                + "HAVING COUNT(*) > 0 ";

        try {
            conex.consultar(sqlSitios, new Operaciones.OperacionConsulta() {
                //SitioDao sDao = new SitioDao();
                boolean asignoEmpleado = false;
                boolean haySitiosDisponibles = false;

                @Override
                public void respuestaConsulta(final ResultSet res) {
                    try {
                        while (res.next()) {
                            // Consulta que permite obtener las personas mas cercanas de acuerdo a las corrdenadas del sitio
                            // y al valor de la distancia que llega como parametro del metodo
                            // Las personas son consultadas teniendo en cuenta la tabla empleado_x_prueba_x_estado
                            // La consulta incluye un LIMIT de empleados, para evitar que se consulten mas empleados de los
                            // que permite el cupo del sitio. Si el sitio no se llena en la primera iteraci??n, lo har?? en la segunda
                            // o tercera etc. 
                            haySitiosDisponibles = true;
                            sqlEmpleados.delete(0, sqlEmpleados.length());
                            sqlEmpleados.append("SELECT e.idEmpleado, e.nroDoc, e.latitud, e.longitud, n.codigoCargo, ");
                            sqlEmpleados.append("TRUNCATE( 3959 * acos( cos( radians(").append(res.getFloat("latitud")).append(") ) * cos( radians( latitud ) ) * cos( radians( longitud ) - radians(").append(res.getFloat("longitud")).append(") ) + sin( radians(").append(res.getFloat("latitud")).append(") ) * sin( radians( latitud ) ) ),2) AS distance ");
                            sqlEmpleados.append("FROM empleado_x_prueba_x_estado n INNER JOIN ");
                            sqlEmpleados.append("empleado e ON e.idEmpleado = n.idempleado ");
                            sqlEmpleados.append("WHERE n.idPrueba = ").append(prueba).append(" ");
                            sqlEmpleados.append("AND n.codigoCargo IN (SELECT codigoCargo FROM cargos WHERE nivel_cargo = ").append(nivelCargo).append(") ");
                            sqlEmpleados.append("AND n.idestpersona = 7 ");
                            sqlEmpleados.append("AND e.codigoMunicipio = ").append(municipio).append(" ");
                            sqlEmpleados.append("AND e.latitud != 0 and e.longitud != 0 ");
                            sqlEmpleados.append("HAVING distance < ").append(distancia).append(" ");
                            sqlEmpleados.append("ORDER BY distance LIMIT 0 , ").append(res.getInt("cantidad")).append(";");

                            conex.consultar(sqlEmpleados.toString(), new Operaciones.OperacionConsulta() {

                                StringBuilder sqlUpdate = new StringBuilder();

                                @Override
                                public void respuestaConsulta(ResultSet cercanos) {
                                    try {
                                        while (cercanos.next()) {
                                            asignoEmpleado = true;

                                            // Update que asigna a una persona en un determinado sitio de la tabla asignaci??n.
                                            // El update consta de una consulta interna que permite consultar un registro de la tabla
                                            // nombramiento. Como puede haber varios registros sin asignaci??n de persona para un mismo
                                            // sitio, se incluye el LIMIT 1 para que retorne solo un registro.
                                            sqlUpdate.delete(0, sqlUpdate.length());
                                            sqlUpdate.append("UPDATE nombramiento, ");
                                            sqlUpdate.append("(SELECT n.id FROM nombramiento n ");
                                            sqlUpdate.append("WHERE n.codigoMunicipio = 11001 AND ");
                                            sqlUpdate.append("n.idPrueba = ").append(prueba).append(" AND ");
                                            sqlUpdate.append("n.codigoCargo IN (SELECT codigoCargo FROM cargos WHERE nivel_cargo = ").append(nivelCargo).append(") AND ");         //= ").append(cercanos.getInt("codigoCargo")).append(" AND ");
                                            sqlUpdate.append("(n.nrodoc IS NULL or n.nrodoc = 0) and n.idDivipol = ").append(res.getInt("idDivipol")).append(" ");
                                            sqlUpdate.append("GROUP BY n.id, n.idDivipol ");
                                            sqlUpdate.append("HAVING COUNT(*) > 0 LIMIT 1) as T1 ");
                                            sqlUpdate.append("SET nombramiento.nrodoc = ").append(cercanos.getLong("nroDoc")).append(", ");
                                            sqlUpdate.append("nombramiento.distancia = ").append(distancia).append(", nombramiento.estado = 1 ");
                                            sqlUpdate.append("WHERE nombramiento.id = T1.id");
                                            boolean resultado = conex.ejecutar(sqlUpdate.toString());

                                        }
                                    } catch (SQLException ex) {
                                        Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                        }

                        // Aqui se controla la recursividad. Si se asignaron personas, contin??a iterando.
                        // Si hay sitios con disponibilidad, pero no se agregaron personas, y ademas la distania no
                        // excede las 15 millas, entonces, aunmenta la distancia y sigue buscando empleados.
                        if (asignoEmpleado || (haySitiosDisponibles && distancia < 15)) {
                            asignarMasivo(distancia + 0.5F, nivelCargo, prueba, municipio);
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(SitioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    public List<Empleado> buscarAsignados(int idSitio, int nivelCargo) {
        final List<Empleado> Empleados = new ArrayList<>();

        try {

            String sql = "SELECT e.*, n.codigoCargo \n "
                    + "FROM nombramiento n INNER JOIN empleado e \n "
                    + "ON n.nrodoc = e.nrodoc \n "
                    + "WHERE n.idDivipol = " + idSitio + " AND distancia > 0 AND "
                    + "codigoCargo IN (SELECT codigoCargo FROM cargos WHERE nivel_cargo = " + nivelCargo + ")";

            conex.consultar(sql,
                    new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Empleado empleado = new Empleado();

                            empleado.setIdEmpleado(res.getInt("idEmpleado"));
                            empleado.setMunicipio(new MunicipioDao().GetById(res.getString("codigoDepartamento"), res.getString("codigoMunicipio")));
                            empleado.setNrodoc(res.getLong("nrodoc"));
                            empleado.setTipodoc(res.getString("tipodoc"));
                            empleado.setApellido1(res.getString("apellido1"));
                            empleado.setApellido2(res.getString("apellido2"));
                            empleado.setNombre1(res.getString("nombre1"));
                            empleado.setNombre2(res.getString("nombre2"));
                            empleado.setDireccion(res.getString("direccion"));
                            empleado.setTelefono(res.getString("telefono"));
                            empleado.setCelular(res.getString("celular"));
                            empleado.setEmail(res.getString("email"));
                            empleado.setLatitud(res.getFloat("latitud"));
                            empleado.setLongitud(res.getFloat("longitud"));
                            empleado.setObservacion((new CargoDao().GetById(res.getString("codigoCargo"))).getDescripcion());
                            Empleados.add(empleado);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Empleados;
    }

    /**
     * Busca todos los empleado por departamento y/o municipio
     *
     * @param departamento
     * @param municipio
     * @return List Empleado
     */
    public List<Empleado> buscarEmpleadosPorDepartamentoMunicipio(String departamento, String municipio) {
        final List<Empleado> Empleados = new ArrayList<>();

        try {

            StringBuilder sql = new StringBuilder();
            sql.append("select e.*, esem.descripcion as estado from empleado e ");
            sql.append("left join estadoempleado esem on esem.codigoestado=e.estadoicfes ");
            if (!departamento.isEmpty()) {
                sql.append(" inner join municipio_dane m on m.codigoMunicipio=e.DaneMunicipio ");
                sql.append(" where m.codigoDepartamento=");
                sql.append(departamento);
                if (!municipio.isEmpty()) {
                    sql.append(" and e.DaneMunicipio=");
                    sql.append(municipio);
                }
            } else {
                sql.append("where e.DaneMunicipio=");
                sql.append(municipio);
            }

            conex.consultar(sql.toString(),
                    new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Empleado empleado = new Empleado();

                            empleado.setIdEmpleado(res.getInt("idEmpleado"));
                            empleado.setNrodoc(res.getLong("nrodoc"));
                            empleado.setTipodoc(res.getString("tipodoc"));
                            empleado.setApellido1(res.getString("apellido1"));
                            empleado.setApellido2(res.getString("apellido2"));
                            empleado.setNombre1(res.getString("nombre1"));
                            empleado.setNombre2(res.getString("nombre2"));
                            empleado.setTelefono(res.getString("telefono"));
                            empleado.setCelular(res.getString("celular"));
                            Estado estado = new Estado();
                            estado.setDescripcion(res.getString("estado"));
                            empleado.setEstado(estado);
                            empleado.setCargoobj(new CargoDao().GetById(res.getString("cargo")));
                            Empleados.add(empleado);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Empleados;
    }

    /**
     * Busca todos los empleado por departamento y/o municipio
     *
     * @param departamento
     * @param nodo
     * @param municipio
     * @param empleado
     * @param p
     * @return List Empleado
     */
    public List<Empleado> buscarEmpleadosPorFiltroAvanzado(String departamento, String nodo, String municipio, Empleado empleado, int tipoPrueba, String fechaInicial, String fechaFinal) {
        final List<Empleado> Empleados = new ArrayList<>();

        try {

            boolean bandera = false;

            StringBuilder sql = new StringBuilder();
            sql.append("select distinct e.*, esem.descripcion as estado from empleado e ");
            sql.append(" left join estadoempleado esem on esem.codigoestado=e.estadoicfes ");
            if (departamento != null) {
                sql.append(" inner join municipio_dane m on m.codigoMunicipio=e.DaneMunicipio ");
            }
            if (tipoPrueba != 0 || !fechaInicial.isEmpty() || !fechaFinal.isEmpty()) {
                sql.append(" left join detalle_cargo_x_puesto_x_evento d on d.nrodoc=e.nrodoc ");
                sql.append(" left join prueba p on p.idprueba=d.idPrueba ");
            }

            if (empleado.getGenero() != null) {
                sql.append(" where ");
                sql.append(" e.genero='");
                sql.append(empleado.getGenero());
                sql.append("'");

                bandera = true;
            }
            if (empleado.getCargo() != 0) {
                if (bandera) {
                    sql.append(" and ");
                } else {
                    sql.append(" where ");
                }
                sql.append(" e.cargo='");
                sql.append(empleado.getCargo());
                sql.append("' ");
                bandera = true;
            }
            if (empleado.getNivelacademico() != 0) {
                if (bandera) {
                    sql.append(" and ");
                } else {
                    sql.append(" where ");
                }
                sql.append(" e.nivelacademico=");
                sql.append(empleado.getNivelacademico());
                bandera = true;
            }
            if (empleado.getExperienciahuellas() != -1) {
                if (bandera) {
                    sql.append(" and ");
                } else {
                    sql.append(" where ");
                }
                sql.append(" e.experienciahuellas=");
                sql.append(empleado.getExperienciahuellas());
                bandera = true;
            }
            if (empleado.getIntepretacionsenas() != -1) {
                if (bandera) {
                    sql.append(" and ");
                } else {
                    sql.append(" where ");
                }
                sql.append(" e.intepretacionsenas=");
                sql.append(empleado.getIntepretacionsenas());
                bandera = true;
            }
            if (empleado.getBilingue() != -1) {
                if (bandera) {
                    sql.append(" and ");
                } else {
                    sql.append(" where ");
                }
                sql.append(" e.bilingue=");
                sql.append(empleado.getBilingue());
                bandera = true;
            }
            if (empleado.getManejodiscapacidad() != -1) {
                if (bandera) {
                    sql.append(" and ");
                } else {
                    sql.append(" where ");
                }
                sql.append(" e.manejodiscapacidad=");
                sql.append(empleado.getManejodiscapacidad());
                bandera = true;
            }

            if (departamento != null) {
                if (bandera) {
                    sql.append(" and ");
                } else {
                    sql.append(" where ");
                }
                sql.append(" m.codigoDepartamento=");
                sql.append(departamento);
                bandera = true;
            }
            if (nodo != null) {
                if (bandera) {
                    sql.append(" and ");
                } else {
                    sql.append(" where ");
                }
                sql.append(" e.codigoDepartamento=");
                sql.append(nodo);
                bandera = true;
            }
            if (tipoPrueba != 0) {
                if (bandera) {
                    sql.append(" and ");
                } else {
                    sql.append(" where ");
                }
                sql.append(" p.tprueba=");
                sql.append(tipoPrueba);
                bandera = true;
            }

            if (!fechaInicial.isEmpty()) {
                if (bandera) {
                    sql.append(" and ");
                } else {
                    sql.append(" where ");
                }
                sql.append(" p.fechaaplicacion='");
                sql.append(fechaInicial);
                sql.append("'");
                bandera = true;
            }

            if (!fechaFinal.isEmpty()) {
                if (bandera) {
                    sql.append(" and ");
                } else {
                    sql.append(" where ");
                }
                sql.append(" p.fecha_final_aplicacion='");
                sql.append(fechaFinal);
                sql.append("'");
                bandera = true;
            }
            if (!municipio.isEmpty()) {
                if (bandera) {
                    sql.append(" and ");
                } else {
                    sql.append(" where ");
                }
                sql.append(" e.DaneMunicipio=");
                sql.append(empleado.getMunicipioDane().getCodigoMunicipio());
            }

            conex.consultar(sql.toString(),
                    new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Empleado empleado = new Empleado();

                            empleado.setIdEmpleado(res.getInt("idEmpleado"));
                            empleado.setNrodoc(res.getLong("nrodoc"));
                            empleado.setTipodoc(res.getString("tipodoc"));
                            empleado.setApellido1(res.getString("apellido1"));
                            empleado.setApellido2(res.getString("apellido2"));
                            empleado.setNombre1(res.getString("nombre1"));
                            empleado.setNombre2(res.getString("nombre2"));
                            empleado.setTelefono(res.getString("telefono"));
                            empleado.setCelular(res.getString("celular"));
                            EstadoEmpleado estado = new EstadoEmpleado();
                            estado.setDescripcion(res.getString("estado"));
                            empleado.setEstadoIcfes(estado);
                            empleado.setCargoobj(new CargoDao().GetById(res.getString("cargo")));
                            Empleados.add(empleado);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Empleados;
    }

    /**
     * Busca todos los empleado por departamento y/o municipio
     *
     * @param documento
     * @param nombre1
     * @param nombre2
     * @param apellido1
     * @param apellido2
     * @return List Empleado
     */
    public List<Empleado> buscarEmpleadosPorFiltroBasico(String documento, String nombre1, String nombre2, String apellido1, String apellido2, String dpto) {
        final List<Empleado> Empleados = new ArrayList<>();
        try {

            boolean bandera = false;

            StringBuilder sql = new StringBuilder();
            sql.append("select distinct e.idEmpleado, e.codigoDepartamento, e.codigoMunicipio,e.nrodoc, e.tipodoc,e.apellido1,e.apellido2, ");
            sql.append(" e.nombre1, e.nombre2, e.telefono, e.celular, e.email, e.cargo, esem.descripcion as estado from empleado e ");
            sql.append(" left join estadoempleado esem on esem.codigoestado=e.estadoicfes ");

            if (!documento.isEmpty()) {
                sql.append(" where ");
                sql.append(" e.nrodoc=");
                sql.append(documento);
                bandera = true;
            }
            if (!nombre1.isEmpty()) {
                if (bandera) {
                    sql.append(" and ");
                } else {
                    sql.append(" where ");
                }
                sql.append(" e.nombre1 like '%");
                sql.append(nombre1);
                sql.append("%' ");
                bandera = true;
            }
            if (!nombre2.isEmpty()) {
                if (bandera) {
                    sql.append(" and ");
                } else {
                    sql.append(" where ");
                }
                sql.append(" e.nombre2 like '%");
                sql.append(nombre2);
                sql.append("%'");
                bandera = true;
            }
            if (!apellido1.isEmpty()) {
                if (bandera) {
                    sql.append(" and ");
                } else {
                    sql.append(" where ");
                }
                sql.append(" e.apellido1 like '%");
                sql.append(apellido1);
                sql.append("%'");
                bandera = true;
            }
            if (!dpto.isEmpty()) {
                if (bandera) {
                    sql.append(" and ");
                } else {
                    sql.append(" where ");
                }
                sql.append(" e.codigoDepartamento='");
                sql.append(dpto);
                sql.append("'");
                bandera = true;
            }
            if (!apellido2.isEmpty()) {
                if (bandera) {
                    sql.append(" and ");
                } else {
                    sql.append(" where ");
                }
                sql.append(" e.apellido2 like '%");
                sql.append(apellido2);
                sql.append("%'");
            }

            sql.append(" order by e.nombre1 ");
            conex.consultar(sql.toString(),
                    new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Empleado empleado = new Empleado();

                            empleado.setIdEmpleado(res.getInt("idEmpleado"));
                            empleado.setNrodoc(res.getLong("nrodoc"));
                            empleado.setTipodoc(res.getString("tipodoc"));
                            empleado.setApellido1(res.getString("apellido1"));
                            empleado.setApellido2(res.getString("apellido2"));
                            empleado.setNombre1(res.getString("nombre1"));
                            empleado.setNombre2(res.getString("nombre2"));
                            empleado.setTelefono(res.getString("telefono"));
                            empleado.setCelular(res.getString("celular"));
                            EstadoEmpleado estado = new EstadoEmpleado();
                            estado.setDescripcion(res.getString("estado"));
                            empleado.setEstadoIcfes(estado);
                            empleado.setCargoobj(new CargoDao().GetById(res.getString("cargo")));
                            Empleados.add(empleado);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Empleados;
    }

    
/**
     * Busca todos los empleado por departamento y/o municipio
     *
     * @param documento
     * @param nombre1
     * @param nombre2
     * @param apellido1
     * @param apellido2
     * @return List Empleado
     */
    public List<Defuncion> buscarEmpleadosPorFiltroBasico_1(String documento, String nombre1, String nombre2, String apellido1, String apellido2, String dpto) {
        final List<Defuncion> Defunciones = new ArrayList<>();
        try {

            boolean bandera = false;

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT dia, mes,  ano, libro, tomo, folio, serialid, depto, mpio, oficina, sexom, sexof, tipodoc, nrodoc, papel, sapel, pnombre, snombre, nombreArchivo, linea, version FROM defuncion d ");

            if (!documento.isEmpty()) {
                sql.append(" where ");
                sql.append(" d.nrodoc='");
                sql.append(documento);
                sql.append("'");
                bandera = true;
            }
            if (!nombre1.isEmpty()) {
                if (bandera) {
                    sql.append(" and ");
                } else {
                    sql.append(" where ");
                }
                sql.append(" d.pnombre like '%");
                sql.append(nombre1);
                sql.append("%' ");
                bandera = true;
            }
            if (!nombre2.isEmpty()) {
                if (bandera) {
                    sql.append(" and ");
                } else {
                    sql.append(" where ");
                }
                sql.append(" d.snombre like '%");
                sql.append(nombre2);
                sql.append("%'");
                bandera = true;
            }
            if (!apellido1.isEmpty()) {
                if (bandera) {
                    sql.append(" and ");
                } else {
                    sql.append(" where ");
                }
                sql.append(" d.papel like '%");
                sql.append(apellido1);
                sql.append("%'");
                bandera = true;
            }
            
            if (!apellido2.isEmpty()) {
                if (bandera) {
                    sql.append(" and ");
                } else {
                    sql.append(" where ");
                }
                sql.append(" d.sapel like '%");
                sql.append(apellido2);
                sql.append("%'");
            }

            sql.append(" order by d.pnombre, d.snombre, d.papel, d.sapel limit 100 ");
            conex.consultar_1(sql.toString(),
                    new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Defuncion defuncion = new Defuncion();

                            defuncion.setNrodoc(res.getString("nrodoc"));
                            defuncion.setTipodoc(res.getString("tipodoc"));
                            defuncion.setApellido1(res.getString("papel"));
                            defuncion.setApellido2(res.getString("sapel"));
                            defuncion.setNombre1(res.getString("pnombre"));
                            defuncion.setNombre2(res.getString("snombre"));
                            defuncion.setDia(res.getString("dia"));
                            defuncion.setMes(res.getString("mes"));
                            defuncion.setAno(res.getString("ano"));
                            defuncion.setLibro(res.getString("libro"));
                            defuncion.setTomo(res.getString("tomo"));
                            defuncion.setFolio(res.getString("folio"));
                            defuncion.setSerialid(res.getString("serialid"));
                            defuncion.setDepto(res.getString("depto"));
                            defuncion.setMpio(res.getString("mpio"));
                            defuncion.setOficina(res.getString("oficina"));
                            defuncion.setSexom(res.getString("sexom"));
                            defuncion.setSexof(res.getString("sexof"));
                            defuncion.setNombreArchivo(res.getString("nombreArchivo"));
                            defuncion.setLinea(res.getString("linea"));
                            
                            Defunciones.add(defuncion);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return Defunciones;
    }

        
    
    
    /**
     * Metodo encargado de verificar si el empleado esta registrado en el nodo
     * coorespondiente
     *
     * @param codigoNodo
     * @param nroDocu
     * @return
     */
    public boolean verificarEmpleadoNodo(String codigoNodo, long nroDocu) {
        final List<Integer> listResult = new ArrayList<>();
        StringBuilder build = new StringBuilder();
        build.append("select ");
        build.append("count(idEmpleado) as cuenta ");
        build.append("from empleado ");
        build.append("where codigoDepartamento = ? ");
        build.append("and nrodoc = ?");
        conex.consultar(new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    while (resultado.next()) {
                        listResult.add(resultado.getInt("cuenta"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, build.toString(), codigoNodo, nroDocu);

        return listResult.get(0) > 0;
    }

    public JSONObject consultarDocumentosEmpleados(int idEmpleado) {

        final JSONArray json = new JSONArray();
        final JSONObject doc = new JSONObject();

        try {

            StringBuilder sql = new StringBuilder();
            sql.append("select imgdociden, imgcertestudio, imgafiliaeps, imgrut from empleado where idEmpleado=");
            sql.append(idEmpleado);

            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {

                            doc.put("imgdociden", res.getInt("imgdociden"));
                            doc.put("imgcertestudio", res.getInt("imgcertestudio"));
                            doc.put("imgafiliaeps", res.getInt("imgafiliaeps"));
                            doc.put("imgrut", res.getInt("imgrut"));

                            if (res.getInt("imgdociden") == 0 || res.getInt("imgcertestudio") == 0 || res.getInt("imgafiliaeps") == 0 || res.getInt("imgrut") == 0) {
                                doc.put("completo", "No");
                            } else {
                                doc.put("completo", "Si");
                            }
                            json.put(doc);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(UsuarioSitioDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return doc;
    }

    public String listarPersonasPorEstado(String codigoDepartamento, String codigoMunicipio, int estado, String nrodoc, int idPrueba) {

        final Object[] resultado = new Object[1];
        JSONArray json = null;

        final List<Empleado> Empleados = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT distinct idEmpleado,nrodoc, tipodoc,apellido1,apellido2, es.descripcion as estado,");
        sql.append(" nombre1, nombre2, telefono, celular, email, imgdociden, m.nombre as nombremunicipio, d.nombre as nombredepartamento ");
        sql.append(" FROM empleado  e ");
        sql.append(" inner join municipio_dane m on m.codigoMunicipio=e.DaneMunicipio ");
        sql.append(" inner join departamento_dane d on d.codigo=m.codigoDepartamento ");
        sql.append(" inner join estado es on es.codigoEstado=e.codigoEstado ");
        sql.append(" where idempleado in (select distinct asis.idempleado from asistencia asis inner join evento ev on ev.codigoEvento=asis.codigoevento where ev.idprueba=");
        sql.append(idPrueba).append(")");

        if (!codigoDepartamento.equals("0")) {
            sql.append(" and e.codigoDepartamento ='").append(codigoDepartamento).append("'");
        }

        if (!codigoMunicipio.equals("0")) {
            sql.append(" and  e.DaneMunicipio='").append(codigoMunicipio).append("'");
        }

        if (estado != -1) {
            sql.append(" and  e.codigoEstado=").append(estado);
        }
        if (!nrodoc.isEmpty()) {
            sql.append(" and e.nrodoc=").append(nrodoc);
        }

        
     
        
        try {
            
            conex.consultar(sql.toString(),
                               new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {            
                    try {
                        while (res.next()) {
                            resultado[0] = ResultSetConverter.convert2(res);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            json = (JSONArray) resultado[0];
        } catch (Exception e) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();
    }

    /**
     * Author: John Steak Herrera Moreno
     * Date: 29/06/2022
     * 
     * @param empleado
     * @return 
     */
    public Boolean actualizarContrasena(Empleado empleado) {
        
        Boolean resultado = false;
        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE empleado SET clave = ?, ");
        sql.append("cambioPassword = 0 WHERE nrodoc = ?");
        resultado = conex.ejecutar(sql.toString(), 
                empleado.getClave(), 
                empleado.getNrodoc());
        return resultado;
    }

}
