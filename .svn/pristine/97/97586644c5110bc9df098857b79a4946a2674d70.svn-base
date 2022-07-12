package co.com.grupoasd.nomina.rest;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 * Filtro que permite eliminar el cache
 * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com.co>
 * Control de cambios: 
 * 2016-07-30: Creacion de la clase
 */
@Provider
public class CacheFilter implements ContainerResponseFilter {

    
    
    @Override
    public void filter(final ContainerRequestContext requestContext,
            final ContainerResponseContext cres) throws IOException {
        // Se dehabilida el cache
        cres.getHeaders().add("Cache-Control", "no-cache, no-store, must-revalidate");
        cres.getHeaders().add("Pragma", "no-cache");
        cres.getHeaders().add("Expires", "0");
    }

}
