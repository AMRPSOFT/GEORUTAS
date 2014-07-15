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
import util.HibernateUtil;

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
    
    private Integer identificacion;

    public recorridoBean() {
        this.recorrido = new Recorrido();
        this.recorridos = new ArrayList<>();
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

    
    public void agregarListadeEstudiantes(Integer idestudiante) {
        this.sesion = null;
        this.transaction = null;

        try {
            this.sesion = HibernateUtil.getSessionFactory().openSession();
            EstudianteDao estudianteDao = new EstudianteDaoImpl();
            this.transaction=this.sesion.beginTransaction();
            this.estudiante = estudianteDao.getByIdEstdiante(this.sesion, idestudiante);
            this.recorridos.add(new Recorrido(null, this.estudiante.getDireccion(), this.estudiante.getNombre(),
                    this.estudiante.getApellido(), this.estudiante.getColegio(), this.estudiante.getJornada()));
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Estudiante Agregado"));
            RequestContext.getCurrentInstance().update("formCreate:tablaListaEstudiantes");
            RequestContext.getCurrentInstance().update("formDataTable:msgs");
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

    public void agregarListaDeEstudiantesPorIdentificacion()
    {
        this.sesion=null;
        this.transaction=null;
        
        try
        {
            if(this.identificacion == 0){return;}
            
            this.sesion=HibernateUtil.getSessionFactory().openSession();
            
            EstudianteDao estudianteDao = new EstudianteDaoImpl();
            
            this.transaction=this.sesion.beginTransaction();
            
            this.estudiante=estudianteDao.getByIdentificacion(this.sesion, this.identificacion);
            
            if(this.estudiante!=null)
            {
                this.recorridos.add(new Recorrido(null, this.estudiante.getDireccion(), this.estudiante.getNombre(),
                    this.estudiante.getApellido(), this.estudiante.getColegio(), this.estudiante.getJornada()));

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Estudiante agregado"));
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Identificacion Invalida", "Estudiante no encontrado"));
            }
            
            this.identificacion = 0;
            
            this.transaction.commit();
            
            RequestContext.getCurrentInstance().update("formCreate:tablaListaestudiantes");
            RequestContext.getCurrentInstance().update("formCreate:msgs");
            RequestContext.getCurrentInstance().update("formCreate:txtAgregarPorIdentificacion");
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

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Recorrido getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(Recorrido recorrido) {
        this.recorrido = recorrido;
    }

    public Integer getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Integer identificacion) {
        this.identificacion = identificacion;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
    
    public List<Recorrido> getRecorridos() {
        return recorridos;
    }

    public void setRecorridos(List<Recorrido> recorridos) {
        this.recorridos = recorridos;
    }
}
