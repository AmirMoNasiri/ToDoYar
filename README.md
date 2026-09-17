# To Do Yar

**To Do Yar (تودویار)** is a modern Android task management application designed to help users organize tasks, manage priorities and categories, set reminders, and track their daily activity.

The application is built with **Kotlin** and **Jetpack Compose**, following the **MVVM architecture** and modern Android development practices. It uses **Room**, **DataStore**, **Dagger Hilt**, **Coroutines**, **Flow**, and **WorkManager** to provide a maintainable and responsive application architecture.

---

## 📱 About the Project

To Do Yar is a task management application developed to provide a simple and modern way to organize daily activities and tasks.

Users can:

* Create, edit, and delete tasks
* Mark tasks as completed
* Assign categories to tasks
* Set task priorities
* Set due dates and times
* Receive task reminders
* Filter tasks by category and priority
* View tasks based on selected dates
* Track weekly task activity
* View task statistics and activity information
* Manage application appearance with light and dark themes

The application stores task data locally, allowing the core task management features to work without requiring a remote backend.

---

## ✨ Features

### 📝 Task Management

* Create new tasks
* Edit existing tasks
* Delete tasks
* Mark tasks as completed
* Track completion time
* Add optional descriptions
* Assign categories and priorities

### 🏷 Categories

Tasks can be organized using categories such as:

* Work
* Personal
* Shopping
* Exercise
* Study

Users can also create and remove custom categories.

### ⭐ Task Priorities

Each task can have a priority level:

* Low
* Medium
* High
* Urgent

### 📅 Date & Time

Tasks can have:

* Due dates
* Due times
* Persian/Jalali date support
* 24-hour time selection

### 🔔 Reminders

To Do Yar uses Android background scheduling to provide task reminders even when the application is not actively open.

The reminder system is based on **WorkManager** and Android notifications.

### 📊 Activity & Statistics

The application provides information about task activity, including:

* Completed tasks
* Pending tasks
* Weekly activity
* Task statistics

### 🌓 Theme

The application supports:

* Light theme
* Dark theme

The selected theme is persisted locally using **DataStore**.

---

## 🛠 Tech Stack

| Technology          | Usage                              |
| ------------------- | ---------------------------------- |
| **Kotlin**          | Primary programming language       |
| **Jetpack Compose** | Declarative UI                     |
| **Material 3**      | UI components and theming          |
| **MVVM**            | Application architecture           |
| **Dagger Hilt**     | Dependency injection               |
| **Room**            | Local database                     |
| **DataStore**       | Persistent application preferences |
| **Coroutines**      | Asynchronous programming           |
| **Flow**            | Reactive data streams              |
| **WorkManager**     | Background task scheduling         |
| **Navigation**      | Screen navigation                  |
| **Lottie**          | UI animations                      |

---

## 🏗 Architecture

To Do Yar follows the **MVVM (Model–View–ViewModel)** architecture pattern.

```text
┌───────────────────────────┐
│          UI Layer         │
│     Jetpack Compose       │
└─────────────┬─────────────┘
              │
              │ UI Events
              ▼
┌───────────────────────────┐
│       ViewModel Layer     │
│        State + Logic      │
└─────────────┬─────────────┘
              │
              ▼
┌───────────────────────────┐
│      Repository Layer     │
│   Task / Category Data     │
└─────────────┬─────────────┘
              │
       ┌──────┴──────┐
       ▼             ▼
┌────────────┐  ┌─────────────┐
│    Room    │  │  DataStore  │
│  Database  │  │ Preferences │
└────────────┘  └─────────────┘
```

Background reminders are handled separately through **WorkManager** and the Android notification system.

### Main architectural components

* **UI:** Jetpack Compose
* **ViewModel:** UI state and user interactions
* **Repository:** Data access and application data operations
* **Room:** Persistent task and category storage
* **DataStore:** Application preferences
* **Hilt:** Dependency injection
* **WorkManager:** Background reminder scheduling

---

## 📸 Screenshots

Here are some screenshots of the To Do Yar application.

### 🏠 Task Management

| Task List                                     | Add Task                                         |
| --------------------------------------------- | ------------------------------------------------ |
| <img src="screenshots/tasks.png" width="300"> | <img src="screenshots/add-task.png" width="300"> |

### 📅 Calendar & Tasks

| Calendar                                         | Task Details                                         |
| ------------------------------------------------ | ---------------------------------------------------- |
| <img src="screenshots/calendar.png" width="300"> | <img src="screenshots/task-details.png" width="300"> |

### 📊 Statistics

| Activity                                         | Statistics                                         |
| ------------------------------------------------ | -------------------------------------------------- |
| <img src="screenshots/activity.png" width="300"> | <img src="screenshots/statistics.png" width="300"> |

### 🌓 Themes

| Light Theme                                         | Dark Theme                                         |
| --------------------------------------------------- | -------------------------------------------------- |
| <img src="screenshots/light-theme.png" width="300"> | <img src="screenshots/dark-theme.png" width="300"> |

> **Note:** Replace the image paths above with the actual screenshots included in the `screenshots/` directory.

---

## 📂 Project Structure

```text
app/
├── data/
│   ├── local/
│   │   ├── dao/
│   │   ├── database/
│   │   └── entity/
│   │
│   └── repository/
│
├── di/
│   └── module/
│
├── model/
│
├── notification/
│
├── utils/
│
├── view/
│   ├── event/
│   ├── screen/
│   ├── state/
│   └── components/
│
├── viewModel/
│
└── MainActivity.kt
```

The project structure may evolve as the application grows and responsibilities are further separated.

---

## 💾 Data Storage

To Do Yar uses **Room Database** for persistent application data.

The database stores information such as:

* Tasks
* Task categories
* Completion status
* Task priorities
* Due dates and times

**DataStore** is used for lightweight application preferences, such as the selected theme.

---

## 🔔 Notification & Background Work

Task reminders are scheduled using Android's background work system.

```text
Task
  │
  ▼
Reminder Scheduler
  │
  ▼
WorkManager
  │
  ▼
Task Reminder Worker
  │
  ▼
Android Notification
```

This allows scheduled reminders to be processed independently from the application's UI lifecycle.

---

## 📦 APK

A release APK can be provided in the project's release section.

The APK can be installed directly on a compatible Android device for testing.

---

## 📄 Documentation

The complete project documentation and final project report are provided separately.

The documentation covers:

* Project requirements
* Application architecture
* Database design
* UI design
* Implementation details
* Testing
* Final results

---

## 🎓 Academic Project

To Do Yar was developed as a **Bachelor's degree project**.

**Developer:** Amir Mohammad Nasiri
**Supervisor:** Dr. Morteza Yousef Sanati

---

## 📌 Project Status

The project is currently under development and may receive further improvements and refinements.

---

## 👨‍💻 Author

**Amir Mohammad Nasiri**

Android Developer
Kotlin • Jetpack Compose • MVVM • Room • Hilt

---

⭐ If you find this project useful, consider giving it a star.
