package co.com.grupoasd.nomina.modelo;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class CarnesDatasource implements JRDataSource
{
    private List<CarneForma> listaParticipantes = new ArrayList<CarneForma>();
    private int indiceParticipanteActual = -1;
    
    public Object getFieldValue(JRField jrField) throws JRException
    {
       Object valor = null;  

        if("nombres".equals(jrField.getName())) 
        { 
            valor = listaParticipantes.get(indiceParticipanteActual).getNombres(); 
        } 
        else if("cargo".equals(jrField.getName())) 
        { 
            valor = listaParticipantes.get(indiceParticipanteActual).getCargo(); 
        } 
        else if("ubicacion".equals(jrField.getName())) 
        { 
            valor = listaParticipantes.get(indiceParticipanteActual).getUbicacion(); 
        } 
        else if("nombreDepartamento".equals(jrField.getName())) 
        { 
            valor = listaParticipantes.get(indiceParticipanteActual).getNombreDepartamento(); 
        } 
        else if("nrodoc".equals(jrField.getName())) 
        { 
            valor = listaParticipantes.get(indiceParticipanteActual).getNrodoc(); 
        }

        return valor; 
    }

    public boolean next() throws JRException
    {
        return ++indiceParticipanteActual < listaParticipantes.size();
    }
    
    public void addParticipante(CarneForma participante)
    {
        this.listaParticipantes.add(participante);
    }    
}