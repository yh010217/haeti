let num = '';

const init_data = function (prod_no) {
    num = prod_no;
}

const init_text1 = function (item) {
    let ele_li = document.createElement('li');
    let txt1 = document.createTextNode(item.repcontent);
    ele_li.appendChild(txt1);
    return ele_li;
}
const init_del = function (item) {
    let ele_li = document.createElement('li');
    const btn_delete = document.createElement('button');
    const del_txt = document.createTextNode('삭제');
    btn_delete.appendChild(del_txt);
    ele_li.appendChild(btn_delete);
    btn_delete.addEventListener('click', function () {
        location.href = "repdelete.do?rep_no=" + item.rep_no + "&prod_no=" + item.prod_no;
    });
    return ele_li;
}


let RepShow = 0;
document.getElementById('rep_show_button').onclick = function () {
    if (RepShow == 0) {
        fetch("repshow?prod_no=" + num
            , {
                method   : 'GET'
                , headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                    , "Accept"    : "text/json"
                }

            }).then(res => {

            return res.json()
        }).then(data => {
            data.forEach(item => {
                let ele_li = document.createElement('li');
                let ele_li1 = init_text1(item);
                let ele_li2 = init_del(item);
                ele_li.appendChild(ele_li1);
                ele_li.appendChild(ele_li2);

                document.getElementById('review_list').appendChild(ele_li);
            })
        }).catch(error => {
            console.log(error + "error!!!!")
        });

        RepShow = 1;
    } else {
        document.getElementById('review_list').innerHTML = '';
        RepShow = 0;
    }
}

