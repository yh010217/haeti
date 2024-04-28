window.onload=function () {
    /*  닉네임 중복 체크 */
    document.getElementById("nick_check").onclick = function () {
        fetch("nick_name_check?nick_name=" + nick_name.value
            , {
                method: "GET"
                , headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                    , "Accept": "text/plain"
                }
            }).then(res => {
            return res.text()
        }).then(data => {
            if(data==="true"){
                document.getElementById("nick_check_result").innerText="중복된 닉네임입니다.";
                document.getElementById("nick_check_result").className="impossible";

            }else if(data==="null"){
                document.getElementById("nick_check_result").innerText="닉네임을 작성해 주세요.";
                document.getElementById("nick_check_result").className="impossible";

            }else if(data==="false"){
                document.getElementById("nick_check_result").innerText="사용 가능한 닉네임입니다.";
                document.getElementById("nick_check_result").className="possible";
            }

        }).catch(error => {
            console.log(error + "error!!!!");
        })
    }


    /*  이메일 중복 체크  */
    let email=document.getElementById("email");
    document.getElementById("email_check").onclick=function (){
        fetch("email_check?email="+email.value
            , {
                method: "GET"
                , headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                    , "Accept": "text/plain"
                }
            }).then(res => {
            return res.text()
        }).then(data => {
            if(data==="true"){
                document.getElementById("email_check_result").innerText="중복된 이메일입니다.";
                document.getElementById("email_check_result").className="impossible";

            }else if(data==="null"){
                document.getElementById("email_check_result").innerText="이메일을 작성해 주세요";
                document.getElementById("email_check_result").className="impossible";

            }else if(data==="false"){
                document.getElementById("email_check_result").innerText="사용 가능한 이메일입니다.";
                document.getElementById("email_check_result").className="possible";

            }
        }).catch(error => {
            console.log(error + "error!!!!");
        })
    }
}

