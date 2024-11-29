function delAttach(attchNo , fileName , imgTag){

    const result = confirm("해당 사진을 삭제하시겠습니까? (복구불가능)");
    if(result == false){return;}

    $.ajax({
        url : "/board/attachment/del" ,
        method : "post" ,
        data : {
            ano : attchNo ,
            fileName : fileName ,
        } ,
        success : function(data){
            if(data == 1){
                alert("첨부파일 삭제 성공 !");
                imgTag.remove();
            }else{
                alert("첨부파일 삭제 실패 ...");
            }
        } ,
        fail : function(){
            alert("통신 실패 ...");
        } ,
    });
}