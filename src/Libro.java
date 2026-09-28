// final: Libro modela un ejemplar concreto del catálogo con reglas propias (validación,
// préstamos); no está pensada para extenderse, ya que una subclase podría romper esas reglas.
public final class Libro {
    private static final double PRECIO_POR_DEFECTO = 15000.0;

    private final String titulo;
    private final String autor;
    private final String isbn;
    private int copiasDisponibles;
    private double precioReposicion;
    private int prestamosHistoricos;

    // Constructor canónico: único lugar donde vive la validación completa.
    public Libro(String titulo, String autor, String isbn, int copiasDisponibles, double precioReposicion) {
        if (titulo == null || titulo.isBlank()) {
            System.out.println("Título inválido, se usó \"Sin título\" por defecto.");
            this.titulo = "Sin título";
        } else {
            this.titulo = titulo;
        }

        if (autor == null || autor.isBlank()) {
            System.out.println("Autor inválido, se usó \"Autor desconocido\" por defecto.");
            this.autor = "Autor desconocido";
        } else {
            this.autor = autor;
        }

        if (isbn == null || isbn.isBlank()) {
            System.out.println("ISBN inválido, se usó \"ISBN pendiente\" por defecto.");
            this.isbn = "ISBN pendiente";
        } else {
            this.isbn = isbn;
        }

        if (copiasDisponibles < 0) {
            System.out.println("Copias disponibles negativas (" + copiasDisponibles
                    + "), se usó 0 por defecto.");
            this.copiasDisponibles = 0;
        } else {
            this.copiasDisponibles = copiasDisponibles;
        }

        // La regla del precio vive en un único validador privado, compartido con el setter.
        if (esPrecioValido(precioReposicion)) {
            this.precioReposicion = precioReposicion;
        } else {
            System.out.println("Precio de reposición inválido (" + precioReposicion
                    + "): debe ser mayor a 0. Se usó $" + PRECIO_POR_DEFECTO + " por defecto.");
            this.precioReposicion = PRECIO_POR_DEFECTO;
        }
    }

    // Constructor de conveniencia: delega en el canónico, sin repetir validaciones.
    public Libro(String titulo, String autor, String isbn) {
        this(titulo, autor, isbn, 1, PRECIO_POR_DEFECTO);
    }

    private static boolean esPrecioValido(double precio) {
        return precio > 0;
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getCopiasDisponibles() {
        return copiasDisponibles;
    }

    public double getPrecioReposicion() {
        return precioReposicion;
    }

    public int getPrestamosHistoricos() {
        return prestamosHistoricos;
    }

    // Setter validado: rechaza sin tocar el precio anterior.
    public boolean setPrecioReposicion(double precio) {
        if (!esPrecioValido(precio)) {
            return false;
        }
        double precioAnterior = this.precioReposicion;
        this.precioReposicion = precio;
        System.out.println("Precio de reposición actualizado de \"" + titulo + "\": $"
                + precioAnterior + " -> $" + this.precioReposicion);
        return true;
    }

    // Operaciones de dominio (no hay setter de copias)
    public boolean prestar() {
        prestamosHistoricos++; // cuenta todos los intentos, se acepten o no
        if (copiasDisponibles > 0) {
            copiasDisponibles--;
            System.out.println("Préstamo registrado: \"" + titulo + "\". Copias disponibles: "
                    + copiasDisponibles);
            return true;
        }
        System.out.println("Error: no hay copias disponibles de \"" + titulo + "\" para prestar.");
        return false;
    }

    public void devolver() {
        copiasDisponibles++;
        System.out.println("Devolución registrada: \"" + titulo + "\". Copias disponibles: "
                + copiasDisponibles);
    }

    public void mostrarFicha() {
        System.out.println("=== Ficha de libro ===");
        System.out.println("Título:  " + titulo);
        System.out.println("Autor:   " + autor);
        System.out.println("ISBN:    " + isbn);
        System.out.println("Copias disponibles: " + copiasDisponibles);
        System.out.println("Precio de reposición: $" + precioReposicion);
        System.out.println("=======================");
    }
}