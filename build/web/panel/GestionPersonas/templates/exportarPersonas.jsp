<%@page import="java.util.Random"%>
<%@page import="co.com.grupoasd.nomina.dao.GeneraSolicitudesDao"%>
<%
           String dpto= request.getParameter("dpto");
     
           co.com.grupoasd.nomina.dao.GeneraSolicitudesDao g = new GeneraSolicitudesDao();

            
            String nombreArchivoRand = "";
            Random rnd = new Random();
            
            char n;
            
            for (int i=0; i < 10 ; i++) { 
                n = (char)(rnd.nextDouble() * 26.0 + 65.0 ); 
                nombreArchivoRand += n; 
            }            

            String filename = "LISTADO_PERSONAS_"+ nombreArchivoRand + ".csv";
            
            String fileNameExport = "/data/"+filename;
            
            g.listarEmpleados(fileNameExport, dpto);
            
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
        
    
    
%>