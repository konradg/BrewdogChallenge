# Brewdog Beer Challenge Demo App
This is a demo app written in a couple of hours, demonstrating the use of Punk API: https://punkapi.com/documentation/v2

## What's included
- List screen with a grid
- Details screen
- Dark theme support
- Usage of AndroidX, RecyclerView, ViewPager2, CardView, ViewModel, LiveData, RxJava3
- Image loading via Glide
- Local HTTP cache
- Dependency Injection via Hilt
- JSON serialization with kotlinx.serialization
- View states for loading/error
- Repository pattern for accessing data
- Interactors to separate business use case from implementation details
- Unit tests for ViewModels, Interactors and Repository

## What's missing

Some elements were omitted because of time constraints:
- Paging - only the first page of 25 beers is currently displayed
- Better theming
- Better landscape & tablet layouts
- Collapsible appbar layout in details screen
- UI tests with Espresso
- Proper Toolbar implementation
- Use of Navigation library
- Network security config in XML
- Handling network (re-)connection
- Shared element transition for nicer beer image animation
- Auto-fitting number of columns in list view
