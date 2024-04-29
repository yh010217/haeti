function checkAll(){
    if (!checkUserId(form.user_id.value)){
        return false;
    }
    return true;
}
//공백확인 함수
function checkExistData(value,dataName){
    if (value == ""){
        alert(dataName + "입력해주세요!");
        return false;
    }
    return true;
}
function checkUserId(user_id){
    //id가 입력되었는지 확인하기
    if (!checkExistData(user_id,"아이디를"))
        return false;
    var user_idRegExp=/^[a-zA-z0-9]{4,12}$/; //아이디 유효성 검사
    if (!user_idRegExp.test(user_id)){
        alert("아이디는 영문 대소문자와 숫자 4~12자리로 입력해야합니다!");
        form.user_id.value="";
        form.user_id.focus();
        return false;
    }
    return true;
}
