package model;
// Generated 6/06/2014 11:50:31 AM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Acudiente generated by hbm2java
 */

public class Acudiente  implements java.io.Serializable {


     private Integer idacudiente;
     private String identificacion;
     private String nombre;
     private String apellido;
     private String direccion;
     private String telefono;
     private String email;
     private String sexo;
     private Set estudiantes = new HashSet(0);

    public Acudiente() {
        this.idacudiente=0;
    }

	
    public Acudiente(String identificacion, String nombre, String apellido, String direccion, String telefono, String email, String sexo) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.sexo = sexo;
    }
    public Acudiente(String identificacion, String nombre, String apellido, String direccion, String telefono, String email, String sexo, Set estudiantes) {
       this.identificacion = identificacion;
       this.nombre = nombre;
       this.apellido = apellido;
       this.direccion = direccion;
       this.telefono = telefono;
       this.email = email;
       this.sexo = sexo;
       this.estudiantes = estudiantes;
    }
   
    public Integer getIdacudiente() {
        return this.idacudiente;
    }
    
    public void setIdacudiente(Integer idacudiente) {
        this.idacudiente = idacudiente;
    }

    
    public String getIdentificacion() {
        return this.identificacion;
    }
    
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getSexo() {
        return this.sexo;
    }
    
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Set getEstudiantes() {
        return this.estudiantes;
    }
    
    public void setEstudiantes(Set estudiantes) {
        this.estudiantes = estudiantes;
    }

}


