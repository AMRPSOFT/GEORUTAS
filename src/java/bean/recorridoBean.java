
package bean;

import dao.EstudianteDao;
import dao.EstudianteDaoImpl;
import dao.RecorridoDao;
import dao.RecorridoDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Estudiante;
import model.Recorrido;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Alex Rodriguez
 */
@Named(value = "recorridoBean")
@RequestScoped
public class recorridoBean {
    
    Session sesion;
    Transaction transaction;
    
    private Estudiante estudiante;
    private List<Estudiante> estudiantes;
    private Recorrido recorrido;
    private List<Recorrido> recorridos;

    public recorridoBean() {
        this.recorrido = new Recorrido();
        this.recorridos = new ArrayList<>();
    }

    public List<Estudiante> getAllEstudiantes() {
        EstudianteDao estudianteDao = new EstudianteDaoImpl();
        this.estudiantes = estudianteDao.findAll();
        return estudiantes;
    }

    public List<Recorrido> getRecorridos() {
        return recorridos;
    }
    
    
    
    public void agregarListadeEstudiantes (Integer idEstudiante) {
        this.sesion=null;
        this.transaction=null;
        EstudianteDao estudianteDao = new EstudianteDaoImpl();
        this.estudiante = estudianteDao.getByIdEstdiante(this.sesion, idEstudiante);
        this.recorridos.add(new Recorrido(null, this.estudiante.getDireccion(), this.estudiante.getNombre(),
                                          this.estudiante.getApellido(), this.estudiante.getColegio(), this.estudiante.getJornada()));
        this.transaction.commit();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Estudiante Agregado"));
        RequestContext.getCurrentInstance().update("formCreate:tablaListaEstudiantes");
        RequestContext.getCurrentInstance().update("formCreate:msgs");
    }
    
}
