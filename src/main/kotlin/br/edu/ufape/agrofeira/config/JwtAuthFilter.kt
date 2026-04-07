package br.edu.ufape.agrofeira.config

import br.edu.ufape.agrofeira.domain.repository.UsuarioRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val jwtService: JwtService,
    private val usuarioRepository: UsuarioRepository,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val authHeader = request.getHeader("Authorization")

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        val token = authHeader.substring(7)

        if (!jwtService.isTokenValid(token)) {
            filterChain.doFilter(request, response)
            return
        }

        val username = jwtService.extractUsername(token)
        val usuario =
            usuarioRepository.findByUsername(username).orElse(null)
                ?: run {
                    filterChain.doFilter(request, response)
                    return
                }

        val auth =
            UsernamePasswordAuthenticationToken(
                usuario.username,
                null,
                listOf(SimpleGrantedAuthority("ROLE_${usuario.perfil}")),
            )
        SecurityContextHolder.getContext().authentication = auth
        filterChain.doFilter(request, response)
    }
}
