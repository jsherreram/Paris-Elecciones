/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.pds;

import co.com.grupoasd.nomina.dao.EventoDao;
import co.com.grupoasd.nomina.dao.PdsDao;
import co.com.grupoasd.nomina.modelo.CantidadPds;
import co.com.grupoasd.nomina.modelo.Evento;
import co.com.grupoasd.nomina.modelo.Pds;
import co.com.grupoasd.nomina.modelo.Prueba;
import java.util.List;

/**
 *
 * @author CristianAlexander
 */
public class PdsController implements IPds {

    
    public CantidadPds cantidadCargosDisponibles(String sitio, String cargo, String evento){
        PdsDao dao = new PdsDao();
        return dao.consultarCantidadVacantes(sitio, cargo, evento);
    }

    @Override
    public List<Pds> listarpdsxSitio(String sitio, String evento) throws Exception {
        Prueba prueba = new Prueba();
        Evento eventoC = new Evento();
        PdsDao dao = new PdsDao();
        EventoDao daoE = new EventoDao();
        eventoC = daoE.GetById(Integer.parseInt(evento));
        prueba = eventoC.getPrueba();
        return dao.listarpdsxSitio(sitio, prueba.getIdprueba());
    }
    
}
