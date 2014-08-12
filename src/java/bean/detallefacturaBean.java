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
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Detallefactura;
import model.Estudiante;
import model.Factura;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
import util.HibernateUtil;
import util.Reportes;

/**
 *
 * @author Alex Rodriguez
 */
@Named(value = "detallefacturaBean")
@ViewScoped
public class detallefacturaBean {

    Session sesion;
    Transaction transaction;

    private Estudiante estudiante;
    private List<Estudiante> estudiantes;
    private Factura factura;
    private List<Factura> listaFactura;
    private List<Detallefactura> listaDetalleFactura;
    private List<Detallefactura> detalleFacturas;
    private Detallefactura selectedDetalle;
    private BigDecimal valorMensualidad;
    private int identificacion;

    public detallefacturaBean() {
        this.factura = new Factura();
        this.selectedDetalle = new Detallefactura();
        this.listaDetalleFactura = new ArrayList<>();
        this.listaFactura = new ArrayList<>();
        this.detalleFacturas = new ArrayList<Detallefactura>();
    }

    public void PDF(ActionEvent actionEvent) throws JRException, IOException {

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/jrFactura.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getDetalleFacturas()));
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=Factura.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        servletOutputStream.flush();
        servletOutputStream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public List<Estudiante> getAllEstudiantes() {
        this.sesion = null;
        this.transaction = null;

        try {
            this.sesion = HibernateUtil.getSessionFactory().openSession();

            EstudianteDao estudianteDao = new EstudianteDaoImpl();

            this.transaction = this.sesion.beginTransaction();

            this.estudiantes = estudianteDao.getAll(this.sesion);

            this.transaction.commit();

            return this.estudiantes;
        } catch (Exception ex) {
            if (this.transaction != null) {
                transaction.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));

            return null;
        } finally {
            if (this.sesion != null) {
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
            this.transaction = this.sesion.beginTransaction();
            this.estudiante = estudianteDao.getByIdEstdiante(this.sesion, idestudiante);
            this.listaDetalleFactura.add(new Detallefactura(null, null, this.estudiante.getIdenticacion(), this.estudiante.getNombre(),
                    this.estudiante.getApellido(), this.estudiante.getColegio(), 0, new BigDecimal("100000"), new BigDecimal("0")));
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Estudiante Agregado"));
            RequestContext.getCurrentInstance().update("frmrealizarFactura:tablaListaEstudiantes");
            RequestContext.getCurrentInstance().update("frmrealizarFactura:mensajeGeneral");
        } catch (Exception ex) {
            if (this.transaction != null) {
                transaction.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        } finally {
            if (this.sesion != null) {
                this.sesion.close();
            }
        }
    }

    public void guardarFactura() {
        this.sesion = null;
        this.transaction = null;

        try {
            this.sesion = HibernateUtil.getSessionFactory().openSession();

            EstudianteDao estudianteDao = new EstudianteDaoImpl();
            FacturaDao facturaDao = new FacturaDaoImpl();
            DetalleFacturaDao detallefacturaDao = new DetalleFacturaDaoImpl();

            this.transaction = this.sesion.beginTransaction();

            facturaDao.insert(this.sesion, this.factura);
            this.factura = facturaDao.getUltimoRegistro(this.sesion);

            for (Detallefactura item : this.listaDetalleFactura) {
                this.estudiante = estudianteDao.getByIdentificacion(this.sesion, item.getIdentificacion());
                item.setEstudiante(this.estudiante);
                item.setFactura(this.factura);
                detallefacturaDao.insert(this.sesion, item);
            }
            this.transaction.commit();

            this.listaDetalleFactura = new ArrayList<>();
            this.factura = new Factura();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Factura Generada"));
        } catch (Exception ex) {
            if (this.transaction != null) {
                transaction.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        } finally {
            if (this.sesion != null) {
                this.sesion.close();
            }
        }
    }

    public void calcularCostos() {
        try {
            BigDecimal total = new BigDecimal("0");

            for (Detallefactura item : this.listaDetalleFactura) {
                BigDecimal totalMensualidadAPagar = item.getMensualidad().multiply(new BigDecimal(item.getMesesvencidos()));

                item.setTotalmensualidad(totalMensualidadAPagar);

                total = total.add(totalMensualidadAPagar);
            }

            this.factura.setTotalapagar(total);

            RequestContext.getCurrentInstance().update("frmRealizarFactura:tablaListaEstudiantes");
            RequestContext.getCurrentInstance().update("frmRealizarFactura:panelFinalVenta");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }

    public void mostrarPdf(ActionEvent actionEvent) throws JRException, IOException {
        Map parametro = new HashMap();
        identificacion = this.getIdentificacion();
        //parametro.put("identificacion", identificacion);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/jrFactura.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametro, new JRBeanCollectionDataSource(this.getDetalleFacturas()));
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setContentLength(bytes.length);
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        servletOutputStream.write(bytes, 0, bytes.length);

        servletOutputStream.flush();
        servletOutputStream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void startReport() throws JRException, IOException {
        this.sesion = null;
        this.transaction = null;
        try {
            this.sesion = HibernateUtil.getSessionFactory().openSession();
            identificacion = this.getIdentificacion();
            Reportes reporte = new Reportes();
            this.transaction = this.sesion.beginTransaction();
            reporte.verReporte(identificacion);

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", e.getMessage()));
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

    public Detallefactura getSelectedDetalle() {
        return selectedDetalle;
    }

    public void setSelectedDetalle(Detallefactura selectedDetalle) {
        this.selectedDetalle = selectedDetalle;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public List<Detallefactura> getDetalleFacturas() {
        DetalleFacturaDao detalleFacturaDao = new DetalleFacturaDaoImpl();
        this.detalleFacturas = detalleFacturaDao.findAll();
        return detalleFacturas;
    }

}
