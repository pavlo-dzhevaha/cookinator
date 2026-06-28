# :data-network

Remote data access for recipe discovery and server-side cache.

## Purpose

Fetches recipes from the Spoonacular REST API and reads/writes server-side recipe cache via Supabase PostgREST. Maps wire DTOs to `:data-model` types.

## Key classes / files

- `NetworkManager.kt` — `getRandomRecipes()`, `getRecipeInformation()`, `getServerRecipes()`
- `model/RandomRecipesResponse.kt` — API DTOs and `toDomainModel()` mappers
- `di/DiNetwork.kt` — Koin `dataNetworkModule` (`HttpClient`, `SupabaseClient`, `NetworkManager`)
- `BuildKonfig` (generated) — `API_KEY`, `SUPABASE_URL`, `SUPABASE_ANON_KEY` from `config/secrets.properties`

## Dependencies

**Project modules:** `:data-model`

**Libraries:** Koin, Supabase (PostgREST), Ktor client (OkHttp on Android, CIO on JVM), kotlinx serialization

## Public API / usage

- `NetworkManager` — injected into domain use cases (`SyncDiscoveryRecipesUseCaseImpl`, `GetRecipeByIdUseCaseImpl`, etc.)
- `dataNetworkModule` — registered in `host/di/AppKoin.kt`
- DTO mappers (`toDomainModel()`, `toDomainModels()`) — used by domain sync/fetch use cases
