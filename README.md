# ✅ KMP To-Do List

**A to-do app for Android and iOS written once with Kotlin Multiplatform and Compose Multiplatform.**

The UI, the ViewModels and the data layer all live in `commonMain`. Each platform only provides a small native entry point and a couple of `expect`/`actual` utilities.

![Kotlin](https://img.shields.io/badge/Kotlin_Multiplatform-7F52FF?style=flat-square&logo=kotlin&logoColor=white)
![Compose Multiplatform](https://img.shields.io/badge/Compose_Multiplatform-4285F4?style=flat-square&logo=jetpackcompose&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=flat-square&logo=android&logoColor=white)
![iOS](https://img.shields.io/badge/iOS-000000?style=flat-square&logo=apple&logoColor=white)

---

## ✨ Features

- List, create, edit and delete tasks
- Reactive in-memory repository built on `StateFlow`
- **One shared codebase** for the UI and the logic on Android and iOS
- MVVM with UI state classes for each screen
- Platform-specific code isolated behind `expect`/`actual` (UUID generation and logging)

## 🗂️ Project structure

```
composeApp/
├── commonMain/      Shared code: screens, ViewModels, repository, models
│   ├── feature/tasksList/   TasksListScreen, TasksListViewModel, TasksListUiState
│   ├── feature/tasksForm/   TaskFormScreen, TaskFormViewModel, TaskFormUiState
│   ├── repository/          TasksRepository
│   └── model/               Task
├── androidMain/     MainActivity and Android actuals
├── iosMain/         MainViewController for SwiftUI
└── appleMain/       Apple actuals (UUID, logging)
iosApp/              SwiftUI host app that embeds the shared Compose UI
```

## 🧰 Tech stack

Kotlin Multiplatform · Compose Multiplatform · Coroutines · MVVM · SwiftUI (iOS host)

## 🚀 Getting started

- **Android:** open the project in Android Studio and run the `composeApp` configuration.
- **iOS:** open `iosApp/iosApp.xcodeproj` in Xcode and run it on a simulator, or use the iOS run configuration in Android Studio with the Kotlin Multiplatform plugin.

---

Made by [Matheus Maignardi](https://github.com/Maignardi)
