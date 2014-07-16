
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

@Entity
@Table(name="detallerecorridoestudiante"
    ,catalog="georutas"
)
public class Detallerecorridoestudiante implements java.io.Serializable{
    
    private Integer iddetallerecorridoestudiante;
     private Estudiante estudiante;
     private Recorrido recorrido;
     private String nombre;
     private String apellido;
     private String direccion;
     private String colegio;
     private String jornada;
     
    
     public Detallerecorridoestudiante(){
     }

    public Detallerecorridoestudiante(Estudiante estudiante, Recorrido recorrido, String nombre, String apellido, String direccion, String colegio, String jornada) {
        this.estudiante = estudiante;
        this.recorrido = recorrido;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.colegio = colegio;
        this.jornada = jornada;
    }

    @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="iddetallerecorridoestudiante", unique=true, nullable=false)
    public Integer getIddetallerecorridoestudiante() {
        return iddetallerecorridoestudiante;
    }

    public void setIddetallerecorridoestudiante(Integer iddetallerecorridoestudiante) {
        this.iddetallerecorridoestudiante = iddetallerecorridoestudiante;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IdEstudiante", nullable=false)
    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IdRecorrido", nullable=false)
    public Recorrido getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(Recorrido recorrido) {
        this.recorrido = recorrido;
    }

    @Column(name="Nombre", nullable=false)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name="Apellido", nullable=false)
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Column(name="Direccion", nullable=false)
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Column(name="Colegio", nullable=false)
    public String getColegio() {
        return colegio;
    }

    public void setColegio(String colegio) {
        this.colegio = colegio;
    }

    @Column(name="Jornada", nullable=false)
    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }
    
     
}
