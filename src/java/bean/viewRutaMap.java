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
        
    @PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel();
        
        //Shared coordinates
        LatLng coord1 = new LatLng(10.38814, -75.49164);
        LatLng coord2 = new LatLng(10.38782, -75.48545);
        LatLng coord3 = new LatLng(10.38659, -75.49600);
        LatLng coord4 = new LatLng(10.38318, -75.49600);
        LatLng coord5 = new LatLng(10.38028, -75.50001);
        LatLng coord6 = new LatLng(10.38183, -75.48909);
        LatLng coord7 = new LatLng(10.38496, -75.49018);
        LatLng coord8 = new LatLng(10.3825, -75.50365);
        
        //Basic marker
        
        simpleModel.addOverlay(new Marker(coord1, "Caracoles"));
        simpleModel.addOverlay(new Marker(coord2, "Blas de Lezo"));
        simpleModel.addOverlay(new Marker(coord3, "Almirante Colon"));
        simpleModel.addOverlay(new Marker(coord4, "El Campestre"));
        simpleModel.addOverlay(new Marker(coord5, "Vista Hermosa"));
        simpleModel.addOverlay(new Marker(coord6, "El Carmelo"));
        simpleModel.addOverlay(new Marker(coord7, "La Central"));
        simpleModel.addOverlay(new Marker(coord8, "Santa Clara"));
    }
  
    public MapModel getSimpleModel() {
        return simpleModel;
    }
}
