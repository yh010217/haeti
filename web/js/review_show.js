let num='';

const init_data = function (prod_no){
    num=prod_no;
}

const init_text1=function(item){
    let ele_li = document.createElement('li');
    let txt1 = document.createTextNode(item.repcontent)
}
const init_del=function(item){
    let ele_li = document.createElement('li');
    const btn_delete=document.createElement('button');
    const del_txt=document.createTextNode('삭제');
    btn_delete.appendChild(del_txt);
    ele_li.appendChild(btn_delete);
    btn_delete.addEventListener('click', function(){
        location.href="repdelete.do?rep_no="+item.rep_no+"&prod_no="+item.prod_no;
    });
}
const init_modify=function(){
    let ele_li = document.createElement('li');
    const btn_modify=document.createElement('button');
    const modify_txt=document.createTextNode('수정');
    btn_modify.appendChild(modify_txt);
    ele_li.appendChild(btn_modify);
    return [ele_li,btn_modify];
}


window.onload=function(){
    fetch("repshow?prod_no="+num
        , {
            method: 'GET'
            , headers: {
                "Content-Type": "application/x-www-form-urlencoded"
                , "Accept": "text/json"
            }

        })

        .then(res => {

            return res.json()
        }
    )

        .then(data => {
            data.forEach(item => {
                let  ele_li = document.createElement('tr');
                let ele_li1= init_text1(item);
                let ele_li2=init_del(item);
                let [ele_li3,btn_modify]=init_modify();
                ele_li.appendChild(ele_li1);
                ele_li.appendChild(ele_li2);
                ele_li.appendChild(ele_li3);

                btn_modify.addEventListener('click', function(){
                    let txt1 = document.createElement('input');
                    txt1.setAttribute('name', 'repcontent');
                    txt1.setAttribute('value', item.repcontent);

                    ele_td1.textContent = "";
                    ele_td1.appendChild(txt1);
                    btn_modifyok = document.createElement('button');
                    let modifyok_txt = document.createTextNode('수정완료');
                    btn_modifyok.appendChild(modifyok_txt);

                    ele_td4.removeChild(btn_modify);
                    ele_td4.appendChild(btn_modifyok);


                    btn_modifyok.addEventListener('click', function() {
                        let repcontent_id=document.getElementById('subcontent').value;
                        location.href="repmodify.do?rep_no="+item.rep_no+"&prod_no="+item.prod_no+"&repcontent="+repcontent_id;
                    });

                });

                document.getElementById('review_list').appendChild(ele_tr);
            })
        })

        .catch(error => {
        console.log(error + "error!!!!")
    })
}










