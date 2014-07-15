/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.Estudiante;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Alex Rodriguez
 */
public class EstudianteDaoImpl implements EstudianteDao{

    @Override
    public Estudiante findByEstudiante(Estudiante estudiante) {
        Estudiante model = null;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Estudiante WHERE idestudiante = '" + estudiante.getIdestudiante()+ "'";
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                model = (Estudiante) sesion.createQuery(sql).uniqueResult();
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
    public List<Estudiante> findAll() {
        List<Estudiante> listado = null;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Estudiante";
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                listado = (List<Estudiante>) sesion.createQuery(sql).list();
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
    public boolean create(Estudiante estudiante) {
        boolean flag = false;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                sesion.save(estudiante);
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
    public boolean update(Estudiante estudiante) {
        boolean flag = false;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                sesion.update(estudiante);
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
                Estudiante estudiante = (Estudiante) sesion.load(Estudiante.class, id);
                sesion.delete(estudiante);
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
    public Estudiante getByIdEstdiante(Session sesion, Integer idestudiante) {
        return (Estudiante) sesion.load(Estudiante.class, idestudiante);
    }

    @Override
    public Estudiante getByIdentificacion(Session sesion, Integer identificacion) {
        String hql="from Estudiante where identificacion=:identificacion";
        Query query=sesion.createQuery(hql);
        query.setParameter("identificacion", identificacion);
        
        return (Estudiante) query.uniqueResult();
    }

    @Override
    public List<Estudiante> getAll(Session sesion) throws Exception {
        return sesion.createCriteria(Estudiante.class).list();
    }
    
}
