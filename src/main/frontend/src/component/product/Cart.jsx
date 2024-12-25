import React, { useState, useEffect } from 'react';
import { Link, useLocation } from 'react-router-dom';
import axios from 'axios';
import sample2 from '../../images/sample2.jpg';
import styles from '../../css/Cart.css';
import X from '../../images/X.png'

export default function Cart() {

    const [cartList, setCartList] = useState([])
    const [user, setUser] = useState({});
    const [products, setProducts] = useState([]) // 배열이 아니라 객체면 map함수에서 에러남

    function loadUser() {

        axios.get("/member/loadUser")
        .then(res => {
            console.log(res.data, "로드유저성공!!");
            if(res.data.username == null || res.data.username == "") {
                console.log(res.data.username)
                console.log("유저 정보가 없습니다");
            } else {
                // window.location.href= "/";
                setUser(res.data)
            }
        }).catch(err => {
            console.log(err, "로드유저실패!!");
        })

    }

    // 페이지 로딩 처음에만 초기화시켜서 무한 초기화를 방지
    useEffect(

        () => {

            loadUser();
            let savedCartList = localStorage.getItem("cartList");
            setCartList(savedCartList ? JSON.parse(savedCartList) : {});
            console.log(cartList);

        }

    , [])

    useEffect(

        () => {

            if(cartList.length > 0) {

                axios.post("/product/cartList", { pid: cartList })
                    .then(res => {

                        setProducts(res.data)
                        console.log(res.data)

                    }).catch(err => {

                        console.log(err)

                    })

            } else {

                setProducts([])

            }

        }

    , [cartList])

    function handleImageError(event) {

        if(event.target.src !== "/static/images/No_Image.jpg") {
            event.target.src = "/static/images/No_Image.jpg"
        }

    }

    // 숫자 천단위에 쉼표를 추가하는 함수
    const formatPrice = (value) => {
        if (value === null || value === undefined) return '';
        return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    };

    // 선택 삭제
    function removeToCartList(pid) {

        // pid와 같지 않은 것만 새로운 카트리스트에 저장
        const updatedCartList = cartList.filter(id => id !== pid)
        alert("삭제 완료!!")
        setCartList(updatedCartList)
        localStorage.setItem("cartList", JSON.stringify(updatedCartList))
        window.location.href = "/cart"

    }

    // 전체 삭제
    function allRemoveToCartList() {

        alert("삭제 완료!!")
        localStorage.removeItem("cartList")
        setCartList([])
        window.location.href = "/cart"

    }

    function buy() {

        alert("buy")

    }

    return(

        <div className="cart_container">

            <div className="cart_box">
                <div className="cart_img">
                    이미지
                </div>
                <div className="cart_contents">
                    <div className="cart_type">
                        분류
                    </div>
                    <div className="cart_title">
                        제목
                    </div>
                    <div className="cart_price">
                        가격
                    </div>
                </div>
            </div>
            {
                products.map((product, index) => (
                    <div className="cart_box2">

                        <img className="cart_img2" src={`/upload/${product.productImageUrl}`} onError={handleImageError} />
                        <div className="cart_contents2">
                            <div className="cart_type2">
                                {product.ptype}
                            </div>
                            <div className="cart_title2">
                                <Link to={`/product/detail/${product.pid}`} state = {{ user }}>
                                    {product.ptitle}
                                </Link>
                            </div>
                            <div className="cart_price2">
                                {formatPrice(product.pprice)}원
                            </div>
                            <div className="cart_delete">
                                <img src={X} style={{cursor: "pointer"}} onClick={() => removeToCartList(`${product.pid}`)} />
                            </div>
                        </div>
                    </div>
                ))
            }
            <div className="cart_button">
                <button className="cart_all_delete" onClick={allRemoveToCartList}>전체삭제</button>
                <button className="cart_buy" onClick={buy}>구매하기</button>
            </div>

        </div>

    )
}
