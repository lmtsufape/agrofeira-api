package br.com.ecofeira.domain.entity

import br.com.ecofeira.domain.enums.StatusFeira
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "feiras")
data class Feira(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",

    @Column(nullable = false)
    val dataHora: LocalDateTime = LocalDateTime.now(),

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: StatusFeira = StatusFeira.AGENDADA,

    @Column(nullable = false)
    val criadoEm: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "feira", cascade = [CascadeType.ALL])
    val comerciantes: MutableList<FeiraComercianteEntity> = mutableListOf(),

    @OneToMany(mappedBy = "feira", cascade = [CascadeType.ALL])
    val itens: MutableList<FeiraItem> = mutableListOf()
)