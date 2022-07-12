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
public class SqlUtil {

    public static final String SQL_AND = " AND ";
    public static final String EQUALS = " = ";
    public static final String GREATER_THAN = " > ";
    public static final String GREATER_OR_EQUALS_THAN = " >= ";
    public static final String LESS_THAN = " < ";
    public static final String LESS_OR_EQUALS_THAN = " <= ";
    public static final int LIKE_BEGIN = 1;
    public static final int LIKE_END = 2;
    public static final int LIKE = 3;
    public static final String STATEMENT_CONFIG_VALUE = " ? ";

    /**
     * Metodo para adicionar parametro a la consulta
     *
     * @param consulta
     * @param column
     * @param dato
     */
    public static void appendParameterWithAnd(StringBuilder consulta, String column, Object dato) {
        if (dato != null) {
            if (dato instanceof String && !String.valueOf(dato).isEmpty()) {
                consulta.append(SQL_AND).append(column).append(EQUALS).append(dato);
            } else if (dato instanceof Integer && ((Integer) dato) > 0) {
                consulta.append(SQL_AND).append(column).append(EQUALS).append(dato);
            } else if (dato instanceof Double && ((Double) dato > 0)) {
                consulta.append(SQL_AND).append(column).append(EQUALS).append(dato);
            } else if (dato instanceof Long && ((Long) dato > 0)) {
                consulta.append(SQL_AND).append(column).append(EQUALS).append(dato);
            }
        }
    }

    /**
     * Metodo para adicionar parametro a la consulta con interrogacion
     *
     * @param consulta
     * @param column
     */
    public static void appendParameterWithoutValue(StringBuilder consulta, String column) {
        if (column != null) {
            consulta.append(SQL_AND).append(column).append(EQUALS).append(STATEMENT_CONFIG_VALUE);
        }
    }

    /**
     * Metodo para adicionar parametro a la consulta con interrogacion
     *
     * @param consulta
     * @param column
     * @param dato
     * @param likeType
     */
    public static void appendParameterWithLike(StringBuilder consulta, String column, Object dato, int likeType) {
        if (dato != null) {
            String startSequence = likeType == LIKE || likeType == LIKE_BEGIN ? " LIKE '%" : "LIKE '";
            String endSequence = likeType == LIKE || likeType == LIKE_END ? "%'" : "'";
            if (dato instanceof String && !String.valueOf(dato).isEmpty()) {
                consulta.append(SQL_AND).append(column).append(startSequence).append(dato).append(endSequence);
            } else if (dato instanceof Integer && ((Integer) dato) > 0) {
                consulta.append(SQL_AND).append(column).append(startSequence).append(dato).append(endSequence);
            } else if (dato instanceof Double && ((Double) dato > 0)) {
                consulta.append(SQL_AND).append(column).append(startSequence).append(dato).append(endSequence);
            } else if (dato instanceof Long && ((Long) dato > 0)) {
                consulta.append(SQL_AND).append(column).append(startSequence).append(dato).append(endSequence);
            }
        }
    }

    /**
     * Metodo para adicionar parametro a la consulta
     *
     * @param consulta
     * @param column
     * @param dato
     * @param charComparison
     */
    public static void appendParameterWithAnd(StringBuilder consulta, String column, Object dato, String charComparison) {
        if (dato != null) {
            if (dato instanceof String && !String.valueOf(dato).isEmpty()) {
                consulta.append(SQL_AND).append(column).append(charComparison).append(dato);
            } else if (dato instanceof Integer && ((Integer) dato) > 0) {
                consulta.append(SQL_AND).append(column).append(charComparison).append(dato);
            } else if (dato instanceof Double && ((Double) dato > 0)) {
                consulta.append(SQL_AND).append(column).append(charComparison).append(dato);
            } else if (dato instanceof Long && ((Long) dato > 0)) {
                consulta.append(SQL_AND).append(column).append(charComparison).append(dato);
            }
        }
    }

    /**
     * Metodo para adicionar parametro al having
     *
     * @param having
     * @param column
     * @param dato
     * @param charComparison
     */
    public static void appendParameterHaving(StringBuilder having, String column, Object dato, String charComparison) {
        if (dato != null) {
            String prechar;
            if (having.toString().toLowerCase().contains("having")) {
                prechar = SQL_AND;
            } else {
                prechar = " having ";
            }
            if (dato instanceof String && !String.valueOf(dato).isEmpty()) {
                having.append(prechar).append(column).append(charComparison).append(dato);
            } else if (dato instanceof Integer && ((Integer) dato) > 0) {
                having.append(prechar).append(column).append(charComparison).append(dato);
            } else if (dato instanceof Double && ((Double) dato > 0)) {
                having.append(prechar).append(column).append(charComparison).append(dato);
            } else if (dato instanceof Long && ((Long) dato > 0)) {
                having.append(prechar).append(column).append(charComparison).append(dato);
            }
        }
    }

}
