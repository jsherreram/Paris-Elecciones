/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo.seguimiento;

import co.com.grupoasd.nomina.modelo.VacantesSitioCargo;
import java.util.ArrayList;

/**
 *
 * @author Grupo ASD
 */
public interface ISeguimiento {
    public ArrayList<VacantesSitioCargo> vacantesSitioCargo(VacantesSitioCargo busqueda) throws Exception;
}
