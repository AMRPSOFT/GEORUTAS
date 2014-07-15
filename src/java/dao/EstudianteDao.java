/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.Estudiante;
import model.Recorrido;
import org.hibernate.Session;

/**
 *
 * @author Alex Rodriguez
 */
public interface EstudianteDao {
    
    public Estudiante findByEstudiante(Estudiante estudiante);
    //public List<Acudiente> selectItems();
    public List<Estudiante> findAll();
    public boolean create(Estudiante estudiante);
    public boolean update(Estudiante estudiante);
    public boolean delete(Integer id);
    public Estudiante getByIdEstdiante(Session sesion, Integer idestudiante);
}
