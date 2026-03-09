package idp.cookinator.host

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform