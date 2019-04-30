/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitocode.bean;

import com.mitocode.dao.ProductoDAO;
import com.mitocode.model.Producto;
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
public class ProductoBean {

    private Producto producto = new Producto();
    private List<Producto> listProductos;
    private String accion;

    public String getAccion() {
        this.limpiar();
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getListProductos() {
        return listProductos;
    }

    public void setListProductos(List<Producto> listProductos) {
        this.listProductos = listProductos;
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
    
    public void limpiar(){
        producto.setCodigo(0);
        producto.setNombre("");
        producto.setPrecio(0);
    }
    
    public boolean isPostBack() {
        boolean rpta;
        rpta = FacesContext.getCurrentInstance().isPostback();
        return rpta;
    }
    
    private void registrar() throws Exception {
        ProductoDAO dao;

        try {
            dao = new ProductoDAO();
            dao.registrar(producto);
            this.listar("V");
        } catch (Exception e) {
            throw e;
        }
    }

    public void listar(String valor) throws Exception {
        ProductoDAO dao;

        try {
            if (valor.equals("F")) {
                if (isPostBack() == false) {
                    dao = new ProductoDAO();
                    listProductos = dao.listar();
                }

            } else {
                dao = new ProductoDAO();
                listProductos = dao.listar();
            }
            
        } catch (Exception e) {
            throw e;
        }
    }

    public void leerID(Producto per) throws Exception {
        ProductoDAO dao;
        Producto temp;
        try {
            dao = new ProductoDAO();
            temp = dao.leerID(per);

            if (temp != null) {
                this.producto = temp;
                this.accion="Modificar";
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void modificar() throws Exception {
        ProductoDAO dao;

        try {
            dao = new ProductoDAO();
            dao.modificar(producto);
            this.listar("V");
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(Producto producto) throws Exception {
        ProductoDAO dao;

        try {
            dao = new ProductoDAO();
            dao.eliminar(producto);
            this.listar("V");
        } catch (Exception e) {
            throw e;
        }
    }
}
