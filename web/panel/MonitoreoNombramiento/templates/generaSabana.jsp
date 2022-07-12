<%-- 
    Document   : generaPlanoViaticos
    Created on : 17/03/2016, 09:05:40 AM
    Author     : Wilfer
--%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="co.com.grupoasd.nomina.dao.GeneraSolicitudesDao"%>

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
            co.com.grupoasd.nomina.dao.GeneraSolicitudesDao reg  =   new GeneraSolicitudesDao();
            String idDpto   = request.getParameter("idDpto");
            int    idprueba = Integer.parseInt(request.getParameter("idprueba"));

            String filename = "Estado_Nombramiento_Asistencia" + idDpto + ".csv";
            
            reg.GeneraEstadoNombramientoAsistencia(idDpto, idprueba);
            
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

