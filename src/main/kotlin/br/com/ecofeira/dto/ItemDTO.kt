package br.com.ecofeira.dto

import br.com.ecofeira.domain.entity.Item
import java.math.BigDecimal

data class ItemDTO(
    val id: String,
    val nome: String,
    val unidadeMedida: String,
    val precoBase: BigDecimal
) {
    companion object {
        fun from(item: Item) = ItemDTO(
            id = item.id,
            nome = item.nome,
            unidadeMedida = item.unidadeMedida,
            precoBase = item.precoBase
        )
    }
}