# :feature-main

Main app shell with bottom navigation tabs.

## Purpose

Hosts the primary app experience: Home (discovery hub), Saved recipes, Notifications, and Profile (user-created recipes). Manages internal tab routing within the main shell.

## Key classes / files

- `screen/main/MainScreen.kt` — bottom bar + tab stack
- **Home:** `HomeScreen`, `HomeContent`, `HomeViewModel`, `home/contract/*`
- **Saved:** `SavedScreen`, `SavedContent`, `SavedViewModel`, `saved/contract/*`
- **Notifications:** `NotificationsScreen`, `NotificationsContent`, `NotificationsViewModel`, `notification/contract/*`
- **Profile:** `ProfileScreen`, `ProfileContent`, `ProfileViewModel`, `profile/contract/*`
- `navigation/NavigationMain.kt` — `NavigationMain.graph()`
- `navigation/internal/NavigationMainInternal.kt` — internal tab routes (not in `:navigation`)
- `di/DiMain.kt` — `featureMainModule` (4 ViewModels)

## Dependencies

**Project modules:** `:navigation`, `:localisation`, `:domain`, `:data-model`

**Libraries:** material3-adaptive-navigation3, Compose resources, Coil, kotlinx-datetime, kotlinx serialization (internal tab keys)

## Public API / usage

- **Owns route (in `:navigation`):** `NavigationMain.Main`
- **Internal tab routes:** `NavigationMainInternal.Home`, `.Saved`, `.Notifications`, `.Profile`
- **Navigates to:** `NavigationRecipe.Detail`, `NavigationRecipe.UserDetail`, `NavigationAllRecipes.All`, `NavigationCreateRecipe.Create` / `.CreateFromRecipe` / `.Edit`, `NavigationSettings.Settings`
- Uses `AllRecipesFilter` (Trending, Category, RecentlyViewed)
- `featureMainModule` — registered in `host/di/AppKoin.kt`
