<%@page import="co.com.grupoasd.nomina.negocio.mediopago.CoberturaMedioPagoController"%>
<%@page import="co.com.grupoasd.nomina.common.model.Acceso"%>
<%@page import="co.com.grupoasd.nomina.common.util.AuthUtil"%>
<%@page import="co.com.grupoasd.nomina.negocio.mediopago.ICoberturaMedioPagoController,org.apache.commons.lang3.StringUtils"%>

<%
    co.com.grupoasd.nomina.negocio.mediopago.ICoberturaMedioPagoController cobertura = new CoberturaMedioPagoController();
    
    
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
            String prueba = request.getParameter("prueba");
            String filename = "COBERTURA_MEDIOS_PAGO.csv";
            cobertura.generaCoberturaPorMunicipio(Integer.valueOf(prueba));
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