/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Alex Rodriguez
 */
@Named(value = "loginBean")
@SessionScoped
public class loginBean implements Serializable {

    
    public loginBean() {
    }
    
}
