import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import Index from './component/Index';
import axios from 'axios';

// Cors 설정 (가장 최상위 index.js에 추가)
/*const instance = axios.create({
  baseURL: 'http://localhost:8080', // 백엔드 API 기본 포트
  timeout: 10000, // 10초 동안 서버로부터 응답이 없으면 요청이 타임아웃됨
  withCredentials: true, // 쿠키와 인증 정보 전송
});*/

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Index />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
