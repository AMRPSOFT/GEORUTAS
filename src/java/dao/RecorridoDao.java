/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import model.Recorrido;
import org.hibernate.Session;

/**
 *
 * @author Alex Rodriguez
 */
public interface RecorridoDao {
    public boolean insert(Session sesion, Recorrido recorrido) throws Exception;
    public Recorrido getUltimoRegistro(Session sesion) throws Exception;
}
