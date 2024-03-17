// var xemthems = Array.from(document.getElementsByClassName('span-xemthem'));

var noidungs = document.getElementsByClassName('div-importain');
// console.log(xemthem)
// console.log(noidung)

// xemthem.onclick= function(){
//     noidung.classList.toggle('nav-left-container-header-nav-container__title');
//     noidung.classList.toggle('nav-left-container-header-nav-container__title2');
// }
// console.log(xemthems);
// console.log(noidungs);
function xemthem(){
    var xemthems = document.getElementsByClassName('span-xemthem');
    for (let i = 0; i < xemthems.length; i++) {

        xemthems[i].onclick = function () {

            if (noidungs[i].classList.contains('nav-left-container-header-nav-container__title')) {
                noidungs[i].classList.replace('nav-left-container-header-nav-container__title', 'nav-left-container-header-nav-container__title2');
                xemthems[i].innerHTML = 'Bỏ bớt';

                // console.log(noidungs[i]);
                // console.log(noidungs);
            } else {

                noidungs[i].classList.replace('nav-left-container-header-nav-container__title2', 'nav-left-container-header-nav-container__title');
                xemthems[i].innerHTML = 'Xem thêm';

            }

        }
    }
}
xemthem();

function showxemthem(){
    var xemthems = document.getElementsByClassName('span-xemthem');

        for (let i = 0; i < xemthems.length; i++) {

            if (noidungs[i].offsetHeight > 40) {
                xemthems[i].style.display = "block";
            }
            // console.log(noidungs[i].offsetHeight);
            // console.log(noidungs[i]);
        }

}
showxemthem();
var chualikes = document.getElementsByClassName('fa-thumbs-up');
// var chualikediv= document.getElementById('chualikediv');
var chualikebuttons = document.getElementsByClassName('nav-left-container-header-nav-container__camxuc-like-submit');
var binhluans = document.getElementsByClassName('nav-left-container-header-nav-container__camxuc-binhluan-submit');
var binhluanlist = document.getElementsByClassName('nav-left-container-header-nav-container__comment');
var soluongthichs = document.getElementsByClassName('soluongthich');
var soluongbinhluans = document.getElementsByClassName('soluongbinhluan');


function likePost(user){

    var username=user.username;

    for (let i = 0; i < chualikebuttons.length; i++) {
        // console.log(truyenid(allPosts,i));
        chualikebuttons[i].onclick = function () {
            var soluongthich1 = parseInt(soluongthichs[i].innerHTML);
            if (chualikes[i].classList.contains('fa-regular')) {
                chualikes[i].classList.replace('fa-regular', 'fa-solid');
                chualikebuttons[i].classList.toggle('xanh');
                if (!isNaN(soluongthich1)) {
                        console.log(truyenid(allPosts,i));
                    soluongthichs[i].innerHTML = soluongthich1 + 1;
                     var formData = {
                        username: username,
                        id: truyenid(allPosts,i),
                     }

                        postUpdateLike(formData,function (){
                        getCourses(truyenid);
                   });


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


function likePost2(user,idUserPost,chualikebutton,chualike,idPost,soluongthich){

    var username=user.username;

            var soluongthich1 = parseInt(soluongthich.innerHTML);
            if (chualike.classList.contains('fa-regular')) {
                chualike.classList.replace('fa-regular', 'fa-solid');
                chualikebutton.classList.toggle('xanh');
                if (!isNaN(soluongthich1)) {
                    soluongthich.innerHTML = soluongthich1 + 1;
                     var formData = {
                        idUserPost: idUserPost,
                        username: username,
                        id: idPost,
                     }

                        postUpdateLike(formData,function (){
                        renderCourses();
                   });


                } else {
                    soluongthich.innerHTML = 1;
                }
            } else {
                chualike.classList.replace('fa-solid', 'fa-regular');
                chualikebutton.classList.toggle('xanh');
                soluongthich.innerHTML = soluongthich1 - 1;

                                                var formData = {
                                                                    idUserPost: idUserPost,
                                                                    id: idPost,
                                                                 }

                                                                    postCancelLike(formData,function (){
                                                                    getCourses(renderCourses);
//                                                                    window.location.reload();

                                                    });

            }


}

function unLikePost(idUserPost,chualikebutton,chualike,idPost,soluongthich){

       console.log(chualikebutton);
       console.log('chualikebutton');

            var soluongthich1 = parseInt(soluongthich.innerHTML);
                            chualike.classList.replace('fa-solid', 'fa-regular');
                            chualikebutton.classList.toggle('xanh');
                            soluongthich.innerHTML = soluongthich1 - 1;
                                           var formData = {
                                                    idUserPost: idUserPost,
                                                    id: idPost,
                                                 }

                                                    postCancelLike(formData,function (){
                                                    getCourses(renderCourses);
//                                                        window.location.reload();
                                    });

}

//likePost();

for (let i = 0; i < binhluans.length; i++) {
    binhluans[i].onclick = function () {
        if (binhluanlist[i].style.display === "block") {
            binhluanlist[i].style.display = "none";
        } else {
            binhluanlist[i].style.display = "block";
        }
    }

}

var chualike2s = document.getElementsByClassName('nav-left-container-header-nav-container__comment-user-right--bottom-bnb-like');
var soluonglikes = document.getElementsByClassName('nav-left-container-header-nav-container__header-name-icon--right-span');


var check = Array(chualike2s.length).fill(false);
var check4 = Array(chualike2s.length).fill(false);

for (let i = 0; i < chualike2s.length; i++) {

    chualike2s[i].onclick = function () {
        var soluonglike = parseInt(soluonglikes[i].innerHTML);



        chualike2s[i].classList.toggle('xanh');
        if (!check[i]) {
            if (!isNaN(soluonglike)) {
                soluonglikes[i].innerHTML = soluonglike + 1;
            } else {
                soluonglikes[i].innerHTML = 1;
            }
            check[i] = true;

        } else {
            soluonglikes[i].innerHTML = soluonglike - 1;
            check[i] = false;

        }

    }
}




var vietbinhluans = document.getElementsByClassName('submit-binhluan-element__right-input');
var submitbinhluans = document.getElementsByClassName('submit-binhluan-element__right-submit--button');
// var divbinhluans = Array.from(document.getElementsByClassName('nav-left-container-header-nav-container__comment-user'));
var divbinhluans =document.getElementsByClassName('nav-left-container-header-nav-container__comment-user');
for (let i = 0; i < vietbinhluans.length; i++) {
    vietbinhluans[i].oninput = function (e) {
        var dulieu = e.target.value;

        if (isNaN(dulieu)) {
            submitbinhluans[i].classList.add('xanh');

            submitbinhluans[i].style.pointerEvents = 'auto';

        } else {
            submitbinhluans[i].classList.remove('xanh');
            submitbinhluans[i].style.pointerEvents = 'none';
        }
    }

}
var vietbinhluan2s = document.getElementsByClassName('submit-binhluan-element__right-input2');
var submitbinhluan2s = document.getElementsByClassName('submit-binhluan-element__right-submit--button2');
var check5 = Array(submitbinhluan2s.length).fill(false);
for (let i = 0; i < vietbinhluan2s.length; i++) {
    vietbinhluan2s[i].oninput = function (e) {
        var dulieu = e.target.value;

        if (isNaN(dulieu)) {
            submitbinhluan2s[i].classList.add('xanh');

            submitbinhluan2s[i].style.pointerEvents = 'auto';

        } else {
            submitbinhluan2s[i].classList.remove('xanh');
            submitbinhluan2s[i].style.pointerEvents = 'none';
        }
    }

}


//function assignInput2Event(user){
//var username=user.username;
//    var commentTime1 = new Date();
//    commentTime1.setMinutes(commentTime1.getMinutes() - commentTime1.getTimezoneOffset());
//    var mysqlFormat = commentTime1.toISOString().slice(0, 19).replace('T', ' ');
//
//    var binhluanusers=document.getElementsByClassName('nav-left-container-header-nav-container__header');
//    for(let i=0;i<binhluanusers.length;i++){
//        submitbinhluan2s[i].onclick = function(e){
//
//
//            var dulieu=vietbinhluan2s[i].value;
//            var biengan;
//            for(let j=0;j<divcontainercmtuser.length;j++){
//                biengan=j;
//
//            }
//
//            var dulieu=vietbinhluan2s[i].value;
//            var soluongbinhluan1 = parseInt(soluongbinhluans[biengan].innerHTML);
//
//            if (!check5[i]) {
//
//                if (!isNaN(soluongbinhluan1)) {
//                    soluongbinhluans[biengan-1].innerHTML = soluongbinhluan1 + 1;
//                    var formData = {
//                        id: idcomment(allComments,i),
//                        username: username,
//                        time: mysqlFormat,
//                        data: dulieu
//                     }
//
//                     createPhanHoi(formData,function (){
//                        getPhanHoi(rederPhanHoi);
//                   });
//
//                } else {
//                    soluongbinhluans[biengan].innerHTML = 1;
//                }
//                check[i] = true;
//
//            } else {
//                soluongbinhluans[biengan].innerHTML = soluongbinhluan1 - 1;
//                check[i] = false;
//
//            }
//
//            var commentTime = new Date();
//
//            submitbinhluan2s[i].classList.remove('xanh');
//            submitbinhluan2s[i].style.pointerEvents = 'none';
//            vietbinhluan2s[i].value='';
//
//            var newElement = document.createElement('div');
//                newElement.classList.add('nav-left-container-header-nav-container__comment-user2');
//                newElement.innerHTML=`<div class="nav-left-container-header-nav-container__header">
//                <div
//                class="nav-left-container-header-nav-container__header-div--image2">
//                <img src="./image/avatar.png" alt=""
//                    class="nav-left-container-header-nav-container__header-image2">
//            </div>
//                <div>
//                    <div
//                        class="nav-left-container-header-nav-container__comment-user-right">
//                        <h1
//                            class="nav-left-container-header-nav-container__header-name-comment">
//                            Nguyễn Duy Bách
//                        </h1>
//                        <span
//                            class="nav-left-container-header-nav-container__header-name-span">${dulieu}</span>
//                        <div
//                            class="nav-left-container-header-nav-container__header-name-icon">
//                            <div
//                                class="nav-left-container-header-nav-container__header-name-icon--left">
//                                <img src="/image/like.svg" alt=""
//                                    class="nav-left-container-header-nav-container__header-name-icon--like">
//                            </div>
//                            <div
//                                class="nav-left-container-header-nav-container__header-name-icon--right">
//                                <span
//                                    class="nav-left-container-header-nav-container__header-name-icon--right-span"></span>
//                            </div>
//                        </div>
//                    </div>
//                    <div
//                        class="nav-left-container-header-nav-container__comment-user-right--bottom">
//                        <span
//                            class="nav-left-container-header-nav-container__header-timeup">
//                            0 giây trước
//                           </span>
//                        <form action="#">
//                            <button
//                                class="nav-left-container-header-nav-container__comment-user-right--bottom-bnb-like">
//                                Thích
//                            </button>
//                        </form>
//                    </div>
//                </div>
//            </div>`;
//            // divbinhluans.push(newElement);
//            divbinhluans[i].appendChild(newElement);
//
//            assignLikeEvent();
//
//            var timeupElement = newElement.querySelector('.nav-left-container-header-nav-container__header-timeup');
//            setInterval(function() {
//                var now = new Date();
//                var secondsElapsed = Math.floor((now - commentTime) / 1000);
//                var minutesElapsed = Math.floor(secondsElapsed / 60);
//                var hoursElapsed = Math.floor(minutesElapsed / 60);
//                var daysElapsed = Math.floor(hoursElapsed / 24);
//                var weeksElapsed = Math.floor(daysElapsed / 7);
//                var monthsElapsed = Math.floor(daysElapsed / 30.44); // Sử dụng trung bình 30.44 ngày trong một tháng
//                var yearsElapsed = Math.floor(daysElapsed / 365.25); // Sử dụng trung bình 365.25 ngày trong một năm (để tính toán năm nhuận)
//
//                if (yearsElapsed > 0) {
//                    timeupElement.textContent = yearsElapsed + ' năm trước';
//                } else if (monthsElapsed > 0) {
//                    timeupElement.textContent = monthsElapsed + ' tháng trước';
//                } else if (weeksElapsed > 0) {
//                    timeupElement.textContent = weeksElapsed + ' tuần trước';
//                } else if (daysElapsed > 0) {
//                    timeupElement.textContent = daysElapsed + ' ngày trước';
//                } else if (hoursElapsed > 0) {
//                    timeupElement.textContent = hoursElapsed + ' giờ trước';
//                } else if (minutesElapsed > 0) {
//                    timeupElement.textContent = minutesElapsed + ' phút trước';
//                } else {
//                    timeupElement.textContent = secondsElapsed + ' giây trước';
//                }
//            }, 1000);
//
//        }
//    }
//
//}
function assignLikeEvent3(userCmtId,user,newElementXanh,soluonglikecmt,idCommen) {
    console.log(userCmtId);
    console.log(newElementXanh);
    var username=user.username;
    var chualike2s = document.getElementsByClassName('nav_binhluan');
    var soluonglikes = document.getElementsByClassName('nav-left-container-header-nav-container__header-name-icon--right-span');

    var check = Array(chualike2s.length).fill(false);


            var soluonglike = parseInt(soluonglikecmt.innerHTML);


//            if (!check[i]) {
              if (!newElementXanh.classList.contains('xanh')) {
                if (!isNaN(soluonglike)) {
                    newElementXanh.classList.toggle('xanh');
                    soluonglikecmt.innerHTML = soluonglike + 1;
                    var formData = {
                        id: idCommen,
                        username:username,
                        userCmtId:userCmtId,
                     }

                     cmtUpdateLike(formData,function (){
                        getPhanHoi(rederPhanHoi);
                   });

                } else {
                newElementXanh.classList.toggle('xanh');
                    soluonglikecmt.innerHTML = 1;
                    var formData = {
                        id: idCommen,
                     }

                     cmtUpdateLike(formData,function (){
                        getPhanHoi(rederPhanHoi);
                   });
                }

            } else {
            newElementXanh.classList.toggle('xanh');
                soluonglikecmt.innerHTML = soluonglike - 1;
                var formData = {
                                        id: idCommen,
                                        userCmtId: userCmtId,
                                     }

                                     cmtCancelLike(formData,function (){
                                        getPhanHoi(rederPhanHoi);

            })
        }

}

function assignInputEvent(){
var vietbinhluan2s = document.getElementsByClassName('submit-binhluan-element__right-input2');
var submitbinhluan2s = document.getElementsByClassName('submit-binhluan-element__right-submit--button2');
for (let i = 0; i < vietbinhluan2s.length; i++) {
    vietbinhluan2s[i].oninput = function (e) {

        var dulieu = e.target.value;

        if (isNaN(dulieu)) {
            submitbinhluan2s[i].classList.add('xanh');

            submitbinhluan2s[i].style.pointerEvents = 'auto';
            start();


        } else {
            submitbinhluan2s[i].classList.remove('xanh');
            submitbinhluan2s[i].style.pointerEvents = 'none';
        }
    }

}
}
// Hàm để gán sự kiện
function assignLikeEvent() {
    var chualike2s = document.getElementsByClassName('nav_binhluan');
    var soluonglikes = document.getElementsByClassName('nav-left-container-header-nav-container__header-name-icon--right-span');

    var check = Array(chualike2s.length).fill(false);

    for (let i = 0; i < chualike2s.length; i++) {
        chualike2s[i].onclick = function () {
            var soluonglike = parseInt(soluonglikes[i].innerHTML);
               console.log(i);
            chualike2s[i].classList.toggle('xanh');
            if (!check[i]) {
                if (!isNaN(soluonglike)) {

                    soluonglikes[i].innerHTML = soluonglike + 1;
                    var formData = {
                        id: idcomment(allComments1,i),
                     }

                     cmtUpdateLike(formData,function (){
                        getPhanHoi(rederPhanHoi);
                   });

                } else {
                    soluonglikes[i].innerHTML = 1;
                    var formData = {
                        id: idcomment(allComments1,i),
                     }

                     cmtUpdateLike(formData,function (){
                        getPhanHoi(rederPhanHoi);
                   });
                }
                check[i] = true;
            } else {
                soluonglikes[i].innerHTML = soluonglike - 1;
                check[i] = false;
            }
        }
    }
}
// var commentTime1 = new Date();
// var mysqlFormat = commentTime1.toISOString().slice(0, 19).replace('T', ' ');
// console.log(mysqlFormat);

var divcontainercmtuser=document.getElementsByClassName('nav-left-container-header-nav-container-1');
//function upbinhluan(user){
//    var username=user.username;
//    var avatarnguoidung=user?.avatar ? /avatar/+user.avatar : './image/default-avatar.png';
//
//    var commentTime1 = new Date();
//    commentTime1.setMinutes(commentTime1.getMinutes() - commentTime1.getTimezoneOffset());
//    var mysqlFormat = commentTime1.toISOString().slice(0, 19).replace('T', ' ');
//
//    for (let i = 0; i < vietbinhluans.length; i++) {
//        submitbinhluans[i].onclick = function (e) {
//            var soluongbinhluan1 = parseInt(soluongbinhluans[i].innerHTML);
//            var dulieu = vietbinhluans[i].value;
//            if (!check4[i]) {
//                if (!isNaN(soluongbinhluan1)) {
//                    soluongbinhluans[i].innerHTML = soluongbinhluan1 + 1;
//                    var formData = {
//                        id: truyenid(allIdPosts,i),
//                        time: mysqlFormat,
//                        data: dulieu
//                     }
//
//                        postCreateCmt(formData,function (){
//                        getCourses(truyenid);
//                   });
//                } else {
//                    soluongbinhluans[i].innerHTML = 1;
//                }
//                check[i] = true;
//
//            } else {
//                soluongbinhluans[i].innerHTML = soluongbinhluan1 - 1;
//                check[i] = false;
//
//            }
//
//
//             // Lưu thời điểm bình luận
//             var commentTime = new Date();
//
//                submitbinhluans[i].classList.remove('xanh');
//                submitbinhluans[i].style.pointerEvents = 'none';
//                vietbinhluans[i].value='';
//
//                // Tạo một phần tử mới
//                var newElement = document.createElement('div');
//                newElement.classList.add('nav-left-container-header-nav-container__comment-user');
//
//                // Thêm nội dung cho phần tử mới
//                newElement.innerHTML = `
//
//                    <div class="nav-left-container-header-nav-container__header">
//                        <div class="nav-left-container-header-nav-container__header-div--image">
//                            <img src=${avatarnguoidung} alt="" class="nav-left-container-header-nav-container__header-image">
//                        </div>
//                        <div>
//                            <div class="nav-left-container-header-nav-container__comment-user-right">
//                                <h1 class="nav-left-container-header-nav-container__header-name-comment">${username}</h1>
//                                <span class="nav-left-container-header-nav-container__header-name-span">${dulieu}</span>
//                                <div class="nav-left-container-header-nav-container__header-name-icon">
//                                    <div class="nav-left-container-header-nav-container__header-name-icon--left">
//                                        <img src="/image/like.svg" alt="" class="nav-left-container-header-nav-container__header-name-icon--like">
//                                    </div>
//                                    <div class="nav-left-container-header-nav-container__header-name-icon--right">
//                                        <span class="nav-left-container-header-nav-container__header-name-icon--right-span"></span>
//                                    </div>
//                                </div>
//                            </div>
//                            <div class="nav-left-container-header-nav-container__comment-user-right--bottom">
//                                <span class="nav-left-container-header-nav-container__header-timeup"></span>
//                                <form action="#">
//                                    <button class="nav-left-container-header-nav-container__comment-user-right--bottom-bnb-like">Thích</button>
//                                </form>
//                                <form action="#">
//                                <button
//                                    class="nav-left-container-header-nav-container__comment-user-right--bottom-bnb-cmt">
//                                    Phản hồi
//                                </button>
//                            </form>
//                            </div>
//                        </div>
//                   </div>
//
//                   <div class="submit-binhluan2" style="display: none;">
//
//                   <div
//                       class="nav-left-container-header-nav-container__header-div--image2">
//                       <img src="./image/avatar.png" alt=""
//                           class="nav-left-container-header-nav-container__header-image2">
//                   </div>
//                   <form action="">
//                       <div class="submit-binhluan-element__right">
//
//                           <input type="text" placeholder="Viết bình luận..."
//                               class="submit-binhluan-element__right-input2">
//                           <div class="submit-binhluan-element__right-submit">
//                               <button type="submit"
//                                   class="submit-binhluan-element__right-submit--button2"
//                                   style="pointer-events: none;">
//                                   <i class="fa-solid fa-up-right-from-square"></i>
//                               </button>
//                           </div>
//
//                       </div>
//                   </form>
//
//               </div>
//                </div>`;
//                // Thêm phần tử mới vào divbinhluans
//                divcontainercmtuser[i].appendChild(newElement);
//
//                // divbinhluans.push(newElement);
//                assignLikeEvent();
//                assignCommentEvent();
//
//                var timeupElement = newElement.querySelector('.nav-left-container-header-nav-container__header-timeup');
//                setInterval(function() {
//                    var now = new Date();
//                    var secondsElapsed = Math.floor((now - commentTime) / 1000);
//                    var minutesElapsed = Math.floor(secondsElapsed / 60);
//                    var hoursElapsed = Math.floor(minutesElapsed / 60);
//                    var daysElapsed = Math.floor(hoursElapsed / 24);
//                    var weeksElapsed = Math.floor(daysElapsed / 7);
//                    var monthsElapsed = Math.floor(daysElapsed / 30.44); // Sử dụng trung bình 30.44 ngày trong một tháng
//                    var yearsElapsed = Math.floor(daysElapsed / 365.25); // Sử dụng trung bình 365.25 ngày trong một năm (để tính toán năm nhuận)
//
//                    if (yearsElapsed > 0) {
//                        timeupElement.textContent = yearsElapsed + ' năm trước';
//                    } else if (monthsElapsed > 0) {
//                        timeupElement.textContent = monthsElapsed + ' tháng trước';
//                    } else if (weeksElapsed > 0) {
//                        timeupElement.textContent = weeksElapsed + ' tuần trước';
//                    } else if (daysElapsed > 0) {
//                        timeupElement.textContent = daysElapsed + ' ngày trước';
//                    } else if (hoursElapsed > 0) {
//                        timeupElement.textContent = hoursElapsed + ' giờ trước';
//                    } else if (minutesElapsed > 0) {
//                        timeupElement.textContent = minutesElapsed + ' phút trước';
//                    } else {
//                        timeupElement.textContent = secondsElapsed + ' giây trước';
//                    }
//                }, 1000);
//
//
//        }
//    }
//}
//upbinhluan();

// var phanhois=document.getElementsByClassName('nav-left-container-header-nav-container__comment-user-right--bottom-bnb-cmt');
// var divphanhois=document.getElementsByClassName('submit-binhluan2');
// // var check2=false;
// var check2 = Array(phanhois.length).fill(false);
// console.log(divphanhois);
// for(let i=0;i<phanhois.length;i++){
//     phanhois[i].onclick = function(){
//         console.log(divphanhois[i])
//         if(!check2){
//             divphanhois[i].style.display ='flex';
//         check2=true;
//         console.log(divphanhois[i]);
//         }else{
//             divphanhois[i].style.display ='none';
//          check2=false;
//         }
//     }
// }
function assignCommentEvent() {
    var phanhois = document.getElementsByClassName('nav-left-container-header-nav-container__comment-user-right--bottom-bnb-cmt');
    var divphanhois = document.getElementsByClassName('submit-binhluan2');
    var noidungphanhois=document.getElementsByClassName('nav-left-container-header-nav-container__comment-user2');
    var check2 = Array(phanhois.length).fill(false);
    for (let i = 0; i < phanhois.length; i++) {
        phanhois[i].onclick = function () {
            if (!check2[i]) { // Sử dụng check2[i] thay vì check2
                divphanhois[i].style.display = 'flex';
                assignInputEvent();
                check2[i] = true; // Thay đổi giá trị của check2[i] thay vì check2

            } else {
                divphanhois[i].style.display = 'none';
                check2[i] = false; // Thay đổi giá trị của check2[i] thay vì check2

            }
        }
    }
}



// Array.from(xemthems).forEach((xemthem, i) => {
//     let isExpanded = false;
//     xemthem.onclick = function() {
//         var j = xemthems.indexOf(xemthem);
//         if (!isExpanded) {
//             noidungs[i].classList.replace('nav-left-container-header-nav-container__title', 'nav-left-container-header-nav-container__title2');
//             xemthem.innerHTML='Bỏ bớt';
//             console.log(noidungs[i]);
//             isExpanded = true;
//             console.log(xemthem);
//         } else {
//             console.log(xemthems.indexOf(xemthem));
//             console.log(noidungs[xemthems.indexOf(xemthem)]);
//             noidungs[0].classList.replace('nav-left-container-header-nav-container__title2', 'nav-left-container-header-nav-container__title');
//             xemthem.innerHTML='Xem thêm';
//             isExpanded = false;
//         }
//     }
// });
// Add headers before the routes are defined
// import express from 'express';
// var app = express();

// app.use(function (req, res, next) {

//     // Website you wish to allow to connect
//     res.setHeader('Access-Control-Allow-Origin', 'http://localhost:8080');

//     // Request methods you wish to allow
//     res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');

//     // Request headers you wish to allow
//     res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With,content-type');

//     // Set to true if you need the website to include cookies in the requests sent
//     // to the API (e.g. in case you use sessions)
//     res.setHeader('Access-Control-Allow-Credentials', true);

//     // Pass to next layer of middleware
//     next();

// });


// var courseApi= 'http://localhost:8080/user/show';

// // fetch(courseApi)
// //     .then(function(respon){
// //         return respon.json();
// //     })
// //     .then(function(course){
// //         console.log(course);
// //     });
// function start(){
//         getCourses(renderCourses);
// }
// start();

// function getCourses(callback){
//     fetch(courseApi)
//         .then(function(response){
//             return response.json();
//         })
//         .then(callback);
// }

// function renderCourses(courses){
//     var username= document.getElementsByClassName('nav-left-container-header-nav-container__header-name')[0];
//     courses.map(function(course){
//         username.innerHTML=course.username;
//     });
//     // console.log(courses);
// }