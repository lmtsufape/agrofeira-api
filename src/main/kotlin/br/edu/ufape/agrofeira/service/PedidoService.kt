package br.edu.ufape.agrofeira.service

import br.edu.ufape.agrofeira.domain.entity.*
import br.edu.ufape.agrofeira.domain.enums.StatusPedido
import br.edu.ufape.agrofeira.domain.enums.TipoRetirada
import br.edu.ufape.agrofeira.domain.repository.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class PedidoService(
    private val pedidoRepository: PedidoRepository,
    private val feiraRepository: FeiraRepository,
    private val clienteRepository: ClienteRepository,
    private val comercianteRepository: ComercianteRepository,
    private val itemRepository: ItemRepository,
    private val estoqueBancaRepository: EstoqueBancaRepository,
    private val feiraComercianteRepository: FeiraComercianteRepository
) {
    fun listarTodos(): List<Pedido> = pedidoRepository.findAll()

    fun listarPorFeira(feiraId: String): List<Pedido> =
        pedidoRepository.findByFeiraId(feiraId)

    fun buscarPorId(id: String): Pedido =
        pedidoRepository.findById(id).orElseThrow { RuntimeException("Pedido não encontrado") }

    @Transactional
    fun criar(
        feiraId: String,
        clienteId: String,
        comercianteVendedorId: String,
        tipoRetirada: TipoRetirada,
        taxaEntrega: BigDecimal,
        itens: List<Pair<String, BigDecimal>>
    ): Pedido {
        val feira = feiraRepository.findById(feiraId)
            .orElseThrow { RuntimeException("Feira não encontrada") }
        val cliente = clienteRepository.findById(clienteId)
            .orElseThrow { RuntimeException("Cliente não encontrado") }
        val comercianteVendedor = comercianteRepository.findById(comercianteVendedorId)
            .orElseThrow { RuntimeException("Comerciante não encontrado") }

        val itensList = itens.map { (itemId, quantidade) ->
            val item = itemRepository.findById(itemId)
                .orElseThrow { RuntimeException("Item não encontrado") }
            Pair(item, quantidade)
        }

        val valorProdutos = itensList.sumOf { (item, quantidade) ->
            item.precoBase.multiply(quantidade)
        }
        val valorTotal = valorProdutos.add(taxaEntrega)

        // Primeiro salva o pedido sem itens
        val pedido = pedidoRepository.save(
            Pedido(
                feira = feira,
                cliente = cliente,
                comercianteVendedor = comercianteVendedor,
                tipoRetirada = tipoRetirada,
                taxaEntrega = taxaEntrega,
                valorProdutos = valorProdutos,
                valorTotal = valorTotal,
                status = StatusPedido.PENDENTE
            )
        )

        // Depois salva os itens com o pedido já persistido
        val itensPedido = itensList.map { (item, quantidade) ->
            ItemPedido(
                pedido = pedido,
                item = item,
                quantidade = quantidade,
                valorUnitario = item.precoBase,
                valorTotal = item.precoBase.multiply(quantidade)
            )
        }

        pedido.itens.addAll(itensPedido)
        return pedidoRepository.save(pedido)
    }

    // Retorna para cada item do pedido quais bancas têm disponível,
    // quanto cada uma tem e quanto cada uma já vendeu — para o admin decidir o rateio
    fun consultarDisponibilidadeParaRateio(pedidoId: String): Map<String, List<EstoqueBanca>> {
        val pedido = buscarPorId(pedidoId)
        return pedido.itens.associate { itemPedido ->
            itemPedido.item.nome to estoqueBancaRepository
                .findDisponiveisPorFeiraEItemOrdenadoPorMenosVendido(
                    pedido.feira.id,
                    itemPedido.item.id
                )
        }
    }

    // Admin confirma o rateio — recebe mapa de itemPedidoId -> lista de (estoqueBancaId, quantidade)
    @Transactional
    fun confirmarRateio(
        pedidoId: String,
        rateio: Map<String, List<Pair<String, BigDecimal>>>
    ): Pedido {
        val pedido = buscarPorId(pedidoId)

        pedido.itens.forEach { itemPedido ->
            val alocacoes = rateio[itemPedido.id] ?: return@forEach

            alocacoes.forEach { (estoqueBancaId, quantidade) ->
                val estoque = estoqueBancaRepository.findById(estoqueBancaId)
                    .orElseThrow { RuntimeException("Estoque não encontrado") }

                // Decrementa o estoque disponível
                estoqueBancaRepository.save(
                    estoque.copy(
                        quantidadeReservada = estoque.quantidadeReservada.add(quantidade)
                    )
                )

                // Atualiza o total vendido da banca
                val feiraComercianteEntity = estoque.feiraComercianteEntity
                val valorAlocado = estoque.item.precoBase.multiply(quantidade)
                feiraComercianteRepository.save(
                    feiraComercianteEntity.copy(
                        totalVendido = feiraComercianteEntity.totalVendido.add(valorAlocado)
                    )
                )

                // Registra o rateio
                itemPedido.rateios.add(
                    RateioItem(
                        itemPedido = itemPedido,
                        estoqueBanca = estoque,
                        quantidadeAlocada = quantidade,
                        valorAlocado = valorAlocado
                    )
                )
            }
        }

        return pedidoRepository.save(pedido.copy(status = StatusPedido.CONFIRMADO))
    }

    @Transactional
    fun atualizarStatus(id: String, status: StatusPedido): Pedido {
        val pedido = buscarPorId(id)
        return pedidoRepository.save(pedido.copy(status = status))
    }
}