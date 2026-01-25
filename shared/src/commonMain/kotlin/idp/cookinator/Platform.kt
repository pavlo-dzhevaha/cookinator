package idp.cookinator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform