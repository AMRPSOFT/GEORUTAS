/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Alex Rodriguez
 */
public class Detallefactura implements Serializable{
    
     private Integer iddetallefactura;
     private Estudiante estudiante;
     private Factura factura;
     private int identificacion;
     private String nombreEstudiante;
     private String apellidoEstudiante;
     private String colegio;

    public Detallefactura() {
    }

    public Detallefactura(Estudiante estudiante, Factura factura, int identificacion, String nombreEstudiante, String apellidoEstudiante, String colegio) {
        this.estudiante = estudiante;
        this.factura = factura;
        this.identificacion = identificacion;
        this.nombreEstudiante = nombreEstudiante;
        this.apellidoEstudiante = apellidoEstudiante;
        this.colegio = colegio;
    }

    public Integer getIddetallefactura() {
        return iddetallefactura;
    }

    public void setIddetallefactura(Integer iddetallefactura) {
        this.iddetallefactura = iddetallefactura;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getApellidoEstudiante() {
        return apellidoEstudiante;
    }

    public void setApellidoEstudiante(String apellidoEstudiante) {
        this.apellidoEstudiante = apellidoEstudiante;
    }

    public String getColegio() {
        return colegio;
    }

    public void setColegio(String colegio) {
        this.colegio = colegio;
    }

}
