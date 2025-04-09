package org.asebastian36.springcloud.msvc.cursos.services;

import org.asebastian36.springcloud.msvc.cursos.entity.Curso;
import java.util.*;

public interface CursoService {
    List<Curso> listar();
    Optional<Curso> porId(Long id);
    void eliminar(Long id);
    Curso guardar(Curso curso);
}