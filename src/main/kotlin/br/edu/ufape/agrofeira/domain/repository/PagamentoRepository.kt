package br.edu.ufape.agrofeira.domain.repository

import br.edu.ufape.agrofeira.domain.entity.Pagamento
import org.springframework.data.jpa.repository.JpaRepository

interface PagamentoRepository : JpaRepository<Pagamento, String> {
    fun findByPedidoId(pedidoId: String): List<Pagamento>
}
