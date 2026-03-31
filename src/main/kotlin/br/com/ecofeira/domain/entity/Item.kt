package br.com.ecofeira.domain.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "itens")
data class Item(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",

    @Column(nullable = false)
    val nome: String = "",

    @Column(nullable = false)
    val unidadeMedida: String = "",

    @Column(nullable = false)
    val precoBase: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    val criadoEm: LocalDateTime = LocalDateTime.now()
)