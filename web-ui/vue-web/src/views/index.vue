<template>
  <div class="mt60">
    <!-- 轮播图 -->
    <el-carousel :interval="4000" type="card" height="520px">
      <el-carousel-item v-for="banner in bannerList" :key="banner.bannerId">
        <a target="_blank" :href="banner.linkUrl">
          <el-image :src="fileUploadHost + banner.imageUrl" :alt="banner.title">
          </el-image>
        </a>
      </el-carousel-item>

     <!-- <el-carousel-item>
        <a target="_blank" href="#">
          <img src="../assets/styles/images/1.jpg" alt="" style="width: 100%;height: 520px" >
        </a>
      </el-carousel-item>
      <el-carousel-item>
        <a target="_blank" href="#">
          <img src="../assets/styles/images/2.jpg" alt="" style="width: 100%;height: 520px" >
        </a>
      </el-carousel-item>
      <el-carousel-item>
        <a target="_blank" href="#">
          <img src="../assets/styles/images/3.jpg" alt="" style="width: 100%;height: 520px">
        </a>
      </el-carousel-item>
      <el-carousel-item>
        <a target="_blank" href="#">
          <img src="../assets/styles/images/4.jpg" alt="" style="width: 100%;height: 520px">
        </a>
      </el-carousel-item>
      <el-carousel-item>
        <a target="_blank" href="#">
          <img src="../assets/styles/images/5.jpg" alt="" style="width: 100%;height: 465px" >
        </a>
      </el-carousel-item>-->
    </el-carousel>

    <!-- 页面主体 -->
    <div class="container">
      <div class="main">
        <!-- 正在热映 -->
        <div class="hot-movie">
          <h2>正在热映（4部）</h2>
          <div class="movies" id="hotmovies">
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <button onclick="alert('123')">抢购</button>
            </div>
            <div class="movies-model">
            <a href="">
              <img src="../assets/styles/images/hotmovie2.png" alt="" srcset="">
            </a>
            <button onclick="alert('123')">抢购</button>
          </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie3.png" alt="" srcset="">
              </a>
              <button onclick="alert('123')">抢购</button>
            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie4.png" alt="" srcset="">
              </a>
              <button onclick="alert('123')">抢购</button>
            </div>
          </div>
        </div>
        <!-- 即将上映 -->
        <div class="movie-box">
          <div class="movie-header">
            <h2>即将上映（115部）</h2>
            <a href="#">
              <span>全部></span>
            </a>
          </div>
          <div class="movies">
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>46461661人想看</p>
              <button onclick="alert('123')">预告片</button>
              <button onclick="alert('123')">预售</button>
              <p>9月31日上映</p>
            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie2.png" alt="" srcset="">
              </a>
              <p>46461661人想看</p>
              <button onclick="alert('123')">预告片</button>
              <button onclick="alert('123')">预售</button>
              <p>10月2日上映</p>
            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie3.png" alt="" srcset="">
              </a>
              <p>46461661人想看</p>
              <button onclick="alert('123')">预告片</button>
              <button onclick="alert('123')">预售</button>
              <p>11月1日上映</p>
            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie4.png" alt="" srcset="">
              </a>
              <p>46461661人想看</p>
              <button onclick="alert('123')">预告片</button>
              <button onclick="alert('123')">预售</button>
              <p>9月23日上映</p>
            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>46461661人想看</p>
              <button onclick="alert('123')">预告片</button>
              <button onclick="alert('123')">预售</button>
              <p>9月31日上映</p>
            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>46461661人想看</p>
              <button onclick="alert('123')">预告片</button>
              <button onclick="alert('123')">预售</button>
              <p>9月31日上映</p>
            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>46461661人想看</p>
              <button onclick="alert('123')">预告片</button>
              <button onclick="alert('123')">预售</button>
              <p>9月31日上映</p>
            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>46461661人想看</p>
              <button onclick="alert('123')">预告片</button>
              <button onclick="alert('123')">预售</button>
              <p>9月31日上映</p>
            </div>
          </div>
        </div>
        <!-- 热播电影 -->
        <div class="movie-box">
          <div class="movie-header c hotmovie ">
            <h2>热播电影（115部）</h2>
            <a href="#">
              <span>全部></span>
            </a>
          </div>
          <div class="movies movies-hot">
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>
            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie2.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie3.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie4.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
          </div>
        </div>
        <!-- 喜剧电影 -->
        <div class="movie-box">
          <div class="movie-header">
            <h2>喜剧电影（115部）</h2>
            <a href="#">
              <span>全部></span>
            </a>
          </div>
          <div class="movies">
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie2.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie3.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie4.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
          </div>
        </div>
        <!-- 动作电影 -->
        <div class="movie-box">
          <div class="movie-header c hotmovie ">
            <h2>动作电影（125部）</h2>
            <a href="#">
              <span>全部></span>
            </a>
          </div>
          <div class="movies movies-hot">
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>
            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie2.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie3.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie4.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
          </div>
        </div>
        <!-- 恐怖片 -->
        <div class="movie-box">
          <div class="movie-header">
            <h2>恐怖电影（115部）</h2>
            <a href="#">
              <span>全部></span>
            </a>
          </div>
          <div class="movies">
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie2.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie3.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie4.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
          </div>
        </div>
      </div>
      <!-- 侧边栏 -->
      <div class="aside">
        <!-- 今日票房 -->
        <div class="rank">
          <h2>今日票房</h2>
          <div class="ranklist">
            <ul class="ranks">
              <li class=" rangk-item-first clearfix">
                <a href="#">
                  <div class="first-item clearfix">
                    <img src="../assets/styles/images/rank.png" alt="" srcset="">
                  </div>
                  <p class="name">爱宠大机密2</p>
                  <p class="money">0.72万</p>
                </a>
              </li>
              <li class="rank-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index index-hot">2</i>
                    <span>冰雪奇缘2</span>
                  </div>
                  <span class="index-money">0.00万</span>

                </a>
              </li>
              <li class="rank-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index index-hot">3</i>
                    <span>冰雪奇缘2</span>
                  </div>
                  <span class="index-money">0.00万</span>
                </a>
              </li>
              <li class="rank-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index">4</i>
                    <span>冰雪奇缘2</span>
                  </div>
                  <span class="index-money">0.00万</span>

                </a>
              </li>
              <li class="rank-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index">5</i>
                    <span>冰雪奇缘2</span>
                  </div>
                  <span class="index-money">0.00万</span>
                </a>
              </li>
            </ul>
          </div>
        </div>
        <!-- 今日大盘 -->
        <div class="aside-total">
          <h2 class="total-nav">今日大盘</h2>
          <div class="total-body">
            <h2>0.6 <span>万</span></h2>
            <span class="more">查看更多></span>
            <p class="time"><span>北京时间14:48:31</span> <span>猫眼专业版实时票房数据</span></p>
          </div>
        </div>
        <!-- 最受期待 -->
        <div class="hope">
          <h2>最受期待 <span>查看完整榜单></span></h2>
          <div class="hope-body">
            <ul class="hope-list">
              <li class="hope-item-top1">
                <a href="#">
                  <div class="hope-img">
                    <img src="../assets/styles/images/hope1.png" alt="">
                  </div>
                  <div class="hope-text">
                    <p class="hope-name">姜子牙</p>
                    <p class="hope-time">上映时间：2020</p>
                    <p class="hope-num">932707人想看</p>
                  </div>

                </a>
              </li>
              <li class="hope-item-top">
                <a href="#">
                  <div class="hope-img">
                    <img src="../assets/styles/images/hope2.png" alt="">
                  </div>
                  <p class="hope-name">哪吒重生</p>
                  <p class="hope-num">932707人想看</p>
                </a>
              </li>
              <li class="hope-item-top left">
                <a href="#">
                  <div class="hope-img">
                    <img src="../assets/styles/images/hope3.png" alt="">
                  </div>
                  <p class="hope-name">唐人街探案3</p>
                  <p class="hope-num">932707人想看</p>
                </a>
              </li>
              <li class="hope-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index index-hot">4</i>
                    <span>冰雪奇缘2</span>
                  </div>
                  <span class="hope-num">932707人想看</span>
                </a>
              </li>
              <li class="hope-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index index-hot">5</i>
                    <span>冰雪奇缘2</span>
                  </div>
                  <span class="hope-num">932707人想看</span>
                </a>
              </li>
              <li class="hope-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index index-hot">6</i>
                    <span>冰雪奇缘2</span>
                  </div>
                  <span class="hope-num">932707人想看</span>
                </a>
              </li>
              <li class="hope-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index index-hot">7</i>
                    <span>冰雪奇缘2</span>
                  </div>
                  <span class="hope-num">932707人想看</span>
                </a>
              </li>
              <li class="hope-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index index-hot">8</i>
                    <span>冰雪奇缘2</span>
                  </div>
                  <span class="hope-num">932707人想看</span>
                </a>
              </li>
              <li class="hope-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index index-hot">9</i>
                    <span>冰雪奇缘2</span>
                  </div>
                  <span class="hope-num">932707人想看</span>
                </a>
              </li>
              <li class="hope-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index index-hot">10</i>
                    <span>冰雪奇缘2</span>
                  </div>
                  <span class="hope-num">932707人想看</span>
                </a>
              </li>
            </ul>
          </div>
        </div>
        <!-- top100 -->
        <div class="top">
          <h2>TOP 100 <span>查看完整榜单></span></h2>
          <div class="toplist">
            <ul class="topone">
              <li class=" top-item-first clearfix">
                <a href="#">
                  <div class="first-item clearfix">
                    <img src="../assets/styles/images/top1.png" alt="" srcset="">
                  </div>
                  <p class="name">活着</p>
                  <p class="money">9.0分</p>
                </a>
              </li>
              <li class="top-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index index-hot">2</i>
                    <span>钢琴家</span>
                  </div>
                  <span class="index-money">8.8分</span>

                </a>
              </li>
              <li class="top-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index index-hot">3</i>
                    <span>勇敢的心</span>
                  </div>
                  <span class="index-money">8.8分</span>
                </a>
              </li>
              <li class="top-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index">4</i>
                    <span>阿飞正传</span>
                  </div>
                  <span class="index-money">8.8分</span>

                </a>
              </li>
              <li class="top-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index">5</i>
                    <span>射雕英雄传之东成西就</span>
                  </div>
                  <span class="index-money">8.8分</span>
                </a>
              </li>
              <li class="top-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index">6</i>
                    <span>爱回家</span>
                  </div>
                  <span class="index-money">8.8分</span>
                </a>
              </li>
              <li class="top-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index">7</i>
                    <span>初恋这件小事</span>
                  </div>
                  <span class="index-money">8.8分</span>
                </a>
              </li>
              <li class="top-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index">8</i>
                    <span>泰坦尼克号</span>
                  </div>
                  <span class="index-money">8.8分</span>
                </a>
              </li>
              <li class="top-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index">9</i>
                    <span>迁徙的鸟</span>
                  </div>
                  <span class="index-money">8.8分</span>
                </a>
              </li>
              <li class="top-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index">10</i>
                    <span>蝙蝠侠：黑暗骑士</span>
                  </div>
                  <span class="index-money">8.8分</span>
                </a>
              </li>
            </ul>
          </div>
        </div>
        <!-- 热门影人 -->
        <div class="people">
          <h2>热门影人</h2>
          <div class="peoplelist">
            <ul class="peoples">
              <li class=" people-item-first clearfix">
                <a href="#">
                  <div class="first-item clearfix">
                    <img src="../assets/styles/images/people.png" alt="" srcset="">
                  </div>
                  <p class="name">大张伟</p>
                </a>
              </li>
              <li class="people-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index index-hot">2</i>
                    <span>蔡国庆</span>
                  </div>
                </a>
              </li>
              <li class="people-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index index-hot">3</i>
                    <span>黄一山</span>
                  </div>

                </a>
              </li>
              <li class="people-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index">4</i>
                    <span>蔡轩正</span>
                  </div>
                </a>
              </li>
              <li class="people-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index">5</i>
                    <span>平安</span>
                  </div>
                </a>
              </li>
              <li class="people-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index">6</i>
                    <span>王源</span>
                  </div>
                </a>
              </li>
              <li class="people-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index">7</i>
                    <span>小黑</span>
                  </div>
                </a>
              </li>
              <li class="people-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index">8</i>
                    <span>郁可唯</span>
                  </div>
                </a>
              </li>
              <li class="people-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index">9</i>
                    <span>许魏洲</span>
                  </div>
                </a>
              </li>
              <li class="people-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index">10</i>
                    <span>王力宏</span>
                  </div>
                </a>
              </li>
            </ul>
          </div>
        </div>
        <!-- 娱乐热点 -->
        <div class="hotspot">
          <h2>娱乐热点</h2>
          <div class="hotspotlist">
            <ul class="hotspots">
              <li class=" hotspot-item-first clearfix">
                <a href="#">
                  <div class="first-item clearfix">
                    <img src="../assets/styles/images/hotspot.png" alt="" srcset="">
                  </div>
                  <p class="name">《什刹海》今晚央一开播...</p>
                </a>
              </li>
              <li class="hotspot-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index index-hot">2</i>
                    <span>反差萌!动画电影《杨戬》“哮天犬” 形象首次曝光</span>
                  </div>
                </a>
              </li>
              <li class="hotspot-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index index-hot">3</i>
                    <span>《民初奇人传》火热收官奇趣甜三大法宝发力赢口碑</span>
                  </div>

                </a>
              </li>
              <li class="hotspot-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index">4</i>
                    <span>电影《再见吧!少年》曝阵容刘敏涛荣梓杉大银幕..</span>
                  </div>
                </a>
              </li>
              <li class="hotspot-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index">5</i>
                    <span>布拉德:皮特参演《死侍2》导演全新动作片后者曾...</span>
                  </div>
                </a>
              </li>
              <li class="hotspot-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index">6</i>
                    <span>《漂亮书生》首曝剧照鞠婧祎宋威龙携“漂亮战队...</span>
                  </div>
                </a>
              </li>
              <li class="hotspot-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index">7</i>
                    <span>《温暖的抱抱》首曝预告常远李沁邂逅妙手“神经...</span>
                  </div>
                </a>
              </li>
              <li class="hotspot-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index">8</i>
                    <span>汤姆汉克斯一让每一 份人生， 都成为充满期待的..</span>
                  </div>
                </a>
              </li>
              <li class="hotspot-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index">9</i>
                    <span>电影《至暗陷阱》至暗双雄霸气亮相明暗对决一触..</span>
                  </div>
                </a>
              </li>
              <li class="hotspot-item">
                <a href="#">
                  <div class="index-box">
                    <i class="index">10</i>
                    <span>《什刹海》央一定档7.10， “多彩京韵” 版海报今...</span>
                  </div>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import index from '@/api/index'
  import { getDicts } from '@/api/system/dict/data'
  import { listTag } from "@/api/media/tag";
  import { listCategory } from "@/api/media/category";
  export default {
    data() {
      return {
        tagOptions: [],
        movieList: [],
        bannerList: [],
        actorList: []
      }
    },
    async created() {
      const tagOptions = await listTag({status: '1'});
      const movieList = await index.getHotMovieList();
      const actorList = await index.getHotActorList();
      const bannerList = await index.getAllBannerList();
      this.tagOptions =  tagOptions.rows
      this.movieList =  movieList.data
      this.bannerList = bannerList.data
      this.actorList = actorList.data
    },
    methods: {
      actorFormatter(actorList) {
        if (actorList.length === 0){
          return '无';
        }
        const currentSeparator = ",";
        let actions = [];
        for (let index = 0; index < actorList.length; index++) {
          actions.push(actorList[index].name + currentSeparator);
        }
        return actions.join('').substring(0, actions.join('').length - 1);
      },
      /** 标签翻译 */
      tagFormat(tagOptions,tagId) {
        if(!tagId || !tagOptions) {
          return ''
        }
        const currentSeparator = ",";
        let actions = [];
        let tempArr = tagId.split(currentSeparator);
        for (let i = 0; i < tempArr.length; i++) {
          for (let j = 0; j < tagOptions.length ; j++) {
            if (tagOptions[j].tagId == ('' + tempArr[i])) {
              actions.push(tagOptions[j].content + currentSeparator);
              break;
            }
          }
        }
        return actions.join('').substring(0, actions.join('').length - 1);
      }
    }
  }
</script>
