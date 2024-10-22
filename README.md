# PersonaGen

<div style="display: flex; justify-content: space-around;">
  <img src="https://github.com/user-attachments/assets/a2cfd4e8-9858-4bd9-b088-c60222206b83" alt="GIF 1" width="300" height="500">
  <img src="https://github.com/user-attachments/assets/a792a509-9158-4fb3-b401-9c5e64ce7552" alt="GIF 2" width="300" height="500">
</div>

PersonaGen is a user-generation app that allows you to create and manage a list of random users based on selected nationality and gender. It fetches user data such as name, location, and other personal details from the RandomUser.me API, and stores it locally for offline access. The app is built using the MVVM architecture, RxJava for reactive programming, and Material Design components for an intuitive and modern UI. A built-in network connection check ensures that users are notified when they're offline, keeping the user experience seamless.

Features:
- Random User Generation: Generate users by choosing nationality and gender.
- Data Persistence: All generated user data is stored locally to avoid data loss.
- Offline Support: Access previously generated users when there's no internet connection.
- Network Connection Alert: Displays a dialog if there's no network, prompting the user to check their connection.
- Modern UI: The app uses Material Design principles for a clean and user-friendly interface.
- Clean Architecture: Built with MVVM architecture for better separation of concerns and testability.


Tech Stack:
- Programming Language: Java
- Architecture: MVVM (Model-View-ViewModel) for separation of concerns and clean code structure
- Networking: Retrofit for API requests
- Reactive Programming: RxJava for handling asynchronous data streams
- Database: Room for local data persistence and offline access
- UI/UX: Material Design components for a modern and intuitive interface
