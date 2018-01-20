package kr.scripton.business.core.util

import kr.scripton.business.core.auth.BusinessUser
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

/**
 * @author archmagece@gmail.com
 * @since 2018-01-03 오후 12:40
 */
object SecurityMethodUtil {
    @JvmStatic
    fun getAuthentication(): Authentication {
        return SecurityContextHolder.getContext().authentication
    }

    @JvmStatic
    fun <PRINCIPAL : BusinessUser<Any>> getPrincipal() : PRINCIPAL? {
        return getAuthentication().principal as PRINCIPAL
//        return try {
//        } catch (e: ClassCastException) {
//            null
//        }
    }

    @JvmStatic
    fun loginProcess(userDetailsService: UserDetailsService, username: String) {
        var userDetails = userDetailsService.loadUserByUsername(username)
        loginProcess(userDetails)
    }

    @JvmStatic
    fun loginProcess(userDetails: UserDetails) {
        SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
    }

    @JvmStatic
    fun logoutProcess() {
        SecurityContextHolder.clearContext()
    }

}