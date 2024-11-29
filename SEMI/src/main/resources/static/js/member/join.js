function checkDupId(){

    // submit 버튼 가져오기
    const submitBtn = document.querySelector("form input[type=submit]");

    // 유저가 입력한 아이디 준비
    const id = document.querySelector("input[name=id]").value;

    // 서버한테 아이디 넘기기
    $.ajax({
        url : "/member/dup-id" , 
        method : "POST" , 
        // 주로 k-v 형식으로 데이터를 넘긴다
        data : {
            // id : id 와 같이 k 와 v 이름이 같으면 하나로 써도 인식 한다
            id
        } ,
        // 함수에 매개변수 추가하면 통신 통해서 가져온 데이터가 담겨진다
        success : function(x){
            console.log("통신 성공~~~");

            // 받아온 Json 문자열을 객체 타입 데이터로 변환
            const o = JSON.parse(x);
            
            // status 값에 따라 결과 출력 및 submit 가능여부 결정
            if(o.status === "pass"){
                alert(`${o.data}는 사용 가능한 아이디입니다.`);
                submitBtn.removeAttribute("disabled");
            }
            else {
                alert(`${o.data}는 이미 사용중인 아이디입니다.`);
                submitBtn.setAttribute("disabled",true);
            }
        } ,
        fail : function(){
            console.log("통신 실패....");
        }
    });

    // 결과 받아서, form 태그 submit 가능여부 결정
    
}

function disableSubmitBtn(){

    // submit 버튼 가져오기
    const submitBtn = document.querySelector("form input[type=submit]");

    // submit 버튼 비활성화
    submitBtn.setAttribute("disabled",true);
}