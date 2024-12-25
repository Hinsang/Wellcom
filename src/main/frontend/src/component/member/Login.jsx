import React from 'react';
import styles from '../../css/Login.css';
import { Link } from 'react-router-dom';
import axios from 'axios';

export default function Login() {

    function login() {

        let username = document.querySelector(".username").value;
        let password = document.querySelector(".password").value;

        // info 객체에 넣어서 key = value 형식으로 보내지 말고 FormData 형식으로 보내야 한다.
        // 며칠 동안 삽질하며 얻은 깨달음 ㅠㅠ
        let formData = new FormData();
        formData.append("username", username);
        formData.append("password", password);

        let checkUser = formData.get("username");
        console.log(checkUser);

        if(checkUser !== null) {
            axios.post("/member/login", formData)
            .then(res => {
                console.log("성공!!");
                loadUser();
            }).catch(err => {
                console.log(err, "오류");
            })
        }

    }

    function LoginButtonClick(event) {

        if(event.key === "Enter") {

            document.querySelector(".Login_form_button").click();

        }

    }

    function loadUser() {

        axios.get("/member/loadUser")
        .then(res => {
            console.log(res.data, "로드유저성공!!");
            if(res.data.username == null || res.data.username == "") {
                console.log(res.data.username);
                console.log("유저 정보가 없습니다");
            } else {
                localStorage.setItem('userInfo', JSON.stringify(res.data))
                window.location.href= "/";
            }
        }).catch(err => {
            console.log(err, "로드유저실패!!");
            alert("아이디 또는 비밀번호가 일치하지 않습니다.")
        })

    }

    return (
        <div>
            <div className="Login_container">

                <form className="Login_form">

                    <div className="Login_form_title">
                        <div style={{ fontSize: "30px", fontWeight: 700 }}>
                            로그인
                        </div>
                    </div>
                    <div className="Login_form_inner">
                        <div className="Login_form_content">
                            <div className="Login_from_text">
                                아이디
                            </div>
                            <div className="Login_form_input">
                                <input className="username" type="text" name="username" required="required" />
                            </div>
                        </div>
                        <div className="Login_form_content">
                            <div className="Login_from_text">
                                패스워드
                            </div>
                            <div className="Login_form_input">
                                <input onKeyDown={LoginButtonClick} className="password" type="password" name="password" required="required" />
                            </div>
                        </div>
                        <button type="button" className="Login_form_button" onClick={login}>로그인</button>
                        <Link to="/signup">
                            <button className="Login_form_button2">회원가입</button>
                        </Link>
                    </div>

                </form>

            </div>
        </div>
    )

}

// 폼으로 직접 데이터 전송
/*
import React from 'react';
import styles from '../../css/Login.css';
import { Link } from 'react-router-dom';
import axios from 'axios';

export default function Login() {

    function login() {

        let info = {
            username : document.querySelector(".username").value,
            password : document.querySelector(".password").value
        }

        console.log(info);

        axios.post("http://localhost:8080/login", info)
            .then(res => {
                console.log("성공!!")
                console.log(info)
                console.log(res)
            }).catch(err => {
                console.log(err, "오류")
            })

    }

    return (
        <div>
            <div className="Login_container">

                <form className="Login_form" action="/login" method="POST">

                    <div className="Login_form_title">
                        <div style={{ fontSize: "30px", fontWeight: 700 }}>
                            로그인
                        </div>
                    </div>
                    <div className="Login_form_inner">
                        <div className="Login_form_content">
                            <div className="Login_from_text">
                                아이디
                            </div>
                            <div className="Login_form_input">
                                <input className="username" type="text" name="username" required="required" />
                            </div>
                        </div>
                        <div className="Login_form_content">
                            <div className="Login_from_text">
                                패스워드
                            </div>
                            <div className="Login_form_input">
                                <input className="password" type="password" name="password" required="required" />
                            </div>
                        </div>
                        <button className="Login_form_button">로그인</button>
                        <Link to="/signup">
                            <button className="Login_form_button2">회원가입</button>
                        </Link>
                    </div>

                </form>

            </div>
        </div>
    )

}
*/
