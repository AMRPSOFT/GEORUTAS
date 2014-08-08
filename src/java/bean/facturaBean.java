/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import dao.EstudianteDao;
import dao.EstudianteDaoImpl;
import dao.FacturaDao;
import dao.FacturaDaoImpl;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.Estudiante;
import model.Factura;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
import util.HibernateUtil;

/**
 *
 * @author Alex Rodriguez
 */
@Named(value = "facturaBean")
@ViewScoped
public class facturaBean {

    private Factura selectedFactura;
    private List<Factura> facturas;
    private Estudiante selectedEstudiante;
    private Session sesion;
    private Transaction transaction;
    private int identificacion;
    
    public facturaBean() {
        this.selectedFactura = new Factura();
        this.selectedEstudiante = new Estudiante();
        this.facturas = new ArrayList<Factura>();
    }

    public Estudiante getSelectedEstudiante() {
        return selectedEstudiante;
    }

    public void setSelectedEstudiante(Estudiante selectedEstudiante) {
        this.selectedEstudiante = selectedEstudiante;
    }
    
    public Factura getSelectedFactura() {
        return selectedFactura;
    }

    public void setSelectedFactura(Factura selectedFactura) {
        this.selectedFactura = selectedFactura;
    }

    public List<Factura> getFacturas() {
        FacturaDao facturaDao = new FacturaDaoImpl();
        this.facturas = facturaDao.findAll();
        return facturas;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }
    
    
    public void btnCreateFactura(ActionEvent event){
       FacturaDao facturaDao = new FacturaDaoImpl();
       String msng;
       if(facturaDao.create(this.selectedFactura)){
           msng = "Se registro correctamente la Factura";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al registrar ls Factura";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
       }
    }
    
    public void btnUpdateFactura(ActionEvent event){
       FacturaDao facturaDao = new FacturaDaoImpl();
       String msng;
       if(facturaDao.update(this.selectedFactura)){
           msng = "Se modificado correctamente la Factura";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al modificar la Factura";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
       }
    }
    
    public void btnDeleteFactura(ActionEvent event){
       FacturaDao facturaDao = new FacturaDaoImpl();
       String msng;
       if(facturaDao.delete(this.selectedFactura.getIdfactura())){
           msng = "Se elimino correctamente la Factura";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al eliminar la Factura";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
        
    }
    
    public void buscarPorIdentificacion()
    {
        this.sesion=null;
        this.transaction=null;
        try
        {
            if(this.selectedEstudiante.getIdenticacion()==0){return;}
            
            this.sesion=HibernateUtil.getSessionFactory().openSession();
            
            EstudianteDao estudianteDao = new EstudianteDaoImpl();
            
            this.transaction=this.sesion.beginTransaction();
            
            this.selectedEstudiante = estudianteDao.getByIdentificacion(this.sesion, this.identificacion);
            
            if(this.selectedEstudiante!=null)
            {
                this.facturas.add(new Factura(null, this.selectedEstudiante.getIdenticacion(), this.selectedEstudiante.getNombre(), this.selectedEstudiante.getApellido(), this.selectedFactura.getColegio(), this.selectedFactura.getPeriodoinicio(), this.selectedFactura.getPeriodofinal(), new BigDecimal("0")));

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Factura Realizada"));
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Identificacion Invalida", "Estudiante no encontrado"));
            }
            
            this.transaction.commit();
            
            RequestContext.getCurrentInstance().update("formDataTable:usuario");
            RequestContext.getCurrentInstance().update("formDataTable:txtIdentificacion");
        }
        catch(Exception ex)
        {
            if(this.transaction!=null)
            {
                transaction.rollback();
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
        finally
        {
            if(this.sesion!=null)
            {
                this.sesion.close();
            }
        }
    }
}
