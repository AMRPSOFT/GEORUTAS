package model;
// Generated 6/06/2014 11:50:31 AM by Hibernate Tools 3.6.0


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
 * Recorrido generated by hbm2java
 */
@Entity
@Table(name="recorrido"
    ,catalog="georutas"
)
public class Recorrido  implements java.io.Serializable {


     private Integer idrecorrido;
     private Integer numEstudiantes;
     private String jornada;
 
    public Recorrido() {
    }

    public Recorrido( Integer numEstudiantes, String jornada) {
       this. numEstudiantes= numEstudiantes;
       this.jornada = jornada;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idrecorrido", unique=true, nullable=false)
    public Integer getIdrecorrido() {
        return this.idrecorrido;
    }
    
    public void setIdrecorrido(Integer idrecorrido) {
        this.idrecorrido = idrecorrido;
    }

    @Column(name="NumEstudiantes", nullable=false, length=11)
    public Integer getNumEstudiantes() {
        return numEstudiantes;
    }

    public void setNomBarrio(Integer numEstudiantes) {
        this.numEstudiantes = numEstudiantes;
    }

    @Column(name="Jornada", nullable=false, length=5)
    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

}