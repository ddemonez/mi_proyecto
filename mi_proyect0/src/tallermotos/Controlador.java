/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallermotos;
import java.util.*;

public class Controlador {
    private Scanner sc = new Scanner(System.in);

    public void menu() {
        int opcion;
        do {
            System.out.println("===== TALLER DE MOTOS =====");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Registrar servicio");
            System.out.println("3. Mostrar datos");
            System.out.println("4. Actualizar registro");
            System.out.println("5. Eliminar registro");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
        case 1:
        registrarCliente();
        break;
        case 2:
        registrarServicio();
        break;
        case 3:
        listarDatos();
        break;
        case 4:
        actualizarRegistro();
        break;
        case 5:
        eliminarRegistro();
        break;
        case 6:
        System.out.println("Saliendo...");
        break;
        default:
        System.out.println("Opción inválida");
        break;
}
        } while (opcion != 5);
    }

    private void registrarCliente() {
        System.out.print("ID Cliente:");
        String id = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Teléfono: ");
        String tel = sc.nextLine();

        Cliente c = new Cliente(id, nombre, tel);
        ArchivoUtil.guardar("CLIENTE," + id + "," + nombre + "," + tel);
        System.out.println(" Cliente registrado.");
    }

    private void registrarServicio() {
        System.out.print("ID Servicio:");
        String id = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Precio: ");
        double precio = sc.nextDouble();
        sc.nextLine();
        System.out.print("Tipo de servicio: ");
        String tipo = sc.nextLine();

        ServicioDerivado s = new ServicioDerivado(id, nombre, precio, tipo);
        ArchivoUtil.guardar("SERVICIO," + id + "," + nombre + "," + precio + "," + tipo);
        System.out.println(" Servicio registrado.");
    }
    
    private void actualizarRegistro() {
        System.out.print("Ingrese ID a actualizar: ");
        String id = sc.nextLine();
        List<String> registros = ArchivoUtil.leer();
        boolean encontrado = false;
        for (int i = 0; i < registros.size(); i++) {
            if (registros.get(i).contains("," + id + ",")) {
                encontrado = true;

                System.out.println("Registro encontrado: " + registros.get(i));
                System.out.println("¿Es cliente o servicio?");
                String tipo = sc.nextLine().toUpperCase();

                if (tipo.equals("CLIENTE")) {
                    System.out.print("Nuevo nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Nuevo teléfono: ");
                    String tel = sc.nextLine();
                    registros.set(i, "CLIENTE," + id + "," + nombre + "," + tel);
                } else if (tipo.equals("SERVICIO")) {
                    System.out.print("Nuevo nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Nuevo precio: ");
                    double precio = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Nuevo tipo: ");
                    String tipoServicio = sc.nextLine();
                    registros.set(i, "SERVICIO," + id + "," + nombre + "," + precio + "," + tipoServicio);
                } else {
                    System.out.println(" Tipo no válido.");
                }
                break;
            }
        }

        if (encontrado) {
            ArchivoUtil.sobrescribir(registros);
            System.out.println(" Registro modificado.");
        } else {
            System.out.println(" No se encontró el ID.");
        }
    }


    private void listarDatos() {
        System.out.println("\n Datos en archivo:");
        for (String linea : ArchivoUtil.leer()) {
            System.out.println(linea);
        }
    }

    private void eliminarRegistro() {
        System.out.print("Ingrese ID a eliminar: ");
        String id = sc.nextLine();

        List<String> registros = ArchivoUtil.leer();
        registros.removeIf(linea -> linea.contains("," + id + ","));

        ArchivoUtil.sobrescribir(registros);
        System.out.println(" Registro eliminado (si existía).");
    }
}
