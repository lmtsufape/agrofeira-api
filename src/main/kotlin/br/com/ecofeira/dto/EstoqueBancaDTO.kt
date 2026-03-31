package br.com.ecofeira.dto

import br.com.ecofeira.domain.entity.EstoqueBanca
import java.math.BigDecimal

data class EstoqueBancaDTO(
    val id: String,
    val comercianteId: String,
    val comercianteNome: String,
    val itemId: String,
    val itemNome: String,
    val quantidadeDisponivel: BigDecimal,
    val quantidadeReservada: BigDecimal,
    val totalVendidoBanca: BigDecimal
) {
    companion object {
        fun from(estoque: EstoqueBanca) = EstoqueBancaDTO(
            id = estoque.id,
            comercianteId = estoque.feiraComercianteEntity.comerciante.id,
            comercianteNome = estoque.feiraComercianteEntity.comerciante.nome,
            itemId = estoque.item.id,
            itemNome = estoque.item.nome,
            quantidadeDisponivel = estoque.quantidadeDisponivel,
            quantidadeReservada = estoque.quantidadeReservada,
            totalVendidoBanca = estoque.feiraComercianteEntity.totalVendido
        )
    }
}