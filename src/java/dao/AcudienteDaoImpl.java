
package dao;

import java.util.List;
import model.Acudiente;
import model.Estudiante;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Alex Rodriguez
 */
public class AcudienteDaoImpl implements AcudienteDao{

    @Override
    public Acudiente findByAcudiente(Acudiente acudiente) {
        Acudiente model = null;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Acudiente WHERE idacudiente = '" + acudiente.getIdacudiente()+ "'";
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                model = (Acudiente) sesion.createQuery(sql).uniqueResult();
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
    public List<Acudiente> selectItems() {
        List<Acudiente> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Acudiente";
        Transaction transaction = sesion.beginTransaction();
            try {
                listado = (List<Acudiente>) sesion.createQuery(sql).list();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        return listado;
    }

    @Override
    public List<Acudiente> findAll() {
        List<Acudiente> listado = null;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Acudiente";
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                listado = (List<Acudiente>) sesion.createQuery(sql).list();
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
    public boolean create(Acudiente acudiente) {
        boolean flag = false;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                sesion.save(acudiente);
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
    public boolean update(Acudiente acudiente) {
        boolean flag = false;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                sesion.update(acudiente);
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
                Acudiente acudiente = (Acudiente) sesion.load(Acudiente.class, id);
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

    @Override
    public List<Estudiante> findByIdentificacion() {
        Acudiente acudiente = new Acudiente();
        List<Estudiante> listado = null;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "SELECT e.nombre, e.apellido FROM Estudiante as e inner join e.acudiente as a WHERE a.identificacion ='" + acudiente.getIdentificacion()+ "'";
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
    
}
