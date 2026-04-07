package br.edu.ufape.agrofeira.controller

import br.edu.ufape.agrofeira.domain.entity.Cliente
import br.edu.ufape.agrofeira.dto.ClienteDTO
import br.edu.ufape.agrofeira.service.ClienteService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/clientes")
class ClienteController(
    private val clienteService: ClienteService,
) {
    @GetMapping
    fun listarTodos() =
        ResponseEntity.ok(
            clienteService.listarTodos().map { ClienteDTO.from(it) },
        )

    @GetMapping("/{id}")
    fun buscarPorId(
        @PathVariable id: String,
    ) = ResponseEntity.ok(ClienteDTO.from(clienteService.buscarPorId(id)))

    @PostMapping
    fun criar(
        @RequestBody cliente: Cliente,
    ) = ResponseEntity.ok(ClienteDTO.from(clienteService.criar(cliente)))

    @PutMapping("/{id}")
    fun atualizar(
        @PathVariable id: String,
        @RequestBody cliente: Cliente,
    ) = ResponseEntity.ok(ClienteDTO.from(clienteService.atualizar(id, cliente)))
}
