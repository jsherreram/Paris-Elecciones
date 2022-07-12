<%@page import="co.com.grupoasd.nomina.dao.GeneraSolicitudesDao"%>
<%
    
    co.com.grupoasd.nomina.dao.GeneraSolicitudesDao g = new GeneraSolicitudesDao();
    
    String filename = g.GenerarListas("09", "001", "0001-0025", "0001-0025", "5", 1);
    String filepath="C:/LOGIWEB/OUTPUT/";
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