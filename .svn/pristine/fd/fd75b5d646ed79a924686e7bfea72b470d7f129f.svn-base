<%@page import="co.com.grupoasd.nomina.negocio.sitio.SitioMasivo"%>
<%
    SitioMasivo convocatoria = new SitioMasivo();
    int idStatusCargue = Integer.parseInt(request.getParameter("idStatus"));
    convocatoria.GeneraArchivoErrores(idStatusCargue);
    
    String filename = "ERR_"+ idStatusCargue+".csv";
            
    String filepath="/data/tmp/";
    response.setContentType("APPLICATION/OCTET-STREAM");
    response.setHeader("Content-Disposition","attachment; filename=\""+filename+"\"");
    java.io.FileInputStream fileInputStream=new java.io.FileInputStream(filepath+filename);
    int i;
    while((i=fileInputStream.read())!=-1)
    {
        out.write(i);
    }
    fileInputStream.close();
%>