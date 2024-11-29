const tbodyTag = document.querySelector("main .table-area tbody");

tbodyTag.addEventListener("click" , function(evt){

    // td 가 아닌 tr 혹은 tbody 영역이 클릭되면 상세보기 동작을 처리하지 않는다.
    if(evt.target.tagName != "TD" || evt.target.className == "checkbox-td"){
        return;
    }

    // 클릭한 라인이 몇 번 글의 라인인지 알기 위해 해당 라인의 글 번호 수집
    const no = evt.target.parentNode.children[0].innerText;
    
    // 상세보기로 이동
    location.href = `/notice/detail?no=${no}`;
     
});

function handleCheckBox(evt){

    // 모든 체크박스 가져오기
    const checkBoxArr =  document.querySelectorAll(".checkbox-td > input");
    
    // 모든 체크박스 체크 & 체크 해제
    for(let i = 0; i < checkBoxArr.length; i++){
        checkBoxArr[i].checked = evt.checked;
    }

}

function deleteNotice(){
    
    // 추출한 번호 담을 배열 선언
    const noticeNoArr = [];

    // 체크된 박스들 태그 수집
    const checkedBoxArr =  document.querySelectorAll(".checkbox-td > input[type=checkbox]:checked");

    // 박스에서 번호 값 추출해서 배열에 저장
    for(let i = 0; i < checkedBoxArr.length; i++){
        noticeNoArr[i] = checkedBoxArr[i].parentNode.parentNode.children[0].innerText;
    }

    // 서버에 삭제 요청 보내기
    $.ajax({
        url : "/notice/delete",
        method : "delete",
        data : {
            noticeNoArr : JSON.stringify(noticeNoArr)
        },
        success : function(x){
            console.log("통신 성공!!");
            if(x == "success"){
                alert("삭제 성공!")
            }
            else{
                alert("삭제 실패..")
            }
            location.reload();  
        },
        error : function(){
            console.log("통신 실패...");            
        }

    })

}