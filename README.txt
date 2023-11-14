# RickAndMortyApp

Rick and Morty Compose

An android app built using Kotlin that consumes Rick and Morty API to display characters,episodes,Location from the TV Series. It has been built following Clean Architecture Principle, Repository Pattern, MVVM Architecture in the presentation layer as well as Jetpack components(including Jetpack Compose).

Prerequisite.
In order to be able to build the application you'll need Android Studio Minimum version Android Studio Chipmunk May Build

Disclaimer.
Complex architectures like the pure clean architecture can also increase code complexity since decoupling your code also means creating lots of data transformations(mappers) and models, that may end up increasing the learning curve of your code to a point where it would be better to use a simpler architecture like MVVM.

Clean arcitecture has only been used in this repository to help me learn Clean Architecture and it's not recommended to use clean architecture in a small scale projects such as this one

So let's get started ...

Architecture.
What is Clean Architecture?
A well planned architecture is extremely important for an app to scale and all architectures have one common goal- to manage complexity of your app. This isn't something to be worried about in smaller apps however it may prove very useful when working on apps with longer development lifecycle and a bigger team.

Clean architecture was proposed by Robert C. Martin in 2012 in the Clean Code Blog and it follow the SOLID principle.

The circles represent different layers of your app. Note that:

The center circle is the most abstract, and the outer circle is the most concrete. This is called the Abstraction Principle. The Abstraction Principle specifies that inner circles should contain business logic, and outer circles should contain implementation details.

Another principle of Clean Architecture is the Dependency Inversion. This rule specifies that each circle can depend only on the nearest inward circle ie. low-level modules do not depend on high-level modules but the other way around.

Why Clean Architecture?
Loose coupling between the code - The code can easily be modified without affecting any or a large part of the app's codebase thus easier to scale the application later on.
Easier to test code.
Separation of Concern - Different modules have specific responsibilities making it easier for modification and maintenance.
S.O.L.I.D Principles.
Single Responsibility: Each software component should have only one reason to change – one responsibility.

Open-Closed: You should be able to extend the behavior of a component, without breaking its usage, or modifying its extensions.

Liskov Substitution: If you have a class of one type, and any subclasses of that class, you should be able to represent the base class usage with the subclass, without breaking the app.

Interface Segregation: It’s better to have many smaller interfaces than a large one, to prevent the class from implementing the methods that it doesn’t need.

Dependency Inversion: Components should depend on abstractions rather than concrete implementations. Also higher level modules shouldn’t depend on lower level modules.

Layers.
1. Domain.
This is the core layer of the application. The domain layer is independent of any other layers thus ] domain models and business logic can be independent from other layers.This means that changes in other layers will have no effect on domain layer eg. screen UI (presentation layer) or changing database (data layer) will not result in any code change withing domain layer.

Components of domain layer include:

Models: Defines the core structure of the data that will be used within the application.

Repositories: Interfaces used by the use cases. Implemented in the data layer.

Use cases/Interactors: They enclose a single action, like getting data from a database or posting to a service. They use the repositories to resolve the action they are supposed to do. They usually override the operator “invoke”, so they can be called as a function.

2. Data.
The data layer is responsibile for selecting the proper data source for the domain layer. It contains the implementations of the repositories declared in the domain layer.

Components of data layer include:

Models

-Dto Models: Defines POJO of network responses.

-Entity Models: Defines the schema of SQLite database.

Repositories: Responsible for exposing data to the domain layer.

Mappers: They perform data transformation between domain, dto and entity models.

Network: This is responsible for performing network operations eg. defining API endpoints using Retrofit.

Cache: This is responsible for performing caching operations using Room.

Data Source: Responsible for deciding which data source (network or cache) will be used when fetching data.

3. Presentation.
The presentation layer contains components involved in showing information to the user. The main part of this layer are the views(activity) and viewmodels.



Libraries.
Hilt - Dependency Injection library.

Jetpack

Android KTX - Provide concise, idiomatic Kotlin to Jetpack and Android platform APIs.

Jetpack Compose - Jetpack Compose is Android’s modern toolkit for building native UI. It simplifies and accelerates UI development on Android.

AndroidX - Major improvement to the original Android Support Library, which is no longer maintained.

Lifecycle - Perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.

ViewModel - Designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.

Compose Navigation -Component that allows easier implementation of navigation from composables.

Retrofit - Type-safe http client and supports coroutines out of the box.

OkHttp-Logging-Interceptor - Logs HTTP request and response data.

Coroutines - Library Support for coroutines.

Flow - Flows are built on top of coroutines and can provide multiple values. A flow is conceptually a stream of data that can be computed asynchronously.

Square/Logcat -Library for easier logging.

Material Design - Build awesome beautiful UIs.

Glide - Jetpack Compose image loading library which fetches and displays network images with Glide, Coil, and Fresco.

kotlinx.coroutines - Library Support for coroutines,provides runBlocking coroutine builder used in tests.

Compose-Accompanist -Accompanist is a group of libraries that aim to supplement Jetpack Compose with features that are commonly required by developers but not yet available.

Navigation Animation A library which provides Compose Animation support for Jetpack Navigation Compose.
System UI Controller System UI Controller provides easy-to-use utilities for updating the System UI bar colors within Jetpack Compose.
