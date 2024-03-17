// document.addEventListener("DOMContentLoaded", function () {
//     var headerHome = document.querySelector(".header-home__3");
//     var container = document.querySelector(".contanier");
//     headerHome.addEventListener("click", function () {
//         var dropdownElement = document.querySelector(".header-home__3--dropdown-element");

//         // Kiểm tra trạng thái hiển thị của dropdown và thay đổi nó
//         if (dropdownElement.style.display === "block") {
//             dropdownElement.style.display = "none";
//         } else {
//             dropdownElement.style.display = "block";
//         }
//     });
//     container.addEventListener("click", function () {
//         dropdownElement.style.display = "none";
//     });

// });

var hearderHome= document.getElementById('div--fullname');
var hearderHomeDrop= document.getElementById('div--drop');
var nav=document.getElementById('nav1');
console.log(hearderHome);
console.log(hearderHomeDrop);

hearderHome.onclick = function(e){
    hearderHomeDrop.classList.toggle('header-home__3--dropdown-element-block');
    // console.log(e.target.value);
};

nav.onclick = function(){
    hearderHomeDrop.classList.remove('header-home__3--dropdown-element-block');
}

var maunen=document.getElementById('thaydoinen');
var container=document.getElementById('container1');

maunen.onclick = function(){
    container.classList.toggle('backgroup');
}



var navRight= document.getElementById('nav--right');
var navLeftul1= document.getElementById('nav-left__ul1');
// var rightcontent1=document.getElementById('nav-right-content');
// var rightcontent2=document.getElementById('nav-right-content-2');
// var rightcontent3=document.getElementById('nav-right-content-3');
var rightContent= navRight.getElementsByClassName('left');
var navleft = navLeftul1.querySelectorAll('li');
// console.log(rightContent);
// console.log(navleft);
// Thiết lập display: none cho tất cả các phần tử
for(let i=0; i< rightContent.length; i++){
    rightContent[i].classList.add('displaynone');
}
rightContent[0].classList.remove('displaynone');
rightContent[0].classList.add('displayblock');


for(let i=0; i< navleft.length; i++ ){
    navleft[i].onclick = function(){
        // Xóa lớp 'displayblock' khỏi tất cả các phần tử
        for(let j=0; j< rightContent.length; j++){
            rightContent[j].classList.remove('displayblock');
            rightContent[j].classList.add('displaynone');
        }
        // Thêm lớp 'displayblock' vào phần tử được click
        rightContent[i].classList.remove('displaynone');
        rightContent[i].classList.add('displayblock');

    };
};

var thisPage=1;
var limit=5;
var listLopDanhNghia= document.querySelectorAll('.table-success1');
var totalRecodrs= listLopDanhNghia.length;


function Page2(list,ctPage1,thisPage2){
    var beginGet=limit*(thisPage2-1);
    var endGet=limit*thisPage2-1;
    for(let i=0;i<list.length;i++){
        if(i >= beginGet && i <= endGet){
            list[i].style.display='table-row';
        }else{
            list[i].style.display='none';
        }

    }
    listPage2(list,ctPage1,thisPage2);

}
function listPage2(list,ctPage1,thisPage2){
    var totalRecodrs1= list.length;
    var totalPage=Math.ceil(totalRecodrs1/limit);
    ctPage1.innerHTML='';

    if(thisPage2 != 1){
        let prev= document.createElement('button');
        prev.innerHTML='<i class="fa-solid fa-angle-left"></i>';
        prev.classList.add('activeElement');
        prev.setAttribute('onclick',"changePage2("+(thisPage2-1)+")");
        ctPage1.appendChild(prev);
    }
    for(let i=1;i <= totalPage;i++){
        let newPage1=document.createElement('button');
        newPage1.classList.add('activeElement');

        newPage1.innerHTML=i;
        if(i == thisPage2){
            newPage1.classList.add('nenxanh');
        }
        newPage1.setAttribute('onclick',"changePage2("+i+")");
        ctPage1.appendChild(newPage1);
    }
    if(thisPage2!= totalPage){
        let next= document.createElement('button');
        next.innerHTML='<i class="fa-solid fa-angle-right"></i>';
        next.classList.add('activeElement');
        next.setAttribute('onclick',"changePage2("+(thisPage2+1)+")");
        ctPage1.appendChild(next);
    }
}
var list= document.querySelectorAll('.table-success2');
var ctPage1= document.getElementById('active1');
var thisPage2=1;

Page2(list,ctPage1,thisPage2);
// function changePage2(i){
//     thisPage2=i;
//     Page2(list,ctPage1,thisPage2);

// }

function Page(){
    var beginGet=limit*(thisPage-1);
    var endGet=limit*thisPage-1;
    for(let i=0;i<listLopDanhNghia.length;i++){
        if(i >= beginGet && i <= endGet){
            listLopDanhNghia[i].style.display='table-row';
        }else{
            listLopDanhNghia[i].style.display='none';
        }

    }
    listPage();
}
Page();
function listPage(){
    var totalPage=Math.ceil(totalRecodrs/limit);
    var ctPage= document.getElementById('active');
    ctPage.innerHTML='';

    if(thisPage != 1){
        let prev= document.createElement('button');
        prev.innerHTML='<i class="fa-solid fa-angle-left"></i>';
        prev.classList.add('activeElement');
        prev.setAttribute('onclick',"changePage("+(thisPage-1)+")");
        ctPage.appendChild(prev);
    }
    for(let i=1;i <= totalPage;i++){
        let newPage=document.createElement('button');
        newPage.classList.add('activeElement');

        newPage.innerHTML=i;
        if(i == thisPage){
            newPage.classList.add('nenxanh');
        }
        newPage.setAttribute('onclick',"changePage("+i+")");
        ctPage.appendChild(newPage);
    }
    if(thisPage!= totalPage){
        let next= document.createElement('button');
        next.innerHTML='<i class="fa-solid fa-angle-right"></i>';
        next.classList.add('activeElement');
        next.setAttribute('onclick',"changePage("+(thisPage+1)+")");
        ctPage.appendChild(next);
    }
}
function changePage(i){
    thisPage=i;
    Page();
}



function search(tens,id){
    const searchTerm = document.getElementById(id).value.toLowerCase();
    const filteredCourses = tens.filter(ten => {
        const Name = ten.innerHTML.toLowerCase();
        return Name.includes(searchTerm);
    });
    displayResults(filteredCourses);
}

function seach(tens, id,list) {
    const searchTerm = document.getElementById(id).value.toLowerCase();
    var listVitri=[];
    const filteredCourses = Array.from(tens).filter(ten => {
        const name = ten.innerHTML.toLowerCase();
        return name.includes(searchTerm);
    });

    if (filteredCourses.length > 0) {
        filteredCourses.forEach(course => {
            // console.log(`Vị trí chỉ mục của phần tử cần tìm: ${tens.indexOf(course)}`);
            listVitri.push(tens.indexOf(course));
             displayResults(listVitri,list);

        });
    } else {
        // console.log("Không tìm thấy phần tử phù hợp.");\
        displayResults(listVitri,list);

    }
}



function displayResults(results,list) {
    if (results.length === 0) {
        for(let i=0;i<list.length;i++){
            list[i].style.display='none';
    }

    }

    for(let i=0;i<list.length;i++){
            list[i].style.display='none';
    }

    results.forEach(result => {
        // ctPage2.appendChild(result);
        list[result].style.display='table-row';

    });


    // Page();

}
var tenmonhocs= document.getElementsByClassName('tenmonhoc');
const monhocArray = Array.from(tenmonhocs);
var id='seach';
var buttonfind=document.getElementById('container-lop-h1__left-find');
var inputSearch=document.getElementById('seach');

buttonfind.onclick = function() {
    seach(monhocArray, id,listLopDanhNghia);

};

inputSearch.onchange = function() {
    Page();
};

var listLophp= document.querySelectorAll('.table-success2');
var tenlops= document.getElementsByClassName('tenlop');
const tenlopsArray = Array.from(tenlops);
var id1='seach1';
var buttonfind1=document.getElementById('container-lop-h1__left-find1');
var inputSearch1=document.getElementById('seach1');
buttonfind1.onclick = function() {
    seach(tenlopsArray, id1,listLophp);

};

inputSearch1.onchange = function() {
    Page2(list,ctPage1,thisPage2);
};

var list1= document.querySelectorAll('.table-success3');
var ctPage2= document.getElementById('active2');
var thisPage3=1;

var list2= document.querySelectorAll('.table-success4');
var ctPage3= document.getElementById('active3');
var thisPage4=1;

var list3= document.querySelectorAll('.table-success5');
var ctPage4= document.getElementById('active4');
var thisPage5=1;

var list4= document.querySelectorAll('.table-success6');
var ctPage5= document.getElementById('active5');
var thisPage6=1;

var list5= document.querySelectorAll('.table-success7');
var ctPage6= document.getElementById('active6');
var thisPage7=1;

var list6= document.querySelectorAll('.table-success8');
var ctPage7= document.getElementById('active7');
var thisPage8=1;

var list7= document.querySelectorAll('.table-success9');
var ctPage8= document.getElementById('active8');
var thisPage9=1;

var list9= document.querySelectorAll('.table-success11');
var ctPage10= document.getElementById('active10');
var thisPage11=1;

var list11= document.querySelectorAll('.table-success10');
var ctPage12= document.getElementById('active9');
var thisPage13=1;

var list10= document.querySelectorAll('.table-success12');
var ctPage11= document.getElementById('active11');
var thisPage12=1;

Page2(list2,ctPage3,thisPage4);
Page2(list1,ctPage2,thisPage3);
Page2(list3,ctPage4,thisPage5);
Page2(list4,ctPage5,thisPage6);
Page2(list5,ctPage6,thisPage7);
Page2(list6,ctPage7,thisPage8);
Page2(list7,ctPage8,thisPage9);
Page2(list9,ctPage10,thisPage11);
Page2(list10,ctPage11,thisPage12);
Page2(list11,ctPage12,thisPage13);
function changePage2(i){
    thisPage2=i;
    thisPage3=i;
    thisPage4=i;
    thisPage5=i;
    thisPage6=i;
    thisPage7=i;
    thisPage8=i;
    thisPage9=i;
    thisPage11=i;
    thisPage12=i;
    thisPage13=i;

    Page2(list1,ctPage2,thisPage3);
    Page2(list,ctPage1,thisPage2);
    Page2(list2,ctPage3,thisPage4);
    Page2(list3,ctPage4,thisPage5);
    Page2(list4,ctPage5,thisPage6);
    Page2(list5,ctPage6,thisPage7);
    Page2(list6,ctPage7,thisPage8);
    Page2(list7,ctPage8,thisPage9);
    Page2(list9,ctPage10,thisPage11);
    Page2(list10,ctPage11,thisPage12);
    Page2(list11,ctPage12,thisPage13);
}



