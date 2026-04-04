package br.edu.ufape.agrofeira.domain.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "itens_pedido")
data class ItemPedido(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    val pedido: Pedido = Pedido(),

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    val item: Item = Item(),

    @Column(nullable = false)
    val quantidade: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    val valorUnitario: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    val valorTotal: BigDecimal = BigDecimal.ZERO,

    @OneToMany(mappedBy = "itemPedido", cascade = [CascadeType.ALL])
    val rateios: MutableList<RateioItem> = mutableListOf()
)