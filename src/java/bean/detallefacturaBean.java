/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import dao.DetalleFacturaDao;
import dao.DetalleFacturaDaoImpl;
import dao.EstudianteDao;
import dao.EstudianteDaoImpl;
import dao.FacturaDao;
import dao.FacturaDaoImpl;
import java.math.BigDecimal;
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
    private List<Factura> listaFactura;
    private List<Detallefactura> listaDetalleFactura;
    private BigDecimal valorMensualidad;
    
    
    public detallefacturaBean() {
        this.factura = new Factura();
        this.listaDetalleFactura = new ArrayList<>();
        this.listaFactura = new ArrayList<>();
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
            this.listaDetalleFactura.add(new Detallefactura(null, null, this.estudiante.getIdenticacion(), this.estudiante.getNombre(), 
                    this.estudiante.getApellido(), this.estudiante.getColegio(), 0, new BigDecimal("0"), new BigDecimal("0")));
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Estudiante Agregado"));
            RequestContext.getCurrentInstance().update("frmrealizarFactura:tablaListaEstudiantes");
            RequestContext.getCurrentInstance().update("frmrealizarFactura:mensajeGeneral");
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
    
    public void guardarFactura()
    {
        this.sesion=null;
        this.transaction=null;
        
        try
        {
            this.sesion=HibernateUtil.getSessionFactory().openSession();
            
            EstudianteDao estudianteDao = new EstudianteDaoImpl();
            FacturaDao facturaDao = new FacturaDaoImpl();
            DetalleFacturaDao detallefacturaDao = new DetalleFacturaDaoImpl();
            
            this.transaction=this.sesion.beginTransaction();
            
            facturaDao.insert(this.sesion, this.factura);
            this.factura = facturaDao.getUltimoRegistro(this.sesion);
            
            for(Detallefactura item : this.listaDetalleFactura)
            {
                this.estudiante = estudianteDao.getByIdentificacion(this.sesion, item.getIdentificacion());
                item.setEstudiante(this.estudiante);
                item.setFactura(this.factura);
                detallefacturaDao.insert(this.sesion, item);
            }
            this.transaction.commit();
            
            this.listaDetalleFactura = new ArrayList<>();
            this.factura = new Factura();
            
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
    public void calcularCostos()
    {
        try
        {   
            BigDecimal totalMensualidad = new BigDecimal("0");
            
            for(Detallefactura item : this.listaDetalleFactura)
            {
                BigDecimal totalMensualidadAPagar = item.getMensualidad().multiply(new BigDecimal(item.getMesesvencidos()));
                
                item.setTotalmensualidad(totalMensualidadAPagar);
                
                totalMensualidad = totalMensualidad.add(totalMensualidadAPagar);
            }
            
            this.factura.setTotalapagar(totalMensualidad);
            
            RequestContext.getCurrentInstance().update("frmRealizarFactura:tablaListaEstudiantes");
            RequestContext.getCurrentInstance().update("frmRealizarFactura:panelFinalVenta");
        }
        catch(Exception ex)
        {            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public List<Detallefactura> getListaDetalleFactura() {
        return listaDetalleFactura;
    }

    public void setListaDetalleFactura(List<Detallefactura> listaDetalleFactura) {
        this.listaDetalleFactura = listaDetalleFactura;
    }

    public BigDecimal getValorMensualidad() {
        return valorMensualidad;
    }

    public void setValorMensualidad(BigDecimal valorMensualidad) {
        this.valorMensualidad = valorMensualidad;
    }
    
}
