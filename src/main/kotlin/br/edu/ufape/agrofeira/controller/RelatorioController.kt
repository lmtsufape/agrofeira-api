package br.edu.ufape.agrofeira.controller

import br.edu.ufape.agrofeira.service.RelatorioService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/relatorios")
class RelatorioController(
    private val relatorioService: RelatorioService,
) {
    @GetMapping("/por-mes")
    fun relatorioPorMes(
        @RequestParam ano: Int,
    ) = ResponseEntity.ok(relatorioService.relatorioPorMes(ano))

    @GetMapping("/por-comerciante")
    fun relatorioPorComerciante(
        @RequestParam ano: Int,
        @RequestParam(required = false) mes: Int?,
    ) = ResponseEntity.ok(relatorioService.relatorioPorComerciante(ano, mes))
}
