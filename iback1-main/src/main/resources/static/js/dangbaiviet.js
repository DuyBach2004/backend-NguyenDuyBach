var courseApi = 'http://localhost:8080/user2/show';
var allPosts = [];

function start() {
    getCourses(renderCourses);
//    getCourses(truyenid);
    getComment(renderComment);
    getComment(idcomment);
    getPhanHoi(rederPhanHoi);
    getPhanHoi(getIdPhanHoi);
    getUser(renderUser);
    getUser(addPost);
    getUser(upbinhluan);
    getUser(assignInput2Event);

}
start();

function getUser(callback) {
    var userApi= 'http://localhost:8080/homeUser/user'
    fetch(userApi)
        .then(function (response) {
            return response.json();
        })
        // .then(callback);
        .then(function(user) {
            callback(user);
        });

}

async function getUser2() {
    var userApi= 'http://localhost:8080/homeUser/user';
    let response = await fetch(userApi);
    let user = await response.json();
    return user;
}
async function printUser() {
    let user = await getUser2();
    return user;
    console.log(user);
}
async function getUserPost() {
    var userPostApi= 'http://localhost:8080/user2/showUserPost';
    let response = await fetch(userPostApi);
    let userPost = await response.json();
    return userPost;
}
async function printUserPost() {
    let userPost = await getUserPost();
    return userPost;
    console.log(userPost);
}

async function getUserComment() {
    var userBinhLuanApi= 'http://localhost:8080/user2/showUserComment';
    let response = await fetch(userBinhLuanApi);
    let userBinhluan = await response.json();
    return userBinhluan;
}


function renderUser(user){
    return user;
}

function truyenid(posts,position){
        return posts[position].id;

}
function idcomment(comments,position){
    return comments[position].id;
}
function getIdPhanHoi(reps,position ){
    return reps[position].idPhanhoi;
}

function getCourses(callback) {
    fetch(courseApi)
        .then(function (response) {
            return response.json();
        })
        // .then(callback);
        .then(function(posts) {
            allPosts = posts; // Cập nhật giá trị của biến toàn cục allPosts
            callback(posts);
        });
}
// Hàm để sắp xếp một mảng một cách ngẫu nhiên
function shuffleArray(array) {
    for (let i = array.length - 1; i > 0; i--) {
        let j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
    }
}
var allComments = [];
var allComments1 = [];
function upbinhluan(user){
    var username=user.username;
    var avatarnguoidung=user?.avatar ? /avatar/+user.avatar : './image/default-avatar.png';

    var commentTime1 = new Date();
    commentTime1.setMinutes(commentTime1.getMinutes() - commentTime1.getTimezoneOffset());
    var mysqlFormat = commentTime1.toISOString().slice(0, 19).replace('T', ' ');

    for (let i = 0; i < vietbinhluans.length; i++) {
        submitbinhluans[i].onclick = function (e) {
            var soluongbinhluan1 = parseInt(soluongbinhluans[i].innerHTML);
            var dulieu = vietbinhluans[i].value;
            if (!check4[i]) {
                if (!isNaN(soluongbinhluan1)) {
                    soluongbinhluans[i].innerHTML = soluongbinhluan1 + 1;
                    var formData = {
                        id: truyenid(allIdPosts,i),
                        username: username,
                        time: mysqlFormat,
                        data: dulieu
                     }

                        postCreateCmt(formData,function (){
                        getCourses(truyenid);
                   });
                } else {
                    soluongbinhluans[i].innerHTML = 1;
                }
                check[i] = true;

            } else {
                soluongbinhluans[i].innerHTML = soluongbinhluan1 - 1;
                check[i] = false;

            }


             // Lưu thời điểm bình luận
             var commentTime = new Date();

                submitbinhluans[i].classList.remove('xanh');
                submitbinhluans[i].style.pointerEvents = 'none';
                vietbinhluans[i].value='';

                // Tạo một phần tử mới
                var newElement = document.createElement('div');
                newElement.classList.add('nav-left-container-header-nav-container__comment-user');

                // Thêm nội dung cho phần tử mới
                newElement.innerHTML = `

                    <div class="nav-left-container-header-nav-container__header">
                        <div class="nav-left-container-header-nav-container__header-div--image">
                            <img src=${avatarnguoidung} alt="" class="nav-left-container-header-nav-container__header-image">
                        </div>
                        <div>
                            <div class="nav-left-container-header-nav-container__comment-user-right">
                                <h1 class="nav-left-container-header-nav-container__header-name-comment">${username}</h1>
                                <span class="nav-left-container-header-nav-container__header-name-span">${dulieu}</span>
                                <div class="nav-left-container-header-nav-container__header-name-icon">
                                    <div class="nav-left-container-header-nav-container__header-name-icon--left">
                                        <img src="/image/like.svg" alt="" class="nav-left-container-header-nav-container__header-name-icon--like">
                                    </div>
                                    <div class="nav-left-container-header-nav-container__header-name-icon--right">
                                        <span class="nav-left-container-header-nav-container__header-name-icon--right-span"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="nav-left-container-header-nav-container__comment-user-right--bottom">
                                <span class="nav-left-container-header-nav-container__header-timeup"></span>
                                <form action="#">
                                    <button class="nav-left-container-header-nav-container__comment-user-right--bottom-bnb-like nav_binhluan">Thích</button>
                                </form>
                                <form action="#">
                                <button
                                    class="nav-left-container-header-nav-container__comment-user-right--bottom-bnb-cmt">
                                    Phản hồi
                                </button>
                            </form>
                            </div>
                        </div>
                   </div>

                   <div class="submit-binhluan2" style="display: none;">

                   <div
                       class="nav-left-container-header-nav-container__header-div--image2">
                       <img src=${avatarnguoidung}  alt=""
                           class="nav-left-container-header-nav-container__header-image2">
                   </div>
                   <form action="">
                       <div class="submit-binhluan-element__right">

                           <input type="text" placeholder="Viết bình luận..."
                               class="submit-binhluan-element__right-input2">
                           <div class="submit-binhluan-element__right-submit">
                               <button type="submit"
                                   class="submit-binhluan-element__right-submit--button2"
                                   style="pointer-events: none;">
                                   <i class="fa-solid fa-up-right-from-square"></i>
                               </button>
                           </div>

                       </div>
                   </form>

               </div>
                </div>`;
                // Thêm phần tử mới vào divbinhluans
                divcontainercmtuser[i].appendChild(newElement);

                // divbinhluans.push(newElement);
                assignLikeEvent();
                assignCommentEvent();

                var timeupElement = newElement.querySelector('.nav-left-container-header-nav-container__header-timeup');
                setInterval(function() {
                    var now = new Date();
                    var secondsElapsed = Math.floor((now - commentTime) / 1000);
                    var minutesElapsed = Math.floor(secondsElapsed / 60);
                    var hoursElapsed = Math.floor(minutesElapsed / 60);
                    var daysElapsed = Math.floor(hoursElapsed / 24);
                    var weeksElapsed = Math.floor(daysElapsed / 7);
                    var monthsElapsed = Math.floor(daysElapsed / 30.44); // Sử dụng trung bình 30.44 ngày trong một tháng
                    var yearsElapsed = Math.floor(daysElapsed / 365.25); // Sử dụng trung bình 365.25 ngày trong một năm (để tính toán năm nhuận)

                    if (yearsElapsed > 0) {
                        timeupElement.textContent = yearsElapsed + ' năm trước';
                    } else if (monthsElapsed > 0) {
                        timeupElement.textContent = monthsElapsed + ' tháng trước';
                    } else if (weeksElapsed > 0) {
                        timeupElement.textContent = weeksElapsed + ' tuần trước';
                    } else if (daysElapsed > 0) {
                        timeupElement.textContent = daysElapsed + ' ngày trước';
                    } else if (hoursElapsed > 0) {
                        timeupElement.textContent = hoursElapsed + ' giờ trước';
                    } else if (minutesElapsed > 0) {
                        timeupElement.textContent = minutesElapsed + ' phút trước';
                    } else {
                        timeupElement.textContent = secondsElapsed + ' giây trước';
                    }
                }, 1000);


        }
    }
}
function assignInput2Event(user){
    var username=user.username;
    var avatarnguoidung5=user?.avatar ? /avatar/+user.avatar : './image/default-avatar.png';
    console.log(user);
    var commentTime1 = new Date();
    commentTime1.setMinutes(commentTime1.getMinutes() - commentTime1.getTimezoneOffset());
    var mysqlFormat = commentTime1.toISOString().slice(0, 19).replace('T', ' ');

    var binhluanusers=document.getElementsByClassName('nav-left-container-header-nav-container__header');
    for(let i=0;i<binhluanusers.length;i++){

        submitbinhluan2s[i].onclick = function(e){


            var dulieu=vietbinhluan2s[i].value;
            var biengan;
            for(let j=0;j<divcontainercmtuser.length;j++){
                biengan=j;

            }

            var dulieu=vietbinhluan2s[i].value;
            var soluongbinhluan1 = parseInt(soluongbinhluans[biengan].innerHTML);

            if (!check5[i]) {

                if (!isNaN(soluongbinhluan1)) {
                    soluongbinhluans[biengan-1].innerHTML = soluongbinhluan1 + 1;
                    var formData = {
                        id: idcomment(allComments1,i),
                        username: username,
                        time: mysqlFormat,
                        data: dulieu
                     }

                     createPhanHoi(formData,function (){
                        getPhanHoi(rederPhanHoi);
                   });

                } else {
                    soluongbinhluans[biengan].innerHTML = 1;
                }
                check[i] = true;

            } else {
                soluongbinhluans[biengan].innerHTML = soluongbinhluan1 - 1;
                check[i] = false;

            }

            var commentTime = new Date();

            submitbinhluan2s[i].classList.remove('xanh');
            submitbinhluan2s[i].style.pointerEvents = 'none';
            vietbinhluan2s[i].value='';

            var newElement = document.createElement('div');
                newElement.classList.add('nav-left-container-header-nav-container__comment-user2');
                newElement.innerHTML=`<div class="nav-left-container-header-nav-container__header">
                <div
                class="nav-left-container-header-nav-container__header-div--image2">
                <img src=${avatarnguoidung5} alt=""
                    class="nav-left-container-header-nav-container__header-image2">
            </div>
                <div>
                    <div
                        class="nav-left-container-header-nav-container__comment-user-right">
                        <h1
                            class="nav-left-container-header-nav-container__header-name-comment">
                            ${username}
                        </h1>
                        <span
                            class="nav-left-container-header-nav-container__header-name-span">${dulieu}</span>
                        <div
                            class="nav-left-container-header-nav-container__header-name-icon">
                            <div
                                class="nav-left-container-header-nav-container__header-name-icon--left">
                                <img src="/image/like.svg" alt=""
                                    class="nav-left-container-header-nav-container__header-name-icon--like">
                            </div>
                            <div
                                class="nav-left-container-header-nav-container__header-name-icon--right">
                                <span
                                    class="nav-left-container-header-nav-container__header-name-icon--right-span"></span>
                            </div>
                        </div>
                    </div>
                    <div
                        class="nav-left-container-header-nav-container__comment-user-right--bottom">
                        <span
                            class="nav-left-container-header-nav-container__header-timeup">
                            0 giây trước
                           </span>

                    </div>
                </div>
            </div>`;
            // divbinhluans.push(newElement);
            divbinhluans[i].appendChild(newElement);

            assignLikeEvent();

            var timeupElement = newElement.querySelector('.nav-left-container-header-nav-container__header-timeup');
            setInterval(function() {
                var now = new Date();
                var secondsElapsed = Math.floor((now - commentTime) / 1000);
                var minutesElapsed = Math.floor(secondsElapsed / 60);
                var hoursElapsed = Math.floor(minutesElapsed / 60);
                var daysElapsed = Math.floor(hoursElapsed / 24);
                var weeksElapsed = Math.floor(daysElapsed / 7);
                var monthsElapsed = Math.floor(daysElapsed / 30.44); // Sử dụng trung bình 30.44 ngày trong một tháng
                var yearsElapsed = Math.floor(daysElapsed / 365.25); // Sử dụng trung bình 365.25 ngày trong một năm (để tính toán năm nhuận)

                if (yearsElapsed > 0) {
                    timeupElement.textContent = yearsElapsed + ' năm trước';
                } else if (monthsElapsed > 0) {
                    timeupElement.textContent = monthsElapsed + ' tháng trước';
                } else if (weeksElapsed > 0) {
                    timeupElement.textContent = weeksElapsed + ' tuần trước';
                } else if (daysElapsed > 0) {
                    timeupElement.textContent = daysElapsed + ' ngày trước';
                } else if (hoursElapsed > 0) {
                    timeupElement.textContent = hoursElapsed + ' giờ trước';
                } else if (minutesElapsed > 0) {
                    timeupElement.textContent = minutesElapsed + ' phút trước';
                } else {
                    timeupElement.textContent = secondsElapsed + ' giây trước';
                }
            }, 1000);

        }
    }

}

var allIdPosts= [];
var idUserPost;
function renderCourses(courses) {

    var listPost = document.getElementById('conainer');
    shuffleArray(courses);

    var htmls = courses.map(function (course) {

        var tennguoidung= course?.user ? course?.user.username : 'Người dùng đã bị mất tài khoản';
        var avatarnguoidung=course?.user?.avatar ? /avatar/+course.user.avatar : './image/default-avatar.png';


        var commentTime = course.thoigianbinhluan;
        var node = document.createElement("div");
        node.classList.add('nav-left-container-header-nav-container')

        node.innerHTML =`<div class="nav-left-container-header-nav-container__header">
        <div class="nav-left-container-header-nav-container__header-div--image"><img src=${avatarnguoidung} alt="" class="nav-left-container-header-nav-container__header-image"></div>
        <div class="nav-left-container-header-nav-container__header-div--right">
            <h1 class="nav-left-container-header-nav-container__header-name">${tennguoidung}
            </h1>
            <span class="nav-left-container-header-nav-container__header-timeup"></span>
        </div>
    </div>
    <div class="nav-left-container-header-nav-container__content">
        <div class="nav-left-container-header-nav-container__content-all">
            <div class="nav-left-container-header-nav-container__title div-importain">
                ${course?.content ? course.content : ''}

            </div>
            <span class="span-xemthem" style="display: none;">Xem thêm</span>
        </div>

        <div class="nav-left-container-header-nav-container__image">

        </div>
        <div style="display: flex; flex-direction: row;">
            <div class="nav-left-container-header-nav-container__soluongcamxuc">
                <div>
                    <img src="./image/like.svg" alt="" class="nav-left-container-header-nav-container__soluongcamxuc-image">
                </div>
                <div>
                    <span style="line-height: 20px;" class="soluongthich">${course.like}</span> lượt
                    thích
                </div>
            </div>
            <div class="nav-left-container-header-nav-container__soluongcamxuc">
                <!-- <div>
                <img src="./image/like.svg" alt=""
                    class="nav-left-container-header-nav-container__soluongcamxuc-image">
            </div> -->
                <div>
                    <span style="line-height: 20px;" class="soluongbinhluan">0 </span> bình
                    luận
                </div>
            </div>
        </div>
        <div class="nav-left-container-header-nav-container__camxuc">
            <form action="#">
                <div class="nav-left-container-header-nav-container__camxuc-like">
                    <button type="submit" class="nav-left-container-header-nav-container__camxuc-like-submit" id="chualikediv">
                        <i class="fa-regular fa-thumbs-up" id="chualike"></i>
                        <!-- <i class="fa-solid fa-thumbs-up" id="dalike"></i> -->
                        <span>Thích</span>
                    </button>
                </div>
            </form>
            <form action="#">
                <div class="nav-left-container-header-nav-container__camxuc-binhluan">
                    <button type="submit" class="nav-left-container-header-nav-container__camxuc-binhluan-submit">
                        <i class="fa-regular fa-comment"></i>
                        <span>Bình luận</span>
                    </button>
                </div>
            </form>
        </div>

    </div>
    <div class="nav-left-container-header-nav-container__comment" style="display: none;">
        <div class="nav-left-container-header-nav-container-1">


        </div>
        <div class="submit-binhluan">

            <div class="nav-left-container-header-nav-container__header-div--image">
                <img src=""  alt="" class="nav-left-container-header-nav-container__header-image submit_image">
            </div>
            <form action="">
                <div class="submit-binhluan-element__right">

                    <input type="text" placeholder="Viết bình luận..." class="submit-binhluan-element__right-input">
                    <div class="submit-binhluan-element__right-submit">
                        <button type="submit" class="submit-binhluan-element__right-submit--button" style="pointer-events: none;" onclick="postCreateCmt(${data}, ${course.id})">
                            <i class="fa-solid fa-up-right-from-square"></i>
                        </button>
                    </div>

                </div>
            </form>

        </div>
    </div>`;
    var imgElement = document.createElement('img');
    var divcontainerimg= node.querySelector('.nav-left-container-header-nav-container__image');
    imgElement.alt = '';
    imgElement.classList.add('nav-left-container-header-nav-container__image-element');
    if (course.image) {
        imgElement.src = course.image;
    } else {
        imgElement.style.display = 'none';
    }
    var chuabinhluans= node.querySelector('.nav-left-container-header-nav-container-1');
    var quanlitybinhluan= node.querySelector('.soluongbinhluan');
    var vietbinhluan = node.querySelector('.submit-binhluan-element__right-input');
    var data;

    vietbinhluan.oninput = function (e){
        data=e.target.value;
    }
    var soluong=0;
    allComments.map(function (comment){
        if(course.id === comment.baiViet.id){
            soluong= soluong+1;
            var avatarnguoidung2=comment?.user?.avatar ? /avatar/+comment.user.avatar : './image/default-avatar.png';
            var thoigianbinhluan=comment.thoigiandangbai;
            var newElement = document.createElement('div');
                newElement.classList.add('nav-left-container-header-nav-container__comment-user');

                // Thêm nội dung cho phần tử mới
                newElement.innerHTML = `

                    <div class="nav-left-container-header-nav-container__header">
                        <div class="nav-left-container-header-nav-container__header-div--image">
                            <img src=${avatarnguoidung2} alt="" class="nav-left-container-header-nav-container__header-image">
                        </div>
                        <div>
                            <div class="nav-left-container-header-nav-container__comment-user-right">
                                <h1 class="nav-left-container-header-nav-container__header-name-comment">${comment?.user ? comment.user.username : 'Underfind'}</h1>
                                <span class="nav-left-container-header-nav-container__header-name-span">${comment.content}</span>
                                <div class="nav-left-container-header-nav-container__header-name-icon">
                                    <div class="nav-left-container-header-nav-container__header-name-icon--left">
                                        <img src="/image/like.svg" alt="" class="nav-left-container-header-nav-container__header-name-icon--like">
                                    </div>
                                    <div class="nav-left-container-header-nav-container__header-name-icon--right">
                                        <span class="nav-left-container-header-nav-container__header-name-icon--right-span">${comment.like}</span>
                                    </div>
                                </div>
                            </div>
                            <div class="nav-left-container-header-nav-container__comment-user-right--bottom">
                                <span class="nav-left-container-header-nav-container__header-timeup"></span>
                                <form action="#">
                                    <button class="nav-left-container-header-nav-container__comment-user-right--bottom-bnb-like nav_binhluan">Thích</button>
                                </form>
                                <form action="#">
                                <button
                                    class="nav-left-container-header-nav-container__comment-user-right--bottom-bnb-cmt">
                                    Phản hồi
                                </button>
                            </form>
                            </div>
                        </div>
                   </div>

                   <div class="submit-binhluan2" style="display: none;">

                   <div
                       class="nav-left-container-header-nav-container__header-div--image2">
                       <img src="" alt=""
                           class="nav-left-container-header-nav-container__header-image2 submit_image2">
                   </div>
                   <form action="">
                       <div class="submit-binhluan-element__right">

                           <input type="text" placeholder="Viết bình luận..."
                               class="submit-binhluan-element__right-input2" style="width:380px;
                                                                                        border-style: none;
                                                                                        background-color: transparent;
                                                                                        outline: none;
                                                                                        font-size: 15px;">
                           <div class="submit-binhluan-element__right-submit">
                               <button type="submit"
                                   class="submit-binhluan-element__right-submit--button2"
                                   style="pointer-events: none;">
                                   <i class="fa-solid fa-up-right-from-square"></i>
                               </button>
                           </div>

                       </div>
                   </form>

               </div>
                </div>`;
                chuabinhluans.appendChild(newElement);
            quanlitybinhluan.innerHTML= soluong;

            var timeupElement = newElement.querySelector('.nav-left-container-header-nav-container__header-timeup');
            setInterval(function() {
                var now = new Date();
                var secondsElapsed = Math.floor((now - thoigianbinhluan) / 1000);
                var minutesElapsed = Math.floor(secondsElapsed / 60);
                var hoursElapsed = Math.floor(minutesElapsed / 60);
                var daysElapsed = Math.floor(hoursElapsed / 24);
                var weeksElapsed = Math.floor(daysElapsed / 7);
                var monthsElapsed = Math.floor(daysElapsed / 30.44); // Sử dụng trung bình 30.44 ngày trong một tháng
                var yearsElapsed = Math.floor(daysElapsed / 365.25); // Sử dụng trung bình 365.25 ngày trong một năm (để tính toán năm nhuận)

                if (yearsElapsed > 0) {
                    timeupElement.textContent = yearsElapsed + ' năm trước';
                } else if (monthsElapsed > 0) {
                    timeupElement.textContent = monthsElapsed + ' tháng trước';
                } else if (weeksElapsed > 0) {
                    timeupElement.textContent = weeksElapsed + ' tuần trước';
                } else if (daysElapsed > 0) {
                    timeupElement.textContent = daysElapsed + ' ngày trước';
                } else if (hoursElapsed > 0) {
                    timeupElement.textContent = hoursElapsed + ' giờ trước';
                } else if (minutesElapsed > 0) {
                    timeupElement.textContent = minutesElapsed + ' phút trước';
                } else {
                    timeupElement.textContent = secondsElapsed + ' giây trước';
                }
            }, 1000);
            var newElement3= document.createElement('div');
            var soluongphanhoi=0;
            var newElement2List = [];
            allReps.map(function (phanhoi){
                if(comment.id == phanhoi.binhluan.id){
                var avatarnguoidung4=phanhoi?.user?.avatar ? /avatar/+phanhoi.user.avatar : './image/default-avatar.png';
                    soluongphanhoi=soluongphanhoi+1;
                    var thoigianphanhoi=phanhoi.thoigianphanhoi;

                    var newElement2 = document.createElement('div');
                    newElement2.style.display= 'none';
                    newElement2.classList.add('nav-left-container-header-nav-container__comment-user2');
                    newElement2.innerHTML=`<div class="nav-left-container-header-nav-container__header">
                    <div
                    class="nav-left-container-header-nav-container__header-div--image2">
                    <img src=${avatarnguoidung4} alt=""
                        class="nav-left-container-header-nav-container__header-image2">
                </div>
                    <div>
                        <div
                            class="nav-left-container-header-nav-container__comment-user-right">
                            <h1
                                class="nav-left-container-header-nav-container__header-name-comment">
                                ${phanhoi?.user ? phanhoi.user.username : 'Người tham gia ẩn danh'}
                            </h1>
                            <span
                                class="nav-left-container-header-nav-container__header-name-span">${phanhoi.content}</span>



                        </div>
                        <div
                            class="nav-left-container-header-nav-container__comment-user-right--bottom">
                            <span
                                class="nav-left-container-header-nav-container__header-timeup">


                               </span>

                        </div>
                    </div>
                    </div>`;


                    newElement3.classList.add('nav-left-container-header-nav-container__comment-user3');
                    newElement3.innerHTML=`
                    <i class="fa-solid fa-arrows-turn-right"></i> Xem tất cả
                    <span>${soluongphanhoi}</span> bình luận
                    `;

                    newElement2List.push(newElement2);



                    newElement.appendChild(newElement2);
                    var timeupElement2 = newElement2.querySelector('.nav-left-container-header-nav-container__header-timeup');
            setInterval(function() {
                var now = new Date();
                var secondsElapsed = Math.floor((now - thoigianphanhoi) / 1000);
                var minutesElapsed = Math.floor(secondsElapsed / 60);
                var hoursElapsed = Math.floor(minutesElapsed / 60);
                var daysElapsed = Math.floor(hoursElapsed / 24);
                var weeksElapsed = Math.floor(daysElapsed / 7);
                var monthsElapsed = Math.floor(daysElapsed / 30.44); // Sử dụng trung bình 30.44 ngày trong một tháng
                var yearsElapsed = Math.floor(daysElapsed / 365.25); // Sử dụng trung bình 365.25 ngày trong một năm (để tính toán năm nhuận)

                if (yearsElapsed > 0) {
                    timeupElement2.textContent = yearsElapsed + ' năm trước';
                } else if (monthsElapsed > 0) {
                    timeupElement2.textContent = monthsElapsed + ' tháng trước';
                } else if (weeksElapsed > 0) {
                    timeupElement2.textContent = weeksElapsed + ' tuần trước';
                } else if (daysElapsed > 0) {
                    timeupElement2.textContent = daysElapsed + ' ngày trước';
                } else if (hoursElapsed > 0) {
                    timeupElement2.textContent = hoursElapsed + ' giờ trước';
                } else if (minutesElapsed > 0) {
                    timeupElement2.textContent = minutesElapsed + ' phút trước';
                } else {
                    timeupElement2.textContent = secondsElapsed + ' giây trước';
                }
            }, 1000);

                }
                newElement3.onclick = function (){
                    // newElement2.style.display= 'block';
                    newElement2List.map(element =>
                            element.style.display= 'block'
                    )
                    newElement3.style.display= 'none'
                }

                newElement.appendChild(newElement3);

            });

                var repBinhLuan= newElement.querySelector('.nav-left-container-header-nav-container__comment-user-right--bottom-bnb-cmt');
                allComments1.push(comment);
            var newElementXanh= newElement.querySelector('.nav_binhluan');
            var soluonglikecmt= newElement.querySelector('.nav-left-container-header-nav-container__header-name-icon--right-span')
            var allLikeComment=[];
            var allNewElement=[];
            getUser2().then(function(user) {
                getUserComment().then(function(users){
                    users.map(user2 => {
                    allNewElement.push(newElementXanh);
                        allLikeComment.push(user2.id);
                        if(comment.id === user2.binhluan.id && user2.user.username === user.username){
                            newElementXanh.classList.toggle('xanh');
                            newElementXanh.onclick = () => assignLikeEvent3(user2.id,user,newElementXanh,soluonglikecmt,comment.id);
                        }else{
                           //                        newElementXanh.onclick = () => assignLikeEvent3(user2.id,newElementXanh);
                                      var userCmtId=Math.floor(Math.random() * 100000);
                                   newElementXanh.onclick = () => assignLikeEvent3(userCmtId,user,newElementXanh,soluonglikecmt,comment.id);
                           }
//                             for(let i=0;i<allLikeComment.length;i++){
//                                    allNewElement[i].onclick = () => assignLikeEvent3(allLikeComment[i],user,newElementXanh,soluonglikecmt,comment.id);
//
//                             }

                    })

                });

            });


        }


    });
    var timeupElement = node.querySelector('.nav-left-container-header-nav-container__header-timeup');
    setInterval(function() {
        var now = new Date();
        var secondsElapsed = Math.floor((now - commentTime) / 1000);
        var minutesElapsed = Math.floor(secondsElapsed / 60);
        var hoursElapsed = Math.floor(minutesElapsed / 60);
        var daysElapsed = Math.floor(hoursElapsed / 24);
        var weeksElapsed = Math.floor(daysElapsed / 7);
        var monthsElapsed = Math.floor(daysElapsed / 30.44); // Sử dụng trung bình 30.44 ngày trong một tháng
        var yearsElapsed = Math.floor(daysElapsed / 365.25); // Sử dụng trung bình 365.25 ngày trong một năm (để tính toán năm nhuận)

        if (yearsElapsed > 0) {
            timeupElement.textContent = yearsElapsed + ' năm trước';
        } else if (monthsElapsed > 0) {
            timeupElement.textContent = monthsElapsed + ' tháng trước';
        } else if (weeksElapsed > 0) {
            timeupElement.textContent = weeksElapsed + ' tuần trước';
        } else if (daysElapsed > 0) {
            timeupElement.textContent = daysElapsed + ' ngày trước';
        } else if (hoursElapsed > 0) {
            timeupElement.textContent = hoursElapsed + ' giờ trước';
        } else if (minutesElapsed > 0) {
            timeupElement.textContent = minutesElapsed + ' phút trước';
        } else {
            timeupElement.textContent = secondsElapsed + ' giây trước';
        }
    }, 1000);
        var chualikebutton = node.querySelector('.nav-left-container-header-nav-container__camxuc-like-submit');
        var chualike = node.querySelector('.fa-thumbs-up');
        var soluongthich = node.querySelector('.soluongthich');
//        chualike.classList.replace('fa-regular', 'fa-solid');

getUser2().then(function(user) {
    var imageA= node.querySelector('.submit_image');
    var imageA2s = node.querySelectorAll('.submit_image2');
    var avatarnguoidung1=user?.avatar ? /avatar/+user.avatar : './image/default-avatar.png';

    imageA2s.forEach(imageA2 => {
            imageA2.src = avatarnguoidung1;
        });
        imageA.src= avatarnguoidung1;


    var allnutdalikes=[];
    var allchualikebutton=[];
    getUserPost().then(function(users) {
        users.map(user1 => {
            if(course.id === user1.baiViet.id && user1.user.username === user.username){
                            allchualikebutton.push(chualikebutton);
                            allnutdalikes.push(user1.id);

                            chualike.classList.replace('fa-regular','fa-solid');
                            chualikebutton.classList.toggle('xanh');
//                            chualikebutton.onclick = () => likePost2(user,user1.id,chualikebutton,chualike,course.id,soluongthich);



             }else{
                    var userId=Math.floor(Math.random() * 100000);
                    chualikebutton.onclick = () => likePost2(user,userId,chualikebutton,chualike,course.id,soluongthich);
             }
                for(let i=0;i<allchualikebutton.length;i++){
                        allchualikebutton[i].onclick = () => likePost2(user,allnutdalikes[i],chualikebutton,chualike,course.id,soluongthich);

                  }

        })
    });
});

        allIdPosts.push(course);

        divcontainerimg.appendChild(imgElement);
        return node;

    });



    // Sắp xếp mảng htmls một cách ngẫu nhiên
//    shuffleArray(htmls);
    // listPost.innerHTML = htmls.join('');
    htmls.forEach(function(node) {
        listPost.appendChild(node);
    });

    assignCommentEvent();
    assignLikeEvent();
//    getUser2().then(function(user) {
//
//         likePost(user);
//    });


    showbinhluan();
    nhapbinhluan();
    showxemthem();
    xemthem();


    // console.log(courses);
}






 function handleUser(user) {
        // Xử lý đối tượng user ở đây
        console.log(user);
        return user;
    }

function getComment(callback) {
    var commentApi = 'http://localhost:8080/user2/showBinhLuan';
    fetch(commentApi)
        .then(function (response) {
            return response.json();
        })
        .then(function(comments) {
            allComments = comments; // Cập nhật giá trị của biến toàn cục allPosts
            callback(comments);
        });

}


var allReps = [];
function getPhanHoi(callback){
    var phanhoiApi = 'http://localhost:8080/user2/showPhanHoi';
    fetch(phanhoiApi)
        .then(function (response) {
            return response.json();
        })
        .then(function(phanhois) {
            allReps = phanhois; // Cập nhật giá trị của biến toàn cục allPosts
            callback(phanhois);
        });
}


function renderComment(comments){

    var htmls = comments.map(function(comment){


    });
}

function rederPhanHoi(phanhois){
    var htmls= phanhois.map(function(phanhoi){

    });
}


var dangbaiviet = document.getElementById('uploadbaiviet');
var baiviets = document.getElementsByClassName('nav-left-container-header-nav-container');
var formupload = document.getElementById('formupload');
var containerAll = document.getElementById('conainer');
var dongbaiviet = document.getElementById('dongbaiviet');





var chech = false;
dangbaiviet.onclick = function () {
    if (!chech) {
        formupload.style.display = 'block';
        containerAll.classList.add('nav-left-container-header-navop');
        chech = true;
    } else {
        formupload.style.display = 'none';
        containerAll.classList.remove('nav-left-container-header-navop');
        chech = false;
    }
}
function dongbaidang() {
    dongbaiviet.onclick = function () {
        formupload.style.display = 'none';
        containerAll.classList.remove('nav-left-container-header-navop');
        chech = false;
    }
}
dongbaidang();
var dulieuanhs = [];
function laydulieuanh() {
    var chonanh = document.getElementById('chonanh');
    var divchonanh = document.getElementById('chon-anh');
    var createbaiviet = document.getElementById('create-baiviet');
    chonanh.onchange = function (e) {
        var file = e.target.files[0];
        var reader = new FileReader();

        reader.onloadend = function () {
            // Tạo một thẻ img mới
            var img = document.createElement('img');
            img.classList.add('showanhdachon');

            // Gắn dữ liệu ảnh vào thuộc tính src của thẻ img
            img.src = reader.result;

            dulieuanhs.push(img);


            createbaiviet.classList.add('create_baivietxanh');

            // Thêm thẻ img vào thẻ div
            divchonanh.appendChild(img);
        }

        if (file) {
            reader.readAsDataURL(file);
        }


    }
}
laydulieuanh();
var dulieunguoidungnhap;

function nguoidungnhaptext() {
    var bandangnghigi = document.getElementById('bandangnghigi');
    var createbaiviet = document.getElementById('create-baiviet');
    bandangnghigi.oninput = function (e) {

        // var duLieu=e.target.value;
        dulieunguoidungnhap = e.target.value;
        if (isNaN(dulieunguoidungnhap)) {
            createbaiviet.classList.add('create_baivietxanh');
        } else {
            createbaiviet.classList.remove('create_baivietxanh');
        }
    }
}
nguoidungnhaptext();

function likedidong() {
    var soluongthichs = document.getElementsByClassName('soluongthich');
    var chualikebuttons = document.getElementsByClassName('nav-left-container-header-nav-container__camxuc-like-submit');
    for (let i = 0; i < chualikebuttons.length; i++) {
        chualikebuttons[i].onclick = function () {
            var soluongthich1 = parseInt(soluongthichs[i].innerHTML);
            if (chualikes[i].classList.contains('fa-regular')) {
                chualikes[i].classList.replace('fa-regular', 'fa-solid');
                chualikebuttons[i].classList.toggle('xanh');
                if (!isNaN(soluongthich1)) {
                    soluongthichs[i].innerHTML = soluongthich1 + 1;
                } else {
                    soluongthichs[i].innerHTML = 1;
                }
            } else {
                chualikes[i].classList.replace('fa-solid', 'fa-regular');
                chualikebuttons[i].classList.toggle('xanh');
                soluongthichs[i].innerHTML = soluongthich1 - 1;
            }
        }
    }
}

function showbinhluan() {
    var binhluans = document.getElementsByClassName('nav-left-container-header-nav-container__camxuc-binhluan-submit');
    var binhluanlist = document.getElementsByClassName('nav-left-container-header-nav-container__comment');
    for (let i = 0; i < binhluans.length; i++) {
        binhluans[i].onclick = function () {
            if (binhluanlist[i].style.display === "block") {
                binhluanlist[i].style.display = "none";
            } else {
                binhluanlist[i].style.display = "block";
            }
        }

    }
}

function nhapbinhluan() {
    var vietbinhluans = document.getElementsByClassName('submit-binhluan-element__right-input');
    var submitbinhluans = document.getElementsByClassName('submit-binhluan-element__right-submit--button');
    // var divbinhluans = Array.from(document.getElementsByClassName('nav-left-container-header-nav-container__comment-user'));
    var divbinhluans = document.getElementsByClassName('nav-left-container-header-nav-container__comment-user');
    for (let i = 0; i < vietbinhluans.length; i++) {
        vietbinhluans[i].oninput = function (e) {
            var dulieu = e.target.value;

            if (isNaN(dulieu)) {
                submitbinhluans[i].classList.add('xanh');

                submitbinhluans[i].style.pointerEvents = 'auto';
                start();

            } else {
                submitbinhluans[i].classList.remove('xanh');
                submitbinhluans[i].style.pointerEvents = 'none';
            }
        }

    }
}



function addPost(user) {

    var username=user.username;
    var commentTime1 = new Date();
    commentTime1.setMinutes(commentTime1.getMinutes() - commentTime1.getTimezoneOffset());
    var mysqlFormat = commentTime1.toISOString().slice(0, 19).replace('T', ' ');

    var chuatatcabaiviet = document.getElementsByClassName('nav-left-container-header-nav')[0];
    var createbaiviet1 = document.getElementById('create-baiviet');


    createbaiviet1.onclick = function () {

        var newElement = document.createElement('div');
        newElement.classList.add('nav-left-container-header-nav-container');
        newElement.innerHTML = `<div class="nav-left-container-header-nav-container__header">
    <div class="nav-left-container-header-nav-container__header-div--image"><img
            src="./image/avatar.png" alt=""
            class="nav-left-container-header-nav-container__header-image"></div>
    <div class="nav-left-container-header-nav-container__header-div--right">
        <h1 class="nav-left-container-header-nav-container__header-name">username
        </h1>
        <span class="nav-left-container-header-nav-container__header-timeup">0 giây trước</span>
    </div>
</div>
<div class="nav-left-container-header-nav-container__content">
    <div class="nav-left-container-header-nav-container__content-all">
        <div class="nav-left-container-header-nav-container__title div-importain">
            ${dulieunguoidungnhap}

        </div>
        <span class="span-xemthem" style="display: none;">Xem thêm</span>
    </div>

    <div class="nav-left-container-header-nav-container__image">

    </div>
    <div style="display: flex; flex-direction: row;">
        <div class="nav-left-container-header-nav-container__soluongcamxuc">
            <div>
                <img src="./image/like.svg" alt=""
                    class="nav-left-container-header-nav-container__soluongcamxuc-image">
            </div>
            <div>
                <span style="line-height: 20px;" class="soluongthich">0</span> lượt
                thích
            </div>
        </div>
        <div class="nav-left-container-header-nav-container__soluongcamxuc">
            <!-- <div>
            <img src="./image/like.svg" alt=""
                class="nav-left-container-header-nav-container__soluongcamxuc-image">
        </div> -->
            <div>
                <span style="line-height: 20px;" class="soluongbinhluan">0 </span> bình
                luận
            </div>
        </div>
    </div>
    <div class="nav-left-container-header-nav-container__camxuc">
        <form action="#">
            <div class="nav-left-container-header-nav-container__camxuc-like">
                <button type="submit"
                    class="nav-left-container-header-nav-container__camxuc-like-submit"
                    id="chualikediv">
                    <i class="fa-regular fa-thumbs-up" id="chualike"></i>
                    <!-- <i class="fa-solid fa-thumbs-up" id="dalike"></i> -->
                    <span>Thích</span>
                </button>
            </div>
        </form>
        <form action="#">
            <div class="nav-left-container-header-nav-container__camxuc-binhluan">
                <button type="submit"
                    class="nav-left-container-header-nav-container__camxuc-binhluan-submit">
                    <i class="fa-regular fa-comment"></i>
                    <span>Bình luận</span>
                </button>
            </div>
        </form>
    </div>

</div>
<div class="nav-left-container-header-nav-container__comment" style="display: none;">
    <div class="nav-left-container-header-nav-container-1">



    </div>
    <div class="submit-binhluan">

        <div class="nav-left-container-header-nav-container__header-div--image">
            <img src="./image/avatar.png" alt=""
                class="nav-left-container-header-nav-container__header-image">
        </div>
        <form action="">
            <div class="submit-binhluan-element__right">

                <input type="text" placeholder="Viết bình luận..."
                    class="submit-binhluan-element__right-input">
                <div class="submit-binhluan-element__right-submit">
                    <button type="submit"
                        class="submit-binhluan-element__right-submit--button"
                        style="pointer-events: none;">
                        <i class="fa-solid fa-up-right-from-square"></i>
                    </button>
                </div>

            </div>
        </form>

    </div>
</div>`;

        chuatatcabaiviet.appendChild(newElement);


        var timeupElement = newElement.querySelector('.nav-left-container-header-nav-container__header-timeup');
        var bandangnghigi = document.getElementById('bandangnghigi');
        // Lưu thời điểm bình luận
        var commentTime = new Date();
        setInterval(function () {
            var now = new Date();
            var secondsElapsed = Math.floor((now - commentTime) / 1000);
            var minutesElapsed = Math.floor(secondsElapsed / 60);
            var hoursElapsed = Math.floor(minutesElapsed / 60);
            var daysElapsed = Math.floor(hoursElapsed / 24);
            var weeksElapsed = Math.floor(daysElapsed / 7);
            var monthsElapsed = Math.floor(daysElapsed / 30.44); // Sử dụng trung bình 30.44 ngày trong một tháng
            var yearsElapsed = Math.floor(daysElapsed / 365.25); // Sử dụng trung bình 365.25 ngày trong một năm (để tính toán năm nhuận)

            if (yearsElapsed > 0) {
                timeupElement.textContent = yearsElapsed + ' năm trước';
            } else if (monthsElapsed > 0) {
                timeupElement.textContent = monthsElapsed + ' tháng trước';
            } else if (weeksElapsed > 0) {
                timeupElement.textContent = weeksElapsed + ' tuần trước';
            } else if (daysElapsed > 0) {
                timeupElement.textContent = daysElapsed + ' ngày trước';
            } else if (hoursElapsed > 0) {
                timeupElement.textContent = hoursElapsed + ' giờ trước';
            } else if (minutesElapsed > 0) {
                timeupElement.textContent = minutesElapsed + ' phút trước';
            } else {
                timeupElement.textContent = secondsElapsed + ' giây trước';
            }
        }, 1000);

        var navElement = newElement.querySelector('.nav-left-container-header-nav-container__image');
        var imgs = []; // Tạo mảng imgs
        for (let i = 0; i < dulieuanhs.length; i++) {
            var img = document.createElement('img');

            img.classList.add('nav-left-container-header-nav-container__image-element');

            // Gắn dữ liệu ảnh vào thuộc tính src của thẻ img
            img = dulieuanhs[i];

            img.classList.replace('showanhdachon', 'nav-left-container-header-nav-container__image-element');
            // Thêm thẻ img vào thẻ div
            navElement.appendChild(img);

            imgs.push(img);
            dulieuanhs = [];
            bandangnghigi.value = '';
        }
        var formData = {
            username: username,
            title: dulieunguoidungnhap,
            time: mysqlFormat,
            images: imgs.map(function (img) {
                return img.currentSrc != null ? img.currentSrc : '';
            })

        }
        createPost(formData, function () {
            getCourses(renderCourses);

        });
        dulieuanhs = [];
        bandangnghigi.value = '';


        formupload.style.display = 'none';
        containerAll.classList.remove('nav-left-container-header-navop');
        chech = false;
        likedidong();
        showbinhluan();
        nhapbinhluan();
        window.location.reload();
    }


}

//addPost();


var postApi = 'http://localhost:8080/user2/create-post';

function createPost(data, callback) {

    var options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };
    fetch(postApi, options)
        .then(function (response) {
            response.json();
        })
        .then(callback);

}
var likeApi= 'http://localhost:8080/user2/post-updateLike';
function postUpdateLike(data, callback) {

    var options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };
    fetch(likeApi, options)
        .then(function (response) {
            response.json();
        })
        .then(callback);

}

function postCancelLike(data, callback) {
    var CancelLikeApi= 'http://localhost:8080/user2/post-updateLike2';
    var options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };
    fetch(CancelLikeApi, options)
        .then(function (response) {
            response.json();
        })
        .then(callback);

}

var cmtLikeApi= 'http://localhost:8080/user2/cmt-updateLike';
function cmtUpdateLike(data, callback){
    var options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };
    fetch(cmtLikeApi, options)
        .then(function (response) {
            response.json();
        })
        .then(callback);
}

function cmtCancelLike(data, callback){
var cmtCancelLikeApi= 'http://localhost:8080/user2/cmt-CancelLikeApi';
        var options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        };
        fetch(cmtCancelLikeApi, options)
            .then(function (response) {
                response.json();
            })
            .then(callback);
}

var phanhoiLikeApi= 'http://localhost:8080/user2/phanhoi-updateLike';
function phanhoiUpdateLike(data, callback){
    var options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };
    fetch(phanhoiLikeApi, options)
        .then(function (response) {
            response.json();
        })
        .then(callback);
}

function postCreateCmt(data,callback) {
    var binhluanApi= 'http://localhost:8080/user2/post-createBinhLuan';
    var options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };
    fetch(binhluanApi, options)
        .then(function (response) {
            response.json();
        })
        .then(callback);

}

function createPhanHoi(data, callback){
    var phanhoiApi= 'http://localhost:8080/user2/binhluan-createPhanhoi';
    var options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };
    fetch(phanhoiApi, options)
        .then(function (response) {
            response.json();
        })
        .then(callback);
}