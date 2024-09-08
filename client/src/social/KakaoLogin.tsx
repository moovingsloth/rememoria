import React from 'react';
import kakaoLoginImage from '../assets/kakao_login_medium_wide.png'; // Adjust the path as necessary

// Load environment variables
const KAKAO_REST_API_KEY = process.env.REACT_APP_KAKAO_REST_API_KEY;
const KAKAO_REDIRECT_URI = process.env.REACT_APP_KAKAO_REDIRECT_URI;

const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${KAKAO_REST_API_KEY}&redirect_uri=${KAKAO_REDIRECT_URI}&response_type=code`;

const KaKaoSocialLogin = () => {
  const getAuthToken = () => {
    window.location.href = KAKAO_AUTH_URL;
  };

  return (
    <button onClick={getAuthToken} style={{ background: `url(${kakaoLoginImage}) no-repeat center/contain`, width: '100%', height: '50px', border: 'none' }}>
      <span className="sr-only">Kakao Login</span>
    </button>
  );
};

export default KaKaoSocialLogin;