package org.asebastian36.springcloud.msvc.usuarios.repositories;

import org.asebastian36.springcloud.msvc.usuarios.models.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    //  opcion 1
    Optional<Usuario> findByEmail(String email);

    //  opcion 2
    @Query("select u from Usuario u where u.email = ?1")
    Optional<Usuario> paraEmail(String email);

    //  opcion 3
    boolean existsByEmail(String email);
}