<%@page import="co.com.grupoasd.nomina.dao.GeneraSolicitudesDao"%>
<%
           
            co.com.grupoasd.nomina.dao.GeneraSolicitudesDao g = new GeneraSolicitudesDao();

            int idPrueba = Integer.parseInt(request.getParameter("idPrueba"));
            int estado=Integer.parseInt(request.getParameter("estado"));
            String filename = "EstadoPersonal"+idPrueba  + ".csv";
            String dpto=request.getParameter("dpto");
            String mcpio=request.getParameter("mcpio");
            g.generaListadoEstadoPersonas("/data/"+filename, idPrueba, dpto, mcpio, estado);
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