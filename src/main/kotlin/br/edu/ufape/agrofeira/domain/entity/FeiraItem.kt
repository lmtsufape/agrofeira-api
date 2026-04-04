package br.edu.ufape.agrofeira.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "feira_itens")
data class FeiraItem(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",

    @ManyToOne
    @JoinColumn(name = "feira_id", nullable = false)
    val feira: Feira = Feira(),

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    val item: Item = Item()
)