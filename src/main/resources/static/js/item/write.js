// 이미지 미리보기
function loadFile(input) {
  const previewContainer = document.getElementById("previewContainer");

  previewContainer.innerHTML = "";

  // 선택한 파일들을 미리보기에 추가합니다.
  for (var i = 0; i < input.files.length; i++) {
    var file = input.files[i];
    var image = document.createElement("img");
    image.src = URL.createObjectURL(file);
    image.classList.add("preview");
    previewContainer.appendChild(image);
  }
}
// 서버로 데이터 전송
function writeFormSubmit() {
  const access_token = window.localStorage.getItem('access_token');
  const title = document.getElementById("title").value;
  const price = document.getElementById("price").value;
  const content = document.getElementById("content").value;
  const image = document.getElementById("itemImgFile").files;
  // 서버에 보낼 데이터 틀 form에 원하는 데이터 넣기
  const formData = new FormData();

  const data ={
    title :  $.trim($('#title').val()),
    price :  $.trim($('#price').val()),
    content : $.trim($('#content').val())
  }
  formData.append("dto", new Blob([JSON.stringify(data)] , {type: "application/json"}));


  for (let i = 0; i < image.length; i++) {
    const images = image[i];
    formData.append("files", images);
  }

  // (29~32행) 데이터 전송 전 확인용! 없어도 무관! 브라우저 f12 누르고 콘솔 창에서 확인 가능
  for (let key of formData.keys()) {
    console.log(key, ":", formData.get(key));
  }


  // 데이터 전송 ::  fetch란 JavaScript에서 서버로 네트워크 요청을 보내고 응답을 받을 수 있도록 해주는 매서드
  fetch("/api/item/write", {
    method: "POST",
    headers: {
        Authorization: "Bearer " + localStorage.getItem("access_token"),
    },
    body: formData,
  })
    .then((res) => console.log(res)) // 성공일 경우 => 응답 확인
        .then(response => {
            console.log(response);
            alert('상품이 등록되었습니다.');
        })
        .then((responseJson) => {
            window.location.replace('/');
        })
        .catch((error) => {
            console.log(error)
        })
}