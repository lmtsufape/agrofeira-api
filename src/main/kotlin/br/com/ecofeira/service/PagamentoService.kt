package br.com.ecofeira.service

import br.com.ecofeira.domain.entity.Pagamento
import br.com.ecofeira.domain.repository.PagamentoRepository
import br.com.ecofeira.domain.repository.PedidoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class PagamentoService(
    private val pagamentoRepository: PagamentoRepository,
    private val pedidoRepository: PedidoRepository
) {
    fun listarPorPedido(pedidoId: String): List<Pagamento> =
        pagamentoRepository.findByPedidoId(pedidoId)

    fun buscarPorId(id: String): Pagamento =
        pagamentoRepository.findById(id).orElseThrow { RuntimeException("Pagamento não encontrado") }

    @Transactional
    fun registrar(pedidoId: String, metodo: String, valor: BigDecimal): Pagamento {
        val pedido = pedidoRepository.findById(pedidoId)
            .orElseThrow { RuntimeException("Pedido não encontrado") }

        return pagamentoRepository.save(
            Pagamento(
                pedido = pedido,
                valor = valor,
                metodo = metodo,
                status = "PAGO",
                pagoEm = LocalDateTime.now()
            )
        )
    }
}