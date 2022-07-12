<%
    String id = request.getParameter("id");
    String departamento = request.getParameter("idDpto");
    
    String filename= id + ".pdf";
    String filepath="/data/Elecciones/img/listas/"+ departamento +"/";
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