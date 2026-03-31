package br.com.ecofeira.domain.entity

import br.com.ecofeira.domain.enums.StatusRepasse
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "repasses")
data class Repasse(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",

    @ManyToOne
    @JoinColumn(name = "feira_comerciante_id", nullable = false)
    val feiraComercianteEntity: FeiraComercianteEntity = FeiraComercianteEntity(),

    @Column(nullable = false)
    val valorBruto: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    val taxaAssociacao: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    val valorLiquido: BigDecimal = BigDecimal.ZERO,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: StatusRepasse = StatusRepasse.PENDENTE,

    val repassadoEm: LocalDateTime? = null
)