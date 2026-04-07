package br.edu.ufape.agrofeira.service

import br.edu.ufape.agrofeira.domain.repository.FeiraComercianteRepository
import br.edu.ufape.agrofeira.domain.repository.FeiraRepository
import br.edu.ufape.agrofeira.domain.repository.PedidoRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

data class RelatorioMesDTO(
    val mes: Int,
    val ano: Int,
    val nomeMes: String,
    val totalVendido: BigDecimal,
    val totalPedidos: Int,
)

data class RelatorioComercianteDTO(
    val comercianteId: String,
    val comercianteNome: String,
    val totalVendido: BigDecimal,
)

@Service
class RelatorioService(
    private val feiraRepository: FeiraRepository,
    private val feiraComercianteRepository: FeiraComercianteRepository,
    private val pedidoRepository: PedidoRepository,
) {
    private val meses =
        listOf(
            "Janeiro",
            "Fevereiro",
            "Março",
            "Abril",
            "Maio",
            "Junho",
            "Julho",
            "Agosto",
            "Setembro",
            "Outubro",
            "Novembro",
            "Dezembro",
        )

    fun relatorioPorMes(ano: Int): List<RelatorioMesDTO> {
        val feiras =
            feiraRepository
                .findAll()
                .filter { it.dataHora.year == ano }

        return (1..12).map { mes ->
            val feirasMes = feiras.filter { it.dataHora.monthValue == mes }
            val totalVendido =
                feirasMes
                    .flatMap {
                        feiraComercianteRepository.findByFeiraId(it.id)
                    }.sumOf { it.totalVendido }

            val totalPedidos =
                feirasMes.sumOf {
                    pedidoRepository.findByFeiraId(it.id).size
                }

            RelatorioMesDTO(
                mes = mes,
                ano = ano,
                nomeMes = meses[mes - 1],
                totalVendido = totalVendido,
                totalPedidos = totalPedidos,
            )
        }
    }

    fun relatorioPorComerciante(
        ano: Int,
        mes: Int?,
    ): List<RelatorioComercianteDTO> {
        val feiras =
            feiraRepository.findAll().filter { feira ->
                feira.dataHora.year == ano &&
                    (mes == null || feira.dataHora.monthValue == mes)
            }

        return feiras
            .flatMap { feiraComercianteRepository.findByFeiraId(it.id) }
            .groupBy { it.comerciante.id }
            .map { (_, fcs) ->
                RelatorioComercianteDTO(
                    comercianteId = fcs.first().comerciante.id,
                    comercianteNome = fcs.first().comerciante.nome,
                    totalVendido = fcs.sumOf { it.totalVendido },
                )
            }.sortedByDescending { it.totalVendido }
    }
}
