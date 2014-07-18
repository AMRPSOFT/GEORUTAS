
package model;

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
     private int numestudiante;
          
    
     public Detallerecorridoestudiante(){
     }

    public Detallerecorridoestudiante(Estudiante estudiante, Recorrido recorrido, String nombreestudiante, String apellidoestudiante, String direccionestudiante, String colegio, String jornada, int numestudiante) {
        this.estudiante = estudiante;
        this.recorrido = recorrido;
        this.nombreestudiante = nombreestudiante;
        this.apellidoestudiante = apellidoestudiante;
        this.direccionestudiante = direccionestudiante;
        this.colegio = colegio;
        this.jornada = jornada;
        this.numestudiante = numestudiante;
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

    public String getNombreestudiante() {
        return nombreestudiante;
    }

    public void setNombreestudiante(String nombreestudiante) {
        this.nombreestudiante = nombreestudiante;
    }

    public String getApellidoestudiante() {
        return apellidoestudiante;
    }

    public void setApellidoestudiante(String apellidoestudiante) {
        this.apellidoestudiante = apellidoestudiante;
    }

    public String getDireccionestudiante() {
        return direccionestudiante;
    }

    public void setDireccionestudiante(String direccionestudiante) {
        this.direccionestudiante = direccionestudiante;
    }

    public String getColegio() {
        return colegio;
    }

    public void setColegio(String colegio) {
        this.colegio = colegio;
    }

    public int getNumestudiante() {
        return numestudiante;
    }

    public void setNumestudiante(int numestudiante) {
        this.numestudiante = numestudiante;
    }
    
    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }
     
}
