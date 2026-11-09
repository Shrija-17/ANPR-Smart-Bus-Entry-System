# 🚌 ANPR-Based Smart Bus Entry System

## 📌 Project Overview

The **ANPR-Based Smart Bus Entry System** is an intelligent bus gate management application that automates bus identification and entry management using Artificial Intelligence and Automatic Number Plate Recognition (ANPR).

The system uses YOLOv8-based number plate detection to identify vehicle registration plates. The detected information is processed and integrated with a Java-based bus management application to manage registered buses, monitor entry activities, and maintain bus entry records.

## 🎯 Objectives

- Automate bus identification at the entry gate.
- Detect vehicle number plates using AI.
- Maintain registered bus information.
- Monitor and record bus entry activities.
- Reduce manual verification at bus entry points.
- Improve campus transportation security and management.

## ⚙️ Technologies Used

| Component | Technology |
|---|---|
| Backend | Java, Spring Boot |
| AI & Image Processing | Python, YOLOv8 |
| Computer Vision | OpenCV |
| Number Plate Recognition | OCR (if integrated) |
| Frontend | HTML, CSS |
| Database | As configured in the Java application |
| Development Tools | IntelliJ IDEA, VS Code |
| Version Control | Git & GitHub |

## 🧠 AI Module – ANPR Detection

The Python module uses YOLOv8 to detect vehicle number plates from camera images.

**Process:**
1. Capture the vehicle image using a camera.
2. Detect the number plate using YOLOv8.
3. Identify and crop the detected number plate region.
4. Extract the registration number using the configured recognition method.
5. Send the detected information to the Java application.

## 🚍 Java Module – Smart Bus Gate Management

The Java application manages bus-related information and entry activities.

**Key functionalities:**
- Bus registration and management.
- Registered bus verification.
- Bus entry log management.
- Admin dashboard.
- Camera interface.
- Entry activity monitoring.

## 🔄 System Workflow

1. A bus arrives at the entry gate.
2. The camera captures the vehicle image.
3. The Python AI module detects the number plate using YOLOv8.
4. The detected registration number is passed to the Java application.
5. The Java application verifies the bus details.
6. The system displays the corresponding bus information and entry status.
7. The entry activity is recorded for monitoring.

## 📂 Project Structure

```text
ANPR-Smart-Bus-Entry-System/
│
├── SmartBusGateManagement/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
│
├── ANPR-Python/
│   ├── models/
│   │   └── best.pt
│   ├── output/
│   ├── camera_test.py
│   └── plate_detection.py
│
└── README.md
```

## 💡 Applications

- College campus bus entry management.
- School transportation management.
- Institutional vehicle monitoring.
- Automated vehicle access management.
- Smart parking and gate management systems.

## 🚀 Future Enhancements

- Real-time CCTV integration.
- Automatic barrier gate control.
- SMS and email notifications.
- Advanced vehicle tracking.
- Detailed analytics and reporting.
- Improved number plate recognition under different lighting and weather conditions.

## 👩‍💻 Development

Developed as an AI-integrated smart transportation and security management project using Java and Python.

**GitHub Repository:**  
https://github.com/Shrija-17/ANPR-Smart-Bus-Entry-System

---

*Combining Artificial Intelligence, Computer Vision and Application Development to create smarter and more efficient bus entry management.*
