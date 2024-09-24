import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class InventorySystem {
    private List<Product> products;

    public InventorySystem() {
        products = new ArrayList<>();  // Inicializar la lista de productos
    }

    // Método para agregar productos
    public void addProduct(Scanner scanner) {
        System.out.print("Nombre del producto: ");
        String name = scanner.nextLine();
        System.out.print("Categoría: ");
        String category = scanner.nextLine();

        // Validar que la categoría no contenga números
        while (!category.matches("^[a-zA-Z]+$")) {
            System.out.println("La categoría no debe contener números. Inténtelo de nuevo.");
            System.out.print("Categoría: ");
            category = scanner.nextLine();
        }

        // Validar que la cantidad sea mayor que 0
        System.out.print("Cantidad: ");
        int quantity = scanner.nextInt();
        while (quantity <= 0) {
            System.out.println("La cantidad debe ser mayor que 0. Inténtelo de nuevo.");
            System.out.print("Cantidad: ");
            quantity = scanner.nextInt();
        }

        // Validar que el precio sea mayor que 0
        System.out.print("Precio: ");
        double price = scanner.nextDouble();
        while (price <= 0) {
            System.out.println("El precio debe ser mayor que 0. Inténtelo de nuevo.");
            System.out.print("Precio: ");
            price = scanner.nextDouble();
        }

        scanner.nextLine();  // Limpiar el buffer
        products.add(new Product(name, category, quantity, price));  // Agregar el producto a la lista
        System.out.println("Producto agregado exitosamente.");
    }

    // Método para modificar un producto existente
    public void modifyProduct(Scanner scanner) {
        boolean productFound = false;
        while (!productFound) {
            System.out.print("Ingrese el nombre del producto a modificar \n Escriba 'menu' para volver al menú: ");
            String name = scanner.nextLine();

            // Opción para volver al menú principal
            if (name.equalsIgnoreCase("menu")) {
                return;
            }

            Product product = findProductByName(name);
            if (product != null) {
                System.out.print("Nuevo nombre: ");
                product.setName(scanner.nextLine());

                System.out.print("Nueva categoría: ");
                String category = scanner.nextLine();
                while (!category.matches("^[a-zA-Z]+$")) {
                    System.out.println("La categoría no debe contener números. Inténtelo de nuevo.");
                    System.out.print("Nueva categoría: ");
                    category = scanner.nextLine();
                }
                product.setCategory(category);

                // Validar la nueva cantidad
                System.out.print("Nueva cantidad: ");
                int quantity = scanner.nextInt();
                while (quantity <= 0) {
                    System.out.println("La cantidad debe ser mayor que 0. Inténtelo de nuevo.");
                    System.out.print("Nueva cantidad: ");
                    quantity = scanner.nextInt();
                }
                product.setQuantity(quantity);

                // Validar el nuevo precio
                System.out.print("Nuevo precio: ");
                double price = scanner.nextDouble();
                while (price <= 0) {
                    System.out.println("El precio debe ser mayor que 0. Inténtelo de nuevo.");
                    System.out.print("Nuevo precio: ");
                    price = scanner.nextDouble();
                }
                product.setPrice(price);
                scanner.nextLine();  // Limpiar el buffer

                System.out.println("Producto modificado exitosamente.");
                productFound = true;
            } else {
                System.out.println("Producto no encontrado.");
                System.out.print("¿Desea intentar nuevamente? (s/n): ");
                String opcion = scanner.nextLine();
                if (!opcion.equalsIgnoreCase("s")) {
                    return;  // Salir al menú si no desea continuar buscando
                }
            }
        }
    }

    // Método para eliminar un producto
    public void deleteProduct(Scanner scanner) {
        System.out.print("Ingrese el nombre del producto a eliminar: ");
        String name = scanner.nextLine();
        Product product = findProductByName(name);
        if (product != null) {
            products.remove(product);
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    // Método para consultar la lista de productos
    public void viewProducts(Scanner scanner) {
        if (products.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            System.out.println("--- Lista de Productos ---");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    // Buscar un producto por su nombre
    private Product findProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    // Cargar los datos del inventario desde un archivo
    public void loadData() throws IOException {
        File file = new File("inventory.txt");
        if (!file.exists()) return;

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 4) {
                String name = parts[0];
                String category = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                double price = Double.parseDouble(parts[3]);
                products.add(new Product(name, category, quantity, price));
            }
        }
        reader.close();
    }

    // Guardar los datos del inventario en un archivo
    public void saveData() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.txt"));
        for (Product product : products) {
            writer.write(product.getName() + "," + product.getCategory() + "," +
                    product.getQuantity() + "," + product.getPrice() + "\n");
        }
        writer.close();
    }
}