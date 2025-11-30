import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';

/**
 * Entry point of the React application.
 * Bootstraps the React application by rendering the root {@link App} component
 * into the DOM element with id="root".
 *
 * React StrictMode is enabled in development to highlight potential problems
 * and help identify side effects.
 */
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);


