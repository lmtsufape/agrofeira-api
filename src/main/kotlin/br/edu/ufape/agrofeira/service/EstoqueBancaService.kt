package br.edu.ufape.agrofeira.service

import br.edu.ufape.agrofeira.domain.entity.EstoqueBanca
import br.edu.ufape.agrofeira.domain.repository.EstoqueBancaRepository
import br.edu.ufape.agrofeira.domain.repository.FeiraComercianteRepository
import br.edu.ufape.agrofeira.domain.repository.ItemRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class EstoqueBancaService(
    private val estoqueBancaRepository: EstoqueBancaRepository,
    private val feiraComercianteRepository: FeiraComercianteRepository,
    private val itemRepository: ItemRepository
) {
    fun listarPorFeira(feiraId: String): List<EstoqueBanca> =
        estoqueBancaRepository.findByFeiraComercianteEntityFeiraIdAndItemId(feiraId, "")
            .let { estoqueBancaRepository.findAll()
                .filter { it.feiraComercianteEntity.feira.id == feiraId } }

    fun listarPorFeiraEItem(feiraId: String, itemId: String): List<EstoqueBanca> =
        estoqueBancaRepository.findDisponiveisPorFeiraEItemOrdenadoPorMenosVendido(feiraId, itemId)

    fun buscarPorId(id: String): EstoqueBanca =
        estoqueBancaRepository.findById(id).orElseThrow { RuntimeException("Estoque não encontrado") }

    @Transactional
    fun declarar(feiraComercianteId: String, itemId: String, quantidade: BigDecimal): EstoqueBanca {
        val feiraComercianteEntity = feiraComercianteRepository.findById(feiraComercianteId)
            .orElseThrow { RuntimeException("Feira/Comerciante não encontrado") }
        val item = itemRepository.findById(itemId)
            .orElseThrow { RuntimeException("Item não encontrado") }

        val estoqueExistente = estoqueBancaRepository
            .findByFeiraComercianteEntityFeiraIdAndItemId(
                feiraComercianteEntity.feira.id,
                itemId
            ).firstOrNull { it.feiraComercianteEntity.id == feiraComercianteId }

        return if (estoqueExistente != null) {
            estoqueBancaRepository.save(
                estoqueExistente.copy(quantidadeDisponivel = quantidade)
            )
        } else {
            estoqueBancaRepository.save(
                EstoqueBanca(
                    feiraComercianteEntity = feiraComercianteEntity,
                    item = item,
                    quantidadeDisponivel = quantidade,
                    quantidadeReservada = BigDecimal.ZERO
                )
            )
        }
    }

    @Transactional
    fun atualizar(id: String, quantidade: BigDecimal): EstoqueBanca {
        val estoque = buscarPorId(id)
        return estoqueBancaRepository.save(
            estoque.copy(quantidadeDisponivel = quantidade)
        )
    }
}