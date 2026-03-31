package br.com.ecofeira.controller

import br.com.ecofeira.domain.entity.Comerciante
import br.com.ecofeira.dto.ComercianteDTO
import br.com.ecofeira.service.ComercianteService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/comerciantes")
class ComercianteController(
    private val comercianteService: ComercianteService
) {
    @GetMapping
    fun listarTodos() = ResponseEntity.ok(
        comercianteService.listarTodos().map { ComercianteDTO.from(it) }
    )

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: String) =
        ResponseEntity.ok(ComercianteDTO.from(comercianteService.buscarPorId(id)))

    @PostMapping
    fun criar(@RequestBody comerciante: Comerciante) =
        ResponseEntity.ok(ComercianteDTO.from(comercianteService.criar(comerciante)))

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: String, @RequestBody comerciante: Comerciante) =
        ResponseEntity.ok(ComercianteDTO.from(comercianteService.atualizar(id, comerciante)))

    @PutMapping("/{id}/itens")
    fun atualizarItens(@PathVariable id: String, @RequestBody itemIds: List<String>) =
        ResponseEntity.ok(ComercianteDTO.from(comercianteService.atualizarItens(id, itemIds)))
}