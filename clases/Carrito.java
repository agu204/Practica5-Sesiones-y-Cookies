package clases;

import java.util.ArrayList;

public class Carrito {

  private ArrayList<ElementoDeCarrito> elementos = new ArrayList<ElementoDeCarrito>();

  public Carrito() {
  }
  
  //deserializador
  public Carrito(String contenido) {
      if (contenido.length() > 0) {
          String[] deserializador = contenido.split("/");
          for (int i = 0; i < deserializador.length; i += 5) {
              int cantidad = Integer.parseInt(deserializador[i]);
              int codigo = Integer.parseInt(deserializador[i + 1]);
              String nombre = deserializador[i + 2];
              double precio = Double.parseDouble(deserializador[i + 3]);
              String imagen = deserializador[i + 4];

              Producto producto = new Producto(codigo, nombre, precio, imagen);
              ElementoDeCarrito elementoCarrito = new ElementoDeCarrito(producto, cantidad);

              elementos.add(elementoCarrito);
          }
      }
  }

  public Carrito(ArrayList<ElementoDeCarrito> elementos) {
    this.elementos = elementos;
  }

  public ArrayList<ElementoDeCarrito> getElementos() {
    return elementos;
  }

  public boolean existeElementoConCodigo(int codigo) {
    return this.posicionElementoConCodigo(codigo) != -1;
  }

  public void meteProductoConCodigo(int codigo) {
    if (this.existeElementoConCodigo(codigo)) {
      elementos.get(posicionElementoConCodigo(codigo)).incrementaCantidad(1);
    } else {
      Catalogo catalogo = new Catalogo();
      catalogo.cargaDatos();
      elementos.add(new ElementoDeCarrito(catalogo.productoConCodigo(codigo), 1));
    }
  }

  public void eliminaProductoConCodigo(int codigo) {
    if (existeElementoConCodigo(codigo)) {
      int i = 0;
      int posicion = 0;
      for (ElementoDeCarrito elemento : elementos) {
        if (elemento.getProducto().getCodigo() == codigo) {
          posicion = i;
        }
        i++;
      }
      elementos.remove(posicion);
    }
  }

  private int posicionElementoConCodigo(int codigo) {
    int i = 0;
    for (ElementoDeCarrito elemento : elementos) {
      if (elemento.getProducto().getCodigo() == codigo) {
        return i;
      }
      i++;
    }
    return -1;
  }

// serializador
    public String serializaCarrito() {
        String serializador = "";
        for (ElementoDeCarrito elemento : elementos) {
            Producto producto = elemento.getProducto();
            serializador += elemento.getCantidad() + "/"
                    + producto.getCodigo() + "/"
                    + producto.getNombre() + "/"
                    + producto.getPrecio() + "/"
                    + producto.getImagen() + "/";
        }
        return serializador;
    }
}
