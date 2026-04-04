package br.edu.ufape.agrofeira.domain.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "feira_comerciantes")
data class FeiraComercianteEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",
    @ManyToOne
    @JoinColumn(name = "feira_id", nullable = false)
    val feira: Feira = Feira(),
    @ManyToOne
    @JoinColumn(name = "comerciante_id", nullable = false)
    val comerciante: Comerciante = Comerciante(),
    @Column(nullable = false)
    val totalVendido: BigDecimal = BigDecimal.ZERO,
    @OneToMany(mappedBy = "feiraComercianteEntity", cascade = [CascadeType.ALL])
    val estoques: MutableList<EstoqueBanca> = mutableListOf(),
)
