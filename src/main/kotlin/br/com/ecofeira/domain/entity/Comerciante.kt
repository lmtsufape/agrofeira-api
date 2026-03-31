package br.com.ecofeira.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "comerciantes")
data class Comerciante(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",

    @Column(nullable = false)
    val nome: String = "",

    val telefone: String? = null,

    val descricao: String? = null,

    @Column(nullable = false)
    val criadoEm: LocalDateTime = LocalDateTime.now(),

    @ManyToMany
    @JoinTable(
        name = "comerciante_item",
        joinColumns = [JoinColumn(name = "comerciante_id")],
        inverseJoinColumns = [JoinColumn(name = "item_id")]
    )
    val itens: MutableList<Item> = mutableListOf()
)