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
        let ele_tr=document.createElement('div');
        let ele_td1= document.createElement('div');
        let ele_td2=document.createElement('div');
        let ele_td3=document.createElement('div');
        let ele_td4=document.createElement('div');
        let ele_td5=document.createElement('div');

        //이미지
        let prod_no=item.prod_no;
        let uploadPath=item.uploadPath;
        let img=item.img;
        let ele_img=document.createElement('img');
        ele_img.src="upload/"+prod_no+"/"+img;
        ele_img.alt="구매목록 이미지";

        //채팅: 구매자 아이디
        let buyer_id=item.buyer_id;
        let chat_url=document.createElement('a');
        chat_url.href="chatting.do?prod_no="+prod_no+"&buyer="+buyer_id+"&iam=seller";
        // chat_url.href="index.do";
        chat_url.innerText="채팅";

        ele_tr.className='tr';
        ele_td1.className='td';
        ele_td2.className='td';
        ele_td3.className='td';
        ele_td4.className='td';
        ele_td5.className='td';

        let txt1=document.createTextNode(item.title);
        let txt2=document.createTextNode(item.sell_date);
        let txt3=document.createTextNode(item.cost);

        ele_td1.appendChild(ele_img);
        ele_td2.appendChild(txt1);
        ele_td3.appendChild(txt2);
        ele_td4.appendChild(txt3);
        ele_td5.appendChild(chat_url);
        ele_tr.appendChild(ele_td1)
        ele_tr.appendChild(ele_td2)
        ele_tr.appendChild(ele_td3)
        ele_tr.appendChild(ele_td4)
        ele_tr.appendChild(ele_td5)

        document.getElementById("result").appendChild(ele_tr)
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
            let ele_tr=document.createElement('div');
            let ele_td1= document.createElement('div');
            let ele_td2=document.createElement('div');
            let ele_td3=document.createElement('div');
            let ele_td4=document.createElement('div');
            let ele_td5=document.createElement('div');

            //이미지
            let prod_no=item.prod_no;
            let uploadPath=item.uploadPath;
            let img=item.img;
            let ele_img=document.createElement('img');
            ele_img.src="upload/"+prod_no+"/"+img;
            ele_img.alt="구매목록 이미지";

            //채팅: 구매자 아이디
            let buyer_id=item.buyer_id;
            let chat_url=document.createElement('a');
            chat_url.href="chatting.do?prod_no="+prod_no+"&buyer="+buyer_id+"&iam=seller";
            // chat_url.href="index.do";
            chat_url.innerText="채팅";

            ele_tr.className='tr';
            ele_td1.className='td';
            ele_td2.className='td';
            ele_td3.className='td';
            ele_td4.className='td';
            ele_td5.className='td';

            let txt1=document.createTextNode(item.title);
            let txt2=document.createTextNode(item.cost);
            let txt3=document.createTextNode(item.sell_date);

            ele_td1.appendChild(ele_img);
            ele_td2.appendChild(txt1);
            ele_td3.appendChild(txt2);
            ele_td4.appendChild(txt3);
            ele_td5.appendChild(chat_url);
            ele_tr.appendChild(ele_td1)
            ele_tr.appendChild(ele_td2)
            ele_tr.appendChild(ele_td3)
            ele_tr.appendChild(ele_td4)
            ele_tr.appendChild(ele_td5)

            document.getElementById("result").appendChild(ele_tr)
        })
    }).catch(error => {
        console.log(error + "error!!!!")
    })
}
