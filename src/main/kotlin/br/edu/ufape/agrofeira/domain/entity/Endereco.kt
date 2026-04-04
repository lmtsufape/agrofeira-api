package br.edu.ufape.agrofeira.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "enderecos")
data class Endereco(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    val cliente: Cliente = Cliente(),

    @ManyToOne
    @JoinColumn(name = "zona_entrega_id")
    val zonaEntrega: ZonaEntrega? = null,

    val cep: String? = null,
    val rua: String? = null,
    val numero: String? = null,
    val complemento: String? = null,
    val bairro: String? = null,
    val cidade: String? = null,
    val estado: String? = null,
    val observacao: String? = null
)