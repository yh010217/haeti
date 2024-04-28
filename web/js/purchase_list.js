fetch("purchase_list_result"
    , {
        method: "GET"
        , headers: {
            "Content-Type": "application/x-www-form-urlencoded"
            , "Accept": "text/json"
        }
    }).then(res => {
    return res.json()
}).then(data => {
    data.forEach(item => {
        /*요소 생성*/
        let ele_tr=document.createElement('div');
        let ele_td1= document.createElement('div');
        let ele_td2=document.createElement('div');
        let ele_td3=document.createElement('div');
        let ele_ul1=document.createElement('ul');
        let ele_ul2=document.createElement('ul');
        let ele_li1=document.createElement('li');
        let ele_li2=document.createElement('li');
        let ele_li3=document.createElement('li');
        let ele_li4=document.createElement('li');
        let ele_li5=document.createElement('li');

        //클래스 이름
        ele_tr.className='tr';
        ele_td1.className='td';
        ele_td2.className='td';
        ele_td3.className='td';
        ele_ul2.className='td3_ul';
        ele_li1.className='td2_li';
        ele_li2.className='td2_li';
        ele_li3.className='td2_li';
        ele_li4.className='td3_li';
        ele_li5.className='td3_li';

        /*이미지*/
        let prod_no=item.prod_no;
        let img=item.img;
        let ele_img=document.createElement('img');
        ele_img.src="upload/"+prod_no+"/"+img;
        ele_img.alt="구매목록 이미지";

        /*리뷰*/
        let review_url=document.createElement('a');
        review_url.href="#";
        review_url.innerText="리뷰 작성하기";
        review_url.id='review';

        /*채팅: 구매자 아이디*/
        let buyer_id=item.buyer_id;
        let chat_url=document.createElement('a');
        chat_url.href="chatting.do?prod_no="+prod_no+"&buyer="+buyer_id+"&iam=buyer";
        // chat_url.innerText="채팅";
        let chat_icon=document.createElement('img');
        chat_icon.src="img/chatting_icon.png";
        chat_icon.alt="채팅";
        chat_icon.id="chat_icon";
        chat_url.appendChild(chat_icon);

        /*제목,구매일,가격*/
        let txt1=document.createTextNode(item.title);
        let txt2=document.createTextNode(item.sell_date);
        let txt3=document.createTextNode(item.cost);

        /*ele_td2 ul1 li*/
        ele_li1.appendChild(txt1);
        ele_li2.appendChild(txt2);
        ele_li3.appendChild(txt3);
        ele_ul1.appendChild(ele_li1);
        ele_ul1.appendChild(ele_li2);
        ele_ul1.appendChild(ele_li3);

        /*ele_td3 ul2 li*/
        ele_li4.appendChild(review_url);
        ele_li5.appendChild(chat_url);
        ele_ul2.appendChild(ele_li4);
        ele_ul2.appendChild(ele_li5);

        /*td,tr*/
        ele_td1.appendChild(ele_img);
        ele_td2.appendChild(ele_ul1);
        ele_td3.appendChild(ele_ul2);
        ele_tr.appendChild(ele_td1);
        ele_tr.appendChild(ele_td2);
        ele_tr.appendChild(ele_td3);

        let detail_url=document.createElement('a');
        detail_url.href="prod_detail.do?prod_no="+prod_no;

        document.getElementById("result").appendChild(ele_tr);
    })
}).catch(error => {
    console.log(error + "error!!!!")
})


function selectChange(value){
    const period=document.getElementById("period").value;
    let parent=document.getElementById('result');
    while(parent.firstChild){
        parent.removeChild(parent.firstChild)
    }

    fetch("purchase_list_result?period="+period
        , {
            method: "GET"
            , headers: {
                "Content-Type": "application/x-www-form-urlencoded"
                , "Accept": "text/json"
            }
        }).then(res => {
        return res.json()
    }).then(data => {
        data.forEach(item => {
            /*요소 생성*/
            let ele_tr=document.createElement('div');
            let ele_td1= document.createElement('div');
            let ele_td2=document.createElement('div');
            let ele_td3=document.createElement('div');
            let ele_ul1=document.createElement('ul');
            let ele_ul2=document.createElement('ul');
            let ele_li1=document.createElement('li');
            let ele_li2=document.createElement('li');
            let ele_li3=document.createElement('li');
            let ele_li4=document.createElement('li');
            let ele_li5=document.createElement('li');

            //클래스 이름
            ele_tr.className='tr';
            ele_td1.className='td';
            ele_td2.className='td';
            ele_td3.className='td';
            ele_ul2.className='td3_ul';
            ele_li1.className='td2_li';
            ele_li2.className='td2_li';
            ele_li3.className='td2_li';
            ele_li4.className='td3_li';
            ele_li5.className='td3_li';

            /*이미지*/
            let prod_no=item.prod_no;
            let img=item.img;
            let ele_img=document.createElement('img');
            ele_img.src="upload/"+prod_no+"/"+img;
            ele_img.alt="구매목록 이미지";

            /*리뷰*/
            let review_url=document.createElement('a');
            review_url.href="#";
            review_url.innerText="리뷰 작성하기";
            review_url.id='review';

            /*채팅: 구매자 아이디*/
            let buyer_id=item.buyer_id;
            let chat_url=document.createElement('a');
            chat_url.href="chatting.do?prod_no="+prod_no+"&buyer="+buyer_id+"&iam=buyer";
            // chat_url.innerText="채팅";
            let chat_icon=document.createElement('img');
            chat_icon.src="img/chatting_icon.png";
            chat_icon.alt="채팅";
            chat_icon.id="chat_icon";
            chat_url.appendChild(chat_icon);

            /*제목,구매일,가격*/
            let txt1=document.createTextNode(item.title);
            let txt2=document.createTextNode(item.sell_date);
            let txt3=document.createTextNode(item.cost);

            /*ele_td2 ul1 li*/
            ele_li1.appendChild(txt1);
            ele_li2.appendChild(txt2);
            ele_li3.appendChild(txt3);
            ele_ul1.appendChild(ele_li1);
            ele_ul1.appendChild(ele_li2);
            ele_ul1.appendChild(ele_li3);

            /*ele_td3 ul2 li*/
            ele_li4.appendChild(review_url);
            ele_li5.appendChild(chat_url);
            ele_ul2.appendChild(ele_li4);
            ele_ul2.appendChild(ele_li5);

            /*td,tr*/
            ele_td1.appendChild(ele_img);
            ele_td2.appendChild(ele_ul1);
            ele_td3.appendChild(ele_ul2);
            ele_tr.appendChild(ele_td1);
            ele_tr.appendChild(ele_td2);
            ele_tr.appendChild(ele_td3);

            document.getElementById("result").appendChild(ele_tr);
        })
    }).catch(error => {
        console.log(error + "error!!!!")
    })
}
