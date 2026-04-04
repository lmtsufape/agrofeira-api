package br.edu.ufape.agrofeira.domain.entity

import br.edu.ufape.agrofeira.domain.enums.StatusPedido
import br.edu.ufape.agrofeira.domain.enums.TipoRetirada
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "pedidos")
data class Pedido(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",

    @ManyToOne
    @JoinColumn(name = "feira_id", nullable = false)
    val feira: Feira = Feira(),

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    val cliente: Cliente = Cliente(),

    @ManyToOne
    @JoinColumn(name = "comerciante_vendedor_id", nullable = false)
    val comercianteVendedor: Comerciante = Comerciante(),

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: StatusPedido = StatusPedido.PENDENTE,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val tipoRetirada: TipoRetirada = TipoRetirada.LOCAL,

    @Column(nullable = false)
    val taxaEntrega: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    val valorProdutos: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    val valorTotal: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    val criadoEm: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "pedido", cascade = [CascadeType.ALL])
    val itens: MutableList<ItemPedido> = mutableListOf()
)