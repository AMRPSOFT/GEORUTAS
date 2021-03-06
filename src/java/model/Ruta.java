package model;
// Generated 6/06/2014 11:50:31 AM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Ruta generated by hbm2java
 */
@Entity
@Table(name="ruta"
    ,catalog="georutas"
)
public class Ruta  implements java.io.Serializable {


     private Integer idruta;
     private double latitud;
     private double longitud;
     private String nomBarrio;
     private Set recorridos = new HashSet(0);
     private Set vehiculos = new HashSet(0);

    public Ruta() {
    }

	
    public Ruta(double latitud, double longitud, String nomBarrio) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.nomBarrio = nomBarrio;
    }
    public Ruta(double latitud, double longitud, String nomBarrio, Set recorridos, Set vehiculos) {
       this.latitud = latitud;
       this.longitud = longitud;
       this.nomBarrio = nomBarrio;
       this.recorridos = recorridos;
       this.vehiculos = vehiculos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idruta", unique=true, nullable=false)
    public Integer getIdruta() {
        return this.idruta;
    }
    
    public void setIdruta(Integer idruta) {
        this.idruta = idruta;
    }

    
    @Column(name="Latitud", nullable=false, precision=22, scale=0)
    public double getLatitud() {
        return this.latitud;
    }
    
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    
    @Column(name="Longitud", nullable=false, precision=22, scale=0)
    public double getLongitud() {
        return this.longitud;
    }
    
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    
    @Column(name="NomBarrio", nullable=false, length=50)
    public String getNomBarrio() {
        return this.nomBarrio;
    }
    
    public void setNomBarrio(String nomBarrio) {
        this.nomBarrio = nomBarrio;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="ruta")
    public Set getRecorridos() {
        return this.recorridos;
    }
    
    public void setRecorridos(Set recorridos) {
        this.recorridos = recorridos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="ruta")
    public Set getVehiculos() {
        return this.vehiculos;
    }
    
    public void setVehiculos(Set vehiculos) {
        this.vehiculos = vehiculos;
    }




}


