# TrainXpert - Android Class Project

Short description
TrainXpert is an educational Android application for managing and visualizing training plans (exercises, sessions, progress). This repository contains the Android source code (Kotlin/Java, Gradle).

Technologies
- Android SDK
- Kotlin / Java
- Gradle
- AndroidX

Prerequisites
- JDK 11+
- Android Studio (recommended 2022.2+)
- Appropriate Android SDK (API level defined in build.gradle)

Installation
1. Clone the repository:
   git clone <repository-url>
2. Open the project in Android Studio.
3. Let Android Studio download Gradle dependencies.

Build and run
- From Android Studio: Run / Debug on an emulator or a connected device.
- From the command line (Windows):
  - Build debug APK: `.\gradlew assembleDebug`
  - Run unit tests: `.\gradlew test`
  - Run instrumentation tests: `.\gradlew connectedAndroidTest`

Project structure
- app/ : main Android module
  - src/main/java/... : source code
  - src/main/res : resources (layouts, drawables, strings)
- build.gradle (root) : Gradle configuration
- settings.gradle

Key features
- Session and workout management
- Progress visualization
- CRUD for exercises and programs
- Simple modular architecture (MVVM recommended)

Testing
- Unit tests: JUnit
- Instrumentation tests: Espresso (if present)
Commands: `.\gradlew test` and `.\gradlew connectedAndroidTest`

Contributing
- Open an issue to discuss features or bugs.
- Create a branch named feature/your-feature (e.g., feature/login).
- Submit a pull request describing your changes.



