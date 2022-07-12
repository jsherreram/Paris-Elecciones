/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.personaAsignada;

import co.com.grupoasd.nomina.dao.PersonaAsignadaDao;
import co.com.grupoasd.nomina.modelo.PersonaAsignada;
import co.com.grupoasd.nomina.negocio.nombramientomasivo.NombramientoController;
import java.util.List;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author ASD
 */
public class PersonaAsignadaBusiness {

    private PersonaAsignadaDao dao = new PersonaAsignadaDao();

    public PersonaAsignadaBusiness() {
    }

    //Valida si hay nombrados titulares y no tienen asistencia y si hay nombrados suplentes y si tienen asistencia
    public String validarPersonasTitularesySuplentesAsignados(int codigoEvento, String usuario) throws JSONException {

        JSONObject objJson = (JSONObject) new JSONObject();
        String resp = "false";

        List<PersonaAsignada> titularesSinAsistencia = dao.titularesAsignadosSinAsistencia(codigoEvento, usuario);

        if (titularesSinAsistencia.size() > 0) {
            for (PersonaAsignada titular : titularesSinAsistencia) {

                List<PersonaAsignada> suplentesConAsistencia = dao.suplentesAsignadosConAsistencia(codigoEvento, usuario, titular.getCargo().getNivel_cargo(), titular.getIddivipol());

                if (suplentesConAsistencia.size() > 0) {
                    resp = "true";
                }
            }
        }

        objJson.put("respuesta", resp);
        return objJson.toString();

    }

    //Valida si hay nombrados titulares y no tienen asistencia y si hay nombrados suplentes y si tienen asistencia
    public String buscarSuplentesConAsistenciaCapacitacion(int codigoEvento, int idPrueba, float latitud, float longitud) throws JSONException {

        JSONArray json = new JSONArray();

        JSONArray array = new PersonaAsignadaDao().buscarSuplentesConAsistenciaCapacitacion(codigoEvento, idPrueba);
        for (int i = 0; i < array.length(); i++) {
            JSONObject objJson = (JSONObject) new JSONObject();
            JSONObject row = array.getJSONObject(i);

            objJson.put("id", row.getInt("id"));
            objJson.put("nombres", row.getString("nombres"));
            objJson.put("apellidos", row.getString("apellidos"));
            objJson.put("nrodoc", row.getLong("nrodoc"));
            objJson.put("cargo", row.getString("descripcion"));
            objJson.put("salones", row.getInt("salones"));
            objJson.put("viaja", row.getString("viaja"));
            objJson.put("idEmpleado", row.getInt("idEmpleado"));
            objJson.put("distancia", NombramientoController.distanciaCoord(longitud, latitud, (float) row.getDouble("longitud"), (float) row.getDouble("latitud")));

            json.put(objJson);
        }

        return json.toString();

    }

    /**
     * Si aceptan asignar a una persona que viene de otro sitio en el PDS, se
     * desasigna del sitio de donde viene y se asigna en el PDS
     *
     * @param id
     * @param nrodoc
     * @return
     */
    public String asignarPersonaOtroSitioAPDS(int id, long nrodoc) throws JSONException {

        JSONObject objJson = (JSONObject) new JSONObject();
        String resp = "false";

        // Consulto la asignación de la persona a cambiar
        PersonaAsignada pa = dao.getById(id);

        // Consulto donde estaba asignada la persona Nueva
        PersonaAsignada nueva = dao.getPersonaAsignada(pa.getEvento().getCodigoEvento(), nrodoc);

        Long l = new Long(0);

        // Primero desasigno a la persona del sitio y evento donde estaba antes, colocandole el nrodoc=0
        if (dao.desasignarPersonaDcpe(nueva.getId())) {
            // Asigno a la persona 
            if (dao.actualizarPersonaCargo(id, nrodoc)) {
                resp = "true";
            }

        }

        objJson.put("respuesta", resp);
        return objJson.toString();

    }

    //desasigna a una persona de la dcpe
    public String desasignarPersonaSitio(int dcpe) throws JSONException {

        JSONObject objJson = (JSONObject) new JSONObject();
        String resp = "false";

        if (dao.desasignarPersonaDcpe(dcpe)) {
            resp = "true";
        }

        objJson.put("respuesta", resp);
        return objJson.toString();

    }

}
