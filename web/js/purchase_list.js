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
        ele_tr.className='tr';
        ele_td1.className='td';
        ele_td2.className='td';

        let txt1=document.createTextNode(item.prod_no);
        let txt2=document.createTextNode(item.title);

        ele_td1.appendChild(txt1);
        ele_td2.appendChild(txt2);
        ele_tr.appendChild(ele_td1)
        ele_tr.appendChild(ele_td2)

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
            ele_tr.className='tr';
            ele_td1.className='td';
            ele_td2.className='td';

            let txt1=document.createTextNode(item.prod_no);
            let txt2=document.createTextNode(item.title);

            ele_td1.appendChild(txt1);
            ele_td2.appendChild(txt2);
            ele_tr.appendChild(ele_td1)
            ele_tr.appendChild(ele_td2)

            document.getElementById("result").appendChild(ele_tr)
        })
    }).catch(error => {
        console.log(error + "error!!!!")
    })
}
