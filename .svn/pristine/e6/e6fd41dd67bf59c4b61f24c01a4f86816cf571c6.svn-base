/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.empleadopruebaestado;

import co.com.grupoasd.nomina.modelo.EmpleadoPruebaEstado;

/**
 *
 * @author Grupo ASD
 */
public interface IEmpleadoPruebaEstado {

    /**
     *
     * @param epe
     * @return
     */
    public EmpleadoPruebaEstado getPrueba(EmpleadoPruebaEstado epe);

    /**
     *
     * @param epe
     * @return
     */
    public EmpleadoPruebaEstado getPruebaById(EmpleadoPruebaEstado epe);

    /**
     *
     * @param epe
     * @return
     */
    public Boolean actualizarEstado(EmpleadoPruebaEstado epe);
    
    public String buscarJsonFuncionariosConRoles(int nrodoc, String apellido1,  String nombre1, String rol, int idPrueba);
    
    String buscarJsonFuncionario(int idEmpleado, int idPrueba);
    
    String buscarJsonFuncionarioCedula(long nroDoc, int idPrueba);
       
    Boolean asignarRolesNodos(String json) throws Exception ;
}
