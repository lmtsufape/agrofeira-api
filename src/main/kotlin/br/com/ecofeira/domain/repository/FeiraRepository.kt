package br.com.ecofeira.domain.repository

import br.com.ecofeira.domain.entity.Feira
import br.com.ecofeira.domain.enums.StatusFeira
import org.springframework.data.jpa.repository.JpaRepository

interface FeiraRepository : JpaRepository<Feira, String> {
    fun findByStatus(status: StatusFeira): List<Feira>
    fun findAllByOrderByDataHoraDesc(): List<Feira>
}