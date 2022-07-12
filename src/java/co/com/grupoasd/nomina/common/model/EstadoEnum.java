package co.com.grupoasd.nomina.common.model;

/**
 * Enumeracion con los estados de la persona en la prueba
 * @author Pedro Rodríguez
 */
public enum EstadoEnum {
    ASIGNADA("ASIGNADA"),
    CONFIRMADA("CONFIRMADA"),    
    CONVOCADA("CONVOCADA"),
    LIQUIDADO("LIQUIDADO"),
    LIQUIDADO_CONFIRMADO("LIQUIDADO_CONFIRMADO"),
    NO_PARTICIPA("NO_PARTICIPA"),
    PRE_INSCRITA("PRE_INSCRITA"),
    SELECCIONADA("SELECCIONADA"),
    VIATICOS_PAGADOS("VIATICOS_PAGADOS");
    
    private final String codigo;        
    
    EstadoEnum(String codigo) {
        this.codigo = codigo;        
    }
    
    public String getCodigo() {
        return codigo;
    }
        
}
