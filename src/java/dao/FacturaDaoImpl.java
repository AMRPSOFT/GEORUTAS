/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.Factura;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Alex Rodriguez
 */
public class FacturaDaoImpl implements FacturaDao{

    @Override
    public Factura findByFactura(Factura factura) {
        Factura model = null;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Factura WHERE idfactura = '" + factura.getIdfactura()+ "'";
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                model = (Factura) sesion.createQuery(sql).uniqueResult();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        } finally {
            
        }
        return model;
    }

    @Override
    public List<Factura> findAll() {
        List<Factura> listado = null;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Factura";
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                listado = (List<Factura>) sesion.createQuery(sql).list();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        } finally {
            
        }
        return listado;
    }

    @Override
    public boolean insert(Session sesion, Factura factura) {
        sesion.save(factura);
        return true;
    }

    @Override
    public Factura getUltimoRegistro(Session sesion) throws Exception {
        String hql="FROM Factura ORDER BY idfactura DESC";
        Query query=sesion.createQuery(hql).setMaxResults(1);
        
        return (Factura) query.uniqueResult();
    }
    
}
