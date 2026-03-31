package br.com.ecofeira.domain.repository

import br.com.ecofeira.domain.entity.Repasse
import br.com.ecofeira.domain.enums.StatusRepasse
import org.springframework.data.jpa.repository.JpaRepository

interface RepasseRepository : JpaRepository<Repasse, String> {
    fun findByFeiraComercianteEntityFeiraId(feiraId: String): List<Repasse>
    fun findByFeiraComercianteEntityComercianteId(comercianteId: String): List<Repasse>
    fun findByStatus(status: StatusRepasse): List<Repasse>
}