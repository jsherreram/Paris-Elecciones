package co.com.grupoasd.nomina.negocio.empleadopruebaestado;

import co.com.grupoasd.nomina.dao.DepartamentoDao;
import co.com.grupoasd.nomina.dao.DivipolDao;
import co.com.grupoasd.nomina.dao.EmpleadoPruebaEstadoDao;
import co.com.grupoasd.nomina.dao.UsuarioDepartamentoDao;
import co.com.grupoasd.nomina.dao.UsuarioGrupoDao;
import co.com.grupoasd.nomina.dao.UsuarioSitioDao;
import co.com.grupoasd.nomina.modelo.Departamento;
import co.com.grupoasd.nomina.modelo.EmpleadoPruebaEstado;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Grupo ASD
 */
public class EmpleadoPruebaEstadoController implements IEmpleadoPruebaEstado {

    @Override
    public EmpleadoPruebaEstado getPrueba(EmpleadoPruebaEstado epe) {
        EmpleadoPruebaEstadoDao eped = new EmpleadoPruebaEstadoDao();
        return eped.getPrueba(epe.getIdEmpleado(), epe.getIdPrueba(), epe.getCargo());
    }

    @Override
    public EmpleadoPruebaEstado getPruebaById(EmpleadoPruebaEstado epe) {
        EmpleadoPruebaEstadoDao eped = new EmpleadoPruebaEstadoDao();
        return eped.getPruebaById(epe.getId());
    }

    /**
     *
     * @param epe
     * @return
     */
    @Override
    public Boolean actualizarEstado(EmpleadoPruebaEstado epe) {
        EmpleadoPruebaEstadoDao dao = new EmpleadoPruebaEstadoDao();
        return dao.actualizarEstado(epe);
    }

    @Override
    public String buscarJsonFuncionariosConRoles(int nrodoc, String apellido1, String nombre1, String rol, int idPrueba) {
        EmpleadoPruebaEstadoDao dao = new EmpleadoPruebaEstadoDao();
        return dao.buscarJsonFuncionariosConRoles(nrodoc, apellido1, nombre1, rol, idPrueba);
    }

    @Override
    public String buscarJsonFuncionario(int idEmpleado, int idPrueba) {
        EmpleadoPruebaEstadoDao dao = new EmpleadoPruebaEstadoDao();
        return dao.buscarJsonFuncionario(idEmpleado, idPrueba);
    }

    @Override
    public String buscarJsonFuncionarioCedula(long nroDoc, int idPrueba) {
        EmpleadoPruebaEstadoDao dao = new EmpleadoPruebaEstadoDao();
        return dao.buscarJsonFuncionarioCedula(nroDoc, idPrueba);
    }

    @Override
    public Boolean asignarRolesNodos(String objetoJson) throws Exception {
        UsuarioGrupoDao daoUsrGrupo = new UsuarioGrupoDao();
        UsuarioDepartamentoDao daoUsrDep = new UsuarioDepartamentoDao();
        UsuarioSitioDao daoUsrSitio = new UsuarioSitioDao();
        DivipolDao daoDivipol = new DivipolDao();
        DepartamentoDao depDao = new DepartamentoDao();
        JSONObject jsonObj = new JSONObject(objetoJson);
        JSONArray listadoRoles = (JSONArray) jsonObj.get("roles");
        JSONArray listadoNodos = (JSONArray) jsonObj.get("nodos");

        long nroDoc = Long.valueOf(jsonObj.get("nroDoc").toString()).longValue();
        int idPrueba = Integer.valueOf(jsonObj.get("idPrueba").toString()).intValue();
        boolean rolAdmin = false;
        String rol;
        String dpto;
        int existeUsuarioRol = 0;
        boolean creoRegistro;
        boolean actualizo;
        List<Long> listadoSitios;
        //deshabilita los roles para el usuario y prueba que esten contenidos en el listado de los
        //roles que se pueden asignar
        boolean actualizoRegistros = daoUsrGrupo.actualizarEstado(nroDoc, idPrueba, 0);
        if (!actualizoRegistros) {
            return false;
        }
        for (int i = 0; i < listadoRoles.length(); i++) {
            rol = listadoRoles.getString(i);
            if (rol.equals("administrador") || rol.equals("administrador_icfes")) {
                rolAdmin = true;
            }
            existeUsuarioRol = daoUsrGrupo.existeUsuarioGrupo(nroDoc, rol, idPrueba);
            if (existeUsuarioRol == 0) {
                creoRegistro = daoUsrGrupo.insertar(nroDoc, rol, idPrueba, 1);
                if (!creoRegistro) {
                    throw new Exception("No se pudo asignar los roles");
                }
            } else {
                actualizo = daoUsrGrupo.actualizar(nroDoc, rol, idPrueba, 1);
                if (!actualizo) {
                    throw new Exception("No se pudo asignar los roles.");
                }
            }
        }

        //Elimina los nodos para el usuario 
        actualizoRegistros = daoUsrDep.eliminarRegistros(nroDoc,idPrueba);
        if (!actualizoRegistros) {
            return false;
        }

        //Elimina los sitios para el usuario 
        actualizoRegistros = daoUsrSitio.eliminarRegistros(nroDoc,idPrueba);
        if (!actualizoRegistros) {
            return false;
        }

        //Registra todos los nodos y sitios para roles administrador o administrador_icfes.
        if (rolAdmin) {
            List<Departamento> listaNodos = depDao.listar();
            for (Departamento departamento : listaNodos) {
                creoRegistro = daoUsrDep.insertar(departamento.getCodigo(), nroDoc,idPrueba);
                if (!creoRegistro) {
                    throw new Exception("No se pudo asignar el departamento para el usuario");
                }

                listadoSitios = daoDivipol.getListDivipolPorDpto(departamento.getCodigo(), idPrueba);
                for (Long sitio : listadoSitios) {
                    //Registra los sitios del usuario
                    creoRegistro = daoUsrSitio.insertar(sitio.longValue(), nroDoc);
                    if (!creoRegistro) {
                        throw new Exception("No se pudo asignar los sitios del nodo al usuario");
                    }
                }
            }
        } else {
            //Registra Nodos y sitios seleccionados para roles coordinadores.
            for (int i = 0; i < listadoNodos.length(); i++) {
                dpto = listadoNodos.getString(i);
                creoRegistro = daoUsrDep.insertar(dpto, nroDoc,idPrueba);
                if (!creoRegistro) {
                    throw new Exception("No se pudo asignar el departamento para el usuario");
                }

                listadoSitios = daoDivipol.getListDivipolPorDpto(dpto, idPrueba);
                for (Long sitio : listadoSitios) {
                    //Registra los sitios del usuario
                    creoRegistro = daoUsrSitio.insertar(sitio.longValue(), nroDoc);
                    if (!creoRegistro) {
                        throw new Exception("No se pudo asignar los sitios del nodo al usuario");
                    }
                }

            }
        }

        return true;
    }

}
