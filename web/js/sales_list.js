fetch("sales_list_result"
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
        let ele_li1=document.createElement('li');
        let ele_li2=document.createElement('li');
        let ele_li3=document.createElement('li');

        /*이미지*/
        let prod_no=item.prod_no;
        let img=item.img_path;
        let ele_img=document.createElement('img');
        ele_img.src="upload/"+prod_no+"/"+img;
        ele_img.alt="판매목록 이미지";

        /*클래스 이름*/
        ele_tr.className='tr';
        ele_td1.className='td';
        ele_td2.className='td';
        ele_td3.className='td';
        ele_td3.id='td3';
        ele_li1.className='td2_li';
        ele_li2.className='td2_li';
        ele_li3.className='td2_li';

        /*제목,구매일,가격*/
        let txt1=document.createTextNode(item.title);
        let txt2=document.createTextNode(item.write_date);
        let txt3=document.createTextNode(item.cost);

        /*채팅: prod_no, buyer_id*/
        let buyer_id=item.buyer_id;
        let chat_url=document.createElement('a');
        // chat_url.href="chatting.do?prod_no="+prod_no+"&buyer="+buyer_id+"&iam=buyer";
        //chat_url.href="chatting.do?prod_no="+prod_no+"&buyer="+buyer_id+"&iam=seller";

        chat_url.href="my_prod_chatting.do?prod_no="+prod_no;

        let chat_icon=document.createElement('img');
        chat_icon.src="img/chatting_icon.png";
        chat_icon.alt="채팅";
        chat_icon.id="chat_icon";
        chat_url.appendChild(chat_icon);

        /*ele_td2 ul1 li*/
        ele_li1.appendChild(txt1);
        ele_li2.appendChild(txt2);
        ele_li3.appendChild(txt3);
        ele_ul1.appendChild(ele_li1);
        ele_ul1.appendChild(ele_li2);
        ele_ul1.appendChild(ele_li3);

        /*td,tr*/
        ele_td1.appendChild(ele_img);
        ele_td2.appendChild(ele_ul1);
        ele_td3.appendChild(chat_url);
        ele_tr.appendChild(ele_td1);
        ele_tr.appendChild(ele_td2);
        ele_tr.appendChild(ele_td3);

        document.getElementById("result").appendChild(ele_tr);
    })
}).catch(error => {
    console.log(error + "error!!!!")
})



function selectChange(value){
    const status=document.getElementById("status").value;
    let parent=document.getElementById('result');
    while(parent.firstChild){
        parent.removeChild(parent.firstChild)
    }


    fetch("sales_list_result?status="+status
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
            let ele_li1=document.createElement('li');
            let ele_li2=document.createElement('li');
            let ele_li3=document.createElement('li');

            /*이미지*/
            let prod_no=item.prod_no;
            let img=item.img_path;
            let ele_img=document.createElement('img');
            ele_img.src="upload/"+prod_no+"/"+img;
            ele_img.alt="판매목록 이미지";

            /*클래스 이름*/
            ele_tr.className='tr';
            ele_td1.className='td';
            ele_td2.className='td';
            ele_td3.className='td';
            ele_td3.id='td3';
            ele_li1.className='td2_li';
            ele_li2.className='td2_li';
            ele_li3.className='td2_li';

            /*제목,구매일,가격*/
            let txt1=document.createTextNode(item.title);
            let txt2=document.createTextNode(item.write_date);
            let txt3=document.createTextNode(item.cost);

            /*채팅: prod_no, buyer_id*/
            let buyer_id=item.buyer_id;
            let chat_url=document.createElement('a');
            // chat_url.href="chatting.do?prod_no="+prod_no+"&buyer="+buyer_id+"&iam=buyer";
            //chat_url.href="chatting.do?prod_no="+prod_no+"&buyer="+buyer_id+"&iam=seller";

            chat_url.href="my_prod_chatting.do?prod_no="+prod_no;

            let chat_icon=document.createElement('img');
            chat_icon.src="img/chatting_icon.png";
            chat_icon.alt="채팅";
            chat_icon.id="chat_icon";
            chat_url.appendChild(chat_icon);

            /*ele_td2 ul1 li*/
            ele_li1.appendChild(txt1);
            ele_li2.appendChild(txt2);
            ele_li3.appendChild(txt3);
            ele_ul1.appendChild(ele_li1);
            ele_ul1.appendChild(ele_li2);
            ele_ul1.appendChild(ele_li3);

            /*td,tr*/
            ele_td1.appendChild(ele_img);
            ele_td2.appendChild(ele_ul1);
            ele_td3.appendChild(chat_url);
            ele_tr.appendChild(ele_td1);
            ele_tr.appendChild(ele_td2);
            ele_tr.appendChild(ele_td3);

            document.getElementById("result").appendChild(ele_tr);
        })
    }).catch(error => {
        console.log(error + "error!!!!")
    })
}
