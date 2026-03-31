package br.com.ecofeira.domain.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "rateio_itens")
data class RateioItem(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",

    @ManyToOne
    @JoinColumn(name = "item_pedido_id", nullable = false)
    val itemPedido: ItemPedido = ItemPedido(),

    @ManyToOne
    @JoinColumn(name = "estoque_banca_id", nullable = false)
    val estoqueBanca: EstoqueBanca = EstoqueBanca(),

    @Column(nullable = false)
    val quantidadeAlocada: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    val valorAlocado: BigDecimal = BigDecimal.ZERO
)