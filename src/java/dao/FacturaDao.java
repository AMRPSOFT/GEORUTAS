/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.Estudiante;
import model.Factura;

/**
 *
 * @author Alex Rodriguez
 */
public interface FacturaDao {
    public Factura findByFactura(Factura factura);
    public List<Estudiante> selectItems();
    public List<Factura> findAll();
    public boolean create(Factura factura);
    public boolean update(Factura factura);
    public boolean delete(Integer id);
}
