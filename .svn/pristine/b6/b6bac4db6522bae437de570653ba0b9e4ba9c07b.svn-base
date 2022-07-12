<%@page import="co.com.grupoasd.nomina.negocio.carguepagos.Pagos"%>
<%
    Pagos pagos = new Pagos();
    int idStatusCargue = Integer.parseInt(request.getParameter("idStatus"));
    pagos.generaArchivoErrores(idStatusCargue);
    
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