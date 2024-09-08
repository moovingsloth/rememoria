import React from 'react';
import { Link } from 'react-router-dom';
import Navigation from './Navigation';

export default function MainApp() {
  return (
    <div className="min-h-screen bg-gray-100">
      <Navigation />{}
      <header className="bg-white shadow">
        <div className="max-w-7xl mx-auto py-6 px-4 sm:px-6 lg:px-8">
        </div>
      </header>
      <main>
        <div className="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
          <div className="px-4 py-6 sm:px-0">
            <div className="border-4 border-dashed border-gray-200 rounded-lg h-96 flex items-center justify-center">
              <p className="text-2xl text-gray-500">여기에 앱 콘텐츠가 들어갑니다.</p>
            </div>
          </div>
          <div className="mt-8 text-center">
            <Link to="/" className="text-pink-600 hover:text-pink-500 font-medium">
              홈으로 돌아가기
            </Link>
          </div>
        </div>
      </main>
    </div>
  );
}
