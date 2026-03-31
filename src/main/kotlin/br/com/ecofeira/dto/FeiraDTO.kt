package br.com.ecofeira.dto

import br.com.ecofeira.domain.entity.Feira
import br.com.ecofeira.domain.enums.StatusFeira
import java.time.LocalDateTime

data class FeiraDTO(
    val id: String,
    val dataHora: LocalDateTime,
    val status: StatusFeira,
    val comerciantes: List<FeiraComercianteDTO>,
    val itens: List<ItemDTO>
) {
    companion object {
        fun from(feira: Feira) = FeiraDTO(
            id = feira.id,
            dataHora = feira.dataHora,
            status = feira.status,
            comerciantes = feira.comerciantes.map { FeiraComercianteDTO.from(it) },
            itens = feira.itens.map { ItemDTO.from(it.item) }
        )
    }
}