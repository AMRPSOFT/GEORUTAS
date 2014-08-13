
package bean;

import dao.VehiculoDao;
import dao.VehiculoDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Vehiculo;

/**
 *
 * @author Alex Rodriguez
 */
@Named(value = "vehiculoBean")
@RequestScoped
public class vehiculoBean {
    
    private Vehiculo selectedVehiculo;
    private List<Vehiculo> vehiculos;
    
    public vehiculoBean() {
        this.selectedVehiculo = new Vehiculo();
        this.vehiculos = new ArrayList<Vehiculo>();
    }

    public Vehiculo getSelectedVehiculo() {
        return selectedVehiculo;
    }

    public void setSelectedVehiculo(Vehiculo selectedVehiculo) {
        this.selectedVehiculo = selectedVehiculo;
    }

    public List<Vehiculo> getVehiculos() {
        VehiculoDao vehiculoDao = new VehiculoDaoImpl();
        this.vehiculos = vehiculoDao.findAll();
        return vehiculos;
    }
    public void btnCreateVehiculo(ActionEvent event){
       VehiculoDao vehiculoDao = new VehiculoDaoImpl();
       String msng;
       if(vehiculoDao.create(this.selectedVehiculo)){
           msng = "Se registro correctamente el Vehiculo";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al registrar el Vehiculo";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
       }
    }
    
    public void btnUpdateVehiculo(ActionEvent event){
       VehiculoDao vehiculoDao = new VehiculoDaoImpl();
       String msng;
       if(vehiculoDao.create(this.selectedVehiculo)){
           msng = "Se modifico correctamente el Vehiculo";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al modificar el Vehiculo";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
       }
    }
    
    public void btnDeleteVehiculo(ActionEvent event){
       VehiculoDao vehiculoDao = new VehiculoDaoImpl();
       String msng;
       if(vehiculoDao.delete(this.selectedVehiculo.getIdvehiculo())){
           msng = "Se elimino correctamente el Vehiculo";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al eliminar el Vehiculo";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
        
    }
}
