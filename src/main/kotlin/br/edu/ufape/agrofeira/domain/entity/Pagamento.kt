package br.edu.ufape.agrofeira.domain.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "pagamentos")
data class Pagamento(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    val pedido: Pedido = Pedido(),
    @Column(nullable = false)
    val valor: BigDecimal = BigDecimal.ZERO,
    val metodo: String? = null,
    @Column(nullable = false)
    val status: String = "PENDENTE",
    val pagoEm: LocalDateTime? = null,
)
