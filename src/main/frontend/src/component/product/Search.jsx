import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link, useLocation } from 'react-router-dom';

export default function Search() {

    const location = useLocation();
    const text = location.state?.text || "";
    const [ productList, setProductList ] = useState( [ ] )
    //const [ text, setText ] = useState(""); // Header 컴포넌트에서 useNavigate로 text 값을 넘겨받음

    useEffect( () => {

        if(text) {

            console.log(text + "@@@@@@@@");
            reSearch()

        }

    }, [text] );

    function reSearch() {

        console.log(text + "########");
        axios.get("/products/api/search", { params : { text : text } })

            .then(res => {

                setProductList(res.data)

            }).catch(err => {

                console.log(err)

            })

    }

    function handleImageError(event) {

        if(event.target.src !== "/static/images/No_Image.jpg") {
            event.target.src = "/static/images/No_Image.jpg"
        }

    }

    const formatPrice = (value) => {

        if (value === null || value === undefined) return '';
        return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');

    };

    return (

        <div className="Home_container">
            <h3 style={{ textAlign: "center", paddingTop: "50px", paddingBottom: "50px"}}>{text !== "" ? `"${text}"로 검색한 결과` : "검색어를 입력해주세요"}</h3>
            <div className="product_container">

                {productList.map((product, index) => (
                    <div key={index} className="product_box">
                        <Link to={`/product/detail/${product.pid}`}>
                            <img src={`/upload/${product.productImageUrl}`} onError={handleImageError} />
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

    )

}
