package idp.cookinator.domain.recipe

/**
 * Synchronizes discovery recipes using an offline-first mediator strategy.
 *
 * Room is the single source of truth for recipe data. Network fetch runs only when the local
 * database has no recipes. Use [ObserveDiscoveryRecipesUseCase] to reactively read discovery data.
 *
 * @see ObserveDiscoveryRecipesUseCase
 */
interface SyncDiscoveryRecipesUseCase {
    /**
     * Syncs discovery recipes according to the mediator rules.
     *
     * @param forceRefresh When `true` and Room has recipes, reshuffles the persisted discovery order
     * from local data only (no network). When Room is empty, fetches from remote sources regardless
     * of this flag. When `false`, fetches from the network only if Room is empty; if Room already
     * already has recipes but no order is stored yet, generates and persists a shuffle order.
     * @return [Result.success] when sync or reshuffle completed, or [Result.failure] when Room is
     * empty and all remote sources fail.
     */
    suspend operator fun invoke(forceRefresh: Boolean = false): Result<Unit>
}
