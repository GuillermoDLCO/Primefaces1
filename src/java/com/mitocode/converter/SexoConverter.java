/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitocode.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Guillermo
 */

@FacesConverter("sexoConverter")
public class SexoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        String sexo="";
        
        if(value!=null){
            sexo=(String) value;
            switch(sexo){
                case "M":
                    sexo="MASCULINO";
                    break;
                case "F":
                    sexo="FEMENINO";
                    break;
            }
        }
        return sexo;
    }
    
    
    
}
