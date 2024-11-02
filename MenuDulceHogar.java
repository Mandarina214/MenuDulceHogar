package dulcehogar;

import java.util.ArrayList; // Importo clase ArrayList
import java.util.Scanner; // Importo clase Scanner
import java.text.DecimalFormat; // Importo clase DecimalFormat

public class DulceHogar {
    public static void main(String[] menu) {
        Scanner teclado = new Scanner(System.in);
        int opcion;

        // Bucle del menú interactivo
        do {
            System.out.println("\n   Menu Sistema Dulce Hogar   ");
            System.out.println("\n1. Registrar socio");
            System.out.println("2. Ver datos del socio");
            System.out.println("3. Cancelar cuota");
            System.out.println("4. Consultar cuota cancelada");
            System.out.println("5. Consultar total de cuotas pagadas por el socio");
            System.out.println("6. Salir del programa");
            System.out.print("\nSeleccione una opcion: ");
            opcion = teclado.nextInt();

            // Se ejecuta instrucción switch con 6 casos para seleccionar una opción del menú
            switch(opcion) {
                case 1:
                    registrarSocio();
                    break;
                case 2:
                    verDatosSocio();
                    break;
                case 3:
                    cancelarCuota();
                    break;
                case 4:
                    consultarCuotaCancelada();
                    break;
                case 5:
                    consultarTotalCuotasPagadas();
                    break;
                case 6:
                    System.out.println("\nHa seleccionado la opcion 6. Salir del programa");
                    break;
                default:
                    System.out.println("Opción invalida");
            }
        } while (opcion != 6);
        teclado.close();  // Cerrar el Scanner
    }

    // Métodos de cada opción
    
    public static void registrarSocio() {
        System.out.println("\nHa seleccionado la opcion 1. Registrar socio\n");
        Scanner teclado = new Scanner(System.in);
       
        System.out.print("Ingrese número de socio: ");
        int numeroSocio = teclado.nextInt();
        teclado.nextLine(); // Limpiar el buffer

        System.out.print("Ingrese RUT (Ej: 22.342.753-0): ");
        String rut = teclado.nextLine();
        while (!validarRut(rut)) {
            System.out.print("RUT inválido. Ingrese nuevamente: ");
            rut = teclado.nextLine();
        }

        System.out.print("Ingrese nombre: ");
        String nombre = teclado.nextLine();

        System.out.print("Ingrese apellido paterno: ");
        String apellidoPaterno = teclado.nextLine();

        System.out.print("Ingrese apellido materno: ");
        String apellidoMaterno = teclado.nextLine();

        System.out.print("Ingrese correo: ");
        String correo = teclado.nextLine();

        System.out.print("Ingrese domicilio: ");
        String domicilio = teclado.nextLine();

        System.out.print("Ingrese región: ");
        String region = teclado.nextLine();

        System.out.print("Ingrese ciudad: ");
        String ciudad = teclado.nextLine();

        System.out.print("Ingrese comuna: ");
        String comuna = teclado.nextLine();

        System.out.print("Ingrese teléfono: ");
        String telefono = teclado.nextLine();

        // Crear objeto Socio
        Socio nuevoSocio = new Socio(numeroSocio, rut, nombre, apellidoPaterno, apellidoMaterno, correo, domicilio, region, ciudad, comuna, telefono);

        // Imprimir mensaje de éxito
        System.out.println("¡Cliente registrado exitosamente!");
    }

    // Método para validar el RUT
    public static boolean validarRut(String rut) {
        return rut.length() >= 11 && rut.length() <= 12; // Validación de longitud
    }
    
    public static void verDatosSocio() {
        System.out.println("\nHa seleccionado la opcion 2. Ver datos del socio\n");
    }

    public static void cancelarCuota() {
        System.out.println("\nHa seleccionado la opcion 3. Cancelar cuota\n");
        
        Scanner teclado = new Scanner(System.in);
        
        System.out.print("Ingrese el valor pactado de la cuota: ");
        double valorPactado = teclado.nextDouble();
        
        Cuota cuota = new Cuota(valorPactado);
        
        System.out.print("Ingrese el valor de la cuota a cancelar: ");
        double valorCuota = teclado.nextDouble();
        
        try {
            cuota.setValorCuota(valorCuota);
            // Aquí puedes agregar lógica adicional para manejar la cuota cancelada.
            
            // Ejemplo de impresión del valor de la cuota cancelada
            System.out.println("Cuota cancelada con éxito: " + cuota.getValorCuota());
            
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void consultarCuotaCancelada() {
        System.out.println("\nHa seleccionado la opcion 4. Consultar cuota cancelada\n");
    }

    public static void consultarTotalCuotasPagadas() {
        System.out.println("\nHa seleccionado la opcion 5. Consultar total de cuotas pagadas por el socio\n");
    }
}

// Clase Cuota
class Cuota {
    private double valorCuota; // Valor de la cuota
    private double valorPactado; // Valor pactado de la cuota

    public Cuota(double valorPactado) {
        this.valorPactado = valorPactado;
    }

    public void setValorCuota(double valorCuota) {
        if (valorCuota < 0) {
            throw new IllegalArgumentException("El valor de la cuota no puede ser negativo.");
        }
        
        if (valorCuota != valorPactado) {
            throw new IllegalArgumentException("El valor ingresado debe ser igual al valor pactado: " + formatValor(valorPactado));
        } else {
            this.valorCuota = valorCuota;
            System.out.println("Cuota registrada: " + formatValor(valorCuota));
        }
    }

    public double getValorCuota() {
        return valorCuota;
    }

    public double getValorPactado() {
        return valorPactado;
    }

    private String formatValor(double valor) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(valor);
    }
}