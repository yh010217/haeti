let num = '';

const init_data = function (prod_no) {
    num = prod_no;
}
const init_text1 = function (item) {
    let ele_li = document.createElement('div');
    ele_li.className='rep_content col-9';
    let txt1 = document.createTextNode(item.repcontent);
    ele_li.appendChild(txt1);
    return ele_li;
}
const init_user = function(item){
    let ele_li = document.createElement('div');
    ele_li.className='rep_user col-2';
    let txt1 = document.createTextNode(item.user_id);
    ele_li.appendChild(txt1);
    return ele_li;
}
const init_del = function (item) {
    const btn_delete = document.createElement('button');
    btn_delete.className='rep_delete col-1';
    const del_txt = document.createTextNode('삭제');
    btn_delete.appendChild(del_txt);
    btn_delete.addEventListener('click', function () {
        location.href = "repdelete.do?rep_no=" + item.rep_no + "&prod_no=" + item.prod_no;
    });
    return btn_delete;
}


let RepShow = 0;
document.getElementById('rep_show_button').onclick = function () {



    if (RepShow == 0) {

        let review_form = document.createElement('form');
        review_form.className='review_container';
        review_form.method='post';
        review_form.action='review_write_result.do?prod_no='+num;

        let review_write = document.createElement('textarea');
        review_write.name='repcontent';
        review_write.cols=2;
        review_write.className='review_write';

        let review_write_submit = document.createElement('button');
        review_write_submit.type='submit';
        let review_submit_image=document.createElement('img');
        review_submit_image.src='img/review_submit.png';
        review_submit_image.className='review_submit_image';
        //let review_submit_text = document.createTextNode('댓글 작성');
        review_write_submit.appendChild(review_submit_image);

        review_form.appendChild(review_write);
        review_form.appendChild(review_write_submit);

        document.getElementById('review_create_box').appendChild(review_form);

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


                let ele_div = document.createElement('div');
                ele_div.className='row';

                let ele_div1 = init_text1(item);

                let ele_user = init_user(item);
                let ele_del = init_del(item);
                ele_div.appendChild(ele_user);
                ele_div.appendChild(ele_div1);
                ele_div.appendChild(ele_del);

                document.getElementById('review_list').appendChild(ele_div);
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

