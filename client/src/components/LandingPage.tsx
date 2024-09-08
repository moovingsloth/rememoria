import React, { useState } from 'react';
import { Bell, Camera, Heart, Home, Search, User, Apple, PlayCircle, Menu, X } from 'lucide-react';
import { Link } from 'react-router-dom';
import Navigation from './Navigation';
import phone from '../assets/phone.png';

export default function LandingPage() {
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  return (
    <div className="flex flex-col min-h-screen bg-white">
      <header className="sticky top-0 z-10 bg-pink-50 text-pink-700">
        <div className="container mx-auto px-4 py-4">
          <div className="flex items-center justify-between">
            <div className="flex items-center">
              <Navigation />
            </div>
            <div className="flex items-center">
              <button className="md:hidden ml-4" onClick={() => setIsMenuOpen(!isMenuOpen)}>
                {isMenuOpen ? <X className="text-pink-700" /> : <Menu className="text-pink-700" />}
              </button>
            </div>
          </div>
          {isMenuOpen && (
            <nav className="mt-4 md:hidden">
              <Link to="/mobile" className="block py-2 text-pink-700 hover:text-pink-500">Mobile</Link>
              <Link to="/developer" className="block py-2 text-pink-700 hover:text-pink-500">Developer</Link>
              <Link to="/blog" className="block py-2 text-pink-700 hover:text-pink-500">Blog</Link>
            </nav>
          )}
        </div>
      </header>

      <main className="flex-grow">
        <section className="bg-pink-500 text-white py-20">
          <div className="container mx-auto px-4 flex flex-col md:flex-row items-center">
            <div className="md:w-1/2 mb-10 md:mb-0">
              <h1 className="text-4xl md:text-6xl font-bold mb-4">찰칵의 덕후와 함께 특별한 순간을 캡처하세요</h1>
              <p className="text-xl mb-6">우리의 앱으로 일상의 모든 순간을 아름답게 기록하세요.</p>
              <Link to="/app" className="bg-white text-pink-500 font-bold py-2 px-6 rounded-full hover:bg-pink-100 transition duration-300">
                지금 시작하기
              </Link>
            </div>
            <div className="md:w-1/2">
              <img
                src={phone}
                alt="찰칵의 덕후 앱 스크린샷"
                className="rounded-lg shadow-2xl mx-auto"
              />
            </div>
          </div>
        </section>

        <section id="features" className="py-20">
          <div className="container mx-auto px-4">
            <h2 className="text-3xl font-bold text-center mb-12">찰칵의 덕후 주요 기능</h2>
            <div className="grid grid-cols-2 md:grid-cols-3 gap-8">
              {[
                { icon: <Camera className="w-12 h-12 text-pink-500" />, title: '중명사진' },
                { icon: <Heart className="w-12 h-12 text-pink-500" />, title: '웨딩사진' },
                { icon: <User className="w-12 h-12 text-pink-500" />, title: '가족사진' },
                { icon: <Bell className="w-12 h-12 text-pink-500" />, title: '아기사진' },
                { icon: <Home className="w-12 h-12 text-pink-500" />, title: '풍경사진' },
                { icon: <Search className="w-12 h-12 text-pink-500" />, title: '기타' },
              ].map((feature, index) => (
                <div key={index} className="flex flex-col items-center text-center">
                  {feature.icon}
                  <h3 className="mt-4 text-xl font-semibold">{feature.title}</h3>
                </div>
              ))}
            </div>
          </div>
        </section>

        <section id="download" className="bg-pink-100 py-20">
          <div className="container mx-auto px-4 text-center">
            <h2 className="text-3xl font-bold mb-8">찰칵의 덕후를 지금 다운로드하세요</h2>
            <p className="text-xl mb-8">iOS와 Android에서 모두 사용 가능합니다.</p>
            <div className="flex flex-col sm:flex-row justify-center space-y-4 sm:space-y-0 sm:space-x-4">
              <button className="bg-black text-white font-bold py-3 px-8 rounded-lg hover:bg-gray-800 transition duration-300 flex items-center justify-center w-full sm:w-auto">
                <Apple className="w-6 h-6 mr-2" />
                App Store
              </button>
              <button className="bg-green-600 text-white font-bold py-3 px-8 rounded-lg hover:bg-green-700 transition duration-300 flex items-center justify-center w-full sm:w-auto">
                <PlayCircle className="w-6 h-6 mr-2" />
                Google Play
              </button>
            </div>
          </div>
        </section>
      </main>

      <footer className="bg-pink-50 text-pink-700 py-8">
        <div className="container mx-auto px-4">
          <div className="flex flex-col md:flex-row justify-between items-center">
            <div className="text-2xl font-bold mb-4 md:mb-0">찰칵의 덕후</div>
            <nav className="flex flex-wrap justify-center md:justify-end space-x-4">
              <a href="#" className="hover:text-pink-500">개인정보 처리방침</a>
              <a href="#" className="hover:text-pink-500">이용약관</a>
              <a href="#" className="hover:text-pink-500">문의하기</a>
            </nav>
          </div>
          <div className="mt-8 text-center text-sm">
            © {new Date().getFullYear()} 찰칵의 덕후. All rights reserved.
          </div>
        </div>
      </footer>
    </div>
  );
}