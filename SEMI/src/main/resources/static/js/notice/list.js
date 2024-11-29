const tbodyTag = document.querySelector("main .table-area tbody");

tbodyTag.addEventListener("click" , function(evt){

    // td 가 아닌 tr 혹은 tbody 영역이 클릭되면 상세보기 동작을 처리하지 않는다.
    if(evt.target.tagName != "TD"){
        return;
    }

    // 클릭한 라인이 몇 번 글의 라인인지 알기 위해 해당 라인의 글 번호 수집
    const no = evt.target.parentNode.children[0].innerText;
    
    // 상세보기로 이동
    location.href = `/notice/detail?no=${no}`;
     
});