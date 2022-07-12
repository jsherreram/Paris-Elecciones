/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.convocatoria;

import co.com.grupoasd.nomina.modelo.PersonaDisponibleVacante;
import java.util.ArrayList;

/**
 *
 * @author CristianAlexander
 */
public interface IAsignacion {
    public ArrayList<PersonaDisponibleVacante> getPersonasDisponibles(String codigoMunicipio, String codigoCargo) throws Exception;
}
