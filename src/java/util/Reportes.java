/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Alex Rodriguez
 */
public class Reportes {
    
    public static final String DRIVER="com.mysql.jdbc.Driver";
        public static final String RUTA="jdbc:mysql://localhost/georutas";
        public static final String USER="root";
        public static final String PASSWORD="12345";
	public static Connection CONEXION;

    public void startReport(int identificacion){

        try{
            Class.forName(DRIVER);
            CONEXION = DriverManager.getConnection(RUTA,USER,PASSWORD);
            
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/reportFactura.jasper"));
            JasperReport reporte = (JasperReport) JRLoader.loadObject(jasper); 
            Map param = new HashMap();
            param.put("identificacion", identificacion);

            JasperPrint jasperprint= JasperFillManager.fillReport(reporte,param,CONEXION);
            JasperViewer visor=new JasperViewer(jasperprint,false);
            visor.setTitle("Geniz Reportes - GSF");
            visor.setVisible(true);



        }catch(Exception ex){
            javax.swing.JOptionPane.showMessageDialog(null, ex);

        }
    }
    
}
