/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitocode.dao;

import com.mitocode.model.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guillermo
 */
public class ProductoDAO extends DAO {

    public void registrar(Producto pro) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.getConn().prepareStatement("INSERT INTO producto (nombre,precio) values(?,?)");
            st.setString(1, pro.getNombre());
            st.setDouble(2, pro.getPrecio());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
    }

    public List<Producto> listar() throws Exception {
        List<Producto> lista;
        ResultSet rs;
        try {
            this.conectar();
            PreparedStatement st = this.getConn().prepareStatement("SELECT codigo, nombre,precio from producto");
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Producto per = new Producto();
                per.setCodigo(rs.getInt(1));
                per.setNombre(rs.getString(2));
                per.setPrecio(rs.getDouble(3));
                lista.add(per);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }

    public Producto leerID(Producto pro) {
        Producto per = null;
        ResultSet rs;
        try {
            this.conectar();
            PreparedStatement st = this.getConn().prepareStatement("SELECT codigo, nombre, precio FROM producto WHERE codigo=?");
            st.setInt(1, pro.getCodigo());
            rs = st.executeQuery();

            while (rs.next()) {
                per = new Producto();
                per.setCodigo(rs.getInt(1));
                per.setNombre(rs.getString(2));
                per.setPrecio(rs.getDouble(3));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
        return per;
    }

    public void modificar(Producto pro) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.getConn().prepareStatement("UPDATE producto SET nombre = ?, sexo= ? WHERE codigo = ?");
            st.setString(1, pro.getNombre());
            st.setDouble(2, pro.getPrecio());
            st.setInt(3, pro.getCodigo());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
    }

    public void eliminar(Producto per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.getConn().prepareStatement("DELETE FROM producto WHERE codigo = ?");
            st.setInt(1, per.getCodigo());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
    }
}
