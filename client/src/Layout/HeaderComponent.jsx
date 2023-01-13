import React from 'react'
import { Link } from 'react-router-dom';

export default function HeaderComponent() {
  return (
    <div>
      <header>
        <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
          <Link className="navbar-brand" to="/">Employee Management System</Link>
          <Link className="btn btn-outline-light" to="/add-employee">Add User</Link>
        </nav>
      </header>
    </div>
  )
}