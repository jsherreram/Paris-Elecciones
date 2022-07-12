/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;


import co.com.grupoasd.nomina.conexion.Operaciones;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import co.com.grupoasd.nomina.modelo.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Rodríguez
 */
public class PagoDao {
    
    private Operaciones conex = new Operaciones();
 
    public PagoDao(){}
    
    public List<Pago> generarArchivo(String codigoDepartamento, int idPrueba) {
        
//       if (idPrueba == 43){
//           return generarArchivoPresidente(codigoDepartamento, idPrueba);
//       }else {
//           return generarArchivoNomina(codigoDepartamento, idPrueba);
//       }
        
       return generarArchivoNomina(codigoDepartamento, idPrueba);
       
    }
    

    
    public List<Pago> generarArchivoNomina(String codigoDepartamento, int idPrueba) {
        
       final List<Pago> pagos = new ArrayList<>();
       String sql = "";
       
       try{
            /*Consultar el idEmpleado*/
            sql =   "select distinct total.codigoEvento, total.tipodoc, total.codigodepartamento, total.nombredepartamento,\n" +
                    "ultimomunicipio_codigo AS codigomunicipio, ultimomunicipio_nombre as nombremunicipio, total.nrodoc,\n" +
                    "total.apellido1, total.apellido2, total.nombre1, total.nombre2,\n" +
                    "total.celular, total.email, total.valor, total.cargo, total.nombre as nombreevento, total.fecha, total.idEmpleado,\n" +
                    "eleccion.codigoZona, eleccion.codigoPuesto,\n" +
                    "case when eleccion.nombrePuesto is null then '' else eleccion.nombrePuesto end as nombrePuesto,\n" +
                    "eleccion.ultimocargo, eleccion.ubicacion, eleccion.codigoCargo, cc.id as idcuenta, \n" +
                    "case \n" +
                    "when total.estado IS NULL then 'SIN ASISTENCIA'\n" +
                    "when total.estado = 0 then 'SIN VALIDAR'\n" +
                    "when total.estado = 1 then 'VALIDADO MANUAL'\n" +
                    "when total.estado = 3 then 'VALIDADO AUTOMATICO'\n" +
                    "end as formaAsistencia\n" +
                    "from (\n" +
                    "select ev.codigoEvento, b.tipodoc, a.codigodepartamento, dep.nombre as nombredepartamento,\n" +
                    "a.codigomunicipio, mun.nombre as nombremunicipio, a.nrodoc, apellido1, apellido2, nombre1, nombre2,\n" +
                    "celular, email, t.valor, ca.descripcion  as cargo, ev.nombre, ev.fecha, b.idEmpleado, asis.estado\n" +
                    "from detalle_cargo_x_puesto_x_evento a left join empleado b\n" +
                    "on a.nrodoc = b.nrodoc\n" +
                    "left join departamento dep\n" +
                    "on a.codigoDepartamento = dep.codigo\n" +
                    "left join municipio mun\n" +
                    "on a.codigoDepartamento = mun.codigoDepartamento\n" +
                    "and a.codigoMunicipio = mun.codigoMunicipio\n" +
                    "left join pagoxsesion t\n" +
                    "on t.codigoevento = a.codigoEvento\n" +
                    "and a.codigoCargo = t.codigocargo\n" +
                    "left join cargos ca\n" +
                    "on a.codigoCargo = ca.codigoCargo\n" +
                    "left join evento ev\n" +
                    "on a.codigoEvento = ev.codigoEvento\n" +
                    "left join asistencia asis\n" +
                    "on asis.iddivipol = a.idDivipol and asis.codigoevento = a.codigoEvento and asis.idempleado = b.idEmpleado\n" +
                    "where a.nrodoc is not NULL \n" +
                    "and a.codigoEvento in(select codigoevento from evento where idprueba = 47 and eseleccion = 1)\n" +
                    "union\n" +
                    "SELECT 910 as codigoEvento, b.tipodoc, a.codigodepartamento, dep.nombre as nombredepartamento,\n" +
                    "a.codigomunicipio, mun.nombre as nombremunicipio, a.nrodoc, apellido1, apellido2, nombre1, nombre2,\n" +
                    "celular, email, t.valor, ca.descripcion  as cargo, 'CAPACITACION' as nombre, '2022-02-01' as fecha, b.idEmpleado, 3 AS estado\n" +
                    "from detalle_cargo_x_puesto_x_evento a join empleado b\n" +
                    "on a.nrodoc = b.nrodoc\n" +
                    "join departamento dep\n" +
                    "on a.codigoDepartamento = dep.codigo\n" +
                    "join municipio mun\n" +
                    "on a.codigoDepartamento = mun.codigoDepartamento\n" +
                    "and a.codigoMunicipio = mun.codigoMunicipio\n" +
                    "left join pagoxsesion t\n" +
                    "on t.codigoevento = 910\n" +
                    "and a.codigoCargo = t.codigocargo\n" +
                    "join cargos ca\n" +
                    "on a.codigoCargo = ca.codigoCargo\n" +
                    "join evento ev\n" +
                    "on a.codigoEvento = ev.codigoEvento\n" +
                    "join (\n" +
                    "	select nrodoc, max(codigoevento) as codigoevento\n" +
                    "	from (\n" +
                    "		select e.nrodoc, dcpe.codigoEvento\n" +
                    "		from detalle_cargo_x_puesto_x_evento dcpe join empleado e\n" +
                    "		on dcpe.nrodoc = e.nrodoc\n" +
                    "		where dcpe.codigoEvento in(select codigoevento from evento where idprueba = 47 and eseleccion = 1 and requiereAsistenciaCtaCobro = 0)\n" +
                    "	) as cedula_eventos\n" +
                    "	group by nrodoc\n" +
                    ") as ultimo_evento\n" +
                    "on a.nrodoc = ultimo_evento.nrodoc and a.codigoEvento = ultimo_evento.codigoevento\n" +
                    "union\n" +
                    "SELECT a.codigoevento + 10000 as codigoEvento, b.tipodoc, a.codigodepartamento, dep.nombre as nombredepartamento,\n" +
                    "a.codigomunicipio, mun.nombre as nombremunicipio, a.nrodoc, apellido1, apellido2, nombre1, nombre2,\n" +
                    "celular, email, 12000 as valor, ca.descripcion  as cargo, 'REFRIG' AS  nombre, ev.fecha, b.idEmpleado, 3 AS estado\n" +
                    "from detalle_cargo_x_puesto_x_evento a left join empleado b\n" +
                    "on a.nrodoc = b.nrodoc\n" +
                    "left join departamento dep\n" +
                    "on a.codigoDepartamento = dep.codigo\n" +
                    "left join municipio mun\n" +
                    "on a.codigoDepartamento = mun.codigoDepartamento\n" +
                    "and a.codigoMunicipio = mun.codigoMunicipio\n" +
                    "left join pagoxsesion t\n" +
                    "on t.codigoevento = a.codigoEvento\n" +
                    "and a.codigoCargo = t.codigocargo\n" +
                    "left join cargos ca\n" +
                    "on a.codigoCargo = ca.codigoCargo\n" +
                    "left join evento ev\n" +
                    "on a.codigoEvento = ev.codigoEvento\n" +
                    "left join asistencia asis\n" +
                    "on asis.iddivipol = a.idDivipol and asis.codigoevento = a.codigoEvento and asis.idempleado = b.idEmpleado\n" +
                    "where a.nrodoc is not null and a.nrodoc > 0\n" +
                    "and asis.estado is not null and a.codigoEvento IN(6000,6001)\n" +
                    "AND a.codigoDepartamento IN(SELECT codigo FROM depto_refrigerio)\n" +
                    "AND a.codigoCargo IN(11,12,31,32,33,9,10)\n" +
                    "UNION\n" +
                    "SELECT a.codigoevento + 10000 as codigoEvento, b.tipodoc, a.codigodepartamento, dep.nombre as nombredepartamento,\n" +
                    "a.codigomunicipio, mun.nombre as nombremunicipio, a.nrodoc, apellido1, apellido2, nombre1, nombre2,\n" +
                    "celular, email, 17000 as valor, ca.descripcion  as cargo, 'REFRIG' AS  nombre, ev.fecha, b.idEmpleado, 3 AS estado\n" +
                    "from detalle_cargo_x_puesto_x_evento a left join empleado b\n" +
                    "on a.nrodoc = b.nrodoc\n" +
                    "left join departamento dep\n" +
                    "on a.codigoDepartamento = dep.codigo\n" +
                    "left join municipio mun\n" +
                    "on a.codigoDepartamento = mun.codigoDepartamento\n" +
                    "and a.codigoMunicipio = mun.codigoMunicipio\n" +
                    "left join pagoxsesion t\n" +
                    "on t.codigoevento = a.codigoEvento\n" +
                    "and a.codigoCargo = t.codigocargo\n" +
                    "left join cargos ca\n" +
                    "on a.codigoCargo = ca.codigoCargo\n" +
                    "left join evento ev\n" +
                    "on a.codigoEvento = ev.codigoEvento\n" +
                    "left join asistencia asis\n" +
                    "on asis.iddivipol = a.idDivipol and asis.codigoevento = a.codigoEvento and asis.idempleado = b.idEmpleado\n" +
                    "where a.nrodoc is not null and a.nrodoc > 0\n" +
                    "and a.codigoEvento IN(6002)\n" +
                    "AND (\n" +
                    "(  a.codigoDepartamento IN('29') and a.codigoCargo IN(11,12,31,32,33,9,10) )\n" +
                    "   or\n" +
                    "(  a.codigoDepartamento IN('24') and a.codigoCargo IN(11,12,9,10) )\n" +
                    ")\n" +
                    "union\n" +
                    "SELECT a.codigoevento + 11000 as codigoEvento, b.tipodoc, a.codigodepartamento, dep.nombre as nombredepartamento,\n" +
                    "a.codigomunicipio, mun.nombre as nombremunicipio, a.nrodoc, apellido1, apellido2, nombre1, nombre2,\n" +
                    "celular, email, 10000 as valor, ca.descripcion  as cargo, 'TRANSP.' AS  nombre, ev.fecha, b.idEmpleado, 3 AS estado\n" +
                    "from detalle_cargo_x_puesto_x_evento a left join empleado b\n" +
                    "on a.nrodoc = b.nrodoc\n" +
                    "left join departamento dep\n" +
                    "on a.codigoDepartamento = dep.codigo\n" +
                    "left join municipio mun\n" +
                    "on a.codigoDepartamento = mun.codigoDepartamento\n" +
                    "and a.codigoMunicipio = mun.codigoMunicipio\n" +
                    "left join pagoxsesion t\n" +
                    "on t.codigoevento = a.codigoEvento\n" +
                    "and a.codigoCargo = t.codigocargo\n" +
                    "left join cargos ca\n" +
                    "on a.codigoCargo = ca.codigoCargo\n" +
                    "left join evento ev\n" +
                    "on a.codigoEvento = ev.codigoEvento\n" +
                    "left join asistencia asis\n" +
                    "on asis.iddivipol = a.idDivipol and asis.codigoevento = a.codigoEvento and asis.idempleado = b.idEmpleado\n" +
                    "where a.nrodoc is not null and a.nrodoc > 0\n" +
                    "and a.codigoEvento IN(6002)\n" +
                    "AND a.codigoDepartamento IN('24')\n" +
                    "AND a.codigoCargo IN(11,12,9,10)\n" +
                    "union\n" +
                    "SELECT a.codigoevento + 10000 as codigoEvento, b.tipodoc, a.codigodepartamento, dep.nombre as nombredepartamento,\n" +
                    "a.codigomunicipio, mun.nombre as nombremunicipio, a.nrodoc, apellido1, apellido2, nombre1, nombre2,\n" +
                    "celular, email, 17000 as valor, ca.descripcion  as cargo, 'REFRIG' AS  nombre, ev.fecha, b.idEmpleado, 3 AS estado\n" +
                    "from detalle_cargo_x_puesto_x_evento a left join empleado b\n" +
                    "on a.nrodoc = b.nrodoc\n" +
                    "left join departamento dep\n" +
                    "on a.codigoDepartamento = dep.codigo\n" +
                    "left join municipio mun\n" +
                    "on a.codigoDepartamento = mun.codigoDepartamento\n" +
                    "and a.codigoMunicipio = mun.codigoMunicipio\n" +
                    "left join pagoxsesion t\n" +
                    "on t.codigoevento = a.codigoEvento\n" +
                    "and a.codigoCargo = t.codigocargo\n" +
                    "left join cargos ca\n" +
                    "on a.codigoCargo = ca.codigoCargo\n" +
                    "left join evento ev\n" +
                    "on a.codigoEvento = ev.codigoEvento\n" +
                    "left join asistencia asis\n" +
                    "on asis.iddivipol = a.idDivipol and asis.codigoevento = a.codigoEvento and asis.idempleado = b.idEmpleado\n" +
                    "where a.nrodoc is not null and a.nrodoc > 0\n" +
                    "and a.codigoEvento IN(6002)\n" +
                    "AND a.codigoDepartamento IN('16')\n" +
                    "AND a.codigoCargo IN(9,10)\n" +
                    "union\n" +
                    "SELECT ev.codigoEvento, b.tipodoc, a.codigodepartamento, dep.nombre as nombredepartamento,\n" +
                    "a.codigomunicipio, mun.nombre as nombremunicipio,\n" +
                    "a.nrodoc, apellido1, apellido2, nombre1, nombre2,\n" +
                    "celular, email, t.valor, ca.descripcion as cargo,\n" +
                    "ev.nombre,\n" +
                    "ev.fecha, b.idEmpleado, asis.estado\n" +
                    "from detalle_cargo_x_puesto_x_evento a left join empleado b\n" +
                    "on a.nrodoc = b.nrodoc\n" +
                    "left join departamento dep\n" +
                    "on a.codigoDepartamento = dep.codigo\n" +
                    "left join municipio mun\n" +
                    "on a.codigoDepartamento = mun.codigoDepartamento\n" +
                    "and a.codigoMunicipio = mun.codigoMunicipio\n" +
                    "left join pagoxsesion t\n" +
                    "on a.codigoevento = t.codigoevento and a.codigoCargo = t.codigocargo\n" +
                    "left join cargos ca\n" +
                    "on a.codigoCargo = ca.codigoCargo\n" +
                    "left join evento ev\n" +
                    "on a.codigoEvento = ev.codigoEvento\n" +
                    "left join asistencia asis\n" +
                    "on asis.iddivipol = a.idDivipol and asis.codigoevento = a.codigoEvento and asis.idempleado = b.idEmpleado\n" +
                    "where a.nrodoc is not null and a.nrodoc > 0\n" +
                    "and a.codigoEvento not in( select codigoevento from evento where idprueba = 47 and eseleccion = 1 ) and a.idprueba = 47 and ev.esCapacitacion != 1\n" +
                    "and (asis.biometrico is not null)\n" +
                    ") as total  left join (\n" +
                    "	select deta.codigoZona, deta.codigoPuesto, divi.nombrePuesto,\n" +
                    "	ca.descripcion as ultimocargo, deta.ubicacion as ubicacion , deta.nrodoc, deta.codigoCargo, direccion, \n" +
                    "   deta.codigoMunicipio AS ultimomunicipio_codigo, divi.nombreMunicipio AS ultimomunicipio_nombre\n" +
                    "	from detalle_cargo_x_puesto_x_evento deta join divipol divi\n" +
                    "	on deta.codigoDepartamento = divi.codigoDepartamento\n" +
                    "	and deta.codigoMunicipio = divi.codigoMunicipio\n" +
                    "	and deta.codigoZona = divi.codigoZona\n" +
                    "	and deta.codigoPuesto = divi.codigoPuesto\n" +
                    "	and deta.idPrueba = divi.idPrueba\n" +
                    "	join cargos ca\n" +
                    "	on deta.codigoCargo = ca.codigoCargo\n" +
                    "	join empleado em\n" +
                    "	on deta.nrodoc = em.nrodoc\n" +
                    "	join (\n" +
                    "		select nrodoc, max(codigoevento) as codigoevento\n" +
                    "		from (\n" +
                    "			select e.nrodoc, dcpe.codigoEvento\n" +
                    "			from detalle_cargo_x_puesto_x_evento dcpe join empleado e\n" +
                    "			on dcpe.nrodoc = e.nrodoc\n" +
                    "			where dcpe.codigoEvento in(select codigoevento from evento where idprueba =  47  and eseleccion = 1 and requiereAsistenciaCtaCobro = 0)\n" +
                    "		) as cedula_eventos\n" +
                    "		group by nrodoc\n" +
                    "	) as ultimo_evento\n" +
                    "	on deta.nrodoc = ultimo_evento.nrodoc and deta.codigoEvento = ultimo_evento.codigoevento\n" +
                    "	) as eleccion\n" +
                    "on total.nrodoc = eleccion.nrodoc\n" +
                    "left join cuentacobro_control cc\n" +
                    "on total.idempleado = cc.idempleado and cc.idprueba =  47 \n" +
                    "where total.idempleado is not null\n" +
                    "and eleccion.nrodoc  is not NULL" ;
            
            if (!codigoDepartamento.equals(""))
            {
                sql = sql +" and total.codigodepartamento = '" + codigoDepartamento + "' \n" ;
            }
                    
            sql = sql + " order by total.codigodepartamento, ultimomunicipio_codigo,\n" +
                        "ultimocargo, codigozona, codigopuesto, ubicacion, total.nrodoc, fecha"; 

                    
                    conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet rs) {
                    try {
                        
                        Pago pago = new Pago();
                        List<RegistroEvento> registrosEvento;
                        RegistroEvento registroEvento;

                        int idCuentaOld = 0;
                        int idCuenta = 0;
                        
                        while (rs.next()) {
                            idCuenta = rs.getInt("idcuenta");

                            //Si es un nuevo personaje se crea un nuevo registro de pago
                            if (idCuenta != idCuentaOld)
                            {   
                                //si no es el primer registro
                                if (idCuentaOld != 0)
                                {
                                    pagos.add(pago);
                                }

                                pago = new Pago();
                                pago.setNombreDepartamento(rs.getString("nombredepartamento"));
                                pago.setNombreMunicipio(rs.getString("nombremunicipio"));
                                pago.setCodigoMunicipio(rs.getString("codigomunicipio"));
                                pago.setTipoIdentificacion(rs.getString("tipodoc"));
                                pago.setNrodoc(rs.getLong("nrodoc"));
                                pago.setApellido1(rs.getString("apellido1"));
                                pago.setApellido2(rs.getString("apellido2"));
                                pago.setNombre1(rs.getString("nombre1"));
                                pago.setNombre2(rs.getString("nombre2"));
                                pago.setCelular(rs.getString("celular"));
                                pago.setEmail(rs.getString("email"));
                                pago.setUltimoCargo(rs.getString("ultimocargo"));
                                pago.setUltimoZona(rs.getString("codigoZona"));
                                pago.setUltimoPuesto(rs.getString("codigoPuesto"));
                                pago.setUltimoUbicacion(rs.getString("ubicacion"));


                                pago.setIdCuenta(Integer.toString(idCuenta));

                                registrosEvento = new ArrayList();
                                registroEvento = new RegistroEvento();


                                registroEvento.setNombreCargo(rs.getString("cargo"));

                                if(rs.getString("nombreevento").equals("CAPACITACION"))
                                {
                                    registroEvento.setCodigoEvento(910);
                                }else
                                {
                                    registroEvento.setCodigoEvento(rs.getInt("codigoEvento"));
                                }

                                registroEvento.setValor(rs.getInt("valor"));
                                registroEvento.setFormaTomaAsistencia(rs.getString("formaAsistencia"));
                                registrosEvento.add(registroEvento);

                                pago.setRegistrosEvento(registrosEvento);

                            }else
                            {
                                //Se agregan como registro de evento

                                registroEvento = new RegistroEvento();

                                registroEvento.setNombreCargo(rs.getString("cargo"));

                                if(rs.getString("nombreevento").equals("CAPACITACION"))
                                {
                                    registroEvento.setCodigoEvento(910);
                                }else
                                {
                                    registroEvento.setCodigoEvento(rs.getInt("codigoEvento"));
                                }

                                registroEvento.setValor(rs.getInt("valor"));
                                registroEvento.setFormaTomaAsistencia(rs.getString("formaAsistencia"));

                                pago.getRegistrosEvento().add(registroEvento);
                            }

                            idCuentaOld = idCuenta;                        
                        }
                        
                        if (idCuentaOld != 0)
                        {
                            pagos.add(pago);
                        }
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(PagoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
                        
       }catch(Exception e)
       {
           Logger.getLogger(PagoDao.class.getName()).log(Level.SEVERE, null, e);
       }
       
       return pagos;

       
       
    }
    
    public List<Pago> generarArchivoPresidente(String codigoDepartamento, int idPrueba) {
        
       final List<Pago> pagos = new ArrayList<>();
       String sql = "";
       
       try{
            /*Consultar el idEmpleado*/
            sql =   " select distinct total.codigoEvento, total.tipodoc, total.codigodepartamento, total.nombredepartamento,\n" +
                    "total.codigomunicipio, total.nombremunicipio, total.nrodoc,\n" +
                    "total.apellido1, total.apellido2, total.nombre1, total.nombre2,\n" +
                    "total.celular, total.email, total.valor, total.cargo, total.nombre as nombreevento, total.fecha, total.idEmpleado,\n" +
                    "eleccion.codigoZona, eleccion.codigoPuesto,\n" +
                    "case when eleccion.nombrePuesto is null then '' else eleccion.nombrePuesto end as nombrePuesto,\n" +
                    "eleccion.ultimocargo, eleccion.ubicacion, eleccion.codigoCargo, cc.id as idcuenta, \n" +
                    "'SIN_VALIDAR'  as formaAsistencia\n" +
                    "from (\n" +
                    "select ev.codigoEvento, b.tipodoc, a.codigodepartamento, dep.nombre as nombredepartamento,\n" +
                    "a.codigomunicipio, mun.nombre as nombremunicipio, a.nrodoc, apellido1, apellido2, nombre1, nombre2,\n" +
                    "celular, email, t.valor, ca.descripcion  as cargo, ev.nombre, ev.fecha, b.idEmpleado\n" +
                    "from detalle_cargo_x_puesto_x_evento a left join empleado b\n" +
                    "on a.nrodoc = b.nrodoc\n" +
                    "left join departamento dep\n" +
                    "on a.codigoDepartamento = dep.codigo\n" +
                    "left join municipio mun\n" +
                    "on a.codigoDepartamento = mun.codigoDepartamento\n" +
                    "and a.codigoMunicipio = mun.codigoMunicipio\n" +
                    "left join pagoxsesion t\n" +
                    "on t.codigoevento = a.codigoEvento\n" +
                    "and a.codigoCargo = t.codigocargo\n" +
                    "left join cargos ca\n" +
                    "on a.codigoCargo = ca.codigoCargo\n" +
                    "left join evento ev\n" +
                    "on a.codigoEvento = ev.codigoEvento\n" +
                    "where a.nrodoc is not null\n" +
                    "and a.codigoEvento in(select codigoevento from evento where idprueba = 47 and eseleccion = 1)\n" +
                    "union\n" +
                    "SELECT 910 as codigoEvento, b.tipodoc, a.codigodepartamento, dep.nombre as nombredepartamento,\n" +
                    "a.codigomunicipio, mun.nombre as nombremunicipio, a.nrodoc, apellido1, apellido2, nombre1, nombre2,\n" +
                    "celular, email, t.valor, ca.descripcion  as cargo, 'CAPACITACION' as nombre, '2022-02-01' as fecha, b.idEmpleado\n" +
                    "from detalle_cargo_x_puesto_x_evento a join empleado b\n" +
                    "on a.nrodoc = b.nrodoc\n" +
                    "join departamento dep\n" +
                    "on a.codigoDepartamento = dep.codigo\n" +
                    "join municipio mun\n" +
                    "on a.codigoDepartamento = mun.codigoDepartamento\n" +
                    "and a.codigoMunicipio = mun.codigoMunicipio\n" +
                    "left join pagoxsesion t\n" +
                    "on t.codigoevento = 910\n" +
                    "and a.codigoCargo = t.codigocargo\n" +
                    "join cargos ca\n" +
                    "on a.codigoCargo = ca.codigoCargo\n" +
                    "join evento ev\n" +
                    "on a.codigoEvento = ev.codigoEvento\n" +
                    "join (\n" +
                    "	select nrodoc, max(codigoevento) as codigoevento\n" +
                    "	from (\n" +
                    "		select e.nrodoc, dcpe.codigoEvento\n" +
                    "		from detalle_cargo_x_puesto_x_evento dcpe join empleado e\n" +
                    "		on dcpe.nrodoc = e.nrodoc\n" +
                    "		where dcpe.codigoEvento in(select codigoevento from evento where idprueba = 47 and eseleccion = 1 and requiereAsistenciaCtaCobro = 0)\n" +
                    "	) as cedula_eventos\n" +
                    "	group by nrodoc\n" +
                    ") as ultimo_evento\n" +
                    "on a.nrodoc = ultimo_evento.nrodoc and a.codigoEvento = ultimo_evento.codigoevento\n" +
                    "union\n" +
                    "SELECT 1 as codigoEvento, b.tipodoc, a.codigodepartamento, dep.nombre as nombredepartamento,\n" +
                    "a.codigomunicipio, mun.nombre as nombremunicipio, a.nrodoc, apellido1, apellido2, nombre1, nombre2,\n" +
                    "celular, email, 12000 as valor, ca.descripcion  as cargo, 'REFRIG' AS  nombre, ev.fecha, b.idEmpleado\n" +
                    "from detalle_cargo_x_puesto_x_evento a left join empleado b\n" +
                    "on a.nrodoc = b.nrodoc\n" +
                    "left join departamento dep\n" +
                    "on a.codigoDepartamento = dep.codigo\n" +
                    "left join municipio mun\n" +
                    "on a.codigoDepartamento = mun.codigoDepartamento\n" +
                    "and a.codigoMunicipio = mun.codigoMunicipio\n" +
                    "left join pagoxsesion t\n" +
                    "on t.codigoevento = a.codigoEvento\n" +
                    "and a.codigoCargo = t.codigocargo\n" +
                    "left join cargos ca\n" +
                    "on a.codigoCargo = ca.codigoCargo\n" +
                    "left join evento ev\n" +
                    "on a.codigoEvento = ev.codigoEvento\n" +
                    "left join asistencia asis\n" +
                    "on asis.iddivipol = a.idDivipol and asis.codigoevento = a.codigoEvento and asis.idempleado = b.idEmpleado\n" +
                    "where a.nrodoc is not null and a.nrodoc > 0\n" +
                    "and a.codigoEvento IN(6000,6001)\n" +
                    "AND a.codigoDepartamento IN(SELECT codigo FROM depto_refrigerio)\n" +
                    "AND a.codigoCargo IN(11,12,31,32,33,9,10)\n" +
                    "UNION\n" +
                    "SELECT 2 as codigoEvento, b.tipodoc, a.codigodepartamento, dep.nombre as nombredepartamento,\n" +
                    "a.codigomunicipio, mun.nombre as nombremunicipio, a.nrodoc, apellido1, apellido2, nombre1, nombre2,\n" +
                    "celular, email, 17000 as valor, ca.descripcion  as cargo, 'REFRIG' AS  nombre, ev.fecha, b.idEmpleado\n" +
                    "from detalle_cargo_x_puesto_x_evento a left join empleado b\n" +
                    "on a.nrodoc = b.nrodoc\n" +
                    "left join departamento dep\n" +
                    "on a.codigoDepartamento = dep.codigo\n" +
                    "left join municipio mun\n" +
                    "on a.codigoDepartamento = mun.codigoDepartamento\n" +
                    "and a.codigoMunicipio = mun.codigoMunicipio\n" +
                    "left join pagoxsesion t\n" +
                    "on t.codigoevento = a.codigoEvento\n" +
                    "and a.codigoCargo = t.codigocargo\n" +
                    "left join cargos ca\n" +
                    "on a.codigoCargo = ca.codigoCargo\n" +
                    "left join evento ev\n" +
                    "on a.codigoEvento = ev.codigoEvento\n" +
                    "left join asistencia asis\n" +
                    "on asis.iddivipol = a.idDivipol and asis.codigoevento = a.codigoEvento and asis.idempleado = b.idEmpleado\n" +
                    "where a.nrodoc is not null and a.nrodoc > 0\n" +
                    "and a.codigoEvento IN(6002)\n" +
                    "AND (\n" +
                    "(  a.codigoDepartamento IN('29') and a.codigoCargo IN(11,12,31,32,33,9,10) )\n" +
                    "   or\n" +
                    "(  a.codigoDepartamento IN('24') and a.codigoCargo IN(11,12,9,10) )\n" +
                    ")\n" +
                    "union\n" +
                    "SELECT 3 as codigoEvento, b.tipodoc, a.codigodepartamento, dep.nombre as nombredepartamento,\n" +
                    "a.codigomunicipio, mun.nombre as nombremunicipio, a.nrodoc, apellido1, apellido2, nombre1, nombre2,\n" +
                    "celular, email, 10000 as valor, ca.descripcion  as cargo, 'TRANSP.' AS  nombre, ev.fecha, b.idEmpleado\n" +
                    "from detalle_cargo_x_puesto_x_evento a left join empleado b\n" +
                    "on a.nrodoc = b.nrodoc\n" +
                    "left join departamento dep\n" +
                    "on a.codigoDepartamento = dep.codigo\n" +
                    "left join municipio mun\n" +
                    "on a.codigoDepartamento = mun.codigoDepartamento\n" +
                    "and a.codigoMunicipio = mun.codigoMunicipio\n" +
                    "left join pagoxsesion t\n" +
                    "on t.codigoevento = a.codigoEvento\n" +
                    "and a.codigoCargo = t.codigocargo\n" +
                    "left join cargos ca\n" +
                    "on a.codigoCargo = ca.codigoCargo\n" +
                    "left join evento ev\n" +
                    "on a.codigoEvento = ev.codigoEvento\n" +
                    "left join asistencia asis\n" +
                    "on asis.iddivipol = a.idDivipol and asis.codigoevento = a.codigoEvento and asis.idempleado = b.idEmpleado\n" +
                    "where a.nrodoc is not null and a.nrodoc > 0\n" +
                    "and a.codigoEvento IN(6002)\n" +
                    "AND a.codigoDepartamento IN('24')\n" +
                    "AND a.codigoCargo IN(11,12,9,10)\n" +
                    "union\n" +
                    "SELECT 4 as codigoEvento, b.tipodoc, a.codigodepartamento, dep.nombre as nombredepartamento,\n" +
                    "a.codigomunicipio, mun.nombre as nombremunicipio, a.nrodoc, apellido1, apellido2, nombre1, nombre2,\n" +
                    "celular, email, 17000 as valor, ca.descripcion  as cargo, 'REFRIG' AS  nombre, ev.fecha, b.idEmpleado\n" +
                    "from detalle_cargo_x_puesto_x_evento a left join empleado b\n" +
                    "on a.nrodoc = b.nrodoc\n" +
                    "left join departamento dep\n" +
                    "on a.codigoDepartamento = dep.codigo\n" +
                    "left join municipio mun\n" +
                    "on a.codigoDepartamento = mun.codigoDepartamento\n" +
                    "and a.codigoMunicipio = mun.codigoMunicipio\n" +
                    "left join pagoxsesion t\n" +
                    "on t.codigoevento = a.codigoEvento\n" +
                    "and a.codigoCargo = t.codigocargo\n" +
                    "left join cargos ca\n" +
                    "on a.codigoCargo = ca.codigoCargo\n" +
                    "left join evento ev\n" +
                    "on a.codigoEvento = ev.codigoEvento\n" +
                    "left join asistencia asis\n" +
                    "on asis.iddivipol = a.idDivipol and asis.codigoevento = a.codigoEvento and asis.idempleado = b.idEmpleado\n" +
                    "where a.nrodoc is not null and a.nrodoc > 0\n" +
                    "and a.codigoEvento IN(6002)\n" +
                    "AND a.codigoDepartamento IN('16')\n" +
                    "AND a.codigoCargo IN(9,10)\n" +
                    "union\n" +
                    "SELECT 5 as codigoEvento, b.tipodoc, a.codigodepartamento, dep.nombre as nombredepartamento,\n" +
                    "a.codigomunicipio, mun.nombre as nombremunicipio, a.nrodoc, apellido1, apellido2, nombre1, nombre2,\n" +
                    "celular, email, 30000 as valor, ca.descripcion  as cargo, 'JORNADA EXT' AS  nombre, ev.fecha, b.idEmpleado\n" +
                    "from detalle_cargo_x_puesto_x_evento a left join empleado b\n" +
                    "on a.nrodoc = b.nrodoc\n" +
                    "left join departamento dep\n" +
                    "on a.codigoDepartamento = dep.codigo\n" +
                    "left join municipio mun\n" +
                    "on a.codigoDepartamento = mun.codigoDepartamento\n" +
                    "and a.codigoMunicipio = mun.codigoMunicipio\n" +
                    "left join pagoxsesion t\n" +
                    "on t.codigoevento = a.codigoEvento\n" +
                    "and a.codigoCargo = t.codigocargo\n" +
                    "left join cargos ca\n" +
                    "on a.codigoCargo = ca.codigoCargo\n" +
                    "left join evento ev\n" +
                    "on a.codigoEvento = ev.codigoEvento\n" +
                    "left join asistencia asis\n" +
                    "on asis.iddivipol = a.idDivipol and asis.codigoevento = a.codigoEvento and asis.idempleado = b.idEmpleado\n" +
                    "where a.nrodoc is not null and a.nrodoc > 0\n" +
                    "and a.codigoEvento IN(7176)\n" +
                    "AND a.codigoDepartamento IN('16')\n" +
                    "union\n" +
                    "SELECT 6 AS codigoEvento, b.tipodoc, a.codigodepartamento, dep.nombre as nombredepartamento,\n" +
                    "a.codigomunicipio, mun.nombre as nombremunicipio, a.nrodoc, apellido1, apellido2, nombre1, nombre2,\n" +
                    "celular, email,\n" +
                    "case when a.codigomunicipio = '15247' then 225000\n" +
                    "when a.codigomunicipio IN('15142','15292') then 135000\n" +
                    "ELSE 180000\n" +
                    "END as valor,\n" +
                    "'INSTRUCTOR '  as cargo, 'APOYO ADMIN' AS  nombre, ev.fecha, b.idEmpleado\n" +
                    "from detalle_cargo_x_puesto_x_evento a left join empleado b\n" +
                    "on a.nrodoc = b.nrodoc\n" +
                    "left join departamento dep\n" +
                    "on a.codigoDepartamento = dep.codigo\n" +
                    "left join municipio mun\n" +
                    "on a.codigoDepartamento = mun.codigoDepartamento\n" +
                    "and a.codigoMunicipio = mun.codigoMunicipio\n" +
                    "left join pagoxsesion t\n" +
                    "on t.codigoevento = a.codigoEvento\n" +
                    "and a.codigoCargo = t.codigocargo\n" +
                    "left join cargos ca\n" +
                    "on a.codigoCargo = ca.codigoCargo\n" +
                    "left join evento ev\n" +
                    "on a.codigoEvento = ev.codigoEvento\n" +
                    "left join asistencia asis\n" +
                    "on asis.iddivipol = a.idDivipol and asis.codigoevento = a.codigoEvento and asis.idempleado = b.idEmpleado\n" +
                    "where a.nrodoc is not null and a.nrodoc > 0\n" +
                    "and a.codigoEvento IN(6002)\n" +
                    "AND a.codigoDepartamento IN('15')\n" +
                    "AND a.codigoCargo IN('31')\n" +
                    "union\n" +
                    "SELECT ev.codigoEvento, b.tipodoc, a.codigodepartamento, dep.nombre as nombredepartamento,\n" +
                    "a.codigomunicipio, mun.nombre as nombremunicipio,\n" +
                    "a.nrodoc, apellido1, apellido2, nombre1, nombre2,\n" +
                    "celular, email, t.valor, ca.descripcion as cargo,\n" +
                    "ev.nombre,\n" +
                    "ev.fecha, b.idEmpleado\n" +
                    "from detalle_cargo_x_puesto_x_evento a left join empleado b\n" +
                    "on a.nrodoc = b.nrodoc\n" +
                    "left join departamento dep\n" +
                    "on a.codigoDepartamento = dep.codigo\n" +
                    "left join municipio mun\n" +
                    "on a.codigoDepartamento = mun.codigoDepartamento\n" +
                    "and a.codigoMunicipio = mun.codigoMunicipio\n" +
                    "left join pagoxsesion t\n" +
                    "on a.codigoevento = t.codigoevento and a.codigoCargo = t.codigocargo\n" +
                    "left join cargos ca\n" +
                    "on a.codigoCargo = ca.codigoCargo\n" +
                    "left join evento ev\n" +
                    "on a.codigoEvento = ev.codigoEvento\n" +
                    "left join asistencia asis\n" +
                    "on asis.iddivipol = a.idDivipol and asis.codigoevento = a.codigoEvento and asis.idempleado = b.idEmpleado\n" +
                    "where a.nrodoc is not null and a.nrodoc > 0\n" +
                    "and a.codigoEvento not in( select codigoevento from evento where idprueba = 47 and eseleccion = 1 ) and a.idprueba = 47 and ev.esCapacitacion != 1\n" +
                    "and (asis.biometrico is not null)\n" +
                    ") as total  left join (\n" +
                    "	select deta.codigoZona, deta.codigoPuesto, divi.nombrePuesto,\n" +
                    "	ca.descripcion as ultimocargo, deta.ubicacion as ubicacion , deta.nrodoc, deta.codigoCargo\n" +
                    "	from detalle_cargo_x_puesto_x_evento deta join divipol divi\n" +
                    "	on deta.codigoDepartamento = divi.codigoDepartamento\n" +
                    "	and deta.codigoMunicipio = divi.codigoMunicipio\n" +
                    "	and deta.codigoZona = divi.codigoZona\n" +
                    "	and deta.codigoPuesto = divi.codigoPuesto\n" +
                    "	and deta.idPrueba = divi.idPrueba\n" +
                    "	join cargos ca\n" +
                    "	on deta.codigoCargo = ca.codigoCargo\n" +
                    "	join empleado em\n" +
                    "	on deta.nrodoc = em.nrodoc\n" +
                    "	join (\n" +
                    "		select nrodoc, max(codigoevento) as codigoevento\n" +
                    "		from (\n" +
                    "			select e.nrodoc, dcpe.codigoEvento\n" +
                    "			from detalle_cargo_x_puesto_x_evento dcpe join empleado e\n" +
                    "			on dcpe.nrodoc = e.nrodoc\n" +
                    "			where dcpe.codigoEvento in(select codigoevento from evento where idprueba =  47  and eseleccion = 1 and requiereAsistenciaCtaCobro = 0)\n" +
                    "		) as cedula_eventos\n" +
                    "		group by nrodoc\n" +
                    "	) as ultimo_evento\n" +
                    "	on deta.nrodoc = ultimo_evento.nrodoc and deta.codigoEvento = ultimo_evento.codigoevento\n" +
                    "	) as eleccion\n" +
                    "on total.nrodoc = eleccion.nrodoc\n" +
                    "left join cuentacobro_control cc\n" +
                    "on total.idempleado = cc.idempleado and cc.idprueba =  47 \n" +
                    "where total.idempleado is not null\n" +
                    "and eleccion.nrodoc  is not NULL " +
                    "and total.codigodepartamento = '" + codigoDepartamento + "' \n" +
                    "order by total.codigodepartamento, total.codigomunicipio,\n" +
                    "ultimocargo, codigozona, codigopuesto, ubicacion, total.nrodoc, fecha"; 
            conex.consultar(sql, new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet rs) {
                    try {
                        
                        Pago pago = new Pago();
                        List<RegistroEvento> registrosEvento;
                        RegistroEvento registroEvento;

                        int idCuentaOld = 0;
                        int idCuenta = 0;
                        
                        while (rs.next()) {
                            idCuenta = rs.getInt("idcuenta");

                            //Si es un nuevo personaje se crea un nuevo registro de pago
                            if (idCuenta != idCuentaOld)
                            {   
                                //si no es el primer registro
                                if (idCuentaOld != 0)
                                {
                                    pagos.add(pago);
                                }

                                pago = new Pago();
                                pago.setNombreDepartamento(rs.getString("nombredepartamento"));
                                pago.setNombreMunicipio(rs.getString("nombremunicipio"));
                                pago.setCodigoMunicipio(rs.getString("codigomunicipio"));
                                pago.setNrodoc(rs.getLong("nrodoc"));
                                pago.setApellido1(rs.getString("apellido1"));
                                pago.setApellido2(rs.getString("apellido2"));
                                pago.setNombre1(rs.getString("nombre1"));
                                pago.setNombre2(rs.getString("nombre2"));
                                pago.setCelular(rs.getString("celular"));
                                pago.setEmail(rs.getString("email"));
                                pago.setUltimoCargo(rs.getString("ultimocargo"));
                                pago.setUltimoZona(rs.getString("codigoZona"));
                                pago.setUltimoPuesto(rs.getString("codigoPuesto"));
                                pago.setUltimoUbicacion(rs.getString("ubicacion"));


                                pago.setIdCuenta((Integer.toString(idCuenta + (idPrueba * 100000) )));

                                registrosEvento = new ArrayList();
                                registroEvento = new RegistroEvento();


                                registroEvento.setNombreCargo(rs.getString("cargo"));

                                if(rs.getString("nombreevento").equals("CAPACITACION"))
                                {
                                    registroEvento.setCodigoEvento(910);
                                }else
                                {
                                    registroEvento.setCodigoEvento(rs.getInt("codigoEvento"));
                                }

                                registroEvento.setValor(rs.getInt("valor"));
                                registroEvento.setFormaTomaAsistencia(rs.getString("formaAsistencia"));
                                registrosEvento.add(registroEvento);

                                pago.setRegistrosEvento(registrosEvento);

                            }else
                            {
                                //Se agregan como registro de evento

                                registroEvento = new RegistroEvento();

                                registroEvento.setNombreCargo(rs.getString("cargo"));

                                if(rs.getString("nombreevento").equals("CAPACITACION"))
                                {
                                    registroEvento.setCodigoEvento(910);
                                }else
                                {
                                    registroEvento.setCodigoEvento(rs.getInt("codigoEvento"));
                                }

                                registroEvento.setValor(rs.getInt("valor"));
                                registroEvento.setFormaTomaAsistencia(rs.getString("formaAsistencia"));

                                pago.getRegistrosEvento().add(registroEvento);
                            }

                            idCuentaOld = idCuenta;                        
                        }
                        
                        if (idCuentaOld != 0)
                        {
                            pagos.add(pago);
                        }
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(PagoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
                        
       }catch(Exception e)
       {
           Logger.getLogger(PagoDao.class.getName()).log(Level.SEVERE, null, e);
       }
       
       return pagos;

       
       
    }
    
    public List<PagoEvento> getEventos(String codigoDepartamento, int idPrueba){

      final List<PagoEvento> pagoEventos = new ArrayList<>();
       
       try{
           
            StringBuilder query = new StringBuilder();
           
            query.append("select distinct ev.codigoevento, ev.nombre, ev.fecha\n");
            query.append("from detalle_cargo_x_puesto_x_evento dcpe left join empleado e\n");
            query.append("on dcpe.nrodoc = e.nrodoc\n");
            query.append("left join evento ev\n");
            query.append("on dcpe.codigoEvento = ev.codigoEvento\n");
            query.append("left join asistencia asis\n" );
            query.append("on dcpe.codigoEvento = asis.codigoevento and e.idEmpleado = asis.idempleado\n" );
            query.append("where dcpe.idprueba = ");
            query.append(idPrueba);
            query.append(" and dcpe.codigoDepartamento = '");
            query.append(codigoDepartamento);
            query.append("' \n");
            query.append(" and asis.biometrico is not null \n" );
            query.append(" and tiposesion NOT IN('CAPACITACION', 'ADMINISTRATIVOS') \n" );
            query.append("union\n" );
            query.append("select codigoevento, nombre, fecha\n" );
            query.append("from evento\n" );
            query.append("where idprueba = ");
            query.append(idPrueba);
            query.append(" and tiposesion = 'REFRIGERIO'\n" );
            query.append(" and coddepartamento = '");
            query.append(codigoDepartamento);
            query.append("' \n" );
            query.append(" union\n" );
            query.append(" SELECT codigoevento + 10000 AS codigoevento, 'REFRIG' as nombre, fecha FROM evento WHERE codigoEvento IN(6000,6001,6002)\n" );
            query.append(" union\n" );
            query.append(" SELECT codigoevento + 11000 AS codigoevento, 'TRANSP' as nombre, fecha FROM evento WHERE codigoEvento IN(6002)\n" );
            query.append(" union\n" );
            query.append("select 910 AS codigoevento, 'CAPACITACION' as nombre, '2022-02-01' as fecha\n" );
            query.append("order by fecha ");
            
            
            conex.consultar(query.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet rs) {
                    try {
                        while (rs.next()) {
                            PagoEvento pagoEvento = new PagoEvento();
                            pagoEvento = new PagoEvento();
                            pagoEvento.setCodigoEvento(rs.getInt("codigoevento"));
                            pagoEvento.setNombre(rs.getString("nombre"));
                            pagoEvento.setFecha(rs.getString("fecha"));
                            pagoEventos.add(pagoEvento);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PagoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
            
       }
       
        catch(Exception e)
       {
           Logger.getLogger(BancoDao.class.getName()).log(Level.SEVERE, null, e);
       }
       return pagoEventos;       
       
    }
    
}
