package mx.ita.navdrawer;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link nav_detalles#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Producto {
    private String codigoprodcuto;
    private String nombreproducto;
    private String detallesproducto;
    private String stock;
    private String preciocompra;
    private String precioventa;

    public Producto() {
    }
    public String getCodigoprodcuto() {
        return codigoprodcuto;
    }

    public void setCodigoprodcuto(String codigoprodcuto) {
        this.codigoprodcuto = codigoprodcuto;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public String getDetallesproducto() {
        return detallesproducto;
    }

    public void setDetallesproducto(String detallesproducto) {
        this.detallesproducto = detallesproducto;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPreciocompra() {
        return preciocompra;
    }

    public void setPreciocompra(String preciocompra) {
        this.preciocompra = preciocompra;
    }

    public String getPrecioventa() {
        return precioventa;
    }

    public void setPrecioventa(String precioventa) {
        this.precioventa = precioventa;
    }

    @Override
    public String toString() {
        return nombreproducto;
    }
}
