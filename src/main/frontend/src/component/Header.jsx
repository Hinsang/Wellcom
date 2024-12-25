import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import logo from '../images/logo.png';
import styles from '../css/Header.css';
import cart from '../images/cart.png';
import login from '../images/login.png';
import logout from '../images/logout.png';
import axios from 'axios';
import search from '../images/search.png';
import upload from '../images/upload.png';
import well from '../images/well.png';
import well2 from '../images/well2.png';
import well3 from '../images/well3.png';
import well4 from '../images/well4.png';

export default function Header({ username }) {

    const [text, setText] = useState("");
    const navigate = useNavigate();

    function logoutHandler() {

        localStorage.removeItem("userInfo")
        localStorage.removeItem("cartList")

    }

    function clickSearchButton(event) {

        if(event.key === "Enter") {

            getSearch();

        }

    }

    function getSearch() {

        const searchText = document.querySelector(".search_input").value;
        setText(searchText)
        navigate('/products/search', { state : { text : searchText } })

    }

    return (

        <div style={{ display:"flex" }}>
            <div>
                <Link to="/" className="logo">
                    <img src={well4} style={{ position: "absolute", width: 40, height: 40, top: 12, left: 232 }} />
                    <img src={logo} alt="Wellcom 로고" style={{width : 338, height : 130}} />
                </Link>
            </div>

            <div className="search_inner">
                <div className="search_box">
                    <input onKeyDown={clickSearchButton} className="search_input"></input>
                    <div className="search_icon_div">
                        <button className="search_button" onClick={getSearch}>
                            <img src={search} />
                        </button>
                    </div>
                </div>
            </div>

            <div className="Toolbar">

                {

                    username === "admin" ? (
                        <div></div>
                    ) : (
                        <div className="Toolbar_content">
                            <Link to="/cart">
                                <div className="Toolbar_content_icon">
                                    <img src={cart} />
                                </div>
                                <div className="Toolbar_text">
                                    장바구니
                                </div>
                            </Link>
                        </div>
                    )

                }

                {
                    username === "" ? (
                        <div className="Toolbar_content">
                            <Link to="/login">
                                <div className="Toolbar_content_icon">
                                    <img src={login} />
                                </div>
                                <div className="Toolbar_text">
                                    로그인
                                </div>
                            </Link>
                        </div>
                    ) : (
                        <div style={{display: "flex"}}>
                            {
                                username === "admin" ? (

                                    <div style={{display: "flex"}}>

                                        <div className="Toolbar_content">
                                            <Link to="/product/upload">
                                                <div className="Toolbar_content_icon">
                                                    <img src={upload} />
                                                </div>
                                                <div className="Toolbar_text">
                                                    상품 등록
                                                </div>
                                            </Link>
                                        </div>

                                        <div className="Toolbar_content">
                                            <Link to="/mypage">
                                                <div className="Toolbar_content_icon">
                                                    <img src={login} />
                                                </div>
                                                <div className="Toolbar_text">
                                                    {username}님<br />
                                                    MY 페이지
                                                </div>
                                            </Link>
                                        </div>

                                    </div>

                                ) : (

                                    <div className="Toolbar_content">
                                        <Link to="/mypage">
                                            <div className="Toolbar_content_icon">
                                                <img src={login} />
                                            </div>
                                            <div className="Toolbar_text">
                                                {username}님<br />
                                                MY 페이지
                                            </div>
                                        </Link>
                                    </div>

                                )
                            }

                            <div className="Toolbar_content">
                                <a href="/logout" onClick={logoutHandler}>
                                    <div className="Toolbar_content_icon">
                                        <img src={logout} />
                                    </div>
                                    <div className="Toolbar_text">
                                        로그아웃
                                    </div>
                                </a>
                            </div>
                        </div>
                    )
                }
            </div>

        </div>
    )

}
