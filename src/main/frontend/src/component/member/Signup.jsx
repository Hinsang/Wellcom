import React from 'react';
import styles from '../../css/Signup.css';
import { Link } from 'react-router-dom';
import axios from 'axios';

export default function Signup() {

    function signup() {

        let checkbox = document.querySelector("#checkbox_container");
        let isChecked = checkbox.checked; // 체크 값 가져오기

        let info = {
            name : document.querySelector("#name").value,
            username : document.querySelector("#username").value,
            password : document.querySelector("#password").value,
            role : "NORMAL_USER"
        };

        // alert(JSON.stringify(info));

        if(isChecked == true) {
            axios.post("/member/signup", info)
            .then(res => {
                let result = res.data
                if(result != false) {
                    alert("회원가입 성공!!")
                    window.location.href = "/"
                } else {
                    alert("회원가입 실패!!")
                }
            }).catch(err => {
                console.log(err)
            })
        } else {
            alert("약관에 동의해주세요!!")
        }

    }

    return (
        <div>
            <div className="Signup_container">

                <div className="Signup_form">
                    <div className="Signup_form_title">
                        <div style={{ fontSize: "30px", fontWeight: 700 }}>
                            회원가입
                        </div>
                    </div>
                    <div className="Signup_form_inner">
                        <div className="Signup_form_content">
                            <div className="Signup_from_text">
                                이름  <span className="check_name"></span>
                            </div>
                            <div className="Signup_form_input">
                                <input type="text" id="name"></input>
                            </div>
                        </div>
                        <div className="Signup_form_content">
                            <div className="Signup_from_text">
                                아이디 <span className="check_username"></span>
                            </div>
                            <div className="Signup_form_input">
                                <input type="text" id="username"></input>
                            </div>
                        </div>
                        <div className="Signup_form_content">
                            <div className="Signup_from_text">
                                패스워드    <span className="check_password"></span>
                            </div>
                            <div className="Signup_form_input">
                                <input type="password" id="password"></input>
                            </div>
                        </div>
                        <div className="Signup_form_content">
                            <div className="Signup_from_text">
                                패스워드 확인 <span className="check_password2"></span>
                            </div>
                            <div className="Signup_form_input">
                                <input type="password"></input>
                            </div>
                        </div>
                        <textarea className="Signup_explanation" placeholder="이용약관 입니다." readOnly={true} defaultValue="개인정보 보호방침" />
                        <div>
                            <label htmlFor="checkbox_container">
                            <input type="checkbox" id="checkbox_container" />
                                약관에 동의합니다.  <span className="check_box"></span>
                            </label>
                        </div>
                        <button className="Signup_form_button2" onClick={signup}>회원가입 완료</button>
                    </div>

                </div>

            </div>
        </div>
    )

}
