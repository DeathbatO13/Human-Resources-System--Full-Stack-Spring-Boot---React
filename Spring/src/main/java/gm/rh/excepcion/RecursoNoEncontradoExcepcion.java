package gm.rh.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom unchecked exception thrown when a requested resource cannot be found.
 * Mapped to HTTP status 404 (Not Found) via {@link ResponseStatus}.
 * Typically used in service or controller layers when an entity (e.g., an employee)
 * with the provided identifier does not exist in the database.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecursoNoEncontradoExcepcion extends RuntimeException {

    /**
     * Constructs a new resource-not-found exception with the specified detail message.
     *
     * @param mensaje the detail message explaining why the resource was not found
     */
    public RecursoNoEncontradoExcepcion(String mensaje) {
        super(mensaje);
    }
}
