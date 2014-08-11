/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.Detallefactura;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Alex Rodriguez
 */
public class DetalleFacturaDaoImpl implements DetalleFacturaDao{

    @Override
    public boolean insert(Session sesion, Detallefactura detalleFactura) {
        sesion.save(detalleFactura);
        return true;
    }

    @Override
    public List<Detallefactura> findAll() {
        List<Detallefactura> listado = null;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Detallefactura";
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                listado = (List<Detallefactura>) sesion.createQuery(sql).list();
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
    public boolean mostrarPdf(Integer identificacion) {
        boolean flag = false;
        Detallefactura model = null;
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Detallefactura WHERE identificacion = '" + model.getIdentificacion()+ "'";
        try {
            final Transaction transaction = sesion.beginTransaction();
            try {
                model = (Detallefactura) sesion.createQuery(sql).uniqueResult();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        } finally {
            
        }
        return flag=true;
    }
}
    

