/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.Ruta;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author Alex Rodriguez
 */
@Named(value = "viewRutaMap")
@SessionScoped
public class viewRutaMap implements Serializable {
    
    private MapModel simpleModel;
    private Ruta ruta;
    
    @PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel();
        ruta = new Ruta();
        //Shared coordinates
        LatLng coord = new LatLng(ruta.getLatitud(), ruta.getLongitud());
        
          
        //Basic marker
        simpleModel.addOverlay(new Marker(coord, ruta.getNomBarrio()));
       
    }
  
    public MapModel getSimpleModel() {
        return simpleModel;
    }
}
