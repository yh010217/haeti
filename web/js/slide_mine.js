let pages = 1;//현재 인덱스 번호
let positionValue = 0;//images 위치값
document.querySelector('.slide-inner').style.width=
    document.querySelector('#my_slide').offsetWidth;
const IMAGE_WIDTH = document.querySelector('.slide-inner').offsetWidth;//한번 이동 시 IMAGE_WIDTH만큼 이동한다.
//DOM
const backBtn = document.querySelector(".slide-prev");
const nextBtn = document.querySelector(".slide-next");
const images = document.querySelector(".slide-items");

for(let i = 0 ; i <5 ; i++) {
    document.querySelectorAll('.slide-item')[i].style.width = IMAGE_WIDTH;
}
function slide_next() {
    if (pages< 5) {
        backBtn.removeAttribute('disabled')//뒤로 이동해 더이상 disabled가 아니여서 속성을 삭제한다.
        positionValue -= IMAGE_WIDTH;//IMAGE_WIDTH의 증감을 positionValue에 저장한다.
        images.style.transform = `translateX(${positionValue}px)`;
        //x축으로 positionValue만큼의 px을 이동한다.
        pages += 1; //다음 페이지로 이동해서 pages를 1증가 시킨다.
    }
    if (pages === 5) { //
        nextBtn.setAttribute('disabled', 'true')//마지막 장일 때 next버튼이 disabled된다.
    }
}

function slide_back() {
    if (pages > 1) {
        nextBtn.removeAttribute('disabled')
        positionValue += IMAGE_WIDTH;
        images.style.transform = `translateX(${positionValue}px)`;
        pages -= 1; //이전 페이지로 이동해서 pages를 1감소 시킨다.
    }
    if (pages === 1) {
        backBtn.setAttribute('disabled', 'true')//마지막 장일 때 back버튼이 disabled된다.
    }
}

function slide_init() {  //초기 화면 상태
    backBtn.setAttribute('disabled', 'true'); //속성이 disabled가 된다.
    backBtn.addEventListener("click", slide_back); //클릭시 다음으로 이동한다.
    nextBtn.addEventListener("click", slide_next);//클릭시 이전으로 이동한다.
}
slide_init();