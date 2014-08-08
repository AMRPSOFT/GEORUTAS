
package model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Alex Rodriguez
 */
public class Factura implements java.io.Serializable{
    
     private Integer idfactura;
     private Date periodoinicio;
     private Date periodofinal;
     private BigDecimal valor;
     private Set detallefactura = new HashSet(0);
     
     public Factura (){
     }
     
     public Factura( Date periodoinicio, Date periodofinal, BigDecimal valor) {
        this.periodoinicio = periodoinicio;
        this.periodofinal = periodofinal;
        this.valor = valor;
    }

    public Factura(Date periodoinicio, Date periodofinal, BigDecimal valor, Set detallefactura ) {
        this.periodoinicio = periodoinicio;
        this.periodofinal = periodofinal;
        this.valor = valor;
        this.detallefactura = detallefactura;
    }
    

    public Integer getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(Integer idfactura) {
        this.idfactura = idfactura;
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

    public Set getDetallefactura() {
        return detallefactura;
    }

    public void setDetallefactura(Set detallefactura) {
        this.detallefactura = detallefactura;
    }
         
}