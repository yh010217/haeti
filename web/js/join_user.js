
window.onload=function () {
    /*  회원가입 아이디 중복 체크 */

    document.getElementById("user_id_check").onclick=function (){
        //console.log("ddddd");
        // const check_result_child=document.getElementById("user_id_check_result");
        // console.log(check_result_child)
        //
     console.log("test");
        fetch("user_id_check?user_id="+user_id.value
            , {
                method: "GET"
                , headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                    , "Accept": "text/plain"
                }
            }).then(res => {
            return res.text()
        }).then(data => {
          document.getElementById("user_id_check_result").innerText=data;

        }).catch(error => {
            console.log(error + "error!!!!");
        })
    }


    /*  회원가입 닉네임 중복 체크  */

    document.getElementById("nick_name_check2").onclick=function (){
        //console.log("ddddd");
        //const check_result_child=document.getElementById("nick_check_result");
        fetch("nick_name_check2?nick_name="+nick_name.value
            , {
                method: "GET"
                , headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                    , "Accept": "text/plain"
                }
            }).then(res => {
            return res.text()
        }).then(data => {
            document.getElementById("nick_name_check_result2").innerText=data;

        }).catch(error => {
            console.log(error + "error!!!!");
        })
    }
    //  회원가입 이메일 체크
    document.getElementById("email_check2").onclick=function (){

        fetch("email_check2?email="+email.value
            , {
                method: "GET"
                , headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                    , "Accept": "text/plain"
                }
            }).then(res => {
            return res.text()
        }).then(data => {
            document.getElementById("email_check_result2").innerText=data;

        }).catch(error => {
            console.log(error + "error!!!!");
        })
    }


}

