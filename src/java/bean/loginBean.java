/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import java.awt.event.ActionEvent;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Usuario;
import org.primefaces.context.RequestContext;
import util.MyUtil;

/**
 *
 * @author Alex Rodriguez
 */
@Named(value = "loginBean")
@SessionScoped
public class loginBean implements Serializable {

    private Usuario usuario;
    private UsuarioDao usuarioDao;
    
    public loginBean() {
        this.usuarioDao = new UsuarioDaoImpl();
        if (this.usuario == null) {
            this.usuario = new Usuario();
        }
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void login(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg;
        boolean loggedIn;
        String ruta = "";
        this.usuario = this.usuarioDao.login(this.usuario);
        if (this.usuario != null) {
            loggedIn = true;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("UserName", this.usuario.getUserName());
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", this.usuario.getUserName());
            ruta = MyUtil.basepathlogin()+"views/inicio.xhtml";
        } else {
            loggedIn = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Credenciales Invalidas");
            if (this.usuario == null) {
                this.usuario = new Usuario();
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("ruta", ruta);
    }
    
    public void logout(){
        String ruta = MyUtil.basepathlogin()+"login.xhtml";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        HttpSession sesion = (HttpSession) facesContext.getExternalContext().getSession(false);
        sesion.invalidate();
        
        context.addCallbackParam("loggetOut", true);
        context.addCallbackParam("ruta", ruta);
    }
}
