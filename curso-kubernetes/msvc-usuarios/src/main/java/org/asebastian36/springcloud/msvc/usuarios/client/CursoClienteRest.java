package org.asebastian36.springcloud.msvc.usuarios.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "msvc-cursos", url = "localhost:8002")
public interface CursoClienteRest {
    @DeleteMapping("/eliminar-curso-usuario/{id}")
    void eliminarCursoUsuarioporId(@PathVariable Long id);
}
