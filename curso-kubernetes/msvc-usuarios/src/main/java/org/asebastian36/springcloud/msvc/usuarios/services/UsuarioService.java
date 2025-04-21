package org.asebastian36.springcloud.msvc.usuarios.services;

import org.asebastian36.springcloud.msvc.usuarios.models.entity.Usuario;
import java.util.*;

public interface UsuarioService {    List<Usuario> listar();
    Optional<Usuario> porId(Long id);
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);
    Optional<Usuario> porEmail(String email);
    boolean existsByEmail(String email);
    List<Usuario> getByIds(Iterable<Long> ids);
}
