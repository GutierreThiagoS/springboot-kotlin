package br.com.gutierre.productsdb.model

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "Users")
class User: UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "user_name", length = 255, unique = true, nullable = false)
    var userName: String = ""

    @Column(name = "full_name", length = 255, nullable = false)
    var fullName: String = ""

    @Column(name = "email", length = 255, nullable = false)
    var email: String = ""

    @Column(name = "password", length = 255, nullable = false)
    private var password: String = ""

    @Column(name = "account_non_expired", nullable = false)
    var accountNonExpired: Boolean = false

    @Column(name = "account_non_locked", nullable = false)
    var accountNonLocked: Boolean = false

    @Column(name = "credentials_non_expired", nullable = false)
    var credentialsNonExpired: Boolean = false

    @Column(name = "enabled", nullable = false)
    var enabled: Boolean = false

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_permission",
        joinColumns = [JoinColumn(name = "id_user")],
        inverseJoinColumns = [JoinColumn(name = "id_permission")],
    )
    var permissions: List<Permission>? = null

    val roles: List<String?>
        get() {
            /*val roles: MutableList<String?> = ArrayList()
            for (permission in permissions!!){
                roles.add(permission.description)
            }
            return roles*/
            return permissions?.map { it.description } ?: listOf()

        }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return permissions!!
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return userName
    }

    override fun isAccountNonExpired(): Boolean {
        return accountNonExpired
    }

    override fun isAccountNonLocked(): Boolean {
        return accountNonLocked
    }

    override fun isCredentialsNonExpired(): Boolean {
        return credentialsNonExpired
    }

    override fun isEnabled(): Boolean {
        return enabled
    }
}