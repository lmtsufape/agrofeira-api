package br.edu.ufape.agrofeira.domain.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "zonas_entrega")
data class ZonaEntrega(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",
    @Column(nullable = false)
    val bairro: String = "",
    val regiao: String? = null,
    @Column(nullable = false)
    val taxa: BigDecimal = BigDecimal.ZERO,
)
