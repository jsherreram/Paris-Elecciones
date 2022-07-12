<%
    String id = request.getParameter("idPrueba");
    String sitio= request.getParameter("idSitio");
    
    String filename= sitio + ".pdf";
    String filepath="/data/Elecciones/img/listas/pagos/"+id+"/";
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