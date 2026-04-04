package br.edu.ufape.agrofeira.controller

import br.edu.ufape.agrofeira.domain.entity.Item
import br.edu.ufape.agrofeira.dto.ItemDTO
import br.edu.ufape.agrofeira.service.ItemService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/itens")
class ItemController(
    private val itemService: ItemService
) {
    @GetMapping
    fun listarTodos() = ResponseEntity.ok(
        itemService.listarTodos().map { ItemDTO.from(it) }
    )

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: String) =
        ResponseEntity.ok(ItemDTO.from(itemService.buscarPorId(id)))

    @PostMapping
    fun criar(@RequestBody item: Item) =
        ResponseEntity.ok(ItemDTO.from(itemService.criar(item)))

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: String, @RequestBody item: Item) =
        ResponseEntity.ok(ItemDTO.from(itemService.atualizar(id, item)))
}