const fields = ["email", "nickname", "password"];

function joinSubmit(){
    var email = $.trim($("#email").val());
    var nickname = $.trim($("#nickname").val());
    var password = $.trim($("#password").val());

    fetch("/api/member/join", {
        method: "POST",
        headers: {"Content-Type":"application/json"},
        body: JSON.stringify({
            "email":email,
            "nickname":nickname,
            "password":password
        }),
    })
    .then(response => {
        if(response.ok) {
            return response.json();
        }
        throw new Error('Failed to register as a member');
    })
    .then(response => {
        console.log(response);
        alert('회원가입이 완료되었습니다.');
    })
    .then((responseJson) => {
        window.location.replace('/');
    })
    .catch((error) => {
        console.log(error)
    })
}