/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import model.Recorrido;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Alex Rodriguez
 */
public class RecorridoDaoImpl implements RecorridoDao{

    @Override
    public boolean insert(Session sesion, Recorrido recorrido) throws Exception {
        sesion.save(recorrido);
        return true;
    }

    @Override
    public Recorrido getUltimoRegistro(Session sesion) throws Exception {
        String hql="from recorrido order by idrecorrido desc";
        Query query=sesion.createQuery(hql).setMaxResults(1);
        
        return (Recorrido) query.uniqueResult();
    }
    
}
