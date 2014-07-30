/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.Estudiante;
import model.Factura;
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
    public List<Estudiante> selectItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public boolean create(Factura factura) {
        boolean flag = false;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                sesion.save(factura);
                transaction.commit();
                flag = true;
            } catch (Exception e) {
                flag = false;
                transaction.rollback();
                throw e;
            }
        } finally {
            
        }
        return flag;
    }

    @Override
    public boolean update(Factura factura) {
        boolean flag = false;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                sesion.update(factura);
                transaction.commit();
                flag = true;
            } catch (Exception e) {
                flag = false;
                transaction.rollback();
                throw e;
            }
        } finally {
            
        }
        return flag;
    }

    @Override
    public boolean delete(Integer id) {
        boolean flag = false;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                Factura factura = (Factura) sesion.load(Factura.class, id);
                sesion.delete(factura);
                transaction.commit();
                flag = true;
            } catch (Exception e) {
                flag = false;
                transaction.rollback();
                throw e;
            }
        } finally {
            
        }
        return flag;
    }
    
}
