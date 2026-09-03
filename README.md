# MahaSetu

**Smart India Hackathon 2026 | Problem Statement SIH26129**
System Integration and Interoperability among Government Digital Platforms — Government of Maharashtra
**Theme:** Smart Automation | **Category:** Software | **Team:** innov8ors

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
| Automation service | Python / Node.js — natural-language status queries, schema-mapping assist |
| Auth | Simulated / mocked eKYC |
| Sync | Async webhooks with polling fallback |

## Team — innov8ers

| Member | Role |
|---|---|
| Anurag Kumawat | Auth + consent-ledger design |
| Shaurya Pratap Singh | Backend APIs, department adapter logic, API Gateway / schema-matching mesh |
| Siva Priya | Presentation & documentation |
| Thota Roshini | Automation service (NL status queries, schema-mapping) |
| Hemanth S | Mock department DBs, testing, demo data |

## Running Locally

No public deployment link is provided for this submission — the prototype is designed to be run locally. Steps:

1. Clone the repo
2. Open the backend module in **IntelliJ IDEA**
3. Run the Spring Boot application (`MahaSetuApplication.java` or equivalent entry point)
4. [Add: how to start the automation service, e.g. `python app.py` / `node index.js`]
5. [Add: how to start/serve the frontend, e.g. `npm install && npm start`]
6. Once all services are running, open the frontend at `http://localhost:[PORT]`

> **Note for evaluators:** A full walkthrough of the running application is also available in our demo video.

## Demo Video

[Add link to demo video here]

---

*Built for Smart India Hackathon 2026.*
