package org.asebastian36.springcloud.msvc.cursos.repository;

import org.asebastian36.springcloud.msvc.cursos.models.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
