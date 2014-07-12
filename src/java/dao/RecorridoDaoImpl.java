
package dao;

import model.Recorrido;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Alex Rodriguez
 */
public class RecorridoDaoImpl  implements RecorridoDao{

    @Override
    public boolean insert(Recorrido recorrido) {
        boolean flag = false;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                sesion.save(recorrido);
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
