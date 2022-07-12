/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.confirmacionevento;

import co.com.grupoasd.nomina.dao.EmpleadoPruebaEstadoDao;
import co.com.grupoasd.nomina.modelo.ConfirmarAsistenciaVO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Grupo ASD
 */
public class ConfirmarEventoController implements IConfirmarEvento{
    

    @Override
    public List<ConfirmarAsistenciaVO> consultarConvocatoria(String idUsuario) {
        EmpleadoPruebaEstadoDao dao = new EmpleadoPruebaEstadoDao();
        return dao.listar(idUsuario);
    }
    
    public static void main(String[]args){
        List<ConfirmarAsistenciaVO> lista = new ArrayList<ConfirmarAsistenciaVO>();
        EmpleadoPruebaEstadoDao dao = new EmpleadoPruebaEstadoDao();
        lista = dao.listar("119874");
    }
    
}
