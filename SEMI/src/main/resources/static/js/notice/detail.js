
// 댓글 작성하기
// function writeReply(refNo){
function writeReply(){

    // 공지사항 번호 가져오기
    const refNo = document.querySelector("#reply-area").getAttribute("noticeNo");

    // 댓글 내용 가져오기
    const replyContentTag = document.querySelector("#reply-write-area input[name=content]");
    
    // 서버에 댓글 내용 전달하기
    $.ajax({
        url: "/notice/reply/write",
        method: "post",
        data: {
            "content" : replyContentTag.value,
            refNo
        },
        success: function(result){
            console.log("통신 성공~~~");
            if(result == 1){
                alert("댓글 작성 성공!!")
                replyContentTag.value="";
                loadReply();
            }
            else{
                alert("댓글 작성 실패...")
            }
            
        },
        error: function(){
            console.log("통신 실패...");
        }

    })

}

// 서버에서 댓글 불러오기
function loadReply(){

    // 공지사항 번호 가져오기
    const noticeNo = document.querySelector("#reply-area").getAttribute("noticeNo");

    // 서버와 비동기 통신
    $.ajax({
        url: "/notice/reply/list",
        method: "GET",
        data : {
            noticeNo
        },
        success: function(replyList){
            console.log("통신 성공~~~");
            console.log("댓글 리스트 : ", replyList);
            paintReplyList(replyList);
        },
        error: function(){
            console.log("통신 실패...");
        }

    })

}

// 화면에 댓글 출력
function paintReplyList(voList){

    // 댓글 내용 추가할 댓글 영역 
    const replyListArea = document.querySelector("#reply-list-area");

    // 리스트 영역 초기화
    replyListArea.innerHTML = "";

    // 영역에 불러온 댓글 추가
    for(const vo of voList){

        // 댓글 내용 추가
        const contentTag = document.createElement("div");
        contentTag.innerText = vo.content;
        replyListArea.appendChild(contentTag);
        // 댓글 작성자 추가
        const writerTag = document.createElement("div");
        writerTag.innerText = vo.writerNick;
        replyListArea.appendChild(writerTag);
        // 댓글 작성일 추가
        const dateTag = document.createElement("div");
        dateTag.innerText = vo.createDate;
        replyListArea.appendChild(dateTag);

    }

}

loadReply();