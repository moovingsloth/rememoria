import axios from 'axios';

const BACK_END_URL = process.env.REACT_APP_BACK_END_URL;

export const kakaoLogin = async (code: string) => {
    console.log('kakaoLogin called with code:', code); // 추가 로그
    try {
        const response = await axios.get(`${BACK_END_URL}?code=${code}`);
        return response.data;
    } catch (error) {
        console.error('Error during Kakao login', error);
        throw error;
    }
};
