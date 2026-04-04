package br.edu.ufape.agrofeira.dto

import br.edu.ufape.agrofeira.domain.entity.Pagamento
import java.math.BigDecimal
import java.time.LocalDateTime

data class PagamentoDTO(
    val id: String,
    val pedidoId: String,
    val clienteNome: String,
    val valor: BigDecimal,
    val metodo: String?,
    val status: String,
    val pagoEm: LocalDateTime?,
) {
    companion object {
        fun from(pagamento: Pagamento) =
            PagamentoDTO(
                id = pagamento.id,
                pedidoId = pagamento.pedido.id,
                clienteNome = pagamento.pedido.cliente.nome,
                valor = pagamento.valor,
                metodo = pagamento.metodo,
                status = pagamento.status,
                pagoEm = pagamento.pagoEm,
            )
    }
}
