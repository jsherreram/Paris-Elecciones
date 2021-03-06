/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Estado;
import co.com.grupoasd.nomina.modelo.Evento;
import co.com.grupoasd.nomina.modelo.ListaAsistencia;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Rodriguez
 */
public class GeneraSolicitudesDao {

    private Operaciones conex = new Operaciones();

    public GeneraSolicitudesDao() {

    }

    public void GenerarTerceros(String idDpto) {
        final Object result[] = new Object[1];
        String CtroCosto = null;
        try {
            String sql = " select centrocosto from departamento \n"
                    + " where codigo = '" + idDpto + "'";

            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = res.getString("centrocosto");
                    } catch (SQLException ex) {
                        Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
            CtroCosto = (String) result[0];

            String archivo = "C:\\Temp\\TERCEROS_" + CtroCosto.replace(' ', '_') + ".csv";
            this.GeneraTerceros(idDpto, archivo);

            File afile = new File(archivo);
            afile.renameTo(new File("C:\\LOGIWEB\\INPUT\\" + afile.getName()));

        } catch (Exception e) {
            Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void GeneraTerceros(String idDpto, String PathNombreArchivo) {

        String nombreArchivo = PathNombreArchivo;
        final Object[] result = new Object[1];
        String texto = "Tipo Id.;No. Id;1er Apellido;2do Apellido;1er Nombre ;2do Nombre;Ciudad Domicilio;Direccion;Telefono;Celular;E-mail;Cod Actividad Economica;C.Costo;RH;E\n";

        try {
            FileWriter fwriter = new FileWriter(nombreArchivo);

            String sql = " select t.codigoLogisys, nrodoc, apellido1, apellido2, \n"
                    + " nombre1, nombre2, concat(codigoDane,codigoMunicipio) as ciudad,\n"
                    + " direccion, telefono, celular, email, codigoActividad, d.centrocosto, 'O+' as rh, 'D' as E\n"
                    + " from empleado e left join tipo_iden t\n"
                    + " on e.tipodoc = t.codigo\n"
                    + " left join departamento d\n"
                    + " on e.codigoDepartamento = d.codigo\n"
                    + " where d.codigo = '" + idDpto + "'";

            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        //Generar el Archivo Plano
                        while (res.next()) {
                            result[0] = res.getString("codigoLogisys") + ";"
                                    + res.getInt("nrodoc") + ";"
                                    + res.getString("apellido1") + ";"
                                    + res.getString("apellido2") + ";"
                                    + res.getString("nombre1") + ";"
                                    + res.getString("nombre2") + ";"
                                    + res.getString("ciudad") + ";"
                                    + res.getString("direccion") + ";"
                                    + res.getString("telefono") + ";"
                                    + res.getString("celular") + ";"
                                    + res.getString("email") + ";"
                                    + res.getString("codigoActividad") + ";"
                                    + res.getString("centrocosto") + ";"
                                    + res.getString("rh") + ";"
                                    + res.getString("E") + "\n";
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            texto += ((String) result[0]);

            fwriter.write(texto);
            fwriter.flush();
            fwriter.close();

        } catch (Exception e) {
            Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public String GenerarListas(String idDpto, String idMpio, String idZona, String idPuesto, String idCargo, int idEvento) {

        ListaAsistencia lista = new ListaAsistencia();
        String filePathString = "";
        final Object[] result = new Object[4];
        try {

            this.GenerarTerceros(idDpto);

            String sql = " select centrocosto, DATE_FORMAT(current_date, '%Y%m%d') as fecha, CURTIME() as hora  from departamento \n"
                    + " where codigo = '" + idDpto + "'";

            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            result[0] = res.getString("centrocosto");
                            result[1] = res.getString("fecha");
                            result[2] = res.getString("hora");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            String CtroCosto = "";
            String fecha = "";
            String hora = "";
            //Generar el Archivo Plano

            CtroCosto = (String) result[0];
            fecha = (String) result[1];
            hora = (String) result[2];
            hora = hora.replace(':', '_');

            String nombreArchivoTmp = "SOL_" + CtroCosto.replace(' ', '_') + "_" + fecha + "_" + hora + ".csv";

            String archivo = "C:\\Temp\\" + nombreArchivoTmp;

            this.GeneraListaAsistencia(idDpto, idMpio, idZona, idPuesto, idCargo, idEvento, archivo, CtroCosto);

            File afile = new File(archivo);
            afile.renameTo(new File("C:\\LOGIWEB\\INPUT\\" + afile.getName()));

            Runtime runtime = Runtime.getRuntime();
            try {
                Process p1 = runtime.exec("cmd /c c:\\LOGIWEB\\LOGISYS_ELEC.bat");
                InputStream is = p1.getInputStream();
                int i = 0;
                while ((i = is.read()) != -1) {
                    System.out.print((char) i);
                }
            } catch (IOException ioException) {
                Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, ioException.getMessage());
            }

            ListaAsistenciaDao listaDao = new ListaAsistenciaDao();

            lista.setMunicipio(new MunicipioDao().GetById(idDpto, idMpio));
            lista.setZona(idZona);
            lista.setPuesto(idPuesto);
            lista.setCargo(new CargoDao().GetById(idCargo));
            lista.setEvento(new EventoDao().GetById(idEvento));
            lista.setNombreArchivo(nombreArchivoTmp.replace("csv", "pdf"));

            listaDao.actualizar(lista);

            filePathString = "C:\\LOGIWEB\\OUTPUT\\" + lista.getNombreArchivo();
            File f = new File(filePathString);
            boolean genero = false;

            while (!genero) {

                if (f.exists() && !f.isDirectory()) {
                    genero = true;
                }
            }

        } catch (Exception e) {
            Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return lista.getNombreArchivo();
    }

    public void GeneraListaAsistencia(String idDpto, String idMpio, String idZona, String idPuesto, String idCargo, int idEvento, String PathNombreArchivo, String ctroCost) {

        final Object[] result = new Object[1];
        String nombreArchivo = PathNombreArchivo;
        String texto = "1;" + ctroCost + ";2;E\n";

        try {
            FileWriter fwriter = new FileWriter(nombreArchivo);

            String sql = " SELECT 2 as tipo, c.codigoLogisys, c.descripcion, d.codigoDane, d.nombre as nombreDpto,\n"
                    + " m.codigoMunicipio, m.nombre, codigoZona, codigoZona, codigoZona as nzona, codigoPuesto, codigoPuesto as ncodigoPuesto,\n"
                    + " ubicacion, nrodoc, \n"
                    + " DATE_FORMAT(e.fecha, '%Y%m%d') as fecha, \n"
                    + " e.codigoLogisys, \n"
                    + " e.nombre\n"
                    + " FROM detalle_cargo_x_puesto_x_evento a left join cargos c\n"
                    + " on a.codigoCargo = c.codigoCargo\n"
                    + " left join departamento d\n"
                    + " on a.codigoDepartamento = d.codigo\n"
                    + " left join municipio m \n"
                    + " on a.codigoDepartamento = m.codigoDepartamento\n"
                    + " and a.codigoMunicipio = m.codigoMunicipio\n"
                    + " left join evento e\n"
                    + " on a.codigoEvento = e.codigoEvento\n"
                    + " WHERE a.codigodepartamento = '" + idDpto + "'\n"
                    + " and a.codigoMunicipio = '" + idMpio + "'\n"
                    + " and codigoZona = '" + idZona + "'\n"
                    + " and codigoPuesto = '" + idPuesto + "'\n"
                    + " and a.codigoCargo = '" + idCargo + "' \n"
                    + " and a.codigoEvento = " + idEvento + " \n"
                    + " and nrodoc > 0";

            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            result[0] = res.getString("tipo") + ";"
                                    + res.getInt("codigoLogisys") + ";"
                                    + res.getString("descripcion") + ";"
                                    + res.getString("codigoDane") + ";"
                                    + res.getString("nombreDpto") + ";"
                                    + res.getString("codigoMunicipio") + ";"
                                    + res.getString("nombre") + ";"
                                    + res.getString("codigoZona") + ";"
                                    + res.getString("nzona") + ";"
                                    + res.getString("codigoPuesto") + ";"
                                    + res.getString("ncodigoPuesto") + ";"
                                    + res.getString("ubicacion") + ";"
                                    + res.getString("nrodoc") + ";"
                                    + res.getString("fecha") + ";"
                                    + res.getString("codigoLogisys") + ";"
                                    + res.getString("nombre") + ";D\n";
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            texto += ((String) result[0]);

            fwriter.write(texto);
            fwriter.flush();
            fwriter.close();

        } catch (Exception e) {
            Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void GeneraPlanComunica(String user, final int idEvento) {

        FileWriter fichero = null;

        String nombreArchivo = "/data/PLAN_" + user + ".csv";

        final StringBuilder sb = new StringBuilder();
        StringBuilder sql = new StringBuilder();

        String tabla = "";
        String tituloasistio = "";

        if (idEvento == 0) {
            tabla = "nombramiento";
            tituloasistio = "";
        } else {
            tabla = "detalle_cargo_x_puesto_x_evento";
            tituloasistio = "asistio;";
        }

        String texto = "Evento;Cargo;Codigo Departamento; Nombre Departamento; Codigo Municipio; Nombre Municipio; Codigo Sitio;Nombre Sitio;Ubicacion; Identificacion; Apellido1; Apellido2; Nombre1; Nombre2; Celular; Operador; email; " + tituloasistio + "Medio; esJurado; tieneRut \n";
        sb.append(texto);
        try {

            sql.append(" select ev.nombre as nevento, c.descripcion, d.codigoDepartamento, dep.nombre as depnombre, d.codigoMunicipio, m.nombre as munnombre, dp.codigoZona, dp.codigoPuesto, dp.nombrePuesto,d.ubicacion, d.nrodoc, e.apellido1, ");
            sql.append(" e.apellido2, e.nombre1, e.nombre2, e.celular, o.nombre, e.email, e.idpuesto,case ifnull(asi.id,0) when 0 then 'No Asistio' else 'Si Asistio' end as asistio, d.cantidadasistio, d.cantidadnoasistio, ");
            sql.append(" case ifnull(asi.id,0) when 0 then 'No Asistio' else case asi.biometrico when 0 then 'Manual' else 'Biometrico' end end as medio, e.esJurado as esJurado, imgdociden as tieneRut ");
            sql.append(" from ").append(tabla).append(" d ");
            sql.append(" inner join divipol dp on dp.idprueba=d.idprueba and dp.iddivipol=d.iddivipol and dp.codigopuesto=d.codigopuesto ");
            sql.append(" left join empleado e on d.nrodoc = e.nrodoc ");
            sql.append(" left join cargos c on d.codigoCargo = c.codigoCargo ");
            sql.append(" left join departamento dep on d.codigoDepartamento = dep.codigo ");
            sql.append(" left join municipio m  on d.codigoDepartamento = m.codigoDepartamento and d.codigoMunicipio = m.codigoMunicipio  ");
            sql.append(" left join evento ev on d.codigoevento = ev.codigoevento  ");
            sql.append(" left join asistencia asi on asi.codigoevento=d.codigoevento and asi.iddivipol=d.iddivipol and asi.idempleado=e.idempleado ");
            sql.append(" left join operador_celular o on e.id_operador = o.id_operador ");
            sql.append(" where d.codigoDepartamento in(  ");
            sql.append(" select codigodepartamento  ");
            sql.append(" from usuario_departamento  ");
            sql.append(" where usuario = '").append(user).append("'");
            sql.append(" and   usuario_departamento.idPrueba=d.idPrueba)  ");
            sql.append(" and d.codigoEvento = ").append(idEvento).append(" ");
            sql.append(" order by c.codigoCargo, d.codigoDepartamento, d.codigoMunicipio, codigoZona, codigoPuesto, ubicacion ");

            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {

                            String texto = res.getString("nevento") + ";"
                                    + res.getString("descripcion") + ";"
                                    + res.getString("codigoDepartamento") + ";"
                                    + res.getString("depnombre") + ";"
                                    + res.getString("codigoMunicipio") + ";"
                                    + res.getString("munnombre") + ";"
                                    + '"' + res.getString("codigoPuesto") + '"' + ";"
                                    + '"' + res.getString("nombrepuesto") + '"' + ";"
                                    + res.getString("ubicacion") + ";";

                            texto += res.getString("nrodoc") + ";"
                                    + res.getString("apellido1") + ";"
                                    + res.getString("apellido2") + ";"
                                    + res.getString("nombre1") + ";"
                                    + res.getString("nombre2") + ";"
                                    + res.getString("celular") + ";"
                                    + res.getString("nombre") + ";"
                                    + res.getString("email") + ";";

                            if (idEvento == 0) {
                                texto += "\n";
                            } else {
                                texto += res.getString("asistio") + ";" + "";
                                texto += res.getString("medio") + ";";
                                texto += res.getString("esJurado") + ";";
                                texto += res.getString("tieneRut") + ";" + "\n";
                            }

                            sb.append(texto);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            String identi = "";

            //Moverlo al directorio de logiweb
            FileWriter fwriter = new FileWriter(nombreArchivo);
            fwriter.write(sb.toString());
            fwriter.flush();
            fwriter.close();

        } catch (Exception e) {
            Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void generaPersonalDetalladoPorPrueba(String user, final int idPrueba) {

        FileWriter fichero = null;

        String nombreArchivo = "/data/PersonalNombrado_" + user + ".csv";

        final StringBuilder sb = new StringBuilder();
        StringBuilder sql = new StringBuilder();
        String tabla = "detalle_cargo_x_puesto_x_evento";

        String texto = "Evento;Codigo Departamento; Nombre Departamento; Codigo Municipio; Nombre Municipio; Codigo Sitio;Nombre Sitio;Ubicacion; Cargo; Identificacion; Apellido1; Apellido2; Nombre1; Nombre2; Celular; Operador; email; asistio; Medio; esJurado; tieneRut \n";
        sb.append(texto);
        try {
            sql.append(" select ev.nombre as nevento, d.codigoDepartamento, dep.nombre as depnombre, d.codigoMunicipio, m.nombre as munnombre, dp.codigoZona, dp.codigoPuesto, dp.nombrePuesto,d.ubicacion,c.descripcion, d.nrodoc, e.apellido1, ");
            sql.append(" e.apellido2, e.nombre1, e.nombre2, e.celular, o.nombre, e.email, e.idpuesto,case ifnull(asi.id,0) when 0 then 'No Asistio' else 'Si Asistio' end as asistio, d.cantidadasistio, d.cantidadnoasistio, ");
            sql.append(" case ifnull(asi.id,0) when 0 then 'No Asistio' else case asi.biometrico when 0 then 'Manual' else 'Biometrico' end end as medio, e.esJurado as esJurado, imgdociden as tieneRut ");
            sql.append(" from ").append(tabla).append(" d ");
            sql.append(" inner join divipol dp on dp.idprueba=d.idprueba and dp.iddivipol=d.iddivipol and dp.codigopuesto=d.codigopuesto ");
            sql.append(" left join empleado e on d.nrodoc = e.nrodoc ");
            sql.append(" left join cargos c on d.codigoCargo = c.codigoCargo ");
            sql.append(" left join departamento dep on d.codigoDepartamento = dep.codigo ");
            sql.append(" left join municipio m  on d.codigoDepartamento = m.codigoDepartamento and d.codigoMunicipio = m.codigoMunicipio  ");
            sql.append(" left join evento ev on d.codigoevento = ev.codigoevento  ");
            sql.append(" left join asistencia asi on asi.codigoevento=d.codigoevento and asi.iddivipol=d.iddivipol and asi.idempleado=e.idempleado ");
            sql.append(" left join operador_celular o on e.id_operador = o.id_operador ");
            sql.append(" where d.codigoDepartamento in(  ");
            sql.append(" select codigodepartamento  ");
            sql.append(" from usuario_departamento  ");
            sql.append(" where usuario = '").append(user).append("'");
            sql.append(" and   usuario_departamento.idPrueba=d.idPrueba)  ");
            sql.append(" and d.idPrueba = ").append(idPrueba).append(" and ev.esCapacitacion=0");
            sql.append(" order by ev.codigoEvento, d.codigoDepartamento, d.codigoMunicipio, codigoZona, codigoPuesto, ubicacion,d.codigoCargo ");

            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {

                            String texto = res.getString("nevento") + ";"
                                    + validaNull(res.getString("codigoDepartamento")) + ";"
                                    + validaNull(res.getString("depnombre")) + ";"
                                    + validaNull(res.getString("codigoMunicipio")) + ";"
                                    + validaNull(res.getString("munnombre")) + ";"
                                    + '"' + res.getString("codigoPuesto") + '"' + ";"
                                    + '"' + res.getString("nombrepuesto") + '"' + ";"
                                    + validaNull(res.getString("ubicacion")) + ";"
                                    + validaNull(res.getString("descripcion")) + ";";
                            texto += validaNull(res.getString("nrodoc")) + ";"
                                    + validaNull(res.getString("apellido1")) + ";"
                                    + validaNull(res.getString("apellido2")) + ";"
                                    + validaNull(res.getString("nombre1")) + ";"
                                    + validaNull(res.getString("nombre2")) + ";"
                                    + validaNull(res.getString("celular")) + ";"
                                    + validaNull(res.getString("nombre")) + ";"
                                    + validaNull(res.getString("email")) + ";";
                            texto += validaNull(res.getString("asistio")) + ";" + "";
                            texto += validaNull(res.getString("medio")) + ";";
                            texto += validaNull(res.getString("esJurado")) + ";";
                            texto += validaNull(res.getString("tieneRut")) + ";" + "\n";
                            sb.append(texto);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            String identi = "";

            //Moverlo al directorio de logiweb
            FileWriter fwriter = new FileWriter(nombreArchivo);
            fwriter.write(sb.toString());
            fwriter.flush();
            fwriter.close();

        } catch (Exception e) {
            Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void GeneraPlanComunica_plano(String user, final int idEvento) {

        FileWriter fichero = null;

        String nombreArchivo = "/data//plan_comunicaciones_" + user + ".csv";

        final StringBuilder sb = new StringBuilder();
        StringBuilder sql = new StringBuilder();

        String tabla = "";
        String tituloasistio = "";

        if (idEvento == 0) {
            tabla = "nombramiento";
            tituloasistio = "";
        } else {
            tabla = "detalle_cargo_x_puesto_x_evento";
            tituloasistio = "asistio;";
        }

        String texto;
        try {
            sql.append(" select dp.codigodepartamento,mid(dp.codigoMunicipio,length(dp.codigoMunicipio)-2,3) codigomunicipio,dp.codigozona,mid(dp.codigopuesto,length(dp.codigopuesto)-1,2) as puesto,1 as inicial,ifnull(dp.cantidadmesas,0) cantidadmesas, ");
            sql.append(" concat(ifnull(e.apellido1,''),' ',ifnull(e.apellido2,''),' ',ifnull(e.nombre1,''),' ',ifnull(e.nombre2,'')) as persona, ");
            sql.append(" ifnull(case when length(trim(e.celular))=10 then trim(e.celular) else trim(e.telefono) end,0) telefono,  ");
            sql.append(" ifnull(case when length(trim(e.celular))=10 then trim(e.celular) else trim(e.telefono) end,0) telefono1,  ");
            sql.append(" ifnull(case when length(trim(e.celular2))=10 then trim(e.celular2) else trim(e.telefono2) end,0) telefono2  ");
            sql.append(" from ").append(tabla).append(" d ");
            sql.append(" inner join divipol dp on dp.idprueba=d.idprueba and dp.iddivipol=d.iddivipol and dp.codigopuesto=d.codigopuesto ");
            sql.append(" inner join empleado e on d.nrodoc = e.nrodoc ");
            sql.append(" inner join cargos c on d.codigoCargo = c.codigoCargo and c.codigocargo = 11");
            sql.append(" inner join departamento dep on d.codigoDepartamento = dep.codigo ");
            sql.append(" inner join municipio m  on d.codigoDepartamento = m.codigoDepartamento and d.codigoMunicipio = m.codigoMunicipio  ");
            sql.append(" inner join evento ev on d.codigoevento = ev.codigoevento  ");
            sql.append(" where d.codigoDepartamento in(  ");
            sql.append(" select codigodepartamento  ");
            sql.append(" from usuario_departamento  ");
            sql.append(" where usuario = '").append(user).append("'");
            sql.append(" and   usuario_departamento.idPrueba=d.idPrueba)  ");
            sql.append(" and d.codigoEvento = ").append(idEvento).append(" ");
            sql.append(" order by dp.codigoDepartamento, dp.codigoMunicipio, dp.codigoZona,puesto ");
            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {

                            String texto = res.getString("codigodepartamento") + ","
                                    + res.getString("codigomunicipio") + ","
                                    + res.getString("codigozona") + ","
                                    + res.getString("puesto") + ","
                                    + res.getString("inicial") + ","
                                    + res.getString("cantidadmesas") + ","
                                    + res.getString("persona") + ","
                                    + res.getString("telefono") + ","
                                    + res.getString("telefono1") + ","
                                    + res.getString("telefono2") + "\n";
                            sb.append(texto);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            String identi = "";

            //Moverlo al directorio de logiweb
            FileWriter fwriter = new FileWriter(nombreArchivo);
            fwriter.write(sb.toString());
            fwriter.flush();
            fwriter.close();

        } catch (Exception e) {
            Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void GeneraNombramiento(String codigoDepto, int idEvento) {

        final Object[] result = new Object[1];
        String nombreArchivo = "/data//PLAN_" + codigoDepto + ".csv";

        String texto = "Evento;Cargo;Codigo Departamento; Nombre Departamento; Codigo Municipio; Nombre Municipio; Sitio; Identificacion; Apellido1; Apellido2; Nombre1; Nombre2; Celular; email; \n";

        try {
            FileWriter fwriter = new FileWriter(nombreArchivo);

            String sql = " select ev.nombre as nevento, c.descripcion, d.codigoDepartamento, dep.nombre as depnombre, d.codigoMunicipio, m.nombre as munnombre, codigoZona, codigoPuesto, ubicacion, d.nrodoc, e.apellido1,\n"
                    + " e.apellido2, e.nombre1, e.nombre2, e.celular, e.email, e.idpuesto, d.asistio, d.cantidadasistio, d.cantidadnoasistio \n"
                    + " from nombramiento d left join empleado e\n"
                    + " on d.nrodoc = e.nrodoc\n"
                    + " left join cargos c\n"
                    + " on d.codigoCargo = c.codigoCargo\n"
                    + " left join departamento dep\n"
                    + " on d.codigoDepartamento = dep.codigo\n"
                    + " left join municipio m \n"
                    + " on d.codigoDepartamento = m.codigoDepartamento and d.codigoMunicipio = m.codigoMunicipio \n"
                    + " left join evento ev on d.codigoevento = ev.codigoevento  \n"
                    + " where d.codigoDepartamento = '" + codigoDepto + "'"
                    + " and d.codigoEvento = " + idEvento + " \n"
                    + " order by c.codigoCargo, d.codigoDepartamento, d.codigoMunicipio, codigoZona, codigoPuesto, ubicacion";

            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            String texto = res.getString("nevento") + ";"
                                    + res.getString("descripcion") + ";"
                                    + res.getString("codigoDepartamento") + ";"
                                    + res.getString("depnombre") + ";"
                                    + res.getString("codigoMunicipio") + ";"
                                    + res.getString("munnombre") + ";";

                            texto += '"' + res.getString("codigoPuesto") + '"' + ";";

                            texto += res.getString("nrodoc") + ";"
                                    + res.getString("apellido1") + ";"
                                    + res.getString("apellido2") + ";"
                                    + res.getString("nombre1") + ";"
                                    + res.getString("nombre2") + ";"
                                    + res.getString("celular") + ";"
                                    + res.getString("email") + ";";

                            texto += "\n";
                            result[0] = texto;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });

            texto += ((String) result[0]);
            fwriter.write(texto);
            fwriter.flush();
            fwriter.close();

        } catch (Exception e) {
            Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void GeneraNombramientoConViaticos(String codigoDepto, int idEvento) {

        String nombreArchivo = "/data//VIATICOS_" + codigoDepto + ".csv";
        final StringBuilder sb = new StringBuilder();

        String where = "";

        if (codigoDepto.equals("X")) {
            where = " ";
        } else {
            where = " d.codigoDepartamento = '" + codigoDepto + "' and ";
        }

        String texto = "Evento;Cargo;Codigo Departamento; Nombre Departamento; Codigo Municipio; Nombre Municipio; Sitio; Identificacion; Apellido1; Apellido2; Nombre1; Nombre2; Celular; email; transporteinterno; transporteintermunicipal;transporterural; transporteaeropuerto;tiqueteaereo; pernocta; fechasalida; fecharegreso; dias; viaticos;total;aplicaviaticos;  \n";
        sb.append(texto);
        try {
            String sql = "select ev.nombre as nevento, c.descripcion, d.codigoDepartamento, dep.nombre as depnombre, \n"
                    + "d.codigoMunicipio, m.nombre as munnombre, d.codigoPuesto, ubicacion, d.nrodoc, e.apellido1,\n"
                    + "e.apellido2, e.nombre1, e.nombre2, e.celular, e.email, \n"
                    + "transporteinterno, transporteintermunicipal,\n"
                    + "transporterural, transporteaeropuerto,\n"
                    + "tiqueteaereo, pernocta, fechasalida, fecharegreso, dias, viaticos,\n"
                    + "transporteinterno+transporteintermunicipal+transporterural+transporteaeropuerto+viaticos as total, aplicaviaticos\n"
                    + "from nombramiento d left join empleado e\n"
                    + "on d.nrodoc = e.nrodoc\n"
                    + "left join cargos c\n"
                    + "on d.codigoCargo = c.codigoCargo\n"
                    + "left join departamento dep\n"
                    + "on d.codigoDepartamento = dep.codigo\n"
                    + "left join municipio m \n"
                    + "on d.codigoDepartamento = m.codigoDepartamento and d.codigoMunicipio = m.codigoMunicipio\n"
                    + "left join evento ev on d.codigoevento = ev.codigoevento \n"
                    + "left join viaticos vi\n"
                    + "on d.codigopuesto = vi.codigopuesto\n"
                    + "and d.codigocargo = vi.codigocargo\n"
                    + "where " + where
                    + " d.codigoEvento = 0\n"
                    + "order by c.codigoCargo, d.codigoDepartamento, d.codigoMunicipio, d.codigoPuesto, ubicacion";

            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            String texto = res.getString("nevento") + ";"
                                    + res.getString("descripcion") + ";"
                                    + res.getString("codigoDepartamento") + ";"
                                    + res.getString("depnombre") + ";"
                                    + res.getString("codigoMunicipio") + ";"
                                    + res.getString("munnombre") + ";";
                            texto += '"' + res.getString("codigoPuesto") + '"' + ";";
                            texto += res.getString("nrodoc") + ";"
                                    + res.getString("apellido1") + ";"
                                    + res.getString("apellido2") + ";"
                                    + res.getString("nombre1") + ";"
                                    + res.getString("nombre2") + ";"
                                    + res.getString("celular") + ";"
                                    + res.getString("email") + ";"
                                    + res.getString("transporteinterno") + ";"
                                    + res.getString("transporteintermunicipal") + ";"
                                    + res.getString("transporterural") + ";"
                                    + res.getString("transporteaeropuerto") + ";"
                                    + res.getString("tiqueteaereo") + ";"
                                    + res.getString("pernocta") + ";"
                                    + res.getString("fechasalida") + ";"
                                    + res.getString("fecharegreso") + ";"
                                    + res.getString("dias") + ";"
                                    + res.getString("viaticos") + ";"
                                    + res.getString("total") + ";"
                                    + res.getString("aplicaviaticos") + "; \n";

                            sb.append(texto);

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            FileWriter fwriter = new FileWriter(nombreArchivo);
            fwriter.write(sb.toString());
            fwriter.flush();
            fwriter.close();

        } catch (Exception e) {
            Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void GeneraEmpleados(String idDpto, String idUsuario) {

        final Object[] result = new Object[1];
        String nombreArchivo = "/data//TERCEROS_" + idDpto + ".csv";

        String texto = "Codigo Departamento;Nombre Departamento;Codigo Municipio;Nombre Municipio;"
                + "Tipo Identificacion;Numero Identificacion;Apellido1;Apellido2;Nombre1;Nombre2;Direccion;"
                + "Celular;telefono;email;Codigo Actividad;Estado;idpuesto;Cargo\n";
        try {
            FileWriter fwriter = new FileWriter(nombreArchivo);

            String sql = " select e.codigoDepartamento, d.nombre as dep, e.codigoMunicipio, m.nombre as mun,\n"
                    + "tipodoc, nrodoc, apellido1, apellido2, nombre1, nombre2, direccion,\n"
                    + "celular, telefono, email, codigoactividad, codigoestado, idpuesto, ca.descripcion "
                    + " from empleado e left join departamento d\n"
                    + " on e.codigoDepartamento = d.codigo\n"
                    + " left join municipio m\n"
                    + " on e.codigoDepartamento = m.codigoDepartamento \n"
                    + " and e.codigoMunicipio = m.codigoMunicipio  left join cargos ca on e.cargo = ca.codigoCargo \n";

            if (idDpto.equals("X")) {
                sql += " where e.codigodepartamento in(\n"
                        + " select codigodepartamento\n"
                        + " from usuario_departamento\n"
                        + " where usuario = '" + idUsuario + "')";
            } else {
                sql += " where e.codigodepartamento = '" + idDpto + "'"
                        + " and e.codigodepartamento in(\n"
                        + " select codigodepartamento\n"
                        + " from usuario_departamento\n"
                        + " where usuario = '" + idUsuario + "')";
            }

            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            int idEstado;
                            String estado = null;

                            String texto = res.getString("codigoDepartamento") + ";"
                                    + res.getString("dep") + ";"
                                    + res.getString("codigoMunicipio") + ";"
                                    + res.getString("mun") + ";"
                                    + res.getString("tipodoc") + ";"
                                    + res.getString("nrodoc") + ";"
                                    + res.getString("apellido1") + ";"
                                    + res.getString("apellido2") + ";"
                                    + res.getString("nombre1") + ";"
                                    + res.getString("nombre2") + ";"
                                    + res.getString("direccion") + ";"
                                    + res.getString("celular") + ";"
                                    + res.getString("telefono") + ";"
                                    + res.getString("email") + ";"
                                    + res.getString("codigoactividad") + ";";
                            idEstado = res.getInt("codigoestado");
                            if (idEstado == 0) {
                                estado = "EN PROCESO";
                            }
                            if (idEstado == 2) {
                                estado = "EN AUDITORIA";
                            }
                            if (idEstado == 3) {
                                estado = "INCONSISTENTE";
                            }
                            if (idEstado == 4) {
                                estado = "APROBADO";
                            }
                            texto += estado + ";";
                            texto += res.getString("idpuesto") + ";";
                            texto += res.getString("descripcion") + "\n";

                            result[0] = texto;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            texto += ((String) result[0]);

            int idEstado = 0;
            String estado = "";

            fwriter.write(texto);
            fwriter.flush();
            fwriter.close();

        } catch (Exception e) {
            Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void GeneraEstadoEnlaPrueba(String codigoDepto, int idPrueba, String fileNameExport) {

        String urlConvocatoria = System.getProperty("urlConvocatorias");
        String nombreArchivo = fileNameExport;
        final StringBuilder sb = new StringBuilder();

        String where = "";

        if (codigoDepto.equals("X")) {
            where = " ";
        } else {
            where = " depa.codigo = '" + codigoDepto + "' and ";
        }

        String texto = "nomprueba; nombre Nodo; estadoenprueba;"
                + "nomcargo; tipodoc; nrodoc;"
                + "apellido1; apellido2; nombre1; nombre2;"
                + "direccion; celular; email; fecha_disponibilidad_inicial;"
                + "fecha_disponibilidad_final; disponibilidad_viaje; disponibilidad_rural;"
                + "disponibilidad_urbana; trabajo_actual; disponibilidad_capcacitacion;"
                + "observaciones; fecha; hora; usuario;link \n";
        sb.append(texto);
        try {
            StringBuilder consulta = new StringBuilder();
            consulta.append("select pr.nombre as nomprueba, depa.nombre as nombreDepartamento, epp.codigo as estadoenprueba, ");
            consulta.append("ca.descripcion as nomcargo , em.tipodoc, em.nrodoc, ");
            consulta.append("em.apellido1, em.apellido2, em.nombre1, em.nombre2, ");
            consulta.append("em.direccion, em.celular, em.email, fecha_disponibilidad_inicial, ");
            consulta.append("fecha_disponibilidad_final, disponibilidad_viaje, disponibilidad_rural, ");
            consulta.append("disponibilidad_urbana, trabajo_actual, disponibilidad_capcacitacion, ");
            consulta.append("observaciones, eps.fecha, eps.hora, eps.usuario, ");
            consulta.append("concat('").append(urlConvocatoria).append("encuesta/index.jsp#/',md5(concat(eps.id,'-', eps.idempleado)),'/1') as link ");
            consulta.append("from empleado_x_prueba_x_estado eps left join empleado em ");
            consulta.append("on eps.idEmpleado = em.idEmpleado ");
            consulta.append("left join cargos ca ");
            consulta.append("on eps.codigocargo = ca.codigoCargo ");
            consulta.append("left join departamento depa ");
            consulta.append("on em.codigoDepartamento  = depa.codigo ");
            consulta.append("left join prueba pr ");
            consulta.append("on eps.idprueba = pr.idprueba ");
            consulta.append("left join estado_persona_prueba epp ");
            consulta.append("on eps.idestpersona = epp.idestpersona ");
            consulta.append("where ").append(where).append(" ");
            consulta.append(" eps.idprueba = ").append(idPrueba).append(" ");
            consulta.append("order by epp.idestpersona ");

            conex.consultar(consulta.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            sb.append(res.getString("nomprueba")).append(";");
                            sb.append(res.getString("nombreDepartamento")).append(";");
                            sb.append(res.getString("estadoenprueba")).append(";");
                            sb.append(res.getString("nomcargo")).append(";");
                            sb.append(res.getString("tipodoc")).append(";");
                            sb.append(res.getString("nrodoc")).append(";");
                            sb.append(validaNull(res.getString("apellido1"))).append(";");
                            sb.append(validaNull(res.getString("apellido2"))).append(";");
                            sb.append(validaNull(res.getString("nombre1"))).append(";");
                            sb.append(validaNull(res.getString("nombre2"))).append(";");
                            sb.append(validaNull(res.getString("direccion"))).append(";");
                            sb.append(validaNull(res.getString("celular"))).append(";");
                            sb.append(validaNull(res.getString("email"))).append(";");
                            sb.append(validaNull(res.getString("fecha_disponibilidad_inicial"))).append(";");
                            sb.append(validaNull(res.getString("fecha_disponibilidad_final"))).append(";");
                            sb.append(validaNull(res.getString("disponibilidad_viaje"))).append(";");
                            sb.append(validaNull(res.getString("disponibilidad_rural"))).append(";");
                            sb.append(validaNull(res.getString("disponibilidad_urbana"))).append(";");
                            sb.append(validaNull(res.getString("trabajo_actual"))).append(";");
                            sb.append(validaNull(res.getString("disponibilidad_capcacitacion"))).append(";");
                            sb.append(validaNull(res.getString("observaciones"))).append(";");
                            sb.append(validaNull(res.getString("fecha"))).append(";");
                            sb.append(validaNull(res.getString("hora"))).append(";");
                            sb.append(validaNull(res.getString("usuario"))).append(";");
                            sb.append(validaNull(res.getString("link"))).append(";\n");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            try (FileWriter fwriter = new FileWriter(nombreArchivo)) {
                fwriter.write(sb.toString());
                fwriter.flush();
            }

        } catch (Exception e) {
            Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void GeneraDetalleEncuesta(int idEvento, String fileNameExport) {

        String nombreArchivo = fileNameExport;
        final StringBuilder sb = new StringBuilder();

        String texto = "prueba;evento;nombreDepartamento;"
                + "codigoDepartamento;nombreMunicipio;"
                + "codigoMunicipio;codigoPuesto;nombrePuesto;"
                + "Todoestalisto;horallegadaOperador;horallegadaDelegado;"
                + "horaInicioSesion;horaFinSesion;horaDevolucionMaterial;"
                + "salonesListos;novedadesSitio;cantidadAsistio;"
                + "cantidadnoAsistio;cantidadAnulados;"
                + "motivosanulacion;novedades;novedadesSitio;"
                + "actualizado;\n";
        sb.append(texto);
        try {
            String sql = "select pu.nombre as nprueba, ev.nombre as nevento, \n"
                    + "divi.nombreDepartamento, divi.codigoDepartamento,\n"
                    + "divi.nombreMunicipio, divi.codigoMunicipio,\n"
                    + "divi.codigoPuesto, divi.nombrePuesto,\n"
                    + "estalisto, horallegada,horaLLegadaDelegado, \n"
                    + "horaInicioSesion, horaFinSesion, horaDevolucionMaterial, \n"
                    + "salonesListos, novedadesSitio,cantidadasistio, \n"
                    + "cantidadnoasistio, cantidadanulados,\n"
                    + "motivosanulacion, novedades, novedadesSitio, actualizado \n"
                    + "from encuesta en left join divipol divi\n"
                    + "on en.idDivipol = divi.idDivipol\n"
                    + "left join evento ev\n"
                    + "on en.codigoEvento = ev.codigoEvento\n"
                    + "left join prueba pu\n"
                    + "on ev.idprueba = pu.idprueba\n"
                    + "where en.codigoEvento = " + idEvento;

            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            sb.append(res.getString("nprueba") + ";");
                            sb.append(res.getString("nevento") + ";");
                            sb.append(res.getString("nombreDepartamento") + ";");
                            sb.append(res.getString("codigoDepartamento") + ";");
                            sb.append(res.getString("nombreMunicipio") + ";");
                            sb.append(res.getString("codigoMunicipio") + ";");
                            sb.append(validaNull(res.getString("codigoPuesto")) + ";");
                            sb.append(validaNull(res.getString("nombrePuesto")) + ";");
                            sb.append(validaNull(res.getString("estalisto")) + ";");
                            sb.append(validaNull(res.getString("horallegada")) + ";");
                            sb.append(validaNull(res.getString("horaLlegadaDelegado")) + ";");
                            sb.append(validaNull(res.getString("horaInicioSesion")) + ";");
                            sb.append(validaNull(res.getString("horaFinSesion")) + ";");
                            sb.append(validaNull(res.getString("horaDevolucionMaterial")) + ";");
                            sb.append(validaNull(res.getString("salonesListos")) + ";");
                            sb.append(validaNull(res.getString("novedadesSitio")).replace("\n", " ").replace(";", ",") + ";");
                            sb.append(validaNull(res.getString("cantidadasistio")) + ";");
                            sb.append(validaNull(res.getString("cantidadnoasistio")) + ";");
                            sb.append(validaNull(res.getString("cantidadanulados")) + ";");
                            sb.append(validaNull(res.getString("motivosanulacion")).replace("\n", " ").replace(";", ",") + ";");
                            sb.append(validaNull(res.getString("novedades")).replace("\n", " ").replace(";", ",") + ";");
                            sb.append(validaNull(res.getString("novedadesSitio")).replace("\n", " ").replace(";", ",") + ";");
                            sb.append(validaNull(res.getString("actualizado")) + ";\n");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            FileWriter fwriter = new FileWriter(nombreArchivo);
            fwriter.write(sb.toString());
            fwriter.flush();
            fwriter.close();

        } catch (Exception e) {
            Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void GeneraEstadoNombramientoAsistencia(String idDpto, int idPrueba) {

        final Object[] result = new Object[1];
        final StringBuilder texto = new StringBuilder();
        String nombreArchivo = "/data//Estado_Nombramiento_Asistencia" + idDpto + ".csv";

        texto.append("Nombre Departamento;Nombre Municipio;C??digo Puesto;Nombre Puesto;Ubicaci??n;C??digo Cargo;Cargo;Evento;Id Empleado;Email;Telefono;Persona;Sal??n;Fecha Registro;MedioAsistencia\n");
        try {
            String s1 = Integer.toString(idPrueba);
            StringBuilder sql = new StringBuilder();
            sql.append(" select dv.nombredepartamento,dv.nombremunicipio,dv.codigopuesto,dv.nombrepuesto,n.ubicacion,car.codigocargo,car.descripcion,ev.nombre,e.nrodoc, ");
            sql.append(" ifnull(e.email,e.email2) as email,ifnull(e.celular,e.telefono) as telefono, ");
            sql.append(" concat(ifnull(e.nombre1,''),' ',ifnull(e.nombre2,''),' ',ifnull(e.apellido1,''),' ',ifnull(e.apellido2,'')) persona, d.salon, a.fecharegistro, ");
            sql.append(" case when a.biometrico=1 then 'Biometrico' when a.biometrico=0 then 'Click' when a.biometrico is null then 'Pendiente de Registro' end as MedioAsistencia ");
            sql.append(" from detalle_cargo_x_puesto_x_evento d ");
            sql.append(" left join empleado e on e.nrodoc = d.nrodoc ");
            sql.append(" left join divipol dv on dv.iddivipol = d.iddivipol ");
            sql.append(" left join cargos car on car.codigocargo = d.codigocargo ");
            sql.append(" left join evento ev on ev.codigoevento = d.codigoevento ");
            sql.append(" left outer join nombramiento n on n.nrodoc=e.nrodoc and d.codigoPuesto=n.codigoPuesto and d.codigoCargo=n.codigoCargo and d.ubicacion=n.ubicacion ");
            sql.append("                        and d.iddivipol=n.iddivipol and n.codigocargo=car.codigocargo and n.codigodepartamento=d.codigodepartamento ");
            sql.append(" left outer join asistencia a on a.codigoevento = d.codigoevento and a.iddivipol=d.iddivipol and a.idempleado=e.idempleado ");
            sql.append(" left outer join asignacion_salon an on an.idprueba=d.idprueba and an.iddivipol=d.iddivipol and an.idempleado=e.idempleado ");
            sql.append(" where d.idprueba=").append(s1);
            if (idDpto.length() > 1) {
                sql.append(" and d.codigoDepartamento='").append(idDpto.trim()).append("' ");
            }
            sql.append(" order by dv.nombredepartamento,dv.nombremunicipio,dv.nombrepuesto,car.descripcion,e.idempleado,ev.fecha,ev.hora_inicial,car.descripcion,ev.nombre ");
            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            texto.append(validaNull(res.getString("nombredepartamento")) + ";");
                            texto.append(validaNull(res.getString("nombremunicipio")) + ";");
                            texto.append(validaNull(res.getString("codigopuesto")) + ";");
                            texto.append(validaNull(res.getString("nombrepuesto")) + ";");
                            texto.append(validaNull(res.getString("ubicacion")) + ";");
                            texto.append(validaNull(res.getString("codigocargo")) + ";");
                            texto.append(validaNull(res.getString("descripcion")) + ";");
                            texto.append(validaNull(res.getString("nombre")) + ";");
                            texto.append(validaNull(res.getString("nrodoc")) + ";");
                            texto.append(validaNull(res.getString("email")) + ";");
                            texto.append(validaNull(res.getString("telefono")) + ";");
                            texto.append(validaNull(res.getString("persona")) + ";");
                            texto.append(validaNull(res.getString("salon")) + ";");
                            texto.append(validaNull(res.getString("fecharegistro")) + ";");
                            texto.append(validaNull(res.getString("MedioAsistencia")) + ";\n");
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

    public void GeneraDetalladoPagos(int idPrueba) {

        final StringBuilder texto = new StringBuilder();
        String nombreArchivo = "/data//Detallado_Pagos" + ".csv";

        texto.append("C??digo Departamento;Nombre Departamento;Codigo Municipio;NombreMunicipio;NombrePuesto;Sitio;Nrodoc;Nombre;Neto;NValor;Valor;Vbase;Direccion;Telefono;Celular;Email;nDescripcionTarifa;ncargo;fecha;codigoCargo;codigoEvento;idEmpleado;nombrePrueba;aplica;fecha_confirmacion;textoRetefuente;textoReteica;retefuente;reteica;asistio;nombresesion\n");
        try {
            String s1 = Integer.toString(idPrueba);
            StringBuilder sql = new StringBuilder();
            sql.append("    SELECT dcpe.sesion,dcpe.codigoDepartamento, dep.nombre as nombreDepartamento,  ");
            sql.append("    dcpe.codigoMunicipio, mun.nombre as nombreMunicipio, divi.nombrePuesto,  ");
            sql.append("    dcpe.codigoPuesto as sitio, e.nrodoc,  ");
            sql.append("    concat(ifnull(nombre1,''),' ',ifnull(nombre2,''),' ',ifnull(apellido1,''),' ',ifnull(apellido2,'')) nombre,(tbase.base + ifnull(dcpe.vr_interno,0))  as Neto,");
            sql.append("    case dcpe.sesion when 1 then ps.valor else dcpe.vr_interno end as nvalor,  ");
            sql.append("    case dcpe.sesion when 1 then (round((case tbase.aplica when 1 then (case ps.aplicaretencion when 1 then (ps.valor/(100-case e.codigomunicipio when '11001' then pcs.porcentaje else pci.porcentaje end)*100) else (ps.valor) end ) else (ps.valor) end),0) * 1) else dcpe.vr_interno end as  valor,");
            sql.append("    case dcpe.sesion when 1 then (round((case tbase.aplica when 1 then (case ps.aplicaretencion when 1 then (ps.valor/(100-case e.codigomunicipio when '11001' then pcs.porcentaje else pci.porcentaje end)*100) else 0 end ) else 0 end),0) * 1) else dcpe.vr_interno end as vbase,  ");
            sql.append("    e.direccion, e.telefono, e.celular, e.email, ");
            sql.append("    case dcpe.sesion when 1 then ps.descripcion else 'VIATICO TRANSPORTE' end as nDescripcionTarifa, car.descripcion as ncargo,                                           ");
            sql.append("    even.fecha, tbase.codigoCargo,dcpe.codigoEvento, e.idEmpleado, p.nombre as nombrePrueba,tbase.aplica,  ");
            sql.append("    (SELECT t.fecha_confirmacion FROM tarea_confirmacion t where t.tipo='contrato' and t.id_prueba=tbase.idprueba and  ");
            sql.append("    t.id_empleado=e.idempleado and t.cod_cargo=tbase.codigocargo ) as fecha_confirmacion,  ");
            sql.append("    (select concat('RETEFUENTE ', round(porcentaje,2)) from parametros_impuestos parimp where parimp.concepto = 'RETENCION' ) as textoRetefuente,  ");
            sql.append("    (select concat('RETEICA ',case e.codigomunicipio when '11001' then porcentaje else 0 end) from parametros_impuestos parimp where parimp.concepto = 'RETICA' ) as textoReteica,  ");
            sql.append("    (select porcentaje from parametros_impuestos parimp where parimp.concepto = 'RETENCION')*tbase.aplica as retefuente,  ");
            sql.append("    (select case e.codigomunicipio when '11001' then porcentaje else 0 end as porcentaje from parametros_impuestos parimp where parimp.concepto ='RETICA')*tbase.aplica as reteica,  ");
            sql.append("    fun_asistio(e.idempleado,even.idprueba,ps.codigocargo,ps.codigoevento) as asistio,");
            sql.append("    case dcpe.sesion when 1 then ps.descripcion else 'VIATICO TRANSPORTE' end as nombresesion  ");
            sql.append("    from empleado e,evento even,pagoxsesion ps,cargos car, departamento dep,municipio mun,divipol divi, prueba p,");
            sql.append("    			( select even.idprueba,(select min(ex.codigoCargo) from detalle_cargo_x_puesto_x_evento ex where ex.nrodoc=e.nrodoc and ex.idprueba=even.idprueba) as codigoCargo,");
            sql.append("                           e.nrodoc,e.idempleado,case when ifnull(sum(ps.valor *1),0) > base then 1 else 0 end as aplica, ifnull(sum(ps.valor *1),0) as base						 ");
            sql.append("    			from empleado e,evento even,detalle_cargo_x_puesto_x_evento dcpe,pagoxsesion ps,cargos car,parametros_impuestos parimp  ");
            sql.append("    			where even.idprueba=").append(s1);
            sql.append("    			and e.nrodoc = dcpe.nrodoc  ");
            sql.append("    			and even.codigoevento=dcpe.codigoevento ");
            sql.append("    			and car.codigocargo=dcpe.codigocargo  ");
            sql.append("    			and ps.codigoevento=even.codigoevento  ");
            sql.append("    			and ps.codigocargo=dcpe.codigocargo");
            sql.append("    			and ps.aplicaretencion = 1  ");
            sql.append("    			and ps.idPasoXSesion=(case ps.salonhasta when 0 then ps.idPasoXSesion else fun_tarifa(e.idempleado,even.idprueba,ps.codigocargo,ps.codigoevento) end)  ");
            sql.append("    			and parimp.concepto='RETENCION'  ");
            sql.append("    			group by even.idprueba,codigoCargo,e.nrodoc,e.idempleado  ");
            sql.append("    			)tbase,");
            sql.append("          ( select sesion,id,idDivipol,idPrueba,codigoDepartamento,codigoMunicipio,codigoZona,codigoPuesto,codigoCargo,codigoEvento,ubicacion,nrodoc,estado,usuario,fecha,asistio,cantidadasistio,cantidadnoasistio,codigotarifa,observacion,consecutivo,orden,fecha_actualiza,asistenciaBiometrica,fechaAsistencia,idOrden,devuelto,nsesion,vr_interno ");
            sql.append("            from  (select 1 as sesion,id,idDivipol,idPrueba,codigoDepartamento,codigoMunicipio,codigoZona,codigoPuesto,codigoCargo,codigoEvento,ubicacion,nrodoc,estado,usuario,fecha,asistio,cantidadasistio,cantidadnoasistio,codigotarifa,observacion,consecutivo,orden,fecha_actualiza,asistenciaBiometrica,fechaAsistencia,idOrden,devuelto,'SESION' as nsesion, ");
            sql.append("                        (select ifnull(dtr.vr_interno,0) from empleado e,divitrans dtr,cargos c,divipol div1 ");
            sql.append("                         where d.nrodoc=e.nrodoc and d.idDivipol=div1.idDivipol ");
            sql.append("                         and e.DaneMunicipio=div1.codigoMunicipio and d.idprueba=dtr.idPrueba and c.codigoCargo=d.codigoCargo and c.viaticos=1 ");
            sql.append("                         and d.idprueba=dtr.idprueba and dtr.div_origen = dtr.div_destino and dtr.div_destino=div1.codigoMunicipio limit 1) as vr_interno ");
            sql.append("                   from detalle_cargo_x_puesto_x_evento d ");
            sql.append("                   where d.idprueba=").append(s1);
            sql.append("                   union all ");
            sql.append("                   select d2.sesion,d2.id,d2.idDivipol,d2.idPrueba,d2.codigoDepartamento,d2.codigoMunicipio,d2.codigoZona,d2.codigoPuesto,d2.codigoCargo,d2.codigoEvento,d2.ubicacion,d2.nrodoc,d2.estado,d2.usuario,d2.fecha,d2.asistio,d2.cantidadasistio,d2.cantidadnoasistio,d2.codigotarifa,d2.observacion,d2.consecutivo,d2.orden,d2.fecha_actualiza,d2.asistenciaBiometrica,d2.fechaAsistencia,d2.idOrden,d2.devuelto,d2.nsesion,d2.vr_interno ");
            sql.append("                   from (select 2 as sesion,d1.id,d1.idDivipol,d1.idPrueba,d1.codigoDepartamento,d1.codigoMunicipio,d1.codigoZona,d1.codigoPuesto,d1.codigoCargo,d1.codigoEvento,d1.ubicacion,d1.nrodoc,d1.estado,d1.usuario,d1.fecha,d1.asistio,d1.cantidadasistio,d1.cantidadnoasistio,d1.codigotarifa,d1.observacion,d1.consecutivo,d1.orden,d1.fecha_actualiza,d1.asistenciaBiometrica,d1.fechaAsistencia,d1.idOrden,d1.devuelto,'VIATICO TRANSPORTE' as nsesion,vr_interno ");
            sql.append("                         from detalle_cargo_x_puesto_x_evento d1,divipol div1,empleado e,cargos c,divitrans ");
            sql.append("                          where d1.idDivipol=div1.idDivipol and e.nrodoc=d1.nrodoc and divitrans.div_destino=d1.codigoMunicipio and divitrans.idprueba=d1.idprueba and d1.idprueba= ").append(s1);
            sql.append("                          and e.DaneMunicipio=div1.codigoMunicipio and c.codigoCargo=d1.codigoCargo and c.viaticos=1 ");
            sql.append("                          and div_origen = div_destino and div_destino=div1.codigoMunicipio ");
            sql.append("                          and d1.id = (select d3.id from detalle_cargo_x_puesto_x_evento d3 where d3.idprueba=d1.idprueba and d3.nrodoc=d1.nrodoc limit 1)) d2 ) f ");
            sql.append("  					) dcpe, ");
            sql.append("    (select sum(porcentaje) as porcentaje from parametros_impuestos parimp where parimp.concepto in ('RETENCION','RETICA')) pcs, ");
            sql.append("    (select sum(porcentaje) as porcentaje from parametros_impuestos parimp where parimp.concepto in ('RETENCION')) pci  ");
            sql.append("    where e.idempleado = tbase.idempleado  ");
            sql.append("    and e.nrodoc = tbase.nrodoc  ");
            sql.append("    and even.idprueba= tbase.idprueba  ");
            sql.append("    and p.idprueba=tbase.idprueba  ");
            sql.append("    and dcpe.codigoevento=even.codigoevento  ");
            sql.append("    and dcpe.codigodepartamento=dep.codigo  ");
            sql.append("    and dcpe.codigoMunicipio=mun.codigoMunicipio  ");
            sql.append("    and dcpe.idDivipol=divi.idDivipol  ");
            sql.append("    and dcpe.nrodoc=tbase.nrodoc  ");
            sql.append("    and car.codigocargo=tbase.codigoCargo");
            sql.append("    and ps.codigoevento=even.codigoevento  ");
            sql.append("    and ps.codigocargo=dcpe.codigocargo");
            sql.append("    and ps.idPasoXSesion=(case ps.salonhasta when 0 then ps.idPasoXSesion else fun_tarifa(e.idempleado,even.idprueba,ps.codigoCargo,ps.codigoevento) end) order by e.nrodoc ");
            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            texto.append(validaNull(res.getString("codigoDepartamento")) + ";");
                            texto.append(validaNull(res.getString("nombreDepartamento")) + ";");
                            texto.append(validaNull(res.getString("codigoMunicipio")) + ";");
                            texto.append(validaNull(res.getString("nombreMunicipio")) + ";");
                            texto.append(validaNull(res.getString("nombrePuesto")) + ";");
                            texto.append(validaNull(res.getString("sitio")) + ";");
                            texto.append(validaNull(res.getString("nrodoc")) + ";");
                            texto.append(validaNull(res.getString("nombre")) + ";");
                            texto.append(validaNull(res.getString("Neto")) + ";");
                            texto.append(validaNull(res.getString("nvalor")) + ";");
                            texto.append(validaNull(res.getString("valor")) + ";");
                            texto.append(validaNull(res.getString("vbase")) + ";");
                            texto.append(validaNull(res.getString("direccion")) + ";");
                            texto.append(validaNull(res.getString("telefono")) + ";");
                            texto.append(validaNull(res.getString("celular")) + ";");
                            texto.append(validaNull(res.getString("email")) + ";");
                            texto.append(validaNull(res.getString("nDescripcionTarifa")) + ";");
                            texto.append(validaNull(res.getString("ncargo")) + ";");
                            texto.append(validaNull(res.getString("fecha")) + ";");
                            texto.append(validaNull(res.getString("codigoCargo")) + ";");
                            texto.append(validaNull(res.getString("codigoEvento")) + ";");
                            texto.append(validaNull(res.getString("idEmpleado")) + ";");
                            texto.append(validaNull(res.getString("nombrePrueba")) + ";");
                            texto.append(validaNull(res.getString("aplica")) + ";");
                            texto.append(validaNull(res.getString("fecha_confirmacion")) + ";");
                            texto.append(validaNull(res.getString("textoRetefuente")) + ";");
                            texto.append(validaNull(res.getString("textoReteica")) + ";");
                            texto.append(validaNull(res.getString("retefuente")) + ";");
                            texto.append(validaNull(res.getString("reteica")) + ";");
                            texto.append(validaNull(res.getString("asistio")) + ";");
                            texto.append(validaNull(res.getString("nombresesion")) + ";\n");
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

    public void GeneraPagoCedulaCargo(int idPrueba) {

        final StringBuilder texto = new StringBuilder();
        String nombreArchivo = "/data//Pagos" + ".csv";

        texto.append("nrodoc;nombre;Cargo;PagoNeto;baseretencion;retefuente;reteica;direccion;telefono;celular;email;nombrePrueba;AplicaRentenci??n\n");
        try {
            String s1 = Integer.toString(idPrueba);
            StringBuilder sql = new StringBuilder();
            sql.append("    select nrodoc,nombre,Ncargo as Cargo,Neto as PagoNeto,sum(vbase) as baseretencion,round((sum(vbase)*retefuente/100),2) as retefuente,round((sum(vbase)*reteica/100),2) as reteica,direccion,telefono,celular,email,nombrePrueba,aplica as AplicaRentencion From ( ");
            sql.append("    SELECT dcpe.sesion,dcpe.codigoDepartamento, dep.nombre as nombreDepartamento,  ");
            sql.append("    dcpe.codigoMunicipio, mun.nombre as nombreMunicipio, divi.nombrePuesto,  ");
            sql.append("    dcpe.codigoPuesto as sitio, e.nrodoc,  ");
            sql.append("    concat(ifnull(nombre1,''),' ',ifnull(nombre2,''),' ',ifnull(apellido1,''),' ',ifnull(apellido2,'')) nombre,(tbase.base + ifnull(dcpe.vr_interno,0))  as Neto,");
            sql.append("    case dcpe.sesion when 1 then ps.valor else dcpe.vr_interno end as nvalor,  ");
            sql.append("    case dcpe.sesion when 1 then (round((case tbase.aplica when 1 then (case ps.aplicaretencion when 1 then (ps.valor/(100-case e.codigomunicipio when '11001' then pcs.porcentaje else pci.porcentaje end)*100) else (ps.valor) end ) else (ps.valor) end),0) * 1) else dcpe.vr_interno end as  valor,");
            sql.append("    case dcpe.sesion when 1 then (round((case tbase.aplica when 1 then (case ps.aplicaretencion when 1 then (ps.valor/(100-case e.codigomunicipio when '11001' then pcs.porcentaje else pci.porcentaje end)*100) else 0 end ) else 0 end),0) * 1) else dcpe.vr_interno end as vbase,  ");
            sql.append("    e.direccion, e.telefono, e.celular, e.email, ");
            sql.append("    case dcpe.sesion when 1 then ps.descripcion else 'VIATICO TRANSPORTE' end as nDescripcionTarifa, car.descripcion as ncargo,                                           ");
            sql.append("    even.fecha, tbase.codigoCargo,dcpe.codigoEvento, e.idEmpleado, p.nombre as nombrePrueba,tbase.aplica,  ");
            sql.append("    (SELECT t.fecha_confirmacion FROM tarea_confirmacion t where t.tipo='contrato' and t.id_prueba=tbase.idprueba and  ");
            sql.append("    t.id_empleado=e.idempleado and t.cod_cargo=tbase.codigocargo ) as fecha_confirmacion,  ");
            sql.append("    (select concat('RETEFUENTE ', round(porcentaje,2)) from parametros_impuestos parimp where parimp.concepto = 'RETENCION' ) as textoRetefuente,  ");
            sql.append("    (select concat('RETEICA ',case e.codigomunicipio when '11001' then porcentaje else 0 end) from parametros_impuestos parimp where parimp.concepto = 'RETICA' ) as textoReteica,  ");
            sql.append("    (select porcentaje from parametros_impuestos parimp where parimp.concepto = 'RETENCION')*tbase.aplica as retefuente,  ");
            sql.append("    (select case e.codigomunicipio when '11001' then porcentaje else 0 end as porcentaje from parametros_impuestos parimp where parimp.concepto ='RETICA')*tbase.aplica as reteica,  ");
            sql.append("    fun_asistio(e.idempleado,even.idprueba,ps.codigocargo,ps.codigoevento) as asistio,");
            sql.append("    case dcpe.sesion when 1 then ps.descripcion else 'VIATICO TRANSPORTE' end as nombresesion  ");
            sql.append("    from empleado e,evento even,pagoxsesion ps,cargos car, departamento dep,municipio mun,divipol divi, prueba p,");
            sql.append("    			( select even.idprueba,(select min(ex.codigoCargo) from detalle_cargo_x_puesto_x_evento ex where ex.nrodoc=e.nrodoc and ex.idprueba=even.idprueba) as codigoCargo,");
            sql.append("                           e.nrodoc,e.idempleado,case when ifnull(sum(ps.valor *1),0) > base then 1 else 0 end as aplica, ifnull(sum(ps.valor *1),0) as base						 ");
            sql.append("    			from empleado e,evento even,detalle_cargo_x_puesto_x_evento dcpe,pagoxsesion ps,cargos car,parametros_impuestos parimp  ");
            sql.append("    			where even.idprueba=").append(s1);
            sql.append("    			and e.nrodoc = dcpe.nrodoc  ");
            sql.append("    			and even.codigoevento=dcpe.codigoevento ");
            sql.append("    			and car.codigocargo=dcpe.codigocargo  ");
            sql.append("    			and ps.codigoevento=even.codigoevento  ");
            sql.append("    			and ps.codigocargo=dcpe.codigocargo");
            sql.append("    			and ps.aplicaretencion = 1  ");
            sql.append("    			and ps.idPasoXSesion=(case ps.salonhasta when 0 then ps.idPasoXSesion else fun_tarifa(e.idempleado,even.idprueba,ps.codigocargo,ps.codigoevento) end)  ");
            sql.append("    			and parimp.concepto='RETENCION'  ");
            sql.append("    			group by even.idprueba,codigoCargo,e.nrodoc,e.idempleado  ");
            sql.append("    			)tbase,");
            sql.append("          ( select sesion,id,idDivipol,idPrueba,codigoDepartamento,codigoMunicipio,codigoZona,codigoPuesto,codigoCargo,codigoEvento,ubicacion,nrodoc,estado,usuario,fecha,asistio,cantidadasistio,cantidadnoasistio,codigotarifa,observacion,consecutivo,orden,fecha_actualiza,asistenciaBiometrica,fechaAsistencia,idOrden,devuelto,nsesion,vr_interno ");
            sql.append("            from  (select 1 as sesion,id,idDivipol,idPrueba,codigoDepartamento,codigoMunicipio,codigoZona,codigoPuesto,codigoCargo,codigoEvento,ubicacion,nrodoc,estado,usuario,fecha,asistio,cantidadasistio,cantidadnoasistio,codigotarifa,observacion,consecutivo,orden,fecha_actualiza,asistenciaBiometrica,fechaAsistencia,idOrden,devuelto,'SESION' as nsesion, ");
            sql.append("                        (select ifnull(dtr.vr_interno,0) from empleado e,divitrans dtr,cargos c,divipol div1 ");
            sql.append("                         where d.nrodoc=e.nrodoc and d.idDivipol=div1.idDivipol ");
            sql.append("                         and e.DaneMunicipio=div1.codigoMunicipio and d.idprueba=dtr.idPrueba and c.codigoCargo=d.codigoCargo and c.viaticos=1 ");
            sql.append("                         and dtr.div_origen = dtr.div_destino and dtr.div_destino=div1.codigoMunicipio limit 1) as vr_interno ");
            sql.append("                   from detalle_cargo_x_puesto_x_evento d ");
            sql.append("                   where d.idprueba=").append(s1);
            sql.append("                   union all ");
            sql.append("                   select d2.sesion,d2.id,d2.idDivipol,d2.idPrueba,d2.codigoDepartamento,d2.codigoMunicipio,d2.codigoZona,d2.codigoPuesto,d2.codigoCargo,d2.codigoEvento,d2.ubicacion,d2.nrodoc,d2.estado,d2.usuario,d2.fecha,d2.asistio,d2.cantidadasistio,d2.cantidadnoasistio,d2.codigotarifa,d2.observacion,d2.consecutivo,d2.orden,d2.fecha_actualiza,d2.asistenciaBiometrica,d2.fechaAsistencia,d2.idOrden,d2.devuelto,d2.nsesion,d2.vr_interno ");
            sql.append("                   from (select 2 as sesion,d1.id,d1.idDivipol,d1.idPrueba,d1.codigoDepartamento,d1.codigoMunicipio,d1.codigoZona,d1.codigoPuesto,d1.codigoCargo,d1.codigoEvento,d1.ubicacion,d1.nrodoc,d1.estado,d1.usuario,d1.fecha,d1.asistio,d1.cantidadasistio,d1.cantidadnoasistio,d1.codigotarifa,d1.observacion,d1.consecutivo,d1.orden,d1.fecha_actualiza,d1.asistenciaBiometrica,d1.fechaAsistencia,d1.idOrden,d1.devuelto,'VIATICO TRANSPORTE' as nsesion,vr_interno ");
            sql.append("                         from detalle_cargo_x_puesto_x_evento d1,divipol div1,empleado e,cargos c,divitrans ");
            sql.append("                          where d1.idDivipol=div1.idDivipol and e.nrodoc=d1.nrodoc and divitrans.div_destino=d1.codigoMunicipio and divitrans.idprueba=d1.idprueba and d1.idprueba= ").append(s1);
            sql.append("                          and e.DaneMunicipio=div1.codigoMunicipio and c.codigoCargo=d1.codigoCargo and c.viaticos=1 ");
            sql.append("                          and div_origen = div_destino and div_destino=div1.codigoMunicipio ");
            sql.append("                          and d1.id = (select d3.id from detalle_cargo_x_puesto_x_evento d3 where d3.idprueba=d1.idprueba and d3.nrodoc=d1.nrodoc limit 1)) d2 ) f ");
            sql.append("  					) dcpe, ");
            sql.append("    (select sum(porcentaje) as porcentaje from parametros_impuestos parimp where parimp.concepto in ('RETENCION','RETICA')) pcs, ");
            sql.append("    (select sum(porcentaje) as porcentaje from parametros_impuestos parimp where parimp.concepto in ('RETENCION')) pci  ");
            sql.append("    where e.idempleado = tbase.idempleado  ");
            sql.append("    and e.nrodoc = tbase.nrodoc  ");
            sql.append("    and even.idprueba= tbase.idprueba  ");
            sql.append("    and p.idprueba=tbase.idprueba  ");
            sql.append("    and dcpe.codigoevento=even.codigoevento  ");
            sql.append("    and dcpe.codigodepartamento=dep.codigo  ");
            sql.append("    and dcpe.codigoMunicipio=mun.codigoMunicipio  ");
            sql.append("    and dcpe.idDivipol=divi.idDivipol  ");
            sql.append("    and dcpe.nrodoc=tbase.nrodoc  ");
            sql.append("    and car.codigocargo=tbase.codigoCargo");
            sql.append("    and ps.codigoevento=even.codigoevento  ");
            sql.append("    and ps.codigocargo=dcpe.codigocargo");
            sql.append("    and ps.idPasoXSesion=(case ps.salonhasta when 0 then ps.idPasoXSesion else fun_tarifa(e.idempleado,even.idprueba,ps.codigoCargo,ps.codigoevento) end) ");
            sql.append("  )Detallado ");
            sql.append("  group by nrodoc,nombre,Ncargo,Neto,direccion,telefono,celular,email,  ");
            sql.append("  nombrePrueba,aplica  ");
            sql.append("  order by nrodoc ");
            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            //System.out.println(res.getString("idpuesto"));
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

    public void GeneraListadoPagoSitio(String usuario, final int idPrueba) {

        final Object[] result = new Object[1];
        final StringBuilder texto = new StringBuilder();
        String nombreArchivo = "/data//LISTADO_PAGOS_SITIOS_" + idPrueba + ".csv";

        texto.append("C??digo Sitio;Nombre Sitio;Archivo Subido\n");
        try {
            StringBuilder sql = new StringBuilder();

            sql.append(" select  b.codigopuesto, b.nombrepuesto as puesto \n");
            sql.append(" from usuario_sitio a left join divipol b\n");
            sql.append(" on a.iddivipol = b.idDivipol \n");
            sql.append(" where usuario = '").append(usuario);
            sql.append("' and b.idPrueba = ").append(idPrueba);

            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            texto.append(validaNull(res.getString("codigopuesto")) + ";");
                            texto.append(validaNull(res.getString("puesto")) + ";");

                            String sFichero = "/data/Elecciones/img/listas/pagos/" + idPrueba + "/" + res.getString("codigopuesto") + ".pdf";
                            File f = new File(sFichero);
                            if (f.exists()) {
                                texto.append("Si" + ";\n");
                            } else {
                                texto.append("No" + ";\n");
                            }

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

    public void GeneraListadoAsistenciaSitioEvento(String usuario, final int idPrueba, final String codEvento) {

        final Object[] result = new Object[1];
        final StringBuilder texto = new StringBuilder();
        String nombreArchivo = "/data//LISTADO_ASISTENCIA_SITIOS_" + idPrueba + "_" + codEvento + ".csv";

        texto.append("C??digo Sitio;Nombre Sitio; Evento; Archivo Subido\n");
        try {
            StringBuilder sql = new StringBuilder();

            sql.append(" select  b.codigopuesto, b.nombrepuesto as puesto, \n");
            sql.append("(select ev.nombre from evento ev where  ev.codigoEvento='");
            sql.append(codEvento).append("') as evento");
            sql.append(" from usuario_sitio a left join divipol b\n");
            sql.append(" on a.iddivipol = b.idDivipol \n");
            sql.append(" where usuario = '").append(usuario);
            sql.append("' and b.idPrueba = ").append(idPrueba);

            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            texto.append(validaNull(res.getString("codigopuesto")) + ";");
                            texto.append(validaNull(res.getString("puesto")) + ";");
                            texto.append(validaNull(res.getString("evento")) + ";");
                            String sFichero = "/data/Elecciones/img/listas/Listas_de_Asistencia/" + idPrueba + "/" + codEvento + "/" + res.getString("codigopuesto") + ".pdf";
                            File f = new File(sFichero);
                            if (f.exists()) {
                                texto.append("Si" + ";\n");
                            } else {
                                texto.append("No" + ";\n");
                            }

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

    public void listarEmpleados(String fileNameExport, String dpto) {

        String nombreArchivo = fileNameExport;
        final StringBuilder text = new StringBuilder();

        String texto = "Codigo Departamento;Nombre Departamento;Codigo Municipio;Nombre Municipio;"
                + "Tipo Identificacion;Numero Identificacion;Apellido1;Apellido2;Nombre1;Nombre2;Direccion;"
                + "Celular;telefono;email;Codigo Actividad; Cargo Elecciones 2015 ; Cedula y RUT; Estado ; Observaciones \n";
        text.append(texto);
        StringBuilder sql = new StringBuilder();
        try {

            sql.append("select m.codigoDepartamento, d.nombre as dep, e.DaneMunicipio, m.nombre as mun,");
            sql.append(" e.tipodoc, e.nrodoc, e.apellido1, e.apellido2, e.nombre1, e.nombre2, e.direccion, e.observacion, ");
            sql.append(" e.celular, e.telefono, e.email, e.codigoactividad,  ca.descripcion, e.imgdociden, e.imgrut, est.descripcion as estado ");
            sql.append(" from empleado e ");
            sql.append(" left join municipio_dane m on e.DaneMunicipio= m.codigoMunicipio ");
            sql.append(" left join departamento_dane d on  m.codigoDepartamento =d.codigo ");
            sql.append(" left join cargos ca on e.cargo = ca.codigoCargo ");
            sql.append(" left join estado est on est.codigoEstado=e.codigoEstado ");
            if (!dpto.isEmpty()) {
                sql.append(" where e.codigoDepartamento=").append(dpto);
            }
            sql.append(" order by d.nombre, m.nombre ");

            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            int idEstado;
                            String estado = null;

                            text.append(validaNull(res.getString("codigoDepartamento"))).append(";");
                            text.append(validaNull(res.getString("dep"))).append(";");
                            text.append(validaNull(res.getString("DaneMunicipio"))).append(";");
                            text.append(validaNull(res.getString("mun"))).append(";");
                            text.append(res.getString("tipodoc")).append(";");
                            text.append(res.getString("nrodoc")).append(";");
                            text.append(res.getString("apellido1")).append(";");
                            text.append(validaNull(res.getString("apellido2"))).append(";");
                            text.append(res.getString("nombre1")).append(";");
                            text.append(validaNull(res.getString("nombre2"))).append(";");
                            text.append(validaNull(res.getString("direccion")).replace("\n", " ")).append(";");
                            text.append(validaNull(res.getString("celular"))).append(";");
                            text.append(validaNull(res.getString("telefono"))).append(";");
                            text.append(validaNull(res.getString("email"))).append(";");
                            text.append(validaNull(res.getString("codigoactividad"))).append(";");
                            text.append(validaNull(res.getString("descripcion"))).append(";");
                            text.append(res.getInt("imgdociden") == 0 ? "NO" : "SI").append(";");
                            text.append(validaNull(res.getString("estado"))).append(";");
                            text.append(validaNull(res.getString("observacion"))).append(";\n");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            FileWriter fwriter = new FileWriter(nombreArchivo);
            fwriter.write(text.toString());
            fwriter.flush();
            fwriter.close();

        } catch (Exception e) {
            Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void listarEmpleadosFirmaCuentaCobro(String fileNameExport, String dpto, int idPrueba) {

        String nombreArchivo = fileNameExport;
        final StringBuilder text = new StringBuilder();

        String texto = "Numero Identificacion;Apellidos ;Nombres;Celular;email\n ";
        text.append(texto);
        StringBuilder sql = new StringBuilder();
        try {

            sql.append(" select e.nrodoc, concat(e.apellido1,' ', e.apellido2) as apellidos,");
            sql.append(" concat(e.nombre1,' ',e.nombre2) as nombres, e.celular, e.email ");
            sql.append(" from tarea_confirmacion t ");
            sql.append(" inner join empleado e on e.idEmpleado=t.id_empleado ");
            sql.append(" where t.id_prueba=").append(idPrueba);
            if (!dpto.isEmpty()) {
                sql.append(" and e.codigoDepartamento=").append(dpto);
            }
            sql.append(" order by apellidos");

            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            text.append(res.getString("nrodoc")).append(";");
                            text.append(validaNull(res.getString("apellidos"))).append(";");
                            text.append(validaNull(res.getString("nombres"))).append(";");
                            text.append(validaNull(res.getString("celular"))).append(";");
                            text.append(validaNull(res.getString("email"))).append(";\n");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            FileWriter fwriter = new FileWriter(nombreArchivo);
            fwriter.write(text.toString());
            fwriter.flush();
            fwriter.close();

        } catch (Exception e) {
            Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void generaListadoEstadoPersonas(String nombreArchivo, final int idPrueba, String codigoDepartamento, String codigoMunicipio, int estado) {

        final Object[] result = new Object[1];
        final StringBuilder texto = new StringBuilder();

        texto.append("Identificaci??n;Nombres;Apellidos;Celular;Departamento;Municipio;C??dula y Rut; Estado;Observaciones\n");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" select distinct d.nrodoc, e.nombre1, e.nombre2, e.apellido1, e.apellido2, e.celular, e.email, ");
            sql.append(" e.imgdociden,  es.descripcion as estado, e.observacion, m.nombre as nombremunicipio, dep.nombre as nombredepartamento ");
            sql.append(" from detalle_cargo_x_puesto_x_evento d ");
            sql.append(" inner join empleado e on e.nrodoc=d.nrodoc ");
            sql.append(" inner join evento ev on ev.codigoEvento=d.codigoEvento ");
            sql.append(" inner join municipio_dane m on m.codigoMunicipio=e.DaneMunicipio ");
            sql.append(" inner join departamento_dane dep on dep.codigo=m.codigoDepartamento ");
            sql.append(" inner join estado es on es.codigoEstado=e.codigoEstado ");
            sql.append(" where ev.esCapacitacion=0 and ev.tipoSesion!='ADMINISTRATIVOS' and ev.idprueba=").append(idPrueba);
            sql.append(" and e.idEmpleado in (select distinct idempleado from asistencia asis inner join evento even on even.codigoEvento=asis.codigoevento where even.idprueba=");
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

            conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            texto.append(validaNull(res.getString("nrodoc")) + ";");
                            texto.append(validaNull(res.getString("nombre1")) + " " + validaNull(res.getString("nombre2")) + ";");
                            texto.append(validaNull(res.getString("apellido1")) + " " + validaNull(res.getString("apellido2")) + ";");
                            texto.append(res.getString("celular")+ ";");
                            texto.append(validaNull(res.getString("nombredepartamento"))+ ";");
                            texto.append(validaNull(res.getString("nombremunicipio"))+ ";");
                            texto.append((res.getInt("imgdociden") == 1 ? "SI" : "NO")+ ";");
                            texto.append(validaNull(res.getString("estado")) + ";");
                            texto.append(validaNull(res.getString("observacion")) + ";\n");

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
