
package bean;

import dao.EstudianteDao;
import dao.EstudianteDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Estudiante;

/**
 *
 * @author Alex Rodriguez
 */
@Named(value = "estudianteBean")
@RequestScoped
public class estudianteBean {

    private Estudiante selectedEstudiante;
    private List<Estudiante> estudiantes;
    
    public estudianteBean() {
        this.estudiantes = new ArrayList<Estudiante>();
        this.selectedEstudiante = new Estudiante();
    }

    public Estudiante getSelectedEstudiante() {
        return selectedEstudiante;
    }

    public void setSelectedEstudiante(Estudiante selectedEstudiante) {
        this.selectedEstudiante = selectedEstudiante;
    }

    public List<Estudiante> getEstudiantes() {
        EstudianteDao estudianteDao = new EstudianteDaoImpl();
        this.estudiantes = estudianteDao.findAll();
        return estudiantes;
    }

    public void btnCreateEstudiante(ActionEvent event){
       EstudianteDao estudianteDao = new EstudianteDaoImpl();
       String msng;
       if(estudianteDao.create(this.selectedEstudiante)){
           msng = "Se registro correctamente el Estudiante";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al registrar el Estudiante";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
       }
    }
    
    public void btnUpdateEstudiante(ActionEvent event){
       EstudianteDao estudianteDao = new EstudianteDaoImpl();
       String msng;
       if(estudianteDao.update(this.selectedEstudiante)){
           msng = "Se modificado correctamente el Estudiante";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al modificar el Estudiante";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
       }
    }
    
    public void btnDeleteEstudiante(ActionEvent event){
       EstudianteDao estudianteDao = new EstudianteDaoImpl();
       String msng;
       if(estudianteDao.delete(this.selectedEstudiante.getIdestudiante())){
           msng = "Se elimino correctamente el Estudiante";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
       else{
           msng = "Error al eliminar el Estudiante";
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msng, null);
           FacesContext.getCurrentInstance().addMessage(null, message);
       }
        
    }
}
