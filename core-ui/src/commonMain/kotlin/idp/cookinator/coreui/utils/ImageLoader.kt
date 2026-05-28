package idp.cookinator.coreui.utils

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.crossfade
import okio.FileSystem

/**
 * Builds and returns an [ImageLoader] instance configured for asynchronous image loading.
 */
internal fun getAsyncImageLoader(context: PlatformContext): ImageLoader = ImageLoader
    .Builder(context)
    .memoryCache {
        MemoryCache
            .Builder()
            .maxSizePercent(context, 0.25)
            .build()
    }
    .diskCache {
        DiskCache
            .Builder()
            .directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "image_cache")
            .maxSizeBytes(512L * 1024 * 1024) // 512MB disk cache limit
            .build()
    }
    .crossfade(true) // Smooth fade-in animation
    .build()
