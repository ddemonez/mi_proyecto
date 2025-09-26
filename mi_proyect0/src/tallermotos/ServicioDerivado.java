/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallermotos;
public class ServicioDerivado extends Servicio {
    private String tipoServicio;

    public ServicioDerivado(String id, String nombre, double precio, String tipoServicio) {
        super(id, nombre, precio);
        this.tipoServicio = tipoServicio;// son los servicios especificos que ofrece el taller
    }

    public String getTipoServicio() { return tipoServicio; }
    public void setTipoServicio(String tipoServicio) { this.tipoServicio = tipoServicio; }

    @Override // hijo
    public void mostrarInfo() {/// se cumple el polimorfisomo
        System.out.println("Servicio: " + getNombre() + " - Tipo: " + tipoServicio + 
                           " - Precio: Q" + getPrecio());
    }
}
