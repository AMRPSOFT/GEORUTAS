
package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Alex Rodriguez
 */
public class Factura implements java.io.Serializable{
    
     private Integer idfactura;
     private Estudiante estudiante;
     private String nombre;
     private String apellido;
     private String colegio;
     private Date periodoinicio;
     private Date periodofinal;
     private BigDecimal valor;
     
     public Factura (){
        this.idfactura=0;
        this.estudiante = new Estudiante();
     }
     
     public Factura(Estudiante estudiante, String nombre, String apellido, String colegio, Date periodoinicio, Date periodofinal, BigDecimal valor) {
        this.estudiante = estudiante;
        this.nombre = nombre;
        this.apellido = apellido;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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
