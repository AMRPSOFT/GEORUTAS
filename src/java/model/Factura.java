
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
     private String nombre;
     private String apellido;
     private String colegio;
     private Date periodoinicio;
     private Date periodofinal;
     private BigDecimal valor;
     private Set estudiantes;
     
     public Factura (){
        this.idfactura=0;
     }
     
     public Factura(String nombre, String apellido, String colegio, Date periodoinicio, Date periodofinal, BigDecimal valor) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.colegio = colegio;
        this.periodoinicio = periodoinicio;
        this.periodofinal = periodofinal;
        this.valor = valor;
    }

    public Factura(Integer idfactura, String nombre, String apellido, String colegio, Date periodoinicio, Date periodofinal, BigDecimal valor, Set estudiantes) {
        this.idfactura = idfactura;
        this.nombre = nombre;
        this.apellido = apellido;
        this.colegio = colegio;
        this.periodoinicio = periodoinicio;
        this.periodofinal = periodofinal;
        this.valor = valor;
        this.estudiantes = estudiantes;
    }
    
    public Integer getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(Integer idfactura) {
        this.idfactura = idfactura;
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

    public Set getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Set estudiantes) {
        this.estudiantes = estudiantes;
    }
    
}
