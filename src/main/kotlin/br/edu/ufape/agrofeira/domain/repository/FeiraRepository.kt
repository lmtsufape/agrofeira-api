package br.edu.ufape.agrofeira.domain.repository

import br.edu.ufape.agrofeira.domain.entity.Feira
import br.edu.ufape.agrofeira.domain.enums.StatusFeira
import org.springframework.data.jpa.repository.JpaRepository

interface FeiraRepository : JpaRepository<Feira, String> {
    fun findByStatus(status: StatusFeira): List<Feira>

    fun findAllByOrderByDataHoraDesc(): List<Feira>
}
