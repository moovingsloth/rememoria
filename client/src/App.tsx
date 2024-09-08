import React from 'react';
import { Routes, Route } from 'react-router-dom';
import LandingPage from './components/LandingPage';
import Login from './components/Login';
import MainApp from './components/MainApp';
import Oauth2RedirectHandler from './components/Oauth2RedirectHandler';

function App() {
  return (
    <Routes>
      <Route path="/" element={<LandingPage />} />
      <Route path="/login" element={<Login />} />
      <Route path="/app" element={<MainApp />} />
      <Route path="/login/oauth/kakao" element={<Oauth2RedirectHandler />} />
    </Routes>
  );
}

export default App;