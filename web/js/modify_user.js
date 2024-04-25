let nick_name=document.getElementById("nick_name");
window.onload=function () {
    /*  닉네임 중복 체크 */
    document.getElementById("nick_check").onclick=function (){
        const check_result_child=document.getElementById("nick_check_result").firstChild;
        console.log(check_result_child)
        if(check_result_child!=null){
            check_result_child.remove();
        }

        fetch("nick_name_check?nick_name="+nick_name.value
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
                const result=item.result;

                if(result){
                    let ele=document.createElement("p");
                    let ele_txt=document.createTextNode("사용 불가능한 닉네임");
                    // document.getElementById("nick_name")
                    //     .setAttribute("readonly","readonly");

                    ele.appendChild(ele_txt);
                    ele.className="impossible";

                    document.getElementById("check_result").appendChild(ele);

                }else{
                    let ele=document.createElement("p");
                    let ele_txt=document.createTextNode("사용 가능한 닉네임");

                    ele.appendChild(ele_txt);
                    ele.className="possible";

                    document.getElementById("nick_check_result").appendChild(ele);

                }
            })
        }).catch(error => {
            console.log(error + "error!!!!");
        })
    }


    /*  이메일 중복 체크  */
    let email=document.getElementById("email");
    document.getElementById("email_check").onclick=function (){
        const check_result_child=document.getElementById("email_check_result").firstChild;
        console.log(check_result_child)
        if(check_result_child!=null){
            check_result_child.remove();
        }

        fetch("email_check?email="+email.value
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
                const result=item.result;

                if(result){
                    let ele=document.createElement("p");
                    let ele_txt=document.createTextNode("사용 불가능한 이메일");
                    // document.getElementById("nick_name")
                    //     .setAttribute("readonly","readonly");

                    ele.appendChild(ele_txt);
                    ele.className="impossible";

                    document.getElementById("email_check_result").appendChild(ele);

                }else{
                    let ele=document.createElement("p");
                    let ele_txt=document.createTextNode("사용 가능한 이메일");

                    ele.appendChild(ele_txt);
                    ele.className="possible";

                    document.getElementById("email_check_result").appendChild(ele);

                }
            })
        }).catch(error => {
            console.log(error + "error!!!!");
        })
    }
}

