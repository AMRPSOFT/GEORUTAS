/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.Factura;
import org.hibernate.Session;

/**
 *
 * @author Alex Rodriguez
 */
public interface FacturaDao {
    public Factura findByFactura(Factura factura);
    public List<Factura> findAll();
    public boolean insert(Session sesion, Factura factura) throws Exception;
    public Factura getUltimoRegistro(Session sesion) throws Exception;
}
