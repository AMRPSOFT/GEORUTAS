
package dao;

import java.util.List;
import model.Recorrido;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

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
        String hql="FROM Recorrido ORDER BY idrecorrido DESC";
        Query query=sesion.createQuery(hql).setMaxResults(1);
        
        return (Recorrido) query.uniqueResult();
    }

    @Override
    public List<Recorrido> selectItems() {
        List<Recorrido> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Recorrido";
        Transaction transaction = sesion.beginTransaction();
            try {
                listado = (List<Recorrido>) sesion.createQuery(sql).list();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        return listado;
    }

}
