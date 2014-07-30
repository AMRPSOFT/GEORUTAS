/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import dao.FacturaDao;
import dao.FacturaDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Factura;

/**
 *
 * @author Alex Rodriguez
 */
@Named(value = "facturaBean")
@RequestScoped
public class facturaBean {

    private Factura selectedFactura;
    private List<Factura> facturas;
    
    public facturaBean() {
        this.selectedFactura = new Factura();
        this.facturas = new ArrayList<Factura>();
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
    
    public void btnCreateUsuario(ActionEvent event){
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
    
    public void btnUpdateUsuario(ActionEvent event){
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
    
    public void btnDeleteUsuario(ActionEvent event){
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
    
}
