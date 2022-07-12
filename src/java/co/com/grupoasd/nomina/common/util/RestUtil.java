package co.com.grupoasd.nomina.common.util;

import co.com.grupoasd.nomina.common.model.HttpResponse;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import org.apache.commons.lang3.StringUtils;

/**
 * TODO jcastellanos Optimizar para usar pool y revisar bien el manejo de los streams ya que se pueden
 * generar fugas de recursos 
 * @author jccastellanos 2015-04-13
 */
public class RestUtil {

    private final int timeout;
    private final boolean hasProxy;
    private final String proxyDireccion;
    private final int proxyPuerto;

    private RestUtil(int timeout) {
        this.timeout = timeout;
        this.hasProxy = false;
        this.proxyDireccion = "";
        this.proxyPuerto = 0;
    }

    private RestUtil(int timeout, String proxyDireccion, int proxyPuerto) {
        this.timeout = timeout;
        this.hasProxy = true;
        this.proxyDireccion = proxyDireccion;
        this.proxyPuerto = proxyPuerto;
    }

    public static RestUtil getInstance(int timeout) {
        return new RestUtil(timeout);
    }

    public static RestUtil getInstance(int timeout, String proxyDireccion, int proxyPuerto) {
        return new RestUtil(timeout, proxyDireccion, proxyPuerto);
    }

    /**
     * Realizar una peticion GET a la URL
     * @param url URL
     * @return HttpResponse
     * @throws IOException Si se presenta algun error al realizar la peticion
     */
    public HttpResponse get(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = null;
        try {
            if (this.hasProxy) {
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.proxyDireccion, Integer.valueOf(this.proxyPuerto)));
                con = (HttpURLConnection) obj.openConnection(proxy);
            } else {
                con = (HttpURLConnection) obj.openConnection();
            }
            con.setRequestMethod("GET");
            con.setConnectTimeout(this.timeout);
            con.setReadTimeout(this.timeout);
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:10.0) Gecko/20100101 Firefox/10.0");
            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                StringBuilder response;
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }
                return new HttpResponse(responseCode, response.toString());                
            } else {
                return new HttpResponse(responseCode);                
            }
        } finally {
            if (con != null) {
                // @jcastellanos: Segun guias de Internet es recomendable no desconectar la conexion ya que Java puede reutilizar el socket
                // para realizar otras peticiones al mismo servidor (si el servidor al que se la hacen peticiones soporte Keep-alive)
                // Lo que se recomienda es cerrar el stream para que libere el socket y pueda ser reutilizado.
                // El try with resource que leel el stream de las lineas anteriores lo debio cerrar automaticamente, sin embargo se fuerza
                // el cierre del stream y se comentarea la linea que desconecta la conexion
                con.getInputStream().close();
                // con.disconnect();
                
            }
        }
    }

    /**
     * Permite hacer una peticion HTTP que retorna un flujo binario.
     * <strong>IMPORTANTE:</strong> El cliente de este metodo despues de utilizar el flujo lo debe cerrar. Si esto no lo realiza puede haber
     * una fuga de recursos.
     * @param url URL
     * @param cookies Cookies del usuario. Cada cadena debe tener la forma "cookieName=valor".
     * @return InputStream con el binario
     * @throws IOException Si la peticion responder con un codigo diferente de 200 o si no responde con un binario
     */
    public InputStream getBinary(String url, String[] cookies) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = null;
        try {
            if (this.hasProxy) {
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.proxyDireccion, Integer.valueOf(this.proxyPuerto)));
                con = (HttpURLConnection) obj.openConnection(proxy);
            } else {
                con = (HttpURLConnection) obj.openConnection();
            }
            con.setRequestMethod("GET");
            con.setConnectTimeout(this.timeout);
            con.setReadTimeout(this.timeout);
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:10.0) Gecko/20100101 Firefox/10.0");
            if(cookies != null && cookies.length > 0) {
                String cookie = StringUtils.join(cookies, ";");
                con.setRequestProperty("Cookie", cookie);
            }
            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                if (con.getContentType() == null || con.getContentType().startsWith("text/")) {
                    con.getInputStream().close();
                    throw new IOException(String.format("El servicio web respondio con un contenido que no es binario. Tipo: %s.", con.getContentType()));
                }
                return con.getInputStream();                
            } else {
                con.getInputStream().close();
                throw new IOException("El servicio web respondio con el codigo http: " + responseCode);
            }
        } finally {
            if (con != null) {
                // @jcastellanos: Segun guias de Internet es recomendable no desconectar la conexion ya que Java puede reutilizar el socket
                // para realizar otras peticiones al mismo servidor (si el servidor al que se la hacen peticiones soporte Keep-alive)
                // Lo que se recomienda es cerrar el stream para que libere el socket y pueda ser reutilizado.
                // Se comentarea la linea que desconecta la conexion
                // con.disconnect();
            }
        }
    }

    public String post(String url, String body, String mediaType) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = null;
        try {
            if (this.hasProxy) {
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.proxyDireccion, Integer.valueOf(this.proxyPuerto)));
                con = (HttpURLConnection) obj.openConnection(proxy);
            } else {
                con = (HttpURLConnection) obj.openConnection();
            }
            con.setRequestMethod("POST");
            con.setConnectTimeout(this.timeout);
            con.setReadTimeout(this.timeout);
            con.setRequestProperty("Content-Type", mediaType);
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:10.0) Gecko/20100101 Firefox/10.0");
            if (body != null) {
                con.setDoOutput(true);
                try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                    wr.writeBytes(body);
                    wr.flush();
                }
            }
            int responseCode = con.getResponseCode();
            if (responseCode == 200 || responseCode == 204) {
                StringBuilder response;
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }
                return response.toString();
            } else {
                throw new IOException("El servicio web respondio con el codigo http: " + responseCode);
            }
        } finally {
            if (con != null) {
                // @jcastellanos: Segun guias de Internet es recomendable no desconectar la conexion ya que Java puede reutilizar el socket
                // para realizar otras peticiones al mismo servidor (si el servidor al que se la hacen peticiones soporte Keep-alive)
                // Lo que se recomienda es cerrar el stream para que libere el socket y pueda ser reutilizado.
                // El try with resource que leel el stream de las lineas anteriores lo debio cerrar automaticamente, sin embargo se fuerza
                // el cierre del stream y se comentarea la linea que desconecta la conexion
                con.getInputStream().close();
                // con.disconnect();
            }
        }
    }

}
