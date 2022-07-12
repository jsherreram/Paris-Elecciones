/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Divitrans;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.EstadoDivitrans;
import co.com.grupoasd.nomina.modelo.wrapper.DivitransConsultaAdmin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wilfer
 */
public class DivitransDao {

    private Operaciones conex = new Operaciones();

    /**
     * Estado para aprobacion de la tabla estado_divitrans
     */
    public static final int ESTADO_DIVITRANS_EN_REVISION = 1;
    public static final int ESTADO_DIVITRANS_PARA_APROBACION = 2;
    public static final int ESTADO_DIVITRANS_APROBADO = 3;
    public static final int ESTADO_DIVITRANS_PAGADO = 4;
    public static final String SEPARATOR_COMA = ",";

    public DivitransDao() {
    }

    /**
     * Metodo encargado de listar la tabla Divitrans por id de prueba
     *
     * @param idPrueba
     * @return
     */
    public List<Divitrans> listarDivitrans(int idPrueba) {
        final List<Divitrans> lstDivitrans = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" select d.Id,d.idprueba,d.div_origen,d.div_destino,d.div_conexion1,d.div_conexion2,d.vr_municipal,d.vr_fluvial,d.vr_rural,d.vr_traccion_animal, ");
            sql.append(" d.ndias_trans_interno,d.vr_interno,d.trans_aereo,d.vr_aereo,d.pernocta,d.ndias,d.vr_dia,d.time_intermunicipal,d.time_interno,d.time_fluvial, ");
            sql.append(" d.time_aereo,d.time_rural,d.time_traccion_animal,d.frecuencia_vuelos,d.frecuencia_trans_interno,d.frecuencia_intermunicipal,d.frecuencia_rural, ");
            sql.append(" d.frecuencia_fluvial,d.frecuencia_traccion_animal,d.fecha_actualiza,d.fecha_salida,d.fecha_regreso,mdo.codigoMunicipio  as cod_municipio_origen, ");
            sql.append(" mdd.codigoMunicipio  as cod_municipio_destino,mdc1.codigoMunicipio as cod_municipio_conexion1,mdc2.codigoMunicipio as cod_municipio_conexion2, ");
            sql.append(" ddo.codigo  as cod_departamento_origen,ddd.codigo  as cod_departamento_destino,ddc1.codigo as cod_departamento_conexion1, ");
            sql.append(" ddc2.codigo as cod_departamento_conexion2, ");
            sql.append(" mdo.nombre  as municipio_origen,mdd.nombre  as municipio_destino,mdc1.nombre as municipio_conexion1,mdc2.nombre as municipio_conexion2,ddo.nombre  as departamento_origen, ");
            sql.append(" ddd.nombre  as departamento_destino,ddc1.nombre as departamento_conexion1,ddc2.nombre as departamento_conexion2 ");
            sql.append(" from divitrans d  ");
            sql.append(" LEFT JOIN municipio_dane    mdo  on d.div_origen=mdo.codigoMunicipio ");
            sql.append(" LEFT JOIN departamento_dane ddo  on mdo.codigoDepartamento=ddo.codigo ");
            sql.append(" LEFT JOIN municipio_dane    mdd  on d.div_destino=mdd.codigoMunicipio ");
            sql.append(" LEFT JOIN departamento_dane ddd  on mdd.codigoDepartamento=ddd.codigo ");
            sql.append(" LEFT JOIN municipio_dane    mdc1 on d.div_conexion1=mdc1.codigoMunicipio ");
            sql.append(" LEFT JOIN departamento_dane ddc1 on mdc1.codigoDepartamento=ddc1.codigo ");
            sql.append(" LEFT JOIN municipio_dane    mdc2 on d.div_conexion2=mdc2.codigoMunicipio ");
            sql.append(" LEFT JOIN departamento_dane ddc2 on mdc2.codigoDepartamento=ddc2.codigo ");
            sql.append(" where d.idprueba = ? ");
            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            Divitrans reg = new Divitrans();
                            reg.setId(res.getInt("id"));
                            reg.setIdprueba(res.getInt("idprueba"));
                            reg.setDiv_origen(res.getString("div_origen"));
                            reg.setDiv_destino(res.getString("div_destino"));
                            reg.setDiv_conexion1(res.getString("div_conexion1"));
                            reg.setDiv_conexion2(res.getString("div_conexion2"));
                            reg.setVr_municipal(res.getDouble("vr_municipal"));
                            reg.setVr_fluvial(res.getDouble("vr_fluvial"));
                            reg.setVr_rural(res.getDouble("vr_rural"));
                            reg.setVr_traccion_animal(res.getDouble("vr_traccion_animal"));
                            reg.setNdias_trans_interno(res.getDouble("ndias_trans_interno"));
                            reg.setVr_interno(res.getDouble("vr_interno"));
                            reg.setTrans_aereo(res.getInt("trans_aereo"));
                            reg.setVr_aereo(res.getDouble("vr_aereo"));
                            reg.setPernocta(res.getInt("pernocta"));
                            reg.setNdias(res.getDouble("ndias"));
                            reg.setVr_dia(res.getDouble("vr_dia"));
                            reg.setTime_intermunicipal(res.getTime("time_intermunicipal"));
                            reg.setTime_interno(res.getTime("time_interno"));
                            reg.setTime_fluvial(res.getTime("time_fluvial"));
                            reg.setTime_aereo(res.getTime("time_aereo"));
                            reg.setTime_rural(res.getTime("time_rural"));
                            reg.setTime_traccion_animal(res.getTime("time_traccion_animal"));
                            reg.setFrecuencia_vuelos(res.getString("frecuencia_vuelos"));
                            reg.setFrecuencia_trans_interno(res.getString("frecuencia_trans_interno"));
                            reg.setFrecuencia_intermunicipal(res.getString("frecuencia_intermunicipal"));
                            reg.setFrecuencia_rural(res.getString("frecuencia_rural"));
                            reg.setFrecuencia_fluvial(res.getString("frecuencia_fluvial"));
                            reg.setFrecuencia_traccion_animal(res.getString("frecuencia_traccion_animal"));
                            reg.setFecha_actualiza(res.getDate("fecha_actualiza"));
                            reg.setFecha_salida(res.getDate("fecha_salida"));
                            reg.setFecha_regreso(res.getDate("fecha_regreso"));
                            reg.setCod_municipio_origen(res.getString("cod_municipio_origen"));
                            reg.setCod_municipio_destino(res.getString("cod_municipio_destino"));
                            reg.setCod_municipio_conexion1(res.getString("cod_municipio_conexion1"));
                            reg.setCod_municipio_conexion2(res.getString("cod_municipio_conexion2"));
                            reg.setCod_departamento_origen(res.getString("cod_departamento_origen"));
                            reg.setCod_departamento_destino(res.getString("cod_departamento_destino"));
                            reg.setCod_departamento_conexion1(res.getString("cod_departamento_conexion1"));
                            reg.setCod_departamento_conexion2(res.getString("cod_departamento_conexion2"));
                            reg.setMunicipio_origen(res.getString("municipio_origen"));
                            reg.setMunicipio_destino(res.getString("municipio_destino"));
                            reg.setMunicipio_conexion1(res.getString("municipio_conexion1"));
                            reg.setMunicipio_conexion2(res.getString("municipio_conexion2"));
                            reg.setDepartamento_origen(res.getString("departamento_origen"));
                            reg.setDepartamento_destino(res.getString("departamento_destino"));
                            reg.setDepartamento_conexion1(res.getString("departamento_conexion1"));
                            reg.setDepartamento_conexion2(res.getString("departamento_conexion2"));
                            lstDivitrans.add(reg);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(DivitransDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString(), idPrueba);
        } catch (Exception e) {
            Logger.getLogger(DivitransDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return lstDivitrans;
    }

    public String DuplicarDivitrans(int idPruebaOrigen, int idPrueba) {
        final Object[] result = new Object[1];
        String operacion = "";
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select fun_duplicardivitrans(?,?) as resultado ");
            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            result[0] = res.getString("resultado");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(DivitransDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, sql.toString(), idPruebaOrigen, idPrueba);
            operacion = (String) result[0];
        } catch (Exception e) {
            Logger.getLogger(DivitransDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return operacion;
    }

    /**
     * Metodo encargado de buscar los registros de la tabla Divitrans segun los
     * criterios ingresados
     *
     * @param idDepartamento
     * @param idMunicipio
     * @param nroDocumento
     * @param codigoSitio
     * @param idPrueba
     * @param idEstado
     * @param codigoCargo
     * @return
     */
    public List<DivitransConsultaAdmin> consultarPorFiltros(int idDepartamento, int idMunicipio,
            double nroDocumento, long codigoSitio, int idPrueba, int idEstado, int codigoCargo) {
        final List<DivitransConsultaAdmin> listResult = new ArrayList<>();
        try {
            StringBuilder consulta = new StringBuilder();
            consulta.append("select ");
            consulta.append("dd.nombre as nomDepartamento, md.nombre as nomMunicipio, ");
            consulta.append("dp.codigoPuesto as codigoPuesto, dp.nombrePuesto as nomPuesto, ");
            consulta.append("coalesce(dt.vr_municipal,0) as valorMunicipal, coalesce(dt.vr_fluvial,0) as valorFluvial, ");
            consulta.append("coalesce(dt.vr_rural,0) as valorRural, coalesce(dt.vr_traccion_animal,0) as valorTraccionAnimal, ");
            consulta.append("coalesce(dt.vr_interno,0) as valorInterno, coalesce(dt.vr_aereo,0) as valorAereo, ");
            consulta.append("coalesce(dt.ndias,0) as numeroDias, coalesce(dt.ndias_trans_interno,0) as ndiasTransInterno, ");
            consulta.append("coalesce(dt.vr_dia,0) as valorDia, em.tipodoc as tipoDocumento ,em.nrodoc as nroDocumento, coalesce(dt.valor,0) as valorTotal, ");
            consulta.append("concat(em.nombre1,' ',em.nombre2,' ',em.apellido1,' ',em.apellido2) as nombreEmpleado, ");
            consulta.append("cg.descripcion as nombreCargo, dt.idEstadoDivitrans as idEstadoDivitrans, ");
            consulta.append("ed.descripcion as descEstado, dt.pernocta as pernocta, dt.Id as idDivitrans, ");
            consulta.append("(select coalesce(eps.idmedio_pago,5) from empleado_x_prueba_x_estado eps inner join empleado emp on eps.idempleado = emp.idEmpleado where em.idEmpleado = emp.idEmpleado limit 1) as idMedioPago ");
            consulta.append("from divitrans dt ");
            consulta.append("inner join divipol dp on dt.idDivipol = dp.idDivipol ");
            consulta.append("inner join departamento dd on dp.codigoDepartamento = dd.codigo ");
            consulta.append("inner join municipio md on dp.codigoMunicipio = md.codigoMunicipio and dd.codigo = md.codigoDepartamento ");
            consulta.append("inner join estado_divitrans ed on dt.idEstadoDivitrans = ed.idEstadoDivitrans ");
            consulta.append("inner join nombramiento nm on dt.idnombramiento = nm.id ");
            consulta.append("left join empleado em on nm.nrodoc = em.nrodoc ");
            consulta.append("left join cargos cg on cg.codigoCargo = nm.codigoCargo ");
            consulta.append("where dt.idPrueba = ").append(idPrueba);
            appendParameter(consulta, "dd.codigo", idDepartamento);
            appendParameter(consulta, "md.codigoMunicipio", idMunicipio);
            appendParameter(consulta, "em.nrodoc", nroDocumento);
            appendParameter(consulta, "dp.codigoPuesto", codigoSitio);
            appendParameter(consulta, "dt.idEstadoDivitrans", idEstado);
            appendParameter(consulta, "cg.codigoCargo", codigoCargo);

            Logger.getLogger(DivitransDao.class.getName()).log(Level.INFO, consulta.toString());
            conex.consultar(consulta.toString(), new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet resultado) {
                    try {
                        while (resultado.next()) {
                            DivitransConsultaAdmin viatico = new DivitransConsultaAdmin();
                            viatico.setDepartamento_destino(resultado.getString("nomDepartamento"));
                            viatico.setMunicipio_destino(resultado.getString("nomMunicipio"));
                            viatico.setCodigoSitio(resultado.getString("codigoPuesto"));
                            viatico.setNombreSitio(resultado.getString("nomPuesto"));
                            viatico.setVr_municipal(resultado.getDouble("valorMunicipal"));
                            viatico.setVr_fluvial(resultado.getDouble("valorFluvial"));
                            viatico.setVr_rural(resultado.getDouble("valorRural"));
                            viatico.setVr_traccion_animal(resultado.getDouble("valorTraccionAnimal"));
                            viatico.setVr_interno(resultado.getDouble("valorInterno"));
                            viatico.setVr_aereo(resultado.getDouble("valorAereo"));
                            viatico.setVr_dia(resultado.getDouble("valorDia"));
                            viatico.setNroDocEmpleadoNombramiento(resultado.getDouble("nroDocumento"));
                            viatico.setNombreEmpleadoNombramiento(resultado.getString("nombreEmpleado"));
                            viatico.setNombreCargo(resultado.getString("nombreCargo"));
                            EstadoDivitrans estado = new EstadoDivitrans();
                            estado.setIdEstadoDivitrans(resultado.getInt("idEstadoDivitrans"));
                            estado.setDescripcion(resultado.getString("descEstado"));
                            viatico.setEstado(estado);
                            viatico.setNdias(resultado.getDouble("numeroDias"));
                            viatico.setNdias_trans_interno(resultado.getDouble("ndiasTransInterno"));
                            viatico.setValorTotal(resultado.getDouble("valorTotal") <= 0
                                    ? calcularValorTotal(viatico) : resultado.getDouble("valorTotal"));
                            viatico.setPernocta(resultado.getInt("pernocta"));
                            viatico.setId(resultado.getInt("idDivitrans"));
                            Empleado empleadoNombramiento = new Empleado();
                            empleadoNombramiento.setTipodoc(resultado.getString("tipoDocumento"));
                            empleadoNombramiento.setNrodoc(resultado.getLong("nroDocumento"));
                            viatico.setEmpleadoNombramiento(empleadoNombramiento);
                            viatico.setIdMedioPago(resultado.getInt("idMedioPago"));
                            listResult.add(viatico);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(DivitransDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                /**
                 * Metodo para calcular el valor total del viatico
                 *
                 * @param viatico
                 * @return
                 */
                private Double calcularValorTotal(DivitransConsultaAdmin viatico) {
                    double total = viatico.getVr_aereo() + viatico.getVr_fluvial()
                            + viatico.getVr_municipal() + viatico.getVr_rural()
                            + viatico.getVr_traccion_animal()
                            + (viatico.getNdias_trans_interno() * viatico.getVr_interno())
                            + (viatico.getNdias() * viatico.getVr_dia());
                    return total;
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(DivitransDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listResult;
    }

    /**
     *
     * @param idDivitrans
     * @return
     */
    public DivitransConsultaAdmin getValoresViaticoById(int idDivitrans) {
        try {
            final DivitransConsultaAdmin viatico = new DivitransConsultaAdmin();
            StringBuilder consulta = new StringBuilder();
            consulta.append("select ");
            consulta.append("dd.nombre as nomDepartamento, md.nombre as nomMunicipio, ");
            consulta.append("dp.codigoPuesto as codigoPuesto, dp.nombrePuesto as nomPuesto, ");
            consulta.append("dt.vr_municipal as valorMunicipal, dt.vr_fluvial as valorFluvial, dt.valor as valorTotal, ");
            consulta.append("dt.vr_rural as valorRural, dt.vr_traccion_animal as valorTraccionAnimal, ");
            consulta.append("dt.vr_interno as valorInterno, dt.ndias_trans_interno as ndiasTransInterno, ");
            consulta.append("dt.vr_aereo as valorAereo, dt.trans_aereo as transAereo, ");
            consulta.append("dt.pernocta as pernocta, dt.ndias as ndiasPernocta, dt.vr_dia as valorDia, ");
            consulta.append("concat(em.nombre1,' ',em.nombre2,' ',em.apellido1,' ',em.apellido2) as nombreEmpleado, ");
            consulta.append("em.nrodoc as nroDocumento, cg.descripcion as nombreCargo, dt.Id as idDivitrans ");
            consulta.append("from divitrans dt ");
            consulta.append("inner join divipol dp on dt.idDivipol = dp.idDivipol ");
            consulta.append("inner join departamento dd on dp.codigoDepartamento = dd.codigo ");
            consulta.append("inner join municipio md on dp.codigoMunicipio = md.codigoMunicipio ");
            consulta.append("left join nombramiento nm on dt.idnombramiento = nm.id ");
            consulta.append("left join empleado em on nm.nrodoc = em.nrodoc ");
            consulta.append("left join cargos cg on cg.codigoCargo = nm.codigoCargo ");
            consulta.append("where dt.Id = ").append(idDivitrans);

            conex.consultar(consulta.toString(), new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet resultado) {
                    try {
                        while (resultado.next()) {
                            viatico.setDepartamento_destino(resultado.getString("nomDepartamento"));
                            viatico.setMunicipio_destino(resultado.getString("nomMunicipio"));
                            viatico.setCodigoSitio(resultado.getString("codigoPuesto"));
                            viatico.setNombreSitio(resultado.getString("nomPuesto"));
                            viatico.setVr_municipal(resultado.getDouble("valorMunicipal"));
                            viatico.setVr_fluvial(resultado.getDouble("valorFluvial"));
                            viatico.setVr_rural(resultado.getDouble("valorRural"));
                            viatico.setVr_traccion_animal(resultado.getDouble("valorTraccionAnimal"));
                            viatico.setVr_interno(resultado.getDouble("valorInterno"));
                            viatico.setNdias_trans_interno(resultado.getDouble("ndiasTransInterno"));
                            viatico.setTrans_aereo(resultado.getInt("transAereo"));
                            viatico.setVr_aereo(resultado.getDouble("valorAereo"));
                            viatico.setPernocta(resultado.getInt("pernocta"));
                            viatico.setNdias(resultado.getDouble("ndiasPernocta"));
                            viatico.setVr_dia(resultado.getDouble("valorDia"));
                            viatico.setNroDocEmpleadoNombramiento(resultado.getDouble("nroDocumento"));
                            viatico.setNombreEmpleadoNombramiento(resultado.getString("nombreEmpleado"));
                            viatico.setNombreCargo(resultado.getString("nombreCargo"));
                            viatico.setValorTotal(resultado.getDouble("valorTotal"));
                            viatico.setId(resultado.getInt("idDivitrans"));
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(DivitransDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return viatico;
        } catch (Exception ex) {
            Logger.getLogger(DivitransDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Metodo encargado de realizar la actualización en base de datos para la
     * tabla divitrans
     *
     * @param divitrans
     * @return
     */
    public boolean actualizarValoresDivitrans(Divitrans divitrans) {
        boolean resultUpdate = false;
        try {
            StringBuilder update = new StringBuilder();
            update.append("UPDATE divitrans SET ");
            update.append("vr_municipal = ?, vr_fluvial = ?, ");
            update.append("vr_rural = ?, vr_traccion_animal = ?, ");
            update.append("vr_interno = ?, ndias_trans_interno = ?, ");
            update.append("vr_aereo = ?, trans_aereo = ?, ");
            update.append("pernocta = ?, ndias = ?, vr_dia = ? , valor = ? ");
            update.append("where Id = ? ");

            //Se calcular valor total de aceurdo a los valores restantes ingresados
            Double valorTotal = divitrans.getVr_municipal()
                    + divitrans.getVr_fluvial()
                    + divitrans.getVr_rural()
                    + divitrans.getVr_traccion_animal()
                    + (divitrans.getVr_interno() * divitrans.getNdias_trans_interno())
                    + divitrans.getVr_aereo()
                    + (divitrans.getNdias() * divitrans.getVr_dia());

            resultUpdate = conex.ejecutar(update.toString(),
                    divitrans.getVr_municipal(), divitrans.getVr_fluvial(),
                    divitrans.getVr_rural(), divitrans.getVr_traccion_animal(),
                    divitrans.getVr_interno(), divitrans.getNdias_trans_interno(),
                    divitrans.getVr_aereo(), (divitrans.getVr_aereo() != null && divitrans.getVr_aereo() > 0 ? 1 : 0),
                    divitrans.getPernocta(), divitrans.getNdias(),
                    divitrans.getVr_dia(), valorTotal, divitrans.getId());

        } catch (Exception ex) {
            Logger.getLogger(DivitransDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultUpdate;
    }

    /**
     * Metodo que consulta las frecuencias y tiempos del viaticos especifico
     *
     * @param idDivitrans
     * @return
     */
    public DivitransConsultaAdmin getFrecuenciaTiempoViaticoById(int idDivitrans) {
        try {
            final DivitransConsultaAdmin viatico = new DivitransConsultaAdmin();
            StringBuilder consulta = new StringBuilder();
            consulta.append("select ");
            consulta.append("dd.nombre as nomDepartamento, md.nombre as nomMunicipio, ");
            consulta.append("dp.codigoPuesto as codigoPuesto, dp.nombrePuesto as nomPuesto, ");
            consulta.append("concat(em.nombre1,' ',em.nombre2,' ',em.apellido1,' ',em.apellido2) as nombreEmpleado, ");
            consulta.append("em.nrodoc as nroDocumento, cg.descripcion as nombreCargo, dt.Id as idDivitrans, ");
            //Tiempos
            consulta.append("dt.time_intermunicipal as tiempoIntermunicipal, dt.time_interno as tiempoInterno, ");
            consulta.append("dt.time_fluvial as tiempoFluvial, dt.time_aereo as tiempoAereo, ");
            consulta.append("dt.time_rural as tiempoRural, dt.time_traccion_animal as tiempoTraccionAnimal, ");
            //Frecuencias
            consulta.append("dt.frecuencia_vuelos as frecuenciaVuelos, dt.frecuencia_trans_interno as frecuenciaTransInterno, ");
            consulta.append("dt.frecuencia_intermunicipal as frecuenciaIntermunicipal, dt.frecuencia_rural as frecuenciaRural, ");
            consulta.append("dt.frecuencia_fluvial as frecuenciaFluvial, dt.frecuencia_traccion_animal as frecuenciaTraccionAnimal ");
            //Tablas
            consulta.append("from divitrans dt ");
            consulta.append("inner join divipol dp on dt.idDivipol = dp.idDivipol ");
            consulta.append("inner join departamento dd on dp.codigoDepartamento = dd.codigo ");
            consulta.append("inner join municipio md on dp.codigoMunicipio = md.codigoMunicipio ");
            consulta.append("left join nombramiento nm on dt.idnombramiento = nm.id ");
            consulta.append("left join empleado em on nm.nrodoc = em.nrodoc ");
            consulta.append("left join cargos cg on cg.codigoCargo = nm.codigoCargo ");
            consulta.append("where dt.Id = ").append(idDivitrans);

            conex.consultar(consulta.toString(), new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet resultado) {
                    try {
                        while (resultado.next()) {
                            viatico.setId(resultado.getInt("idDivitrans"));
                            viatico.setDepartamento_destino(resultado.getString("nomDepartamento"));
                            viatico.setMunicipio_destino(resultado.getString("nomMunicipio"));
                            viatico.setCodigoSitio(resultado.getString("codigoPuesto"));
                            viatico.setNombreSitio(resultado.getString("nomPuesto"));
                            viatico.setNombreCargo(resultado.getString("nombreCargo"));
                            //Tiempos
                            viatico.setTimeIntermunicipal(resultado.getString("tiempoIntermunicipal"));
                            viatico.setTimeInterno(resultado.getString("tiempoInterno"));
                            viatico.setTimeFluvial(resultado.getString("tiempoFluvial"));
                            viatico.setTimeAereo(resultado.getString("tiempoAereo"));
                            viatico.setTimeRural(resultado.getString("tiempoRural"));
                            viatico.setTimeTraccionAnimal(resultado.getString("tiempoTraccionAnimal"));
                            //Frecuencias
                            viatico.setFrecuencia_intermunicipal(resultado.getString("frecuenciaIntermunicipal"));
                            viatico.setFrecuencia_trans_interno(resultado.getString("frecuenciaTransInterno"));
                            viatico.setFrecuencia_fluvial(resultado.getString("frecuenciaFluvial"));
                            viatico.setFrecuencia_vuelos(resultado.getString("frecuenciaVuelos"));
                            viatico.setFrecuencia_rural(resultado.getString("frecuenciaRural"));
                            viatico.setFrecuencia_traccion_animal(resultado.getString("frecuenciaTraccionAnimal"));
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(DivitransDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return viatico;
        } catch (Exception ex) {
            Logger.getLogger(DivitransDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Metodo encargao de actualizar los tiempos y frecuencias de la tabla
     * divitrans
     *
     * @param divitrans
     * @return
     */
    public boolean actualizarFrecuenciaTiempoDivitrans(DivitransConsultaAdmin divitrans) {
        boolean resultUpdate = false;
        try {
            StringBuilder update = new StringBuilder();
            update.append("UPDATE divitrans SET ");
            update.append("time_intermunicipal = ?, time_interno = ?, ");
            update.append("time_fluvial = ?, time_aereo = ?, ");
            update.append("time_rural = ?, time_traccion_animal = ?, ");
            update.append("frecuencia_vuelos = ?, frecuencia_trans_interno = ?, ");
            update.append("frecuencia_intermunicipal = ?, frecuencia_rural = ?, ");
            update.append("frecuencia_fluvial = ?, frecuencia_traccion_animal = ? ");
            update.append("where Id = ? ");

            resultUpdate = conex.ejecutar(update.toString(),
                    divitrans.getTimeIntermunicipal(), divitrans.getTimeInterno(),
                    divitrans.getTimeFluvial(), divitrans.getTimeAereo(),
                    divitrans.getTimeRural(), divitrans.getTimeTraccionAnimal(),
                    divitrans.getFrecuencia_vuelos(), divitrans.getFrecuencia_trans_interno(),
                    divitrans.getFrecuencia_intermunicipal(), divitrans.getFrecuencia_rural(),
                    divitrans.getFrecuencia_fluvial(), divitrans.getFrecuencia_traccion_animal(),
                    divitrans.getId());

        } catch (Exception ex) {
            Logger.getLogger(DivitransDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultUpdate;
    }

    /**
     * Metodo encargao de actualizar los tiempos y frecuencias de la tabla
     * divitrans
     *
     * @param divitrans
     * @return
     */
    public boolean actualizarFrecuenciaTiempoDivitrans(Divitrans divitrans) {
        boolean resultUpdate = false;
        try {
            StringBuilder update = new StringBuilder();
            update.append("UPDATE divitrans SET ");
            update.append("time_intermunicipal = ?, time_interno = ?, ");
            update.append("time_fluvial = ?, time_aereo = ?, ");
            update.append("time_rural = ?, time_traccion_animal = ?, ");
            update.append("frecuencia_vuelos = ?, frecuencia_trans_interno = ?, ");
            update.append("frecuencia_intermunicipal = ?, frecuencia_rural = ?, ");
            update.append("frecuencia_fluvial = ?, frecuencia_traccion_animal = ? ");
            update.append("where Id = ? ");

            resultUpdate = conex.ejecutar(update.toString(),
                    divitrans.getTime_intermunicipal(), divitrans.getTime_interno(),
                    divitrans.getTime_fluvial(), divitrans.getTime_aereo(),
                    divitrans.getTime_rural(), divitrans.getTime_traccion_animal(),
                    divitrans.getFrecuencia_vuelos(), divitrans.getFrecuencia_trans_interno(),
                    divitrans.getFrecuencia_intermunicipal(), divitrans.getFrecuencia_rural(),
                    divitrans.getFrecuencia_fluvial(), divitrans.getFrecuencia_traccion_animal(),
                    divitrans.getId());

        } catch (Exception ex) {
            Logger.getLogger(DivitransDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultUpdate;
    }

    /**
     * Metodo que realiza la consulta dentro de la tabla de estados de divitrans
     *
     * @return
     */
    public List<EstadoDivitrans> listarEstadosDivitrans() {
        final List<EstadoDivitrans> listaEstadosDivitrans = new ArrayList<>();
        try {
            StringBuilder consulta = new StringBuilder();
            consulta.append("select ");
            consulta.append(" idEstadoDivitrans, descripcion ");
            consulta.append("from estado_divitrans");

            conex.consultar(consulta.toString(), new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet resultado) {
                    try {
                        while (resultado.next()) {
                            EstadoDivitrans estado = new EstadoDivitrans();
                            estado.setIdEstadoDivitrans(resultado.getInt("idEstadoDivitrans"));
                            estado.setDescripcion(resultado.getString("descripcion"));
                            listaEstadosDivitrans.add(estado);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(DivitransDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return listaEstadosDivitrans;
        } catch (Exception ex) {
            Logger.getLogger(DivitransDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Metodo encargado de realizar el update del estado a 2 = PARA APROBACION a
     * los divitrans recibidos
     *
     * @param listaParaAprobar
     * @param estado
     * @return
     */
    public String cambiarEstadoViaticos(List<Integer> listaParaAprobar, int estado) {
        StringBuilder idsError = new StringBuilder("");
        try {
            StringBuilder update = new StringBuilder();
            update.append("update ");
            update.append("divitrans SET ");
            update.append("idEstadoDivitrans = ").append(estado);
            update.append(" where Id = ? ");

            for (Integer idDivitrans : listaParaAprobar) {
                boolean success = conex.ejecutar(update.toString(), idDivitrans);
                if (!success) {
                    idsError.append(idDivitrans).append(",");
                }
            }
            return idsError.toString();
        } catch (Exception ex) {
            Logger.getLogger(DivitransDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idsError.toString();
    }

    /**
     * Metodo encargado de realizar la aprobación del viatico cambiando su
     * estado a APROBADO
     *
     * @param idDivitrans
     * @return
     */
    public boolean aprobar(int idDivitrans) {
        boolean success = false;
        try {
            StringBuilder updateAprobar = new StringBuilder();
            updateAprobar.append("update ");
            updateAprobar.append("divitrans set ");
            updateAprobar.append("idEstadoDivitrans = ").append(ESTADO_DIVITRANS_APROBADO);
            updateAprobar.append(" where Id = ? ");
            //Se ejecuta la sentencia
            success = conex.ejecutar(updateAprobar.toString(), idDivitrans);
            return success;
        } catch (Exception ex) {
            Logger.getLogger(DivitransDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return success;
    }

    /**
     * Metodo que realiza la consulta de los registros divitrans para realizar
     * la aprobacion de los mismos segun los filtros ingresados
     *
     * @param idDepartamento
     * @param idMunicipio
     * @param nroDocumentoEmpleadoNombra
     * @param codigoSitio
     * @param idPrueba
     * @param idEstadoDivitrans
     * @param idCargo
     * @param nroDocumentoSession
     * @return
     */
    public List<DivitransConsultaAdmin> consultarAprobarPorFiltros(int idDepartamento,
            int idMunicipio, double nroDocumentoEmpleadoNombra, long codigoSitio, int idPrueba,
            int idEstadoDivitrans, int idCargo, double nroDocumentoSession) {
        final List<DivitransConsultaAdmin> listResult = new ArrayList<>();
        try {
            StringBuilder consulta = new StringBuilder();
            consulta.append("select ");
            consulta.append("dd.nombre as nomDepartamento, md.nombre as nomMunicipio, ");
            consulta.append("dp.codigoPuesto as codigoPuesto, dp.nombrePuesto as nomPuesto, ");
            consulta.append("coalesce(dt.vr_municipal,0) as valorMunicipal, coalesce(dt.vr_fluvial,0) as valorFluvial, ");
            consulta.append("coalesce(dt.vr_rural,0) as valorRural, coalesce(dt.vr_traccion_animal,0) as valorTraccionAnimal, ");
            consulta.append("coalesce(dt.vr_interno,0) as valorInterno, coalesce(dt.vr_aereo,0) as valorAereo, ");
            consulta.append("coalesce(dt.ndias,0) as numeroDias, coalesce(dt.ndias_trans_interno,0) as ndiasTransInterno, ");
            consulta.append("coalesce(dt.vr_dia,0) as valorDia, em.nrodoc as nroDocumento, coalesce(dt.valor,0) as valorTotal, ");
            consulta.append("concat(em.nombre1,' ',em.nombre2,' ',em.apellido1,' ',em.apellido2) as nombreEmpleado, ");
            consulta.append("cg.descripcion as nombreCargo, dt.idEstadoDivitrans as idEstadoDivitrans, ");
            consulta.append("ed.descripcion as descEstado, dt.pernocta as pernocta, dt.Id as idDivitrans ");
            consulta.append("from divitrans dt ");
            consulta.append("inner join divipol dp on dt.idDivipol = dp.idDivipol ");
            consulta.append("inner join departamento dd on dp.codigoDepartamento = dd.codigo ");
            consulta.append("inner join municipio md on dp.codigoMunicipio = md.codigoMunicipio ");
            consulta.append("inner join usuario_sitio us on us.iddivipol = dt.idDivipol ");
            //Nuevo inner (unificar)
            consulta.append("inner join estado_divitrans ed on dt.idEstadoDivitrans = ed.idEstadoDivitrans ");
            consulta.append("left join nombramiento nm on dt.idnombramiento = nm.id ");
            consulta.append("left join empleado em on nm.nrodoc = em.nrodoc ");
            consulta.append("left join cargos cg on cg.codigoCargo = nm.codigoCargo ");
            consulta.append("where dt.idPrueba = ").append(idPrueba);
            appendParameter(consulta, "us.usuario", nroDocumentoSession);//Nuevo AND
            appendParameter(consulta, "dd.codigo", idDepartamento);
            appendParameter(consulta, "md.codigoMunicipio", idMunicipio);
            appendParameter(consulta, "em.nrodoc", nroDocumentoEmpleadoNombra);
            appendParameter(consulta, "dp.codigoPuesto", codigoSitio);
            appendParameter(consulta, "dt.idEstadoDivitrans", idEstadoDivitrans);
            appendParameter(consulta, "cg.codigoCargo", idCargo);

            conex.consultar(consulta.toString(), new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet resultado) {
                    try {
                        while (resultado.next()) {
                            DivitransConsultaAdmin viatico = new DivitransConsultaAdmin();
                            viatico.setDepartamento_destino(resultado.getString("nomDepartamento"));
                            viatico.setMunicipio_destino(resultado.getString("nomMunicipio"));
                            viatico.setCodigoSitio(resultado.getString("codigoPuesto"));
                            viatico.setNombreSitio(resultado.getString("nomPuesto"));
                            viatico.setVr_municipal(resultado.getDouble("valorMunicipal"));
                            viatico.setVr_fluvial(resultado.getDouble("valorFluvial"));
                            viatico.setVr_rural(resultado.getDouble("valorRural"));
                            viatico.setVr_traccion_animal(resultado.getDouble("valorTraccionAnimal"));
                            viatico.setVr_interno(resultado.getDouble("valorInterno"));
                            viatico.setVr_aereo(resultado.getDouble("valorAereo"));
                            viatico.setVr_dia(resultado.getDouble("valorDia"));
                            viatico.setNroDocEmpleadoNombramiento(resultado.getDouble("nroDocumento"));
                            viatico.setNombreEmpleadoNombramiento(resultado.getString("nombreEmpleado"));
                            viatico.setNombreCargo(resultado.getString("nombreCargo"));
                            EstadoDivitrans estado = new EstadoDivitrans();
                            estado.setIdEstadoDivitrans(resultado.getInt("idEstadoDivitrans"));
                            estado.setDescripcion(resultado.getString("descEstado"));
                            viatico.setEstado(estado);
                            viatico.setNdias(resultado.getDouble("numeroDias"));
                            viatico.setNdias_trans_interno(resultado.getDouble("ndiasTransInterno"));
                            viatico.setValorTotal(resultado.getDouble("valorTotal") <= 0
                                    ? calcularValorTotal(viatico) : resultado.getDouble("valorTotal"));
                            viatico.setPernocta(resultado.getInt("pernocta"));
                            viatico.setId(resultado.getInt("idDivitrans"));
                            listResult.add(viatico);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(DivitransDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                /**
                 * Metodo para calcular el valor total del viatico
                 *
                 * @param viatico
                 * @return
                 */
                private Double calcularValorTotal(DivitransConsultaAdmin viatico) {
                    double total = viatico.getVr_aereo() + viatico.getVr_fluvial()
                            + viatico.getVr_municipal() + viatico.getVr_rural()
                            + viatico.getVr_traccion_animal()
                            + (viatico.getNdias_trans_interno() * viatico.getVr_interno())
                            + (viatico.getNdias() * viatico.getVr_dia());
                    return total;
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(DivitransDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listResult;

    }

    /**
     * Metodo encargado de generar un insert en la tabla de reversos de
     * divitrans
     *
     * @param divitrans
     */
    public void generarLogReverso(DivitransConsultaAdmin divitrans) {
        try {
            StringBuilder insertAprobar = new StringBuilder();
            insertAprobar.append("insert into log_reverso_divitrans ");
            insertAprobar.append("(idDivitrans,vr_municipal,vr_fluvial,vr_rural,vr_traccion_animal,");
            insertAprobar.append("ndias_trans_interno,vr_interno,trans_aereo,vr_aereo,pernocta,ndias,vr_dia,");
            insertAprobar.append("idEstadoDivitrans,fecha_pago,referencia_pago,valor,idBanco,idmedio_pago,numeroCuenta,");
            insertAprobar.append("idTipoCuenta,observaciones)");
            insertAprobar.append("values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            //Se asignan los valores y se ejecuta el insert
            conex.ejecutar(insertAprobar.toString(), divitrans.getId(), divitrans.getVr_municipal(),
                    divitrans.getVr_fluvial(), divitrans.getVr_rural(),
                    divitrans.getVr_traccion_animal(), divitrans.getNdias_trans_interno(),
                    divitrans.getVr_interno(), divitrans.getTrans_aereo(),
                    divitrans.getVr_aereo(), divitrans.getPernocta(),
                    divitrans.getNdias(), divitrans.getVr_dia(), divitrans.getEstado().getIdEstadoDivitrans(),
                    divitrans.getFechaPago(), divitrans.getReferenciaPago(), divitrans.getValorTotal(),
                    divitrans.getIdBanco(), divitrans.getIdMedioPago(), divitrans.getNumeroCuenta(),
                    divitrans.getIdTipoCuenta(), divitrans.getObservaciones());

            Logger.getLogger(DivitransDao.class.getName()).log(Level.INFO, "[Log Reverso Divitrans generado]");
        } catch (Exception ex) {
            Logger.getLogger(DivitransDao.class.getName()).log(Level.SEVERE, "[Log reverso Divitrans no generado]", ex);
        }
    }

    /**
     * Metodo para adicionar parametro a la consulta
     *
     * @param consulta
     * @param ddcodigo
     * @param idDepartamento
     */
    private void appendParameter(StringBuilder consulta, String column, Object dato) {
        if (dato != null) {
            if (dato instanceof String && !String.valueOf(dato).isEmpty()) {
                consulta.append(" AND ").append(column).append("=").append(dato);
            } else if (dato instanceof Integer && ((Integer) dato) > 0) {
                consulta.append(" AND ").append(column).append("=").append(dato);
            } else if (dato instanceof Double && ((Double) dato > 0)) {
                consulta.append(" AND ").append(column).append("=").append(dato);
            } else if (dato instanceof Long && ((Long) dato > 0)) {
                consulta.append(" AND ").append(column).append("=").append(dato);
            }
        }
    }

    /**
     * Metodo que actualiza el viatico a estado
     *
     * @param mapaPago
     * @param idDivitrans
     * @return
     */
    public boolean actualizarPagoViatico(Map<String, String> mapaPago, long idDivitrans) {
        StringBuilder update = new StringBuilder("update divitrans ");
        update.append("set fecha_pago = '").append(mapaPago.get("Fecha Pago")).append("'");
        update.append(" ,referencia_pago = '").append(mapaPago.get("Referencia Pago")).append("'");
        update.append(" ,valor_pago = ").append(mapaPago.get("Valor Pago"));
        update.append(" ,idBanco = ").append(mapaPago.get("Banco Pago"));
        update.append(" ,idmedio_pago = ").append(mapaPago.get("Medio de Pago"));
        update.append(" ,numeroCuenta = '").append(mapaPago.get("No Cuenta")).append("'");
        update.append(" ,idTipoCuenta = ").append(mapaPago.get("Tipo Cuenta"));
        update.append(" ,idEstadoDivitrans = ").append(ESTADO_DIVITRANS_PAGADO);
        update.append(" where Id = ").append(idDivitrans);
        Logger.getLogger(DivitransDao.class.getName()).log(Level.INFO, update.toString());
        boolean success = conex.ejecutar(update.toString());
        return success;
    }

    /**
     * Metodo que realiza la consulta del viatico segun el estado,prueba y
     * nroDocumento del empleado
     *
     * @param idEmpleado
     * @param idPrueba
     * @param estados
     * @return
     */
    public Divitrans consultarViaticoEmpleadoPorEstado(int idEmpleado, int idPrueba, int[] estados) {
        final Divitrans viatico = new Divitrans();
        StringBuilder consulta = new StringBuilder("select ");
        consulta.append("d.Id as id, d.idEstadoDivitrans as idEstadoViatico ");
        consulta.append("from divitrans d ");
        consulta.append("inner join nombramiento n on d.idnombramiento = n.id ");
        consulta.append("inner join empleado e on n.nrodoc = e.nrodoc ");
        consulta.append("where d.idPrueba = ").append(idPrueba);
        consulta.append(" and e.idEmpleado = ").append(idEmpleado);
        consulta.append(" and d.idEstadoDivitrans IN (");
        String separator = "";
        for (int estado : estados) {
            consulta.append(separator).append(estado);
            separator = ",";
        }
        consulta.append(")");
        Logger.getLogger(DivitransDao.class.getName()).log(Level.INFO, consulta.toString());
        conex.consultar(consulta.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    while (resultado.next()) {
                        viatico.setId(resultado.getInt("id"));
                        EstadoDivitrans estado = new EstadoDivitrans();
                        estado.setIdEstadoDivitrans(resultado.getInt("idEstadoViatico"));
                        viatico.setEstado(estado);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DivitransDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return viatico;
    }

    /**
     * Metodo para consultar un viatico segun el idNombramiento
     *
     * @param idNombramiento
     * @return
     */
    public Divitrans consultarViaticoNombramiento(int idNombramiento) {
        final Divitrans viatico = new Divitrans();
        StringBuilder consulta = new StringBuilder("SELECT ");
        consulta.append("d.Id as id, d.idEstadoDivitrans as idEstadoViatico, d.div_origen as munOrigen, dp.codigoMunicipio AS munDestino ");
        consulta.append("FROM divitrans d ");
        consulta.append("INNER JOIN divipol dp ON d.idDivipol = dp.idDivipol ");
        consulta.append("INNER JOIN nombramiento n ON d.idnombramiento = n.id ");
        consulta.append("INNER JOIN empleado e ON n.nrodoc = e.nrodoc ");
        consulta.append("WHERE d.idnombramiento = ").append(idNombramiento);
        Logger.getLogger(DivitransDao.class.getName()).log(Level.INFO, consulta.toString());
        conex.consultar(consulta.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    while (resultado.next()) {
                        viatico.setId(resultado.getInt("id"));
                        EstadoDivitrans estado = new EstadoDivitrans();
                        estado.setIdEstadoDivitrans(resultado.getInt("idEstadoViatico"));
                        viatico.setEstado(estado);
                        viatico.setDiv_origen(resultado.getString("munOrigen"));
                        viatico.setDiv_destino(resultado.getString("munDestino"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DivitransDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return viatico;
    }

    /**
     * Metodo para consultar las tarifas parametrizadas para el viatico
     * dependiendo del origen y el destino
     *
     * @param codigoMunicipioOrigen
     * @param codigoMunicipioDestino
     * @return
     */
    public Divitrans buscarViaticoMaestro(String codigoMunicipioOrigen, String codigoMunicipioDestino) {
        final Divitrans viaticoMaestro = new Divitrans();
        StringBuilder consulta = new StringBuilder("SELECT ");
        consulta.append("Id,div_origen, div_destino, COALESCE(vr_municipal,0) AS valorMunicipal, ");
        consulta.append("COALESCE(vr_fluvial,0) AS valorFluvial, COALESCE(vr_rural,0) AS valorRural, ");
        consulta.append("COALESCE(vr_traccion_animal,0) AS valorTraccionAnimal, COALESCE(vr_interno,0) AS valorInterno, ");
        consulta.append("COALESCE(trans_aereo,0) AS transporteAereo, COALESCE(vr_aereo,0) AS valorAeropuerto, ");
        consulta.append("(SELECT valor FROM parametro WHERE concepto = 'valorViaticoDiario') AS valorDia, ");
        consulta.append("COALESCE(pernocta,0) AS pernocta, COALESCE(ndias,0) AS numeroDias, ");
        consulta.append("time_intermunicipal, time_interno, time_fluvial, time_aereo, time_rural, ");
        consulta.append("time_traccion_animal, frecuencia_vuelos, frecuencia_trans_interno, frecuencia_intermunicipal, ");
        consulta.append("frecuencia_rural, frecuencia_fluvial, frecuencia_traccion_animal ");
        consulta.append("FROM maestra_divitrans ");
        consulta.append("WHERE div_origen = ").append(codigoMunicipioOrigen).append(" AND div_destino = ").append(codigoMunicipioDestino);
        Logger.getLogger(DivitransDao.class.getName()).log(Level.INFO, consulta.toString());
        conex.consultar(consulta.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    while (resultado.next()) {
                        viaticoMaestro.setId(resultado.getInt("Id"));
                        viaticoMaestro.setDiv_origen(resultado.getString("div_origen"));
                        viaticoMaestro.setDiv_destino(resultado.getString("div_destino"));
                        viaticoMaestro.setVr_municipal(resultado.getDouble("valorMunicipal"));
                        viaticoMaestro.setVr_fluvial(resultado.getDouble("valorFluvial"));
                        viaticoMaestro.setVr_rural(resultado.getDouble("valorRural"));
                        viaticoMaestro.setVr_traccion_animal(resultado.getDouble("valorTraccionAnimal"));
                        viaticoMaestro.setNdias_trans_interno(0D);
                        viaticoMaestro.setVr_interno(resultado.getDouble("valorInterno"));
                        viaticoMaestro.setTrans_aereo(resultado.getInt("transporteAereo"));
                        viaticoMaestro.setVr_aereo(resultado.getDouble("valorAeropuerto"));
                        viaticoMaestro.setPernocta(resultado.getInt("pernocta"));
                        viaticoMaestro.setNdias(resultado.getDouble("numeroDias"));
                        viaticoMaestro.setVr_dia(resultado.getDouble("valorDia"));
                        viaticoMaestro.setFrecuencia_fluvial(resultado.getString("frecuencia_fluvial"));
                        viaticoMaestro.setFrecuencia_intermunicipal(resultado.getString("frecuencia_intermunicipal"));
                        viaticoMaestro.setFrecuencia_rural(resultado.getString("frecuencia_rural"));
                        viaticoMaestro.setFrecuencia_traccion_animal(resultado.getString("frecuencia_traccion_animal"));
                        viaticoMaestro.setFrecuencia_trans_interno(resultado.getString("frecuencia_trans_interno"));
                        viaticoMaestro.setFrecuencia_vuelos(resultado.getString("frecuencia_vuelos"));
                        viaticoMaestro.setTime_aereo(resultado.getTime("time_aereo"));
                        viaticoMaestro.setTime_fluvial(resultado.getTime("time_fluvial"));
                        viaticoMaestro.setTime_intermunicipal(resultado.getTime("time_intermunicipal"));
                        viaticoMaestro.setTime_interno(resultado.getTime("time_interno"));
                        viaticoMaestro.setTime_rural(resultado.getTime("time_rural"));
                        viaticoMaestro.setTime_traccion_animal(resultado.getTime("time_traccion_animal"));
                        viaticoMaestro.setEstado(new EstadoDivitrans(ESTADO_DIVITRANS_EN_REVISION, ""));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DivitransDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return viaticoMaestro;
    }
}
