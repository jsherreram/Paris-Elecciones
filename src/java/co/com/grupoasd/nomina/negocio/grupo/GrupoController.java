/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.grupoasd.nomina.negocio.grupo;

import co.com.grupoasd.nomina.dao.GrupoDao;
import co.com.grupoasd.nomina.modelo.Grupo;
import java.util.List;

/**
 *
 * @author ASD
 */
public class GrupoController  implements IGrupoController{
    
    private GrupoDao grupoDao;

    public GrupoController() {
        grupoDao = new GrupoDao();
    }
    @Override
    public List<Grupo> listarGruposFuncionarios()   {
         return grupoDao.listarGruposFuncionarios();
    }
    

}
