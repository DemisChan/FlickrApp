# FlickrApp

FlickrApp is an Android application that allows users to search and view photos from Flickr. The app is built using Kotlin and leverages various libraries and tools such as Retrofit, OkHttp, and Jetpack Compose.

## Features

- Search photos by user ID
- Search photos by tags
- Display photo details including icon server, owner name, description, and date taken

## Getting Started

### Prerequisites

- Android Studio
- Kotlin 1.5+
- Gradle 8.7+
- Internet connection

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/DemisChan/FlickrApp.git
    ```
2. Open the project in Android Studio.
3. Sync the project with Gradle files.

### Building and Running

1. Connect your Android device or start an emulator.
2. Click on the "Run" button in Android Studio or use the following command:
    ```sh
    ./gradlew assembleDebug
    ```

## Project Structure

- **MainActivity.kt**: The main entry point of the application.
- **FlickrRepo.kt**: Repository class that handles network requests to the Flickr API.
- **NetworkService.kt**: Interface defining the API endpoints.
- **ScreenContainer.kt**: Composable function that contains the main screen layout.
- **FlickrViewModel.kt**: ViewModel that manages the UI-related data.

## Libraries Used

- [Retrofit](https://square.github.io/retrofit/): A type-safe HTTP client for Android and Java.
- [OkHttp](https://square.github.io/okhttp/): An HTTP client for Android and Java applications.
- [Gson](https://github.com/google/gson): A Java library that can be used to convert Java Objects into their JSON representation.
- [Jetpack Compose](https://developer.android.com/jetpack/compose): Android’s modern toolkit for building native UI.

## API Key

The Flickr API key is hardcoded in the repository for demonstration purposes. For production use, consider storing API keys securely.

## Contributing

1. Fork the repository.
2. Create your feature branch:
    ```sh
    git checkout -b feature/YourFeature
    ```
3. Commit your changes:
    ```sh
    git commit -m 'Add some feature'
    ```
4. Push to the branch:
    ```sh
    git push origin feature/YourFeature
    ```
5. Open a pull request.

## Screenshots
| Initial Screen | User Photos Screen | Photo Details Screen |
|---------|---------|---------|
|![image](https://github.com/user-attachments/assets/c0ab5078-c1f5-4765-8b43-9faa28e2e718)|![image](https://github.com/user-attachments/assets/c2194421-b49b-42a6-bacb-d2088d7b68ff)|![image](https://github.com/user-attachments/assets/761af28f-379a-4533-8992-ea74180d10cd)|




## Acknowledgements

- [Flickr API](https://www.flickr.com/services/api/)
- [Android Developers](https://developer.android.com/)
