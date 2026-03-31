package br.com.ecofeira.controller

import br.com.ecofeira.dto.EstoqueBancaDTO
import br.com.ecofeira.service.EstoqueBancaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

data class DeclararEstoqueRequest(
    val feiraComercianteId: String,
    val itemId: String,
    val quantidade: BigDecimal
)

data class AtualizarEstoqueRequest(
    val quantidade: BigDecimal
)

@RestController
@RequestMapping("/api/estoque-bancas")
class EstoqueBancaController(
    private val estoqueBancaService: EstoqueBancaService
) {
    @GetMapping("/feira/{feiraId}")
    fun listarPorFeira(@PathVariable feiraId: String) =
        ResponseEntity.ok(
            estoqueBancaService.listarPorFeira(feiraId).map { EstoqueBancaDTO.from(it) }
        )

    @GetMapping("/feira/{feiraId}/item/{itemId}")
    fun listarPorFeiraEItem(
        @PathVariable feiraId: String,
        @PathVariable itemId: String
    ) = ResponseEntity.ok(
        estoqueBancaService.listarPorFeiraEItem(feiraId, itemId).map { EstoqueBancaDTO.from(it) }
    )

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: String) =
        ResponseEntity.ok(EstoqueBancaDTO.from(estoqueBancaService.buscarPorId(id)))

    @PostMapping
    fun declarar(@RequestBody request: DeclararEstoqueRequest) =
        ResponseEntity.ok(EstoqueBancaDTO.from(
            estoqueBancaService.declarar(
                request.feiraComercianteId,
                request.itemId,
                request.quantidade
            )
        ))

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: String, @RequestBody request: AtualizarEstoqueRequest) =
        ResponseEntity.ok(EstoqueBancaDTO.from(estoqueBancaService.atualizar(id, request.quantidade)))
}