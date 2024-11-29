/*
// 카테고리 항목 동적으로 가져오기 연습 코드
function setCateOptions(){

    $.ajax({
        url : "http://127.0.0.1:8888/board/category" ,
        method : "GET" ,
        success : function(categoryList){

            const selectTag = document.querySelector("#cate-select");
            for(const vo of categoryList){
                const optionTag = document.createElement('option');
                optionTag.setAttribute("value",vo.no);
                optionTag.innerHTML=vo.name;
                selectTag.appendChild(optionTag);
            }

        } ,
        fail : function(){}

    });

}

window.onload = function(){
    setCateOptions();
}*/

const fileTag = document.querySelector("input[name=f]");
fileTag.addEventListener("change",preview);

function preview(evt){

    const previeArea = document.querySelector(".preview-area");
    previeArea.innerHTML = "";

    for(let i=0; i<evt.target.files.length; i++){

        const f = evt.target.files[i];
        
        const fr = new FileReader();
        fr.onload= function(evt){
        
            const dataUrl = evt.target.result;
    
            const imgTag = document.createElement("img");
            imgTag.setAttribute("src",dataUrl);
            imgTag.setAttribute("width","100");
            imgTag.setAttribute("height","100");
            previeArea.appendChild(imgTag);
        }
        fr.readAsDataURL(f);
        
    }

}

