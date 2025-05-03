# 🗨️ ITI-Java-Chat-Room

<div align="center">
  <h1>Java Chat Room Application</h1>
  <p>A multi-user Java chat application with client-server architecture and a user-friendly JavaFX GUI.</p>
</div>

<div align="center">
  <img src="https://img.shields.io/badge/Java-11%2B-orange" alt="Java 11+">
  <img src="https://img.shields.io/badge/JavaFX-GUI-blue" alt="JavaFX GUI">
  <img src="https://img.shields.io/badge/Architecture-Client--Server-green" alt="Client-Server">
  <img src="https://img.shields.io/badge/Database-MySQL-blue" alt="MySQL">
  <img src="https://img.shields.io/badge/Feature-Multithreading-red" alt="Multithreading">
</div>

## 🚀 Features

- **Real-time messaging**: Chat with multiple users simultaneously
- **User status management**: Set your status as Available, Busy, or Offline
- **User authentication**: Secure login and registration system
- **Chat history**: Automatically save and load your chat history
- **User management**: Admin panel for user administration
- **Online user list**: See who's currently online and their status
- **Multithreaded server**: Handles multiple client connections concurrently
- **Responsive UI**: Built with JavaFX for an intuitive user experience

## 🏗️ Architecture

The application follows a client-server architecture:

### Server Components
- **Server**: Core server that handles client connections using multithreading
- **ClientHandler**: Manages individual client sessions in separate threads
- **Database**: MySQL database integration for user data storage and retrieval
- **Admin**: Administrative interface for user management with real-time updates

### Client Components
- **Login/Registration**: User authentication interface with form validation
- **Chat Interface**: Main messaging UI with status management and real-time updates
- **User list display**: Shows online users and their status with automatic refresh
- **Threading**: Background threads for receiving messages without blocking the UI

## 🛠️ Technology Stack

- **Backend**: Java with multithreading for concurrent operations
- **GUI Framework**: JavaFX for creating responsive and interactive user interfaces
- **Database**: MySQL for persistent data storage
- **Build Tool**: Maven for dependency management and building
- **Network**: Socket programming for client-server communication
- **Design Pattern**: MVC (Model-View-Controller) pattern for the application structure

## 💻 Implementation Details

- **Socket Programming**: Uses Java sockets for network communication
- **Multithreading**: Server creates a new thread for each client connection
- **JavaFX UI**: Modern user interface with CSS styling
- **Data Persistence**: Chat history saved to local files for each user
- **State Management**: Real-time status updates for all connected users
- **Concurrency Control**: Thread-safe implementations for shared resources

## 📋 Prerequisites

- Java Development Kit (JDK) 11 or higher
- MySQL Server
- Maven

## 🚀 Getting Started

### Database Setup

1. Create a MySQL database 

### Running the Server

1. Navigate to the `projectserver` directory
2. Run the server application:
   ```
   mvn clean javafx:run
   ```

### Running the Client

1. Navigate to the `projectclient` directory
2. Run the client application:
   ```
   mvn clean javafx:run
   ```

## 📝 Usage

1. **Register**: Create a new account if you don't have one
2. **Login**: Enter your credentials to access the chat room
3. **Chat**: Send messages to all connected users
4. **Status**: Toggle your status between Available and Busy
5. **View Users**: See who's currently online and their status
6. **Save Chat**: Save your chat history locally

## 🎥 Demo

Check out the demo video to see the application in action:
[Demo Video](https://youtu.be/9HmFjMOfo_k)



