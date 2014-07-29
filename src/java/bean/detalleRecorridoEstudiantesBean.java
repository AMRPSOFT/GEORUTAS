package bean;

import dao.DetalleRecorridoEstudianteDao;
import dao.DetalleRecorridoEstudianteDaoImpl;
import dao.EstudianteDao;
import dao.EstudianteDaoImpl;
import dao.RecorridoDao;
import dao.RecorridoDaoImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.Detallerecorridoestudiante;
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
@Named(value = "detalleRecorridoEstudiantesBean")
@ViewScoped
public class detalleRecorridoEstudiantesBean implements Serializable{

    Session sesion;
    Transaction transaction;

    private Estudiante estudiante;
    private List<Estudiante> estudiantes;
    private Recorrido recorrido;
    private List<Detallerecorridoestudiante> listaDetallerecorridoestudiante;
    private int identificacion;
    private int numestudiante;

    public detalleRecorridoEstudiantesBean() {
        this.recorrido = new Recorrido();
        this.listaDetallerecorridoestudiante = new ArrayList<>();
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
            this.listaDetallerecorridoestudiante.add(new Detallerecorridoestudiante(null, null, this.estudiante.getIdenticacion() , this.estudiante.getNombre(),
                    this.estudiante.getApellido(), this.estudiante.getDireccion(), this.estudiante.getColegio(), 
                    this.estudiante.getJornada()));
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
                this.listaDetallerecorridoestudiante.add(new Detallerecorridoestudiante(null, null, this.estudiante.getIdenticacion(), this.estudiante.getNombre(),
                    this.estudiante.getApellido(), this.estudiante.getDireccion(), this.estudiante.getColegio(), this.estudiante.getJornada()));

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
    
    public void guardarRecorrido()
    {
        this.sesion=null;
        this.transaction=null;
        
        try
        {
            this.sesion=HibernateUtil.getSessionFactory().openSession();
            
            EstudianteDao estudianteDao = new EstudianteDaoImpl();
            RecorridoDao recorridoDao = new RecorridoDaoImpl();
            DetalleRecorridoEstudianteDao detallerecorridoestudianteDao = new DetalleRecorridoEstudianteDaoImpl();
            
            this.transaction=this.sesion.beginTransaction();
            
            recorridoDao.insert(this.sesion, this.recorrido);
            this.recorrido=recorridoDao.getUltimoRegistro(this.sesion);
            
            for(Detallerecorridoestudiante item : this.listaDetallerecorridoestudiante)
            {
                this.estudiante = estudianteDao.getByIdentificacion(this.sesion, item.getEstudiante().getIdenticacion());
                item.setRecorrido(this.recorrido);
                item.setEstudiante(this.estudiante);
                detallerecorridoestudianteDao.insert(this.sesion, item);
                
            }
            this.transaction.commit();
            
            this.listaDetallerecorridoestudiante = new ArrayList<>();
            this.recorrido = new Recorrido();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Estudiantes Agregados Correctamente"));
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

    public List<Detallerecorridoestudiante> getListaDetallerecorridoestudiante() {
        return listaDetallerecorridoestudiante;
    }

    public void setListaDetallerecorridoestudiante(List<Detallerecorridoestudiante> listaDetallerecorridoestudiante) {
        this.listaDetallerecorridoestudiante = listaDetallerecorridoestudiante;
    }

}
