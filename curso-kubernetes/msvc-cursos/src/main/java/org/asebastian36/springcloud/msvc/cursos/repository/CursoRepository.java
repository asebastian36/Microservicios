package org.asebastian36.springcloud.msvc.cursos.repository;

import org.asebastian36.springcloud.msvc.cursos.models.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    @Modifying
    @Query("delete from CursoUsuario cu where cu.usuarioId = ?1")
    void eliminarCursoUsuarioporId(Long id);
}
