/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitocode.dao;

import com.mitocode.model.DetalleVenta;
import com.mitocode.model.Venta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Guillermo
 */
public class VentaDAO extends DAO {

    public void registrar(Venta venta, List<DetalleVenta> lista) throws Exception {
        try {
            this.conectar();
            
            this.getConn().setAutoCommit(false);
            
            PreparedStatement st = this.getConn().prepareStatement("INSERT INTO venta (fecha, codPersona, monto) values(NOW(),?,?)");
            //st.setDate(1, venta.getFecha());
            st.setInt(1, venta.getPersona().getCodigo());
            st.setDouble(2, venta.getMonto());
            System.out.println(""+venta.getFecha()+ venta.getPersona().getCodigo()+venta.getMonto());
            st.executeUpdate();
            st.close();
            
            PreparedStatement st2 = this.getConn().prepareStatement("SELECT LAST_INSERT_ID() FROM venta limit 1");
            ResultSet rs;
            int codVenta=0;
            rs= st2.executeQuery();
            while (rs.next()) {                
                codVenta =rs.getInt(1);
            }
            rs.close();
            
            for (DetalleVenta det : lista) {
                PreparedStatement st3 = this.getConn().prepareStatement("INSERT INTO detalleventa (codVenta, codProducto, cantidad) values(?,?,?)");
                st3.setInt(1, codVenta);
                st3.setInt(2, det.getProducto().getCodigo());
                st3.setInt(3, det.getCantidad());
                st3.executeUpdate();
                st3.close();
            }
            
            this.getConn().commit();

        } catch (Exception e) {
            
            System.out.println(""+venta.getFecha()+ venta.getPersona().getCodigo()+venta.getMonto());
            throw e;
        } finally {
            this.cerrar();
            System.out.println(""+venta.getFecha()+ venta.getPersona().getCodigo()+venta.getMonto());
        }
    }
}
