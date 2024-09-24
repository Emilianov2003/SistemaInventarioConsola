class Product {
private String name;
private String category;
private int quantity;
private double price;

// Constructor para inicializar los valores del producto
public Product(String name, String category, int quantity, double price) {
    this.name = name;
    this.category = category;
    this.quantity = quantity;
    this.price = price;
}

// Getters y setters para acceder y modificar los datos del producto
public String getName() { return name; }
public String getCategory() { return category; }
public int getQuantity() { return quantity; }
public double getPrice() { return price; }

public void setName(String name) { this.name = name; }
public void setCategory(String category) { this.category = category; }
public void setQuantity(int quantity) { this.quantity = quantity; }
public void setPrice(double price) { this.price = price; }

// Representación en cadena de un producto
@Override
public String toString() {
    return "Producto: " + name + ", Categoría: " + category +
            ", Cantidad: " + quantity + ", Precio: " + price;
}
}