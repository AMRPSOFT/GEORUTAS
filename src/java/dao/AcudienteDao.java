/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.Acudiente;

/**
 *
 * @author Alex Rodriguez
 */
public interface AcudienteDao {
    
    public Acudiente findByAcudiente(Acudiente acudiente);
    public List<Acudiente> selectItems();
    public List<Acudiente> findAll();
    public boolean create(Acudiente extra);
    public boolean update(Acudiente extra);
    public boolean delete(Integer id);
    
}
