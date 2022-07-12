/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.common.util;

/**
 *
 * @author ASD
 */
public final class Constants {

    /**
     * Constantes para errores de procesamiento de csv
     */
    public static final int ERROR_INVALID_TYPE = 1;
    public static final int ERROR_INVALID_LENGHT = 2;
    public static final int ERROR_INVALID_EMPLOYEE = 3;
    public static final int ERROR_INVALID_DIVITRANS = 4;
    public static final int ERROR_INVALID_TOTAL_VALUE = 5;
    public static final int ERROR_INVALID_PAY_VALUE = 6;

    public static final String DESC_ERROR_DESCONOCIDO = "Ha ocurrido un error en el sistema";
    /**
     * Constantes para mensajes retornados a la vista de nombramiento
     */
    public static final String DESC_USUARIO_NO_EXISTE = "Usuario no existe";
    public static final String DESC_USUARIO_NODO = "Usuario no esta asignado en el nodo correspondiente";
    public static final String DESC_USUARIO_YA_ASIGNADO = "Usuario ya esta asignado";
    public static final String DESC_USUARIO_NO_PREINSCRITO = "Usuario NO se encuentra en estado Preinscrito!";
    /**
     * Constantes para mensaje retornado a la vista de Nombramiento Codigo
     * Barras
     */
    public static final String DESC_EVENTO_YA_ASIGNADO = "Ya existe un empleado asignado para el id ";
    public static final String DESC_USUARIO_YA_ASIGNADO_EVENTO = "El usuario ya esta asignado al evento para el sitio ";
    public static final String DESC_NIVEL_CARGO_INVALIDO = "El nivel de cargo del usuario a asignar no es el mismo del cargo evento ";

    /**
     * Enum para los codigos y descripciones de error retornados a la
     * presentacion
     */
    public static enum Errors {
        ERROR_INTERNO(500, DESC_ERROR_DESCONOCIDO),
        ERROR_USUARIO_NO_EXISTE(400, DESC_USUARIO_NO_EXISTE),
        ERROR_USUARIO_NODO(401, DESC_USUARIO_NODO),
        ERROR_USUARIO_YA_ASIGNADO(402, DESC_USUARIO_YA_ASIGNADO),
        ERROR_USUARIO_NO_PREINSCRITO(403, DESC_USUARIO_NO_PREINSCRITO),
        ERROR_EVENTO_YA_ASIGNADO(404, DESC_EVENTO_YA_ASIGNADO),
        ERROR_USUARIO_YA_ASIGNADO_EVENTO(405, DESC_USUARIO_YA_ASIGNADO_EVENTO),
        ERROR_NIVEL_CARGO_INVALIDO(406, DESC_NIVEL_CARGO_INVALIDO);

        private final int code;
        private final String description;

        private Errors(int code, String description) {
            this.code = code;
            this.description = description;
        }

        /**
         * @return the code
         */
        public int getCode() {
            return code;
        }

        /**
         * @return the description
         */
        public String getDescription() {
            return description;
        }

    }

}
