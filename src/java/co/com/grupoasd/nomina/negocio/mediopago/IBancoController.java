/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.mediopago;

import co.com.grupoasd.nomina.modelo.Banco;
import java.util.List;

/**
 *
 * @author Iván Vargas
 */
public interface IBancoController {

    public List<Banco> getListBancos() throws Exception;
}
