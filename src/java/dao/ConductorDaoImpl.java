
package dao;

import java.util.List;
import model.Conductor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Alex Rodriguez
 */
public class ConductorDaoImpl implements ConductorDao{

    @Override
    public Conductor findByConductor(Conductor conductor) {
        Conductor model = null;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Conductor WHERE idconductor = '" + conductor.getIdconductor()+ "'";
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                model = (Conductor) sesion.createQuery(sql).uniqueResult();
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
    public List<Conductor> selectItems() {
        List<Conductor> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Conductor";
        Transaction transaction = sesion.beginTransaction();
            try {
                listado = (List<Conductor>) sesion.createQuery(sql).list();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        return listado;
    }

    @Override
    public List<Conductor> findAll() {
        List<Conductor> listado = null;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Conductor";
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                listado = (List<Conductor>) sesion.createQuery(sql).list();
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
    public boolean create(Conductor conductor) {
        boolean flag = false;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                sesion.save(conductor);
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
    public boolean update(Conductor conductor) {
        boolean flag = false;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                sesion.update(conductor);
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
                Conductor acudiente = (Conductor) sesion.load(Conductor.class, id);
                sesion.delete(acudiente);
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
