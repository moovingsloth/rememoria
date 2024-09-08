import React, { useEffect, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { kakaoLogin } from './service/AuthenticationService';

const Oauth2RedirectHandler: React.FC = () => {
    const params = new URL(document.URL).searchParams;
    const code = params.get('code');
    const navigate = useNavigate();
    const hasRun = useRef(false); // Ref to track if the effect has run

    useEffect(() => {
        const handleLogin = async () => {
            if (code) { // Ensure code is not null
                try {
                    console.log('kakaoLogin called'); // 추가 로그

                    const response = await kakaoLogin(code);
                    console.log('kakaologin');
                    navigate('/app');
                } catch (error: unknown) {
                    console.log('kakao login failed', error);
                    navigate('/login');
                }
            } else {
                console.error('No code found in URL');
                navigate('/login');
            }
        };

        if (!hasRun.current) {
            handleLogin();
            hasRun.current = true; // Mark the effect as run
        }
    }, [code, navigate]);

    return (
        <div>
            Redirecting...
        </div>
    );
};

export default Oauth2RedirectHandler;