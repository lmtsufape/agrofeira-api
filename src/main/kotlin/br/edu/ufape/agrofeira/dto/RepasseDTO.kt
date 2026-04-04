package br.edu.ufape.agrofeira.dto

import br.edu.ufape.agrofeira.domain.entity.Repasse
import br.edu.ufape.agrofeira.domain.enums.StatusRepasse
import java.math.BigDecimal
import java.time.LocalDateTime

data class RepasseDTO(
    val id: String,
    val feiraId: String,
    val feiraData: String,
    val comercianteId: String,
    val comercianteNome: String,
    val valorBruto: BigDecimal,
    val taxaAssociacao: BigDecimal,
    val valorLiquido: BigDecimal,
    val status: StatusRepasse,
    val repassadoEm: LocalDateTime?,
) {
    companion object {
        fun from(repasse: Repasse) =
            RepasseDTO(
                id = repasse.id,
                feiraId = repasse.feiraComercianteEntity.feira.id,
                feiraData =
                    repasse.feiraComercianteEntity.feira.dataHora
                        .toString(),
                comercianteId = repasse.feiraComercianteEntity.comerciante.id,
                comercianteNome = repasse.feiraComercianteEntity.comerciante.nome,
                valorBruto = repasse.valorBruto,
                taxaAssociacao = repasse.taxaAssociacao,
                valorLiquido = repasse.valorLiquido,
                status = repasse.status,
                repassadoEm = repasse.repassadoEm,
            )
    }
}
