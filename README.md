# GamifiedPlanner

A task planner app with gamification features. Implemented for android using kotlin.

> [!Warining]
> **Work in Progress:**  This project is under active development. Features may change, and bugs are expected.

---

## Features

- Task managing system
- Level and xp system
- Shop and currency system
- Achievement system
- Daily progress view

## Tech Stack

- **Language:** [Kotlin](https://)
- **DI Framework:** [Koin](https://)
- **Build System:** Gradle

## Project structure

-  `app/src/main/res`: contains resource files such as generated icons
-  `app/src/main/java/marcel/uni/gamifiedplanner`: is the main root of the code

- `~/data`:  contains the raw data logic and repo implementaiton to firebase/firestore
- `~/di`: contains the koin dependency injections module definitions
- `~/domain`: contains the domain logic 
- `~/ui`: contains the ui built using jetpack composes
- `~/util`: contains helper function like firebase extension methods and such
---

## Getting Started

### Prerequisites

- Android Studio
- JDK 11+

### Installation and Running

1. Clone the repo:
    ``` bash
    git clone https://github.com/MarcelOwO/GamifiedPlanner.git
    ```

2. Build and install the debug build to a connected device:

    ``` bash
    ./gradlew installDebug
    ```
    or run play using android studio
