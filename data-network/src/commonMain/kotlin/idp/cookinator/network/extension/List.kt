package idp.cookinator.network.extension

/**
 * Extension function to safely map a nullable list of nullable items.
 * It first ensures the list is not null (or empty), then maps each non-null item using the provided transform function.
 */
fun <T, R> List<T?>?.compactMap(transform: (T) -> R): List<R> = this
    .orEmpty()
    .mapNotNull { item ->
        runCatching {
            item?.let(transform)
        }.getOrNull()
    }
