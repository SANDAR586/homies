# 📡 Android Peer-to-Peer Chat App (Wi-Fi Direct + Sockets)

A lightweight Android chat application built from scratch using **Wi-Fi Direct** and **socket programming**, enabling two Android devices to connect and exchange messages **without internet access**. This project demonstrates a deeper understanding of Android’s networking APIs and system-level components beyond typical UI-focused development.

---

## 🚀 Features

- 🔌 Peer-to-peer connection using **Wi-Fi Direct** (`WifiP2pManager`)
- 📬 Real-time **message exchange over sockets**
- 🧠 Custom **host-client negotiation** logic
- ⚙️ Background thread management for **socket I/O**
- 📶 Works entirely **offline**, no server or internet required
- 🛠️ Minimal UI — focused on system-level networking

---

## 📷 Screenshots

> _Include a few screenshots of the app showing connection and chat messages._

---

## 🛠️ Tech Stack

- **Language:** Java / Kotlin
- **API Used:** `WifiP2pManager`, Java Sockets
- **Min SDK:** 21 (Android 5.0)
- **Architecture:** MVVM-lite + Threads / AsyncTasks (or Coroutines, if using Kotlin)

---

## 🧠 What This Project Demonstrates

> “I wanted to go beyond building traditional UI apps and dive deeper into how Android manages peer-to-peer networking and sockets at the system level.”

This project shows:
- A solid grasp of **low-level networking**
- Experience working with **Android OS internals**
- Manual handling of connection states, socket lifecycle, and device roles
- Real-world use of **asynchronous communication** and multithreading

---

## 📲 How to Build & Run

1. Clone this repo:
   ```bash
   git clone https://github.com/your-username/wifidirect-chat.git




### Folder Architecture 
/app
├── /data
│     ├── /model          # Data models (e.g., PeerDevice, VideoFrame, ConnectionState)
│     ├── /repository     # Data sources, Wi-Fi Direct and socket communication logic
│     └── /network        # Socket server/client, Wi-Fi Direct low-level APIs
│
├── /domain
│     ├── /usecase        # Business logic (e.g., DiscoverPeersUseCase, ConnectToDeviceUseCase)
│     └── /model          # Domain-level models (can mirror data models or be more abstract)
│
├── /ui
│     ├── /host           # Host screen (server) Compose UI + MVI components
│     ├── /client         # Client screen (finder) Compose UI + MVI components
│     ├── /common         # Shared UI components, themes, styles
│     └── /navigation     # Navigation graph, routing logic
      └── /receivers      # actions listeners and receivers
│
├── /util                # Utility classes and helpers (e.g., extension functions, logging)
│
├── /di                  # Dependency Injection (e.g., Koin modules)
│
└── /service             # Background services if needed (e.g., socket communication running in background)

