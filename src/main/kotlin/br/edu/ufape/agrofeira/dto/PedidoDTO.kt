package br.edu.ufape.agrofeira.dto

import br.edu.ufape.agrofeira.domain.entity.Pedido
import br.edu.ufape.agrofeira.domain.enums.StatusPedido
import br.edu.ufape.agrofeira.domain.enums.TipoRetirada
import java.math.BigDecimal
import java.time.LocalDateTime

data class PedidoDTO(
    val id: String,
    val feiraId: String,
    val feiraData: LocalDateTime,
    val clienteId: String,
    val clienteNome: String,
    val comercianteVendedorId: String,
    val comercianteVendedorNome: String,
    val status: StatusPedido,
    val tipoRetirada: TipoRetirada,
    val taxaEntrega: BigDecimal,
    val valorProdutos: BigDecimal,
    val valorTotal: BigDecimal,
    val itens: List<ItemPedidoDTO>,
) {
    companion object {
        fun from(pedido: Pedido) =
            PedidoDTO(
                id = pedido.id,
                feiraId = pedido.feira.id,
                feiraData = pedido.feira.dataHora,
                clienteId = pedido.cliente.id,
                clienteNome = pedido.cliente.nome,
                comercianteVendedorId = pedido.comercianteVendedor.id,
                comercianteVendedorNome = pedido.comercianteVendedor.nome,
                status = pedido.status,
                tipoRetirada = pedido.tipoRetirada,
                taxaEntrega = pedido.taxaEntrega,
                valorProdutos = pedido.valorProdutos,
                valorTotal = pedido.valorTotal,
                itens = pedido.itens.map { ItemPedidoDTO.from(it) },
            )
    }
}
