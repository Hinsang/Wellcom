// https://swiperjs.com/에서 튜토리얼을 따른다
import React from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Pagination, Navigation, Autoplay } from 'swiper/modules';

// Import Swiper styles
import 'swiper/css';
import 'swiper/css/pagination';
import 'swiper/css/navigation';
import 'swiper/css/autoplay';

import sample1 from '../images/sample1.jpg';
import sample2 from '../images/sample2.jpg';
import sample3 from '../images/sample3.jpg';
import sample4 from '../images/sample4.jpg';
import sample5 from '../images/sample5.jpg';
import sample6 from '../images/sample6.jpg';
import sample7 from '../images/sample7.jpg';
import sample8 from '../images/sample8.jpg';
import sample9 from '../images/sample9.jpg';

export default () => {
  return (
    <Swiper
      modules={[Pagination, Navigation, Autoplay]}
      pagination={{ clickable: true }}
      spaceBetween={50}
      slidesPerView={3}
      navigation
      speed={1000}
      onSwiper={(swiper) => console.log(swiper)}
      autoplay={{
        delay: 2500,
        disableOnInteraction: false
      }}
    >
      <SwiperSlide><img src={sample2} /></SwiperSlide>
      <SwiperSlide><img src={sample3} /></SwiperSlide>
      <SwiperSlide><img src={sample4} /></SwiperSlide>
      <SwiperSlide><img src={sample5} /></SwiperSlide>
      <SwiperSlide><img src={sample6} /></SwiperSlide>
      <SwiperSlide><img src={sample7} /></SwiperSlide>
      <SwiperSlide><img src={sample8} /></SwiperSlide>
      <SwiperSlide><img src={sample9} /></SwiperSlide>
      <SwiperSlide><img src={sample1} /></SwiperSlide>
    </Swiper>
  );
};
