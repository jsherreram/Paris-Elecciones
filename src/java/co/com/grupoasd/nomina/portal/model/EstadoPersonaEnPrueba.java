package co.com.grupoasd.nomina.portal.model;

import co.com.grupoasd.nomina.common.model.EstadoEnum;

/**
 * Modelo que representa los estados de persona en la prueba
 *
 * @author Pedro Rodríguez
 * Control de cambios: 
 * 2016-02-07: Creacion de la clase
 */
public class EstadoPersonaEnPrueba {
    
    private static String estados = null;
    
    public static String getEstados()  {
        if(estados == null) {
            StringBuilder json = new StringBuilder("{");
            json.append(String.format("'%s': '%s',", EstadoEnum.ASIGNADA.getCodigo(), EstadoEnum.ASIGNADA.getCodigo()));            
            json.append(String.format("'%s': '%s',", EstadoEnum.CONFIRMADA.getCodigo(), EstadoEnum.CONFIRMADA.getCodigo()));
            json.append(String.format("'%s': '%s',", EstadoEnum.CONVOCADA.getCodigo(), EstadoEnum.CONVOCADA.getCodigo()));
            json.append(String.format("'%s': '%s',", EstadoEnum.LIQUIDADO.getCodigo(), EstadoEnum.LIQUIDADO.getCodigo()));
            json.append(String.format("'%s': '%s',", EstadoEnum.LIQUIDADO_CONFIRMADO.getCodigo(), EstadoEnum.LIQUIDADO_CONFIRMADO.getCodigo()));
            json.append(String.format("'%s': '%s',", EstadoEnum.NO_PARTICIPA.getCodigo(), EstadoEnum.NO_PARTICIPA.getCodigo()));
            json.append(String.format("'%s': '%s',", EstadoEnum.PRE_INSCRITA.getCodigo(), EstadoEnum.PRE_INSCRITA.getCodigo()));
            json.append(String.format("'%s': '%s',", EstadoEnum.SELECCIONADA.getCodigo(), EstadoEnum.SELECCIONADA.getCodigo()));
            json.append(String.format("'%s': '%s',", EstadoEnum.VIATICOS_PAGADOS.getCodigo(), EstadoEnum.VIATICOS_PAGADOS.getCodigo()));
            json.append("}");
            estados = json.toString();
        }
        return estados;
    }
}
