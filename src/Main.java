import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventorySystem inventorySystem = new InventorySystem();  // Crear el sistema de inventario
        try {
            inventorySystem.loadData();  // Cargar los datos almacenados previamente
        } catch (IOException e) {
            System.out.println("Error al cargar los datos del inventario.");  // Manejar error al cargar datos
        }

        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Modificar Producto");
            System.out.println("3. Eliminar Producto");
            System.out.println("4. Consultar Productos");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    inventorySystem.addProduct(scanner);  // Agregar un producto
                    break;
                case 2:
                    inventorySystem.modifyProduct(scanner);  // Modificar un producto
                    break;
                case 3:
                    inventorySystem.deleteProduct(scanner);  // Eliminar un producto
                    break;
                case 4:
                    inventorySystem.viewProducts(scanner);  // Consultar lista de productos
                    break;
                case 5:
                    try {
                        inventorySystem.saveData();  // Guardar los datos antes de salir
                        System.out.println("Datos guardados correctamente.");
                    } catch (IOException e) {
                        System.out.println("Error al guardar los datos.");
                    }
                    System.out.println("Saliendo del sistema.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);
        scanner.close();
    }
}
