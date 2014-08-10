
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
     private BigDecimal totalapagar;
     private Set detallefactura = new HashSet(0);
     
     public Factura (){
     }
     
     public Factura( Date periodoinicio, Date periodofinal, BigDecimal totalapagar) {
        this.periodoinicio = periodoinicio;
        this.periodofinal = periodofinal;
        this.totalapagar = totalapagar;
    }

    public Factura(Date periodoinicio, Date periodofinal, Set detallefactura, BigDecimal totalapagar) {
        this.periodoinicio = periodoinicio;
        this.periodofinal = periodofinal;
        this.totalapagar = totalapagar;
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

    public Set getDetallefactura() {
        return detallefactura;
    }

    public void setDetallefactura(Set detallefactura) {
        this.detallefactura = detallefactura;
    }

    public BigDecimal getTotalapagar() {
        return totalapagar;
    }

    public void setTotalapagar(BigDecimal totalapagar) {
        this.totalapagar = totalapagar;
    }
         
}