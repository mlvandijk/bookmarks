# Development process
Make sure the application builds by running `mvn verify`.
Make sure all tests pass.

### Core Technologies
- Spring Boot 3.x
- Java 17
- Thymeleaf
- CSS

# Application Style Guide

## Design Framework
- **Framework**: Bootstrap 5.3.0
- **Layout System**: Bootstrap's container and spacing utilities
- **CDN Links**:
  ```html
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
  ```

## Layout & Spacing
- **Container**: Use `container` class for main content areas
- **Top Margin**: `mt-5` for main sections
- **Component Spacing**: `mb-3` for form groups and content sections
- **Responsive Tables**: `table-responsive` for horizontal scrolling on small screens

## Typography
### Headers
- Main Page Title: `<h1>`
- Card Headers: `<h3>` with appropriate classes
- Section Titles: `<h5 class="card-title">`

## Color Scheme
### Button Colors
- **Primary Actions**: `btn-primary` (blue)
    - Example: Save, Add New
- **Secondary Actions**: `btn-secondary` (gray)
    - Example: Cancel, Back
- **Warning Actions**: `btn-warning` (yellow)
    - Example: Edit
- **Danger Actions**: `btn-danger` (red)
    - Example: Delete
- **Info Actions**: `btn-info` (light blue)
    - Example: View

## Components

### Tables
```html
<table class="table table-striped table-hover">
    <thead class="table-dark">
        <!-- content -->
    </thead>
</table>
```
- Use striped rows
- Include hover effects
- Dark themed headers

### Forms
```html
<div class="mb-3">
    <label class="form-label">Label</label>
    <input class="form-control">
</div>
```
- Form controls with `form-control` class
- Labels with `form-label` class
- Consistent spacing using `mb-3`

### Cards
```html
<div class="card">
    <div class="card-header">
        <!-- header content -->
    </div>
    <div class="card-body">
        <!-- main content -->
    </div>
    <div class="card-footer">
        <!-- footer content -->
    </div>
</div>
```
- Use for detailed views
- Include header, body, and footer sections
- Flexible header layouts with `d-flex` and `justify-content-between`

### Buttons
- **Standard Buttons**: `class="btn btn-{variant}"`
- **Small Buttons**: `class="btn btn-{variant} btn-sm"`
- **Button Groups**: Wrap related buttons in `<div class="btn-group">`

## Interactive Elements

### Links
```html
<!-- External Links -->
<a href="url" target="_blank">Link Text</a>

<!-- Action Links -->
<a href="url" class="btn btn-{variant}">Action Text</a>
```

### Confirmation Dialogs
```html
<button onclick="return confirm('Are you sure?')">Delete</button>
```
- Use for destructive actions
- Clear warning messages

## Responsive Design
- Implement table responsiveness
- Use Bootstrap's flex utilities
- Maintain container-based layouts

## Error Handling
```html
<div class="container">
    <h1>Error</h1>
    <p>[Error Message]</p>
    <a href="/" class="btn btn-secondary">Back</a>
</div>
```
- Clean, simple error page design
- Clear error message display
- Easy navigation back to main content

## Best Practices
1. Maintain consistent spacing throughout the application
2. Use appropriate button colors based on action type
3. Implement responsive design patterns
4. Include confirmation for destructive actions
5. Provide clear navigation paths
6. Use semantic HTML elements
7. Follow Bootstrap's component guidelines