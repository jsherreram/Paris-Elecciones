<%@page import="java.util.Random"%>
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
            co.com.grupoasd.nomina.dao.GeneraSolicitudesDao g = new GeneraSolicitudesDao();

            int idEvento = Integer.parseInt(request.getParameter("idEvento"));
            
            String nombreArchivoRand = "";
            Random rnd = new Random();
            
            char n;
            
            for (int i=0; i < 10 ; i++) { 
                n = (char)(rnd.nextDouble() * 26.0 + 65.0 ); 
                nombreArchivoRand += n; 
            }            

            String filename = "DETALLE_ENCUESTA_"+ nombreArchivoRand + ".csv";
            
            String fileNameExport = "/data/"+filename;
            
            g.GeneraDetalleEncuesta(idEvento, fileNameExport);
            
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