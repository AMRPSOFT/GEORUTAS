
package dao;

import model.Detallerecorridoestudiante;
import org.hibernate.Session;

/**
 *
 * @author Alex Rodriguez
 */
public interface DetalleRecorridoEstudianteDao {
    public boolean insert(Session sesion, Detallerecorridoestudiante dtellaRecorridoEstudiante);
}
