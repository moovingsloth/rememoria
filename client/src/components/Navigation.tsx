import React from 'react';
import { Link } from 'react-router-dom';
import { Apple } from 'lucide-react'; // Assuming you are using react-icons
import logo from '../assets/logo.png';

const Navigation: React.FC = () => {
  return (
	<nav className="fixed top-0 left-0 w-full bg-pink-50 shadow-md z-10 hidden md:flex items-center justify-between p-6"> {/* Increased padding for height */}
	  <div className="flex items-center space-x-6">
		<Link to="/" className="flex items-center">
		  <img src={logo} alt="Logo" className="w-10 rounded-full h-10 mr-3" />
		</Link>
		<Link to="/mobile" className="text-pink-700 hover:text-pink-500">Mobile</Link>
		<Link to="/developer" className="text-pink-700 hover:text-pink-500">Developer</Link>
		<Link to="/blog" className="text-pink-700 hover:text-pink-500">Blog</Link>
	  </div>
	  <div className="flex items-center space-x-3">
		<button className="bg-black text-white font-bold py-2 px-4 rounded-lg hover:bg-gray-700 transition duration-300 flex items-center justify-center">
		  <Apple className="w-5 h-5 mr-2" />
		  App Store
		</button>
		<Link to="/login" className="items-center px-4 py-2 bg-pink-100 font-medium rounded-md">Get Started</Link>
	  </div>
	</nav>
  );
};

export default Navigation;