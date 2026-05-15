# Smart Home Application TODO

## Phase 1 — Clean and Stabilize

- [x] Clean duplicated or invalid HTML in templates.
- [x] Make the sidebar/navigation reusable across pages.
- [x]  Ensure all visible UI text uses localization keys.
- [x]  Add success and error messages for user actions.
- [x]  Improve form validation for registration, login, and device creation.
- [x]  Redirect unauthenticated users away from protected pages.

## Phase 2 — Persistent Device State

- [x] Store device state in the database.
- [x] Add fields such as room, status, brightness, mode, and last updated time.
- [x] Display real user devices on lighting, climate, and security pages.
- [ ] Add edit device functionality.
- [ ] Save light toggles and brightness changes.
- [ ] Save thermostat mode and target temperature.
- [ ] Save security alarm mode.

## Phase 3 — Rooms and Organization

- [ ] Add a Room model.
- [ ] Allow users to create, edit, and delete rooms.
- [ ] Assign devices to rooms.
- [ ] Filter devices by room.
- [ ] Show room summaries on the dashboard.

## Phase 4 — Smart Features

- [ ] Add custom scenes.
- [ ] Allow scenes to control multiple devices.
- [ ] Add automation rules.
- [ ] Add scheduled actions.
- [ ] Add recent activity logs.
- [ ] Show activity history on the dashboard.

## Phase 5 — Security Improvements

- [ ] Replace manual login handling with Spring Security.
- [ ] Hash passwords using BCrypt.
- [ ] Add CSRF protection for forms.
- [ ] Add account settings.
- [ ] Allow users to change email and password.
- [ ] Add logout/session timeout improvements.

## Phase 6 — Dashboard Improvements

- [ ] Show total devices.
- [ ] Show active lights.
- [ ] Show current climate status.
- [ ] Show alarm/security status.
- [ ] Show estimated energy usage.
- [ ] Show recent activity.
- [ ] Add warning cards for offline devices or unusual states.

## Phase 7 — Notifications

- [ ] Add in-app notifications.
- [ ] Notify users about security events.
- [ ] Notify users when a device goes offline.
- [ ] Notify users about unusual temperature or humidity.
- [ ] Optional: add email notifications.

## Phase 8 — API and Real-Time Updates

- [ ] Add REST API endpoints for devices.
- [ ] Add REST API endpoints for rooms.
- [ ] Add REST API endpoints for dashboard summaries.
- [ ] Add AJAX updates for device controls.
- [ ] Optional: add polling for live dashboard refresh.
- [ ] Optional: add WebSocket or Server-Sent Events support.

## Phase 9 — Testing and Quality

- [ ] Add unit tests for user registration.
- [ ] Add unit tests for login validation.
- [ ] Add unit tests for device creation.
- [ ] Add unit tests for device toggling.
- [ ] Add controller tests for main pages.
- [ ] Add tests for protected routes.
- [ ] Add basic integration tests.

## Phase 10 — Deployment and Maintenance

- [ ] Move environment-specific settings out of source files.
- [ ] Add production database configuration.
- [ ] Improve Docker setup.
- [ ] Document setup steps in README.
- [ ] Document future API endpoints.
- [ ] Add backup/export plan for user data.

## Suggested Next Priority

1. Protect authenticated pages.
2. Add validation and friendly error messages.
3. Save real device state in the database.
4. Add rooms and device editing.
5. Add activity logs.