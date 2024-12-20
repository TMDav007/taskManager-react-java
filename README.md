# taskManager-react-java

1. Navigate to the frontend directory:
   ```bash
   cd ../frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the React application:
   ```bash
   npm start
   ```
4. Frontend will be accessible at: `http://localhost:3000`

---

## API Endpoints

### Base URL:
`http://localhost:8080/api/tasks-list`

| Method | Endpoint                      | Description              |
|--------|-------------------------------|--------------------------|
| GET    | `/tasks-lists/{id}`           | Retrieve all task lists. |
| POST   | `/tasks-lists`                | Create a new task list   |
| PUT    | `/tasks-lists/{id}`           | Update a task by ID.     |
| DELETE | `tasks-lists/{id}`            | Delete a task by ID.     |
| GET    | `/tasks-lists/{id}/tasks/{id}`| Retrieve all task .      |
| POST   | `/tasks-lists/{id}/tasks/`    | Create a new task        |
| PUT    | `/tasks-lists/{id}/tasks/{id}`| Update a task by ID.     |
| DELETE | `/tasks-lists/{id}/tasks/{id}`| Delete a task by ID.     |

---

## Folder Structure

```
.
├── backend
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com.tolz.tasks
│   │   │   │       ├── controllers  # REST Controllers
│   │   │   │       ├── services     # Business Logic
│   │   │   │       ├── domain       # Entity Classes
│   │   │   │       └── repositories  # Data Access
│   │   └── resources
│   │       └── application.properties
├── frontend
│   ├── src
│   │   ├── components  # React Components
│   │   ├── AppProvider    # API
│   │   └── App.tsx      # Entry Point
└── README.md
```

---

## Future Enhancements
- Add user authentication (e.g., login/logout).
- Implement drag-and-drop task prioritization.
- Add due dates with reminders.
- Use a production-ready database like PostgreSQL.

---
