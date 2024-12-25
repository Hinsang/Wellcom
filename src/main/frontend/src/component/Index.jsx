import React, { useState, useEffect } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Header from './Header';
import Home from './Home';
import Footer from './Footer';
import Login from './member/Login';
import Cart from './product/Cart';
import MyPage from './member/MyPage'
import styles from '../css/Index.css';
import Signup from './member/Signup';
import Upload from './product/Upload';
import Detail from './product/Detail';
import Search from './product/Search';
import axios from 'axios';

export default function Index() {

    const [username, setUsername] = useState("");
    const [user, setUser] = useState({})

    useEffect(() => {
        loadUser()
    }, username)

    function loadUser() {

        axios.get("/member/loadUser")
        .then(res => {
            console.log(res.data, "로드유저성공!!");
            if(res.data.username == null || res.data.username == "") {
                console.log(res.data.username)
                console.log("유저 정보가 없습니다");
            } else {
                // window.location.href= "/";
                setUsername(res.data.username);
                setUser(res.data)
            }
        }).catch(err => {
            console.log(err, "로드유저실패!!");
        })

    }

    return(
        <div className="main_inner">
            <BrowserRouter>
                <Header username={username} />
                <Routes>
                    <Route path="/" element={<Home user={user} />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/cart" element={<Cart />} />
                    <Route path="/signup" element={<Signup />} />
                    <Route path="/mypage" element={<MyPage user={user} />} />
                    <Route path="/products/search" element={<Search />} />
                    <Route path="/product/upload" element={<Upload />} />
                    <Route path="/product/detail/:pid" element={<Detail />} />
                </Routes>
                <Footer />
            </BrowserRouter>
        </div>
    )

}
