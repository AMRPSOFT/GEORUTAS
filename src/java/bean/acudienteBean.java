/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import dao.AcudienteDao;
import dao.AcudienteDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import model.Acudiente;

/**
 *
 * @author Alex Rodriguez
 */
@Named(value = "acudienteBean")
@RequestScoped
public class acudienteBean {

    private Acudiente selectedAcudiente;
    private List<Acudiente> acudientes;
    private List<SelectItem> selectOneItemsAcudiente;
    
    public acudienteBean() {
        this.acudientes = new ArrayList<Acudiente>();
        this.selectedAcudiente = new Acudiente();
    }
    
    public List<SelectItem> getSelectOneItemsAcudiente() {
        this.selectOneItemsAcudiente = new ArrayList<SelectItem>();
        AcudienteDao acudienteDao = new AcudienteDaoImpl();
        acudientes = acudienteDao.selectItems();
        for (Acudiente acudiente : acudientes) {
            SelectItem selectItem = new SelectItem(acudiente.getIdacudiente(), acudiente.getNombre());
            this.selectOneItemsAcudiente.add(selectItem);
        }
        return selectOneItemsAcudiente;
    }

    public Acudiente getSelectedAcudiente() {
        return selectedAcudiente;
    }

    public void setSelectedAcudiente(Acudiente selectedAcudiente) {
        this.selectedAcudiente = selectedAcudiente;
    }

    public List<Acudiente> getAcudientes() {
        AcudienteDao acudientedao = new AcudienteDaoImpl();
        this.acudientes = acudientedao.findAll();
        return acudientes;
    }
    
    public void btnCreateAcudiente(ActionEvent event){
       AcudienteDao acudienteDao = new AcudienteDaoImpl();
       String msng;
       if(acudienteDao.create(this.selectedAcudiente)){
           msng = "Se registro correctamente el Acudiente";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al registrar el Acudiente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
       }
    }
    
    public void btnUpdateAcudiente(ActionEvent event){
       AcudienteDao acudienteDao = new AcudienteDaoImpl();
       String msng;
       if(acudienteDao.update(this.selectedAcudiente)){
           msng = "Se modificado correctamente el Acudiente";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al modificar el Acudiente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
       }
    }
    
    public void btnDeleteAcudiente(ActionEvent event){
       AcudienteDao acudienteDao = new AcudienteDaoImpl();
       String msng;
       if(acudienteDao.delete(this.selectedAcudiente.getIdacudiente())){
           msng = "Se elimino correctamente el Acudiente";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al eliminar el Acudiente";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
        
    }
    
}
