import React, { useState } from 'react';
import styles from '../../css/Upload.css';
import preview from '../../images/preview.png'
import axios from 'axios'

export default function Upload() {

    const [selectedOption, setSelectedOption] = useState('선택 없음')
    const [price, setPrice] = useState('');

    // type 변경할 때마다 값을 변수에 저장
    const selectChangeHandler = (event) => {
         setSelectedOption(event.target.value)
    }

    const handlePriceInput = (event) => {
        const input = event.target.value.replace(/,/g, ''); // 기존 콤마 제거
        const formattedInput = new Intl.NumberFormat().format(input); // 천단위 콤마 추가된 형식으로 변환 (국제 표준 API 활용 )
        setPrice(formattedInput);
    }

    function imageSelect(image) {

        if (!image.files || !image.files[0]) {
            return; // 아무 선택도 하지 않았을 경우 함수 종료
        }

        let selectImage = image.files[0]; // selectImage는 파일의 여러 정보를 포함하는 객체

        if (!selectImage.type.match("image.*")) { // 선택된 파일의 MIME 유형을 "image.*"라는 정규표현식과 비교, 이미지 선택 유무 확인
            alert("오직 이미지를 등록해야 합니다.")
            return;
        }

        let reader = new FileReader();
        reader.onload = (e) => {
            document.querySelector(".preview_image").setAttribute("src", e.target.result)
        }
        reader.readAsDataURL(selectImage); // 선택된 객체 데이터를 URL 형식으로 읽어옴

    }

    function productUpload() {

        let upload_form = document.querySelector(".upload_form")
        let formData = new FormData(upload_form)
        formData.set("ptype", selectedOption)

        // 가격에서 콤마를 제거하고 숫자만 추출하여 설정
        const onlyNumber = price.replace(/,/g, '');
        formData.set("pprice", onlyNumber)

        /*alert("상품 제목" + formData.ptitle)
        alert("상품 분류" + formData.ptype)
        alert("이미지 파일" + formData.file)
        alert("상품 가격" + formData.pprice)
        alert("상품 설명" + formData.pcontent)*/

        axios.post("/product/setUpload", formData, { headers : { "Content-Type" : "multipart/form-data" } })
        .then(res => {

            console.log(res.data)
            if(res.data == true) {
                alert("상품 등록 성공")
                window.location.href = "/"
            } else {
                alert("상품 등록 실패")
            }

        })
        .catch(err => {
            alert(err)
        })

    }

    return (

        <div className="upload_inner">

            <div className="upload_container">
                <p>상품 등록하기</p>

                <form className="upload_form">
                    <div>상품 제목</div>
                    <input type="text" name="ptitle" style={{marginRight : "50px"}}></input>
                    <span>상품 분류</span>
                    <select className="ptype" name="ptype" value={selectedOption} onChange={selectChangeHandler} style={{marginLeft: 5, marginBottom: 10, height: 24}}>
                        <option value="none">선택 없음</option>
                        <option value="Monitor">모니터</option>
                        <option value="CPU">CPU</option>
                        <option value="GraphicsCard">그래픽카드</option>
                        <option value="Desktop">본체</option>
                        <option value="InputDevice">입력장치</option>
                        <option value="Etc">기타</option>
                    </select>
                    <br />
                    <input type="file" name="pfile" onChange = {(event) => imageSelect(event.target)} />
                    <img src={preview} className="preview_image" />
                    <div>상품 가격</div>
                    <input type="text" name="pprice" value={price} onChange={handlePriceInput}></input>원
                    <div>상품 설명</div>
                    <textarea name="pcontent" style={{width: "200px", height: "50px"}}></textarea><br />
                    <button type="button" onClick={productUpload}>등록하기</button>
                </form>

            </div>

        </div>

    )

}
