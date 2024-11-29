function setBoardList(searchType, searchValue){

    const tbodyTag = document.querySelector("main table>tbody");

    // 브라우저 URL 창에서 파라미터 값만(쿼리스트링) 가져오기
    //const pno = window.location.search;
    
    const url = new URL(location);
    let pno = url.searchParams.get("pno");
    if(pno == null){
        pno = 1;
    }

    $.ajax({

        // url : "/board/list/data?pno=pno" ,
        url : `/board/list/data?pno=${pno}` ,
        data : {
            searchType ,
            searchValue
        } , 
        success : function(m){
            console.log(m);
            
            const data = m.a;
            
            tbodyTag.innerHTML = "";

            for(const vo of data){
                const trTag = document.createElement("tr");

                const cateTag = document.createElement("td");
                cateTag.innerText = vo.categoryName;
                trTag.appendChild(cateTag);
                
                const titleTag = document.createElement("td");
                titleTag.innerText = vo.title;
                trTag.appendChild(titleTag);

                titleTag.innerHTML = `<a href='/board/detail?bno=${vo.no}'>${vo.title}</a>`;

                const writerTag = document.createElement("td");
                writerTag.innerText = vo.writerNick;
                trTag.appendChild(writerTag);
                
                const hitTag = document.createElement("td");
                hitTag.innerText = vo.hit;
                trTag.appendChild(hitTag);
                
                const dateTag = document.createElement("td");
                dateTag.innerText = vo.createDate;
                trTag.appendChild(dateTag);

                tbodyTag.appendChild(trTag);
            };

            const pvo = m.b;
            setPageNav(pvo);
        },
        fail : function(){
            alert("게시글 목록조회 실패(관리자에게 문의하세요)");
        }

    });

}

function setPageNav(pvo){

    const pageArea = document.querySelector(".page-area");
    pageArea.innerHTML = "";

    if(pvo.startPage != 1){

        const aTag = document.createElement("a");

        aTag.setAttribute("href",`/board/list?pno=${pvo.startPage-1}`)
        aTag.innerText = "<";
    
        pageArea.appendChild(aTag);

    }

    for(let i = pvo.startPage; i<=pvo.endPage; i++){

        const aTag = document.createElement("a");

        aTag.setAttribute("href",`/board/list?pno=${i}`)
        aTag.innerText = i;
    
        pageArea.appendChild(aTag);

    }    
    
    if(pvo.endPage != pvo.maxPage){

        const aTag = document.createElement("a");

        aTag.setAttribute("href",`/board/list?pno=${pvo.endPage+1}`)
        aTag.innerText = ">";
    
        pageArea.appendChild(aTag);

    }

}

setBoardList();

function handleSearchType(x){

    // 요소 가져오기 -  제목 검색
    const titleTag = document.querySelector("input[name=searchValue]");
    // 요소 가져오기 -  카테고리 검색
    const categoryTag = document.querySelector("select[name=searchValue]");

    // searchType 값에 따라서 searchValue 항목 중 하나를 disabled 상태로 만든다
    if(x.value == "title"){
        titleTag.removeAttribute("disabled");
        categoryTag.setAttribute("disabled",true);
    }
    else{
        titleTag.setAttribute("disabled",true);
        categoryTag.removeAttribute("disabled");;
    }

}

function submitSearchForm(){
    
    const searchType = document.querySelector("select[name='searchType']").value;
    let searchValue = "";
    if(searchType == "title"){
        searchValue = document.querySelector("input[name='searchValue']").value;
    }
    else{
        searchValue = document.querySelector("select[name='searchValue']").value;
    }

    setBoardList(searchType,searchValue);

    return false; // 기본 이벤트 막기 위한 리턴 값

}
