# ✨ Recruitment Management System

![Java](https://img.shields.io/badge/Java-17-blue) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen) ![JWT](https://img.shields.io/badge/Security-JWT%20Authentication-red)

> **Assessment Submission:**  


---

## 🚀 Overview

A secure, role-based recruitment platform empowering admins and applicants to manage job postings, applications, and resume uploads. Implemented with RESTful APIs, JWT authentication, file upload, and CRUD logic typical of modern job portals.

---

## 🛠️ Technologies Used

| Technology    | Version |
| ------------- | ------- |
| Java          | 17      |
| Spring Boot   | 3.x     |
| Spring Security| JWT    |
| Maven/Gradle  | Latest  |
| Database      | H2/MySQL|
| Postman       | For API Testing |
| BCrypt        | For secure password hashing |

---

## 🌟 Core Features

- **User Registration** as Admin/Applicant
- **JWT Authentication** for protected API access
- **Job Management** – create, view, and fetch job details
- **Application Management** – apply for jobs
- **Resume Upload** (PDF/DOCX only)
- **Role-based Security** – Only Admins or Applicants access appropriate endpoints

---

## 📖 Endpoints Summary

| Method | Endpoint                         | Description                | Access        |
|--------|----------------------------------|----------------------------|---------------|
| POST   | /api/signup                      | Register new user          | Public        |
| POST   | /api/login                       | Login & receive JWT        | Public        |
| POST   | /api/uploadResume                | Upload resume (PDF/DOCX)   | Applicant     |
| POST   | /api/admin/job                   | Create job opening         | Admin         |
| GET    | /api/admin/job/{job_id}          | Job details & applicants   | Admin         |
| GET    | /api/admin/applicants            | List all users             | Admin         |
| GET    | /api/admin/applicant/{id}        | Specific applicant info    | Admin         |
| GET    | /api/jobs                        | View jobs                  | Authenticated |
| GET    | /api/jobs/apply?job_id={id}      | Apply for a job            | Applicant     |

---

## ⚡ Quick Start

git clone https://github.com/dipanshu2001/RecruitementManagenmentSystem.git
cd RecruitementManagenmentSystem
./mvnw spring-boot:run

text

---

## 📦 How To Use

1. **Signup:** Register with your role (Admin/Applicant).
2. **Login:** Obtain JWT token.
3. **Authenticated Requests:**  
   - Add header: `Authorization: Bearer <token>`
   - For file uploads, use multipart form-data: `file` key, type File.
4. **Admin Actions:** Create/view jobs, list/view applicants.
5. **Applicant Actions:** View jobs, apply, upload resume.

---

## 🖼 Example Request (Postman)

**Uploading Resume:**
- Select Body → `form-data`
- Key: `file` (type: File), Value: choose your PDF/DOCX
- Header: `Authorization: Bearer <applicant-token>`

---

## 🎯 Design Highlights

- Secure, stateless JWT authentication
- Role checks enforced server-side
- Passwords stored hashed, never in plain text
- Exception handling for unauthorized/invalid requests

---

## 👥 Contact

Dipanshu Joshi  
[GitHub: dipanshu2001](https://github.com/dipanshu2001)  

---
