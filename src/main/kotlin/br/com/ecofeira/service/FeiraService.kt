package br.com.ecofeira.service

import br.com.ecofeira.domain.entity.*
import br.com.ecofeira.domain.enums.StatusFeira
import br.com.ecofeira.domain.repository.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class FeiraService(
    private val feiraRepository: FeiraRepository,
    private val feiraComercianteRepository: FeiraComercianteRepository,
    private val comercianteRepository: ComercianteRepository,
    private val itemRepository: ItemRepository
) {
    fun listarTodas(): List<Feira> = feiraRepository.findAllByOrderByDataHoraDesc()

    fun buscarPorId(id: String): Feira =
        feiraRepository.findById(id).orElseThrow { RuntimeException("Feira não encontrada") }

    @Transactional
    fun criar(feira: Feira, comercianteIds: List<String>, itemIds: List<String>): Feira {
        val feiraSalva = feiraRepository.save(feira)

        comercianteIds.forEach { comercianteId ->
            val comerciante = comercianteRepository.findById(comercianteId)
                .orElseThrow { RuntimeException("Comerciante não encontrado") }
            feiraComercianteRepository.save(
                FeiraComercianteEntity(
                    feira = feiraSalva,
                    comerciante = comerciante,
                    totalVendido = BigDecimal.ZERO
                )
            )
        }

        itemIds.forEach { itemId ->
            val item = itemRepository.findById(itemId)
                .orElseThrow { RuntimeException("Item não encontrado") }
            feiraSalva.itens.add(FeiraItem(feira = feiraSalva, item = item))
        }

        return feiraRepository.save(feiraSalva)
    }

    @Transactional
    fun atualizarStatus(id: String, status: StatusFeira): Feira {
        val feira = buscarPorId(id)
        return feiraRepository.save(feira.copy(status = status))
    }

    fun buscarFeiraComerciantePorFeiraEComerciante(feiraId: String, comercianteId: String): FeiraComercianteEntity? =
        feiraComercianteRepository.findByFeiraIdAndComercianteId(feiraId, comercianteId)
}