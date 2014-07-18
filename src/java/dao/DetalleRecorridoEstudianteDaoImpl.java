
package dao;

import model.Detallerecorridoestudiante;
import org.hibernate.Session;

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
}
