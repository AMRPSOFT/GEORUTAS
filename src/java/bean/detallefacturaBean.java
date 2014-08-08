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
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.Detallefactura;
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
public class detallefacturaBean {

    Session sesion;
    Transaction transaction;
    
    private Estudiante estudiante;
    private List<Estudiante> estudiantes;
    private Factura factura;
    private List<Detallefactura> listaDetalleFactura;
    
    
    public detallefacturaBean() {
        this.factura = new Factura();
        this.listaDetalleFactura = new ArrayList<>();
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

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }
    
    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }
    
    public List<Estudiante> getAllEstudiantes() {
        this.sesion=null;
        this.transaction=null;
        
        try
        {
            this.sesion=HibernateUtil.getSessionFactory().openSession();
            
            EstudianteDao estudianteDao = new EstudianteDaoImpl();
            
            this.transaction=this.sesion.beginTransaction();
            
            this.estudiantes=estudianteDao.getAll(this.sesion);
            
            this.transaction.commit();
            
            return this.estudiantes;
        }
        catch(Exception ex)
        {
            if(this.transaction!=null)
            {
                transaction.rollback();
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
            
            return null;
        }
        finally
        {
            if(this.sesion!=null)
            {
                this.sesion.close();
            }
        }
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

    public void agregarListadeEstudiantes(Integer idestudiante) {
        this.sesion = null;
        this.transaction = null;

        try {
            this.sesion = HibernateUtil.getSessionFactory().openSession();
            EstudianteDao estudianteDao = new EstudianteDaoImpl();
            this.transaction=this.sesion.beginTransaction();
            this.selectedEstudiante = estudianteDao.getByIdEstdiante(this.sesion, idestudiante);
            this.facturas.add(new  (null, this.selectedEstudiante.getIdenticacion(), this.selectedEstudiante.getNombre(), 
                    this.selectedEstudiante.getApellido(), this.selectedEstudiante.getColegio(), 
                    this.selectedFactura.getPeriodoinicio(), this.selectedFactura.getPeriodofinal(), this.selectedFactura.getValor()));
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Estudiante Agregado"));
            RequestContext.getCurrentInstance().update("frmrealizarRecorrido:tablaListaEstudiantes");
            RequestContext.getCurrentInstance().update("frmrealizarRecorrido:mensajeGeneral");
        } 
        catch(Exception ex) {
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
