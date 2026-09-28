public class MainBiblioteca {
    public static void main(String[] args) {

        Libro libroInvalido = new Libro("", "Autor Anónimo", "9780000000000", 1, 5000.0);
        System.out.println("Título guardado: \"" + libroInvalido.getTitulo() + "\"");
        // Otros datos inválidos: copias negativas y precio en cero
        Libro libroInvalido2 = new Libro("Libro de prueba", "Autor X", "9781111111111", -5, 0);
        System.out.println("Copias: " + libroInvalido2.getCopiasDisponibles()
                + " | Precio: $" + libroInvalido2.getPrecioReposicion());
        System.out.println();

        // Constructor de conveniencia (1 copia, precio por defecto)
        Libro libro1 = new Libro("Clean Code", "Robert C. Martin", "9780132350884");
        // Constructor canónico
        Libro libro2 = new Libro("Efectivo con Java", "Ana Restrepo", "9781234567897", 3, 22000.0);
        Libro libro3 = new Libro("Cien Años de Soledad", "Gabriel García Márquez", "9780307474728", 2, 18500.0);

        // Rechazo 2: setter con valor inválido
        boolean aceptado = libro1.setPrecioReposicion(-100.0);
        System.out.println("¿Se aceptó el precio -100.0? " + aceptado
                + " (se mantiene el precio anterior)");
        System.out.println("Precio actual de \"" + libro1.getTitulo() + "\": $"
                + libro1.getPrecioReposicion());
        System.out.println();

        // Fichas, cada libro en su propia variable
        libro1.mostrarFicha();
        libro2.mostrarFicha();
        libro3.mostrarFicha();

        // Agotar copias y comprobar el boolean de cada llamada
        boolean primerPrestamo = libro1.prestar();
        System.out.println("¿Primer préstamo aceptado? " + primerPrestamo);
        boolean segundoPrestamo = libro1.prestar();
        System.out.println("¿Segundo préstamo aceptado? " + segundoPrestamo);
        System.out.println("Copias de \"" + libro1.getTitulo() + "\": " + libro1.getCopiasDisponibles()
                + " (nunca negativo)");

        // Devolución y cambio de precio válido
        libro1.devolver();
        libro1.setPrecioReposicion(18000.0);

        System.out.println("Intentos de préstamo de \"" + libro1.getTitulo() + "\": "
                + libro1.getPrestamosHistoricos());
    }
}
