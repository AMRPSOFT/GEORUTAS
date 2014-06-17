
package bean;

import dao.ConductorDao;
import dao.ConductorDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import model.Conductor;

/**
 *
 * @author Alex Rodriguez
 */
@Named(value = "conductorBean")
@Dependent
public class conductorBean {

   private Conductor selectedConductor;
    private List<Conductor> conductores;
    private List<SelectItem> selectOneItemsConductor;
    
    public conductorBean() {
        this.conductores = new ArrayList<Conductor>();
        this.selectedConductor = new Conductor();
    }
    
    public List<SelectItem> getSelectOneItemsConductor() {
        this.selectOneItemsConductor = new ArrayList<SelectItem>();
        ConductorDao conductorDao = new ConductorDaoImpl();
        conductores = conductorDao.selectItems();
        for (Conductor conductor : conductores) {
            SelectItem selectItem = new SelectItem(conductor.getIdconductor(), conductor.getNombre());
            this.selectOneItemsConductor.add(selectItem);
        }
        return selectOneItemsConductor;
    }

    public Conductor getSelectedConductor() {
        return selectedConductor;
    }

    public void setSelectedConductor(Conductor selectedConductor) {
        this.selectedConductor = selectedConductor;
    }

    public List<Conductor> getConductores() {
        ConductorDao conductorDao = new ConductorDaoImpl();
        this.conductores = conductorDao.findAll();
        return conductores;
    }
    
    public void btnCreateConductor(ActionEvent event){
       ConductorDao conductorDao = new ConductorDaoImpl();
       String msng;
       if(conductorDao.create(this.selectedConductor)){
           msng = "Se registro correctamente el Conductor";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al registrar el Conductor";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
       }
    }
    
    public void btnUpdateConductor(ActionEvent event){
       ConductorDao conductorDao = new ConductorDaoImpl();
       String msng;
       if(conductorDao.update(this.selectedConductor)){
           msng = "Se modificado correctamente el Conductor";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al modificar el Conductor";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
       }
    }
    
    public void btnDeleteAcudiente(ActionEvent event){
       ConductorDao conductorDao = new ConductorDaoImpl();
       String msng;
       if(conductorDao.delete(this.selectedConductor.getIdconductor())){
           msng = "Se elimino correctamente el Conductor";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al eliminar el Conductor";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
        
    }
}
