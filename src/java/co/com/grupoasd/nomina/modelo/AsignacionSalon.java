/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

/**
 *
 * @author ASD
 */
public class AsignacionSalon {
    
    private int id;
    private Prueba prueba;
    private Empleado empleado;
    private Evento evento;
    private String salon;
    private int sitio;

    public AsignacionSalon() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

      
    
    public Prueba getPrueba() {
        return prueba;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public int getSitio() {
        return sitio;
    }

    public void setSitio(int sitio) {
        this.sitio = sitio;
    }
    
    
          
    
}
