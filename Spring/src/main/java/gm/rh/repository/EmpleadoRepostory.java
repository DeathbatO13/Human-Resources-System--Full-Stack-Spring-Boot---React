package gm.rh.repository;

import gm.rh.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Empleado} entity.
 * Provides CRUD operations and additional query methods through Spring Data JPA.
 * The primary key type is {@link Integer}.
 *
 * @see JpaRepository
 */
public interface EmpleadoRepostory extends JpaRepository<Empleado, Integer> { }
