/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.statusCargue;

import co.com.grupoasd.nomina.modelo.StatusCargue;
import java.io.File;
import java.util.List;

/**
 *
 * @author Pedro Rodríguez
 */
public interface IStatus {
    public List<StatusCargue> listar(String usuario, int idTipoCargue);
    
    public List<StatusCargue> listar(String usuario, int idTipoCargue, int identificadorRegistro);
            
    public int Insertar(StatusCargue s);
    
    public Boolean ActualizarAvance(StatusCargue s);
    
    public Boolean Finalizar(StatusCargue s, StringBuilder sb);

    public File generarReporteErrores(int idStatusCargue);
    
}
