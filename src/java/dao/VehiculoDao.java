/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.Vehiculo;

/**
 *
 * @author Alex Rodriguez
 */
public interface VehiculoDao {
    public Vehiculo findByVehiculo(Vehiculo vehiculo);
    public List<Vehiculo> findAll();
    public boolean create(Vehiculo vehiculo);
    public boolean update(Vehiculo vehiculo);
    public boolean delete(Integer id);
}
