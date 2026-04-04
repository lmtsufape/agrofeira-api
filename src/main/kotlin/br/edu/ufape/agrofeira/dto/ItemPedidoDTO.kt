package br.edu.ufape.agrofeira.dto

import br.edu.ufape.agrofeira.domain.entity.ItemPedido
import java.math.BigDecimal

data class ItemPedidoDTO(
    val id: String,
    val itemId: String,
    val itemNome: String,
    val quantidade: BigDecimal,
    val valorUnitario: BigDecimal,
    val valorTotal: BigDecimal
) {
    companion object {
        fun from(itemPedido: ItemPedido) = ItemPedidoDTO(
            id = itemPedido.id,
            itemId = itemPedido.item.id,
            itemNome = itemPedido.item.nome,
            quantidade = itemPedido.quantidade,
            valorUnitario = itemPedido.valorUnitario,
            valorTotal = itemPedido.valorTotal
        )
    }
}