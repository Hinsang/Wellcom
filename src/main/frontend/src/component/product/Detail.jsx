import React, { useState, useEffect } from 'react';
import { useParams, useLocation, useNavigate } from "react-router-dom";
import axios from 'axios';
import styles from '../../css/Detail.css';
import sample2 from "../../images/sample2.jpg";
import goCart from "../../images/goCart.png";
import buy from "../../images/buy.png";
import star1 from '../../images/star1.png';
import star2 from '../../images/star2.png';

export default function Detail() {

    const params = useParams();
    const navigate = useNavigate();
    const [product, setProduct] = useState({})
    const [reviewList, setReviewList] = useState([])
    const [maxIndex, setMaxIndex] = useState(0);
    const stars = [1, 2, 3, 4, 5];
    const location = useLocation(); // Link to로 전달받은 user 정보 state를 활용
    const { user } = location.state || {}; // 유저 정보를 location.state에서 가져오기

    useEffect(

        () => {
            axios.get("/product/detail/", { params : { pid : params.pid } } )
            .then(res => {
                setProduct(res.data)
            }).catch(err => {
                console.log(err)
            })
        }

    , [])

    useEffect(

        () => {

            loadReview()

        }

    , [params.pid])

    function handleImageError(event) {

        if(event.target.src !== "/static/images/No_Image.jpg") {
            event.target.src = "/static/images/No_Image.jpg"
        }

    }

    const formatPrice = (value) => {

        if (value === null || value === undefined) return '';
        return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');

    };

    /*
    <div>
        <div className="product_container">
            상품 번호 : {product.pid} <br />
            상품 이름 : {product.ptitle} <br />
            상품 이미지 <br />
            <img src={`/upload/${product.productImageUrl}`} onError={handleImageError} /> <br />
            상품 타입 : {product.ptype} <br />
            상품 가격 : {product.pprice} <br />
            상품 내용 : {product.pcontent} <br />
        </div>
    </div>
    */

    function addCart(productId) {
        const cartItem = productId
        // 기존 카트리스트 로컬스토리지에 객체를 넣을 수 없으므로 로컬스토리지를 JSON으로 객체화 시킨다. 기존 값이 없을 경우 빈 배열 저장
        const cartList = JSON.parse(localStorage.getItem("cartList")) || []
        // 푸시로 값 추가
        cartList.push(cartItem)
        // 카트리스트 객체에 JSON화 해서 저장
        localStorage.setItem("cartList", JSON.stringify(cartList))
        // console.log(localStorage.getItem("cartList"))

        navigate("/cart", { state : { user } })
    }

    function buyProduct() {

        alert("buy")

    }

    function changeStar(i) {

        setMaxIndex(i)

    }

    function postReview() {

        const userInfo = JSON.parse(localStorage.getItem("userInfo"))

        let info = {

            comment : document.querySelector(".review_comment").value,
            stars : maxIndex,
            mid : userInfo.mid,
            pid : product.pid

        }

        axios.post("/product/postReview", info)
        .then(res => {
            console.log(info)
            console.log(res)
            window.location.reload();
        }).catch(err => {
            console.log(err)
        })

    }

    function loadReview() {

        axios.get("/product/loadReview", { params : { pid : params.pid } })
        .then(res => {
            setReviewList(res.data)
            console.log(JSON.stringify(res.data) + " @@@@@@@@@")
        }).catch(err => {
            console.log(err)
        })

    }

    return(

        <div style={{ height : 2000 }}>
            <div className="product_inner">

                <div className="product_container">
                    <div className="product_content1">
                        <img src={`/upload/${product.productImageUrl}`} /> <br />
                    </div>
                    <div className="product_content2">
                        <div className="product_type">
                            {product.ptype}
                        </div>
                        <div className="product_title_inner">
                            <span className="product_title">
                                {product.ptitle}
                            </span>
                        </div>
                        <div className="product_price">
                            {formatPrice(product.pprice)}원
                        </div>
                        <div className="product_description">
                            {product.pcontent}
                        </div>
                        <div className="button_container">
                            <div className="button_box1" onClick={() => addCart(`${product.pid}`)}>
                                <img src={goCart} className="goCart" />
                                    <div className="button_text1">
                                        장바구니
                                    </div>
                            </div>
                            <div className="button_box2" onClick={buyProduct}>
                                <img src={buy} className="buy" />
                                <div className="button_text2">
                                    구매하기
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            {
                user.name && user.name !== "admin" ?  (
                    <div className="review_inner" style={{ marginTop: 135 }}>
                        <div className="stars_inner">
                            {
                                stars.map((i) => (
                                    <img src={i <= maxIndex ? star2 : star1} style={{ cursor : "pointer" }} onClick={() => { changeStar(i) }} />
                                ))
                            }
                        </div>
                        <div className="review_comment_container">
                            <textarea type="text" className="review_comment" placeholder="리뷰를 작성해 주세요." name="comment" />
                            <button className="review_button" style={{fontWeight: "bold"}} onClick={postReview}>
                                리뷰 등록
                            </button>
                        </div>
                    </div>
                ) : <div />
            }
            <div>
                {
                    reviewList.map((review, index) => {

                        let formattedDate = review.date.split("T")[0];

                        return (
                            <div className="reviewList_comment_container">
                                <div>
                                    <div style={{ display : "flex" }}>
                                        <div className="stars_inner2">
                                            {
                                                stars.map((i) => (
                                                    <img src={i <= review.stars ? star2 : star1} />
                                                ))
                                            }
                                        </div>
                                        <div className="reviewList_date">
                                            {formattedDate}
                                        </div>
                                    </div>
                                    <div className="reviewList_name">
                                        {review.name}
                                    </div>
                                    <div className="reviewList_comment">
                                        {review.comment}
                                    </div>
                                </div>
                            </div>
                        )

                    })
                }
            </div>
        </div>

    )

}
