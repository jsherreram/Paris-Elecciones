<%
    String cedula = request.getParameter("idEmpleado");
    String archivo = request.getParameter("archivo");
    String tipoDoc = request.getParameter("tipoDocumental");
    
    String filename= cedula + ".pdf";
    String filepath="/data/Elecciones/img/"+ tipoDoc + "/";
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition","inline; filename=\""+archivo+"\"");
    java.io.FileInputStream fileInputStream=new java.io.FileInputStream(filepath+archivo);
    int i;
    while((i=fileInputStream.read())!=-1)
    {
        out.write(i);
    }
    fileInputStream.close();
%>
