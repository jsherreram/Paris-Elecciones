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

            int idPrueba = Integer.parseInt(request.getParameter("idPrueba"));
            String idDpto = request.getParameter("idDpto");
            
            if(idDpto.equals(""))
            {
                idDpto = "X";
            }
            
            String nombreArchivoRand = "";
            Random rnd = new Random();
            
            char n;
            
            for (int i=0; i < 10 ; i++) { 
                n = (char)(rnd.nextDouble() * 26.0 + 65.0 ); 
                nombreArchivoRand += n; 
            }            

            String filename = "DETALLE_ESTADOS_"+ nombreArchivoRand + ".csv";
            
            String fileNameExport = "/data/"+filename;
            
            g.GeneraEstadoEnlaPrueba(idDpto, idPrueba, fileNameExport);
            
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