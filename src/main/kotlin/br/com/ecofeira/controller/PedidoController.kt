package br.com.ecofeira.controller

import br.com.ecofeira.domain.enums.StatusPedido
import br.com.ecofeira.domain.enums.TipoRetirada
import br.com.ecofeira.dto.EstoqueBancaDTO
import br.com.ecofeira.dto.PedidoDTO
import br.com.ecofeira.service.PedidoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

data class CriarPedidoRequest(
    val feiraId: String,
    val clienteId: String,
    val comercianteVendedorId: String,
    val tipoRetirada: TipoRetirada,
    val taxaEntrega: BigDecimal = BigDecimal.ZERO,
    val itens: List<ItemPedidoRequest>
)

data class ItemPedidoRequest(
    val itemId: String,
    val quantidade: BigDecimal
)

data class RateioRequest(
    val rateio: Map<String, List<AlocacaoRequest>>
)

data class AlocacaoRequest(
    val estoqueBancaId: String,
    val quantidade: BigDecimal
)

@RestController
@RequestMapping("/api/pedidos")
class PedidoController(
    private val pedidoService: PedidoService
) {
    @GetMapping
    fun listarTodos() = ResponseEntity.ok(
        pedidoService.listarTodos().map { PedidoDTO.from(it) }
    )

    @GetMapping("/feira/{feiraId}")
    fun listarPorFeira(@PathVariable feiraId: String) =
        ResponseEntity.ok(pedidoService.listarPorFeira(feiraId).map { PedidoDTO.from(it) })

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: String) =
        ResponseEntity.ok(PedidoDTO.from(pedidoService.buscarPorId(id)))

    @PostMapping
    fun criar(@RequestBody request: CriarPedidoRequest) =
        ResponseEntity.ok(PedidoDTO.from(
            pedidoService.criar(
                feiraId = request.feiraId,
                clienteId = request.clienteId,
                comercianteVendedorId = request.comercianteVendedorId,
                tipoRetirada = request.tipoRetirada,
                taxaEntrega = request.taxaEntrega,
                itens = request.itens.map { it.itemId to it.quantidade }
            )
        ))

    @GetMapping("/{id}/rateio")
    fun consultarRateio(@PathVariable id: String) =
        ResponseEntity.ok(
            pedidoService.consultarDisponibilidadeParaRateio(id)
                .mapValues { (_, estoques) -> estoques.map { EstoqueBancaDTO.from(it) } }
        )

    @PostMapping("/{id}/rateio")
    fun confirmarRateio(@PathVariable id: String, @RequestBody request: RateioRequest) =
        ResponseEntity.ok(PedidoDTO.from(
            pedidoService.confirmarRateio(
                pedidoId = id,
                rateio = request.rateio.mapValues { (_, alocacoes) ->
                    alocacoes.map { it.estoqueBancaId to it.quantidade }
                }
            )
        ))

    @PatchMapping("/{id}/status")
    fun atualizarStatus(@PathVariable id: String, @RequestBody status: StatusPedido) =
        ResponseEntity.ok(PedidoDTO.from(pedidoService.atualizarStatus(id, status)))
}