package org.asebastian36.springcloud.msvc.cursos.services;

import org.asebastian36.springcloud.msvc.cursos.models.Usuario;
import org.asebastian36.springcloud.msvc.cursos.models.entity.Curso;
import java.util.*;

public interface CursoService {
    List<Curso> listar();
    Optional<Curso> porId(Long id);

    //  logica de negocio hacia el cliente
    Optional<Curso> porIdConUsuarios(Long
                                             id);
    void eliminarCursoUsuarioporId(Long id);

    void eliminar(Long id);
    Curso guardar(Curso curso);

    //  logica de negocio hacia otro servicio
    Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId);
    Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId);
    Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId);

}