package br.edu.ufape.agrofeira.domain.repository

import br.edu.ufape.agrofeira.domain.entity.Pedido
import org.springframework.data.jpa.repository.JpaRepository

interface PedidoRepository : JpaRepository<Pedido, String> {
    fun findByFeiraId(feiraId: String): List<Pedido>
    fun findByClienteId(clienteId: String): List<Pedido>
    fun findByComercianteVendedorId(comercianteId: String): List<Pedido>
}