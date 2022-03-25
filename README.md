# EstateFeasibilityDesktopApplication

Estate feasibility application written in Kotlin using Compose Desktop UI framework, running On Windows.

* Video 🧪

|View|
|----------------------|
|<img src="https://user-images.githubusercontent.com/50905347/160124239-7445df38-9ad4-46dc-a589-ab50248f50b9.gif" width="550" height="550">|

* Structure 🌲

|App Package Structure|
|---------------------|
|<img src="https://user-images.githubusercontent.com/50905347/156565001-46ee1ac2-f231-47aa-b802-b49a1abed092.png" width="250" height="125">|

* Application - Features ☕
   * User Status (ONLINE - OFFLINE)
   * Message Status (RECEIVED - READ) - PENDING: After push notification.
   * Friend Status (PENDING, ACCEPTED, CANCELED, BLOCKED)
   * New Message Alert
   * Navigation Animation Between Sceens
   * LazyColumn Last Message Snap
   * UserList Swipe Reflesh
   * Push Notification
   * Dark Theme
   * Language - Turkish

* Tech-stack ⚛️
    * [Compose Desktop](https://www.jetbrains.com/lp/compose-desktop/)
    * [Decompose](https://arkivanov.github.io/Decompose/)
    * [Kotlin](https://kotlinlang.org/) + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operation
    * [Kodein DB](https://docs.kodein.org/kodein-db/0.8/index.html) - database.
    * [Flows](https://developer.android.com/kotlin/flow)
    * [Dagger](https://github.com/google/dagger) - DI
    * [Jetpack](https://developer.android.com/jetpack)
        * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) - deal with whole in-app navigation      
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way
        * [Compose](https://developer.android.com/jetpack/compose)
* Architecture 🏗️
    * Model-View-ViewModel - [with theapache64 compose desktop template](https://github.com/theapache64/compose-desktop-template)
    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel),
 
 * Todo ✔️
   * Main Calculation ❌
   * Dark theme ❌
   * Language - English ❌
