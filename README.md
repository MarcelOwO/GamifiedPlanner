# GamifiedPlanner

A task planner app with gamification features. Implemented for android using kotlin.

> [!Warining]
> **Work in Progress:** This project is under active development. Features may change, and bugs are expected.

---

## Features

- Task managing system
- Level and xp system
- Shop and currency system
- Achievement system
- Daily progress view

## Tech Stack

- **Language:** Kotlin
- **DI Framework:** Koin
- **Build System:** Gradle

## Project structure

- `app/src/main/res`: contains resource files such as generated icons
- `app/src/main/java/marcel/uni/gamifiedplanner`: is the main root of the code

- `./data`: contains the raw data logic and repo implementaiton to firebase/firestore
- `./di`: contains the koin dependency injections module definitions
- `./domain`: contains the domain logic composed of Repo interfaces, data models and usecases and other service implementations
- `./ui`: contains the ui built using jetpack composes and the according viewmodels for each view
- `./util`: contains helper function like firebase extension methods

---

## Getting Started

### Prerequisites

- Android Studio
- JDK 11+

### Installation and Running

1. Clone the repo:

   ```bash
   git clone https://github.com/MarcelOwO/GamifiedPlanner.git
   ```

2. Build and install the debug build to a connected device:

   ```bash
   ./gradlew installDebug
   ```

   or run play using android studio

## Documentation

Documentatation is found inside the docs/ directory
