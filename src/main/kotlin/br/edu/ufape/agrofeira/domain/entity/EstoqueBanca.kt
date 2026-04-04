package br.edu.ufape.agrofeira.domain.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "estoque_bancas")
data class EstoqueBanca(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",
    @ManyToOne
    @JoinColumn(name = "feira_comerciante_id", nullable = false)
    val feiraComercianteEntity: FeiraComercianteEntity = FeiraComercianteEntity(),
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    val item: Item = Item(),
    @Column(nullable = false)
    val quantidadeDisponivel: BigDecimal = BigDecimal.ZERO,
    @Column(nullable = false)
    val quantidadeReservada: BigDecimal = BigDecimal.ZERO,
)
