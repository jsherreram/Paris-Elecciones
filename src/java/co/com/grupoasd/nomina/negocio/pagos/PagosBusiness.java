/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.pagos;

import co.com.grupoasd.nomina.dao.PagoDao;
import co.com.grupoasd.nomina.modelo.Pago;
import co.com.grupoasd.nomina.modelo.PagoEvento;
import co.com.grupoasd.nomina.modelo.RegistroEvento;
import static co.com.grupoasd.nomina.negocio.Divitrans.DivitransBussines.COLUMN_SEPARATOR;
import static co.com.grupoasd.nomina.negocio.Divitrans.DivitransBussines.QUOTE_CHARACTER;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASD
 */
public class PagosBusiness implements IPagosBusiness {

    /**
     * Constantes para la ubicacion del directorio de Salida
     */
    private static final String PREFIX_LOCATION_TMP = "/data/tmp/";
    public static final String LINE_FEED = "\n";
    public static final String COLUMN_SEPARATOR = ";";
    public static final String QUOTE_CHARACTER = "\"";

    

    @Override
    public File generaArchivoPagos(String codigoDepartamento, String nombreDepartamento, int idPrueba) {

        PagoDao pagoDao = new PagoDao();
        
        int baseRetefuente =  152000;
        double porcentajeReteIca = 0.00966;
        
        List<PagoEvento> pagoEventos = pagoDao.getEventos(codigoDepartamento, idPrueba);
        List<Pago> pagos =  pagoDao.generarArchivo(codigoDepartamento, idPrueba);

        
        File file = null;
        try {
            String nombreArchivo = PREFIX_LOCATION_TMP+ codigoDepartamento+"-"+ nombreDepartamento +".csv";
            file = new File(nombreArchivo);
            
            StringBuilder sbEncabezadoEventos = new StringBuilder();
            StringBuilder sbEncabezado = new StringBuilder();
            StringBuilder sbLinea = new StringBuilder();

            
            sbEncabezadoEventos.append(";;;;;;;;;;;;;;;");
            for(PagoEvento even: pagoEventos ){
                sbEncabezadoEventos.append( even.getNombre() );
                sbEncabezadoEventos.append(";");
                sbEncabezadoEventos.append( even.getFecha());
                sbEncabezadoEventos.append(";");
                sbEncabezadoEventos.append(";");
            }
            sbEncabezadoEventos.append(";;;;");
            sbEncabezadoEventos.append("\n");

            
            sbEncabezado.append("Departamento;Municipio;Zona;Puesto;Ubiacion;IdCuenta;UltimoCargo;TipoIden;Identificacion;Apellido1;Apellido2;Nombre1;Nombre2;");
            sbEncabezado.append("Celular;Email;");
            
            for(PagoEvento even: pagoEventos ){
                sbEncabezado.append( "Cargo;Valor;Asistencia;" );
            }
            sbEncabezado.append("subtotal;retefuente;reteica;ajuste;totalPagar");
            sbEncabezado.append("\n");

            int subtotal;
            int retefuente;
            int reteica;
            int ajuste;
            int totalPagar;
            int residuo;
            
            for (Pago pag : pagos) {
                
                subtotal = 0;
                retefuente = 0;
                reteica = 0;
                ajuste = 0;
                totalPagar = 0;
                residuo = 0;

                sbLinea.append(QUOTE_CHARACTER).append(pag.getNombreDepartamento()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
                sbLinea.append(QUOTE_CHARACTER).append(pag.getNombreMunicipio()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
                sbLinea.append(QUOTE_CHARACTER).append(pag.getUltimoZona()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
                sbLinea.append(QUOTE_CHARACTER).append(pag.getUltimoPuesto()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
                sbLinea.append(QUOTE_CHARACTER).append(pag.getUltimoUbicacion()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
                sbLinea.append(QUOTE_CHARACTER).append(pag.getIdCuenta()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
                sbLinea.append(QUOTE_CHARACTER).append(pag.getUltimoCargo()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
                sbLinea.append(QUOTE_CHARACTER).append(pag.getTipoIdentificacion()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
                sbLinea.append(QUOTE_CHARACTER).append(pag.getNrodoc()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
                sbLinea.append(QUOTE_CHARACTER).append(pag.getApellido1()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
                sbLinea.append(QUOTE_CHARACTER).append(pag.getApellido2()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
                sbLinea.append(QUOTE_CHARACTER).append(pag.getNombre1()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
                sbLinea.append(QUOTE_CHARACTER).append(pag.getNombre2()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
                sbLinea.append(QUOTE_CHARACTER).append(pag.getCelular()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
                sbLinea.append(QUOTE_CHARACTER).append(pag.getEmail()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
                
                for(PagoEvento pe: pagoEventos)
                {
                    RegistroEvento reTmp = new RegistroEvento();
                    for(RegistroEvento regE : pag.getRegistrosEvento())
                    {
                        if (pe.getCodigoEvento() == regE.getCodigoEvento())
                        {
                            reTmp = regE;
                            subtotal = subtotal + regE.getValor();
                            break;
                        }
                    }
                
                    sbLinea.append(QUOTE_CHARACTER).append(reTmp.getNombreCargo()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
                    sbLinea.append(QUOTE_CHARACTER).append(reTmp.getValor()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
                    sbLinea.append(QUOTE_CHARACTER).append(reTmp.getFormaTomaAsistencia()).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
                }
                
                
                if (subtotal > baseRetefuente){
                    retefuente = Math.round(subtotal * 6 / 100) ;
                }
                
                if (
                        pag.getUltimoPuesto().substring(0,2).equals("16") || 
                        pag.getCodigoMunicipio().equals("15001")  || 
                        pag.getUltimoPuesto().equals("070010000")
                        )
                        {
                            reteica = Math.round((int)( subtotal * porcentajeReteIca ));
                }
                
                residuo = ((subtotal - retefuente - reteica) % 50 );
                
//                if (residuo < 25 )
//                {
//                    ajuste = residuo * -1;
//                }
//
//                if (residuo >= 25 )
//                {
//                    ajuste = ( 50 - residuo);
//                }
                
                totalPagar = subtotal - retefuente - reteica + ajuste;
                
                sbLinea.append(QUOTE_CHARACTER).append(subtotal).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
                sbLinea.append(QUOTE_CHARACTER).append(retefuente).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
                sbLinea.append(QUOTE_CHARACTER).append(reteica).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
                sbLinea.append(QUOTE_CHARACTER).append(ajuste).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
                sbLinea.append(QUOTE_CHARACTER).append(totalPagar).append(QUOTE_CHARACTER).append(COLUMN_SEPARATOR);
                
                sbLinea.append(System.lineSeparator());
            } 

            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "ISO-8859-1"))) {
                writer.write(sbEncabezadoEventos.toString());
                writer.write(sbEncabezado.toString());
                writer.write(sbLinea.toString());
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(PagosBusiness.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return file;
    }




}
