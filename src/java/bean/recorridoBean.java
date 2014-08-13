/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import dao.EstudianteDaoImpl;
import dao.RecorridoDao;
import dao.RecorridoDaoImpl;
import dao.VehiculoDao;
import dao.VehiculoDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import model.Recorrido;

/**
 *
 * @author Alex Rodriguez
 */
@Named(value = "recorridoBean")
@RequestScoped
public class recorridoBean {

    private Recorrido selectedRecorrido;
    private List<Recorrido> recorridos;
    private List<SelectItem> selectOneItemsRecorrido;
    
    public recorridoBean() {
        this.selectedRecorrido = new Recorrido();
        this.recorridos = new ArrayList<Recorrido>();
    }

    public List<SelectItem> getSelectOneItemsRecorrido() {
        this.selectOneItemsRecorrido = new ArrayList<SelectItem>();
        RecorridoDao recorridoDao = new RecorridoDaoImpl();
        recorridos = recorridoDao.selectItems();
        for (Recorrido recorrido : recorridos) {
            SelectItem selectItem = new SelectItem(recorrido.getIdrecorrido(),"");
            this.selectOneItemsRecorrido.add(selectItem);
        }
        return selectOneItemsRecorrido;
    }
    
    
}
