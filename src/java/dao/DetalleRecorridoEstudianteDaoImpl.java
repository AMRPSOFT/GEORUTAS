
package dao;

import java.util.List;
import model.Detallerecorridoestudiante;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Alex Rodriguez
 */
public class DetalleRecorridoEstudianteDaoImpl  implements DetalleRecorridoEstudianteDao{

    @Override
    public boolean insert(Session sesion, Detallerecorridoestudiante detallaRecorridoEstudiante) {
        sesion.save(detallaRecorridoEstudiante);
        return true;
    }

    @Override
    public List<Detallerecorridoestudiante> findAll() {
        List<Detallerecorridoestudiante> listado = null;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Detallerecorridoestudiante";
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                listado = (List<Detallerecorridoestudiante>) sesion.createQuery(sql).list();
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
