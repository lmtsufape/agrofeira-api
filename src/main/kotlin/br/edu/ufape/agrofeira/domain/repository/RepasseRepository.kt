package br.edu.ufape.agrofeira.domain.repository

import br.edu.ufape.agrofeira.domain.entity.Repasse
import br.edu.ufape.agrofeira.domain.enums.StatusRepasse
import org.springframework.data.jpa.repository.JpaRepository

interface RepasseRepository : JpaRepository<Repasse, String> {
    fun findByFeiraComercianteEntityFeiraId(feiraId: String): List<Repasse>
    fun findByFeiraComercianteEntityComercianteId(comercianteId: String): List<Repasse>
    fun findByStatus(status: StatusRepasse): List<Repasse>
}