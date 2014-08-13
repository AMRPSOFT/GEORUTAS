/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Recorrido;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Alex Rodriguez
 */
public class Reportes {

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String RUTA = "jdbc:mysql://localhost/georutas";
    public static final String USER = "root";
    public static final String PASSWORD = "12345";
    public static Connection CONEXION;
    
    private Recorrido recorrido;

    public void verReporte(int identificacion) throws JRException, IOException {
        try {
            Class.forName(DRIVER);
            CONEXION = DriverManager.getConnection(RUTA, USER, PASSWORD);
            
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/reportFactura.jasper"));
            Map parametro = new HashMap();
            parametro.put("identificacion", identificacion);
            byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametro, CONEXION);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.setContentType("application/pdf");
            httpServletResponse.setContentLength(bytes.length);
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            servletOutputStream.write(bytes, 0, bytes.length);

            servletOutputStream.flush();
            servletOutputStream.close();
            FacesContext.getCurrentInstance().responseComplete();
        }catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));

        }
    }
    
    public void verReporteRecorrido(int idrecorrido) throws JRException, IOException {
        
        try {
            Class.forName(DRIVER);
            CONEXION = DriverManager.getConnection(RUTA, USER, PASSWORD);
             
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/reportRecorrido.jasper"));
            Map parametro = new HashMap();
            parametro.put("idrecorrido", idrecorrido);
            byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametro, CONEXION);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.setContentType("application/pdf");
            httpServletResponse.setContentLength(bytes.length);
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            servletOutputStream.write(bytes, 0, bytes.length);

            servletOutputStream.flush();
            servletOutputStream.close();
            FacesContext.getCurrentInstance().responseComplete();
        }catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));

        }
    }

    public Recorrido getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(Recorrido recorrido) {
        this.recorrido = recorrido;
    }
    
}
