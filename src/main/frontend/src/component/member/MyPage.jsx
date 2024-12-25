import React, { useState, useEffect } from 'react';
import styles from '../../css/MyPage.css';
import axios from 'axios';

export default function MyPage({ user }) {

    let [button, setButton] = useState(false);
    let input1;
    let input2;
    let [passwordCheck, setPasswordCheck] = useState(true);

    // 배열 길이 확인은 user.length
    // 객체 길이 확인은 Object.keys(user).length
    /*
    if(Object.keys(user).length > 0) {
        alert(user)
        alert(user.username)
    }
    */

    function buttonHandler() {

            if(button == false) {
                setButton(true)
            }

            if(button == true) {

            input1 = document.querySelector(".input_password").value;
            input2 = document.querySelector(".input_password2").value;

            if(input1 == input2) {

                setPasswordCheck(true)
                axios.post("/member/update/", { mid: user.mid, password: input1 })
                    .then(res => {
                        window.location.href = "/logout"
                        console.log(res)
                    }).catch(err => {
                        console.log(err)
                    })

            } else {

                setPasswordCheck(false);

            }

        }
    }

    return (

        <div>
            <div className="info_inner">
                <div className="info_container">
                    <div className="info_container_inner">
                        <h2>내 정보</h2>
                        <div className="info_box">
                            <div className="info_box_inner">
                                이름 <br />
                                <input className="input_name" disabled={true} value={user.name} /> <br />
                            </div >
                            <div className="info_box_inner">
                                아이디 <br />
                                <input className="input_username" disabled={true} value={user.username} /><br />
                            </div>
                            {
                                button ? (
                                    <div>
                                        <div className="info_box_inner">
                                            비밀번호 <br />
                                            <input className="input_password" type="password" style={{backgroundColor: "white"}}/> <br />
                                        </div>
                                        <div className="info_box_inner">
                                            비밀번호 확인<br />
                                            <input className="input_password2" type="password" style={{backgroundColor: "white"}}/> <br />
                                            {passwordCheck ? (
                                                    <div>
                                                    </div>
                                                ) : (
                                                    <div style={{color : "red"}}>
                                                        비밀번호가 일치하지 않습니다.
                                                    </div>
                                                )

                                            }
                                        </div>
                                    </div>
                                ) : (
                                    <div className="info_box_inner">
                                        비밀번호 <br />
                                        <input className="input_password" disabled={true} value="password" type="password"/> <br />
                                    </div>
                                )
                            }
                            <button className="input_button" onClick={buttonHandler} style={{float: "left"}}>비밀번호 변경</button> <br/>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    )

}
