# 🐄 Green Gaushala – Android App for Cow Rescue and Sustainability

Green Gaushala is a socially driven Android application designed to report, rescue, and rehabilitate stray cows through a collaborative platform involving three types of users: public reporters, gaushala caretakers, and small-scale biogas plant owners.

---

## 📱 App Features Overview

### 👤 1. Public Users
- Report stray cows by uploading real-time photos.
- Fill out a form with details like location, description, and time.
- Submit the report which is visible to nearby Gaushalas.

### 🏠 2. Gaushala Admins
- View and prioritize reports based on distance.
- Mark cows as rescued, sheltered, or unreachable.
- Manage cow shelter inventory.
- Track available cow dung and urine for sale.

### 🔋 3. Biogas Plant Owners
- Browse and purchase cow dung or urine.
- Place orders based on availability from Gaushalas.
- Receive notifications when new stock is added.

---

## 🧰 Tech Stack

| Component      | Technology Used             |
|----------------|------------------------------|
| Frontend       | Android (Java, Retrofit)     |
| Backend        | Spring Boot (Java)           |
| Database       | AWS RDS (MySQL)              |
| API Comm.      | RESTful APIs with Retrofit   |
| Image Upload   | Multipart requests to backend|
| Authentication | Role-based access (planned JWT) |
| Hosting        | AWS/Heroku (Spring Boot App) |

---

## 🌐 System Architecture

```mermaid
graph TD
    A[Public User] -->|Report Cow| B[Spring Boot API]
    B --> C[Gaushala Admin Panel]
    B --> D[Image Storage / DB]
    C -->|Mark as Rescued| D
    C -->|Add Stock| E[Cow Dung/Urin Inventory]
    E --> F[Biogas Plant Owner]
````

---

## 📸 Screenshots

> Add app screenshots here (login, report cow, dashboard, stock page, etc.)
> login 
![gaushala Login](https://github.com/user-attachments/assets/102de62d-8d3b-4571-a425-d861ee9ba190)

> Bioplant Interfaces
> Registration
![bioplant reg](https://github.com/user-attachments/assets/89d8f891-985f-4e9e-aa6e-525851a8a42c)
Homepage
> ![bioplant homepage](https://github.com/user-attachments/assets/84bfd311-2675-42aa-bce4-c21fc2824ae0)
Profile
> ![bioplant profile](https://github.com/user-attachments/assets/ace2dc7d-21b0-48a8-a5cd-bea6d4ad40cc)
> Available gaushalas
![avaialable gaushala through bioplant POV](https://github.com/user-attachments/assets/747b8bb2-4c5e-497c-8a0d-b7aa794e42d7)

Gaushala Reports:
![Gaushala Reports](https://github.com/user-attachments/assets/fb451e73-43eb-4973-99ee-a63f9113359c)
Resource Manager  
![Resource Quanities](https://github.com/user-attachments/assets/7e72facd-4d05-434f-867e-1cc344ed853e)
Orders :
![Report Status](https://github.com/user-attachments/assets/8bdcb4c7-296f-47fb-9a9e-96da77f62589)

Reporting Interface:
![reporting interface](https://github.com/user-attachments/assets/8b9551a9-53b9-4eff-831c-601424464559)


---

## 🚀 Getting Started

### Prerequisites

* Android Studio (Java)
* Spring Boot installed or IDE like IntelliJ
* MySQL setup (local or AWS RDS)

### Running the Project

#### Android Client

```bash
1. Clone the repository
2. Open in Android Studio
3. Configure Retrofit base URL (API endpoint)
4. Run on emulator or device
```

#### Spring Boot Backend

```bash
1. Navigate to backend folder
2. Set up `application.properties` for DB and CORS
3. Run the application using:
   ./mvnw spring-boot:run
```

---

## 🛠️ Key Modules

* 📷 `ReportCowActivity.java` – Handles camera input and report form submission.
* 🌍 `LocationUtils.java` – Fetches real-time location via GPS.
* 📦 `GaushalaDashboard.java` – Admin panel to manage cows and stock.
* 🛒 `BiogasBuyerActivity.java` – Interface for purchasing cow by-products.

---

## ✨ Future Enhancements

* Firebase-based push notifications.
* Web-based admin dashboard for Gaushalas.
* Cow QR tagging system.
* Real-time tracking map.
* Payment integration for biogas orders.

---

## 🙌 Contribution

Pull requests are welcome! If you'd like to add features or fix bugs, please fork the repo and submit a PR.

---

## 🧑‍💻 Author

**Sazid Saifi**
Java & Android Developer
📍 Delhi, India
🎓 MCA Student, GGSIPU

---

## 📃 License

This project is licensed under the MIT License. See `LICENSE` for more details.


