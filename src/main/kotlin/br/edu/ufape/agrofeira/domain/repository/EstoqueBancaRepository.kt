package br.edu.ufape.agrofeira.domain.repository

import br.edu.ufape.agrofeira.domain.entity.EstoqueBanca
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface EstoqueBancaRepository : JpaRepository<EstoqueBanca, String> {
    fun findByFeiraComercianteEntityId(feiraComercianteId: String): List<EstoqueBanca>

    fun findByFeiraComercianteEntityFeiraIdAndItemId(
        feiraId: String,
        itemId: String,
    ): List<EstoqueBanca>

    @Query(
        """
        SELECT e FROM EstoqueBanca e 
        WHERE e.feiraComercianteEntity.feira.id = :feiraId 
        AND e.item.id = :itemId 
        AND e.quantidadeDisponivel > e.quantidadeReservada
        ORDER BY e.feiraComercianteEntity.totalVendido ASC
    """,
    )
    fun findDisponiveisPorFeiraEItemOrdenadoPorMenosVendido(
        feiraId: String,
        itemId: String,
    ): List<EstoqueBanca>
}
