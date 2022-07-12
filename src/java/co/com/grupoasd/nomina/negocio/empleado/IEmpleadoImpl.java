/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.empleado;

import co.com.grupoasd.nomina.common.model.Foto;
import co.com.grupoasd.nomina.dao.EmpleadoDao;
import co.com.grupoasd.nomina.dao.UsuarioDAO;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.EmpleadoSesion;
import co.com.grupoasd.nomina.modelo.Usuario;
import co.com.grupoasd.nomina.modelo.UsuarioGrupo;
import co.com.grupoasd.nomina.modelo.UsuarioSitio;
import co.com.grupoasd.nomina.negocio.usuario.UsuarioController;
import co.com.grupoasd.nomina.negocio.usuariogrupo.UsuarioGrupoController;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;

public class IEmpleadoImpl implements IEmpleado {

    private EmpleadoDao dao = new EmpleadoDao();
    /**
     * Constantes para la ubicacion del directorio de imagenes
     */
    private static final String PREFIX_LOCATION_PHOTOS = "/data/Icfes/img/perfilProfesional/";
    private static final String PREFIX_FOLDER_EMPLEADO = "empleado_";

    @Override
    public EmpleadoSesion getEmpleadoSesion(String cedula) {

        EmpleadoSesion empleado = new EmpleadoSesion();
        empleado = dao.obtenerEmpleadoSesion(cedula);
        return empleado;
    }

    public boolean anadirHuella(int identifiacion, byte[] huella, byte[] imagen) {

        return dao.actualizarHuella(identifiacion, huella, imagen);
    }

    public List<Empleado> getUsuariosYHuellas(int idEvento, int idDivipol) {
        return dao.GetHuellasByEventoYDivipol(idEvento, idDivipol);
    }

    public List<Empleado> getUsuariosYHuellasSitios(int idEvento, List<UsuarioSitio> usuarioSitios) {

        List<Empleado> empleados = new ArrayList();

        for (UsuarioSitio usuarioSitio : usuarioSitios) {
            List<Empleado> empleadosSitio = dao.GetHuellasByEventoYDivipol(idEvento, usuarioSitio.getIdDivipol());

            for (Empleado empleado : empleadosSitio) {

                empleados.add(empleado);
            }
        }

        return empleados;

    }

    @Override
    public int Insertar(Empleado e) {
        int result;
        result = dao.insertar(e);
        if (result > 0) {
            //insertar el empleado
            e.setIdEmpleado(result);

            //crearle usuario del sistema y enviar email 
            UsuarioController usuarioController = new UsuarioController();

            try {
                usuarioController.establecerContraseñaUsuarioNuevo(Integer.toString(e.getIdEmpleado()), Boolean.TRUE);

                //Asignarle permisos de operador y coordinador
                UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
                usuarioGrupo.setGrupo("operador");
                Usuario usuario = new Usuario();
                usuario.setCorreo(Long.toString(e.getNrodoc()));
                usuarioGrupo.setUsuario(usuario);

                List<UsuarioGrupo> usuarioGrupos = new ArrayList<>();
                usuarioGrupos.add(usuarioGrupo);

                usuarioGrupo = new UsuarioGrupo();
                usuarioGrupo.setGrupo("examinador");
                usuario = new Usuario();
                usuario.setCorreo(Long.toString(e.getNrodoc()));
                usuarioGrupo.setUsuario(usuario);
                usuarioGrupos.add(usuarioGrupo);

                UsuarioGrupoController usuarioGrupoController = new UsuarioGrupoController();
                usuarioGrupoController.asignarGrupo(usuarioGrupos);

            } catch (Exception ex) {
                Logger.getLogger(IEmpleadoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public int InsertarDatosPrincipalesRDS(Empleado e) {
        int result;
        result = dao.insertarDatosPrincipalesRDS(e);
        if (result > 0) {
            //insertar el empleado
            e.setIdEmpleado(result);

            //crearle usuario del sistema y enviar email 
            UsuarioController usuarioController = new UsuarioController();

            try {
                usuarioController.establecerContraseñaUsuarioNuevo(Integer.toString(e.getIdEmpleado()), Boolean.TRUE);

                //Asignarle permisos de operador y examinador
                UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
                usuarioGrupo.setGrupo("operador");
                Usuario usuario = new Usuario();
                usuario.setCorreo(Long.toString(e.getNrodoc()));
                usuarioGrupo.setUsuario(usuario);

                List<UsuarioGrupo> usuarioGrupos = new ArrayList<>();
                usuarioGrupos.add(usuarioGrupo);

                usuarioGrupo = new UsuarioGrupo();
                usuarioGrupo.setGrupo("examinador");
                usuario = new Usuario();
                usuario.setCorreo(Long.toString(e.getNrodoc()));
                usuarioGrupo.setUsuario(usuario);
                usuarioGrupos.add(usuarioGrupo);

                UsuarioGrupoController usuarioGrupoController = new UsuarioGrupoController();
                usuarioGrupoController.asignarGrupo(usuarioGrupos);

            } catch (Exception ex) {
                Logger.getLogger(IEmpleadoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public int InsertarDatosBasicos(Empleado e) {
        int result;
        result = dao.insertarDatosBasicos(e);
        if (result > 0) {
            //insertar el empleado
            e.setIdEmpleado(result);

            //crearle usuario del sistema y enviar email 
            UsuarioController usuarioController = new UsuarioController();

            try {
                usuarioController.establecerContraseñaUsuarioNuevo(Integer.toString(e.getIdEmpleado()), Boolean.TRUE);

                //Asignarle permisos de operador y examinador
                UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
                usuarioGrupo.setGrupo("operador");
                Usuario usuario = new Usuario();
                usuario.setCorreo(Long.toString(e.getNrodoc()));
                usuarioGrupo.setUsuario(usuario);

                List<UsuarioGrupo> usuarioGrupos = new ArrayList<>();
                usuarioGrupos.add(usuarioGrupo);

                usuarioGrupo = new UsuarioGrupo();
                usuarioGrupo.setGrupo("examinador");
                usuario = new Usuario();
                usuario.setCorreo(Long.toString(e.getNrodoc()));
                usuarioGrupo.setUsuario(usuario);
                usuarioGrupos.add(usuarioGrupo);

                UsuarioGrupoController usuarioGrupoController = new UsuarioGrupoController();
                usuarioGrupoController.asignarGrupo(usuarioGrupos);

            } catch (Exception ex) {
                Logger.getLogger(IEmpleadoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public Boolean Actualizar(Empleado e) {
        boolean result;
        result = dao.actualizar(e);
        return result;
    }

    public Boolean actualizarDatosPrincipalesRDS(Empleado e) {
        boolean result;
        result = dao.actualizarDatosPrincipalesRDS(e);
        return result;
    }

    public Boolean actualizarDatosPrincipalesPistoleo(Empleado e) {
        boolean result;
        result = dao.actualizarDatosPrincipalesPistoleo(e);
        return result;
    }
    
    public Empleado getHuellaEmpleadoById(int id) {
        return dao.getHuellaEmpleadoById(id);
    }

    /**
     * Metodo encargado de obtener las fotos del sitio que estan almacenadas en
     * el servidor
     *
     * @param idEmpleado
     * @param folderFotos
     * @return
     */
    public List<Foto> obtenerFotos(Integer idEmpleado, String folderFotos) {
        List<Foto> listaFotos = new ArrayList<>();
        String ubicacion = obtenerUbicacionFotos(idEmpleado, folderFotos);
        File folder = new File(ubicacion);
        if (folder.exists()) {
            File[] listFiles = folder.listFiles();
            for (File listFile : listFiles) {
                Foto foto = new Foto();
                foto.setPath(PREFIX_FOLDER_EMPLEADO + idEmpleado + "/" + folderFotos);
                foto.setName(listFile.getName());
                foto.setWeight(String.valueOf(listFile.getTotalSpace()));
                listaFotos.add(foto);
            }
        }
        return listaFotos;
    }

    /**
     * Metodo para obtener el directorio de las fotos
     *
     * @param idEmpleado
     * @param folder
     * @return
     */
    private String obtenerUbicacionFotos(Integer idEmpleado, String subFolderFotos) {
        StringBuilder builder = new StringBuilder(PREFIX_LOCATION_PHOTOS);
        builder.append(PREFIX_FOLDER_EMPLEADO);
        builder.append(idEmpleado);
        builder.append("/");
        builder.append(subFolderFotos);
        return builder.toString();
    }

    /**
     * Author: John Steak Herrera Moreno
     * Date: 29/06/2022
     * 
     * @param empleado
     * @return
     * @throws Exception 
     */
    public boolean actualizarContrasena(Empleado empleado) throws Exception {

        String nuevaPassword = empleado.getClave();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        Boolean respuesta;
        md.update(nuevaPassword.getBytes());
        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        empleado.setClave(sb.toString());
        respuesta = new EmpleadoDao().actualizarContrasena(empleado);
        return respuesta;
    }

}
