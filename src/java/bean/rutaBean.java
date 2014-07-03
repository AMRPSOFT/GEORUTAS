
package bean;

import dao.RutaDao;
import dao.RutaDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import model.Ruta;

/**
 *
 * @author Alex Rodriguez
 */
@Named(value = "rutaBean")
@RequestScoped
public class rutaBean {

    private Ruta selectedRuta;
    private List<Ruta> rutas;
    private List<SelectItem> selectOneItemsRuta;
    
    public rutaBean() {
        this.rutas = new ArrayList<Ruta>();
        this.selectedRuta = new Ruta();
    }

    public List<SelectItem> getSelectOneItemsRuta() {
        this.selectOneItemsRuta = new ArrayList<SelectItem>();
        RutaDao rutaDao = new RutaDaoImpl();
        rutas = rutaDao.selectItems();
        for (Ruta ruta : rutas) {
            SelectItem selectItem = new SelectItem(ruta.getIdruta(), ruta.getNomBarrio());
            this.selectOneItemsRuta.add(selectItem);
        }
        return selectOneItemsRuta;
    }

    public Ruta getSelectedRuta() {
        return selectedRuta;
    }

    public void setSelectedRuta(Ruta selectedRuta) {
        this.selectedRuta = selectedRuta;
    }

    public List<Ruta> getRutas() {
        RutaDao rutaDao = new RutaDaoImpl();
        this.rutas = rutaDao.findAll();
        return rutas;
    }
    
    public void btnCreateRuta(ActionEvent event){
       RutaDao rutaDao = new RutaDaoImpl();
       String msng;
       if(rutaDao.create(this.selectedRuta)){
           msng = "Se registro correctamente la Ruta";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al registrar la Ruta";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
       }
    }
    
    public void btnUpdateAcudiente(ActionEvent event){
       RutaDao rutaDao = new RutaDaoImpl();
       String msng;
       if(rutaDao.update(this.selectedRuta)){
           msng = "Se modificado correctamente la Ruta";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al modificar la Ruta";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
       }
    }
    
    public void btnDeleteAcudiente(ActionEvent event){
       RutaDao rutaDao = new RutaDaoImpl();
       String msng;
       if(rutaDao.delete(this.selectedRuta.getIdruta())){
           msng = "Se elimino correctamente la Ruta";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al eliminar la Ruta";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
        
    }
    
}
