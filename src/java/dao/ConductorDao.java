/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.Conductor;

/**
 *
 * @author Alex Rodriguez
 */
public interface ConductorDao {
    
    public Conductor findByConductor(Conductor conductor);
    public List<Conductor> selectItems();
    public List<Conductor> findAll();
    public boolean create(Conductor conductor);
    public boolean update(Conductor conductor);
    public boolean delete(Integer id);
    
}
