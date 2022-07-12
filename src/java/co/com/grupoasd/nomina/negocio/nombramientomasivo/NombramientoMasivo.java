/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.nombramientomasivo ;

import co.com.grupoasd.nomina.dao.CargoDao;
import co.com.grupoasd.nomina.dao.EmpleadoDao;
import co.com.grupoasd.nomina.dao.EventoDao;
import co.com.grupoasd.nomina.dao.NombramientoDao;
import co.com.grupoasd.nomina.dao.PruebaDao;
import co.com.grupoasd.nomina.dao.SitioDao;
import co.com.grupoasd.nomina.dao.StatusCargueDao;
import co.com.grupoasd.nomina.modelo.Cargo;
import co.com.grupoasd.nomina.modelo.Departamento;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.EmpleadoPruebaEstado;
import co.com.grupoasd.nomina.modelo.Estado;
import co.com.grupoasd.nomina.modelo.Evento;
import co.com.grupoasd.nomina.modelo.Municipio;
import co.com.grupoasd.nomina.modelo.Nombramiento;
import co.com.grupoasd.nomina.modelo.Prueba;
import co.com.grupoasd.nomina.modelo.Sitio;
import co.com.grupoasd.nomina.modelo.StatusCargue;
import co.com.grupoasd.nomina.modelo.Usuario;
import co.com.grupoasd.nomina.modelo.UsuarioGrupo;
import co.com.grupoasd.nomina.negocio.empleado.IEmpleadoImpl;
import co.com.grupoasd.nomina.negocio.statusCargue.StatusBusiness;
import co.com.grupoasd.nomina.negocio.usuario.UsuarioController;
import co.com.grupoasd.nomina.negocio.usuariogrupo.UsuarioGrupoController;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Rodríguez
 */
public class NombramientoMasivo {
    
    public NombramientoMasivo(){
        
    }
    
    public void cargar(String nombreArchivoOrigen, String pathArchivo, int codigoPrueba, String user){
            final String csvFile = pathArchivo;
            final String usuario = user;
            final int idPrueba = codigoPrueba;
            final String nombreArchivo = nombreArchivoOrigen;
            
            Thread hilo;
            hilo = new Thread() {
            @Override
            public void run(){
                    try {
                        int cantidadRegistroTotal=0;
                        int cantidadRegistrosOk=0;
                        int cantidadRegistrosErr=0;
                        
                        BufferedReader br = null;
                        String line = "";
                        String cvsSplitBy = ";";

                        Prueba prueba = new PruebaDao().getById(idPrueba);
                        String textoVtiger = "Fecha de Aplicación:"+ prueba.getFechaaplicacion()+"\n"; 
                        
                        //Contar las filas del archivo 
                        br = new BufferedReader(new FileReader(csvFile));
                        while ((line = br.readLine()) != null) {
                            cantidadRegistroTotal ++;
                        }
                        br.close();

                        //Agregar el registro en la tabla de status_cargue para hacer seguiiento del proceso
                        StatusBusiness statusBusiness = new StatusBusiness();
                        StatusCargue statusCargue = new StatusCargue();
                        statusCargue.setUsuario(usuario);
                        statusCargue.setIdtipoCargue(3); //tipo de cargue Nombramiento Masivo
                        statusCargue.setNombreArchivo(nombreArchivo);
                        statusCargue.setEstadoStatus(0); //estado: 0 en proceso, 1 procesado
                        statusCargue.setCantidadRegistrosTotal(cantidadRegistroTotal-1);
                        statusCargue.setCantidadRegistrosProcesadosOk(cantidadRegistrosOk);
                        statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                        int idStatus = statusBusiness.Insertar(statusCargue);
                        statusCargue.setId(idStatus);
                        
                        cantidadRegistroTotal =0;
                        br = new BufferedReader(new FileReader(csvFile));
                        EmpleadoDao empleadoDao = new EmpleadoDao();
                        CargoDao cargoDao = new CargoDao();
                        NombramientoDao nombramientoDao = new NombramientoDao();
                        EventoDao eventoDao = new EventoDao();
                        SitioDao sitioDao = new SitioDao();
                        StringBuilder sb =new StringBuilder();
                        int idEmpleado = 0;
                        
                        Evento evento =  eventoDao.GetEventoNombramiento(prueba);
                        
                        long nrodoc = 0;
                        String codigositio = "";
                        
                        //DESDE AQUI INICIAN LAS VALIDACIONES    
                        while ((line = br.readLine()) != null) {
                            try {
                                        if (cantidadRegistroTotal ==0){
                                            //Esta linea corresponde al titulo
                                            cantidadRegistroTotal ++;
                                        }else
                                        {
                                                String[] lineas = line.split(cvsSplitBy);
                                                cantidadRegistroTotal ++;

                                                //Validar la estructura
                                                if(lineas.length < 9)
                                                {
                                                    sb.append("Linea:"+cantidadRegistroTotal+ " - Número de columnas invalido.\n");
                                                    cantidadRegistrosErr ++;
                                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                                    statusBusiness.ActualizarAvance(statusCargue);
                                                    continue;
                                                }

                                                codigositio = lineas[0];

                                                if (codigositio.length() == 7)
                                                {
                                                    codigositio = "0"+codigositio;
                                                }

                                                //validar sitio
                                                if(!codigositio.matches("-?\\d+(\\.\\d+)?"))
                                                {
                                                    sb.append("Linea:"+cantidadRegistroTotal+ " - Codigo de sitio debe ser numérico\n");
                                                    cantidadRegistrosErr ++;
                                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                                    statusBusiness.ActualizarAvance(statusCargue);
                                                    continue;
                                                }

                                                Sitio sitio = sitioDao.GetSitio(codigositio, prueba);

                                                if ( sitio.getId() == 0 )
                                                {
                                                    sb.append("Linea:"+cantidadRegistroTotal+ " - Codigo de sitio no Existe\n");
                                                    cantidadRegistrosErr ++;
                                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                                    statusBusiness.ActualizarAvance(statusCargue);
                                                    continue;
                                                }

                                                if(!lineas[1].matches("-?\\d+(\\.\\d+)?"))
                                                {
                                                    sb.append("Linea:"+cantidadRegistroTotal+ " - Número de Identificación debe ser numérico\n");
                                                    cantidadRegistrosErr ++;
                                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                                    statusBusiness.ActualizarAvance(statusCargue);
                                                    continue;
                                                }
                                                
                                                  if(lineas[4].length()==0)
                                                {
                                                    sb.append("Linea:"+cantidadRegistroTotal+ " - Nombre no puede ser vacio\n");
                                                    cantidadRegistrosErr ++;
                                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                                    statusBusiness.ActualizarAvance(statusCargue);
                                                    continue;
                                                }
                                                  
                                                    if(lineas[2].length()==0)
                                                {
                                                    sb.append("Linea:"+cantidadRegistroTotal+ " - Apellido no puede ser vacio\n");
                                                    cantidadRegistrosErr ++;
                                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                                    statusBusiness.ActualizarAvance(statusCargue);
                                                    continue;
                                                }

                                                lineas[0] = lineas[0].trim();
                                                lineas[1] = lineas[1].trim();
                                                lineas[2] = lineas[2].trim();
                                                lineas[3] = lineas[3].trim();
                                                lineas[4] = lineas[4].trim();
                                                lineas[5] = lineas[5].trim();
                                                lineas[6] = lineas[6].trim();
                                                lineas[7] = lineas[7].trim();
                                                lineas[8] = lineas[8].trim();

                                                if(lineas[1].length() > 10)
                                                {
                                                    sb.append("Linea:"+cantidadRegistroTotal+ " - Número de Identificación tiene mas de 10 digitos, no permitido\n");
                                                    cantidadRegistrosErr ++;
                                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                                    statusBusiness.ActualizarAvance(statusCargue);
                                                    continue;
                                                }

                                                if(lineas[1].length() == 10 && !lineas[1].substring(0,1).equals("1"))
                                                {
                                                    sb.append("Linea:"+cantidadRegistroTotal+ " - Número de Identificación no valido\n");
                                                    cantidadRegistrosErr ++;
                                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                                    statusBusiness.ActualizarAvance(statusCargue);
                                                    continue;
                                                }


                                                if(!lineas[8].matches("-?\\d+(\\.\\d+)?"))
                                                {
                                                    sb.append("Linea:"+cantidadRegistroTotal+ " - Código de Cargo debe ser numérico\n");
                                                    cantidadRegistrosErr ++;
                                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                                    statusBusiness.ActualizarAvance(statusCargue);
                                                    continue;
                                                }

                                                Cargo cargo = cargoDao.GetById(lineas[8]);
                                                if(cargo.getCodigoCargo() == null ){
                                                    sb.append("Linea:"+cantidadRegistroTotal+ " - Código de Cargo no existe\n");
                                                    cantidadRegistrosErr ++;
                                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                                    statusBusiness.ActualizarAvance(statusCargue);
                                                    continue;
                                                }


                                                nrodoc = Long.parseLong(lineas[1]);

                                                Empleado empleadoNew = new Empleado();
                                                empleadoNew.setTipodoc("CC");
                                                empleadoNew.setNrodoc(nrodoc);
                                                empleadoNew.setApellido1(lineas[2]);
                                                empleadoNew.setApellido2(lineas[3]);
                                                empleadoNew.setNombre1(lineas[4]);
                                                empleadoNew.setNombre2(lineas[5]);
                                                empleadoNew.setDireccion("");
                                                empleadoNew.setCelular(lineas[6]);
                                                empleadoNew.setEmail(lineas[7]);
                                                empleadoNew.setIdpuesto(codigositio);
                                                Departamento departamento = new Departamento();
                                                departamento.setCodigo(sitio.getCodigoDepartamento());
                                                Municipio municipio = new Municipio();
                                                municipio.setCodigoMunicipio(sitio.getCodigoMunicipio());
                                                municipio.setDepartamento(departamento);
                                                empleadoNew.setMunicipio(municipio);


                                                Nombramiento validaNombramiento = nombramientoDao.getNombramiento(idPrueba, nrodoc);
                                                
                     
                                                if(validaNombramiento.getId()> 0)
                                                {
                                                        sb.append("Linea:"+cantidadRegistroTotal+ " - Persona ya tiene una asignación. Sitio:"+validaNombramiento.getPuesto()+" Cargo:" + validaNombramiento.getCargo().getDescripcion()+ " \n");
                                                        cantidadRegistrosErr ++;
                                                        statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                                        statusBusiness.ActualizarAvance(statusCargue);
                                                        continue;
                                                }

                                                //consultar idEmpleado con la cedula
                                                idEmpleado = empleadoDao.GetIdByNumeroDocumento(nrodoc);
                                                empleadoNew.setIdEmpleado(idEmpleado);

                                                if(idEmpleado == 0){
                                                   if (empleadoDao.insertarDatosPrincipales(empleadoNew) == 0){

                                                        sb.append("Linea:"+cantidadRegistroTotal+ " - No se pudo Agregar la persona \n");
                                                        cantidadRegistrosErr ++;
                                                        statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                                        statusBusiness.ActualizarAvance(statusCargue);
                                                        continue;
                                                   }else
                                                   {
                                                        //crearle usuario del sistema y enviar email 
                                                        UsuarioController usuarioController = new UsuarioController();

                                                        try {
                                                            usuarioController.establecerContraseñaUsuarioNuevo(Integer.toString(empleadoNew.getIdEmpleado()), Boolean.TRUE);

                                                            //Asignarle permisos de operador y examinador
                                                            UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
                                                            usuarioGrupo.setGrupo("operador");
                                                            Usuario usuario2 = new Usuario();
                                                            usuario2.setCorreo(Long.toString(empleadoNew.getNrodoc()));
                                                            usuarioGrupo.setUsuario(usuario2);

                                                            List<UsuarioGrupo> usuarioGrupos = new ArrayList<>();
                                                            usuarioGrupos.add(usuarioGrupo);

                                                            usuarioGrupo = new UsuarioGrupo();
                                                            usuarioGrupo.setGrupo("examinador");
                                                            usuario2 = new Usuario();
                                                            usuario2.setCorreo(Long.toString(empleadoNew.getNrodoc()));
                                                            usuarioGrupo.setUsuario(usuario2);
                                                            usuarioGrupos.add(usuarioGrupo);

                                                            UsuarioGrupoController usuarioGrupoController = new UsuarioGrupoController();
                                                            usuarioGrupoController.asignarGrupo(usuarioGrupos);

                                                        } catch (Exception ex) {
                                                            Logger.getLogger(NombramientoMasivo.class.getName()).log(Level.SEVERE, null, ex);
                                                            sb.append("Linea:"+cantidadRegistroTotal+ " - No se pudo Asignar los permisos de acceso al sistema. Consulte con el Administrador \n");
                                                            cantidadRegistrosErr ++;
                                                            statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                                            statusBusiness.ActualizarAvance(statusCargue);
                                                        }
                                                   }
                                                }else
                                                {
                                                    if (!empleadoDao.actualizarDatosPrincipales(empleadoNew))
                                                    {
                                                        sb.append("Linea:"+cantidadRegistroTotal+ " - No se pudo Actualizar la persona \n");
                                                        cantidadRegistrosErr ++;
                                                        statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                                        statusBusiness.ActualizarAvance(statusCargue);
                                                        continue;
                                                    }
                                                }

                                                //buscar el primer disponible para asignarlo
                                                int idNombramientoDisponible = nombramientoDao.getNombramientoDisponible(evento.getCodigoEvento(), cargo.getCodigoCargo(), codigositio);

                                                if (idNombramientoDisponible > 0){
                                                    Nombramiento nombramiento = new Nombramiento();
                                                    nombramiento.setId(idNombramientoDisponible);
                                                    Estado estado = new Estado();
                                                    estado.setCodigoEstado(1);
                                                    nombramiento.setEstado(estado);
                                                    nombramiento.setEmpleado(empleadoNew);
                                                    nombramiento.setUsuario(usuario);
                                                    if (!nombramientoDao.actualizar(nombramiento))
                                                    {
                                                        sb.append("Linea:"+cantidadRegistroTotal+ " - Eror al realizar el nombramiento. Consulte con el Administrador \n");
                                                        cantidadRegistrosErr ++;
                                                        statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                                        statusBusiness.ActualizarAvance(statusCargue);
                                                        continue;
                                                    }else
                                                    {
                                                        cantidadRegistrosOk ++;        
                                                        statusCargue.setCantidadRegistrosProcesadosOk(cantidadRegistrosOk);
                                                        statusBusiness.ActualizarAvance(statusCargue);
                                                    }
                                                }else
                                                {
                                                    //no existen vacantes disponibles para el nombramiento
                                                    sb.append("Linea:"+cantidadRegistroTotal+ " - no existen vacantes disponibles para el cargo\n");
                                                    cantidadRegistrosErr ++;
                                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                                    statusBusiness.ActualizarAvance(statusCargue);
                                                    continue;
                                                }
                                    }
                                } catch (IOException ex) {
                                    Logger.getLogger(NombramientoMasivo.class.getName()).log(Level.SEVERE, null, ex);
                                    sb.append("Linea:"+cantidadRegistroTotal+ " - Error: "+  ex.getMessage() +" \n");
                                    cantidadRegistrosErr ++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                } catch (Exception ex) {
                                    Logger.getLogger(NombramientoMasivo.class.getName()).log(Level.SEVERE, null, ex);
                                    sb.append("Linea:"+cantidadRegistroTotal+ " - Error: "+  ex.getMessage() +" \n");
                                    cantidadRegistrosErr ++;
                                    statusCargue.setCantidadRegistrosProcesadosError(cantidadRegistrosErr);
                                    statusBusiness.ActualizarAvance(statusCargue);
                                }
                        }
                        br.close();
                        statusBusiness.Finalizar(statusCargue, sb);
                        
                    } catch (IOException ex) {
                        Logger.getLogger(NombramientoMasivo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                    Logger.getLogger(NombramientoMasivo.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                }
            };

            hilo.start();
    }
 
    
}