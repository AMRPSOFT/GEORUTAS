/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.Ruta;
import model.Vehiculo;

/**
 *
 * @author Alex Rodriguez
 */
public interface RutaDao {
    public Ruta findByRuta(Ruta ruta);
    public List<Ruta> selectItems();
    public List<Ruta> findAll();
    public boolean create(Ruta ruta);
    public boolean update(Ruta ruta);
    public boolean delete(Integer id);
    public List<Vehiculo> findByBarrio();
}
