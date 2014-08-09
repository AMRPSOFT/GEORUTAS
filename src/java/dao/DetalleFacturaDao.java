/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import model.Detallefactura;
import org.hibernate.Session;

/**
 *
 * @author Alex Rodriguez
 */
public interface DetalleFacturaDao {
    public boolean insert(Session sesion, Detallefactura detalleFactura);
}
