package br.edu.ufape.agrofeira.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "usuarios")
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",

    @Column(nullable = false, unique = true)
    val username: String = "",

    @Column(nullable = false)
    val senhaHash: String = "",

    @Column(nullable = false)
    val perfil: String = "ADMIN"
)