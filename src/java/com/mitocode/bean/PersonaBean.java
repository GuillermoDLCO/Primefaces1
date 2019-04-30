/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitocode.bean;

import com.mitocode.dao.PersonaDAO;
import com.mitocode.model.Persona;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Guillermo
 */
@ManagedBean
@ViewScoped
public class PersonaBean {

    private Persona persona = new Persona();
    private List<Persona> listPersonas;
    private String accion;

    public String getAccion() {
        this.limpiar();
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getListPersonas() {
        return listPersonas;
    }

    public void setListPersonas(List<Persona> listPersonas) {
        this.listPersonas = listPersonas;
    }

    public boolean isPostBack() {
        boolean rpta;
        rpta = FacesContext.getCurrentInstance().isPostback();
        return rpta;
    }

    public void operar() throws Exception {
        switch (accion) {
            case "Registrar":
                this.registrar();
                this.limpiar();
                break;
            case "Modificar":
                this.modificar();
                this.limpiar();
                break;
        }
    }

    public void limpiar() {
        persona.setCodigo(0);
        persona.setNombre("");
        persona.setSexo("");
    }

    private void registrar() throws Exception {
        PersonaDAO dao;

        try {
            dao = new PersonaDAO();
            dao.registrar(persona);
            this.listar("V");
        } catch (Exception e) {
            throw e;
        }
    }

    public void listar(String valor) throws Exception {
        PersonaDAO dao;

        try {
            if (valor.equals("F")) {
                if (isPostBack() == false) {
                    dao = new PersonaDAO();
                    listPersonas = dao.listar();
                }

            } else {
                dao = new PersonaDAO();
                listPersonas = dao.listar();
            }
            
        } catch (Exception e) {
            throw e;
        }
    }

    public void leerID(Persona per) throws Exception {
        PersonaDAO dao;
        Persona temp;
        try {
            dao = new PersonaDAO();
            temp = dao.leerID(per);

            if (temp != null) {
                this.persona = temp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void modificar() throws Exception {
        PersonaDAO dao;

        try {
            dao = new PersonaDAO();
            dao.modificar(persona);
            this.listar("V");
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(Persona persona) throws Exception {
        PersonaDAO dao;

        try {
            dao = new PersonaDAO();
            dao.eliminar(persona);
            this.listar("V");
        } catch (Exception e) {
            throw e;
        }
    }
}
