import React, { useState, useEffect } from 'react';
import styles from '../css/Home.css';
import axios from 'axios';
import Myslide from './Myslide';
import { Link } from 'react-router-dom';

export default function Home({ user }) {

    const [ productList, setProductList ] = useState( [ ] )
    const [ prices, setPrices ] = useState( [ ] );

    useEffect( loadProducts, [] );

    function loadProducts() {

        axios.get("/product/loadProducts")
        .then(res => {

            setProductList(res.data)

        }).catch(err => {

            console.log(err)

        })

    }

    useEffect(() => {
        console.log(productList)
    }, [productList])

    function handleImageError(event) {

        if(event.target.src !== "/static/images/No_Image.jpg") {
            event.target.src = "/static/images/No_Image.jpg"
        }

    }

    // 숫자에 쉼표를 추가하는 함수
    const formatPrice = (value) => {

        if (value === null || value === undefined) return '';
        return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');

    };

    function productDetail(pid) {

    }

    /* 스프링 부트 WebConfiguration 설정에서 /upload로 요청이 올 경우 가로채서 외부 업로드 경로에 접근하도록 설정 */

    return (
        <div>
            <div className="Home_container">
                <h3>☆추천 상품☆</h3>
                <Myslide></Myslide>
                <h3>☆최근 상품☆</h3>
                <div className="product_container">

                    {productList.map((product, index) => (
                        <div key={index} className="product_box">
                            <Link to={`/product/detail/${product.pid}`} state={{ user }}>
                                <img src={`/upload/${product.productImageUrl}`} onError={handleImageError} onClick={() => productDetail(product.pid)} />
                            </Link>
                            <div className="product_contents">
                                <div>
                                    {product.ptitle}
                                </div>
                                <div>
                                    {formatPrice(product.pprice)}원
                                </div>
                            </div>

                        </div>
                    ))}

                </div>
            </div>
        </div>
    )

}
