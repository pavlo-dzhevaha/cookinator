This is a Kotlin Multiplatform project targeting Android, Desktop (JVM).

* [shared](./shared/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./shared/src/commonMain/kotlin) is for code that’s common for all targets.
  - [androidMain](./shared/src/androidMain/kotlin) Android part.
  - [jvmMain](./shared/src/jvmMain/kotlin) Desktop (JVM) part.

### Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the run widget
in your IDE’s toolbar or build it directly from the terminal:
- on macOS/Linux/Windows
  ```shell
  ./gradlew :androidApp:assembleDebug
  ```

### Build and Run Desktop (JVM) Application

To build and run the development version of the desktop app, use the run configuration from the run widget
in your IDE’s toolbar or run it directly from the terminal:
- on macOS/Linux/Windows
  ```shell
  ./gradlew :desktopApp:run
  ```

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…