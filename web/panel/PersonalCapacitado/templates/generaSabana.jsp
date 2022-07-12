<%-- 
    Document   : generaPlanoViaticos
    Created on : 17/03/2016, 09:05:40 AM
    Author     : Wilfer
--%>
<%@page import="co.com.grupoasd.nomina.dao.PersonaAsignadaDao"%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="co.com.grupoasd.nomina.dao.OrdenesReportesDao"%>

<%
        String nombre;
        String valor;
        String user = "";

        Cookie[] GalletaE = request.getCookies();
        for(int i=0; i<GalletaE.length; i++){
            nombre = GalletaE[i].getName();
            valor = GalletaE[i].getValue();
            if(nombre.equals("APP-TOKEN"))
            {
                user = valor;
            }                    
        }
        if(!user.equals("")) {
            co.com.grupoasd.nomina.dao.PersonaAsignadaDao reg  =   new PersonaAsignadaDao();
            int    idPrueba = Integer.parseInt(request.getParameter("idPrueba"));
            int    usuario  = Integer.parseInt(request.getParameter("usuario"));

            String filename = "Seguimiento_Personal_Capacitacion_" + idPrueba + ".csv";
            
            reg.seguimientoPersonal_Capacitacion(usuario,idPrueba);
            
            
            String filepath="/data/";
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition","attachment; filename=\""+filename+"\"");
            java.io.FileInputStream fileInputStream=new java.io.FileInputStream(filepath+filename);
            int i;
            while((i=fileInputStream.read())!=-1)
            {
                out.write(i);
            }
            fileInputStream.close();
        }
%>

