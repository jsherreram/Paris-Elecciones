/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.mediopago;

import co.com.grupoasd.nomina.dao.BancoDao;
import co.com.grupoasd.nomina.modelo.Banco;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Iván Vargas
 */
public class BancoController implements IBancoController {

    private BancoDao objBancoDao = null;

    public BancoController() {
        objBancoDao = new BancoDao();
    }

    @Override
    public List<Banco> getListBancos() throws Exception {
        
        List<Banco> lstBanco = new ArrayList<>();
        try {
            lstBanco = objBancoDao.listar();
        } catch (Exception e) {
            throw new Exception("Error al listar los bancos", e);
        }
        return lstBanco;
    }

}
