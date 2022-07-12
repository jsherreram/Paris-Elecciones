<%-- 
    Document   : generaPlanoViaticos
    Created on : 22/02/2016, 09:05:40 AM
    Author     : Wilfer
--%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="co.com.grupoasd.nomina.dao.GestionPagosDao"%>

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
            co.com.grupoasd.nomina.dao.GestionPagosDao reg  =   new GestionPagosDao();
            int idEmp = Integer.parseInt(request.getParameter("idEmp"));
            
            /*java.util.Calendar fecha = java.util.Calendar.getInstance();
            DateFormat formateador = DateFormat.getInstance();*/
            valor   =  "individual";
            if (idEmp == 0 ){
                valor   =  "todos";
            }
            String filename = "Plano_Viaticos_"+ valor + ".csv";
            
            reg.listarPlanoViaticos(idEmp, valor);
            
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

