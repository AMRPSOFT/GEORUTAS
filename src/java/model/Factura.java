
package model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Alex Rodriguez
 */
public class Factura implements java.io.Serializable{
    
     private Integer idfactura;
     private Estudiante estudiante;
     private String nombreestudiante;
     private String apellidoestudiante;
     private String colegio;
     private Date periodoinicio;
     private Date periodofinal;
     private BigDecimal valor;
     
     public Factura (){
        this.idfactura=0;
        this.estudiante = new Estudiante();
     }
     
     public Factura(Estudiante estudiante, String nombreestudiante, String apellidoestudiante, String colegio, Date periodoinicio, Date periodofinal, BigDecimal valor) {
        this.estudiante = estudiante;
        this.nombreestudiante = nombreestudiante;
        this.apellidoestudiante = apellidoestudiante;
        this.colegio = colegio;
        this.periodoinicio = periodoinicio;
        this.periodofinal = periodofinal;
        this.valor = valor;
    }

    public Integer getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(Integer idfactura) {
        this.idfactura = idfactura;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public String getNombreestudiante() {
        return nombreestudiante;
    }

    public void setNombreestudiante(String nombreestudiante) {
        this.nombreestudiante = nombreestudiante;
    }

    public String getApellidoestudiante() {
        return apellidoestudiante;
    }

    public void setApellidoestudiante(String apellidoestudiante) {
        this.apellidoestudiante = apellidoestudiante;
    }

    public String getColegio() {
        return colegio;
    }

    public void setColegio(String colegio) {
        this.colegio = colegio;
    }

    public Date getPeriodoinicio() {
        return periodoinicio;
    }

    public void setPeriodoinicio(Date periodoinicio) {
        this.periodoinicio = periodoinicio;
    }

    public Date getPeriodofinal() {
        return periodofinal;
    }

    public void setPeriodofinal(Date periodofinal) {
        this.periodofinal = periodofinal;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
     
    
}