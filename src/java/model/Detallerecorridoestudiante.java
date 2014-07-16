
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Alex Rodriguez
 */

public class Detallerecorridoestudiante implements java.io.Serializable{
    
    private Integer iddetallerecorridoestudiante;
     private Estudiante estudiante;
     private Recorrido recorrido;
     private String nombreestudiante;
     private String apellidoestudiante;
     private String direccionestudiante;
     private String colegio;
     private String jornada;
     
    
     public Detallerecorridoestudiante(){
     }

    public Detallerecorridoestudiante(Estudiante estudiante, Recorrido recorrido, String nombre, String apellido, String direccion, String colegio, String jornada) {
        this.estudiante = estudiante;
        this.recorrido = recorrido;
        this.nombreestudiante = nombre;
        this.apellidoestudiante = apellido;
        this.direccionestudiante = direccion;
        this.colegio = colegio;
        this.jornada = jornada;
    }

    public Integer getIddetallerecorridoestudiante() {
        return iddetallerecorridoestudiante;
    }

    public void setIddetallerecorridoestudiante(Integer iddetallerecorridoestudiante) {
        this.iddetallerecorridoestudiante = iddetallerecorridoestudiante;
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

    public String getNombreEstudiante() {
        return nombreestudiante;
    }

    public void setNombreEstudiante(String nombre) {
        this.nombreestudiante = nombre;
    }

    public String getApellidoEstudiante() {
        return apellidoestudiante;
    }

    public void setApellidoEstudiante(String apellido) {
        this.apellidoestudiante = apellido;
    }

    public String getDireccionEstudiante() {
        return direccionestudiante;
    }

    public void setDireccionEstudiante(String direccion) {
        this.direccionestudiante = direccion;
    }

    public String getColegio() {
        return colegio;
    }

    public void setColegio(String colegio) {
        this.colegio = colegio;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }
    
     
}
