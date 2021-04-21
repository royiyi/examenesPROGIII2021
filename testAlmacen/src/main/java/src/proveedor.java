/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Royer
 */
public class proveedor implements Serializable {
    static final long serialVersionUID=43L;
    private String razonSocial;
    private String direccion;
    private int nroTelefono;
    private List<producto> products;

    public proveedor() {
        
        products = new ArrayList<>();
    }
    ///GETTERS AND SETTERS
    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNroTelefono() {
        return nroTelefono;
    }

    public void setNroTelefono(int nroTelefono) {
        this.nroTelefono = nroTelefono;
    }

    public List<producto> getProducts() {
        return products;
    }

    public void setProducts(List<producto> products) {
        this.products = products;
    }
    
    
    
}
