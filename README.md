# TheMovieApp

**TheMovieApp** is a sample Android application built using Jetpack Compose that integrates with the TMDB API to allow users to search for movies and TV shows. The app is developed following the MVVM with Clean Architecture principles and is modularized for scalability and maintainability.

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
  - [Layered Structure](#layered-structure)
  - [Modules](#modules)
  - [Architecture Flow](#architecture-flow)
- [Tech Stack](#tech-stack)
- [API Integration](#api-integration)
- [Dependencies](#dependencies)


## Features

- **Search Movies and TV Shows:** Perform a search query using the TMDB API and display the results in categorized carousels.
- **Detail View:** View detailed information about selected movies or TV shows.
- **Responsive Design:** Supports right-to-left (RTL) and left-to-right layouts.
- **Jetpack Compose:** Fully developed using the modern declarative UI framework.
- **Modularization:** Separates app and core logic into independent modules.

## Architecture

### Layered Structure

#### Presentation Layer (App Module)

- Contains all UI-related components like Composables, ViewModels, and navigation logic.
- Utilizes Jetpack Compose for building the UI.
- Depends on the core module for business logic and data.

#### Domain Layer (Part of Core Module)

- Defines the application's business logic and use cases.
- Contains UseCases that interact with repositories to fetch or process data.
- Independent of frameworks like Retrofit 

#### Data Layer (Part of Core Module)

- Manages data sources, including API calls.
- Implements repository interfaces defined in the domain layer.
- Uses Retrofit for API integration with the TMDB API.

### Modules

#### Core Module

**Responsibilities:**
- Handles all business logic and data operations.
- Provides repository implementations and domain use cases.

**Key Packages:**
- `data`: Includes DTOs, mappers, and repository implementations.
- `domain`: Contains models and use cases.

#### App Module

**Responsibilities:**
- Contains the UI layer and handles navigation.
- Consumes the core module for data and business logic.

**Key Packages:**
- `ui`: Contains screens like `HomeScreen`, `DetailScreen`, `PlayerScreen` and shared UI components.
- `vm`: Houses ViewModels for handling UI logic.

### Architecture Flow

1. **UI Layer:**
   - The user interacts with the UI (e.g., entering a search query).
   - UI events are passed to the corresponding ViewModel.

2. **Domain Layer:**
   - ViewModel invokes a UseCase to handle the business logic.
   - UseCase interacts with the repository to fetch/process data.

3. **Data Layer:**
   - Repository fetches data from the remote API.
   - DTOs are mapped to domain models and returned to the domain layer.

## Tech Stack

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Dependency Injection:** Hilt
- **Networking:** Retrofit
- **State Management:** Kotlin Flows


## Screens

### Home Screen

- Search for movies and TV shows.
- Displays results in categorized carousels by media type (e.g., Movies, TV Shows).
- Each carousel scrolls horizontally.

### Detail Screen

- Displays detailed information about a selected movie or TV show.
- Shows a playback button for items with `media_type` as movie or TV.

### Player Screen

- **Media Playback Integration:** The Player Screen utilizes Media3's ExoPlayer for seamless video playback in landscape mode.
- **Hardcoded Video URL:** This screen plays a video from a predefined URL, as specified in the documentation.

## API Integration

- **Multi-Search API:** [https://api.themoviedb.org/3/search/multi](https://api.themoviedb.org/3/search/multi)
  - Returns results across multiple media types (e.g., movies, TV shows).
- **API Key:** Ensure you replace the placeholder `API_ACCESS_TOKEN` in `Constants` file in core module with your TMDB API key.


## Dependencies

This project uses the following major libraries and frameworks:

### Jetpack Compose

- **Description:** Declarative UI development framework for Android.
- **Libraries:**
  - Core: Compose BOM, UI components, Material Design 3.
  
### Dependency Injection

- **Hilt:** For injecting dependencies across the app, including support for Jetpack Compose.

### Networking 

**Retrofit:** Utilized for executing API requests and efficiently managing network communications.

### State Management

- **ViewModel:** Lifecycle-aware architecture component for managing UI-related data.

### Navigation

- **Jetpack Navigation:** For handling screen navigation in Compose.

### Image Loading

- **Coil:** An efficient and lightweight image loading library.

### Media Playback

- **ExoPlayer:** Customizable media player for handling video and audio playback.
