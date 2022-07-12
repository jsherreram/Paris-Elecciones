package co.com.grupoasd.nomina.common.model;

/**
 * Modelo de datos que representa una respuesta HTTP
 * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com.co>
 * Control de cambios:
 * 2015-07-02: jccastellanos. Creacion
 */
public class HttpResponse {
    
    public static final int STATUS_OK = 200;
    public static final int STATUS_NO_CONTENT = 204;
    
    // Codigo de respueta (200, 400, 500)
    private int statusCode;
    // Contenido de la respuesta
    private String data;

    public HttpResponse(int statusCode) {
        this(statusCode, "");
    }

    public HttpResponse(int statusCode, String data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getData() {
        return data;
    }
        
}
