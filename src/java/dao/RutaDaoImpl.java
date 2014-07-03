/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.Ruta;
import model.Vehiculo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Alex Rodriguez
 */
public class RutaDaoImpl implements RutaDao{

    @Override
    public Ruta findByRuta(Ruta ruta) {
        Ruta model = null;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Ruta WHERE idruta = '" + ruta.getIdruta()+ "'";
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                model = (Ruta) sesion.createQuery(sql).uniqueResult();
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
    public List<Ruta> selectItems() {
        List<Ruta> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Ruta";
        Transaction transaction = sesion.beginTransaction();
            try {
                listado = (List<Ruta>) sesion.createQuery(sql).list();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        return listado;
    }

    @Override
    public List<Ruta> findAll() {
        List<Ruta> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Ruta";
        Transaction transaction = sesion.beginTransaction();
            try {
                listado = (List<Ruta>) sesion.createQuery(sql).list();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        return listado;
    }

    @Override
    public boolean create(Ruta ruta) {
        boolean flag = false;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                sesion.save(ruta);
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
    public boolean update(Ruta ruta) {
        boolean flag = false;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                sesion.update(ruta);
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
                Ruta ruta = (Ruta) sesion.load(Ruta.class, id);
                sesion.delete(ruta);
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
    public List<Vehiculo> findByBarrio() {
        Ruta ruta = new Ruta();
        List<Vehiculo> listado = null;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "SELECT v.NroRuta, v.Placa FROM Vehiculo as v inner join v.ruta as r WHERE r.NomBarrio ='" + ruta.getNomBarrio()+ "'";
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                listado = (List<Vehiculo>) sesion.createQuery(sql).list();
                transaction.commit();
                
            } catch (Exception e) {
                
                transaction.rollback();
                throw e;
            }
        } finally {
            
        }
        return listado;
    }
    
}
