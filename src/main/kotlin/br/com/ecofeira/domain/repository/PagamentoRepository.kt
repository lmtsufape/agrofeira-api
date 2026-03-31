package br.com.ecofeira.domain.repository

import br.com.ecofeira.domain.entity.Pagamento
import org.springframework.data.jpa.repository.JpaRepository

interface PagamentoRepository : JpaRepository<Pagamento, String> {
    fun findByPedidoId(pedidoId: String): List<Pagamento>
}