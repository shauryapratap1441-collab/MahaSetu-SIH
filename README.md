# MahaSetu

**Smart India Hackathon 2026 | Problem Statement SIH26129**
System Integration and Interoperability among Government Digital Platforms — Government of Maharashtra
**Theme:** Smart Automation | **Category:** Software | **Team:** innov8ers

---

## Problem

Government digital platforms in Maharashtra — Revenue, Social Welfare, and Cooperation — operate as disconnected silos. Citizens end up re-submitting the same KYC and identity documents across departments because there's no shared identity or status-tracking layer between them.

## Solution

**MahaSetu** is a lightweight, consent-driven middleware layer that sits *between* existing department backends — it doesn't replace them. It gives citizens a single identity and a single status-tracking view across departments, without forcing a rebuild of any department's existing systems.

**What makes it different:**
- **Consent-driven data routing**, not a central data warehouse — no department's data is duplicated or centrally stored
- **Schema-translation adapter layer** so legacy department databases don't need to be touched or rebuilt
- Focuses on the **backend interoperability layer** rather than adding yet another citizen-facing frontend

## Architecture

- **MahaSetu Core Engine** — authentication, consent ledger, SLA monitoring
- **API Gateway / Schema-Matching Mesh** — routes and translates requests between departments
- **Department Adapters** — one each for Revenue, Social Welfare, and Cooperation
- **Unified Status Dashboard** — single view for citizens to track requests across departments

## Tech Stack

| Layer | Technology |
|---|---|
| Backend APIs | Java, Spring Boot |
| Department DB simulation | SQLite / H2 (one instance per simulated department) |
| Frontend | HTML, CSS, JavaScript |
| Auth | Simulated / mocked eKYC |
| Sync | Async webhooks with polling fallback |

## Team — innov8ers

| Member | Role |
|---|---|
| Anurag Kumawat | Auth + consent-ledger design |
| Shaurya Pratap Singh | Backend APIs, department adapter logic, API Gateway / schema-matching mesh |
| Siva Priya | Presentation & documentation |
| Thota Roshini | Schema-mapping & status query logic |
| Hemanth S | Mock department DBs, testing, demo data |

## Running Locally

No public deployment link is provided for this submission — the prototype is designed to be run locally. Steps:

The backend is split into the MahaSetu Core Engine plus one service per simulated department. All four need to be running together for the full flow to work.

1. Open the project in **IntelliJ IDEA**
2. Run each of the following (via the run configuration dropdown or by opening the class and clicking the green run arrow):

| Module | Entry Point Class |
|---|---|
| `mahasetu-core` | `MahasetuCoreApplication.java` (runs on `localhost:8080`) |
| `revenue-service` | `RevenueServiceApplication.java` |
| `welfare-service` | `WelfareServiceApplication.java` |
| `cooperation-service` | `CooperationServiceApplication.java` |

> Each department service runs on its own port — see each module's `application.properties` for the exact port.

**Frontend:**
1. Open the `frontend` folder
2. Open `index.html` directly in a browser
   (or serve it with a simple local server / IDE's Live Server-style extension if it needs to call the backend APIs)

> Start MahaSetu Core and all three department services before opening the frontend, or department-specific requests will fail.

> **Note for evaluators:** A full walkthrough of the running application is also available in our demo video.

## Demo Video

[https://drive.google.com/file/d/1uA-IR9eecpd2dahJuIezG8B0BXR63TaS/view?usp=drive_link]

---

*Built for Smart India Hackathon 2026.*
