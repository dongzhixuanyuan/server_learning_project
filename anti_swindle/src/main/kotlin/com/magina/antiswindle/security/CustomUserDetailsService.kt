package com.magina.antiswindle.security

import com.magina.antiswindle.user.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component


//账户:user1  密码:123456
//账户:admin 密码：123456

@Component
class CustomUserDetailsService : UserDetailsService {

    @Autowired
    var userMapper: UserMapper? = null
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userMapper!!.queryByName(username)
            ?: throw UsernameNotFoundException("User $username was not found in db")
        val grantedAuthorities: MutableList<GrantedAuthority> = mutableListOf()
        user.role?.split(",")?.forEach {
            grantedAuthorities.add(SimpleGrantedAuthority(it))
        }
        return User(username, user.password, grantedAuthorities)
    }

}
