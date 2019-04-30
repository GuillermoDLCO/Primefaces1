/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitocode.dao;

import com.mitocode.model.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guillermo
 */
public class PersonaDAO extends DAO {

    public void registrar(Persona per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.getConn().prepareStatement("INSERT INTO persona (nombre,sexo) values(?,?)");
            st.setString(1, per.getNombre());
            st.setString(2, per.getSexo());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
    }

    public List<Persona> listar() throws Exception {
        List<Persona> lista;
        ResultSet rs;
        try {
            this.conectar();
            PreparedStatement st = this.getConn().prepareStatement("SELECT codigo, nombre,sexo from persona");
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Persona per = new Persona();
                per.setCodigo(rs.getInt(1));
                per.setNombre(rs.getString(2));
                per.setSexo(rs.getString(3));
                lista.add(per);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }

    public Persona leerID(Persona persona) {
        Persona per = null;
        ResultSet rs;
        try {
            this.conectar();
            PreparedStatement st = this.getConn().prepareStatement("SELECT codigo, nombre, sexo FROM persona WHERE codigo=?");
            st.setInt(1, persona.getCodigo());
            rs = st.executeQuery();

            while (rs.next()) {
                per = new Persona();
                per.setCodigo(rs.getInt(1));
                per.setNombre(rs.getString(2));
                per.setSexo(rs.getString(3));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
        return per;
    }

    public void modificar(Persona per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.getConn().prepareStatement("UPDATE persona SET nombre = ?, sexo= ? WHERE codigo = ?");
            st.setString(1, per.getNombre());
            st.setString(2, per.getSexo());
            st.setInt(3, per.getCodigo());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
    }

    public void eliminar(Persona per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.getConn().prepareStatement("DELETE FROM persona WHERE codigo = ?");
            st.setInt(1, per.getCodigo());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
    }
}
