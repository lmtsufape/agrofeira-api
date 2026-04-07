package br.edu.ufape.agrofeira.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "clientes")
data class Cliente(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",
    @Column(nullable = false)
    val nome: String = "",
    val telefone: String? = null,
    val descricao: String? = null,
    @Column(nullable = false)
    val criadoEm: LocalDateTime = LocalDateTime.now(),
    @OneToMany(mappedBy = "cliente", cascade = [CascadeType.ALL])
    val enderecos: MutableList<Endereco> = mutableListOf(),
)
