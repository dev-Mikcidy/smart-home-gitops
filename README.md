# 🏠 SmartHomeGitOps

SmartHomeGitOps is an Android application developed with **Kotlin and Jetpack Compose** that demonstrates how a mobile application can communicate with the **GitHub API** and monitor GitHub repository activity.

The project uses **MVVM architecture**, **Kotlin Coroutines**, and **Retrofit** to retrieve and process GitHub data asynchronously.

## 🚀 Features

- 🔗 Connects to the GitHub REST API
- 📦 Retrieves repository information
- 🔀 Monitors Pull Requests
- 💬 Retrieves issue and Pull Request comments
- 🔍 Detects potentially suspicious or security-related comments
- 🚨 Displays security alerts in the UI
- ✅ Provides actions to force reject or force merge a Pull Request
- ⚡ Uses Kotlin Coroutines for asynchronous operations
- 🏗️ Uses MVVM architecture
- 🎨 Built with Jetpack Compose

## 🛠️ Technologies

| Technology | Purpose |
|---|---|
| **Kotlin** | Main programming language |
| **Jetpack Compose** | User interface |
| **MVVM** | Application architecture |
| **Retrofit** | Communication with the GitHub API |
| **Kotlin Coroutines** | Asynchronous programming |
| **GitHub REST API** | Repository and Pull Request data |
| **Android Studio** | Development environment |

## 🏗️ Architecture

The application follows the **MVVM (Model–View–ViewModel)** architecture.

```text
┌──────────────────────────┐
│      Jetpack Compose     │
│          UI / View       │
└────────────┬─────────────┘
             │
             │ User actions
             ↓
┌──────────────────────────┐
│       ViewModel          │
│     UI State & Logic     │
└────────────┬─────────────┘
             │
             ↓
┌──────────────────────────┐
│       Repository         │
│      Data management     │
└────────────┬─────────────┘
             │
             ↓
┌──────────────────────────┐
│         Retrofit         │
│      GitHub API Client   │
└────────────┬─────────────┘
             │
             ↓
┌──────────────────────────┐
│       GitHub API         │
└──────────────────────────┘
```

### View

The Jetpack Compose UI is responsible for displaying information to the user and handling user interactions.

### ViewModel

The ViewModel manages UI state and application logic. It communicates with the repository and exposes the data needed by the UI.

### Repository

The repository acts as an abstraction between the ViewModel and the data source. It handles requests to the GitHub API through Retrofit.

### Retrofit

Retrofit is used to communicate with the GitHub REST API and convert API responses into Kotlin data objects.

### Kotlin Coroutines

Coroutines are used to perform network operations asynchronously without blocking the Android UI thread.

## 🔐 Security Monitoring

One of the main purposes of the project is to demonstrate how GitHub activity can be monitored for potentially suspicious content.

The application analyzes comments associated with Pull Requests and uses a **DeceptionDetector** to determine whether a comment may represent a security concern.

The application can display different states such as:

```text
NORMAL
```

or

```text
SECURITY ALERT
```

When a security alert is detected, additional information can be presented to the user, including the confidence level and the Pull Request associated with the alert.

## 📱 UI

The application provides a security monitoring interface where repository activity can be displayed and analyzed.

Example states include:

### Normal

```text
┌─────────────────────────────┐
│                             │
│          NORMAL             │
│                             │
│   No security threat        │
│   detected                  │
│                             │
└─────────────────────────────┘
```

### Security Alert

```text
┌─────────────────────────────┐
│                             │
│     SECURITY ALERT          │
│                             │
│   Suspicious activity       │
│   detected                  │
│                             │
│  [ FORCE REJECT ]           │
│  [ FORCE MERGE  ]           │
│                             │
└─────────────────────────────┘
```

## 📂 Project Structure

The project is organized around the MVVM architecture:

```text
app/
└── src/
    └── main/
        ├── java/
        │   └── ...
        │
        └── res/
            └── ...
```

The main components include:

- **UI / Compose screens** – User interface
- **ViewModel** – UI state and application logic
- **Repository** – Data management
- **Retrofit API interface** – GitHub API communication
- **Data models** – Representation of GitHub data
- **DeceptionDetector** – Security/deception detection logic

## 🔄 Application Flow

The general application flow is:

```text
GitHub Repository
       ↓
GitHub REST API
       ↓
Retrofit
       ↓
Repository
       ↓
ViewModel
       ↓
UI State
       ↓
Jetpack Compose
       ↓
User
```

When GitHub data changes, the application can retrieve the latest information and update the UI accordingly.

## ⚙️ Getting Started

### Prerequisites

Before running the project, make sure you have:

- Android Studio
- JDK
- Android SDK
- A GitHub account
- An Android emulator or physical Android device

### Clone the repository

```bash
git clone https://github.com/dev-Mikcidy/smart-home-gitops.git
```

Open the project in **Android Studio**.

Allow Gradle to synchronize the project dependencies and then run the application on an emulator or Android device.

## 🔑 GitHub API

The application communicates with GitHub through the GitHub REST API.

Depending on the API operations being performed, authentication may be required.

For development, avoid committing sensitive credentials such as:

```text
GitHub Personal Access Tokens
API keys
Passwords
Secrets
```

Use local configuration or secure environment/secret management instead.

## 🎯 Learning Objectives

This project was developed to gain practical experience with:

- Android development using Kotlin
- Jetpack Compose
- MVVM architecture
- Repository pattern
- REST APIs
- Retrofit
- Kotlin Coroutines
- State management
- GitHub API integration
- Pull Request workflows
- Security monitoring
- Git and GitHub

## 🔮 Future Improvements

Possible improvements include:

- User authentication with GitHub
- Support for multiple repositories
- Improved security/deception detection
- More detailed Pull Request analysis
- Push notifications for security alerts
- Improved error handling
- Offline caching
- More comprehensive testing
- GitHub Actions integration
- Improved UI/UX

## 👨‍💻 Author

**Michael Agunbiade**

Software Development Student

GitHub: [dev-Mikcidy](https://github.com/dev-Mikcidy)

---

⭐ If you find this project interesting, feel free to explore the repository and follow the development.
