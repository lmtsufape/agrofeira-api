package br.edu.ufape.agrofeira.controller

import br.edu.ufape.agrofeira.dto.PagamentoDTO
import br.edu.ufape.agrofeira.service.PagamentoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

data class RegistrarPagamentoRequest(
    val pedidoId: String,
    val metodo: String,
    val valor: BigDecimal,
)

@RestController
@RequestMapping("/api/pagamentos")
class PagamentoController(
    private val pagamentoService: PagamentoService,
) {
    @GetMapping("/pedido/{pedidoId}")
    fun listarPorPedido(
        @PathVariable pedidoId: String,
    ) = ResponseEntity.ok(
        pagamentoService.listarPorPedido(pedidoId).map { PagamentoDTO.from(it) },
    )

    @GetMapping("/{id}")
    fun buscarPorId(
        @PathVariable id: String,
    ) = ResponseEntity.ok(PagamentoDTO.from(pagamentoService.buscarPorId(id)))

    @PostMapping
    fun registrar(
        @RequestBody request: RegistrarPagamentoRequest,
    ) = ResponseEntity.ok(
        PagamentoDTO.from(
            pagamentoService.registrar(
                request.pedidoId,
                request.metodo,
                request.valor,
            ),
        ),
    )
}
