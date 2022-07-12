/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.empleado;

import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.EmpleadoSesion;

/**
 *
 * @author Grupo ASD
 */
public interface IEmpleado{
    public EmpleadoSesion getEmpleadoSesion(String cedula);
    
    public int Insertar(Empleado e);
    
    public Boolean Actualizar (Empleado e); 
    
    public Empleado getHuellaEmpleadoById(int id);
    
}
