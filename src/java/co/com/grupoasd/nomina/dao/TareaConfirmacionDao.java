/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.TareaConfirmacion;
import co.com.grupoasd.nomina.modelo.seguimiento.ResultSetConverter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author ASD
 */
public class TareaConfirmacionDao {

    private Operaciones conex = new Operaciones();

    public boolean insertar(TareaConfirmacion tarea) {

        boolean inserto = false;

        StringBuilder sql = new StringBuilder();
                 sql.append("insert into tarea_confirmacion (tipo, estado, forma_confirmacion, fecha_confirmacion,");
                 sql.append(" id_empleado, cod_cargo, id_prueba, iddivipol) ");
                 sql.append("values(?,?,?,?,?,?,?,?)");

        inserto = conex.ejecutar(sql.toString(), tarea.getTipo(), tarea.getEstado(),
                tarea.getFormaConfirmacion(), tarea.getFechaConfirmacion(),
                tarea.getIdEmpleado(), tarea.getCodigoCargo(),
                tarea.getIdPrueba(), tarea.getIdDivipol());
        return inserto;

    }

    public TareaConfirmacion buscarConfirmacion(int idempleado, int idprueba, String codcargo, String tipo) {

        final TareaConfirmacion tarea = new TareaConfirmacion();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM tarea_confirmacion where tipo= '").append(tipo);
        sql.append("' and id_prueba=").append(idprueba);
        sql.append(" and id_empleado=").append(idempleado); 
        sql.append(" and cod_cargo='").append(codcargo).append("'");
        
        conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet res) {
                try {
                    while (res.next()) {
                        tarea.setCodigoCargo(res.getString("cod_cargo"));
                        tarea.setIdEmpleado(Integer.parseInt(res.getString("id_empleado")));
                        tarea.setIdPrueba(Integer.parseInt(res.getString("id_prueba")));
                        tarea.setTipo(res.getString("tipo"));
                        tarea.setEstado(Integer.parseInt(res.getString("estado")));
                        tarea.setIdDivipol(res.getInt("iddivipol"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(TareaConfirmacionDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        return tarea;
    }
    
    public TareaConfirmacion buscarContratoEmpleado(int idempleado, int idprueba, String tipo) {

        final TareaConfirmacion tarea = new TareaConfirmacion();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * FROM tarea_confirmacion where tipo='").append(tipo);
        sql.append("' and id_prueba=").append(idprueba);
        sql.append(" and id_empleado=").append(idempleado);
        sql.append(" limit 1");
        
        conex.consultar(sql.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet res) {
                try {
                    while (res.next()) {
                        tarea.setCodigoCargo(res.getString("cod_cargo"));
                        tarea.setIdEmpleado(Integer.parseInt(res.getString("id_empleado")));
                        tarea.setIdPrueba(Integer.parseInt(res.getString("id_prueba")));
                        tarea.setTipo(res.getString("tipo"));
                        tarea.setEstado(Integer.parseInt(res.getString("estado")));
                        tarea.setIdDivipol(res.getInt("iddivipol"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(TareaConfirmacionDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        return tarea;
    }

    

    /* Consulta de estado Nacional x departamento */
    public String listarConfirmacionTipo(int idPrueba, int idEmpleado) {

        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            StringBuilder sql=new StringBuilder();
            sql.append( "select distinct e.idEmpleado, e.huella , e.nombre1, e.nombre2, e.apellido1, e.apellido2, e.celular, e.nrodoc, c.descripcion , c.codigoCargo, epe.idprueba, ");
            sql.append(" case (select count(*) from tarea_confirmacion tc where tc.id_empleado=e.idempleado and tc.cod_cargo=c.codigoCargo and tc.tipo='contrato'");
            sql.append(" and tc.id_prueba=");
            sql.append(idPrueba);
            sql.append(")");
            sql.append(" when 0 then 'No' else 'Si' end as firmo_contrato,");
            sql.append(" case (select count(*) from tarea_confirmacion tc where tc.id_empleado=e.idempleado and tc.cod_cargo=c.codigoCargo and tc.tipo='cuenta de cobro'");
            sql.append(" and tc.id_prueba=");
            sql.append(idPrueba);
            sql.append(")");
            sql.append(" when 0 then 'No' else 'Si' end as firmo_cobro, ");
            sql.append("(select a.salon from asignacion_salon a  where a.idempleado=e.idEmpleado and");
            sql.append(" a.iddivipol=epe.iddivipol and a.idprueba=epe.idprueba) as salon");
            sql.append(" from empleado e");
            sql.append(" inner join empleado_x_prueba_x_estado epe ON epe.idempleado=e.idEmpleado ");
            sql.append(" inner join cargos c ON  c.codigoCargo=epe.codigoCargo");
            sql.append(" where  epe.iddivipol=(select distinct us.iddivipol FROM usuario_sitio us ");
            sql.append(" INNER JOIN empleado e ON e.nrodoc=us.usuario ");
            sql.append(" where e.idEmpleado=");
            sql.append(idEmpleado);
            sql.append(") and epe.idprueba=");
            sql.append(idPrueba);
        

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

}
